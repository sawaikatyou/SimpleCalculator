package com.sasakik.simplecalculator.state;

import com.sasakik.simplecalculator.model.CalculatorModel;
import com.sasakik.simplecalculator.operand.SCOperand;
import com.sasakik.simplecalculator.operator.SCOperator;

/**
 * Created by sasakik on 2018/01/06.
 */

public class CalculatorStateOutputResult extends CalculatorStateTemplate {
    public static final String TAG = "CalculatorStateOutputResult";

    @Override
    protected SCOperand getTargetOperand(CalculatorModel model) {
        return null;
    }

    @Override
    protected void setTargetOperand(CalculatorModel model, SCOperand newvalue) {

    }

    @Override
    public void processNumber(Character character, CalculatorModel model) {
        model.init();

        /* 打ち込まれた値で初期変数を初期化 */
        model.mOperandA = new SCOperand(String.valueOf(character));
    }

    @Override
    public void processOperator(SCOperator operator, CalculatorModel model) {
        model.mOperator = operator;
        model.setStatus(new CalculatorStateSelectOperator());
    }

    @Override
    public void processResult(CalculatorModel model) {
        /* 何もしない */
    }

    @Override
    public void processDecrement(CalculatorModel model) {
        /* 何もしない */
    }

    @Override
    public String output(CalculatorModel model) {
        return model.mOperandA.toString();
    }

    @Override
    public void processPercent(CalculatorModel model) {
        model.mOperandA = doPercentCommon(model);
    }
}
