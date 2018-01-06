
package calculator.sasakik.com.simplecalculator.state;

import calculator.sasakik.com.simplecalculator.model.CalculatorModel;
import calculator.sasakik.com.simplecalculator.operand.SCOperand;
import calculator.sasakik.com.simplecalculator.operator.SCOperator;

/**
 * Created by sasakik on 2018/01/06.
 */

public abstract class CalculatorStateTemplate implements CalculatorState {

    @Override
    public void processNumber(Character character, CalculatorModel model) {

    }

    protected SCOperand append(Character character, SCOperand target_operand) {
        String temp;
        if (target_operand.containsLastDot()) {
            temp = target_operand.toString() + "." + character;
        } else {
            temp = target_operand.toString() + character;
        }
        return new SCOperand((String) temp);
    }

    @Override
    public void processOperand(SCOperator operator, CalculatorModel model) {
        model.mOperator = operator;
    }

    @Override
    public void processDecrement(CalculatorModel model) {
        SCOperand target_operand = getTargetOperand(model);
        if (target_operand != null) {
            String temp = target_operand.toString();
            temp = temp.substring(0, temp.length() - 1);
            if (temp != null && !temp.isEmpty()) {
                setTargetOperand(model, new SCOperand((String) temp));
            } else {
                setTargetOperand(model, new SCOperand());
            }
        }
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
        model.init();
    }

    @Override
    public String output(CalculatorModel model) {
        return "";
    }

    protected abstract SCOperand getTargetOperand(CalculatorModel model);

    protected abstract void setTargetOperand(CalculatorModel model, SCOperand newvalue);
}
