package com.xfeng.data.service;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.CollectionUtils;
import com.xfeng.data.mapper.StoreLocationMapper;
import com.xfeng.data.mapper.StoreMapper;
import com.xfeng.data.model.entity.Store;
import com.xfeng.data.model.entity.StoreLocation;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * @author xuefeng.wang
 * @date 2020-08-28
 */
@Service
@RequiredArgsConstructor
public class StoreService {

    private final StoreMapper storeMapper;

    private final StoreLocationMapper storeLocationMapper;

    private final StoreLocationService storeLocationService;

    public List<Store> sync(List<Store> stores) {
        List<String> existStoreCodes = stores.stream().map(Store::getCode).collect(Collectors.toList());
        LambdaQueryWrapper<Store> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.in(Store::getCode, existStoreCodes);
        List<Store> oldStores = storeMapper.selectList(queryWrapper);
        Map<String, Store> exists = oldStores.stream().collect(Collectors.toMap(Store::getCode, Function.identity()));

        Date date = new Date();
        stores.forEach(item -> {
            item.setUpdatedTime(date);
            if (exists.containsKey(item.getCode())) {
                Store old = exists.get(item.getCode());
                item.setId(old.getId());
                item.setCreatedTime(old.getCreatedTime());
            } else {
                item.setCreatedTime(date);
            }
        });
        storeMapper.batchSaveOrUpdate(stores);

        //删除门店地址
        List<Long> storeIds = stores.stream().map(Store::getId).collect(Collectors.toList());
        LambdaQueryWrapper<StoreLocation> locationWrapper = new LambdaQueryWrapper<>();
        locationWrapper.in(StoreLocation::getStoreId, storeIds);
        storeLocationMapper.delete(locationWrapper);

        //重新插入门店地址
        List<StoreLocation> storeLocations = new ArrayList<>();
        stores.forEach(item -> {
            if (CollectionUtils.isNotEmpty(item.getLocations())) {
                List<StoreLocation> locations = item.getLocations();
                locations.forEach(o -> {
                    o.setStoreId(item.getId());
                    storeLocations.add(o);
                });
            }
        });
        storeLocationService.saveBatch(storeLocations);

        return stores;
    }
}
