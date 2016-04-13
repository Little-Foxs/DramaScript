package com.liangwang.DramaScript;

import android.os.Bundle;
import android.widget.TextView;

import com.liangwang.dramascriptlibs.ControlerBase.BaseActivity;
import com.liangwang.dramascriptlibs.ControlerBase.BasePresenter;

import butterknife.Bind;

/**
 * 　　　　　　　　┏┓　　　┏┓
 * 　　　　　　　┏┛┻━━━┛┻┓
 * 　　　　　　　┃　　　　　　　┃
 * 　　　　　　　┃　　　━　　　┃
 * 　　　　　　　┃　＞　　　＜　┃
 * 　　　　　　　┃　　　　　　　┃
 * 　　　　　　　┃...　⌒　...　┃
 * 　　　　　　　┃　　　　　　　┃
 * 　　　　　　　┗━┓　　　┏━┛
 * 　　　　　　　　　┃　　　┃　Code is far away from bug with the animal protecting
 * 　　　　　　　　　┃　　　┃   神兽保佑,代码无bug
 * 　　　　　　　　　┃　　　┃
 * 　　　　　　　　　┃　　　┃
 * 　　　　　　　　　┃　　　┃
 * 　　　　　　　　　┃　　　┃
 * 　　　　　　　　　┃　　　┗━━━┓
 * 　　　　　　　　　┃　　　　　　　┣┓
 * 　　　　　　　　　┃　　　　　　　┏┛
 * 　　　　　　　　　┗┓┓┏━┳┓┏┛
 * 　　　　　　　　　　┃┫┫　┃┫┫
 * 　　　　　　　　　　┗┻┛　┗┻┛
 * <p>
 * ━━━━━━感觉萌萌哒━━━━━━
 * <p>
 * 作者：LigthWang
 * <p>
 * 描述：
 */
public class TestActivity extends BaseActivity implements TestView{

    @Bind(R.id.tv)
    TextView tv;

    @Override
    public void getIntentValue() {
        super.getIntentValue();

    }

    @Override
    public int getToolBarId() {
        return R.id.toolbar;
    }

    @Override
    public BasePresenter getPresenter() {
        return new TestPresenter();
    }

    @Override
    public void bindView(Bundle savedInstanceState) {
        if (mPresenter == null) return;
        ((TestPresenter) mPresenter).getContent();
    }

    @Override
    public int getContentLayout() {
        return R.layout.activity_test;
    }

    @Override
    public void setContent(String content) {
        if (tv == null)
            return;
        tv.setText(content);
    }
}
