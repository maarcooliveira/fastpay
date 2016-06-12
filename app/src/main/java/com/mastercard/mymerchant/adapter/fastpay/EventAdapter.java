package com.mastercard.mymerchant.adapter.fastpay;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.koushikdutta.ion.Ion;
import com.mastercard.mymerchant.R;
import com.mastercard.mymerchant.model.fastpay.Event;

import java.util.ArrayList;

/**
 * SmartPay project
 * Created by marco on 11/06/2016.
 */

public class EventAdapter extends ArrayAdapter<Event> {
    private Activity context;
    private ArrayList<Event> events;

    public EventAdapter(Activity context, ArrayList<Event> events) {
        super(context, R.layout.fp_user_event_item, events);

        this.context = context;
        this.events = events;
    }

    public View getView(int position, View view, ViewGroup parent) {
        LayoutInflater inflater = context.getLayoutInflater();
        View rowView = inflater.inflate(R.layout.fp_user_event_item, null, true);

        ImageView banner = (ImageView) rowView.findViewById(R.id.event_banner);
        TextView name = (TextView) rowView.findViewById(R.id.event_name);
        TextView date = (TextView) rowView.findViewById(R.id.event_date);

        // Ion lib for image download and cache - https://github.com/koush/ion
        Ion.with(banner)
//                .placeholder(R.mipmap.smartphone)
//                .error(R.mipmap.smartphone)
                .load(events.get(position).getBannerUrl());

        name.setText(events.get(position).getName());
        date.setText(events.get(position).getStartTime());

        return rowView;
    }
}
