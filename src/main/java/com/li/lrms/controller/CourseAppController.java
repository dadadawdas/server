package com.li.lrms.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.li.lrms.common.result.Result;
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
        String userName = JwtUtils.getMemberNameByJwtToken(request);  //返回用户名

        courseApp.setUserId(userId);
        courseApp.setUserName(userName);

        //判断人数是否符合
        QueryWrapper<Labuse> labuseQueryWrapper=new QueryWrapper<>();
        labuseQueryWrapper.eq("lab_id",courseApp.getLab_id());
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
}
