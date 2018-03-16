package com.cs.book.service;


import com.cs.book.entity.WxUser;
import com.cs.mvc.service.BaseService;

public interface WxUserService  extends BaseService<WxUser, String>{
	
    
    /**
     * 
     * @param account
     * @return
     * @throws Exception
     */
    public WxUser findByOpenId(String openId) throws Exception;
}
