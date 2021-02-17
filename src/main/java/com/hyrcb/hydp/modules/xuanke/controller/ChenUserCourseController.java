package com.hyrcb.hydp.modules.xuanke.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;

import java.util.*;
import java.util.stream.Collectors;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.hyrcb.hydp.modules.chengji.domain.ChenChengji;
import com.hyrcb.hydp.modules.course.domain.ChenCourse;
import com.hyrcb.hydp.modules.course.service.IChenCourseService;
import com.hyrcb.hydp.modules.fxq.domain.ChenKaoshi;
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
import com.hyrcb.hydp.modules.xuanke.domain.ChenUserCourse;
import com.hyrcb.hydp.modules.xuanke.service.IChenUserCourseService;
import com.ruoyi.framework.web.controller.BaseController;
import com.ruoyi.framework.web.domain.AjaxResult;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.framework.web.page.TableDataInfo;

/**
 * 用户选课Controller
 *
 * @author liuzenglu
 * @date 2021-02-16
 */

@RestController
@RequestMapping("/xuanke/xuanke" )
public class ChenUserCourseController extends BaseController {

    @Autowired
    private IChenUserCourseService iChenUserCourseService;
    @Autowired
    private IChenCourseService iChenCourseService;
    /**
     * 查询用户选课列表
     */
//    @PreAuthorize("@ss.hasPermi('xuanke:xuanke:list')")
    @GetMapping("/list")
    public TableDataInfo list(ChenUserCourse chenUserCourse){
        startPage();
        LambdaQueryWrapper<ChenUserCourse> lqw = new LambdaQueryWrapper<ChenUserCourse>();
        if (chenUserCourse.getUserId() != null){
            lqw.eq(ChenUserCourse::getUserId ,chenUserCourse.getUserId());
        }
        if (chenUserCourse.getCourseId() != null){
            lqw.eq(ChenUserCourse::getCourseId ,chenUserCourse.getCourseId());
        }
        List<ChenUserCourse> list = iChenUserCourseService.list(lqw);
       List<Long> courseIdList =  list.stream().map(ChenUserCourse::getCourseId).collect(Collectors.toList());
        List<ChenCourse> lsit = new ArrayList<>();
        if (StringUtils.isNotEmpty(courseIdList)){
            lsit =  iChenCourseService.listByIds(courseIdList);
        }
        return getDataTable(lsit);
    }

    /**
     * 导出用户选课列表
     */
//    @PreAuthorize("@ss.hasPermi('xuanke:xuanke:export')" )
    @Log(title = "用户选课" , businessType = BusinessType.EXPORT)
    @GetMapping("/export" )
    public AjaxResult export(ChenUserCourse chenUserCourse) {
        LambdaQueryWrapper<ChenUserCourse> lqw = new LambdaQueryWrapper<ChenUserCourse>(chenUserCourse);
        List<ChenUserCourse> list = iChenUserCourseService.list(lqw);
        ExcelUtil<ChenUserCourse> util = new ExcelUtil<ChenUserCourse>(ChenUserCourse. class);
        return util.exportExcel(list, "xuanke" );
    }

    /**
     * 获取用户选课详细信息
     */
//    @PreAuthorize("@ss.hasPermi('xuanke:xuanke:query')" )
    @GetMapping(value = "/{id}" )
    public AjaxResult getInfo(@PathVariable("id" ) Long id) {
        return AjaxResult.success(iChenUserCourseService.getById(id));
    }

    /**
     * 新增用户选课
     */
//    @PreAuthorize("@ss.hasPermi('xuanke:xuanke:add')" )
    @Log(title = "用户选课" , businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody ChenUserCourse chenUserCourse) {
        List<ChenUserCourse> list = this.iChenUserCourseService.list(Wrappers.<ChenUserCourse>lambdaQuery()
                .eq(ChenUserCourse::getUserId, chenUserCourse.getUserId())
                .eq(ChenUserCourse::getCourseId, chenUserCourse.getCourseId()));
        if ( ! StringUtils.isEmpty(list)){
            return  AjaxResult.error(99999,"您已报名，不用重复报名");
        }
        return toAjax(iChenUserCourseService.save(chenUserCourse) ? 1 : 0);
    }

    /**
     * 修改用户选课
     */
//    @PreAuthorize("@ss.hasPermi('xuanke:xuanke:edit')" )
    @Log(title = "用户选课" , businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody ChenUserCourse chenUserCourse) {
        return toAjax(iChenUserCourseService.updateById(chenUserCourse) ? 1 : 0);
    }

    /**
     * 删除用户选课
     */
//    @PreAuthorize("@ss.hasPermi('xuanke:xuanke:remove')" )
    @PostMapping("/shanchu" )
    public AjaxResult remove(@RequestBody ChenUserCourse chenUserCourse) {
        Map map = new HashMap();
                map.put("user_id",chenUserCourse.getUserId());
                map.put("course_id",chenUserCourse.getCourseId());
        return toAjax(iChenUserCourseService.removeByMap(map) ? 1 : 0);
    }
}
