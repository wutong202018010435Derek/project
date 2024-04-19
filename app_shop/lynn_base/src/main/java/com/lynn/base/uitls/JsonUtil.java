package com.lynn.base.uitls;


import android.text.TextUtils;

import com.google.gson.Gson;

import java.lang.reflect.Type;

public class JsonUtil {
    private static Gson gson = new Gson();
    public static Gson getGson(){
        return gson;
    }

    @SuppressWarnings("hiding")
    public static <T> T parseJson(String response, Class<T> clazz) {
        try {
            if (!TextUtils.isEmpty(response)) {
                return gson.fromJson(response, clazz);
            } else {
                return null;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public static <T> T parseJson(String response, Type type) {
        try {
            return gson.fromJson(response, type);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public static String toJson(Object object) {
        try {
            return gson.toJson(object);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}