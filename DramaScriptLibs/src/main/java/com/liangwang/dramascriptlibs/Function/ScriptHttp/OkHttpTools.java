package com.liangwang.dramascriptlibs.Function.ScriptHttp;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.List;
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
 * 描述:获取url的包装
 */
class OkHttpTools {

    public static String getFullUrl(String url, List<Sliet> params, boolean urlEncoder) {
        StringBuffer urlFull = new StringBuffer();
        urlFull.append(url);
        if (urlFull.indexOf("?", 0) < 0 && params.size() > 0) {
            urlFull.append("?");
        }
        int flag = 0;
        for (Sliet sliet :params){
            String key = sliet.getKey();
            String value = sliet.getValue();
            if(urlEncoder){//只对key和value编码
                try {
                    key = URLEncoder.encode(key, "UTF-8");
                    value = URLEncoder.encode(value, "UTF-8");
                } catch (UnsupportedEncodingException e) {
                    e.printStackTrace();
                }
            }
            urlFull.append(key).append("=").append(value);
            if (++flag != params.size()){
                urlFull.append("&");
            }
        }

        return urlFull.toString();
    }
}
