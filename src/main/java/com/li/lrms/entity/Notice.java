package com.li.lrms.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.util.Date;

@Data
@TableName("notice")
public class Notice {
    private Integer nId;
    private String title;
    private String resume;
    private String content;
    private String publisher;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date pTime;
}
