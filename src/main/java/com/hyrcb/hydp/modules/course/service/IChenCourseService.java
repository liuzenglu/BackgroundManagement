package com.hyrcb.hydp.modules.course.service;

import com.hyrcb.hydp.modules.course.domain.ChenCourse;
import com.baomidou.mybatisplus.extension.service.IService;
import com.hyrcb.hydp.modules.course.domain.ChenCourseDTO;

import java.util.List;

/**
 * 课程管理Service接口
 *
 * @author shenzulun
 * @date 2021-02-12
 */
public interface IChenCourseService extends IService<ChenCourse> {

   public List<ChenCourseDTO> selectChenCourseList(ChenCourse chenCourse);
}
