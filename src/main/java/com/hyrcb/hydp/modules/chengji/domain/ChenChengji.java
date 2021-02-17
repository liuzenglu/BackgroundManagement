package com.hyrcb.hydp.modules.chengji.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.ruoyi.framework.aspectj.lang.annotation.Excel;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import java.io.Serializable;
import java.util.Date;


/**
 * 成绩对象 chen_chengji
 * 
 * @author liuzenglu
 * @date 2021-02-13
 */

@TableName("chen_chengji")
public class ChenChengji implements Serializable {

private static final long serialVersionUID=1L;


    /** $column.columnComment */
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /** 学生id */
    @Excel(name = "学生id")
    private Long userId;

    /** 学生姓名 */
    @Excel(name = "学生姓名")
    private String username;

    /** 班级id */
    @Excel(name = "班级id")
    private Long banjiId;

    /** 班级名称 */
    @Excel(name = "班级名称")
    private String banjiName;

    /** 课程id */
    @Excel(name = "课程id")
    private Long courseId;

    /** 课程名称 */
    @Excel(name = "课程名称")
    private String courseName;

    /** 教师名称 */
    @Excel(name = "教师名称")
    private String courseTeacherName;

    /** 所考分数 */
    @Excel(name = "所考分数")
    private String grade;

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }
    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getUserId() {
        return userId;
    }
    public void setUsername(String username) {
        this.username = username;
    }

    public String getUsername() {
        return username;
    }
    public void setBanjiId(Long banjiId) {
        this.banjiId = banjiId;
    }

    public Long getBanjiId() {
        return banjiId;
    }
    public void setBanjiName(String banjiName) {
        this.banjiName = banjiName;
    }

    public String getBanjiName() {
        return banjiName;
    }
    public void setCourseId(Long courseId) {
        this.courseId = courseId;
    }

    public Long getCourseId() {
        return courseId;
    }
    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public String getCourseName() {
        return courseName;
    }
    public void setCourseTeacherName(String courseTeacherName) {
        this.courseTeacherName = courseTeacherName;
    }

    public String getCourseTeacherName() {
        return courseTeacherName;
    }
    public void setGrade(String grade) {
        this.grade = grade;
    }

    public String getGrade() {
        return grade;
    }
}
