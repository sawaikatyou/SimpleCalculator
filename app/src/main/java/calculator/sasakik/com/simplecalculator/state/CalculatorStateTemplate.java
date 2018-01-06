
package calculator.sasakik.com.simplecalculator.state;

import android.util.Log;

import java.math.BigDecimal;

import calculator.sasakik.com.simplecalculator.model.CalculatorModel;
import calculator.sasakik.com.simplecalculator.operand.SCOperand;
import calculator.sasakik.com.simplecalculator.operator.OperatorDivide;
import calculator.sasakik.com.simplecalculator.operator.SCOperator;

import static calculator.sasakik.com.simplecalculator.operand.SCOperand.TAG;

/**
 * Created by sasakik on 2018/01/06.
 */

public abstract class CalculatorStateTemplate implements CalculatorState {


    protected SCOperand append(Character character, SCOperand target_operand) {
        String temp;
        if (target_operand.containsLastDot()) {
            temp = target_operand.toString() + "." + character;
        } else {
            temp = target_operand.toString() + character;
        }
        return new SCOperand(temp);
    }

    protected void doDecrementCommon(CalculatorModel model) {
        SCOperand target_operand = getTargetOperand(model);
        if (target_operand != null) {
            String temp = target_operand.toString();
            temp = temp.substring(0, temp.length() - 1);
            if (temp != null && !temp.isEmpty()) {
                setTargetOperand(model, new SCOperand(temp));
            } else {
                setTargetOperand(model, new SCOperand());
            }
        }
    }

    protected SCOperand doPercentCommon(CalculatorModel model) {
        SCOperand target_operand = getTargetOperand(model);
        if (target_operand != null) {
            OperatorDivide div = new OperatorDivide();
            return div.eval(target_operand, new SCOperand(100));
        }

        return new SCOperand();
    }


    @Override
    public void processClear(CalculatorModel model) {
        setTargetOperand(model, new SCOperand());
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
