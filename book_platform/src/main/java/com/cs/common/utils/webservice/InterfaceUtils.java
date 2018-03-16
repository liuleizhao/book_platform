package com.cs.common.utils.webservice;

import java.util.HashMap;
import java.util.Map;

import com.cs.book.entity.BookInfo;
import com.cs.common.entityenum.InterfaceEnum;
import com.cs.common.utils.CommonAESUtil;
import com.cs.mvc.init.InitData;



/**
 * WebService请求封装类
 * @author
 * 
 */
public class InterfaceUtils {
	public static String callInterface(InterfaceEnum interfaceEnum,BookInfo bookInfo){
		String interfaceName = interfaceEnum.name();
		String fatherNode = interfaceEnum.getFatherNode();
		
		Map<String,String> map = new HashMap<String, String>();
		if(InterfaceEnum.JK03.name().equals(interfaceName)){
			map.put("mobile", "18888888888");
			map.put("stationId", bookInfo.getStationId());
			map.put("vehicleType", bookInfo.getVehicleType());
			map.put("vehicleingCharacter", bookInfo.getVehicleCharacter());
			map.put("carTypeId", bookInfo.getCarTypeId());
			map.put("driverType", bookInfo.getDriverType());
		}
		
		if(InterfaceEnum.JK04.name().equals(interfaceName)){
			map.put("mobile", "18888888888");
			map.put("stationId", bookInfo.getStationId());
			map.put("vehicleType", bookInfo.getVehicleType());
			map.put("vehicleingCharacter", bookInfo.getVehicleCharacter());
			map.put("carTypeId", bookInfo.getCarTypeId());
			map.put("driverType", bookInfo.getDriverType());
			map.put("bookDate", bookInfo.getBookDate());
		}
		
		if(InterfaceEnum.JK05.name().equals(interfaceName)){
			map.put("mobile", bookInfo.getMobile());
			map.put("stationId", bookInfo.getStationId());
			map.put("vehicleType", bookInfo.getVehicleType());
			map.put("vehicleingCharacter", bookInfo.getVehicleCharacter());
			map.put("carTypeId", bookInfo.getCarTypeId());
			map.put("driverType", bookInfo.getDriverType());
			map.put("frameNumber", bookInfo.getFrameNumber());
			
			
			map.put("bookerName", bookInfo.getBookerName());
			map.put("idNumber", bookInfo.getIdNumber());
			
			map.put("platNumber", bookInfo.getPlatNumber());
			map.put("newflag", bookInfo.getNewflag());
			
			map.put("idTypeId", bookInfo.getIdTypeId());
			map.put("fuelType", bookInfo.getFuelType());
			map.put("bookDate", bookInfo.getBookDate());
			map.put("bookTime", bookInfo.getBookTime());
			map.put("bookingChannel", String.valueOf(bookInfo.getBookChannel()));
		}
		if(InterfaceEnum.JK06.name().equals(interfaceName)){
			map.put("mobile", bookInfo.getMobile());
			map.put("bookerName", bookInfo.getBookerName());
			map.put("idNumber", bookInfo.getIdNumber());
			map.put("platNumber", bookInfo.getPlatNumber());
		}
		
		if(InterfaceEnum.JK07.name().equals(interfaceName)){
			map.put("mobile", bookInfo.getMobile());
			map.put("bookNumber", bookInfo.getBookerName());
			map.put("verifyCode", bookInfo.getVerifyCode());
		}

		String src = null;
		try {
			src = CommonAESUtil.encryptAES(WebServiceUtils.buildRequestXml(fatherNode,map));
		} catch (Exception e1) {
			e1.printStackTrace();
		}
		
		String url = InitData.getGlobalValue("INTERFACE_URL");
		String account = InitData.getGlobalValue("INTERFACE_ACCOUNT");
		String password = InitData.getGlobalValue("INTERFACE_PASSWORD");
		
		String date= null;
		try {
			date = WebServiceUtils.sendRequest(url,interfaceName,account,password,src);
		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println(date);
		return date;
	}
}
