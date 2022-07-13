package com.slcp.devops.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.slcp.devops.entity.TagBlogRel;

/**
 * @author: Slcp
 * @create: 2022-07-09 23:58:46
 **/
public interface ITagBlogRelService extends IService<TagBlogRel> {

    /**
     * 批量保存
     * @param tagIds 标签id
     * @param blogId 博客id
     * @return boolean
     */
    boolean saveTagBlog(Object tagIds, Long blogId);

    /**
     * 根据id删除标签
     * @param id 博客id
     * @return boolean
     */
    boolean removeTagByBlogId(Long id);
}
