/*
 * *
 *  OperatorMinus
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
 * Created by sasakik on 2018/01/06.
 */

public class OperatorMinus implements SCOperator {
    @Override
    public SCOperand eval(SCOperand valueA, SCOperand valueB) {
        BigDecimal vA = valueA.getValue();
        BigDecimal vB = valueB.getValue();

        return new SCOperand(vA.subtract(vB));
    }
}
