package com.cs.book.service;

import java.util.List;

import com.cs.book.entity.BookInfo;
import com.cs.mvc.service.BaseService;

public interface BookInfoService extends BaseService<BookInfo, String>{
	
	public List<BookInfo> findbyUserId(String userId) throws Exception;
	
	
	public Integer cancel(String bookInfoId) throws Exception;
}
