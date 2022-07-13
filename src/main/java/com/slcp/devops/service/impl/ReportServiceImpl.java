package com.slcp.devops.service.impl;

import cn.hutool.core.map.MapUtil;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.slcp.devops.entity.SysReport;
import com.slcp.devops.mapper.IReportMapper;
import com.slcp.devops.service.IReportService;
import org.springframework.stereotype.Service;

import java.util.*;


/**
 * @author Slcp
 */
@Service
public class ReportServiceImpl extends ServiceImpl<IReportMapper, SysReport> implements IReportService {

    @Override
    public Map listReport() {
        List<SysReport> reports = this.list();
        //不同的学院容器
        Set<String> set = new HashSet<>();
        //不同时间容器
        Set time = new TreeSet();
        for (SysReport report : reports) {
            //将不同的学院进行存储
            String academy = report.getAcademy();
            set.add(academy);
            //将不同的时间进行存储
            time.add(report.getDate());
        }
        //封装数据的容器
        Map map = new HashMap();
        //提交数据给用户
        HashMap legend = new HashMap();
        legend.put("data", set);
        map.put("legend", legend);


        //存储每个学院每天总数
        List series = new ArrayList();

        for (String s : set) {
            series.add(map(reports, s));
        }
        map.put("series", series);

        //获取所有时间
        HashMap hashMap = new HashMap();
        hashMap.put("boundaryGap", false);
        hashMap.put("data", time);
        List xAxis = new ArrayList();
        xAxis.add(hashMap);
        map.put("xAxis", xAxis);
        return map;
    }

    public HashMap<String, Object> map(List<SysReport> reports, String academy) {
        List<Integer> list = new ArrayList<>();
        for (SysReport report : reports) {
            if (academy.equals(report.getAcademy())) {
                list.add(report.getCount());
            }
        }
        //将相同学院不同时间的出勤异常数量分别进行保存
        HashMap<String, Object> hashMap = MapUtil.newHashMap();
        hashMap.put("areaStyle", "");
        hashMap.put("data", list);
        hashMap.put("name", academy);
        hashMap.put("stack", "总数");
        hashMap.put("type", "line");
        return hashMap;
    }
}
