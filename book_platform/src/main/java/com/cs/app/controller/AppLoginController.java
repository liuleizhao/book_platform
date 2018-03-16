package com.cs.app.controller;

import java.util.ArrayList;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.cs.app.service.impl.WxServiceImpl;
import com.cs.book.entity.Station;
import com.cs.book.entity.WxUser;
import com.cs.book.service.StationService;
import com.cs.book.service.WxUserService;
import com.cs.common.annotation.AuthValidate;
import com.cs.common.constant.Constant;
import com.cs.common.entityenum.SexEnum;
import com.cs.common.utils.CacheUtil;
import com.cs.common.utils.IpUtil;
import com.cs.mvc.init.InitData;
import com.cs.mvc.web.BaseController;
import com.cs.system.service.UserService;
import com.soecode.wxtools.api.WxConsts;
import com.soecode.wxtools.bean.WxJsapiConfig;
import com.soecode.wxtools.bean.WxUserList.WxUser.WxUserGet;
import com.soecode.wxtools.bean.result.WxOAuth2AccessTokenResult;

/**
 * 手机端登录控制类
 * @author LLZ
 *
 */
@Controller
@RequestMapping(value = "/app")
public class AppLoginController extends BaseController{
	
	@Autowired
	WxServiceImpl WxServiceImpl;
	
	@Autowired
	UserService userService;
	
	@Autowired
	WxUserService wxUserService;
	
	@Autowired
	StationService stationService;
	
	
	/**
	 * 
	 * @param request
	 * @param model
	 * @param state 检查站code
	 * @return
	 * @throws Exception
	 */
	@AuthValidate(exceptAuth = true)
	@RequestMapping(value="/login",method = RequestMethod.GET)
	public String login(HttpServletRequest request,Model model,String state) throws Exception{
		
		if(state == null){
			request.setAttribute("message", "对不起，该检测在暂未开通公众号预约服务");
			return forward("/error/403.jsp");
		}
		Station station = stationService.findByCode(state);
		if(station == null){
			request.setAttribute("message", "对不起，该检测站暂未开通公众号预约服务");
			return forward("/error/403.jsp");
		}
		
		CacheUtil.setCacheObject(Constant.APP_STATION_CACHE,station,request);

		String url = WxServiceImpl.oauth2buildAuthorizationUrl(station.getId(),"http://www.carruyi.com/book/app/oauth", WxConsts.OAUTH2_SCOPE_USER_INFO,state);
		return redirect(url);
		
	}
	
	
	/**
	 * 
	 * @param request
	 * @param model
	 * @param code 微信授权 code
	 * @param state 检查站 code
	 * @return
	 * @throws Exception
	 */
	@AuthValidate(exceptAuth = true)
	@RequestMapping(value="/oauth",method = RequestMethod.GET)
	public String oauth(HttpServletRequest request,Model model,String code,String state) throws Exception{
		if(code == null || state == null){
			request.setAttribute("message", "对不起，该检测站暂未开通公众号预约服务");
			return forward("/error/403.jsp");
		}
		
		Station station = (Station) CacheUtil.getCacheObject(Constant.APP_STATION_CACHE,request);
		if(station == null){
			request.setAttribute("message", "对不起，该检测站暂未开通公众号预约服务");
			return forward("/error/403.jsp");
		}
		
		WxOAuth2AccessTokenResult result = WxServiceImpl.oauth2ToGetAccessToken(station.getId(),code);
		if(result == null){
			request.setAttribute("message", "对不起，请从微信公众号菜单栏进入!");
			return forward("/error/403.jsp");
		}
		
		WxUserGet userGet = new WxUserGet();
		userGet.setOpenid(result.getOpenid());
		userGet.setLang(WxConsts.LANG_CHINA);
		com.soecode.wxtools.bean.WxUserList.WxUser wxuser =WxServiceImpl.oauth2ToGetUserInfo(result.getAccess_token(), userGet);
		String ip = IpUtil.getClientIp(request);

		String openId = wxuser.getOpenid();
		WxUser wxUser = wxUserService.findByOpenId(openId);
		if(wxUser==null){
			wxUser = new WxUser();
			wxUser.setOpenId(wxuser.getOpenid());
			wxUser.setSex(SexEnum.findByIndex(wxuser.getSex()));
			wxUser.setNickName(wxuser.getNickname());
			wxUser.setLoginCount(0);
			wxUser.setCreateDate(new Date());
			wxUser.setStationId(station.getId());
			wxUserService.insert(wxUser);
		}
		wxUser.setLastLoginDate(new Date());
		wxUser.setLastLoginIp(ip);
		wxUser.setLoginCount(wxUser.getLoginCount()+1);
		wxUserService.updateByPrimaryKey(wxUser);
		
        CacheUtil.setCacheObject(Constant.APP_USER_CACHE, wxUser, request);
        CacheUtil.setCacheObject(Constant.APP_STATION_CACHE, station, request);
        
		return redirect("/app/index");
	}
	
	@RequestMapping(value="/index",method = RequestMethod.GET)
	public String index(HttpServletRequest request,Model model) throws Exception{
		Station station = (Station) CacheUtil.getCacheObject(Constant.APP_STATION_CACHE,request);
		model.addAttribute("station", station);
		
		String url = "http://www.carruyi.com/book/app/index";
		ArrayList<String> list = new ArrayList<String>();
		list.add("openLocation");
		WxJsapiConfig wxJsapiConfig = WxServiceImpl.createJsapiConfig(station.getId(),url,list);
		
		wxJsapiConfig.setAppid(InitData.getWxConfig(station.getId()).getAppId());
		model.addAttribute("wxJsapiConfig", wxJsapiConfig);
		return "app/index";
	}
}
