package com.slcp.devops.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.slcp.devops.dto.*;
import com.slcp.devops.dto.BlogDTO;
import com.slcp.devops.entity.Blog;

import java.util.List;

/**
 * @author: Slcp
 * @date: 2020/9/22 14:32
 * @code: 一生的挚爱
 * @description:
 */
public interface IBlogService extends IService<Blog> {

    /**
     * 列表
     * @return 数据
     * @param listInfoByPage 分页条件
     * @param title 标题
     */
    IPage<BlogQueryDTO> listBlogs(IPage<BlogQueryDTO> listInfoByPage, String title);

    /**
     * 保存
     * @param blog 博客
     * @return 数值
     */
    int saveBlog(BlogDTO blog);

    /**
     * 博客
     * @param id 主键
     * @return 数据
     */
    ShowDTO getBlogById(Long id);

    /**
     * 根据id获取博客信息
     * @param id 主键
     * @return 博客信息
     */
    void getBolgOneById(Long id);


    /**
     * 获取
     * @param id 主键
     * @return FirstPageDTO
     */
    FirstPageDTO getFirstPageDTO(Long id);

    /**
     * 修改博客
     * @param showBlog 博客信息
     * @return 数值
     */
    int updateBlog(ShowDTO showBlog);

    /**
     * 删除
     * @param id 主键
     * @return 数值
     */
    int deleteBlogById(Long id);

    /**
     * 搜索
     * @param searchBlog 搜索信息
     * @return 数据
     */
    List<SearchDTO> searchBlogs(SearchDTO searchBlog);

    /**
     * 获取博客首页数据
     * @param pageNum 当前页
     * @return 博客首页数据
     */
    IPage<FirstPageDTO> getFirstPageBlogs(Integer pageNum);

    /**
     * 根据分类获取文章
     * @param name 名称
     * @return 数据
     */
    List<FirstPageDTO> getCategoryBlogs(String name);

    /**
     * 根据时间获取文章
     * @param time 时间
     * @return 数据
     */
    List<FirstPageDTO> getBlogsByTime(String time);

    /**
     * 最热博客推荐
     * @return 数据
     */
    List<RecommendDTO> getRecommendBlogs();


    /**
     * 置顶推荐
     * @return 数据
     */
    List<RecommendDTO> getTopBlogs();

    /**
     * 热门文章
     * @return 数据
     */
    List<RecommendDTO> getHotBlogs();

    /**
     * 搜索
     * @param query 条件
     * @return 数据
     */
    List<FirstPageDTO> getSearchBlogs(String query);

    /**
     * 根据标签名称搜索博客
     * @param tid 主键
     * @return 博客信息
     */
    List<FirstPageDTO> getSearchBlogsByTagName(Integer tid);

    /**
     * 博客详情
     * @param id 主键
     * @return 博客信息
     */
    DetailedDTO getDetailedBlogById(Long id);

    /**
     * 博客数量
     * @return 数值
     */
    Integer getBlogTotal();

    /**
     * 博客数量
     * @return 数值
     */
    Integer getBlogViewTotal();

    /**
     * B
     * @return R
     */
    Integer getBlogCommentTotal();

    /**
     * B
     * @return R
     */
    Integer getBlogMessageTotal();

    /**
     * 获取博客
     * @param id 主键
     * @return 数据
     */
    List<FirstPageDTO> getBlogsByTypeId(Long id);

    /**
     * 获取总数
     * @return 数值
     */
    Integer getCount();

    /**
     * 获取博客信息
     * @param id 博客id
     * @return BlogQueryDTO
     */
    BlogQueryDTO blogInfo(String id);
}