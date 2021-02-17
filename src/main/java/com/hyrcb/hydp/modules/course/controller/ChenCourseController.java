package com.hyrcb.hydp.modules.course.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;

import java.util.List;
import java.util.Arrays;
import java.util.stream.Collectors;

import com.hyrcb.hydp.modules.course.domain.ChenCourseDTO;
import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.project.system.domain.SysUser;
import com.ruoyi.project.system.service.ISysUserService;
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
import com.hyrcb.hydp.modules.course.domain.ChenCourse;
import com.hyrcb.hydp.modules.course.service.IChenCourseService;
import com.ruoyi.framework.web.controller.BaseController;
import com.ruoyi.framework.web.domain.AjaxResult;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.framework.web.page.TableDataInfo;

/**
 * 课程管理Controller
 *
 * @author shenzulun
 * @date 2021-02-12
 */

@RestController
@RequestMapping("/course/course" )
public class ChenCourseController extends BaseController {

    @Autowired
    private ISysUserService userService;
    @Autowired
    private IChenCourseService iChenCourseService;

    /**
     * 查询课程管理列表
     */
//    @PreAuthorize("@ss.hasPermi('course:course:list')")
    @GetMapping("/list")
    public TableDataInfo list(ChenCourse chenCourse){
        startPage();
        List<ChenCourseDTO> list = iChenCourseService.selectChenCourseList(chenCourse);
        return getDataTable(list);
    }

    /**
     * 导出课程管理列表
     */
//    @PreAuthorize("@ss.hasPermi('course:course:export')" )
    @Log(title = "课程管理" , businessType = BusinessType.EXPORT)
    @GetMapping("/export" )
    public AjaxResult export(ChenCourse chenCourse) {
        LambdaQueryWrapper<ChenCourse> lqw = new LambdaQueryWrapper<ChenCourse>(chenCourse);
        List<ChenCourseDTO> list = iChenCourseService.selectChenCourseList(chenCourse);
        ExcelUtil<ChenCourseDTO> util = new ExcelUtil<ChenCourseDTO>(ChenCourseDTO. class);
        return util.exportExcel(list, "course" );
    }

    /**
     * 获取课程管理详细信息
     */
//    @PreAuthorize("@ss.hasPermi('course:course:query')" )
    @GetMapping(value = "/{id}" )
    public AjaxResult getInfo(@PathVariable("id" ) Long id) {
        return AjaxResult.success(iChenCourseService.getById(id));
    }

    /**
     * 新增课程管理
     */
//    @PreAuthorize("@ss.hasPermi('course:course:add')" )
    @Log(title = "课程管理" , businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody ChenCourse chenCourse) {
        return toAjax(iChenCourseService.save(chenCourse) ? 1 : 0);
    }

    /**
     * 修改课程管理
     */
//    @PreAuthorize("@ss.hasPermi('course:course:edit')" )
    @Log(title = "课程管理" , businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody ChenCourse chenCourse) {
        return toAjax(iChenCourseService.updateById(chenCourse) ? 1 : 0);
    }

    /**
     * 删除课程管理
     */
//    @PreAuthorize("@ss.hasPermi('course:course:remove')" )
    @Log(title = "课程管理" , businessType = BusinessType.DELETE)
    @DeleteMapping("/{ids}" )
    public AjaxResult remove(@PathVariable Long[] ids) {
        return toAjax(iChenCourseService.removeByIds(Arrays.asList(ids)) ? 1 : 0);
    }


    @GetMapping("/getTeacher")
    public TableDataInfo getTeacher(SysUser user)
    {
        List<SysUser> list = userService.selectUserList(user);
        return getDataTable(list);
    }
}
