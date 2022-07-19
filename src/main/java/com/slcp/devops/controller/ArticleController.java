package com.slcp.devops.controller;

import cn.hutool.core.collection.ListUtil;
import cn.hutool.http.HttpRequest;
import cn.hutool.http.HttpResponse;
import cn.hutool.http.HttpUtil;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.slcp.devops.constant.DevOpsConstant;
import com.slcp.devops.dto.RecommendDTO;
import com.slcp.devops.service.IBlogService;
import com.slcp.devops.dto.TypeDTO;
import com.slcp.devops.dto.FirstPageDTO;
import com.slcp.devops.service.ITypeService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.List;

/**
 * @author: SunXiaoWei
 * @create: 2022/2/1210:49
 **/
@Controller
@AllArgsConstructor
@Api(value = "首页接口查询", tags = "首页接口查询")
@Slf4j
public class ArticleController {

    private final IBlogService blogService;
    private final ITypeService typeService;

    /**
     * 博客首页展示
     *
     * @param pageNum 当前页
     * @param model   对象
     * @return html
     */
    @GetMapping({"/","/article"})
    public String article(@RequestParam(value = "pageNum", defaultValue = "1") Integer pageNum, Model model) {
        IPage<FirstPageDTO> firstPageBlogs = blogService.getFirstPageBlogs(pageNum);
        List<RecommendDTO> recommendBlogs = ListUtil.empty();
        if (1 == pageNum) {
            recommendBlogs = blogService.getHotBlogs();
        }
        List<TypeDTO> types = typeService.getAllType();
        List<RecommendDTO> hotBlogs = blogService.getRecommendBlogs();
        List<RecommendDTO> topBlogs = blogService.getTopBlogs();

        model.addAttribute("pageInfo", firstPageBlogs);
        model.addAttribute("types", types);
        model.addAttribute("hotBlogs", hotBlogs);
        model.addAttribute("topBlogs", topBlogs);
        model.addAttribute("recommendedBlogs", recommendBlogs);
        return "article";
    }

    @GetMapping("/article/read/{id}")
    public String read(@PathVariable Long id, Model model) {
        FirstPageDTO firstPageBlog = blogService.getFirstPageDTO(id);
        blogService.getBolgOneById(id, firstPageBlog.getViews());
        firstPageBlog.setFirstPicture("background-image: url(" + firstPageBlog.getFirstPicture() + ")");
        model.addAttribute("blog", firstPageBlog);
        return "read";
    }

    @RequestMapping("/search")
    public String search(@RequestParam("keywords") String keywords,
                         @RequestParam(value = "pageNum", defaultValue = "1", required = false) Integer pageNum,
                         Model model) {
        List<FirstPageDTO> firstPageBlogs = blogService.getSearchBlogs(keywords);
        List<RecommendDTO> hotBlogs = blogService.getRecommendBlogs();
        List<RecommendDTO> topBlogs = blogService.getTopBlogs();
        List<TypeDTO> types = typeService.getAllType();

        model.addAttribute("firstPageBlogs", firstPageBlogs);
        model.addAttribute("types", types);
        model.addAttribute("hotBlogs", hotBlogs);
        model.addAttribute("topBlogs", topBlogs);
        model.addAttribute("keywords", keywords);
        return "search";
    }

    @GetMapping("/article/category/{name}")
    public String category(@PathVariable String name, Model model) {
        try {
            URLDecoder.decode(name, "utf-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

        //分类
        List<TypeDTO> types = typeService.getAllType();
        //热门文章
        List<RecommendDTO> hotBlogs = blogService.getRecommendBlogs();
        List<RecommendDTO> topBlogs = blogService.getTopBlogs();


        List<FirstPageDTO> firstPageBlogs = blogService.getCategoryBlogs(name);

        model.addAttribute("firstPageBlogs", firstPageBlogs);
        model.addAttribute("types", types);
        model.addAttribute("hotBlogs", hotBlogs);
        model.addAttribute("topBlogs", topBlogs);
        model.addAttribute("keywords", name);
        return "search";
    }

    @RequestMapping("/article/poem")
    @ResponseBody
    @ApiOperation(httpMethod = "GET", value = "getPoem", notes = "诗")
    public String getPoem() {
        HttpRequest httpRequest = HttpUtil.createGet(DevOpsConstant.POEM_PATH).header(DevOpsConstant.POEM_KEY, DevOpsConstant.POEM_VALUE);
        HttpResponse resp = httpRequest.execute();
        int status = 200;
        if (status == resp.getStatus()) {
            String data = resp.body();
            JSONObject obj = JSONObject.parseObject(data);
            JSONObject json = (JSONObject) obj.get("data");
            return json.getString("content");
        }
        return DevOpsConstant.POEM_CONTENT;
    }
}
