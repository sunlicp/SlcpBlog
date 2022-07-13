package com.slcp.devops.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.slcp.devops.config.DoQueryCache;
import com.slcp.devops.service.*;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 * @author: SunXiaoWei
 * @create: 2022/2/1611:49
 **/
@Service
public class AboutServiceImpl implements AboutService {

    @Resource
    private ArchiveService archiveService;
    @Resource
    private ITypeService typeService;
    @Resource
    private ITagService tagService;
    @Resource
    private IBlogService blogService;

    @Override
    @DoQueryCache
    public JSONObject getQuantityStatistics() {
        JSONObject data = archiveService.getMon();
        List<Map<String, String>> types = typeService.getAllOfMap();
        JSONObject allTags = tagService.getAllTags();
        data.put("categoryEchartsList", types);
        data.put("tag", allTags.get("tag"));
        data.put("tagCount", allTags.get("tagCount"));
        return data;
    }

    @Override
    @DoQueryCache
    public JSONObject getQuantityStatisticsBySum() {
        JSONObject data = new JSONObject();
        data.put("blogs", blogService.getCount());
        data.put("types", typeService.getCount());
        data.put("tags", tagService.getCount());
        return data;
    }
}
