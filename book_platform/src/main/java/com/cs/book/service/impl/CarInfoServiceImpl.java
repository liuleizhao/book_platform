package com.cs.book.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cs.book.dao.CarInfoDao;
import com.cs.book.entity.CarInfo;
import com.cs.book.service.CarInfoService;
import com.cs.mvc.dao.BaseDao;
import com.cs.mvc.dao.SqlCondition;
import com.cs.mvc.service.BaseServiceSupport;

@Service
@Transactional
public class CarInfoServiceImpl extends BaseServiceSupport<CarInfo, String>  implements CarInfoService{

	
	@Autowired
	private CarInfoDao carInfoDao;
	
	@Override
	protected BaseDao<CarInfo, String> getBaseDao() throws Exception {
		return carInfoDao;
	}

	@Override
	public List<CarInfo> findbyUserId(String userId) throws Exception {
		SqlCondition sqlCondition = new SqlCondition();
		sqlCondition.addSingleNotNullCriterion("USER_ID =", userId);
		List<CarInfo> carInfoList = carInfoDao.findByCondition(sqlCondition);
		return carInfoList;
	}

}
