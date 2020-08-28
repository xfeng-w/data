package com.xfeng.data.controller;

import com.xfeng.data.model.entity.Store;
import com.xfeng.data.service.StoreService;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author xuefeng.wang
 * @date 2020-08-28
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("portal/store/v1")
public class StoreController {

    private final StoreService storeService;

    @ApiOperation(value = "同步门店")
    @PostMapping
    public ResponseEntity<List<Store>> sync(@RequestBody List<Store> stores) {
        return new ResponseEntity<>(storeService.sync(stores), HttpStatus.OK);
    }
}
