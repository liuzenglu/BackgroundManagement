package com.hyrcb.hydp.modules.fxq.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.ruoyi.framework.aspectj.lang.annotation.Excel;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import java.io.Serializable;
import java.util.Date;


/**
 * 考试安排对象 chen_kaoshi
 * 
 * @author shenzulun
 * @date 2021-02-12
 */

@TableName("chen_kaoshi")
public class ChenKaoshi implements Serializable {

private static final long serialVersionUID=1L;


    /** $column.columnComment */
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /** 所考课程 */
    @Excel(name = "所考课程")
    private Long courseId;

    /** 考试时间 */
    @Excel(name = "考试开始时间")
    private String begainTime;
    /** 考试时间 */
    @Excel(name = "考试结束时间")
    private String endTime;
    /** 考试地点 */
    @Excel(name = "考试地点")
    private String testAddress;
    /** 状态 */
    @Excel(name = "状态")
    private String status;
    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }
    public void setCourseId(Long courseId) {
        this.courseId = courseId;
    }

    public Long getCourseId() {
        return courseId;
    }
    public void setBegainTime(String begainTime) {
        this.begainTime = begainTime;
    }

    public String getBegainTime() {
        return begainTime;
    }
    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public String getEndTime() {
        return endTime;
    }
    public void setTestAddress(String testAddress) {
        this.testAddress = testAddress;
    }

    public String getTestAddress() {
        return testAddress;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getStatus() {
        return status;
    }
}
