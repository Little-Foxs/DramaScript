
package com.liangwang.dramascriptlibs.Function.ScriptHttp;

import java.lang.reflect.Type;

import okhttp3.Headers;
import okhttp3.Response;

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
 *
 * ━━━━━━感觉萌萌哒━━━━━━
 *
 * 作者：LigthWang
 *
 * 描述：整个请求的回调方法
 */
public class ScriptHttpRequestCallback<T> {

    public static final int ERROR_RESPONSE_NULL = 1001;
    public static final int ERROR_RESPONSE_JSON_EXCEPTION = 1002;
    public static final int ERROR_RESPONSE_UNKNOWN = 1003;
    public static final int ERROR_RESPONSE_TIMEOUT = 1004;
    protected Type type;
    protected Headers headers;

    public ScriptHttpRequestCallback() {
        type = ScriptClassTypeReflect.getModelClazz(getClass());
    }

    public void onStart() {
    }

    public void onResponse(Response httpResponse, String response, Headers headers) {

    }

    public void onResponse(String response, Headers headers) {
    }

    public void onFinish() {
    }

    protected void onSuccess(Headers headers, T t) {
    }
    protected void onSuccess(T t) {

    }

    /**
     * 上传文件进度
     * @param progress
     * @param networkSpeed 网速
     * @param done
     */
    public void onProgress(int progress, long networkSpeed, boolean done){
    }

    public void onFailure(int errorCode, String msg) {
    }

    public Headers getHeaders() {
        return headers;
    }

    protected void setResponseHeaders(Headers headers) {
        this.headers = headers;
    }


}
