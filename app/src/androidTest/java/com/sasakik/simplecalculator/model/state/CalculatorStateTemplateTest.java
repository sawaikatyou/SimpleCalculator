/*
 * Copyright (c) 2018. Lorem ipsum dolor sit amet, consectetur adipiscing elit.
 * Morbi non lorem porttitor neque feugiat blandit. Ut vitae ipsum eget quam lacinia accumsan.
 * Etiam sed turpis ac ipsum condimentum fringilla. Maecenas magna.
 * Proin dapibus sapien vel ante. Aliquam erat volutpat. Pellentesque sagittis ligula eget metus.
 * Vestibulum commodo. Ut rhoncus gravida arcu.
 */

package com.sasakik.simplecalculator.model.state;

import android.test.AndroidTestCase;

import org.junit.Test;

import com.sasakik.simplecalculator.model.CalculatorModel;
import com.sasakik.simplecalculator.operand.SCOperand;
import com.sasakik.simplecalculator.state.CalculatorStateTemplate;

/**
 * Created by sasakik on 2018/01/06.
 */
public class CalculatorStateTemplateTest extends AndroidTestCase {

    // テスト専用サブクラス
    static class CalculatorModelForTest extends CalculatorModel {
        public boolean mCalled_init = false;

        @Override
        public void init() {
            mCalled_init = true;
            super.init();
        }
    }


    /* -------------------------------- */
    /* ここからテストケース本体         */
    /* -------------------------------- */

    /* 123.45 が1文字づつ消えていくテスト */
    @Test
    public void testProcessDecrementCommon() {
        CalculatorModel model = new CalculatorModel();
        MockCalculatorStateTemplate target = new MockCalculatorStateTemplate();
        target.mTestOperand = new SCOperand("123.45");
        assertEquals("123.45", target.getTargetOperand(model).toString());

        target.processDecrement(model);
        assertEquals("123.4", target.getTargetOperand(model).toString());

        target.processDecrement(model);
        assertEquals("123", target.getTargetOperand(model).toString());

        target.processDecrement(model);
        assertEquals("12", target.getTargetOperand(model).toString());

        target.processDecrement(model);
        assertEquals("1", target.getTargetOperand(model).toString());

        target.processDecrement(model);
        assertEquals("0", target.getTargetOperand(model).toString());

        target.processDecrement(model);
        assertEquals("0", target.getTargetOperand(model).toString());
    }

    /* 100分率 10000 → 100 → 1 */
    @Test
    public void testprocessPercentCommon() {
        CalculatorModel model = new CalculatorModel();
        MockCalculatorStateTemplate target = new MockCalculatorStateTemplate();

        /* 10000 → 100 → 1 */
        target.mTestOperand = new SCOperand("10000");
        assertEquals("10000", target.getTargetOperand(model).toString());
        target.processPercent(model);
        assertEquals("100", target.getTargetOperand(model).toString());
        target.processPercent(model);
        assertEquals("1", target.getTargetOperand(model).toString());

        /* 123.45 → 1.23 → 0 */
        target.mTestOperand = new SCOperand("123.45");
        assertEquals("123.45", target.getTargetOperand(model).toString());
        target.processPercent(model);
        assertEquals("1.2345", target.getTargetOperand(model).toString());
        target.processPercent(model);
        assertEquals("0.012345", target.getTargetOperand(model).toString());

        /* NULLケース */
        target.processPercent(null);
        assertEquals("0", target.getTargetOperand(model).toString());
    }

    // SCOperand が飛ぶこと
    @Test
    public void testClear() {
        CalculatorModelForTest model = new CalculatorModelForTest();
        MockCalculatorStateTemplate target = new MockCalculatorStateTemplate();
        target.mTestOperand = new SCOperand("123.45");

        assertEquals("123.45", target.mTestOperand.toString());
        target.processClear(model);
        assertEquals("0", target.mTestOperand.toString());
    }

    // 全状態クリア関数が実施されること
    @Test
    public void testAllClear() {
        CalculatorModelForTest model = new CalculatorModelForTest();
        MockCalculatorStateTemplate target = new MockCalculatorStateTemplate();
        assertFalse(model.mCalled_init);
        target.processAllClear(model);
        assertTrue(model.mCalled_init);
    }

    public void testOutput() {
        CalculatorModelForTest model = new CalculatorModelForTest();
        MockCalculatorStateTemplate target = new MockCalculatorStateTemplate();
        assertTrue(target.output(model).isEmpty());
    }
}
