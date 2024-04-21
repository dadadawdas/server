package com.li.lrms.model.courseapp;

import lombok.Data;

/**
 * ClassName: CourseApp
 * Package: com.li.lrms.model.courseapp
 * Description:
 *
 * @Author: Mr.zechaowei
 * @Create: 2024/4/20 - 15:25
 * @Version: v1.0
 */
@Data
public class CourseApp {
    private String id;//数据库自增长
//    private String lab_id;//前端
    private String lab_name;//前端
    private String status;//数据库
    private Integer count;//数据库
    private Integer semester;//前端
    private String userId;//后台
    private String userName;//后台
    private String courseName;//前端
    private String classId;//前端 班级
    private Integer studentNum;//前端
    private Integer firstWeek;//前端
    private Integer lastWeek;//前端
    private Integer section;//节次 前端
    private String day;//星期几 前端
}
