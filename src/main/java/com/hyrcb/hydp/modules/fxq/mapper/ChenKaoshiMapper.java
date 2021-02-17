package com.hyrcb.hydp.modules.fxq.mapper;

import com.hyrcb.hydp.modules.fxq.domain.ChenKaoshi;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.hyrcb.hydp.modules.fxq.domain.ChenKaoshiDTO;

import java.util.List;

/**
 * 考试安排Mapper接口
 *
 * @author shenzulun
 * @date 2021-02-12
 */
public interface ChenKaoshiMapper extends BaseMapper<ChenKaoshi> {
    public List<ChenKaoshiDTO> selectChenKaoshiList(ChenKaoshi chenKaoshi);
}
