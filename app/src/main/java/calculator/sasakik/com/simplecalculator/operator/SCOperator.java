package calculator.sasakik.com.simplecalculator.operator;

import calculator.sasakik.com.simplecalculator.operand.SCOperand;

/**
 * Created by sasakik on 2018/01/06.
 */

public interface SCOperator {
    SCOperand eval(SCOperand valueA, SCOperand valueB);
}
