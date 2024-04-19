package com.li.lrms.vo.labuse;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class LabuseUpdateVo {
    @ApiModelProperty(value = "实验室uid")
    private Long uid;

//    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd" , timezone = "GMT+8")
//    private Date today;
    private  String today;
}
