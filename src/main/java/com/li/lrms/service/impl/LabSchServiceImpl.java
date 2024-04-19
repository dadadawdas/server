package com.li.lrms.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.li.lrms.mapper.LabSchMapper;
import com.li.lrms.model.labuse.LabScheduled;
import com.li.lrms.service.LabSchService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LabSchServiceImpl extends ServiceImpl<LabSchMapper, LabScheduled> implements LabSchService {


    @Override
    public List<LabScheduled> searchLab1Sched(String labId) {

        return baseMapper.searchLab1Sched();
    }

    @Override
    public String selectByUid(String uid) {
        return baseMapper.selectLabId(uid);
    }

    @Override
    public List<LabScheduled> searchLab2Sched(String labId) {
        return baseMapper.searchLab2Sched();
    }

    @Override
    public List<LabScheduled> searchLab3Sched(String labId) {
        return baseMapper.searchLab3Sched();
    }
}


