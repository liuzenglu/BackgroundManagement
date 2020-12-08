package com.hyrcb.hydp.modules.crm.service;

import com.hyrcb.hydp.modules.crm.domain.Custinfo1;

import java.util.List;

import com.baomidou.mybatisplus.extension.service.IService;

/**
 * 客户信息查询Service接口
 *
 * @author shenzulun
 * @date 2020-05-20
 */
public interface ICustinfo1Service extends IService<Custinfo1> {
	
	public List<Custinfo1> selectCustInfoList();

}
