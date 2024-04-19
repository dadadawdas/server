package com.li.lrms.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data
@TableName("course")
public class Course {
    private Integer uuid;
    private Integer cid;
    private String cname;
    private Integer lid;
    private String lname;
    private String cdate;
    private String ctype;
}
