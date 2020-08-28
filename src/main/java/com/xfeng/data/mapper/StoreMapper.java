package com.xfeng.data.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.xfeng.data.model.entity.Store;

import java.util.List;

/**
 * @author xuefeng.wang
 * @date 2020-08-28
 */
public interface StoreMapper extends BaseMapper<Store> {

    void batchSaveOrUpdate(List<Store> stores);
}
