/*
 * *
 *  OperatorAddTest
 *
 *  Copyright (c) 2018. sasakik
 *
 *  This software is released under the MIT License.
 *  http://opensource.org/licenses/mit-license.php
 *
 */

package com.sasakik.simplecalculator.model.operator;

import android.test.AndroidTestCase;

import org.junit.Test;

import java.math.BigDecimal;

import com.sasakik.simplecalculator.operand.SCOperand;
import com.sasakik.simplecalculator.operator.OperatorAdd;
import com.sasakik.simplecalculator.operator.SCOperator;

/**
 * Created by sasakik on 2018/01/03.
 */

public class OperatorAddTest extends AndroidTestCase {

    @Test
    public void testEval() {
        OperatorAdd target = new OperatorAdd();
        final SCOperand valueA = new SCOperand(1);
        final SCOperand valueB = new SCOperand(2);
        final SCOperand valueC = new SCOperand(3);

        assertEquals(new SCOperand(3), target.eval(valueA,valueB));
        assertEquals(new SCOperand(4), target.eval(valueA,valueC));
        assertEquals(new SCOperand(5), target.eval(valueB,valueC));
        assertEquals(new SCOperand(5), target.eval(valueC,valueB));
    }
}
