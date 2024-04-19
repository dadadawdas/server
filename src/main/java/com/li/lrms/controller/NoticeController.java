package com.li.lrms.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.li.lrms.common.result.Result;
import com.li.lrms.entity.Notice;
import com.li.lrms.mapper.NoticeMapper;
import com.li.lrms.service.impl.NoticeServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/admin/member/notice")
public class NoticeController {
    @Autowired
    private NoticeServiceImpl noticeService;
    @Autowired
    private NoticeMapper noticeMapper;

    //1.查询所有公告
    @GetMapping("findAllNotice")
    public Result findAllNotices(){
        List<Notice> NoticesList = noticeService.list();
        return Result.ok(NoticesList);
    }
    //2.添加公告
    @PostMapping("addNotice")
    public Result addNotice(@RequestBody Notice notice){
        boolean save = noticeService.save(notice);
        if (save)
            return Result.ok();
        else
            return Result.fail();
    }
    //3.根据id获取公告
    @GetMapping("getNotice/{id}")
    public Result getNotice(@PathVariable Integer id){
        QueryWrapper<Notice> wrapper=new QueryWrapper<>();
        wrapper.eq("n_id",id);
        Notice one = noticeService.getOne(wrapper);
        return Result.ok(one);
    }

}
