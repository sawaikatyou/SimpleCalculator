package com.sasakik.simplecalculator.state;

import com.sasakik.simplecalculator.model.CalculatorModel;
import com.sasakik.simplecalculator.operand.SCOperand;
import com.sasakik.simplecalculator.operator.SCOperator;

/**
 * Created by sasakik on 2017/12/23.
 */

public interface CalculatorState {
    void processNumber(Character character, CalculatorModel model);
    void processOperator(SCOperator operator, CalculatorModel model);
    void processResult(CalculatorModel model);
    void processDecrement(CalculatorModel model);
    void processPercent(CalculatorModel model);
    void processClear(CalculatorModel model);
    void processAllClear(CalculatorModel model);

    String output(CalculatorModel model);

}
