package com.hyrcb.hydp.modules.fxq.service;

import com.hyrcb.hydp.modules.fxq.domain.ChenKaoshi;
import com.baomidou.mybatisplus.extension.service.IService;
import com.hyrcb.hydp.modules.fxq.domain.ChenKaoshiDTO;

import java.util.List;


/**
 * 考试安排Service接口
 *
 * @author shenzulun
 * @date 2021-02-12
 */
public interface IChenKaoshiService extends IService<ChenKaoshi> {
    public List<ChenKaoshiDTO> selectChenKaoshiList(ChenKaoshi chenKaoshi);
}
