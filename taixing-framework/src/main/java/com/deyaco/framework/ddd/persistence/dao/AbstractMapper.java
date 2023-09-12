package com.deyaco.framework.ddd.persistence.dao;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.deyaco.framework.core.Whoami;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.io.Serializable;
import java.time.OffsetDateTime;
import java.util.Collection;
import java.util.Map;

public interface AbstractMapper<T> extends BaseMapper<T> {
    @Select({"SELECT NOW()"})
    OffsetDateTime now();

    boolean exists(@Param("ew") Wrapper<T> queryWrapper);

    int deleteByIdWithWhoami(Serializable id, @Param("whoami") Whoami whoami);

    int deleteByMapWithWhoami(@Param("cm") Map<String, Object> columnMap, @Param("whoami") Whoami whoami);

    int deleteWithWhoami(@Param("ew") Wrapper<T> queryWrapper, @Param("whoami") Whoami whoami);

    int deleteBatchIdsWithWhoami(@Param("coll") Collection<? extends Serializable> idList, @Param("whoami") Whoami whoami);

    default int deleteById(Serializable id) {
        return this.deleteByIdWithWhoami(id, Whoami.get());
    }

    default int deleteByMap(@Param("cm") Map<String, Object> columnMap) {
        return this.deleteByMapWithWhoami(columnMap, Whoami.get());
    }

    default int delete(@Param("ew") Wrapper<T> queryWrapper) {
        return this.deleteWithWhoami(queryWrapper, Whoami.get());
    }

    default int deleteBatchIds(@Param("coll") Collection<? extends Serializable> idList) {
        return this.deleteBatchIdsWithWhoami(idList, Whoami.get());
    }
}