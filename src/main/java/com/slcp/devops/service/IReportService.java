package com.slcp.devops.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.slcp.devops.entity.SysReport;

import java.util.Map;


/**
 * @author Slcp
 */
public interface IReportService extends IService<SysReport> {


    /**
     * 获取报表
     * @return map
     */
    Map listReport();
}
