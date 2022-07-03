package com.slcp.devops.service;

import com.alibaba.fastjson.JSONObject;
import com.slcp.devops.dto.ArchiveDTO;

import java.util.List;

/**
 * @author: SunXiaoWei
 * @create: 2022/2/1512:30
 **/
public interface ArchiveService {

    /**
     * 获取历史数据
     * @return 数据
     */
    List<ArchiveDTO> getAll();

    /**
     * 获取年
     * @return 数据
     */
    List<String> getYear();

    /**
     * 获取一年每月的文章数量
     * @return 数据
     */
    JSONObject getMon();

}
