package com.slcp.devops.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.slcp.devops.dto.*;
import com.slcp.devops.dto.BlogDTO;
import com.slcp.devops.entity.Blog;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author: Slcp
 * @date: 2020/9/22 14:35
 * @code: 一生的挚爱
 * @description:
 */
@Repository
@Mapper
public interface IBlogMapper extends BaseMapper<Blog> {

    /**
     * 列表
     * @return 数据
     * @param listInfoByPage 分页条件
     * @param title 标题
     */
    IPage<BlogQueryDTO> listBlogs(IPage<BlogQueryDTO> listInfoByPage,@Param("title") String title);

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
    List<FirstPageDTO> getBolgOneById(@Param("id") Long id);

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
     * @param page 分页参数
     * @param name 分类名
     * @return 博客首页数据
     */
    IPage<FirstPageDTO> getFirstPageBlogs(IPage<FirstPageDTO> page, @Param("name") String name);

    /**
     * 根据分类获取文章
     * @param name 名称
     * @return 数据
     */
    List<FirstPageDTO> getFirstPageBlogs(@Param("name") String name);

    /**
     * 根据时间获取博客
     * @param time 时间
     * @return 数据
     */
    List<FirstPageDTO> getBlogsByTime(@Param("time") String time);

    /**
     * 热门文章
     * @return 数据
     */
    List<RecommendDTO> getHotBlogs();

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
     * 搜索
     * @param name 条件
     * @return 数据
     */
    List<FirstPageDTO> getSearchBlogs(@Param("name") String name);

    /**
     * 根据标签名称搜索博客
     * @param id 主键
     * @return 博客信息
     */
    List<FirstPageDTO> getSearchBlogsByTagName(Integer id);

    /**
     * 获取博客
     * @param id 主键
     * @return 数据
     */
    DetailedDTO getDetailedBlogById(@Param("id") Long id);

    /**
     * 修改浏览数
     * @param id 主键
     */
    void updateViews(Long id);

    /**
     * 博客详情
     * @param id 主键
     */
    void getCommentCountById(@Param("id") Long id);

    /**
     * 查询博客总数量
     * @return 数值
     */
    Integer getBlogTotal();

    /**
     * 查询访问总数
     * @return 数值
     */
    Integer getBlogViewTotal();

    /**
     * 查询评论总数
     * @return 数值
     */
    Integer getBlogCommentTotal();

    /**
     * 查询留言总数
     * @return 数值
     */
    Integer getBlogMessageTotal();

    /**
     * 获取数量
     * @return 数值
     */
    Integer getCount();

    /**
     * 根据分类id获取博客
     * @param id 主键
     * @return 数据
     */
    List<FirstPageDTO> getBlogsByTypeId(@Param("id") Long id);

    /**
     * 获取博客信息
     * @param id 博客id
     * @return BlogQueryDTO
     */
    BlogQueryDTO getBlogInfoById(@Param("id") String id);

    /**
     * 根据博客id获取标签
     * @param id
     * @return List<Long>
     */
    List<String> getTagId(@Param("id") Long id);
}