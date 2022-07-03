package com.slcp.devops.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.slcp.devops.entity.SysRights;
import com.slcp.devops.mapper.IRightsMapper;
import com.slcp.devops.service.IRightsService;
import org.springframework.stereotype.Service;


/**
 * @author Slcp
 */
@Service
public class RightsServiceImpl extends ServiceImpl<IRightsMapper, SysRights> implements IRightsService {

}
