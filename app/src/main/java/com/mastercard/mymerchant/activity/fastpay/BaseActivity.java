package com.mastercard.mymerchant.activity.fastpay;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

/**
 * SmartPay project
 * Created by marco on 12/06/2016.
 */

public class BaseActivity extends AppCompatActivity {

    SharedPreferences prefs;
    Activity context;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        prefs = getPreferences(Context.MODE_PRIVATE);
        context = this;
    }
}
