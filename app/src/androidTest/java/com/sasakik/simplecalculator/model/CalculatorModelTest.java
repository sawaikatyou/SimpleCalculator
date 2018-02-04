/*
 * *
 *  CalculatorModelTest
 *
 *  Copyright (c) 2018. sasakik
 *
 *  This software is released under the MIT License.
 *  http://opensource.org/licenses/mit-license.php
 *
 */

package com.sasakik.simplecalculator.model;

import android.support.test.runner.AndroidJUnit4;
import android.test.AndroidTestCase;

import com.sasakik.simplecalculator.R;
import com.sasakik.simplecalculator.state.NullCalculatorState;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.ArrayList;

/**
 * Created by sasakik on 2018/01/03.
 */
@RunWith(AndroidJUnit4.class)
public class CalculatorModelTest extends AndroidTestCase {

    @Before
    public void setup() throws Exception {

    }

    static ArrayList<Integer> mIdList = new ArrayList<>();

    static {
        mIdList.add(R.id.zero);
        mIdList.add(R.id.one);
        mIdList.add(R.id.two);
        mIdList.add(R.id.three);
        mIdList.add(R.id.four);
        mIdList.add(R.id.five);
        mIdList.add(R.id.six);
        mIdList.add(R.id.seven);
        mIdList.add(R.id.eight);
        mIdList.add(R.id.nine);
        mIdList.add(R.id.dot);
        // 数値系
        mIdList.add(R.id.plus);
        mIdList.add(R.id.minus);
        mIdList.add(R.id.multi);
        mIdList.add(R.id.div);

        mIdList.add(R.id.per);
        mIdList.add(R.id.dec);

        mIdList.add(R.id.equal);
        mIdList.add(R.id.clear);
        mIdList.add(R.id.all_clear);
    }

    @Test
    public void test_get_and_setStatus() {
        CalculatorModel model = new CalculatorModel();
        model.setStatus(new NullCalculatorState());

        /* ありえないID */
        model.process(0);

        /* 全ループ回っても強制終了とかしないことだけ確認する */
        for (int loop : mIdList) {
            model.process(loop);
        }
    }

    @After
    public void tearDown() throws Exception {

    }

}
