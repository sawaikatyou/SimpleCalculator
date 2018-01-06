package calculator.sasakik.com.simplecalculator.model.operator;

import android.test.AndroidTestCase;

import org.junit.Test;

import calculator.sasakik.com.simplecalculator.operand.SCOperand;
import calculator.sasakik.com.simplecalculator.operator.OperatorMinus;
import calculator.sasakik.com.simplecalculator.operator.OperatorMulti;
import calculator.sasakik.com.simplecalculator.operator.SCOperator;

/**
 * Created by sasakik on 2018/01/03.
 */

public class OperatorMultiTest extends AndroidTestCase {

    @Test
    public void testEval() {
        SCOperator target = new OperatorMulti();
        final SCOperand valueA = new SCOperand(1);
        final SCOperand valueB = new SCOperand(2);
        final SCOperand valueC = new SCOperand(3);

        assertEquals(new SCOperand(2), target.eval(valueA,valueB));
        assertEquals(new SCOperand(3), target.eval(valueA,valueC));
        assertEquals(new SCOperand(6), target.eval(valueB,valueC));
        assertEquals(new SCOperand(6), target.eval(valueC,valueB));
    }

}
