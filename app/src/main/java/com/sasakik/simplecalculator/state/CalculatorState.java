package com.sasakik.simplecalculator.state;

import com.sasakik.simplecalculator.model.CalculatorModel;
import com.sasakik.simplecalculator.operand.SCOperand;
import com.sasakik.simplecalculator.operator.SCOperator;

/**
 * 計算状態
 *
 * Created by sasakik on 2017/12/23.
 */
public interface CalculatorState {

    /**
     * 数値打ち込み時イベントハンドラ
     *
     * @param character
     * @param model 演算モデル
     */
    void processNumber(Character character, CalculatorModel model);

    /**
     * 演算子打ち込み時イベントハンドラ
     *
     * @param operator
     * @param model 演算モデル
     */
    void processOperator(SCOperator operator, CalculatorModel model);

    /**
     * "=" 打ち込み時イベントハンドラ
     *
     * @param model 演算モデル
     */
    void processResult(CalculatorModel model);

    /**
     * "←"（バックスペース） 打ち込み時イベントハンドラ
     *
     * @param model 演算モデル
     */
    void processDecrement(CalculatorModel model);

    /**
     * "％" (１００分率) 打ち込み時イベントハンドラ
     *
     * @param model 演算モデル
     */
    void processPercent(CalculatorModel model);

    /**
     * "C"（クリア）打ち込み時イベントハンドラ
     *
     * @param model 演算モデル
     */
    void processClear(CalculatorModel model);


    /**
     * "AC"（オールクリア）打ち込み時イベントハンドラ
     *
     * @param model 演算モデル
     */
    void processAllClear(CalculatorModel model);

    /**
     * 計算結果を返却する
     *
     * @param model 演算モデル
     *
     * @return 出力文字列
     */
    String output(CalculatorModel model);

}
