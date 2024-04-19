package com.li.lrms.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.li.lrms.model.lab.Laboratory;
import com.li.lrms.model.labuse.Labuse;

import java.text.ParseException;

public interface LabuseService extends IService<Labuse> {
    void updateLab(String today) throws ParseException;

    Laboratory getLabById(Long uid);



}
