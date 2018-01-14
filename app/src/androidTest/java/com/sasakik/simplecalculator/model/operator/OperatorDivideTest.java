/*
 * *
 *  OperatorDivideTest
 *
 *  Copyright (c) 2018. sasakik
 *
 *  This software is released under the MIT License.
 *  http://opensource.org/licenses/mit-license.php
 *
 */

package com.sasakik.simplecalculator.model.operator;

import android.test.AndroidTestCase;
import android.util.Log;

import org.junit.Test;

import com.sasakik.simplecalculator.operand.SCOperand;
import com.sasakik.simplecalculator.operator.OperatorDivide;
import com.sasakik.simplecalculator.operator.OperatorMinus;
import com.sasakik.simplecalculator.operator.SCOperator;

/**
 * Created by sasakik on 2018/01/03.
 */

public class OperatorDivideTest extends AndroidTestCase {

    @Test
    public void testEval() {
        SCOperator target = new OperatorDivide();
        final SCOperand valueA = new SCOperand(1);
        final SCOperand valueB = new SCOperand(2);
        final SCOperand valueC = new SCOperand(3);

        assertEquals(new SCOperand("0.5"), target.eval(valueA,valueB));
        assertEquals(new SCOperand("0.500"), target.eval(valueA,valueB));
        assertEquals(new SCOperand("0.333"), target.eval(valueA,valueC));
        assertEquals(new SCOperand("0.666"), target.eval(valueB,valueC));
        assertEquals(new SCOperand("1.500"), target.eval(valueC,valueB));
    }

}
