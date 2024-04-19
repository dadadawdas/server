package com.li.lrms.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.li.lrms.common.result.Result;
import com.li.lrms.entity.Lm;
import com.li.lrms.entity.Rp;
import com.li.lrms.service.impl.LmServiceImpl;
import com.li.lrms.service.impl.RpServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/admin/member/lr")
public class LmController {
    @Autowired
    private LmServiceImpl lmService;
    @Autowired
    private RpServiceImpl rpService;
    //1.实验室材料查看
    @GetMapping("findList")
    public Result findList(){
        QueryWrapper<Lm> wrapper=new QueryWrapper<>();
        List<Lm> list = lmService.list();
        return Result.ok(list);

    }
    //2.材料添加
    @PostMapping("addM")
    public Result addM(@RequestBody Lm lm){
        boolean save = lmService.save(lm);
        if (save)
            return Result.ok();
        else return Result.fail();
    }
    //3.材料删除
    @GetMapping("deleteM/{uuid}")
    public Result deleteM(@PathVariable Integer uuid){
        QueryWrapper<Lm> wrapper=new QueryWrapper<>();
        wrapper.eq("uuid",uuid);
        boolean remove = lmService.remove(wrapper);
        if (remove)
            return Result.ok();
        else return Result.fail();
    }
    //4.查看报修
    @GetMapping("findAllR")
    public Result findAllR(){
        List<Rp> list = rpService.list();
        return Result.ok(list);
    }
    //5.添加报修
    @PostMapping("addR")
    public Result addR(@RequestBody Rp rp){
        boolean save = rpService.save(rp);
        if (save)
            return Result.ok();
        else return Result.fail();
    }
    //6.删除报修
    @GetMapping("deleteR/{uuid}")
    public Result deleteR(@PathVariable Integer uuid){
        QueryWrapper<Rp> wrapper=new QueryWrapper<>();
        wrapper.eq("uuid",uuid);
        boolean remove = rpService.remove(wrapper);
        if (remove)
            return Result.ok();
        else return Result.fail();
    }
}
