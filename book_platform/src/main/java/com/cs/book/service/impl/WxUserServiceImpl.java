package com.cs.book.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cs.book.dao.WxUserDao;
import com.cs.book.entity.WxUser;
import com.cs.book.service.WxUserService;
import com.cs.mvc.dao.BaseDao;
import com.cs.mvc.dao.SqlCondition;
import com.cs.mvc.service.BaseServiceSupport;

@Service
@Transactional
public class WxUserServiceImpl extends
		BaseServiceSupport<WxUser, String> implements WxUserService {

	@Autowired
	private WxUserDao WxUserDao;

	@Override
	protected BaseDao<WxUser, String> getBaseDao() throws Exception {
		return WxUserDao;
	}
	@Override
	public WxUser findByOpenId(String openId) throws Exception {
		SqlCondition sqlCondition = new SqlCondition();
		sqlCondition.addSingleNotNullCriterion("OPEN_ID = ", openId);
		List<WxUser> wxUsers = WxUserDao.findByCondition(sqlCondition);
		if(null != wxUsers && wxUsers.size() > 0)
		{
			return wxUsers.get(0);
		}
		return null;
	}
}
