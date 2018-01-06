package calculator.sasakik.com.simplecalculator.model.operand;

import calculator.sasakik.com.simplecalculator.model.CalculatorModel;
import calculator.sasakik.com.simplecalculator.operand.SCOperand;
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
        return mTestOperand;
    }

    @Override
    protected void setTargetOperand(CalculatorModel model, SCOperand newvalue) {
        mTestOperand = newvalue;
    }
}
