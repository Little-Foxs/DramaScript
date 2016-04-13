package com.liangwang.dramascriptlibs.ControlerBase;

/**
 * Created by sysadminl on 2015/12/9.
 */
public abstract class BasePresenter<T extends IBaseView> {
    public T mView;

    //做绑定view的操作
    public void attach(T mView) {
        this.mView = mView;
    }

    //做分离view的操作
    public void detachView() {
        if (mView != null) {
            mView = null;
        }
    }
}
