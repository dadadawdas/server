package com.li.lrms.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.li.lrms.mapper.AppointmentMapper;
import com.li.lrms.mapper.CourseAppMapper;
import com.li.lrms.model.appointment.Appointment;
import com.li.lrms.model.courseapp.CourseApp;
import com.li.lrms.service.AppointmentService;
import com.li.lrms.service.CourseAppService;
import com.li.lrms.service.CourseService;
import org.springframework.stereotype.Service;

/**
 * ClassName: CourseAppServiceImpl
 * Package: com.li.lrms.service.impl
 * Description:
 *
 * @Author: Mr.zechaowei
 * @Create: 2024/4/20 - 16:11
 * @Version: v1.0
 */
@Service
public class CourseAppServiceImpl extends ServiceImpl<CourseAppMapper, CourseApp> implements CourseAppService {
}
