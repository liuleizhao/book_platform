package com.cs.mvc.init;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Service;

import com.cs.app.vo.WxConfig;
import com.cs.book.entity.Station;
import com.cs.book.service.StationService;
import com.cs.system.entity.GlobalConfig;
import com.cs.system.service.GlobalConfigService;

/**
 * 初始化数据Service，将常用数据缓存到内存中
 */
@Service
public class InitData implements ApplicationListener<ContextRefreshedEvent> {
	
	//全局参数
	private static final Map<String,String> global = new HashMap<String,String>();
	//微信配置
	private static final Map<String,WxConfig> wxConfig = new HashMap<String,WxConfig>();
	
	
	@Autowired
	private GlobalConfigService globalConfigService;
	
	@Autowired
	private StationService stationService;
	
	@Override
	public void onApplicationEvent(ContextRefreshedEvent event) {
		if (event.getApplicationContext().getParent() == null) {
			try {
				//全局配置
				reloadGlobalConfig();
				//加载每个检测站并生成WxConfig
				reloadWxConfig();
				System.out.println("----------------------初始化数据成功----------------------");
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
	
	/**
	 * 重新加载全局配置
	 * @throws Exception
	 */
	public void reloadGlobalConfig() throws Exception{
		System.out.println("----------------------加载全局配置----------------------");
		List<GlobalConfig> list = globalConfigService.findAllData();
		if (list != null) {

			Map<String, String> newConfigMap = new HashMap<String, String>();
			for (GlobalConfig global : list) {
				newConfigMap.put(global.getDataKey(), global.getDataValue());
			}
			global.putAll(newConfigMap);
		}
	}
	
	/**
	 * 根据参数Key获取参数值
	 * @param dataKey
	 * @return
	 */
	public static String getGlobalValue(String dataKey){
		return global.get(dataKey);
	}
	
	/**
	 * 加载微信配置
	 * @throws Exception
	 */
	public void reloadWxConfig() throws Exception{
		System.out.println("----------------------加载WxConfig----------------------");
		List<Station> list = stationService.findAllData();
		if (list != null) {

			Map<String, WxConfig> newWxConfigMap = new HashMap<String, WxConfig>();
			for (Station station : list) {
				WxConfig wxConfig = new WxConfig();
				wxConfig.setAppId(station.getAppId());
				wxConfig.setAppSecret(station.getAppSecret());
				wxConfig.setToken(station.getToken());
				wxConfig.setAesKey(station.getAesKey());
				wxConfig.setMchId(station.getMchId());
				newWxConfigMap.put(station.getId(), wxConfig);
			}
			
			wxConfig.putAll(newWxConfigMap);
		}
		
	}
	
	public static WxConfig getWxConfig(String stationId){
		return wxConfig.get(stationId);
	}
}
