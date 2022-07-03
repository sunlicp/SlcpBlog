package com.slcp.devops.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.slcp.devops.service.IMusicService;
import com.slcp.devops.entity.Music;
import com.slcp.devops.mapper.IMusicMapper;
import org.springframework.stereotype.Service;

/**
 * @author: SunXiaoWei
 * @description: 音乐
 * @create: 2022-06-28 09:01:27
 **/
@Service
public class MusicServiceImpl extends ServiceImpl<IMusicMapper, Music> implements IMusicService {

}
