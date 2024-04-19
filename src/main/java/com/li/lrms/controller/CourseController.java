package com.li.lrms.controller;

import com.alibaba.excel.util.StringUtils;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.li.lrms.common.result.Result;

import com.li.lrms.entity.Course;
import com.li.lrms.entity.CourseVo;
import com.li.lrms.entity.SC;
import com.li.lrms.entity.SCVo;
import com.li.lrms.mapper.CourseMapper;
import com.li.lrms.service.impl.CourseServiceImpl;
import com.li.lrms.service.impl.SCServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RestController
@RequestMapping("/admin/member/course")
public class CourseController {
    @Autowired
    private CourseServiceImpl courseService;
    @Autowired
    private CourseMapper courseMapper;
    @Autowired
    private SCServiceImpl scService;

    //1.条件查询课程
    @PostMapping("findpage/{current}/{limit}")
    public Result findPageUser(@PathVariable long current,
                               @PathVariable long limit,
                               @RequestBody(required = false) CourseVo courseVo){
        //创建page对象,传递当前页，每页记录数
        Page<Course> page=new Page<>(current,limit);

        QueryWrapper<Course> wrapper=new QueryWrapper<>();
        String cname = courseVo.getCname();
        String lname = courseVo.getLname();


        if (!StringUtils.isEmpty(cname)){     //输入非空
            wrapper.like("cname",cname);
        }
        if (!StringUtils.isEmpty(lname)){
            wrapper.like("lname", lname); //获取用户id
        }
        //调用方法实现分页查询
        IPage<Course> iPage=courseService.page(page,wrapper);
        //返回结果
        return  Result.ok(iPage);

    }


    //2.发布课程
    @PostMapping("addCourse")
    public Result addCourse(@RequestBody Course course){
        boolean save = courseService.save(course);
        if(save)
            return Result.ok();
        else
            return Result.fail();
    }
    //3.删除课程
    @GetMapping("deleteCourse/{id}")
    public Result deleteCourse(@PathVariable Integer id){
        QueryWrapper<Course> wrapper=new QueryWrapper<>();
        wrapper.eq("uuid",id);
        boolean remove = courseService.remove(wrapper);
        if (remove)
            return Result.ok();
        else
            return Result.fail();
    }

    //4.订阅课程
    @PostMapping("addSC")
    public Result addSC(@RequestBody SCVo scVo){
        String userId = scVo.getUserId();
        String userName = scVo.getUserName();
        Integer uuid = scVo.getUuid();
        QueryWrapper<Course> wrapper=new QueryWrapper<>();
        wrapper.eq("uuid",uuid);
        Course course = courseService.getOne(wrapper);
        SC sc=new SC();

        QueryWrapper<SC> wrapper1=new QueryWrapper<>();
        wrapper1.eq("uid",userId)
                .eq("uname",userName)
                .eq("cid",course.getCid());
        SC one = scService.getOne(wrapper1);
        if (one==null){
            sc.setCid(course.getCid());
            sc.setCname(course.getCname());
            sc.setLid(course.getLid());
            sc.setLname(course.getLname());
            sc.setUid(userId);
            sc.setUname(userName);
            sc.setCtype(course.getCtype());
            sc.setCdate(course.getCdate());

            boolean save = scService.save(sc);
            if (save)
                return Result.ok();
            else return Result.fail();
        }
        else
            return Result.fail();

    }

}
