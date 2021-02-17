package com.hyrcb.hydp.modules.chengji.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;

import java.util.List;
import java.util.Arrays;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.hyrcb.hydp.modules.course.domain.ChenCourse;
import com.hyrcb.hydp.modules.course.service.IChenCourseService;
import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.project.system.domain.SysDept;
import com.ruoyi.project.system.domain.SysUser;
import com.ruoyi.project.system.service.ISysDeptService;
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
import com.hyrcb.hydp.modules.chengji.domain.ChenChengji;
import com.hyrcb.hydp.modules.chengji.service.IChenChengjiService;
import com.ruoyi.framework.web.controller.BaseController;
import com.ruoyi.framework.web.domain.AjaxResult;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.framework.web.page.TableDataInfo;

/**
 * 成绩Controller
 *
 * @author liuzenglu
 * @date 2021-02-13
 */

@RestController
@RequestMapping("/chengji/chengji" )
public class ChenChengjiController extends BaseController {

    @Autowired
    private IChenChengjiService iChenChengjiService;

    @Autowired
    private ISysDeptService iSysDeptService;
    @Autowired
    private ISysUserService userService;
    @Autowired
    private ISysDeptService deptService;
    @Autowired
    private IChenCourseService iChenCourseService;
    /**
     * 查询成绩列表
     */
//    @PreAuthorize("@ss.hasPermi('chengji:chengji:list')")
    @GetMapping("/list")
    public TableDataInfo list(ChenChengji chenChengji){
        startPage();
        LambdaQueryWrapper<ChenChengji> lqw = new LambdaQueryWrapper<ChenChengji>();
        if (chenChengji.getUserId() != null){
            lqw.eq(ChenChengji::getUserId ,chenChengji.getUserId());
        }
        if (StringUtils.isNotBlank(chenChengji.getUsername())){
            lqw.like(ChenChengji::getUsername ,chenChengji.getUsername());
        }
        if (chenChengji.getBanjiId() != null){
            lqw.eq(ChenChengji::getBanjiId ,chenChengji.getBanjiId());
        }
        if (StringUtils.isNotBlank(chenChengji.getBanjiName())){
            lqw.like(ChenChengji::getBanjiName ,chenChengji.getBanjiName());
        }
        if (chenChengji.getCourseId() != null){
            lqw.eq(ChenChengji::getCourseId ,chenChengji.getCourseId());
        }
        if (StringUtils.isNotBlank(chenChengji.getCourseName())){
            lqw.like(ChenChengji::getCourseName ,chenChengji.getCourseName());
        }
        if (StringUtils.isNotBlank(chenChengji.getCourseTeacherName())){
            lqw.like(ChenChengji::getCourseTeacherName ,chenChengji.getCourseTeacherName());
        }
        if (StringUtils.isNotBlank(chenChengji.getGrade())){
            lqw.eq(ChenChengji::getGrade ,chenChengji.getGrade());
        }
        List<ChenChengji> list = iChenChengjiService.list(lqw);
        return getDataTable(list);
    }

    /**
     * 导出成绩列表
     */
//    @PreAuthorize("@ss.hasPermi('chengji:chengji:export')" )
    @Log(title = "成绩" , businessType = BusinessType.EXPORT)
    @GetMapping("/export" )
    public AjaxResult export(ChenChengji chenChengji) {
        LambdaQueryWrapper<ChenChengji> lqw = new LambdaQueryWrapper<ChenChengji>(chenChengji);
        List<ChenChengji> list = iChenChengjiService.list(lqw);
        ExcelUtil<ChenChengji> util = new ExcelUtil<ChenChengji>(ChenChengji. class);
        return util.exportExcel(list, "chengji" );
    }

    /**
     * 获取成绩详细信息
     */
//    @PreAuthorize("@ss.hasPermi('chengji:chengji:query')" )
    @GetMapping(value = "/{id}" )
    public AjaxResult getInfo(@PathVariable("id" ) Long id) {
        return AjaxResult.success(iChenChengjiService.getById(id));
    }

    /**
     * 新增成绩
     */
//    @PreAuthorize("@ss.hasPermi('chengji:chengji:add')" )
    @Log(title = "成绩" , businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody ChenChengji chenChengji) {
        List<ChenChengji> list = this.iChenChengjiService.list(Wrappers.<ChenChengji>lambdaQuery()
                .eq(ChenChengji::getUserId, chenChengji.getUserId())
                .eq(ChenChengji::getCourseId, chenChengji.getCourseId()));
        if ( ! StringUtils.isEmpty(list)){
            return  AjaxResult.error(99999,"同一个学生不能有相同课程的考试成绩");
        }
       SysUser sysUser = userService.selectUserById(chenChengji.getUserId());
        SysDept sysDept =  deptService.selectDeptById(sysUser.getDeptId());
        chenChengji.setUsername(sysUser.getNickName());
        chenChengji.setBanjiId(sysDept.getDeptId());
        chenChengji.setBanjiName(sysDept.getDeptName());

       ChenCourse chenCourse =  iChenCourseService.getById(chenChengji.getCourseId());
        chenChengji.setCourseName(chenCourse.getCourseName());

        SysUser systeacher = userService.selectUserById(chenCourse.getCourseTeacherId());
        chenChengji.setCourseTeacherName(systeacher.getNickName());
        return toAjax(iChenChengjiService.save(chenChengji) ? 1 : 0);
    }

    /**
     * 修改成绩
     */
//    @PreAuthorize("@ss.hasPermi('chengji:chengji:edit')" )
    @Log(title = "成绩" , businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody ChenChengji chenChengji) {
        List<ChenChengji> list = this.iChenChengjiService.list(Wrappers.<ChenChengji>lambdaQuery()
                .ne(ChenChengji::getId, chenChengji.getId())
                .eq(ChenChengji::getUserId, chenChengji.getUserId())
                .eq(ChenChengji::getCourseId, chenChengji.getCourseId()));
        if ( ! StringUtils.isEmpty(list)){
            return  AjaxResult.error(99999,"同一个学生不能有相同课程的考试成绩");
        }
        SysUser sysUser = userService.selectUserById(chenChengji.getUserId());
        SysDept sysDept =  deptService.selectDeptById(sysUser.getDeptId());
        chenChengji.setUsername(sysUser.getNickName());
        chenChengji.setBanjiId(sysDept.getDeptId());
        chenChengji.setBanjiName(sysDept.getDeptName());

        ChenCourse chenCourse =  iChenCourseService.getById(chenChengji.getCourseId());
        chenChengji.setCourseName(chenCourse.getCourseName());

        SysUser systeacher = userService.selectUserById(chenCourse.getCourseTeacherId());
        chenChengji.setCourseTeacherName(systeacher.getNickName());
        return toAjax(iChenChengjiService.updateById(chenChengji) ? 1 : 0);
    }

    /**
     * 删除成绩
     */
//    @PreAuthorize("@ss.hasPermi('chengji:chengji:remove')" )
    @Log(title = "成绩" , businessType = BusinessType.DELETE)
    @DeleteMapping("/{ids}" )
    public AjaxResult remove(@PathVariable Long[] ids) {
        return toAjax(iChenChengjiService.removeByIds(Arrays.asList(ids)) ? 1 : 0);
    }

    @GetMapping(value = "/banjilist" )
    public AjaxResult getBanjilist() {
        SysDept dept = new SysDept();
                dept.setParentId(102L);
        return AjaxResult.success(iSysDeptService.selectDeptList(dept));
    }
}
