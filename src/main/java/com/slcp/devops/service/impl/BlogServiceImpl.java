package com.slcp.devops.service.impl;

import cn.hutool.core.util.ObjectUtil;
import com.github.pagehelper.PageHelper;
import com.slcp.devops.NotFountException;
import com.slcp.devops.config.DoQueryCache;
import com.slcp.devops.dto.*;
import com.slcp.devops.service.BlogService;
import com.slcp.devops.utils.MarkdownUtils;
import com.slcp.devops.mapper.BlogMapper;
import com.slcp.devops.dto.BlogDTO;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
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
public class BlogServiceImpl implements BlogService {

    @Resource
    private BlogMapper blogMapper;

    private static final ExecutorService simpleExecutorService = new ThreadPoolExecutor(
            8,
            16,
            60,
            TimeUnit.SECONDS,
            new LinkedBlockingDeque<>(32),
            new ThreadPoolExecutor.CallerRunsPolicy());

    @Override
    public List<BlogQueryDTO> listBlogs() {
        return blogMapper.listBlogs();
    }

    @Override
    public int saveBlog(BlogDTO blog) {
        blog.setCreateTime(new Date());
        blog.setUpdateTime(new Date());
        blog.setViews(0);
        blog.setCommentCount(0);
        return blogMapper.saveBlog(blog);
    }

    @Override
    public ShowDTO getBlogById(Long id) {
        return blogMapper.getBlogById(id);
    }


    @Override
    public void getBolgOneById(Integer id) {
        simpleExecutorService.execute(() -> blogMapper.updateViews(id));
    }

    @DoQueryCache
    @Override
    public FirstPageDTO getFirstPageDTO(Integer id) {
        List<FirstPageDTO> blogs = blogMapper.getBolgOneById(id);
        if (null != blogs && blogs.size() > 0) {
            return blogs.get(0);
        }
        return null;
    }

    @Override
    public int updateBlog(ShowDTO showBlog) {
        showBlog.setUpdateTime(new Date());
        return blogMapper.updateBlog(showBlog);
    }

    @Override
    public int deleteBlogById(Long id) {
        return blogMapper.deleteBlogById(id);
    }

    @Override
    public List<SearchDTO> searchBlogs(SearchDTO searchBlog) {
        return blogMapper.searchBlogs(searchBlog);
    }

    @Override
    @DoQueryCache
    public List<FirstPageDTO> getFirstPageBlogs(Integer pageNum) {
        PageHelper.startPage(pageNum, 6);
        return blogMapper.getFirstPageBlogs();
    }

    @Override
    @DoQueryCache
    public List<FirstPageDTO> getCategoryBlogs(String name) {
        return blogMapper.getFirstPageBlogs(name);
    }

    @Override
    public List<FirstPageDTO> getBlogsByTime(String name) {
        return blogMapper.getBlogsByTime(name);
    }

    @Override
    @DoQueryCache
    public List<RecommendDTO> getHotBlogs() {
        return blogMapper.getHotBlogs();
    }

    @Override
    @DoQueryCache
    public List<FirstPageDTO> getSearchBlogs(String query) {
        return blogMapper.getSearchBlogs(query);
    }

    @Override
    @DoQueryCache
    public List<FirstPageDTO> getSearchBlogsByTagName(Integer tid) {
        return blogMapper.getSearchBlogsByTagName(tid);
    }

    @Override
    @DoQueryCache
    public List<RecommendDTO> getRecommendBlogs() {
        return blogMapper.getRecommendBlogs();
    }

    @Override
    @DoQueryCache
    public List<RecommendDTO> getTopBlogs() {
        return blogMapper.getTopBlogs();
    }

    @Override
    @DoQueryCache
    public DetailedDTO getDetailedBlogById(Long id) {
        DetailedDTO detailedBlog = blogMapper.getDetailedBlogById(id);
        if (ObjectUtil.isEmpty(detailedBlog)) {
            throw new NotFountException("该博客不存在");
        }
        String content = detailedBlog.getContent();
        detailedBlog.setContent(MarkdownUtils.markdownToHtmlExtensions(content));
        blogMapper.getCommentCountById(id);

        return detailedBlog;
    }

    @Override
    public Integer getBlogTotal() {
        return getTotal();
    }

    @DoQueryCache
    private Integer getTotal() {
        return blogMapper.getBlogTotal();
    }

    @Override
    public Integer getBlogViewTotal() {
        return blogMapper.getBlogViewTotal();
    }

    @Override
    public Integer getBlogCommentTotal() {
        return blogMapper.getBlogCommentTotal();
    }

    @Override
    public Integer getBlogMessageTotal() {
        return blogMapper.getBlogMessageTotal();
    }

    @Override
    public List<FirstPageDTO> getBlogsByTypeId(Long id) {
        return blogMapper.getBlogsByTypeId(id);
    }

    @Override
    public Integer getCount() {
        return blogMapper.getCount();
    }

}