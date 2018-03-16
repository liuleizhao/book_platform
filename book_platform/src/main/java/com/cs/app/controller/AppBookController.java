package com.cs.app.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cs.book.entity.BookInfo;
import com.cs.book.entity.CarInfo;
import com.cs.book.entity.Station;
import com.cs.book.entity.WxUser;
import com.cs.book.service.BookInfoService;
import com.cs.book.service.CarInfoService;
import com.cs.common.constant.Constant;
import com.cs.common.entityenum.BookStateEnum;
import com.cs.common.entityenum.InterfaceCodeEnum;
import com.cs.common.entityenum.InterfaceEnum;
import com.cs.common.utils.CacheUtil;
import com.cs.common.utils.XmlHelper;
import com.cs.common.utils.json.JSONArray;
import com.cs.common.utils.json.JSONObject;
import com.cs.common.utils.json.XML;
import com.cs.common.utils.webservice.InterfaceUtils;
import com.cs.mvc.web.BaseController;

/**
 * 手机端预约业务控制类
 * @author LLZ
 *
 */
@Controller
@RequestMapping(value = "/app/book")
public class AppBookController extends BaseController{
	
	@Autowired
	private CarInfoService carInfoService;
	
	@Autowired
	private BookInfoService bookInfoService;
	
	
	@RequestMapping(value="/chancel",method = RequestMethod.GET)
	public String login(HttpServletRequest request){
		return "app/own_book_chancel";
	}
	
	@RequestMapping(value="/car",method = RequestMethod.GET)
	public String choose(HttpServletRequest request,Model model,String carInfoId) throws Exception{
		WxUser wxuser = getAppUser(request);
		List<CarInfo> carInfoList= carInfoService.findbyUserId(wxuser.getId());
		model.addAttribute("carInfoList", carInfoList);
		return "app/own_book_car";
	}
	
	@RequestMapping(value="/input",method = RequestMethod.GET)
	public String entrust(HttpServletRequest request,Model model,String carInfoId) throws Exception{
		CarInfo carInfo = carInfoService.selectByPrimaryKey(carInfoId);
		model.addAttribute("carInfo", carInfo);
		return "app/own_book_input";
	}
	
	@ResponseBody
	@RequestMapping(value="/getDate",method = RequestMethod.POST)
	public String getBookDate(HttpServletRequest request,Model model,BookInfo bookInfo) throws Exception{
		
		Station station = (Station) CacheUtil.getCacheObject(Constant.APP_STATION_CACHE,request);
		bookInfo.setStationId(station.getId());
		
		String dateXml = InterfaceUtils.callInterface(InterfaceEnum.JK03,bookInfo);
		JSONObject dataJSONObj = XML.toJSONObject(dateXml);
		return dataJSONObj.getJSONObject("ResponseMessage").toString();
	}
	
	@ResponseBody
	@RequestMapping(value="/getTime",method = RequestMethod.POST)
	public String getBookTime(HttpServletRequest request,Model model,BookInfo bookInfo) throws Exception{
		
		Station station = (Station) CacheUtil.getCacheObject(Constant.APP_STATION_CACHE,request);
		bookInfo.setStationId(station.getId());
		
		String timeXml = InterfaceUtils.callInterface(InterfaceEnum.JK04,bookInfo);
		JSONObject timeJSONObj = XML.toJSONObject(timeXml);
		JSONArray timeJSONArray = timeJSONObj.getJSONObject("ResponseMessage").getJSONObject("result").getJSONArray("AppTimeHelper");
		for (int i = 0;i<timeJSONArray.length();i++){
			JSONObject j = timeJSONArray.getJSONObject(i);
					j.put("text", j.getString("apptime") + "   预约量:"+j.getInt("yetnumber") +"/"+ j.getInt("maxnumber"));
		}
		return timeJSONObj.getJSONObject("ResponseMessage").toString();
		
	}
	
	@RequestMapping(value="/submit",method = RequestMethod.POST)
	public String writeBookInfo(HttpServletRequest request,Model model,BookInfo bookInfo,String carInfoId) throws Exception{
		
		Station station = (Station) CacheUtil.getCacheObject(Constant.APP_STATION_CACHE,request);
		bookInfo.setStationId(station.getId());
		bookInfo.setStationName(station.getName());
		
		WxUser wxuser = getAppUser(request);
		bookInfo.setUserId(wxuser.getId());
		
		String appointXml = InterfaceUtils.callInterface(InterfaceEnum.JK05,bookInfo);
		/*JSONObject appointJSONObj = XML.toJSONObject(appointXml);*/
		
		if(!InterfaceCodeEnum.SUCCEED.getId().equals(XmlHelper.getValue("code", appointXml))){
			CarInfo carInfo = carInfoService.selectByPrimaryKey(carInfoId);
			model.addAttribute("carInfo", carInfo);
			model.addAttribute("message", XmlHelper.getValue("result", appointXml));
			return "app/own_book_input";
		}
		
		bookInfo.setBookNumber(XmlHelper.getValue("result", appointXml));
		bookInfo.setVerifyCode(RegexMatches(XmlHelper.getValue("msg", appointXml)));
		bookInfo.setBookState(BookStateEnum.YYZ);
		
		int count = bookInfoService.insert(bookInfo);
		if(count<=0){
			CarInfo carInfo = carInfoService.selectByPrimaryKey(carInfoId);
			model.addAttribute("carInfo", carInfo);
			model.addAttribute("message", "预约失败");
			return "app/own_book_input";
		}
		
		model.addAttribute("station", station);
		model.addAttribute("bookInfo", bookInfo);
		return "app/book_ok";
	}
	
	
	private static String RegexMatches(String msg){
		String pattern = "预约验证码为：\\d{6}";
		Pattern r = Pattern.compile(pattern);
		Matcher m = r.matcher(msg);
		if(m.find()){
			Matcher m1 = Pattern.compile("\\d{6}").matcher(m.group());
			if(m1.find()){
				return m1.group();
			}
		}
		return "";
	}
	
	public static void main(String args[]) {
		String str = "预约成功：您的预约号为：171222108943,预约验证码为：452001,请妥善保管。您的预约名字为：庞xx";
		String pattern = "预约验证码为：\\d{6}";

		Pattern r = Pattern.compile(pattern);
		Matcher m = r.matcher(str);
		System.out.println(m.matches());
	}
}
