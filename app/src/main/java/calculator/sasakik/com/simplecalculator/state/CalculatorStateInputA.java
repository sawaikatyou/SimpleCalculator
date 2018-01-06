package calculator.sasakik.com.simplecalculator.state;

import calculator.sasakik.com.simplecalculator.model.CalculatorModel;
import calculator.sasakik.com.simplecalculator.operand.SCOperand;
import calculator.sasakik.com.simplecalculator.operator.SCOperator;

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
