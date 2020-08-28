package com.xfeng.data.model.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * @author xuefeng.wang
 * @date 2020-08-28
 */
@Data
public class Store implements Serializable {

    private static final long serialVersionUID = 6704183615586626814L;

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    private String code;

    private String name;

    private String tel;

    @TableField(exist = false)
    private List<StoreLocation> locations;

    private Date createdTime;

    private Date updatedTime;
}
