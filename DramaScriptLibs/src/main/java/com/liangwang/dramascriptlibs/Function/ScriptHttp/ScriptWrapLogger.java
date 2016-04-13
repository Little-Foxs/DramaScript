

package com.liangwang.dramascriptlibs.Function.ScriptHttp;

import cn.finalteam.toolsfinal.BuildConfig;
import cn.finalteam.toolsfinal.logger.LoggerFactory;
import cn.finalteam.toolsfinal.logger.LoggerPrinter;
import cn.finalteam.toolsfinal.logger.Printer;
import cn.finalteam.toolsfinal.logger.Settings;

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
 * 描述：logger的包装
 */
class ScriptWrapLogger {
    public static final String DEFAULT_TAG = "OkHttpFinal";
    protected static boolean DEBUG = BuildConfig.DEBUG;
    private static LoggerPrinter printer;

    //no instance
    private ScriptWrapLogger() {
        printer = LoggerFactory.getFactory(DEFAULT_TAG, DEBUG);
    }

    private static void createInstance(){
        if (printer == null){
            new ScriptWrapLogger();
        }
    }

    public static void clear() {
        createInstance();
        printer.clear();
    }

    public static Settings getSettings() {
        createInstance();
        return printer.getSettings();
    }

    public static Printer t(String tag) {
        createInstance();
        return printer.t(tag, printer.getSettings().getMethodCount());
    }

    public static Printer t(int methodCount) {
        createInstance();
        return printer.t(null, methodCount);
    }

    public static Printer t(String tag, int methodCount) {
        createInstance();
        return printer.t(tag, methodCount);
    }

    public static void d(String message, Object... args) {
        createInstance();
        printer.d(message, args);
    }

    public static void e(Throwable throwable) {
        createInstance();
        printer.e(throwable);
    }

    public static void e(String message, Object... args) {
        createInstance();
        printer.e(null, message, args);
    }

    public static void e(Throwable throwable, String message, Object... args) {
        createInstance();
        printer.e(throwable, message, args);
    }

    public static void i(String message, Object... args) {
        createInstance();
        printer.i(message, args);
    }

    public static void v(String message, Object... args) {
        createInstance();
        printer.v(message, args);
    }

    public static void w(String message, Object... args) {
        createInstance();
        printer.w(message, args);
    }

    public static void wtf(String message, Object... args) {
        createInstance();
        printer.wtf(message, args);
    }

    /**
     * Formats the json content and print it
     *
     * @param json the json content
     */
    public static void json(String json) {
        createInstance();
        printer.json(json);
    }

    /**
     * Formats the json content and print it
     *
     * @param xml the xml content
     */
    public static void xml(String xml) {
        createInstance();
        printer.xml(xml);
    }
}
