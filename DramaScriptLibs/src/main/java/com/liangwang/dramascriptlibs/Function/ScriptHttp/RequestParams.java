

package com.liangwang.dramascriptlibs.Function.ScriptHttp;

import android.text.TextUtils;

import com.alibaba.fastjson.JSONObject;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import cn.finalteam.toolsfinal.StringUtils;
import okhttp3.CacheControl;
import okhttp3.FormBody;
import okhttp3.Headers;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;

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
 * 描述：http的请求参数
 */
public class RequestParams {

    protected final Headers.Builder headers = new Headers.Builder();
    private final List<Sliet> params = new ArrayList<>();
    private final List<Sliet> files = new ArrayList<>();

    protected HttpLifeContext httpLifeContext;
    private String httpTaskKey;
    private RequestBody requestBody;
    private boolean applicationJson;
    private boolean urlEncoder;//是否进行URL编码
    private JSONObject jsonParams;
    protected CacheControl cacheControl;
    public RequestParams() {
        this(null);
    }

    public RequestParams(HttpLifeContext cycleContext) {
        this.httpLifeContext = cycleContext;
        init();
    }

    private void init() {
        headers.add("charset", "UTF-8");

        List<Sliet> commonParams = ScriptOkhttp.getInstance().getCommonParams();
        if (commonParams != null && commonParams.size() > 0){
            params.addAll(commonParams);
        }

        //添加公共header
        Headers commonHeaders = ScriptOkhttp.getInstance().getCommonHeaders();
        if ( commonHeaders != null && commonHeaders.size() > 0 ) {
            for (int i = 0; i < commonHeaders.size(); i++) {
                String key = commonHeaders.name(i);
                String value = commonHeaders.value(i);
                headers.add(key, value);
            }
        }

        if ( httpLifeContext != null ) {
            httpTaskKey = httpLifeContext.getHttpTaskKey();
        }
    }

    public String getHttpTaskKey() {
        return this.httpTaskKey;
    }

    //==================================params====================================

    /**
     * @param key
     * @param value
     */
    public void addFormDataPart(String key, String value) {
        if ( value == null ) {
            value = "";
        }

        Sliet sliet = new Sliet(key, value);
        if (!StringUtils.isEmpty(key) && !params.contains(sliet)) {
            params.add(sliet);
        }
    }

    public void addFormDataPart(String key, int value) {
        addFormDataPart(key, String.valueOf(value));
    }

    public void addFormDataPart(String key, long value) {
        addFormDataPart(key, String.valueOf(value));
    }

    public void addFormDataPart(String key, float value) {
        addFormDataPart(key, String.valueOf(value));
    }

    public void addFormDataPart(String key, double value) {
        addFormDataPart(key, String.valueOf(value));
    }

    public void addFormDataPart(String key, boolean value) {
        addFormDataPart(key, String.valueOf(value));
    }

    /**
     * @param key
     * @param file
     */
    public void addFormDataPart(String key, File file) {
        if (file == null || !file.exists() || file.length() == 0) {
            return;
        }

        boolean isPng = file.getName().lastIndexOf("png") > 0 || file.getName().lastIndexOf("PNG") > 0;
        if (isPng) {
            addFormDataPart(key, file, "image/png; charset=UTF-8");
            return;
        }

        boolean isJpg = file.getName().lastIndexOf("jpg") > 0 || file.getName().lastIndexOf("JPG") > 0
                ||file.getName().lastIndexOf("jpeg") > 0 || file.getName().lastIndexOf("JPEG") > 0;
        if (isJpg) {
            addFormDataPart(key, file, "image/jpeg; charset=UTF-8");
            return;
        }

        if (!isPng && !isJpg) {
            addFormDataPart(key, new FileTools(file, null));
        }
    }

    public void addFormDataPart(String key, File file, String contentType) {
        if (file == null || !file.exists() || file.length() == 0) {
            return;
        }

        MediaType mediaType = null;
        try {
            mediaType = MediaType.parse(contentType);
        } catch (Exception e){
            ScriptWrapLogger.e(e);
        }

        addFormDataPart(key, new FileTools(file, mediaType));
    }

    public void addFormDataPart(String key, File file, MediaType mediaType) {
        if (file == null || !file.exists() || file.length() == 0) {
            return;
        }

        addFormDataPart(key, new FileTools(file, mediaType));
    }


    public void addFormDataPartFiles(String key, List<File> files) {
        for (File file:files){
            if (file == null || !file.exists() || file.length() == 0) {
                continue;
            }
            addFormDataPart(key, file);
        }
    }

    public void addFormDataPart(String key, List<File> files, MediaType mediaType) {
        for (File file:files){
            if (file == null || !file.exists() || file.length() == 0) {
                continue;
            }
            addFormDataPart(key, new FileTools(file, mediaType));
        }
    }

