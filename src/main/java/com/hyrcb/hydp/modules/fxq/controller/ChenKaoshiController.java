package com.hyrcb.hydp.modules.fxq.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;

import java.util.List;
import java.util.Arrays;

import com.hyrcb.hydp.modules.course.domain.ChenCourse;
import com.hyrcb.hydp.modules.course.domain.ChenCourseDTO;
import com.hyrcb.hydp.modules.course.service.IChenCourseService;
import com.hyrcb.hydp.modules.fxq.domain.ChenKaoshiDTO;
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
import com.hyrcb.hydp.modules.fxq.domain.ChenKaoshi;
import com.hyrcb.hydp.modules.fxq.service.IChenKaoshiService;
import com.ruoyi.framework.web.controller.BaseController;
import com.ruoyi.framework.web.domain.AjaxResult;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.framework.web.page.TableDataInfo;

/**
 * 考试安排Controller
 *
 * @author shenzulun
 * @date 2021-02-12
 */

@RestController
@RequestMapping("/fxq/kaoshi" )
public class ChenKaoshiController extends BaseController {

    @Autowired
    private IChenKaoshiService iChenKaoshiService;
    @Autowired
    private IChenCourseService iChenCourseService;
    /**
     * 查询考试安排列表
     */
//    @PreAuthorize("@ss.hasPermi('fxq:kaoshi:list')")
    @GetMapping("/list")
    public TableDataInfo list(ChenKaoshi chenKaoshi){
        startPage();
        List<ChenKaoshiDTO> list = iChenKaoshiService.selectChenKaoshiList(chenKaoshi);
        return getDataTable(list);
    }

    /**
     * 导出考试安排列表
     */
//    @PreAuthorize("@ss.hasPermi('fxq:kaoshi:export')" )
    @Log(title = "考试安排" , businessType = BusinessType.EXPORT)
    @GetMapping("/export" )
    public AjaxResult export(ChenKaoshi chenKaoshi) {
        List<ChenKaoshiDTO> list = iChenKaoshiService.selectChenKaoshiList(chenKaoshi);
        ExcelUtil<ChenKaoshiDTO> util = new ExcelUtil<ChenKaoshiDTO>(ChenKaoshiDTO. class);
        return util.exportExcel(list, "kaoshi" );
    }

    /**
     * 获取考试安排详细信息
     */
//    @PreAuthorize("@ss.hasPermi('fxq:kaoshi:query')" )
    @GetMapping(value = "/{id}" )
    public AjaxResult getInfo(@PathVariable("id" ) Long id) {
        return AjaxResult.success(iChenKaoshiService.getById(id));
    }

    /**
     * 新增考试安排
     */
//    @PreAuthorize("@ss.hasPermi('fxq:kaoshi:add')" )
    @Log(title = "考试安排" , businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody ChenKaoshi chenKaoshi) {
        return toAjax(iChenKaoshiService.save(chenKaoshi) ? 1 : 0);
    }

    /**
     * 修改考试安排
     */
//    @PreAuthorize("@ss.hasPermi('fxq:kaoshi:edit')" )
    @Log(title = "考试安排" , businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody ChenKaoshi chenKaoshi) {
        return toAjax(iChenKaoshiService.updateById(chenKaoshi) ? 1 : 0);
    }

    /**
     * 删除考试安排
     */
//    @PreAuthorize("@ss.hasPermi('fxq:kaoshi:remove')" )
    @Log(title = "考试安排" , businessType = BusinessType.DELETE)
    @DeleteMapping("/{ids}" )
    public AjaxResult remove(@PathVariable Long[] ids) {
        return toAjax(iChenKaoshiService.removeByIds(Arrays.asList(ids)) ? 1 : 0);
    }

    @GetMapping("/getCourseList")
    public TableDataInfo list(ChenCourse chenCourse){
        List<ChenCourseDTO> list = iChenCourseService.selectChenCourseList(chenCourse);
        return getDataTable(list);
    }
}
