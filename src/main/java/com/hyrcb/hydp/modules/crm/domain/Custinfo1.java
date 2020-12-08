package com.hyrcb.hydp.modules.crm.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.ruoyi.framework.aspectj.lang.annotation.Excel;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import java.io.Serializable;
import java.util.Date;


/**
 * 客户信息查询对象 crm_custinfo
 * 
 * @author shenzulun
 * @date 2020-05-25
 */

@TableName("crm_custinfo")
public class Custinfo1 implements Serializable {

	private static final long serialVersionUID=1L;


    /** 主键 */
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /** 客户号 */
    @Excel(name = "客户号")
    private String custNo;

    /** 客户名称 */
    @Excel(name = "客户名称")
    private String custName;

    /** 证件类型 */
    @Excel(name = "证件类型")
    private String certType;

    /** 证件号码 */
    @Excel(name = "证件号码")
    private String certNo;

    /** 联系电话 */
    @Excel(name = "联系电话")
    private String telNo;

    /** 联系地址 */
    @Excel(name = "联系地址")
    private String address;

    /** 创建人 */
    @Excel(name = "创建人")
    private String createBy;

    /** 更新人 */
    @Excel(name = "更新人")
    private String updateBy;

    /** 创建时间 */
    @Excel(name = "创建时间" , width = 30, dateFormat = "yyyy-MM-dd")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date createTime;

    /** 更新时间 */
    @Excel(name = "更新时间" , width = 30, dateFormat = "yyyy-MM-dd")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date updateTime;

    public void setId(Long id) 
    {
        this.id = id;
    }

    public Long getId() 
    {
        return id;
    }
    public void setCustNo(String custNo) 
    {
        this.custNo = custNo;
    }

    public String getCustNo() 
    {
        return custNo;
    }
    public void setCustName(String custName) 
    {
        this.custName = custName;
    }

    public String getCustName() 
    {
        return custName;
    }
    public void setCertType(String certType) 
    {
        this.certType = certType;
    }

    public String getCertType() 
    {
        return certType;
    }
    public void setCertNo(String certNo) 
    {
        this.certNo = certNo;
    }

    public String getCertNo() 
    {
        return certNo;
    }
    public void setTelNo(String telNo) 
    {
        this.telNo = telNo;
    }

    public String getTelNo() 
    {
        return telNo;
    }
    public void setAddress(String address) 
    {
        this.address = address;
    }

    public String getAddress() 
    {
        return address;
    }
    public void setCreateBy(String createBy) 
    {
        this.createBy = createBy;
    }

    public String getCreateBy() 
    {
        return createBy;
    }
    public void setUpdateBy(String updateBy) 
    {
        this.updateBy = updateBy;
    }

    public String getUpdateBy() 
    {
        return updateBy;
    }
    public void setCreateTime(Date createTime) 
    {
        this.createTime = createTime;
    }

    public Date getCreateTime() 
    {
        return createTime;
    }
    public void setUpdateTime(Date updateTime) 
    {
        this.updateTime = updateTime;
    }

    public Date getUpdateTime() 
    {
        return updateTime;
    }
}