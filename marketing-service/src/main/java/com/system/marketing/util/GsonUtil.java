package com.system.marketing.util;

import com.google.gson.Gson;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.List;

/**
 * @author: Liu.B.J
 * @date: 2021/3/28 15:09
 * @description:
 */
public class GsonUtil {

    private static final Gson gson = new Gson();

    public static <T> T convert(Object o, Class<T> clazz){
        if(o == null) {
            return null;
        }
        return gson.fromJson(gson.toJson(o), clazz);
    }

    public static <T> List<T> convertList(Object o, Class<T> clazz){
        if(o == null) {
            return null;
        }
        Type type = new ParameterizedTypeImpl(clazz);
        return gson.fromJson(gson.toJson(o), type);
    }

    private static class ParameterizedTypeImpl implements ParameterizedType {
        Class clazz;

        public ParameterizedTypeImpl(Class clz) {
            clazz = clz;
        }

        @Override
        public Type[] getActualTypeArguments() {
            return new Type[]{clazz};
        }

        @Override
        public Type getRawType() {
            return List.class;
        }

        @Override
        public Type getOwnerType() {
            return null;
        }
    }

}
