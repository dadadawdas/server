package com.li.lrms.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.li.lrms.common.result.Result;
import com.li.lrms.entity.SC;
import com.li.lrms.mapper.SCMapper;
import com.li.lrms.service.impl.SCServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/admin/member/sc")
public class SCController {
    @Autowired
    private SCServiceImpl scService;
    @Autowired
    private SCMapper scMapper;

    //1.查看订阅课程
    @GetMapping("findMySC/{id}")
    public Result findMySC(@PathVariable String id){
        QueryWrapper<SC> wrapper=new QueryWrapper<>();
        wrapper.eq("uid",id);
        List<SC> list = scService.list(wrapper);
        return Result.ok(list);
    }
    //2.删除订阅课程
    @GetMapping("deleteMySC/{uuid}")
    public Result deleteMySC(@PathVariable Integer uuid){
        QueryWrapper<SC> wrapper=new QueryWrapper<>();
        wrapper.eq("uuid",uuid);
        boolean flag = scService.remove(wrapper);
        if (flag)
            return Result.ok();
        else return Result.fail();
    }
}
