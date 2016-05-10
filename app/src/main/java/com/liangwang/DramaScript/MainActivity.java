package com.liangwang.DramaScript;

import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.liangwang.dramascriptlibs.Function.Tools.SharedPreferencesUtil;
import com.liangwang.dramascriptlibs.UI.TimeView;

public class MainActivity extends AppCompatActivity {

    private TimeView mWeightView;
    private TextView mWeightTv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mWeightView = (TimeView)findViewById(R.id.weightRulerView);
        mWeightTv = (TextView)findViewById(R.id.weight_tv);
        //设置初始值
        mWeightView.setStartValue(0);
        //设置末尾值
        mWeightView.setEndValue(96);
        //设置两个长线之间的距离
        mWeightView.setPartitionWidthInDP(100.0f);
        //设置两个长线的间隔值
        mWeightView.setPartitionValue(1);
        //设置两个间隔之间的数量  1分钟应该有60
        mWeightView.setSmallPartitionCount(900);
        //设置当前值
        mWeightView.setmValue(1);
        //设置原点的最小值
        mWeightView.setOriginValueSmall(1);
        //设置滑动的监听
        mWeightView.flag = 93;
        mWeightView.flag2 = 95;

//        SharedPreferencesUtil.getInstance(this).setInt("flag",95);

        mWeightView.setValueChangeListener(new TimeView.OnValueChangeListener() {
            @Override
            public void onValueChange(String intVal, int fltval) {

                mWeightTv.setText(intVal);
            }
        });

    }
}
