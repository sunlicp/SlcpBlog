package com.slcp.devops.queryVo;

import lombok.Data;

import java.util.Date;
import java.util.List;

/**
 * @author: Slcp
 * @date: 2020/9/24 14:31
 * @code: 一生的挚爱
 * @description:
 */
@Data
public class ArchiveBlog {

    private Date createTime;
    private List<FirstPageBlog> data;
}
