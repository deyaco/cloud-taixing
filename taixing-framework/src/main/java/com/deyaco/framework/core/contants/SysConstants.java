package com.deyaco.framework.core.contants;


import org.springframework.lang.NonNull;
import org.springframework.lang.Nullable;

public abstract class SysConstants {
    @Nullable
    public static final String SWAGGER_TOKEN_URL = System.getProperty("SWAGGER_TOKEN_URL");
    @NonNull
    public static final boolean SKIP_API_DATA = "true".equalsIgnoreCase(System.getProperty("SKIP_API_DATA"));
    @Nullable
    public static final String SQL_GUARD_STRATEGY = System.getProperty("SQL_GUARD_STRATEGY");

    public SysConstants() {
    }
}