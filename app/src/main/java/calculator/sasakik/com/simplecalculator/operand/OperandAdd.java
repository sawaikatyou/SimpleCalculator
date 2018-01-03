package calculator.sasakik.com.simplecalculator.operand;

/**
 * Created by mega_ on 2018/01/03.
 */

public class OperandAdd implements SCOperand{
    @Override
    public long eval(int x, int y) {
        return x+y;
    }
}
