package com.hyrcb.hydp.modules.fxq.service.impl;

import com.hyrcb.hydp.modules.course.mapper.ChenCourseMapper;
import com.hyrcb.hydp.modules.fxq.domain.ChenKaoshiDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.hyrcb.hydp.modules.fxq.mapper.ChenKaoshiMapper;
import com.hyrcb.hydp.modules.fxq.domain.ChenKaoshi;
import com.hyrcb.hydp.modules.fxq.service.IChenKaoshiService;

import java.util.List;

/**
 * 考试安排Service业务层处理
 *
 * @author shenzulun
 * @date 2021-02-12
 */
@Service
public class ChenKaoshiServiceImpl extends ServiceImpl<ChenKaoshiMapper, ChenKaoshi> implements IChenKaoshiService {
    @Autowired
    private ChenKaoshiMapper chenKaoshiMapper;
    public List<ChenKaoshiDTO> selectChenKaoshiList(ChenKaoshi chenKaoshi){
        return chenKaoshiMapper.selectChenKaoshiList(chenKaoshi);
    }
}
