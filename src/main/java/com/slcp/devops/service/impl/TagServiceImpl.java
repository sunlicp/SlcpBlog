package com.slcp.devops.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.slcp.devops.config.DoQueryCache;
import com.slcp.devops.entity.Tag;
import com.slcp.devops.mapper.ITagMapper;
import com.slcp.devops.dto.TagDTO;
import com.slcp.devops.service.ITagService;
import com.slcp.devops.utils.ColorUtil;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author: Slcp
 * @date: 2020/9/22 15:47
 * @code: 一生的挚爱
 * @description:
 */
@Service("tagService")
public class TagServiceImpl extends ServiceImpl<ITagMapper, Tag> implements ITagService {

    @Override
    @DoQueryCache
    public List<TagDTO> listTags() {
        return this.baseMapper.listTags();
    }

    @Override
    public List<TagDTO> tagList() {
        List<TagDTO> tagList = this.baseMapper.tagList();
        for (TagDTO tag : tagList) {
            String randColor = ColorUtil.getRandColor();
            tag.setColor("color: " + randColor);
        }
        return tagList;
    }

    @Override
    public TagDTO getType(String typeId) {
        return this.baseMapper.getTag(typeId);
    }

    @Override
    public List<TagDTO> getAllTag() {
        return this.baseMapper.listTags();
    }

    @Override
    public int saveTag(TagDTO type) {
        return this.baseMapper.saveTag(type);
    }

    @Override
    public TagDTO getTagByName(String name) {
        return this.baseMapper.getTagByName(name);
    }

    @Override
    public int updateTag(TagDTO type) {
        return this.baseMapper.updateTag(type);
    }

    @Override
    public int deleteById(String id) {
        return this.baseMapper.deleteById(id);
    }

    @Override
    public List<TagDTO> listTagsAndBlogs() {
        return this.baseMapper.listTagsAndBlogs();
    }

    @Override
    public Integer getCount(){
        return this.baseMapper.getCount();
    }

    @Override
    public JSONObject getAllTags() {
        JSONObject obj = new JSONObject();

        List<TagDTO> data = this.baseMapper.getAllTags();
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
