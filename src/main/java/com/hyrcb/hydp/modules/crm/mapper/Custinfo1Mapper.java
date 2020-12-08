package com.hyrcb.hydp.modules.crm.mapper;

import com.hyrcb.hydp.modules.crm.domain.Custinfo1;

import java.util.List;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;

/**
 * 客户信息查询Mapper接口
 *
 * @author shenzulun
 * @date 2020-05-20
 */
public interface Custinfo1Mapper extends BaseMapper<Custinfo1> {
	
	public List<Custinfo1> selectCustInfoList();

}
