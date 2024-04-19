package com.li.lrms.service.impl;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.li.lrms.mapper.EquMapper;
import com.li.lrms.model.equipment.Equipment;
import com.li.lrms.service.EquService;
import org.springframework.stereotype.Service;

@Service
public class EquServiceImpl extends ServiceImpl<EquMapper, Equipment> implements EquService {
}
