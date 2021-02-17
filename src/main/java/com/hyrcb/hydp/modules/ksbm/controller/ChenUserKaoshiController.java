package com.hyrcb.hydp.modules.ksbm.controller;

import cn.hutool.core.lang.func.Func;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;

import java.util.*;
import java.util.stream.Collectors;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.hyrcb.hydp.modules.course.domain.ChenCourse;
import com.hyrcb.hydp.modules.course.service.IChenCourseService;
import com.hyrcb.hydp.modules.fxq.domain.ChenKaoshi;
import com.hyrcb.hydp.modules.fxq.domain.ChenKaoshiDTO;
import com.hyrcb.hydp.modules.fxq.service.IChenKaoshiService;
import com.hyrcb.hydp.modules.xuanke.domain.ChenUserCourse;
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
import com.hyrcb.hydp.modules.ksbm.domain.ChenUserKaoshi;
import com.hyrcb.hydp.modules.ksbm.service.IChenUserKaoshiService;
import com.ruoyi.framework.web.controller.BaseController;
import com.ruoyi.framework.web.domain.AjaxResult;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.framework.web.page.TableDataInfo;

/**
 * 考试报名Controller
 *
 * @author liuzenglu
 * @date 2021-02-16
 */

@RestController
@RequestMapping("/ksbm/ksbm" )
public class ChenUserKaoshiController extends BaseController {

    @Autowired
    private IChenUserKaoshiService iChenUserKaoshiService;
    @Autowired
    private IChenKaoshiService iChenKaoshiService;
    @Autowired
    private IChenCourseService iChenCourseService;
    /**
     * 查询考试报名列表
     */
//    @PreAuthorize("@ss.hasPermi('ksbm:ksbm:list')")
    @GetMapping("/list")
    public TableDataInfo list(ChenUserKaoshi chenUserKaoshi){
        startPage();
        LambdaQueryWrapper<ChenUserKaoshi> lqw = new LambdaQueryWrapper<ChenUserKaoshi>();
        if (chenUserKaoshi.getUserId() != null){
            lqw.eq(ChenUserKaoshi::getUserId ,chenUserKaoshi.getUserId());
        }
        if (chenUserKaoshi.getKaoshiId() != null){
            lqw.eq(ChenUserKaoshi::getKaoshiId ,chenUserKaoshi.getKaoshiId());
        }
        List<ChenUserKaoshi> list = iChenUserKaoshiService.list(lqw);
        List<Long> kaoshiIdList =  list.stream().map(ChenUserKaoshi::getKaoshiId).collect(Collectors.toList());
        List<ChenKaoshi> lsit = new ArrayList<>();
        if (StringUtils.isNotEmpty(kaoshiIdList)){
            lsit =  iChenKaoshiService.listByIds(kaoshiIdList);
        }
        List<ChenKaoshiDTO> chenKaoshiDTOList = lsit.parallelStream().map(this::buildChenKaoshiDTO).collect(Collectors.toList());
        for(ChenKaoshiDTO chenKaoshiDTO : chenKaoshiDTOList){
            ChenCourse chenCourse = iChenCourseService.getById(chenKaoshiDTO.getCourseId());
            chenKaoshiDTO.setCourseName(chenCourse.getCourseName());
        }
        return getDataTable(chenKaoshiDTOList);
    }

    /**
     * 导出考试报名列表
     */
//    @PreAuthorize("@ss.hasPermi('ksbm:ksbm:export')" )
    @Log(title = "考试报名" , businessType = BusinessType.EXPORT)
    @GetMapping("/export" )
    public AjaxResult export(ChenUserKaoshi chenUserKaoshi) {
        LambdaQueryWrapper<ChenUserKaoshi> lqw = new LambdaQueryWrapper<ChenUserKaoshi>(chenUserKaoshi);
        List<ChenUserKaoshi> list = iChenUserKaoshiService.list(lqw);
        ExcelUtil<ChenUserKaoshi> util = new ExcelUtil<ChenUserKaoshi>(ChenUserKaoshi. class);
        return util.exportExcel(list, "ksbm" );
    }

    /**
     * 获取考试报名详细信息
     */
//    @PreAuthorize("@ss.hasPermi('ksbm:ksbm:query')" )
    @GetMapping(value = "/{id}" )
    public AjaxResult getInfo(@PathVariable("id" ) Long id) {
        return AjaxResult.success(iChenUserKaoshiService.getById(id));
    }

    /**
     * 新增考试报名
     */
//    @PreAuthorize("@ss.hasPermi('ksbm:ksbm:add')" )
    @Log(title = "考试报名" , businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody ChenUserKaoshi chenUserKaoshi) {
        List<ChenUserKaoshi> list = this.iChenUserKaoshiService.list(Wrappers.<ChenUserKaoshi>lambdaQuery()
                .eq(ChenUserKaoshi::getUserId, chenUserKaoshi.getUserId())
                .eq(ChenUserKaoshi::getKaoshiId, chenUserKaoshi.getKaoshiId()));
        if ( ! StringUtils.isEmpty(list)){
            return  AjaxResult.error(99999,"您已报名，不用重复报名");
        }
        return toAjax(iChenUserKaoshiService.save(chenUserKaoshi) ? 1 : 0);
    }

    /**
     * 修改考试报名
     */
//    @PreAuthorize("@ss.hasPermi('ksbm:ksbm:edit')" )
    @Log(title = "考试报名" , businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody ChenUserKaoshi chenUserKaoshi) {
        return toAjax(iChenUserKaoshiService.updateById(chenUserKaoshi) ? 1 : 0);
    }

    /**
     * 删除考试报名
     */
//    @PreAuthorize("@ss.hasPermi('ksbm:ksbm:remove')" )
    @Log(title = "考试报名" , businessType = BusinessType.DELETE)
    @PostMapping("/shanchu" )
    public AjaxResult remove(@RequestBody ChenUserKaoshi chenUserKaoshi) {
        Map map = new HashMap();
        map.put("kaoshi_id",chenUserKaoshi.getKaoshiId());
        map.put("user_id",chenUserKaoshi.getUserId());
        return toAjax(iChenUserKaoshiService.removeByMap(map) ? 1 : 0);
    }


    protected ChenKaoshiDTO buildChenKaoshiDTO(ChenKaoshi zhenKaoshi)
    {
        ChenKaoshiDTO chenKaoshiDTO = new ChenKaoshiDTO();
        chenKaoshiDTO.setId(zhenKaoshi.getId());
        chenKaoshiDTO.setBegainTime(zhenKaoshi.getBegainTime());
        chenKaoshiDTO.setEndTime(zhenKaoshi.getEndTime());
        chenKaoshiDTO.setCourseId(zhenKaoshi.getCourseId());
        chenKaoshiDTO.setTestAddress(zhenKaoshi.getTestAddress());
        chenKaoshiDTO.setStatus(zhenKaoshi.getStatus());
        return chenKaoshiDTO ;
    }
}
