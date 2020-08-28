package com.xfeng.data.model.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

import java.io.Serializable;

/**
 * @author xuefeng.wang
 * @date 2020-08-28
 */
@Data
public class StoreLocation implements Serializable {

    private static final long serialVersionUID = 9015455806715040708L;

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    private Long storeId;

    private String address;

    private String lat;

    private String lng;
}
