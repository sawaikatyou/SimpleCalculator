package calculator.sasakik.com.simplecalculator.model.operand;

import android.test.AndroidTestCase;

import org.junit.Test;

import calculator.sasakik.com.simplecalculator.model.CalculatorModel;
import calculator.sasakik.com.simplecalculator.operand.SCOperand;
import calculator.sasakik.com.simplecalculator.state.CalculatorStateTemplate;

/**
 * Created by sasakik on 2018/01/06.
 */

public class CalculatorStateTemplateTest extends AndroidTestCase {

    @Test
    public void testProcessDecrement() {
        CalculatorModel model = new CalculatorModel();
        MockCalculatorStateTemplate target = new MockCalculatorStateTemplate();
        target.mTestOperand = new SCOperand("123.45");

        target.processDecrement(model);
        assertEquals("123.4", target.getTargetOperand(model).toString());
        target.processDecrement(model);
        assertEquals("123", target.getTargetOperand(model).toString());
        target.processDecrement(model);
        assertEquals("12", target.getTargetOperand(model).toString());
        target.processDecrement(model);
        assertEquals("1", target.getTargetOperand(model).toString());
        target.processDecrement(model);
        assertEquals("0", target.getTargetOperand(model).toString());
        target.processDecrement(model);
        assertEquals("0", target.getTargetOperand(model).toString());
    }
}
