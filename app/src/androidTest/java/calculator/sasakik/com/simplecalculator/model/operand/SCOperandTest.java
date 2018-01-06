package calculator.sasakik.com.simplecalculator.model.operand;

import android.test.AndroidTestCase;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.math.BigDecimal;

import calculator.sasakik.com.simplecalculator.operand.SCOperand;

/**
 * Created by sasakik on 2018/01/06.
 */

public class SCOperandTest extends AndroidTestCase {

    @Before
    public void setup() throws Exception {

    }

    @Test
    public void testConstractor_String() {
        SCOperand target;
        final double unexpected_0_1 = 0.1f;

        try {
            target = new SCOperand("100");
            assertEquals(new BigDecimal(100), target.getValue());
            assertEquals((double) 100, target.getdouble());

            target = new SCOperand("0.1");
            assertEquals(new BigDecimal("0.1"), target.getValue());
            assertNotSame(unexpected_0_1, target.getdouble());

            // 最後がドットの場合、フラグが立つ
            target = new SCOperand("1.");
            assertEquals(new BigDecimal("1"), target.getValue());
            assertTrue(target.containsLastDot());

            // ドットがついている状態で"."付きマージされるテスト
            target = new SCOperand(target.toString() + (target.containsLastDot() ? "." : "") + "5");
            assertEquals(new BigDecimal("1.5"), target.getValue());
            assertFalse(target.containsLastDot());

        } catch (Exception e) {
            fail();
        }
    }


    @Test
    public void testConstractor_Bigdecimal() {
        SCOperand target;
        BigDecimal expected_100 = new BigDecimal(100);
        BigDecimal expected_0_1 = new BigDecimal("0.1");
        final double unexpected_0_1 = 0.1f;

        try {
            target = new SCOperand(expected_100);
            assertEquals(new BigDecimal(100), target.getValue());
            assertEquals((double) 100, target.getdouble());

            target = new SCOperand(expected_0_1);
            assertEquals(new BigDecimal("0.1"), target.getValue());
            assertNotSame(unexpected_0_1, target.getdouble());
        } catch (Exception e) {
            fail();
        }
    }

    @After
    public void tearDown() throws Exception {

    }
}
