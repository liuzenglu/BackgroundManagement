package com.hyrcb.hydp.modules.ksbm.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.ruoyi.framework.aspectj.lang.annotation.Excel;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import java.io.Serializable;
import java.util.Date;


/**
 * 考试报名对象 chen_user_kaoshi
 * 
 * @author liuzenglu
 * @date 2021-02-16
 */

@TableName("chen_user_kaoshi")
public class ChenUserKaoshi implements Serializable {

private static final long serialVersionUID=1L;


    /** $column.columnComment */
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /** 学生id */
    @Excel(name = "学生id")
    private Long userId;

    /** 考试id */
    @Excel(name = "考试id")
    private Long kaoshiId;

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
    public void setKaoshiId(Long kaoshiId) {
        this.kaoshiId = kaoshiId;
    }

    public Long getKaoshiId() {
        return kaoshiId;
    }
}
