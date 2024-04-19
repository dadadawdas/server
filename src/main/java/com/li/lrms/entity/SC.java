package com.li.lrms.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data
@TableName("SC")
public class SC{
    private Integer uuid;
    private Integer cid;
    private String cname;
    private Integer lid;
    private String lname;
    private String uid;
    private String uname;
    private String cdate;
    private String ctype;
}
