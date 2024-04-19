package com.li.lrms.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.li.lrms.entity.Course;
import com.li.lrms.mapper.CourseMapper;
import com.li.lrms.service.CourseService;
import org.springframework.stereotype.Service;

@Service

public class CourseServiceImpl extends ServiceImpl<CourseMapper, Course> implements CourseService {

}
