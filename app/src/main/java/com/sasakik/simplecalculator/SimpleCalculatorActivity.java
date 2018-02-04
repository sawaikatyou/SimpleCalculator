/*
 * *
 *  SimpleCalculatorActivity
 *
 *  Copyright (c) 2018. sasakik
 *
 *  This software is released under the MIT License.
 *  http://opensource.org/licenses/mit-license.php
 *
 */

package com.sasakik.simplecalculator;

import android.os.Handler;
import android.os.HandlerThread;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.sasakik.simplecalculator.model.CalculatorModel;
import com.sasakik.simplecalculator.view.SCViewFeedbackListener;

/**
 * Activityメインクラス
 */
public class SimpleCalculatorActivity extends AppCompatActivity implements SCViewFeedbackListener {

    public static final String TAG = "SimpleCalculator";
    CalculatorModel mCalculatorModel = new CalculatorModel();
    TextView mLabel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_simple_calculator);
        mLabel = findViewById(R.id.disp_num);
        mCalculatorModel.setFeedBackTarget(this);
    }

    public void sendMessage(View view) {
        Log.d(TAG, "sendMessage");

        int id = view.getId();
        Log.d(TAG, "id = " + id);
        mCalculatorModel.process(id);
    }

    @Override
    public void onUpdate(String value) {
        mLabel.setText(value);
    }

}
