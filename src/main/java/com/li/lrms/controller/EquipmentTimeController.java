package com.li.lrms.controller;

import com.li.lrms.common.result.Result;
import com.li.lrms.model.equipment.Equipment;
import com.li.lrms.service.EquTimeService;
import com.li.lrms.vo.equipment.EquipmentTimeVo;
import com.li.lrms.vo.equipment.EquipmentUpdateVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/admin/equipment")
public class EquipmentTimeController {
    @Autowired
    private EquTimeService equTimeService;

    //1.查询未来某天设备列表
    /**
     * 输入设备的id和日期，时间段，将该时间段的设备状态设为1
     */
    @PostMapping("findEquTime")
    public Result findEquTime(@RequestBody EquipmentTimeVo equipmentTimeVo){

        List<Equipment> equipmentList = equTimeService.findEqu(equipmentTimeVo);//寻找符合输入条件的设备信息
        return Result.ok(equipmentList);//返回这些设备信息
    }
    //2.使用该设备

    /**
     * 获取设备id值，
     * 将该设备该日该时间段的值置为1
     * @param
     * @return
     */
    @PostMapping("useThisEqu")
    public Result useThisEqu(@RequestBody EquipmentUpdateVo equipmentUpdateVo){

        equTimeService.updateEquipmentTime(equipmentUpdateVo);

        return Result.ok();
    }
}
