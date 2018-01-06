package calculator.sasakik.com.simplecalculator.state;

import calculator.sasakik.com.simplecalculator.model.CalculatorModel;
import calculator.sasakik.com.simplecalculator.operand.SCOperand;
import calculator.sasakik.com.simplecalculator.operator.SCOperator;

/**
 * Created by sasakik on 2018/01/06.
 */

public class CalculatorStateInputB extends CalculatorStateTemplate {
    public static final String TAG = "CalculatorStateInputB";

    @Override
    public void processNumber(Character character, CalculatorModel model) {
        SCOperand old_opeB = model.mOperandB;
        model.mOperandB = append(character, old_opeB);
    }

    @Override
    public void processOperator(SCOperator operator, CalculatorModel model) {
        model.mOperandA = model.mOperator.eval(model.mOperandA, model.mOperandB);
        model.mOperator = operator;
        model.setStatus(new CalculatorStateSelectOperator());
    }

    @Override
    public void processResult(CalculatorModel model) {
        model.mOperandA = model.mOperator.eval(model.mOperandA, model.mOperandB);
        model.setStatus(new CalculatorStateOutputResult());
    }

    @Override
    public void processDecrement(CalculatorModel model) {
        doDecrementCommon(model);
    }

    @Override
    public void processPercent(CalculatorModel model) {
        model.mOperandB = doPercentCommon(model);
    }


    @Override
    public String output(CalculatorModel model) {
        return model.mOperandB.toString();
    }

    @Override
    protected SCOperand getTargetOperand(CalculatorModel model) {
        return model.mOperandB;
    }

    @Override
    protected void setTargetOperand(CalculatorModel model, SCOperand newvalue) {
        model.mOperandB = newvalue;
    }
}
