
package com.sasakik.simplecalculator.model;

import android.util.Log;
import android.util.SparseArray;

import com.sasakik.simplecalculator.R;
import com.sasakik.simplecalculator.operand.SCOperand;
import com.sasakik.simplecalculator.operator.OperatorAdd;
import com.sasakik.simplecalculator.operator.OperatorDivide;
import com.sasakik.simplecalculator.operator.OperatorMinus;
import com.sasakik.simplecalculator.operator.OperatorMulti;
import com.sasakik.simplecalculator.operator.SCOperator;
import com.sasakik.simplecalculator.state.CalculatorState;
import com.sasakik.simplecalculator.state.CalculatorStateInputA;
import com.sasakik.simplecalculator.view.SCViewFeedbackListener;

/**
 * 演算モデル
 * <p>
 * Created by sasakik on 2017/12/23.
 */
public class CalculatorModel {

    public static final String TAG = "CalculatorModel";

    /**
     * 入力タイプ判定
     * CalculatorState になんのイベントを発行するか？の判定で使用する
     * <p>
     * {@link CalculatorState }
     */
    enum SCINPUTTYPE {
        SCINPUTTYPE_NUMBER,
        SCINPUTTYPE_OPERATOR,
        SCINPUTTYPE_EQUAL,
        SCINPUTTYPE_DECREMENT,
        SCINPUTTYPE_PERCENT,
        SCINPUTTYPE_CLEAR,
        SCINPUTTYPE_ALLCLEAR,
    }

    /**
     * 描画物更新イベントのリスナー
     */
    SCViewFeedbackListener mSCViewFeedbackListener;

    /**
     * 現在の計算状態
     */
    CalculatorState mState;

    /**
     * 計算数値A
     */
    public SCOperand mOperandA;

    /**
     * 計算数値B
     */
    public SCOperand mOperandB;

    /**
     * 現在の演算子
     */
    public SCOperator mOperator;

    /**
     * ViewのID値 → SCINPUTTYPE 変換テーブル
     */
    private static final SparseArray<SCINPUTTYPE> mViewIdToTypeConverter = new SparseArray<>();

    static {
        // 数値系
        mViewIdToTypeConverter.put(R.id.zero, SCINPUTTYPE.SCINPUTTYPE_NUMBER);
        mViewIdToTypeConverter.put(R.id.one, SCINPUTTYPE.SCINPUTTYPE_NUMBER);
        mViewIdToTypeConverter.put(R.id.two, SCINPUTTYPE.SCINPUTTYPE_NUMBER);
        mViewIdToTypeConverter.put(R.id.three, SCINPUTTYPE.SCINPUTTYPE_NUMBER);
        mViewIdToTypeConverter.put(R.id.four, SCINPUTTYPE.SCINPUTTYPE_NUMBER);
        mViewIdToTypeConverter.put(R.id.five, SCINPUTTYPE.SCINPUTTYPE_NUMBER);
        mViewIdToTypeConverter.put(R.id.six, SCINPUTTYPE.SCINPUTTYPE_NUMBER);
        mViewIdToTypeConverter.put(R.id.seven, SCINPUTTYPE.SCINPUTTYPE_NUMBER);
        mViewIdToTypeConverter.put(R.id.eight, SCINPUTTYPE.SCINPUTTYPE_NUMBER);
        mViewIdToTypeConverter.put(R.id.nine, SCINPUTTYPE.SCINPUTTYPE_NUMBER);
        mViewIdToTypeConverter.put(R.id.dot, SCINPUTTYPE.SCINPUTTYPE_NUMBER);
        // 数値系
        mViewIdToTypeConverter.put(R.id.plus, SCINPUTTYPE.SCINPUTTYPE_OPERATOR);
        mViewIdToTypeConverter.put(R.id.minus, SCINPUTTYPE.SCINPUTTYPE_OPERATOR);
        mViewIdToTypeConverter.put(R.id.multi, SCINPUTTYPE.SCINPUTTYPE_OPERATOR);
        mViewIdToTypeConverter.put(R.id.div, SCINPUTTYPE.SCINPUTTYPE_OPERATOR);

        mViewIdToTypeConverter.put(R.id.per, SCINPUTTYPE.SCINPUTTYPE_PERCENT);
        mViewIdToTypeConverter.put(R.id.dec, SCINPUTTYPE.SCINPUTTYPE_DECREMENT);

        mViewIdToTypeConverter.put(R.id.equal, SCINPUTTYPE.SCINPUTTYPE_EQUAL);
        mViewIdToTypeConverter.put(R.id.clear, SCINPUTTYPE.SCINPUTTYPE_CLEAR);
        mViewIdToTypeConverter.put(R.id.all_clear, SCINPUTTYPE.SCINPUTTYPE_ALLCLEAR);
    }

