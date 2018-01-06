package calculator.sasakik.com.simplecalculator.state;

import calculator.sasakik.com.simplecalculator.model.CalculatorModel;
import calculator.sasakik.com.simplecalculator.operand.SCOperand;

/**
 * Created by sasakik on 2018/01/06.
 */

public class CalculatorStateInputA extends CalculatorStateTemplate {

    @Override
    public void processNumber(Character character, CalculatorModel model) {
        SCOperand old_opeA = model.mOperandA;
        model.mOperandA = append(character, old_opeA);
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
