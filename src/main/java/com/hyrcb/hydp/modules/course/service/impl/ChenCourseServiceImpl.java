package com.hyrcb.hydp.modules.course.service.impl;

import com.hyrcb.hydp.modules.course.domain.ChenCourseDTO;
import com.ruoyi.project.system.mapper.SysUserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.hyrcb.hydp.modules.course.mapper.ChenCourseMapper;
import com.hyrcb.hydp.modules.course.domain.ChenCourse;
import com.hyrcb.hydp.modules.course.service.IChenCourseService;

import java.util.List;

/**
 * 课程管理Service业务层处理
 *
 * @author shenzulun
 * @date 2021-02-12
 */
@Service
public class ChenCourseServiceImpl extends ServiceImpl<ChenCourseMapper, ChenCourse> implements IChenCourseService {
    @Autowired
    private ChenCourseMapper chenCourseMapper;

    public List<ChenCourseDTO> selectChenCourseList(ChenCourse chenCourse){
        return chenCourseMapper.selectChenCourseList(chenCourse);
    }
}
