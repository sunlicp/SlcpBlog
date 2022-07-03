package com.slcp.devops.mapper;

import com.slcp.devops.dto.ArchiveDTO;

import java.util.List;
import java.util.Map;

/**
 * @description: ArchiveMapper
 * @author: SunXiaoWei
 * @create: 2022/2/1512:33
 **/
public interface ArchiveMapper {

    /**
     * 获取历史数据
     * @return 数据
     */
    List<ArchiveDTO> getAll();

    /**
     * 获取所有年
     * @return 数据
     */
    List<String> getYear();

    /**
     * 获取月
     * @return 数据
     */
    List<Map<String, String>> getMon();
}
