/*
 * *
 *  CalculatorStateSelectOperatorTest
 *
 *  Copyright (c) 2018. sasakik
 *
 *  This software is released under the MIT License.
 *  http://opensource.org/licenses/mit-license.php
 *
 */

package com.sasakik.simplecalculator.model.state;

import android.test.AndroidTestCase;

import com.sasakik.simplecalculator.model.CalculatorModel;
import com.sasakik.simplecalculator.operand.SCOperand;
import com.sasakik.simplecalculator.operator.OperatorAdd;
import com.sasakik.simplecalculator.operator.OperatorMinus;
import com.sasakik.simplecalculator.state.CalculatorStateInputB;
import com.sasakik.simplecalculator.state.CalculatorStateOutputResult;
import com.sasakik.simplecalculator.state.CalculatorStateSelectOperator;

import org.junit.Test;

/**
 * Created by mega_ on 2018/02/04.
 */
public class CalculatorStateSelectOperatorTest extends AndroidTestCase {

    // テスト専用サブクラス
    static class CalculatorStateSelectOperatorForTest extends CalculatorStateSelectOperator {
        public boolean mCalled_doDecrementCommon = false;
        public boolean mCalled_doPercentCommon = false;

        @Override
        protected void doDecrementCommon(CalculatorModel model) {
            mCalled_doDecrementCommon = true;
            super.doDecrementCommon(model);
        }

        @Override
        protected SCOperand doPercentCommon(CalculatorModel model) {
            mCalled_doPercentCommon = true;
            return super.doPercentCommon(model);
        }

        SCOperand call_getTargetOperand(CalculatorModel model) {
            return getTargetOperand(model);
        }

        void call_setTargetOperand(CalculatorModel model, SCOperand newvalue) {
            setTargetOperand(model, newvalue);
        }
    }

    /* -------------------------------- */
    /* ここからテストケース本体         */
    /* -------------------------------- */
    public void test_getTargetOperand() {
        CalculatorModel model = new CalculatorModel();
        CalculatorStateSelectOperatorForTest target = new CalculatorStateSelectOperatorForTest(); // ←テスト専用クラス
        model.mOperandA = new SCOperand("123");
        model.mOperandB = new SCOperand("456");

        /* Aのほうが返ってくることを確認 */
        assertEquals(target.call_getTargetOperand(model), model.mOperandA);
        assertEquals(target.call_getTargetOperand(model).toString(), "123");
    }

    public void test_setTargetOperand() {
        CalculatorModel model = new CalculatorModel();
        CalculatorStateSelectOperatorForTest target = new CalculatorStateSelectOperatorForTest(); // ←テスト専用クラス
        model.mOperandA = new SCOperand("123");
        model.mOperandB = new SCOperand("456");

        /* 更新がスルーされることを確認 */
        target.call_setTargetOperand(model, new SCOperand("789"));
        assertEquals(target.call_getTargetOperand(model), model.mOperandA);
        assertEquals(model.mOperandA.toString(), "123");
        assertEquals(model.mOperandB.toString(), "456");
    }

    public void test_processNumber() {
        CalculatorModel model = new CalculatorModel();
        CalculatorStateSelectOperator target = new CalculatorStateSelectOperator();
        model.setStatus(target);
        model.mOperator = new OperatorAdd();
        target.processNumber('1', model);

        /* 状態が変化すること */
        assertEquals(model.mOperandB.toString(), "1");
        assertEquals(model.getStatus().getClass(), CalculatorStateInputB.class);
    }

    public void test_processOperator() {
        CalculatorModel model = new CalculatorModel();
        CalculatorStateSelectOperator target = new CalculatorStateSelectOperator();
        model.setStatus(target);
        model.mOperator = new OperatorAdd();
        target.processOperator(new OperatorMinus(), model);

        /* 状態が変化すること */
        assertEquals(model.mOperator.getClass(), OperatorMinus.class);
    }

    public void test_processResult() {
        CalculatorModel model = new CalculatorModel();
        CalculatorStateSelectOperatorForTest target = new CalculatorStateSelectOperatorForTest(); // ←テスト専用クラス
        model.setStatus(target);
        target.processResult(model);

        /* 状態が変化すること */
        assertEquals(model.getStatus().getClass(), CalculatorStateOutputResult.class);
    }

    public void test_processDecrement() {
        CalculatorModel model = new CalculatorModel();
        CalculatorStateSelectOperatorForTest target = new CalculatorStateSelectOperatorForTest(); // ←テスト専用クラス
        model.setStatus(target);
        target.processDecrement(model);

        /* フラグがTrueになら”ない”ことを確認 */
        assertFalse(target.mCalled_doDecrementCommon);
    }

    public void test_processPercent() {
        CalculatorModel model = new CalculatorModel();
        CalculatorStateSelectOperatorForTest target = new CalculatorStateSelectOperatorForTest(); // ←テスト専用クラス
        model.mOperandA = new SCOperand("100");
        model.mOperandB = new SCOperand("100");

        /* mOperandB の側だけ変更されることを確認 */
        target.processPercent(model);
        assertTrue(target.mCalled_doPercentCommon);
        assertEquals(model.mOperandA.toString(), "100");
        assertNotSame(model.mOperandB.toString(), "100");
    }

    public void test_output() {
        CalculatorModel model = new CalculatorModel();
        CalculatorStateSelectOperator target = new CalculatorStateSelectOperator(); // ←テスト専用クラス
        model.mOperandA = new SCOperand("123");
        model.mOperandB = new SCOperand("456");

        /* mOperandAの値が返ってくることを確認 */
        assertEquals(target.output(model), "123");
    }
}
