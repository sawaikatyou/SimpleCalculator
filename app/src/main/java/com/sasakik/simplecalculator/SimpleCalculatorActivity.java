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

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.sasakik.simplecalculator.model.CalculatorModel;
import com.sasakik.simplecalculator.state.CalculatorState;

/**
 * Activityメインクラス
 *
 */
public class SimpleCalculatorActivity extends AppCompatActivity {

    public static final String TAG = "SimpleCalculator";
    CalculatorModel mCalculatorModel = new CalculatorModel();
    TextView mLabel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_simple_calculator);
        mLabel = findViewById(R.id.disp_num);
    }

    public void sendMessage(View view) {
        Log.d(TAG, "sendMessage");

        int id = view.getId();
        Log.d(TAG, "id = " + id);
        mCalculatorModel.process(id);
        mLabel.setText(mCalculatorModel.getMainWindowCaption());
    }
}