    /**
     * 数値系ViewのID値 → 数字の変換テーブル
     */
    private static final SparseArray<Character> mViewIdToNumCharacterConverter = new SparseArray<>();

    static {
        mViewIdToNumCharacterConverter.put(R.id.zero, '0');
        mViewIdToNumCharacterConverter.put(R.id.one, '1');
        mViewIdToNumCharacterConverter.put(R.id.two, '2');
        mViewIdToNumCharacterConverter.put(R.id.three, '3');
        mViewIdToNumCharacterConverter.put(R.id.four, '4');
        mViewIdToNumCharacterConverter.put(R.id.five, '5');
        mViewIdToNumCharacterConverter.put(R.id.six, '6');
        mViewIdToNumCharacterConverter.put(R.id.seven, '7');
        mViewIdToNumCharacterConverter.put(R.id.eight, '8');
        mViewIdToNumCharacterConverter.put(R.id.nine, '9');
        mViewIdToNumCharacterConverter.put(R.id.dot, '.');
    }

    private static final SparseArray<SCOperator> mViewIdToSCOperatorConverter = new SparseArray<>();

    static {
        mViewIdToSCOperatorConverter.put(R.id.plus, new OperatorAdd());
        mViewIdToSCOperatorConverter.put(R.id.minus, new OperatorMinus());
        mViewIdToSCOperatorConverter.put(R.id.multi, new OperatorMulti());
        mViewIdToSCOperatorConverter.put(R.id.div, new OperatorDivide());
    }

    public CalculatorModel() {
        init();
    }

    public void init() {
        mState = new CalculatorStateInputA();
        mOperandA = new SCOperand(0);
        mOperandB = new SCOperand(0);
    }

    public void setStatus(CalculatorState newvalue) {
        mState = newvalue;
    }

    public CalculatorState getStatus() {
        return mState;
    }

    public void process(int id) {
        CalculatorState currentstate = mState;
        if (mViewIdToTypeConverter.get(id) != null) {
            switch (mViewIdToTypeConverter.get(id)) {
                case SCINPUTTYPE_NUMBER:
                    if (mViewIdToNumCharacterConverter.get(id) != null) {
                        mState.processNumber(mViewIdToNumCharacterConverter.get(id), this);
                    } else {
                        Log.e(TAG, "process() invalid num process id = " + id);
                    }
                    break;

                case SCINPUTTYPE_OPERATOR:
                    if (mViewIdToSCOperatorConverter.get(id) != null) {
                        mState.processOperator(mViewIdToSCOperatorConverter.get(id), this);
                    }
                    break;
                case SCINPUTTYPE_EQUAL:
                    mState.processResult(this);
                    break;
                case SCINPUTTYPE_DECREMENT:
                    mState.processDecrement(this);
                    break;
                case SCINPUTTYPE_PERCENT:
                    mState.processPercent(this);
                    break;
                case SCINPUTTYPE_CLEAR:
                    mState.processClear(this);
                    break;
                case SCINPUTTYPE_ALLCLEAR:
                default:
                    init();
                    break;
            }
        }
        /* 描画物更新イベントを発行*/
        if (mSCViewFeedbackListener != null) {
            mSCViewFeedbackListener.onUpdate(mState.output(this));
        }
    }

    public void setFeedBackTarget(SCViewFeedbackListener feedBackTarget) {
        mSCViewFeedbackListener = feedBackTarget;
    }

}
