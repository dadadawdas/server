package com.li.lrms.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.li.lrms.common.result.Result;
import com.li.lrms.model.appointment.Appointment;
import com.li.lrms.model.courseapp.CourseApp;
import com.li.lrms.model.labuse.Labuse;
import com.li.lrms.service.CourseAppService;
import com.li.lrms.service.LabuseService;
import com.li.lrms.util.JwtUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

/**
 * ClassName: CourseAppController
 * Package: com.li.lrms.controller
 * Description:
 *
 * @Author: Mr.zechaowei
 * @Create: 2024/4/20 - 15:20
 * @Version: v1.0
 */
@CrossOrigin
@RestController
@RequestMapping("/admin/courseApp")
public class CourseAppController {
    @Autowired
    private CourseAppService courseAppService;

    @Autowired
    private LabuseService labuseService;

    //申请课程
    @PostMapping("/addCourseApp")
    public Result addCourseApp(@RequestBody CourseApp courseApp,
                               HttpServletRequest request){
        String userId= JwtUtils.getMemberIdByJwtToken(request);   //返回用户id
        System.out.println("userId = " + userId);
        String userName = JwtUtils.getMemberNameByJwtToken(request);  //返回用户名
        System.out.println("userName = " + userName);

        courseApp.setUserId(userId);
        courseApp.setUserName(userName);

        //判断人数是否符合
        QueryWrapper<Labuse> labuseQueryWrapper=new QueryWrapper<>();
        labuseQueryWrapper.eq("lab_id",courseApp.getLab_id());
        System.out.println(courseApp.getLab_id());
        Labuse labuse=labuseService.getOne(labuseQueryWrapper);

        if(courseApp.getSection()==1&&courseApp.getStudentNum()>labuse.getFirstTime())
            return Result.fail("当前人数超过实验室可承载人数");
        if(courseApp.getSection()==2&&courseApp.getStudentNum()>labuse.getFirstTime())
            return Result.fail("当前人数超过实验室可承载人数");
        if(courseApp.getSection()==3&&courseApp.getStudentNum()>labuse.getFirstTime())
            return Result.fail("当前人数超过实验室可承载人数");
        if(courseApp.getSection()==4&&courseApp.getStudentNum()>labuse.getFirstTime())
            return Result.fail("当前人数超过实验室可承载人数");

        courseAppService.save(courseApp);

        return Result.ok();
    }

    //获取个人预约信息
    //需要分页
    @PostMapping("getCourseAppInfo/{current}/{limit}")
    public Result getAppointmentInfo(@PathVariable long current,
                                     @PathVariable long limit,
                                     HttpServletRequest request){

        Page<CourseApp> page=new Page<>(current,limit);

        //构建查询条件
        QueryWrapper<CourseApp> queryWrapper=new QueryWrapper<>();

        String userId= JwtUtils.getMemberIdByJwtToken(request);//返回用户id
        System.out.println("userId = " + userId);
        queryWrapper.eq("user_id", userId);//查找该用户的预约信息


        //调用方法实现分页查询
        IPage<CourseApp> usersPage=courseAppService.page(page,queryWrapper);

        //返回结果
        return  Result.ok(usersPage);
    }

}
