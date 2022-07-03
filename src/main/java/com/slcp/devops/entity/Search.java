package com.slcp.devops.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * 搜索封装类
 *
 * @author devops
 */
@Data
@ApiModel(description = "搜索条件")
public class Search implements Serializable {

    /**
     * 当前页
     */
    @ApiModelProperty(value = "当前页")
    private Integer pageNum = 1;

    /**
     * 每页的数量
     */
    @ApiModelProperty(value = "每页的数量")
    private Integer pageSize = 10;

    /**
     * 升序 字段数组
     */
    @ApiModelProperty(hidden = true)
    private String ascs;

    /**
     * 降序 字段数组
     */
    @ApiModelProperty(hidden = true)
    private String descs;
}