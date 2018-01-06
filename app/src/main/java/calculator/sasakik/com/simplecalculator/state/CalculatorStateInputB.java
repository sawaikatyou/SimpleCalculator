package calculator.sasakik.com.simplecalculator.state;

import calculator.sasakik.com.simplecalculator.model.CalculatorModel;
import calculator.sasakik.com.simplecalculator.operand.SCOperand;

/**
 * Created by sasakik on 2018/01/06.
 */

public class CalculatorStateInputB extends CalculatorStateTemplate {

    @Override
    protected SCOperand getTargetOperand(CalculatorModel model) {
        return model.mOperandB;
    }

    @Override
    protected void setTargetOperand(CalculatorModel model, SCOperand newvalue) {
        model.mOperandB = newvalue;
    }
}
