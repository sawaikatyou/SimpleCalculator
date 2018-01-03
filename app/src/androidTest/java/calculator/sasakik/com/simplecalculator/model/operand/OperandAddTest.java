package calculator.sasakik.com.simplecalculator.model.operand;

import android.test.AndroidTestCase;

import org.junit.Test;

import calculator.sasakik.com.simplecalculator.operand.OperandAdd;

/**
 * Created by mega_ on 2018/01/03.
 */

public class OperandAddTest extends AndroidTestCase {

    @Test
    public void testEval() {
        OperandAdd target = new OperandAdd();
        assertEquals(4, target.eval(2,2));
    }
}
