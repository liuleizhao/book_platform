package com.cs.book.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cs.book.dao.BookInfoDao;
import com.cs.book.entity.BookInfo;
import com.cs.book.service.BookInfoService;
import com.cs.common.entityenum.BookStateEnum;
import com.cs.mvc.dao.BaseDao;
import com.cs.mvc.dao.SqlCondition;
import com.cs.mvc.service.BaseServiceSupport;

@Service
@Transactional
public class BookInfoServiceImpl extends BaseServiceSupport<BookInfo, String>  implements BookInfoService{

	
	@Autowired
	private BookInfoDao bookInfoDao;
	
	@Override
	protected BaseDao<BookInfo, String> getBaseDao() throws Exception {
		return bookInfoDao;
	}

	@Override
	public List<BookInfo> findbyUserId(String userId) throws Exception {
		SqlCondition sqlCondition = new SqlCondition();
		sqlCondition.addSingleNotNullCriterion("USER_ID =", userId);
		List<BookInfo> bookInfoList = bookInfoDao.findByCondition(sqlCondition);
		return bookInfoList;
	}

	@Override
	public Integer cancel(String bookInfoId) throws Exception {
		BookInfo bookInfo = new BookInfo();
		bookInfo.setId(bookInfoId);
		bookInfo.setBookState(BookStateEnum.YYQX);
		return bookInfoDao.updateByPrimaryKeySelective(bookInfo);
	}
	
}
