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
import org.apache.ibatis.annotations.Delete;
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

    //教师申请课程
    @PostMapping("/addCourseApp")
    public Result addCourseApp(@RequestBody CourseApp courseApp,
                               HttpServletRequest request){
        String userId= JwtUtils.getMemberIdByJwtToken(request);   //返回用户id
        System.out.println("userId = " + userId);
        String userName = JwtUtils.getMemberNameByJwtToken(request);  //返回用户名
        System.out.println("userName = " + userName);

        courseApp.setUserId(userId);
        courseApp.setUserName(userName);

        courseAppService.save(courseApp);

        return Result.ok();
    }

    //教师获取个人预约信息
    //需要分页
    @PostMapping("getCourseAppInfo/{current}/{limit}")
    public Result getAppointmentInfo(@PathVariable long current,
                                     @PathVariable long limit,
                                     HttpServletRequest request){

//        Page<CourseApp> page=new Page<>(current,limit);
//
//        //构建查询条件
//        QueryWrapper<CourseApp> queryWrapper=new QueryWrapper<>();
//
        String userId= JwtUtils.getMemberIdByJwtToken(request);//返回用户id
//        System.out.println("userId = " + userId);
//        queryWrapper.eq("user_id", userId);//查找该用户的预约信息
//
//
//        //调用方法实现分页查询
//        IPage<CourseApp> usersPage=courseAppService.page(page,queryWrapper);

        Page<CourseApp> page=courseAppService.query()
                .eq("user_id",userId)
                .page(new Page<>(current,limit));

        //返回结果
        return  Result.ok(page.getRecords());
    }

    //教师取消预约
    //获取该行id，删除
    //如果审核完成，则不能取消
    @PostMapping("deleteCourseApp/{id}")
    public Result deleteCourseApp(@PathVariable Long id){

        CourseApp courseApp =courseAppService.getById(id);
        if (courseApp.getStatus().equals("未排课")){                                 //只有在预约审核中的才能取消
            CourseApp byId = courseAppService.getById(id);
            byId.setStatus("已取消");

            //  appointmentService.update();
            courseAppService.removeById(id);

            return Result.ok();
        }
        else
            return Result.fail();
    }


//    //同意预约
//    public Result agreeApp(){
//        //判断人数是否符合
//        QueryWrapper<Labuse> labuseQueryWrapper=new QueryWrapper<>();
//        labuseQueryWrapper.eq("lab_id",courseApp.getLab_id());
//        Labuse labuse=labuseService.getOne(labuseQueryWrapper);
//
//        if(courseApp.getSection()==1&&courseApp.getStudentNum()>labuse.getFirstTime())
//            return Result.fail("当前人数超过实验室可承载人数");
//        if(courseApp.getSection()==2&&courseApp.getStudentNum()>labuse.getFirstTime())
//            return Result.fail("当前人数超过实验室可承载人数");
//        if(courseApp.getSection()==3&&courseApp.getStudentNum()>labuse.getFirstTime())
//            return Result.fail("当前人数超过实验室可承载人数");
//        if(courseApp.getSection()==4&&courseApp.getStudentNum()>labuse.getFirstTime())
//            return Result.fail("当前人数超过实验室可承载人数");
//    }
}
