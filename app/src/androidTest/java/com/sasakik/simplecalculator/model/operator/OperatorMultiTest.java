package com.sasakik.simplecalculator.model.operator;

import android.test.AndroidTestCase;

import org.junit.Test;

import com.sasakik.simplecalculator.operand.SCOperand;
import com.sasakik.simplecalculator.operator.OperatorMinus;
import com.sasakik.simplecalculator.operator.OperatorMulti;
import com.sasakik.simplecalculator.operator.SCOperator;

/**
 * Created by sasakik on 2018/01/03.
 */

public class OperatorMultiTest extends AndroidTestCase {

    @Test
    public void testEval() {
        SCOperator target = new OperatorMulti();
        final SCOperand valueA = new SCOperand(1);
        final SCOperand valueB = new SCOperand(2);
        final SCOperand valueC = new SCOperand(3);

        assertEquals(new SCOperand(2), target.eval(valueA,valueB));
        assertEquals(new SCOperand(3), target.eval(valueA,valueC));
        assertEquals(new SCOperand(6), target.eval(valueB,valueC));
        assertEquals(new SCOperand(6), target.eval(valueC,valueB));
    }

}
