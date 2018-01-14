/*
 * *
 *  NullCalculatorStateTest
 *
 *  Copyright (c) 2018. sasakik
 *
 *  This software is released under the MIT License.
 *  http://opensource.org/licenses/mit-license.php
 *
 */

package com.sasakik.simplecalculator.model.state;

import android.test.AndroidTestCase;
import android.util.Log;

import com.sasakik.simplecalculator.model.CalculatorModel;
import com.sasakik.simplecalculator.operand.SCOperand;
import com.sasakik.simplecalculator.operator.SCOperator;
import com.sasakik.simplecalculator.state.NullCalculatorState;

import junit.framework.TestResult;

import org.junit.Test;

/**
 * Created by sasakik 2018/01/14.
 */
public class NullCalculatorStateTest extends AndroidTestCase {

    Character mTtestCharacter = new Character('a');
    SCOperator mTestOperator = new SCOperator() {
        @Override
        public SCOperand eval(SCOperand valueA, SCOperand valueB) {
            return null;
        }
    };
    CalculatorModel mTestModel = new CalculatorModel();

    @Test
    public void test_processNumber() {
        NullCalculatorState target = new NullCalculatorState();
        target.processNumber(mTtestCharacter, mTestModel);
    }

    @Test
    public void test_processOperator() {
        NullCalculatorState target = new NullCalculatorState();
        target.processOperator(mTestOperator, mTestModel);
    }

    @Test
    public void test_processDecrement() {
        NullCalculatorState target = new NullCalculatorState();
        target.processDecrement(mTestModel);
    }

    @Test
    public void test_processPercent() {
        NullCalculatorState target = new NullCalculatorState();
        target.processPercent(mTestModel);
    }

    @Test
    public void test_processResult() {
        NullCalculatorState target = new NullCalculatorState();
        target.processResult(mTestModel);
    }

    @Test
    public void test_processClear() {
        NullCalculatorState target = new NullCalculatorState();
        target.processClear(mTestModel);
    }

    @Test
    public void test_processAllClear() {
        NullCalculatorState target = new NullCalculatorState();
        target.processAllClear(mTestModel);
    }

    @Test
    public void test_output() {
        NullCalculatorState target = new NullCalculatorState();
        assertNull(target.output(mTestModel));
    }
}
