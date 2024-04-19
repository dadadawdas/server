package com.li.lrms.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data
@TableName("rp")
public class Rp {
    private Integer uuid;
    private String tname;
    private String rname;
    private String rinfo;
}
