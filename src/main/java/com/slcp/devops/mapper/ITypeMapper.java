package com.slcp.devops.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.slcp.devops.dto.TypeDTO;
import com.slcp.devops.entity.SysRole;
import com.slcp.devops.entity.Type;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * @author: Slcp
 * @date: 2020/9/22 15:48
 * @code: TypeMapper
 * @description:
 */
@Repository
public interface ITypeMapper extends BaseMapper<Type> {

    /**
     * 列表
     * @return 数据
     */
    List<TypeDTO> listTypes();

    /**
     * 列表
     * @return 数据
     */
    List<TypeDTO> typeList();

    /**
     * 类型信息
     * @param typeId 主键
     * @return 数据
     */
    TypeDTO getType(String typeId);

    /**
     * 保存
     * @param type 类型
     * @return r
     */
    int saveType(TypeDTO type);

    /**
     * 根据名称查询
     * @param name 名称
     * @return 数据
     */
    TypeDTO getTypeByName(String name);

    /**
     * 修改
     * @param type 类型
     * @return 数值
     */
    int updateType(TypeDTO type);

    /**
     * 删除
     * @param id 主键
     * @return 数值
     */
    int deleteById(Long id);

    /**
     * 获取总数
     * @return 数值
     */
    Integer getCount();

    /**
     * 获取分类以及每个总数
     * @return 数据
     */
    List<Map<String, String>> getAllOfMap();
}
