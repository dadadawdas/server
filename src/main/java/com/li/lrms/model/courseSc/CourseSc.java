package com.li.lrms.model.courseSc;

import lombok.Data;
import org.apache.ibatis.ognl.OgnlRuntime;

/**
 * ClassName: CourseSc
 * Package: com.li.lrms.model.courseSc
 * Description:
 *
 * @Author: Mr.zechaowei
 * @Create: 2024/4/20 - 16:49
 * @Version: v1.0
 */
@Data
public class CourseSc {
    private Integer semester;//学期
    private String labId;//实验室id
    private String labName;//实验室名称
    private Integer firstWeek;//起始周
    private Integer lastWeek;//结束周
    private Integer section;//节次
    private String courseName;//课程名称
    private String userId;//教师id
    private Integer classId;//班级
}
