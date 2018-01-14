/*
 * *
 *  CalculatorStateInputA
 *
 *  Copyright (c) 2018. sasakik
 *
 *  This software is released under the MIT License.
 *  http://opensource.org/licenses/mit-license.php
 *
 */

package com.sasakik.simplecalculator.state;

import com.sasakik.simplecalculator.model.CalculatorModel;
import com.sasakik.simplecalculator.operand.SCOperand;
import com.sasakik.simplecalculator.operator.SCOperator;

/**
 * Created by sasakik on 2018/01/06.
 */

public class CalculatorStateInputA extends CalculatorStateTemplate {
    public static final String TAG = "CalculatorStateInputA";

    @Override
    public void processNumber(Character character, CalculatorModel model) {
        SCOperand old_opeA = model.mOperandA;
        model.mOperandA = append(character, old_opeA);
    }

    @Override
    public void processOperator(SCOperator operator, CalculatorModel model) {
        model.mOperator = operator;
        model.setStatus(new CalculatorStateSelectOperator());
    }

    @Override
    public void processResult(CalculatorModel model) {
        model.setStatus(new CalculatorStateOutputResult());
    }

    @Override
    public void processDecrement(CalculatorModel model) {
        doDecrementCommon(model);
    }

    @Override
    public void processPercent(CalculatorModel model) {
        model.mOperandA = doPercentCommon(model);
    }

    @Override
    public String output(CalculatorModel model) {
        return model.mOperandA.toString();
    }

    @Override
    protected SCOperand getTargetOperand(CalculatorModel model) {
        return model.mOperandA;
    }

    @Override
    protected void setTargetOperand(CalculatorModel model, SCOperand newvalue) {
        model.mOperandA = newvalue;
    }

}
