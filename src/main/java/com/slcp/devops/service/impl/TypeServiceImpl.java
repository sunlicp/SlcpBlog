package com.slcp.devops.service.impl;

import com.slcp.devops.config.DoQueryCache;
import com.slcp.devops.mapper.TypeMapper;
import com.slcp.devops.entity.Type;
import com.slcp.devops.service.TypeService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 * @author: Slcp
 * @date: 2020/9/22 15:47
 * @code: typeService
 * @description:
 */
@Service("typeService")
public class TypeServiceImpl implements TypeService {

    @Resource
    private TypeMapper typeMapper;

    @Override
    @DoQueryCache
    public List<Type> listTypes() {
        return typeMapper.listTypes();
    }

    @Override
    @DoQueryCache
    public Type getType(String typeId) {
        return typeMapper.getType(typeId);
    }

    @Override
    @DoQueryCache
    public List<Type> getAllType() {
        return typeMapper.listTypes();
    }

    @Override
    public int saveType(Type type) {
        return typeMapper.saveType(type);
    }

    @Override
    @DoQueryCache
    public Type getTypeByName(String name) {
        return typeMapper.getTypeByName(name);
    }

    @Override
    public int updateType(Type type) {
        return typeMapper.updateType(type);
    }

    @Override
    public int deleteById(Long id) {
        return typeMapper.deleteById(id);
    }

    @Override
    @DoQueryCache
    public Integer getCount() {
        return typeMapper.getCount();
    }

    @Override
    @DoQueryCache
    public List<Map<String, String>> getAllOfMap() {
        return typeMapper.getAllOfMap();
    }

}
