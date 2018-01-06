package com.sasakik.simplecalculator.model.operator;

import android.test.AndroidTestCase;

import org.junit.Test;

import com.sasakik.simplecalculator.operand.SCOperand;
import com.sasakik.simplecalculator.operator.OperatorAdd;
import com.sasakik.simplecalculator.operator.OperatorMinus;
import com.sasakik.simplecalculator.operator.SCOperator;

/**
 * Created by sasakik on 2018/01/03.
 */

public class OperatorMinusTest extends AndroidTestCase {

    @Test
    public void testEval() {
        SCOperator target = new OperatorMinus();
        final SCOperand valueA = new SCOperand(1);
        final SCOperand valueB = new SCOperand(2);
        final SCOperand valueC = new SCOperand(3);

        assertEquals(new SCOperand(-1), target.eval(valueA,valueB));
        assertEquals(new SCOperand(-2), target.eval(valueA,valueC));
        assertEquals(new SCOperand(-1), target.eval(valueB,valueC));
        assertEquals(new SCOperand(1), target.eval(valueC,valueB));
    }

}
