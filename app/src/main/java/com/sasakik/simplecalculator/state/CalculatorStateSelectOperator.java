package com.sasakik.simplecalculator.state;

import com.sasakik.simplecalculator.model.CalculatorModel;
import com.sasakik.simplecalculator.operand.SCOperand;
import com.sasakik.simplecalculator.operator.SCOperator;

/**
 * Created by sasakik on 2018/01/06.
 */

public class CalculatorStateSelectOperator extends CalculatorStateTemplate {
    public static final String TAG = "CalculatorStateSelectOperator";

    @Override
    protected SCOperand getTargetOperand(CalculatorModel model) {
        return model.mOperandA;
    }

    @Override
    protected void setTargetOperand(CalculatorModel model, SCOperand newvalue) {
        /* 何もしない*/
    }

    @Override
    public void processNumber(Character character, CalculatorModel model) {
        CalculatorState newState = new CalculatorStateInputB();
        model.setStatus(newState);

        /* 打ち込まれた値で次回変数を初期化 */
        model.mOperandB = new SCOperand(String.valueOf(character));
    }

    @Override
    public void processOperator(SCOperator operator, CalculatorModel model) {
        model.mOperator = operator;
    }

    @Override
    public void processResult(CalculatorModel model) {
        model.setStatus(new CalculatorStateOutputResult());
    }

    @Override
    public void processDecrement(CalculatorModel model) {
        /* 何もしない */
    }

    @Override
    public void processPercent(CalculatorModel model) {
        model.mOperandB = doPercentCommon(model);
        model.setStatus(new CalculatorStateInputB());
    }

    @Override
    public String output(CalculatorModel model) {
        return model.mOperandA.toString();
    }
}
