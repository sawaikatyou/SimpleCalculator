/*
 * *
 *  SCOperator
 *
 *  Copyright (c) 2018. sasakik
 *
 *  This software is released under the MIT License.
 *  http://opensource.org/licenses/mit-license.php
 *
 */

package com.sasakik.simplecalculator.operator;

import com.sasakik.simplecalculator.operand.SCOperand;

/**
 * Created by sasakik on 2018/01/06.
 */

public interface SCOperator {
    SCOperand eval(SCOperand valueA, SCOperand valueB);
}
