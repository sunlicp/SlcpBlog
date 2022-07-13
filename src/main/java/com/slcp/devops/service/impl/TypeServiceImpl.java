package com.slcp.devops.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.slcp.devops.config.DoQueryCache;
import com.slcp.devops.entity.Type;
import com.slcp.devops.mapper.ITypeMapper;
import com.slcp.devops.dto.TypeDTO;
import com.slcp.devops.service.ITypeService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * @author: Slcp
 * @date: 2020/9/22 15:47
 * @code: typeService
 * @description:
 */
@Service("typeService")
public class TypeServiceImpl extends ServiceImpl<ITypeMapper, Type> implements ITypeService {

    @Override
    @DoQueryCache
    public List<TypeDTO> listTypes() {
        return this.baseMapper.listTypes();
    }

    @Override
    @DoQueryCache
    public List<TypeDTO> typeList() {
        return this.baseMapper.typeList();
    }

    @Override
    @DoQueryCache
    public TypeDTO getType(String typeId) {
        return this.baseMapper.getType(typeId);
    }

    @Override
    @DoQueryCache
    public List<TypeDTO> getAllType() {
        return this.baseMapper.listTypes();
    }

    @Override
    public int saveType(TypeDTO type) {
        return this.baseMapper.saveType(type);
    }

    @Override
    @DoQueryCache
    public TypeDTO getTypeByName(String name) {
        return this.baseMapper.getTypeByName(name);
    }

    @Override
    public int updateType(TypeDTO type) {
        return this.baseMapper.updateType(type);
    }

    @Override
    public int deleteById(Long id) {
        return this.baseMapper.deleteById(id);
    }

    @Override
    @DoQueryCache
    public Integer getCount() {
        return this.baseMapper.getCount();
    }

    @Override
    @DoQueryCache
    public List<Map<String, String>> getAllOfMap() {
        return this.baseMapper.getAllOfMap();
    }

}
