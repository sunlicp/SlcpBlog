package com.slcp.devops.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.slcp.devops.entity.SysReport;
import com.slcp.devops.mapper.IReportMapper;
import com.slcp.devops.service.IReportService;
import org.springframework.stereotype.Service;


/**
 * @author Slcp
 */
@Service
public class ReportServiceImpl extends ServiceImpl<IReportMapper, SysReport> implements IReportService {

}
