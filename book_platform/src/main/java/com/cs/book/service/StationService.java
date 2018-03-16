package com.cs.book.service;


import java.util.List;

import com.cs.book.entity.Station;
import com.cs.common.entityenum.CommonStateEnum;
import com.cs.common.entityenum.DistrictEnum;
import com.cs.mvc.service.BaseService;


public interface StationService  extends BaseService<Station, String>{
	
	public List<Station> findByDistrict(DistrictEnum district)  throws Exception;
	
	public List<Station> findbyState(CommonStateEnum state) throws Exception;
	
	public Station findByCode(String code) throws Exception;
	
	public Station findByName(String name) throws Exception;
	
}
