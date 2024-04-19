package com.li.lrms.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data
@TableName("lm")
public class Lm {
    private Integer uuid;
    private String lname;
    private String mname;
    private String minfo;
    private Integer mcount;
}
