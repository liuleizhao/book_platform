package com.cs.common.utils.webservice;

import java.net.URL;
import java.net.URLDecoder;
import java.util.HashMap;
import java.util.Map;

import javax.xml.namespace.QName;
import javax.xml.rpc.ParameterMode;
import javax.xml.rpc.encoding.XMLType;

import org.apache.axis.client.Call;
import org.apache.axis.client.Service;

import com.cs.common.utils.CommonAESUtil;

/**
 * WebService请求封装类
 * @author
 * 
 */
public class WebServiceUtils {
	
	public static String namespace="http://tempuri1.org/";
	
	public static String sendRequest(String url,String jkId,String account,String password,String src) throws Exception {
		
		String responseXml = null;
		try {
			Service service = new Service();
			Call call = (Call) service.createCall();
			call.setTargetEndpointAddress(new URL(url));
			call.setUseSOAPAction(true);
			call.setSOAPActionURI(namespace + "uniformAccess");
			call.setOperationName(new QName(namespace, "uniformAccess"));// 访问地址
			
			call.addParameter(new QName(namespace, "jkId"), XMLType.XSD_STRING, ParameterMode.IN);
			call.addParameter(new QName(namespace, "account"), XMLType.XSD_STRING, ParameterMode.IN);
			call.addParameter(new QName(namespace, "password"), XMLType.XSD_STRING, ParameterMode.IN);
			call.addParameter(new QName(namespace, "src"), XMLType.XSD_STRING, ParameterMode.IN);
			
			call.setReturnType(XMLType.XSD_STRING);// 返回值类型
			call.setTimeout(2000000); // 20秒调用时间
			responseXml = (String) call.invoke(new Object[] {jkId,account,password,src});
		} catch (Exception e) {
			e.printStackTrace();
		}
		return URLDecoder.decode(responseXml,"UTF-8");
	}
	
	public static  String buildRequestXml(String fatherNode,Map<String,String> param){
		StringBuffer xmlDoc = new StringBuffer();
		xmlDoc.append("<?xml version=\"1.0\" encoding=\"UTF-8\"?><root>");
		if(fatherNode!=null){
			xmlDoc.append("<"+fatherNode+">");
		}
		for (Map.Entry<String, String> entry : param.entrySet()) {
			String key = entry.getKey();
			Object value = entry.getValue();
			xmlDoc.append("<"+key+">"+value+"</"+key+">");
		  }
		if(fatherNode!=null){
			xmlDoc.append("</"+fatherNode+">");
		}
		xmlDoc.append("</root>");
		System.out.println(xmlDoc.toString());
		return xmlDoc.toString();
	}
	public static void main(String[] args) {
	   	 String fatherNode = "bookInfo" ;
			Map<String,Object> map = new HashMap<String, Object>();
			map.put("mobile", "17665202833");
			map.put("stationId", "5CEA951EE4B36510E0536B01A8C0B326");
			map.put("vehicleType", "1");
			map.put("vehicleingCharacter", "XXZK");
			map.put("carTypeId", "312AED23657445C194540C794DBDBDB9");
			map.put("driverType", "0");
			map.put("bookDate", "2016-01-04");
			String src = null;
			try {
				src = CommonAESUtil.encryptAES("<?xml version=\"1.0\" encoding=\"UTF-8\"?><root><bookInfo><mobile>17665202833</mobile><stationId>5CEA951EE4B36510E0536B01A8C0B326</stationId><bookDate>2018-03-13</bookDate><vehicleingCharacter>1</vehicleingCharacter><carTypeId>312AED23657445C194540C794DBDBDB9</carTypeId><vehicleType>1</vehicleType><driverType>0</driverType></bookInfo></root>");
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			//获取webservice 请求配置
			String account = "admintest";
			String password = "3691308f2a4c2f6983f2880d32e29c86";
			String url = "http://app.stc.gov.cn:8092/mot/services/bookService?wsdl";
			
			try {
				String r = sendRequest(url,"JK04",account,password,src);
				System.out.println(r);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
 	
}
