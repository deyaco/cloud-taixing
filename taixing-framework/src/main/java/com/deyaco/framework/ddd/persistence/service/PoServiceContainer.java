package com.deyaco.framework.ddd.persistence.service;

import org.springframework.stereotype.Component;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

@Component
public class PoServiceContainer {
    private ConcurrentMap<Class<?>, IPoService<?>> poServiceMap = new ConcurrentHashMap();

    public PoServiceContainer() {
    }

    public <T> void put(Class<? extends T> t, IPoService<? extends T> poService) {
        this.poServiceMap.put(t, poService);
    }

    public <T> IPoService<T> get(Class<? extends T> t) {
        return (IPoService)this.poServiceMap.get(t);
    }
}