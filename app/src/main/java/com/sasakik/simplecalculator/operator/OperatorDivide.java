/*
 * *
 *  OperatorDivide
 *
 *  Copyright (c) 2018. sasakik
 *
 *  This software is released under the MIT License.
 *  http://opensource.org/licenses/mit-license.php
 *
 */

package com.sasakik.simplecalculator.operator;

import java.math.BigDecimal;

import com.sasakik.simplecalculator.operand.SCOperand;

/**
 * Created by sasakik on 2018/01/03.
 */

public class OperatorDivide implements SCOperator {

    @Override
    public SCOperand eval(SCOperand valueA, SCOperand valueB) {
        BigDecimal vA = valueA.getValue();
        BigDecimal vB = valueB.getValue();
        int scale = Math.max(vA.scale(), vB.scale());
        if (scale == 0) {
            scale = 3;
        }
        String result_str = vA.divide(vB, scale, BigDecimal.ROUND_DOWN).stripTrailingZeros().toPlainString();
        return new SCOperand(result_str);
    }
}
