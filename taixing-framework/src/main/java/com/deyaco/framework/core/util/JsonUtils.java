package com.deyaco.framework.core.util;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonSetter.Value;
import com.fasterxml.jackson.annotation.Nulls;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.lang.NonNull;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;

import java.io.IOException;
@Component
public class JsonUtils implements ApplicationContextAware{
    private static volatile ObjectMapper objectMapper = createDegaultObjectMapper();

    public JsonUtils() {}

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        objectMapper = applicationContext.getBean(ObjectMapper.class);
    }
    public static String toJsonString(@Nullable Object javaObject) {
        return toJsonString(javaObject, false);
    }
    public static String toJsonString(@Nullable Object javaObject, boolean forcePretty) {
        try {
            return forcePretty ? objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(javaObject) : objectMapper.writeValueAsString(javaObject);
        } catch (JsonProcessingException var3) {
            throw new IllegalArgumentException(var3.getMessage(), var3);
        }
    }

    public static <T> T fromJsonString(@NonNull String jsonString, @NonNull Class<T> valueType) {
        try {
            return objectMapper.readValue(jsonString, valueType);
        } catch (IOException var3) {
            throw new IllegalArgumentException(var3.getMessage(), var3);
        }
    }

    public static <T> T fromJsonString(@NonNull String jsonString, @NonNull TypeReference<T> valueTypeRef) {
        try {
            return objectMapper.readValue(jsonString, valueTypeRef);
        } catch (IOException var3) {
            throw new IllegalArgumentException(var3.getMessage(), var3);
        }
    }

    public static <T> T convertValue(@Nullable Object fromValue, @NonNull Class<T> toValueType) {
        return objectMapper.convertValue(fromValue, toValueType);
    }



    private static ObjectMapper createDegaultObjectMapper() {
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.configure(SerializationFeature.FAIL_ON_EMPTY_BEANS, false);
        objectMapper.configure(SerializationFeature.INDENT_OUTPUT, false);
        objectMapper.configure(DeserializationFeature.ACCEPT_FLOAT_AS_INT, false);
        objectMapper.configure(DeserializationFeature.ACCEPT_SINGLE_VALUE_AS_ARRAY, true);
        objectMapper.configure(DeserializationFeature.UNWRAP_SINGLE_VALUE_ARRAYS, true);
        objectMapper.setDefaultPropertyInclusion(JsonInclude.Include.NON_NULL);
        objectMapper.setDefaultSetterInfo(Value.construct(Nulls.SKIP, Nulls.DEFAULT));
        return objectMapper;
    }
}
