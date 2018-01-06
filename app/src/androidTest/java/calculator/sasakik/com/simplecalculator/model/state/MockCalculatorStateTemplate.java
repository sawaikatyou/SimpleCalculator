/*
 * Copyright (c) 2018. Lorem ipsum dolor sit amet, consectetur adipiscing elit.
 * Morbi non lorem porttitor neque feugiat blandit. Ut vitae ipsum eget quam lacinia accumsan.
 * Etiam sed turpis ac ipsum condimentum fringilla. Maecenas magna.
 * Proin dapibus sapien vel ante. Aliquam erat volutpat. Pellentesque sagittis ligula eget metus.
 * Vestibulum commodo. Ut rhoncus gravida arcu.
 */

package calculator.sasakik.com.simplecalculator.model.state;

import calculator.sasakik.com.simplecalculator.model.CalculatorModel;
import calculator.sasakik.com.simplecalculator.operand.SCOperand;
import calculator.sasakik.com.simplecalculator.operator.SCOperator;
import calculator.sasakik.com.simplecalculator.state.CalculatorStateTemplate;

/**
 * Created by sasakik on 2018/01/06.
 */

public class MockCalculatorStateTemplate extends CalculatorStateTemplate {
    public SCOperand mTestOperand;

    MockCalculatorStateTemplate() {
        mTestOperand = new SCOperand(0);
    }

    @Override
    protected SCOperand getTargetOperand(CalculatorModel model) {
        if(model != null) {
            return mTestOperand;
        }

        return null;
    }

    @Override
    protected void setTargetOperand(CalculatorModel model, SCOperand newvalue) {
        mTestOperand = newvalue;
    }

    @Override
    public void processNumber(Character character, CalculatorModel model) {

    }

    @Override
    public void processOperator(SCOperator operator, CalculatorModel model) {

    }

    @Override
    public void processResult(CalculatorModel model) {

    }

    @Override
    public void processDecrement(CalculatorModel model) {
        doDecrementCommon(model);
    }

    @Override
    public void processPercent(CalculatorModel model) {
        setTargetOperand(model, doPercentCommon(model));
    }
}
