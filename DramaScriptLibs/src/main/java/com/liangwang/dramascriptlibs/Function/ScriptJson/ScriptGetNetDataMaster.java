package com.liangwang.dramascriptlibs.Function.ScriptJson;

import com.google.gson.JsonParseException;

import org.codehaus.jackson.map.DeserializationConfig.Feature;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.map.type.TypeFactory;

import java.io.IOException;
import java.util.ArrayList;

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
 * <p/>
 * ━━━━━━感觉萌萌哒━━━━━━
 * <p/>
 * 作者：LigthWang
 * <p/>
 * 描述：Base class for the custom parsing json
 */
public class ScriptGetNetDataMaster<T> {

	private ObjectMapper mapper = new ObjectMapper();

	@SuppressWarnings("unchecked")
	public T getJson2Bean(String jsonStr, Class<?> clazz)
			throws JsonParseException, JsonMappingException, IOException {
		// Class<T> clazz = (Class<T>) ((ParameterizedType) getClass()
		// .getGenericSuperclass()).getActualTypeArguments()[0];
		mapper.configure(Feature.FAIL_ON_UNKNOWN_PROPERTIES, false);
		mapper.configure(Feature.ACCEPT_EMPTY_STRING_AS_NULL_OBJECT, true);
		return (T) mapper.readValue(jsonStr, clazz);
	}

	public ArrayList<T> getJson2List(String jsonStr, Class<?> clazz)
			throws JsonParseException, JsonMappingException, IOException {

		mapper.configure(Feature.FAIL_ON_UNKNOWN_PROPERTIES, false);
		mapper.configure(Feature.ACCEPT_EMPTY_STRING_AS_NULL_OBJECT, true);
		ArrayList<T> list = mapper.readValue(jsonStr,
				TypeFactory.collectionType(ArrayList.class, clazz));
		return list;
	}
}