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

    /**
     * データ値本体
     */
    protected BigDecimal mValue;

    /**
     * 次回"."付与フラグ
     */
    protected boolean mContainsNextDot;

    public SCOperand() {
        mValue = new BigDecimal(0);
        mContainsNextDot = false;
    }

    public SCOperand(String inputsource) {
        try {
            mValue = new BigDecimal(inputsource);
            if (inputsource.endsWith(".")) {
                mContainsNextDot = true;
            }
        } catch (NumberFormatException e) {
            mValue = new BigDecimal(0);
            Log.e(TAG, "constructor failed. invalid inputsource=" + inputsource);
        }
    }

    public SCOperand(BigDecimal input) {
        mValue = input;
        mContainsNextDot = false;
    }

    public SCOperand(int input) {
        mValue = new BigDecimal(input);
        mContainsNextDot = false;
    }

    public double getdouble() {
        return mValue.doubleValue();
    }

    public BigDecimal getValue() {
        return mValue;
    }

    /**
     * 次回"."付与フラグ返却
     *
     * @return
     */
    public boolean containsNextDot() {
        return mContainsNextDot;
    }

    /**
     * "."の二重要求にならないかの判定<BR>
     * 次回"."付与フラグがONか、もしくはすでに小数点が<BR>
     * 含まれている状態で"."が要求されるとNGと判定する
     *
     * @param input 更新要求値
     * @return true：二重要求になっている／false：なっていない
     */
    public boolean isDotOverLaps(String input) {
        if (input != null && !input.isEmpty()) {
            if (mContainsNextDot && input.contains(".")) {
                return true;
            }
            if (isValueContainsDot() && input.contains(".")) {
                return true;
            }
        }
        return false;
    }

    /**
     * 現在のデータ値が小数点含みか否かを返却
     *
     * @return true：含まれている／false：含まれていない
     */
    public boolean isValueContainsDot() {
        return (mValue.scale() > 0);
    }

    /**
     * データ値更新
     *
     * @param input 更新要求値
     */
    public void update(String input) {
        String parameter = input;
        /* "."が重複するケースは扱いが大変になるので無視 */
        if (!isDotOverLaps(input)) {
            if (mValue != null) {
                if (mContainsNextDot) {
                    parameter = mValue.toString() + "." + input;
                    mContainsNextDot = false;
                } else {
                    parameter = mValue.toString() + input;
                }
            }

            /* "."のみである場合は"."を付与 */
            if (!mContainsNextDot && input.equals(".") && !isValueContainsDot()) {
                mContainsNextDot = true;
            }

            try {
                mValue = new BigDecimal(parameter);
            } catch (NumberFormatException e) {
                mValue = new BigDecimal(0);
                Log.e(TAG, "update failed. invalid parameter=" + parameter);
            }
        }
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
}
