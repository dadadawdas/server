package com.li.lrms.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.li.lrms.model.statistics.Statistics;

import java.util.Map;

public interface AppointmentStaService extends IService<Statistics>{

    void countAppointmentDay(String day);

    Map<String, Object> showDataMap(String type, String begin, String end);
}
