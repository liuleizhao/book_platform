package com.cs.book.service.impl;

import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cs.book.dao.StationDao;
import com.cs.book.entity.Station;
import com.cs.book.service.StationService;
import com.cs.common.entityenum.CommonStateEnum;
import com.cs.common.entityenum.DistrictEnum;
import com.cs.mvc.dao.BaseDao;
import com.cs.mvc.dao.SqlCondition;
import com.cs.mvc.service.BaseServiceSupport;

@Service
@Transactional
public class StationServiceImpl extends
		BaseServiceSupport<Station, String> implements StationService {

	@Autowired
	private StationDao stationDao;

	@Override
	protected BaseDao<Station, String> getBaseDao() throws Exception {
		return stationDao;
	}

	@Override
	public List<Station> findByDistrict(DistrictEnum district) throws Exception {
		SqlCondition sqlCondition = new SqlCondition();
		sqlCondition.addSingleNotNullCriterion("DISTRICT = ", district);
		sqlCondition.addAscOrderbyColumn("ORDER_NUM");
		List<Station> stationList = stationDao.findByCondition(sqlCondition);
		return stationList;
	}

	@Override
	public List<Station> findbyState(CommonStateEnum state) throws Exception {
		SqlCondition sqlCondition = new SqlCondition();
		sqlCondition.addSingleNotNullCriterion("STATE = ", state);
		sqlCondition.addAscOrderbyColumn("ORDER_NUM");
		List<Station> stationList = stationDao.findByCondition(sqlCondition);
		return stationList;
	}

	@Override
	public Station findByCode(String code) throws Exception {
		SqlCondition sqlCondition = new SqlCondition();
		sqlCondition.addSingleNotNullCriterion("CODE = ", code);
		List<Station> list = this.findByCondition(sqlCondition);
		if(CollectionUtils.isNotEmpty(list)){
			return list.get(0);
		}
		return null;
	}

	@Override
	public Station findByName(String name) throws Exception {
		SqlCondition sqlCondition = new SqlCondition();
		sqlCondition.addSingleNotNullCriterion("NAME = ", name);
		List<Station> list = this.findByCondition(sqlCondition);
		if(CollectionUtils.isNotEmpty(list)){
			return list.get(0);
		}
		return null;
	}
}
