package com.slcp.devops.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.slcp.devops.config.DoQueryCache;
import com.slcp.devops.dto.ArchiveDTO;
import com.slcp.devops.mapper.ArchiveMapper;
import com.slcp.devops.service.ArchiveService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 * @author: SunXiaoWei
 * @create: 2022/2/1512:31
 **/
@Service
public class ArchiveServiceImpl implements ArchiveService {

    @Resource
    private ArchiveMapper archiveMapper;

    @Override
    @DoQueryCache
    public List<ArchiveDTO> getAll() {
        return archiveMapper.getAll();
    }

    @Override
    @DoQueryCache
    public List<String> getYear() {
        return archiveMapper.getYear();
    }

    @Override
    @DoQueryCache
    public JSONObject getMon() {
        JSONObject obj = new JSONObject();

        List<Map<String, String>> mon = archiveMapper.getMon();
        addJson(obj, mon, "date", "count");
        return obj;
    }

    public static void addJson(JSONObject obj, List<Map<String, String>> mon, String paramOne, String paramTwo) {
        Integer[] count = new Integer[12];
        String[] str = new String[12];
        for (int i = 0; i < mon.size(); i++) {
            str[i] = String.valueOf(mon.get(i).get("mon"));
            count[i] = Integer.parseInt(String.valueOf(mon.get(i).get("count")));
        }
        obj.put(paramOne, str);
        obj.put(paramTwo, count);
    }
}
