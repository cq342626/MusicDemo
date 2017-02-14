package com.chenqi.musicdemo.JarUtils;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;

public class GsonUtils {

	private static GsonUtils gsonUtils;

	public static GsonUtils getIntence() {
		if (gsonUtils == null)
			gsonUtils = new GsonUtils();
		return gsonUtils;
	}

	// 将Json数据解析成相应的映射对象
	public static <T> T parseJsonWithGson(String jsonData, Class<T> type) {
		Gson gson = new Gson();
		T result = gson.fromJson(jsonData, type);
		return result;
	}

	// 将Json数组解析成相应的映射对象列表
	public static <T> LinkedList<T> parseJsonArrayWithGson(String jsonData, Class<T> type) {
		Gson gson = new Gson();
		LinkedList<T> result = gson.fromJson(jsonData, new TypeToken<LinkedList<T>>() {
		}.getType());
		return result;
	}

	public static Object getInstanceByJson(Class<?> clazz, String json) {
		Object obj = null;
		Gson gson = new Gson();
		obj = gson.fromJson(json, clazz);
		return obj;
	}

	/**
	 * @author I321533
	 * @param json
	 * @param clazz
	 * @return
	 */
	public static <T> LinkedList<T> jsonToList(String json, Class<T[]> clazz) {
		Gson gson = new Gson();
		T[] array = gson.fromJson(json, clazz);
		return (LinkedList<T>) Arrays.asList(array);
	}

	/**
	 * @param json
	 * @param clazz
	 * @return
	 */
	public static <T> LinkedList<T> jsonToArrayList(String json, Class<T> clazz) {
		Type type = new TypeToken<ArrayList<JsonObject>>() {
		}.getType();
		LinkedList<JsonObject> jsonObjects = new Gson().fromJson(json, type);

		LinkedList<T> arrayList = new LinkedList<>();
		for (JsonObject jsonObject : jsonObjects) {
			arrayList.add(new Gson().fromJson(jsonObject, clazz));
		}
		return arrayList;
	}

}
