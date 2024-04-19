package com.li.lrms.service.impl;


import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.li.lrms.entity.Notice;
import com.li.lrms.mapper.NoticeMapper;
import com.li.lrms.service.NoticeService;
import org.springframework.stereotype.Service;

@Service
public class NoticeServiceImpl extends ServiceImpl<NoticeMapper, Notice> implements NoticeService {

}
