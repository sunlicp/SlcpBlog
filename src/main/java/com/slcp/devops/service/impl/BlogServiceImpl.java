package com.slcp.devops.service.impl;

import cn.hutool.core.convert.Convert;
import cn.hutool.core.util.ObjectUtil;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.slcp.devops.NotFountException;
import com.slcp.devops.config.DoQueryCache;
import com.slcp.devops.dto.*;
import com.slcp.devops.entity.Blog;
import com.slcp.devops.mapper.IBlogMapper;
import com.slcp.devops.service.IBlogService;
import com.slcp.devops.utils.MarkdownUtils;
import com.slcp.devops.dto.BlogDTO;
import com.slcp.devops.utils.StringUtil;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @author: Slcp
 * @date: 2020/9/22 14:33
 * @code: 一生的挚爱
 * @description:
 */
@Service("blogService")
public class BlogServiceImpl extends ServiceImpl<IBlogMapper, Blog> implements IBlogService {

    private static final ExecutorService simpleExecutorService = new ThreadPoolExecutor(
            8,
            16,
            60,
            TimeUnit.SECONDS,
            new LinkedBlockingDeque<>(32),
            new ThreadPoolExecutor.CallerRunsPolicy());

    @Override
    public IPage<BlogQueryDTO> listBlogs(IPage<BlogQueryDTO> listInfoByPage, String title) {
        return this.baseMapper.listBlogs(listInfoByPage, title);
    }

    @Override
    public int saveBlog(BlogDTO blog) {
        blog.setCreateTime(new Date());
        blog.setUpdateTime(new Date());
        blog.setViews(0);
        blog.setCommentCount(0);
        return this.baseMapper.saveBlog(blog);
    }

    @Override
    public ShowDTO getBlogById(Long id) {
        return this.baseMapper.getBlogById(id);
    }


    @Override
    public void getBolgOneById(Long id) {
        simpleExecutorService.execute(() -> this.baseMapper.updateViews(id));
    }

    @DoQueryCache
    @Override
    public FirstPageDTO getFirstPageDTO(Long id) {
        List<FirstPageDTO> blogs = this.baseMapper.getBolgOneById(id);
        if (null != blogs && blogs.size() > 0) {
            return blogs.get(0);
        }
        return null;
    }

    @Override
    public int updateBlog(ShowDTO showBlog) {
        showBlog.setUpdateTime(new Date());
        return this.baseMapper.updateBlog(showBlog);
    }

    @Override
    public int deleteBlogById(Long id) {
        return this.baseMapper.deleteBlogById(id);
    }

    @Override
    public List<SearchDTO> searchBlogs(SearchDTO searchBlog) {
        return this.baseMapper.searchBlogs(searchBlog);
    }

    @Override
    @DoQueryCache
    public IPage<FirstPageDTO> getFirstPageBlogs(Integer pageNum) {
        IPage<FirstPageDTO> page = new Page<>(Convert.toInt(pageNum, 1),6);
        return this.baseMapper.getFirstPageBlogs(page, null);
    }

    @Override
    @DoQueryCache
    public List<FirstPageDTO> getCategoryBlogs(String name) {
        return this.baseMapper.getFirstPageBlogs(name);
    }

    @Override
    public List<FirstPageDTO> getBlogsByTime(String name) {
        return this.baseMapper.getBlogsByTime(name);
    }

    @Override
    @DoQueryCache
    public List<RecommendDTO> getHotBlogs() {
        return this.baseMapper.getHotBlogs();
    }

    @Override
    @DoQueryCache
    public List<FirstPageDTO> getSearchBlogs(String query) {
        return this.baseMapper.getSearchBlogs(query);
    }

    @Override
    @DoQueryCache
    public List<FirstPageDTO> getSearchBlogsByTagName(Integer tid) {
        return this.baseMapper.getSearchBlogsByTagName(tid);
    }

    @Override
    @DoQueryCache
    public List<RecommendDTO> getRecommendBlogs() {
        return this.baseMapper.getRecommendBlogs();
    }

    @Override
    @DoQueryCache
    public List<RecommendDTO> getTopBlogs() {
        return this.baseMapper.getTopBlogs();
    }

    @Override
    @DoQueryCache
    public DetailedDTO getDetailedBlogById(Long id) {
        DetailedDTO detailedBlog = this.baseMapper.getDetailedBlogById(id);
        if (ObjectUtil.isEmpty(detailedBlog)) {
            throw new NotFountException("该博客不存在");
        }
        String content = detailedBlog.getContent();
        detailedBlog.setContent(MarkdownUtils.markdownToHtmlExtensions(content));
        this.baseMapper.getCommentCountById(id);

        return detailedBlog;
    }

    @Override
    public Integer getBlogTotal() {
        return getTotal();
    }

    @DoQueryCache
    private Integer getTotal() {
        return this.baseMapper.getBlogTotal();
    }

    @Override
    public Integer getBlogViewTotal() {
        return this.baseMapper.getBlogViewTotal();
    }

    @Override
    public Integer getBlogCommentTotal() {
        return this.baseMapper.getBlogCommentTotal();
    }

    @Override
    public Integer getBlogMessageTotal() {
        return this.baseMapper.getBlogMessageTotal();
    }

    @Override
    public List<FirstPageDTO> getBlogsByTypeId(Long id) {
        return this.baseMapper.getBlogsByTypeId(id);
    }

    @Override
    public Integer getCount() {
        return this.baseMapper.getCount();
    }

    @Override
    public BlogQueryDTO blogInfo(String id) {
        if (StringUtil.isBlank(id)) {
            return null;
        }
        BlogQueryDTO blogInfo = this.baseMapper.getBlogInfoById(id);
        List<String> tagIds = this.baseMapper.getTagId(Long.valueOf(id));
        blogInfo.setTagIds(tagIds);
        return blogInfo;
    }
}