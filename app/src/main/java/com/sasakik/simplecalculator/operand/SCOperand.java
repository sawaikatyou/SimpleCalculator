/**
 * SCOperand.java
 * <p>
 * Copyright (c) 2018 sasakik
 * <p>
 * This software is released under the MIT License.
 * http://opensource.org/licenses/mit-license.php
 */
package com.sasakik.simplecalculator.operand;

import android.util.Log;

import java.math.BigDecimal;

/**
 * 演算モデル
 * <p>
 * Created by sasakik on 2018/01/03.
 */
public class SCOperand {

    public static final String TAG = "SCOperand";

    /* データ値本体 */
    protected BigDecimal mValue;

    public SCOperand() {
        mValue = new BigDecimal(0);
    }

    public SCOperand(String inputsource) {
        update(inputsource);
    }

    public SCOperand(BigDecimal input) {
        mValue = input;
    }

    public SCOperand(int input) {
        mValue = new BigDecimal(input);
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

    public void update(String input) {
        String parameter = input;
        if (mValue != null) {
            parameter = mValue.toString() + input;
        }
        try {
            mValue = new BigDecimal(input);
        } catch (NumberFormatException e) {
            mValue = new BigDecimal(0);
            Log.e(TAG, "constructor failed. invalid parameter=" + parameter);
        }
    }
}
