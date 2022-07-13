package com.slcp.devops.service.impl;

import cn.hutool.core.collection.ListUtil;
import cn.hutool.core.util.ObjectUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.slcp.devops.entity.TagBlogRel;
import com.slcp.devops.mapper.ITagBlogRelMapper;
import com.slcp.devops.service.ITagBlogRelService;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author: Slcp
 * @create: 2022-07-09 23:59:19
 **/
@Service("tagBlogRelService")
public class TagBlogRelServiceImpl extends ServiceImpl<ITagBlogRelMapper, TagBlogRel> implements ITagBlogRelService {
    @Override
    public boolean saveTagBlog(Object tagIds, Long blogId) {
        List<TagBlogRel> tagBlog = ListUtil.list(false);
        for (Object tagId : (List<?>) tagIds) {
            if (ObjectUtil.isEmpty(tagId)) {
                continue;
            }
            TagBlogRel tagBlogRel = new TagBlogRel(null, Long.valueOf(String.valueOf(tagId)), blogId);
            tagBlog.add(tagBlogRel);
        }
        return this.saveBatch(tagBlog);
    }

    @Override
    public boolean removeTagByBlogId(Long id) {
        return this.remove(new QueryWrapper<TagBlogRel>().lambda().eq(TagBlogRel::getBlogId, id));
    }
}
