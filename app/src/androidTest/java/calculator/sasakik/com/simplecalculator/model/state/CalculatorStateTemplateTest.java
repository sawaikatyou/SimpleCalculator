/*
 * Copyright (c) 2018. Lorem ipsum dolor sit amet, consectetur adipiscing elit.
 * Morbi non lorem porttitor neque feugiat blandit. Ut vitae ipsum eget quam lacinia accumsan.
 * Etiam sed turpis ac ipsum condimentum fringilla. Maecenas magna.
 * Proin dapibus sapien vel ante. Aliquam erat volutpat. Pellentesque sagittis ligula eget metus.
 * Vestibulum commodo. Ut rhoncus gravida arcu.
 */

package calculator.sasakik.com.simplecalculator.model.state;

import android.test.AndroidTestCase;

import org.junit.Test;

import calculator.sasakik.com.simplecalculator.model.CalculatorModel;
import calculator.sasakik.com.simplecalculator.operand.SCOperand;

/**
 * Created by sasakik on 2018/01/06.
 */

public class CalculatorStateTemplateTest extends AndroidTestCase {

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

    @Test
    public void testprocessPercentCommon() {
        CalculatorModel model = new CalculatorModel();
        MockCalculatorStateTemplate target = new MockCalculatorStateTemplate();
        target.mTestOperand = new SCOperand("10000");

        assertEquals("10000", target.getTargetOperand(model).toString());
        target.processPercent(model);
        assertEquals("100", target.getTargetOperand(model).toString());
        target.processPercent(model);
        assertEquals("1", target.getTargetOperand(model).toString());

        target.mTestOperand = new SCOperand("123.45");
        assertEquals("123.45", target.getTargetOperand(model).toString());
        target.processPercent(model);
        assertEquals("1.23", target.getTargetOperand(model).toString());

        assertEquals("1.23", target.getTargetOperand(model).toString());
        target.processPercent(null);
        assertEquals("0", target.getTargetOperand(model).toString());
    }

    static class CalculatorModelForTest extends CalculatorModel {
        public boolean mCalled_init = false;

        @Override
        public void init() {
            mCalled_init = true;
            super.init();
        }
    }

    @Test
    public void testClear() {
        CalculatorModelForTest model = new CalculatorModelForTest();
        MockCalculatorStateTemplate target = new MockCalculatorStateTemplate();
        target.mTestOperand = new SCOperand("123.45");

        assertEquals("123.45", target.mTestOperand.toString());
        target.processClear(model);
        assertEquals("0", target.mTestOperand.toString());
    }

    @Test
    public void testAllClear() {
        CalculatorModelForTest model = new CalculatorModelForTest();
        MockCalculatorStateTemplate target = new MockCalculatorStateTemplate();
        assertFalse(model.mCalled_init);
        target.processAllClear(model);
        assertTrue(model.mCalled_init);
    }
}
