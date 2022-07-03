package com.slcp.devops.service;

import com.alibaba.fastjson.JSONObject;


/**
 * @author: SunXiaoWei
 * @create: 2022/2/1611:46
 **/
public interface AboutService {

    /**
     * 文章、标签、分类个数统计
     * @return 统计
     */
    JSONObject getQuantityStatistics();

    /**
     * 文章、标签、分类 总计
     * @return 总计
     */
    JSONObject getQuantityStatisticsBySum();
}
