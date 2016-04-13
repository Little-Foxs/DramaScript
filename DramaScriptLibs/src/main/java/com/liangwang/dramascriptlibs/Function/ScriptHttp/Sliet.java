package com.liangwang.dramascriptlibs.Function.ScriptHttp;

import android.text.TextUtils;

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
 * 描述：请求部分键值对的传入
 */
public final class Sliet {
    private String key;
    private String value;
    private FileTools fileTools;

    public Sliet(String key, String value) {
        setKey(key);
        setValue(value);
    }

    public Sliet(String key, FileTools fileTools) {
        setKey(key);
        this.fileTools = fileTools;
    }

    public String getKey() {
        return key;
    }

    public String getValue() {
        return value;
    }

    public FileTools getFileTools() {
        return fileTools;
    }

    protected void setKey(String key) {
        if(key == null) {
            this.key = "";
        } else {
            this.key = key;
        }
    }

    protected void setValue(String value) {
        if(value == null) {
            this.value = "";
        } else {
            this.value = value;
        }
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || !(o instanceof Sliet)){
            return false;
        }
        Sliet sliet = (Sliet) o;
        if (sliet == null){
            return false;
        }
        if (TextUtils.equals(sliet.getKey(), getKey()) && TextUtils.equals(sliet.getValue(), getValue())){
            return true;
        }
        return false;
    }
}
