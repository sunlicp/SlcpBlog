package com.slcp.devops.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.slcp.devops.mapper.TagMapper;
import com.slcp.devops.entity.Tag;
import com.slcp.devops.service.TagService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author: Slcp
 * @date: 2020/9/22 15:47
 * @code: 一生的挚爱
 * @description:
 */
@Service
public class TagServiceImpl implements TagService {

    @Resource
    private TagMapper tagMapper;

    @Override
    public List<Tag> listTags() {
        return tagMapper.listTags();
    }

    @Override
    public Tag getType(String typeId) {
        return tagMapper.getTag(typeId);
    }

    @Override
    public List<Tag> getAllTag() {
        return tagMapper.listTags();
    }

    @Override
    public int saveTag(Tag type) {
        return tagMapper.saveTag(type);
    }

    @Override
    public Tag getTagByName(String name) {
        return tagMapper.getTagByName(name);
    }

    @Override
    public int updateTag(Tag type) {
        return tagMapper.updateTag(type);
    }

    @Override
    public int deleteById(String id) {
        return tagMapper.deleteById(id);
    }

    @Override
    public List<Tag> listTagsAndBlogs() {
        return tagMapper.listTagsAndBlogs();
    }

    @Override
    public Integer getCount(){
        return tagMapper.getCount();
    }

    @Override
    public JSONObject getAllTags() {
        JSONObject obj = new JSONObject();

        List<Tag> data = tagMapper.getAllTags();
        int size = data.size();
        Integer[] count = new Integer[size];
        String[] str = new String[size];
        for (int i = 0; i < size; i++) {
            str[i] = data.get(i).getTagName();
            count[i] = data.get(i).getSum();
        }
        obj.put("tag", str);
        obj.put("tagCount", count);
        return obj;
    }
}
