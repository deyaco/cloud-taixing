package com.deyaco.framework.ddd.persistence.service;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import org.apache.ibatis.annotations.Param;
import org.springframework.util.MultiValueMap;

import java.awt.print.Pageable;
import java.time.OffsetDateTime;
import java.util.List;

public interface IPoService<T> extends IService<T> {
    OffsetDateTime now();

    boolean exists(@Param("ew") Wrapper<T> queryWrapper);

    boolean updateByIdWithAllColumns(@Param("et") T entity);

    List<T> getList(MultiValueMap<String, String> query);

    IPage<T> getPageable(MultiValueMap<String, String> query, Pageable pageable);
}