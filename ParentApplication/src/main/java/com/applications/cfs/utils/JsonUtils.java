package com.applications.cfs.utils;

import java.io.FileReader;
import java.nio.charset.StandardCharsets;
import java.util.Objects;

import com.applications.cfs.exception.CfsException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import com.google.gson.stream.JsonReader;

import static com.applications.cfs.constants.AppConstants.CONTENT_URL;

final public class JsonUtils {

    private static Gson gson = new Gson();

    private JsonUtils() {
        super();
    }

    private static final Logger logger = LoggerFactory.getLogger(JsonUtils.class);
    private static final Gson GSON = new Gson();
    public static <T> T toObject(String jsonPath, Class<T> targetClass) throws CfsException {
        try {
            return GSON.fromJson(new JsonReader(new FileReader(jsonPath, StandardCharsets.UTF_8)),
                    targetClass);
        } catch (Exception e) {
            throw new CfsException(e.getMessage());
        }
    }

    public static <T> T fromJson(String jsonPath, Class<T> targetClass) throws CfsException {
        try {
            return GSON.fromJson(jsonPath, targetClass);
        } catch (Exception e) {
            throw new CfsException(e.getMessage());
        }
    }

    public static <T> String toJson(Object object, Class<T> sourceClass) {
        if (sourceClass != null) {
            return GSON.toJson(object, sourceClass);
        }
        return GSON.toJson(object);
    }

    public static String toJsonWithoutFormat(Object object) {
        try {
            return object!=null?gson.toJson(object, object.getClass()):null;
        } catch (Exception exp) {
        }
        return object.toString();
    }

    public static String toJson(Object object) {
        try {
            return object!=null?GSON.toJson(object, object.getClass()):null;
        } catch (Exception exp) {
        }
        return object.toString();
    }

    public static <T> T getObjectFromJsonString(String jsonString, Class<T> targetClass) {
        return GSON.fromJson(jsonString, targetClass);
    }
    public static String getContentUrl(String fileDataType) throws CfsException {
        final JsonObject jsonObject = getObjectFromJsonString(fileDataType, JsonObject.class);
        if (Objects.nonNull(jsonObject) && Objects.nonNull(jsonObject.get(CONTENT_URL))) {
            return jsonObject.get(CONTENT_URL).getAsString();
        }
        throw new CfsException("exception in getContentUrl method");
    }
}

