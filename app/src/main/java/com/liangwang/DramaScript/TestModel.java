package com.liangwang.DramaScript;

import android.os.SystemClock;

import rx.Observable;
import rx.Subscriber;

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
public class TestModel implements ITestModel {

    private Observable<String> observable;

    @Override
    public void loadData(String url) {
        observable = Observable.create(new Observable.OnSubscribe<String>() {

            @Override
            public void call(final Subscriber<? super String> subscriber) {
                SystemClock.sleep(5000);
                subscriber.onNext("我是新数据");
                subscriber.onCompleted();
                /*ScriptOkhttpRequestAngent.get("http://waycloud.info/mobileIndex!getHotService.do",new StringHttpRequestCallbackScriptHttp(){
                    @Override
                    protected void onSuccess(String s) {
                        super.onSuccess(s);
                        subscriber.onNext(s);
                        subscriber.onCompleted();
                    }
                });*/

            }
        });
              /*  .subscribeOn(Schedulers.io()).
                observeOn(AndroidSchedulers.mainThread());*/
    }

    @Override
    public void sub(Subscriber<String> subscriber) {
        observable.subscribe(subscriber);
    }
}
