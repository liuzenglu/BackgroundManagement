package com.hyrcb.hydp.modules.crm.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.hyrcb.hydp.modules.crm.mapper.Custinfo1Mapper;
import com.hyrcb.hydp.modules.crm.domain.Custinfo1;
import com.hyrcb.hydp.modules.crm.service.ICustinfo1Service;

/**
 * 客户信息查询Service业务层处理
 *
 * @author shenzulun
 * @date 2020-05-20
 */
@Service
public class Custinfo1ServiceImpl extends ServiceImpl<Custinfo1Mapper, Custinfo1> implements ICustinfo1Service {
	
	@Autowired
    private Custinfo1Mapper custinfo1Mapper;

	@Override
	public List<Custinfo1> selectCustInfoList() {
		return custinfo1Mapper.selectCustInfoList();
	}

}
