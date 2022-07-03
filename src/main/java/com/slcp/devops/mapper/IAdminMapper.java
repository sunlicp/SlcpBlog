package com.slcp.devops.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.slcp.devops.entity.SysAdmin;
import org.apache.ibatis.annotations.Param;


/**
 * @author Slcp
 */
public interface IAdminMapper extends BaseMapper<SysAdmin> {

    /**
     * 查询用户列表
     *
     * @param listInfoByPage     分页对象
     * @param query 请求参数
     * @return 列表
     */
    IPage<SysAdmin> listSysAdminPageOfXml(IPage<SysAdmin> listInfoByPage,@Param("query") String query);
}
