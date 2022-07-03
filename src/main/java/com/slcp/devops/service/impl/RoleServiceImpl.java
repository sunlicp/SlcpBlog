package com.slcp.devops.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.slcp.devops.mapper.IRoleMapper;
import com.slcp.devops.entity.SysRole;
import com.slcp.devops.service.IRoleService;
import org.springframework.stereotype.Service;


/**
 * @author Slcp
 */
@Service
public class RoleServiceImpl extends ServiceImpl<IRoleMapper, SysRole> implements IRoleService {


}
