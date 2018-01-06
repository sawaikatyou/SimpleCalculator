package calculator.sasakik.com.simplecalculator.operator;

import java.math.BigDecimal;

import calculator.sasakik.com.simplecalculator.operand.SCOperand;

/**
 * Created by sasakik on 2018/01/03.
 */

public class OperatorDivide implements SCOperator {

    @Override
    public SCOperand eval(SCOperand valueA, SCOperand valueB) {
        BigDecimal vA = valueA.getValue();
        BigDecimal vB = valueB.getValue();

        return new SCOperand(vA.divide(vB, 3, BigDecimal.ROUND_DOWN).stripTrailingZeros());
    }
}
