package com.liangwang.dramascriptlibs.ControlerBase;

import android.annotation.TargetApi;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;


import com.liangwang.dramascriptlibs.Function.Tools.AppManager;
import com.liangwang.dramascriptlibs.Function.Tools.SystemBarTintManager;
import com.liangwang.dramascriptlibs.Function.Tools.UITools;
import com.liangwang.dramascriptlibs.R;

import butterknife.ButterKnife;

public abstract class BaseActivity<T extends BasePresenter<IBaseView>> extends AppCompatActivity implements IBase {

    /**
     * 主线程
     */
    private long mUIThreadId;

    protected BasePresenter mPresenter;

    public abstract int getToolBarId();

    private Toolbar mToolbar;
    protected View mRootView;

    private SystemBarTintManager tintManager;

    public void setActionBar() {

    }

    public void getIntentValue() {

    }

    public void onResume() {
        super.onResume();
    }

    public void onPause() {
        super.onPause();
    }

    /**
     * 是否设置沉浸式
     *
     * @return
     */
    protected boolean isSetStatusBar() {
        return false;
    }




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mUIThreadId = android.os.Process.myTid();
        AppManager.getAppManager().addActivity(this);
        mPresenter = getPresenter();
        if (mPresenter != null && this instanceof IBaseView) {
            mPresenter.attach((IBaseView) this);
        }
        initWindow();
        getIntentValue();
        mRootView = createView(null, null, savedInstanceState);
        setContentView(mRootView);
      /*  mToolbar = (Toolbar) findViewById(getToolBarId());
        setSupportActionBar(mToolbar);
        setActionBar();*/
        bindView(savedInstanceState);
    }



    public Toolbar getToolbar() {
        return mToolbar;
    }



    @Override
    public View createView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = UITools.inflate(this, getContentLayout());
        ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public View getView() {
        return mRootView;
    }

    @Override
    protected void onNewIntent(Intent intent) {
        mUIThreadId = android.os.Process.myTid();
        super.onNewIntent(intent);
    }

    /**
     * 获取UI线程ID
     *
     * @return UI线程ID
     */
    public long getUIThreadId() {
        return mUIThreadId;
    }

    @TargetApi(19)
    private void initWindow() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT && isSetStatusBar()) {
            getWindow().addFlags(
                    WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            tintManager = new SystemBarTintManager(this);
            tintManager.setStatusBarTintEnabled(true);
            tintManager.setStatusBarTintResource(R.color.material_drawer_primary);
        }
    }



    protected void setStatusBarTintRes(int color) {
        if (tintManager != null) {
            tintManager.setStatusBarTintResource(color);
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                onBackPressed();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    protected void onDestroy() {
        AppManager.getAppManager().finishActivity(this);
        if (mPresenter != null && this instanceof IBaseView) {
            mPresenter.detachView();
            mPresenter = null;
        }
        super.onDestroy();
    }
}
