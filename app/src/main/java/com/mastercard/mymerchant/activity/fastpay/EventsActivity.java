package com.mastercard.mymerchant.activity.fastpay;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.facebook.AccessToken;
import com.facebook.GraphRequest;
import com.facebook.GraphResponse;
import com.facebook.HttpMethod;
import com.mastercard.mymerchant.R;
import com.mastercard.mymerchant.activity.CheckoutActivity;
import com.mastercard.mymerchant.adapter.fastpay.EventAdapter;
import com.mastercard.mymerchant.model.fastpay.Event;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.Serializable;
import java.util.ArrayList;

public class EventsActivity extends BaseActivity {

    EventAdapter eventAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fp_activity_events);

        loadEvents();
    }

    private void loadEvents() {
        final ArrayList<Event> events = new ArrayList<>();
        String request = "/" + com.facebook.AccessToken.getCurrentAccessToken().getUserId() + "/events";

        Bundle b = new Bundle();
        b.putString("fields", "name,place,id,start_time,end_time,cover");

        GraphRequest r = new GraphRequest(
                AccessToken.getCurrentAccessToken(),
                request,
                null,
                HttpMethod.GET,
                new GraphRequest.Callback() {
                    @Override
                    public void onCompleted(GraphResponse response) {
                        Log.d("LoginActivity", response.getRequest().toString());

                        try {
                            JSONObject obj = response.getJSONObject();
                            JSONArray data = obj.getJSONArray("data");
                            Log.d("LoginActivity", obj.toString());
                            int i = data.length() >= 4 ? 3 : data.length() - 1;
                            for (; i >= 0; i--) {
                                JSONObject jsonEvent = data.getJSONObject(i);
                                Event event = new Event();
                                event.setName(jsonEvent.getString("name"));
                                event.setId(jsonEvent.getString("id"));
                                event.setBannerUrl(jsonEvent.getJSONObject("cover").getString("source"));
                                event.setLocal(jsonEvent.getJSONObject("place").getString("name"));
                                event.setStartTime(jsonEvent.getString("start_time"));
//                                event.setEndTime(jsonEvent.getString("end_time"));
                                events.add(event);
                            }
                        } catch (Exception e) {
                            Log.e("EventsActivity", "#SHIFT HAPPENED");
                            e.printStackTrace();
                        }
                        final ListView userEventsList = (ListView) findViewById(R.id.user_events);
                        eventAdapter = new EventAdapter(context, events);
                        userEventsList.setAdapter(eventAdapter);
                        userEventsList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                            @Override
                            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                                Intent intent = new Intent(context, CheckoutActivity.class);
                                intent.putExtra("event", (Serializable) parent.getAdapter().getItem(position));
                                startActivity(intent);
                            }
                        });
                    }
                }

        );
        r.setParameters(b);
        r.executeAsync();
    }
}
