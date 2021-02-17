package com.hyrcb.hydp.modules.course.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.ruoyi.framework.aspectj.lang.annotation.Excel;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import java.io.Serializable;
import java.util.Date;


/**
 * 课程管理对象 chen_course
 * 
 * @author shenzulun
 * @date 2021-02-12
 */

@TableName("chen_course")
public class ChenCourse implements Serializable {

private static final long serialVersionUID=1L;


    /** 课程号 */
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /** 课程名称 */
    @Excel(name = "课程名称")
    private String courseName;

    /** 授课教师 */
    @Excel(name = "授课教师")
    private Long courseTeacherId;

    /** 课程学分 */
    @Excel(name = "课程学分")
    private String courseGrade;

    /** 上课地点 */
    @Excel(name = "上课地点")
    private String courseAddress;

    /** 所属年份 */
    @Excel(name = "所属年份")
    private String couresYear;

    /** 所属学期1上学期，2下学期 */
    @Excel(name = "所属学期1上学期，2下学期")
    private String courseXueqi;

    /** 上课时间 */
    @Excel(name = "上课时间")
    private String courseTime;

    /** 状态 */
    @Excel(name = "状态")
    private String status;

    /** 是否可选 */
    private String xuanze;
    /**备注 */
    @Excel(name = "备注")
    private String beizhu;

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }
    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public String getCourseName() {
        return courseName;
    }
    public void setCourseTeacherId(Long courseTeacherId) {
        this.courseTeacherId = courseTeacherId;
    }

    public Long getCourseTeacherId() {
        return courseTeacherId;
    }
    public void setCourseGrade(String courseGrade) {
        this.courseGrade = courseGrade;
    }

    public String getCourseGrade() {
        return courseGrade;
    }
    public void setCourseAddress(String courseAddress) {
        this.courseAddress = courseAddress;
    }

    public String getCourseAddress() {
        return courseAddress;
    }
    public void setCouresYear(String couresYear) {
        this.couresYear = couresYear;
    }

    public String getCouresYear() {
        return couresYear;
    }
    public void setCourseXueqi(String courseXueqi) {
        this.courseXueqi = courseXueqi;
    }

    public String getCourseXueqi() {
        return courseXueqi;
    }
    public void setCourseTime(String courseTime) {
        this.courseTime = courseTime;
    }

    public String getCourseTime() {
        return courseTime;
    }
    public void setStatus(String status) {
        this.status = status;
    }

    public String getStatus() {
        return status;
    }
    public void setXuanze(String xuanze) {
        this.xuanze = xuanze;
    }

    public String getXuanze() {
        return xuanze;
    }

    public void setBeizhu(String beizhu) {
        this.beizhu = beizhu;
    }

    public String getBeizhu() {
        return beizhu;
    }
}
