package com.deyaco.framework.utils;

import com.deyaco.framework.ddd.api.IApiStatus;
import com.deyaco.framework.exception.AppException;

public class AssertUtils {
    public AssertUtils() {
    }

    public static void assertEquals(Object o1, Object o2, IApiStatus iApiStatus) {
        assertEquals(o1, o2, iApiStatus, "");
    }

    public static void assertEquals(Object o1, Object o2, IApiStatus iApiStatus, String message) {
        if (o1 == null || !o1.equals(o2)) {
            throw new AppException(iApiStatus, message);
        }
    }

    public static void assertNotNull(Object o1, IApiStatus iApiStatus) {
        assertNotNull(o1, iApiStatus, "");
    }

    public static void assertNotNull(Object o1, IApiStatus iApiStatus, String message) {
        if (o1 == null) {
            throw new AppException(iApiStatus, message);
        }
    }

    public static void assertTrue(boolean b1, IApiStatus iApiStatus) {
        assertTrue(b1, iApiStatus, "");
    }

    public static void assertTrue(boolean b1, IApiStatus iApiStatus, String message) {
        if (!b1) {
            throw new AppException(iApiStatus, message);
        }
    }

    public static void assertFalse(boolean b1, IApiStatus iApiStatus) {
        assertFalse(b1, iApiStatus, "");
    }

    public static void assertFalse(boolean b1, IApiStatus iApiStatus, String message) {
        if (b1) {
            throw new AppException(iApiStatus, message);
        }
    }
}