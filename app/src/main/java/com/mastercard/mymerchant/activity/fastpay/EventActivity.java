package com.mastercard.mymerchant.activity.fastpay;

import android.os.Bundle;
import android.view.View;
import android.widget.RelativeLayout;

import com.mastercard.mymerchant.adapter.fastpay.TransactionAdapter;

public class EventActivity extends BaseActivity {

    TransactionAdapter transactionAdapter;
    private RelativeLayout mLayoutBuyWithMasterPass;
    final String TAG = "EventActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.fp_activity_event);
//
//        final ArrayList<Transaction> transactions = new ArrayList<>();
//
//        transactions.add(new Transaction("11/06/2016", "12:40", 6.90));
//        transactions.add(new Transaction("11/06/2016", "17:27", 10.90));
//        transactions.add(new Transaction("11/06/2016", "21:05", 15.00));
//        transactions.add(new Transaction("11/06/2016", "23:49", 5.00));
//        transactions.add(new Transaction("12/06/2016", "01:02", 17.00));
//
//        final ListView transactionList = (ListView) findViewById(R.id.event_transactions);
//        transactionAdapter = new TransactionAdapter(context, transactions);
//        transactionList.setAdapter(transactionAdapter);
//
//        mLayoutBuyWithMasterPass = (RelativeLayout) findViewById(R.id.layout_buy_with_masterpass);

    }

    public void addItem(final View view) {
//        final Intent intent = new Intent(this, NewItemActivity.class);
////        intent.putExtra("event", event);
//        startActivity(intent);
    }


}
