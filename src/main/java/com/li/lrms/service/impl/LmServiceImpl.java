package com.li.lrms.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.li.lrms.entity.Lm;
import com.li.lrms.mapper.LmMapper;
import com.li.lrms.service.LmService;
import org.springframework.stereotype.Service;

@Service

public class LmServiceImpl extends ServiceImpl<LmMapper, Lm> implements LmService {
}