    public void addFormDataPart(String key, FileTools fileTools) {
        if (!StringUtils.isEmpty(key) && fileTools != null) {
            File file = fileTools.getFile();
            if (file == null || !file.exists() || file.length() == 0) {
                return;
            }
            files.add(new Sliet(key, fileTools));
        }
    }

    public void addFormDataPart(String key, List<FileTools> fileToolses) {
        for (FileTools fileTools : fileToolses){
            addFormDataPart(key, fileTools);
        }
    }

    public void addFormDataParts(List<Sliet> params) {
        this.params.addAll(params);
    }

    //==================================header====================================
    public void addHeader(String line) {
        headers.add(line);
    }

    public void addHeader(String key, String value) {
        if ( value == null ) {
            value = "";
        }

        if (!TextUtils.isEmpty(key)) {
            headers.add(key, value);
        }
    }

    public void addHeader(String key, int value) {
        addHeader(key, String.valueOf(value));
    }

    public void addHeader(String key, long value) {
        addHeader(key, String.valueOf(value));
    }

    public void addHeader(String key, float value) {
        addHeader(key, String.valueOf(value));
    }

    public void addHeader(String key, double value) {
        addHeader(key, String.valueOf(value));
    }

    public void addHeader(String key, boolean value) {
        addHeader(key, String.valueOf(value));
    }

    /**
     * URL编码，只对GET,DELETE,HEAD有效
     */
    public void urlEncoder() {
        urlEncoder = true;
    }

    public boolean isUrlEncoder() {
        return urlEncoder;
    }

    public void setCacheControl(CacheControl cacheControl) {
        this.cacheControl = cacheControl;
    }

    public void clear() {
        params.clear();
        files.clear();
    }

    /**
     * 设置application/json方式传递数据
     * @param jsonParams 请求的JSON实例
     */
    public void applicationJson(JSONObject jsonParams){
        applicationJson = true;
        this.jsonParams = jsonParams;
    }

    public void applicationJson() {
        applicationJson = true;
    }

    public void setCustomRequestBody(RequestBody requestBody) {
        this.requestBody = requestBody;
    }

    public void setRequestBodyString(String string) {
        setRequestBody(MediaType.parse("text/plain; charset=utf-8"), string);
    }

    public void setRequestBody(String mediaType, String string) {
        setRequestBody(MediaType.parse(mediaType), string);
    }

    public void setRequestBody(MediaType mediaType, String string) {
        setCustomRequestBody(RequestBody.create(mediaType, string));
    }

    public List<Sliet> getFormParams() {
        return params;
    }

    protected RequestBody getRequestBody() {
        RequestBody body = null;
        if (applicationJson) {
            String json;
            if (jsonParams == null) {
                JSONObject jsonObject = new JSONObject();
                for (Sliet sliet : params) {
                    jsonObject.put(sliet.getKey(), sliet.getValue());
                }
                json = jsonObject.toJSONString();
            } else {
                json = jsonParams.toJSONString();
            }
            body = RequestBody.create(MediaType.parse("application/json; charset=utf-8"), json);
        } else if (requestBody != null) {
            body = requestBody;
        } else if (files.size() > 0) {
            boolean hasData = false;
            MultipartBody.Builder builder = new MultipartBody.Builder();
            builder.setType(MultipartBody.FORM);
            for (Sliet sliet :params){
                String key = sliet.getKey();
                String value = sliet.getValue();
                builder.addFormDataPart(key, value);
                hasData = true;
            }

            for (Sliet sliet :files){
                String key = sliet.getKey();
                FileTools file = sliet.getFileTools();
                if (file != null) {
                    hasData = true;
                    builder.addFormDataPart(key, file.getFileName(), RequestBody.create(file.getMediaType(), file.getFile()));
                }
            }
            if (hasData) {
                body = builder.build();
            }
        } else {
            FormBody.Builder builder = new FormBody.Builder();
            boolean hasData = false;
            for (Sliet sliet :params){
                String key = sliet.getKey();
                String value = sliet.getValue();
                builder.add(key, value);
                hasData = true;
            }
            if (hasData) {
                body = builder.build();
            }
        }

        return body;
    }

    @Override
    public String toString() {
        StringBuilder result = new StringBuilder();
        for (Sliet sliet :params){
            String key = sliet.getKey();
            String value = sliet.getValue();
            if (result.length() > 0)
                result.append("&");

            result.append(key);
            result.append("=");
            result.append(value);
        }

        for (Sliet sliet :files){
            String key = sliet.getKey();
            if (result.length() > 0)
                result.append("&");

            result.append(key);
            result.append("=");
            result.append("FILE");
        }

        if(jsonParams != null) {
            result.append(jsonParams.toJSONString());
        }

        return result.toString();
    }
}
