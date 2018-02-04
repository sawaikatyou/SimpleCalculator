/*
 * *
 *  CalculatorStateInputBTest
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
import com.sasakik.simplecalculator.state.CalculatorStateInputA;
import com.sasakik.simplecalculator.state.CalculatorStateInputB;
import com.sasakik.simplecalculator.state.CalculatorStateOutputResult;
import com.sasakik.simplecalculator.state.CalculatorStateSelectOperator;

import org.junit.Test;

/**
 * Created by mega_ on 2018/02/04.
 */
public class CalculatorStateInputBTest extends AndroidTestCase {

    // テスト専用サブクラス
    static class CalculatorStateInputBForTest extends CalculatorStateInputB {
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

    @Test
    public void test_processNumber() {
        CalculatorModel model = new CalculatorModel();
        CalculatorStateInputB target = new CalculatorStateInputB();
        model.mOperandA = new SCOperand("0");
        model.mOperandB = new SCOperand("0");

        /* mOperandB の側だけ変更されることを確認 */
        target.processNumber('1', model);
        assertEquals(model.mOperandA.toString(), "0");
        assertEquals(model.mOperandB.toString(), "1");
    }

    @Test
    public void test_processOperator() {
        CalculatorModel model = new CalculatorModel();
        CalculatorStateInputB target = new CalculatorStateInputB();
        model.mOperandA = new SCOperand("1");
        model.mOperandB = new SCOperand("1");
        model.mOperator = new OperatorAdd();
        model.setStatus(target);
        target.processOperator(new OperatorMinus(), model);

        /* 状態が変化すること */
        assertEquals(model.mOperandA.toString(), "2");
        assertEquals(model.mOperator.getClass(), OperatorMinus.class);
        assertEquals(model.getStatus().getClass(), CalculatorStateSelectOperator.class);
    }

    @Test
    public void test_processResult() {
        CalculatorModel model = new CalculatorModel();
        CalculatorStateInputB target = new CalculatorStateInputB();
        model.mOperandA = new SCOperand("1");
        model.mOperandB = new SCOperand("1");
        model.mOperator = new OperatorAdd();
        model.setStatus(target);
        target.processResult(model);

        /* 状態が変化すること */
        assertEquals(model.mOperandA.toString(), "2");
        assertEquals(model.getStatus().getClass(), CalculatorStateOutputResult.class);
    }

    public void test_processDecrement() {
        CalculatorModel model = new CalculatorModel();
        CalculatorStateInputBForTest target = new CalculatorStateInputBForTest(); // ←テスト専用クラス
        model.setStatus(target);
        target.processDecrement(model);

        assertTrue(target.mCalled_doDecrementCommon);
    }


    public void test_processPercent() {
        CalculatorModel model = new CalculatorModel();
        CalculatorStateInputBForTest target = new CalculatorStateInputBForTest(); // ←テスト専用クラス
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
        CalculatorStateInputBForTest target = new CalculatorStateInputBForTest(); // ←テスト専用クラス
        model.mOperandA = new SCOperand("123");
        model.mOperandB = new SCOperand("456");

        /* mOperandAの値が返ってくることを確認 */
        assertEquals(target.output(model), "456");
    }

    public void test_getTargetOperand() {
        CalculatorModel model = new CalculatorModel();
        CalculatorStateInputBForTest target = new CalculatorStateInputBForTest(); // ←テスト専用クラス
        model.mOperandA = new SCOperand("123");
        model.mOperandB = new SCOperand("456");

        /* Aのほうが返ってくることを確認 */
        assertEquals(target.call_getTargetOperand(model), model.mOperandB);
        assertEquals(target.call_getTargetOperand(model).toString(), "456");
    }

    public void test_setTargetOperand() {
        CalculatorModel model = new CalculatorModel();
        CalculatorStateInputBForTest target = new CalculatorStateInputBForTest(); // ←テスト専用クラス
        model.mOperandA = new SCOperand("123");
        model.mOperandB = new SCOperand("456");

        /* Aのほうが更新されることを確認 */
        target.call_setTargetOperand(model, new SCOperand("789"));
        assertEquals(target.call_getTargetOperand(model), model.mOperandB);
        assertEquals(target.call_getTargetOperand(model).toString(), "789");
    }
}
