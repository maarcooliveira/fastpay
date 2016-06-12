package com.mastercard.mymerchant.adapter.fastpay;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.mastercard.mymerchant.R;
import com.mastercard.mymerchant.model.fastpay.Transaction;

import java.util.ArrayList;


/**
 * SmartPay project
 * Created by marco on 12/06/2016.
 */

public class TransactionAdapter extends ArrayAdapter<Transaction> {
    private Activity context;
    private ArrayList<Transaction> transactions;

    public TransactionAdapter(Activity context, ArrayList<Transaction> transactions) {
        super(context, R.layout.fp_transaction_item, transactions);

        this.context = context;
        this.transactions = transactions;
    }

    public View getView(int position, View view, ViewGroup parent) {
        LayoutInflater inflater = context.getLayoutInflater();
        View rowView = inflater.inflate(R.layout.fp_transaction_item, null, true);


        TextView date = (TextView) rowView.findViewById(R.id.transaction_date);
        TextView hour = (TextView) rowView.findViewById(R.id.transaction_time);
        TextView value = (TextView) rowView.findViewById(R.id.transaction_value);

        date.setText(transactions.get(position).getDate());
        hour.setText(transactions.get(position).getHour());
        value.setText("R$ " + transactions.get(position).getValue());

        return rowView;
    }
}
