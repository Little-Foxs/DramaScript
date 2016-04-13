

package com.liangwang.dramascriptlibs.Function.ScriptHttp;

import java.io.File;

import cn.finalteam.toolsfinal.StringUtils;
import okhttp3.Call;

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
 * 描述：http请求类
 */
public class ScriptOkhttpRequestAngent {

    public static void get(String url) {
        get(url, null, null);
    }

    public static void get(String url, RequestParams params) {
        get(url, params, null);
    }

    public static void get(String url, ScriptHttpRequestCallback callback) {
        get(url, null, callback);
    }

    /**
     * Get请求 
     * @param url
     * @param params
     * @param callback
     */
    public static void get(String url, RequestParams params, ScriptHttpRequestCallback callback) {
        executeRequest(Method.GET, url, params, callback);
    }

    public static void post(String url) {
        post(url, null, null);
    }

    public static void post(String url, RequestParams params) {
        post(url, params, null);
    }

    public static void post(String url, ScriptHttpRequestCallback callback) {
        post(url, null, callback);
    }

    /**
     * Post请求 
     * @param url
     * @param params
     * @param callback
     */
    public static void post(String url, RequestParams params, ScriptHttpRequestCallback callback) {
        executeRequest(Method.POST, url, params, callback);
    }

    public static void put(String url) {
        put(url, null, null);
    }

    public static void put(String url, RequestParams params) {
        put(url, params, null);
    }

    public static void put(String url, ScriptHttpRequestCallback callback) {
        put(url, null, callback);
    }

    /**
     * put请求 
     * @param url
     * @param params
     * @param callback
     */
    public static void put(String url, RequestParams params, ScriptHttpRequestCallback callback) {
        executeRequest(Method.PUT, url, params, callback);
    }

    public static void delete(String url) {
        delete(url, null, null);
    }

    public static void delete(String url, RequestParams params) {
        delete(url, params, null);
    }

    public static void delete(String url, ScriptHttpRequestCallback callback) {
        delete(url, null, callback);
    }

    /**
     * delete请求 
     * @param url
     * @param params
     * @param callback
     */
    public static void delete(String url, RequestParams params, ScriptHttpRequestCallback callback) {
        executeRequest(Method.DELETE, url, params, callback);
    }

    public static void head(String url) {
        head(url, null, null);
    }

    public static void head(String url, RequestParams params) {
        head(url, params, null);
    }

    public static void head(String url, ScriptHttpRequestCallback callback) {
        head(url, null, callback);
    }

    /**
     * head请求 
     * @param url
     * @param params
     * @param callback
     */
    public static void head(String url, RequestParams params, ScriptHttpRequestCallback callback) {
        executeRequest(Method.HEAD, url, params, callback);
    }

    public static void patch(String url) {
        patch(url, null, null);
    }

    public static void patch(String url, RequestParams params) {
        patch(url, params, null);
    }

    public static void patch(String url, ScriptHttpRequestCallback callback) {
        patch(url, null, callback);
    }

    /**
     * patch请求 
     * @param url
     * @param params
     * @param callback
     */
    public static void patch(String url, RequestParams params, ScriptHttpRequestCallback callback) {
        executeRequest(Method.PATCH, url, params, callback);
    }

    /**
     * 取消请求
     * @param url
     */
    public static void cancel(String url) {
        if ( !StringUtils.isEmpty(url) ) {
            Call call = OkHttpCallManager.getInstance().getCall(url);
            if ( call != null ) {
                call.cancel();
            }

            OkHttpCallManager.getInstance().removeCall(url);
        }
    }

    public static void download(String url, File target) {
        download(url, target, null);
    }

    /**
     * 下载文件
     * @param url
     * @param target 保存的文件
     * @param callback
     */
    public static void download(String url, File target, ScriptDownloadFileCallback callback) {
        if (!StringUtils.isEmpty(url) && target != null) {
            FileDownloadTask task = new FileDownloadTask(url, target, callback);
            task.execute();
        }
    }

    private static void executeRequest(Method method, String url, RequestParams params, ScriptHttpRequestCallback callback) {
        if (!StringUtils.isEmpty(url)) {
            HttpTask task = new HttpTask(method, url, params, callback);
            task.execute();
        }
    }

}
