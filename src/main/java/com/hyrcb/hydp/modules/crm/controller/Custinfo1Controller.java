package com.hyrcb.hydp.modules.crm.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;

import java.util.List;
import java.util.Arrays;
import com.ruoyi.common.utils.StringUtils;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.ruoyi.framework.aspectj.lang.annotation.Log;
import com.ruoyi.framework.aspectj.lang.enums.BusinessType;
import com.hyrcb.hydp.modules.crm.domain.Custinfo1;
import com.hyrcb.hydp.modules.crm.service.ICustinfo1Service;
import com.ruoyi.framework.web.controller.BaseController;
import com.ruoyi.framework.web.domain.AjaxResult;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.framework.web.page.TableDataInfo;

/**
 * 客户信息查询Controller
 *
 * @author shenzulun
 * @date 2020-05-25
 */

@RestController
@RequestMapping("/crm/custinfo1" )
public class Custinfo1Controller extends BaseController {

    @Autowired
    private ICustinfo1Service iCustinfo1Service;

    /**
     * 查询客户信息查询列表
     */
    @PreAuthorize("@ss.hasPermi('crm:custinfo1:list')")
    @GetMapping("/list")
    public TableDataInfo list(Custinfo1 custinfo1){
        startPage();
        LambdaQueryWrapper<Custinfo1> lqw = new LambdaQueryWrapper<Custinfo1>();
        if (StringUtils.isNotBlank(custinfo1.getCustNo())){
            lqw.eq(Custinfo1::getCustNo ,custinfo1.getCustNo());
        }
        if (StringUtils.isNotBlank(custinfo1.getCustName())){
            lqw.like(Custinfo1::getCustName ,custinfo1.getCustName());
        }
        if (StringUtils.isNotBlank(custinfo1.getCertType())){
            lqw.eq(Custinfo1::getCertType ,custinfo1.getCertType());
        }
        if (StringUtils.isNotBlank(custinfo1.getCertNo())){
            lqw.eq(Custinfo1::getCertNo ,custinfo1.getCertNo());
        }
        if (StringUtils.isNotBlank(custinfo1.getTelNo())){
            lqw.eq(Custinfo1::getTelNo ,custinfo1.getTelNo());
        }
        if (StringUtils.isNotBlank(custinfo1.getAddress())){
            lqw.eq(Custinfo1::getAddress ,custinfo1.getAddress());
        }
        List<Custinfo1> list = iCustinfo1Service.list(lqw);
        return getDataTable(list);
    }

    /**
     * 导出客户信息查询列表
     */
    @PreAuthorize("@ss.hasPermi('crm:custinfo1:export')" )
    @Log(title = "客户信息查询" , businessType = BusinessType.EXPORT)
    @GetMapping("/export" )
    public AjaxResult export(Custinfo1 custinfo1) {
        LambdaQueryWrapper<Custinfo1> lqw = new LambdaQueryWrapper<Custinfo1>(custinfo1);
        List<Custinfo1> list = iCustinfo1Service.list(lqw);
        ExcelUtil<Custinfo1> util = new ExcelUtil<Custinfo1>(Custinfo1. class);
        return util.exportExcel(list, "custinfo1" );
    }

    /**
     * 获取客户信息查询详细信息
     */
    @PreAuthorize("@ss.hasPermi('crm:custinfo1:query')" )
    @GetMapping(value = "/{id}" )
    public AjaxResult getInfo(@PathVariable("id" ) Long id) {
        return AjaxResult.success(iCustinfo1Service.getById(id));
    }

    /**
     * 新增客户信息查询
     */
    @PreAuthorize("@ss.hasPermi('crm:custinfo1:add')" )
    @Log(title = "客户信息查询" , businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody Custinfo1 custinfo1) {
        return toAjax(iCustinfo1Service.save(custinfo1) ? 1 : 0);
    }

    /**
     * 修改客户信息查询
     */
    @PreAuthorize("@ss.hasPermi('crm:custinfo1:edit')" )
    @Log(title = "客户信息查询" , businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody Custinfo1 custinfo1) {
        return toAjax(iCustinfo1Service.updateById(custinfo1) ? 1 : 0);
    }

    /**
     * 删除客户信息查询
     */
    @PreAuthorize("@ss.hasPermi('crm:custinfo1:remove')" )
    @Log(title = "客户信息查询" , businessType = BusinessType.DELETE)
    @DeleteMapping("/{ids}" )
    public AjaxResult remove(@PathVariable Long[] ids) {
        return toAjax(iCustinfo1Service.removeByIds(Arrays.asList(ids)) ? 1 : 0);
    }
}