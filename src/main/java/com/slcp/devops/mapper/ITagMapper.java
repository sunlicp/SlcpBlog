package com.slcp.devops.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.slcp.devops.dto.TagDTO;
import com.slcp.devops.entity.Tag;

import java.util.List;

/**
 * @author: SunXiaoWei
 * @create: 2022/2/1316:46
 **/
public interface ITagMapper extends BaseMapper<Tag> {


    /**
     * 获取数据
     * @return 数据
     */
    List<TagDTO> listTags();

    /**
     * 获取数据
     * @return 数据
     */
    List<TagDTO> tagList();

    /**
     * 根据id获取标签
     * @param typeId id
     * @return r
     */
    TagDTO getTag(String typeId);

    /**
     * 保存
     * @param type 标签对象
     * @return 数值
     */
    int saveTag(TagDTO type);

    /**
     * 根据名称获取
     * @param name 名称
     * @return 对象
     */
    TagDTO getTagByName(String name);

    /**
     * 修改
     * @param type 标签
     * @return 数值
     */
    int updateTag(TagDTO type);

    /**
     * 删除
     * @param id 主键
     * @return 数值
     */
    int deleteById(String id);

    /**
     * 获取标签以及博客
     * @return 数据
     */
    List<TagDTO> listTagsAndBlogs();

    /**
     * 获取所有标签及统计数
     * @return json
     */
    List<TagDTO> getAllTags();

    /**
     * 获取总数
     * @return 数值
     */
    Integer getCount();

    /**
     * 获取标签
     * @param id
     * @return
     */
    List<TagDTO> getTagsByBid(Long id);
}
