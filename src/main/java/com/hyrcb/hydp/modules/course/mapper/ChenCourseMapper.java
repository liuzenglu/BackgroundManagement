package com.hyrcb.hydp.modules.course.mapper;

import com.hyrcb.hydp.modules.course.domain.ChenCourse;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.hyrcb.hydp.modules.course.domain.ChenCourseDTO;

import java.util.List;

/**
 * 课程管理Mapper接口
 *
 * @author shenzulun
 * @date 2021-02-12
 */
public interface ChenCourseMapper extends BaseMapper<ChenCourse> {

    public List<ChenCourseDTO> selectChenCourseList(ChenCourse chenCourse);
}
