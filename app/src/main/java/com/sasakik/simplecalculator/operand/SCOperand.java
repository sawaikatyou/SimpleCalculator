/*
 * Copyright (c) 2018. Lorem ipsum dolor sit amet, consectetur adipiscing elit.
 * Morbi non lorem porttitor neque feugiat blandit. Ut vitae ipsum eget quam lacinia accumsan.
 * Etiam sed turpis ac ipsum condimentum fringilla. Maecenas magna.
 * Proin dapibus sapien vel ante. Aliquam erat volutpat. Pellentesque sagittis ligula eget metus.
 * Vestibulum commodo. Ut rhoncus gravida arcu.
 */

package com.sasakik.simplecalculator.operand;

import android.util.Log;

import java.math.BigDecimal;

/**
 * Created by sasakik on 2018/01/03.
 */

public class SCOperand {

    public static final String TAG = "SCOperand";

    /* データ値本体 */
    protected boolean mLastCharacterIsDot;
    protected BigDecimal mValue;

    public SCOperand() {
        mValue = new BigDecimal(0);
        mLastCharacterIsDot = false;
    }

    public SCOperand(String inputsource) {
        try {
            mValue = new BigDecimal(inputsource);
            if (inputsource.endsWith(".")) {
                mLastCharacterIsDot = true;
            }
        } catch (NumberFormatException e) {
            mValue = new BigDecimal(0);
            Log.e(TAG, "constructor failed. invalid inputsource=" + inputsource);
        }
    }

    public SCOperand(BigDecimal input) {
        mValue = input;
        mLastCharacterIsDot = false;
    }

    public SCOperand(int input) {
        mValue = new BigDecimal(input);
        mLastCharacterIsDot = false;
    }

    public double getdouble() {
        return mValue.doubleValue();
    }

    public BigDecimal getValue() {
        return mValue;
    }

    public String toString() {
        return mValue.toString();
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof SCOperand) {
            SCOperand obj_scoperand = (SCOperand) obj;
            return (mValue.compareTo(obj_scoperand.getValue()) == 0);
            // return mValue.equals(obj_scoperand.getValue());
        }

        return false;
    }

    public boolean containsLastDot() {
        return mLastCharacterIsDot;
    }
}
