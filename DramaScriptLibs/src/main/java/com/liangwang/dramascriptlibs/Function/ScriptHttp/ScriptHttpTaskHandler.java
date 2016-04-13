

package com.liangwang.dramascriptlibs.Function.ScriptHttp;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

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
 * 描述：Http请求辅助类，用于处理UI已经销毁，但后台线程还在进行的问题；
 */
public class ScriptHttpTaskHandler {

    /** 正在请求的任务集合 */
    private static Map<String, List<HttpTask>> httpTaskMap;
    /** 单例请求处理器 */
    private static ScriptHttpTaskHandler scriptHttpTaskHandler = null;

    private ScriptHttpTaskHandler() {
        httpTaskMap = new ConcurrentHashMap<>();
    }

    /**
     * 获得处理器实例
     */
    public static ScriptHttpTaskHandler getInstance() {
        if (scriptHttpTaskHandler == null) {
            scriptHttpTaskHandler = new ScriptHttpTaskHandler();
        }
        return scriptHttpTaskHandler;
    }

    /**
     * 移除KEY
     * @param key
     */
    public void removeTask(String key) {

        if (httpTaskMap.containsKey(key)) {
            List<HttpTask> tasks = httpTaskMap.get(key);
            if (tasks != null && tasks.size() > 0) {
                for (HttpTask task : tasks) {
                    ScriptOkhttpRequestAngent.cancel(task.getUrl());
                    if (!task.isCancelled()) {
                        task.cancel(true);
                    }
                }
            }

            //移除对应的Key
            httpTaskMap.remove(key);
        }
    }

    /**
     * 将请求放到Map里面
     * @param key
     * @param task
     */
    public void addTask(String key, HttpTask task) {
        if (httpTaskMap.containsKey(key)) {
            List<HttpTask> tasks = httpTaskMap.get(key);
            tasks.add(task);
            httpTaskMap.put(key, tasks);
        } else {
            List<HttpTask> tasks = new ArrayList<>();
            tasks.add(task);
            httpTaskMap.put(key, tasks);
        }
    }

    /**
     * 判断是否存在
     * @param key
     * @return
     */
    public boolean contains(String key) {
        return httpTaskMap.containsKey(key);
    }
}
