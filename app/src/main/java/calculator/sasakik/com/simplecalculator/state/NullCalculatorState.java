package calculator.sasakik.com.simplecalculator.state;

import calculator.sasakik.com.simplecalculator.model.CalculatorModel;
import calculator.sasakik.com.simplecalculator.operand.SCOperand;
import calculator.sasakik.com.simplecalculator.operator.SCOperator;

/**
 * Created by sasakik on 2018/01/06.
 */

public class NullCalculatorState implements CalculatorState {

    @Override
    public void processNumber(Character character, CalculatorModel model) {

    }

    @Override
    public void processOperand(SCOperator operand, CalculatorModel model) {

    }

    @Override
    public void processDecrement(CalculatorModel model) {

    }

    @Override
    public void processPercent(CalculatorModel calculatorModel) {

    }

    @Override
    public void processResult(CalculatorModel model) {

    }

    @Override
    public void processClear(CalculatorModel model) {

    }

    @Override
    public void processAllClear(CalculatorModel model) {

    }

    @Override
    public String output(CalculatorModel model) {
        return null;
    }
}
