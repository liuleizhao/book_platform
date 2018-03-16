package com.cs.book.service;

import java.util.List;

import com.cs.book.entity.CarInfo;
import com.cs.mvc.service.BaseService;

public interface CarInfoService extends BaseService<CarInfo, String>{
	
	public List<CarInfo> findbyUserId(String userId) throws Exception;
	
}
