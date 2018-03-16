package com.cs.book.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cs.book.entity.Station;
import com.cs.book.service.StationService;
import com.cs.common.entityenum.CommonStateEnum;
import com.cs.common.entityenum.DistrictEnum;
import com.cs.mvc.dao.SqlCondition;
import com.cs.mvc.init.InitData;
import com.cs.mvc.web.BaseController;
import com.github.pagehelper.PageInfo;

/**
 * @ClassName:    StationInfoController.java
 * @Description:  检测站信息控制器
 * @Author        xuj
 * @date          2016-11-03 下午5:02:25
 */
@Controller
@RequestMapping(value = "/backend/appoint/station")
public class StationController  extends BaseController{
	
	@Autowired
	private StationService stationService;
	
	@Autowired
	private InitData initData;
	
	private PageInfo<Station> pageView;
	
	/**
	 * @throws Exception
	 * @Description: 查询检测站列表
	 */
	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public String list(HttpServletRequest request, Model model,Station station) throws Exception {
		SqlCondition sqlCondition = new SqlCondition();
		if(station != null){
			sqlCondition.addSingleCriterion("code = ", station.getCode());
			sqlCondition.addLikeCriterion("name like ", station.getName());
		}
		sqlCondition.addAscOrderbyColumn("ORDER_NUM");
		pageView = stationService.findByCondition(sqlCondition,getCurrentPage(request),getCurrentPageSize(request));
		
		model.addAttribute("pageView", pageView);
		model.addAttribute("station", station);
		return "backend/appoint/station_list";
	}

	/**
	 * @Description: 跳转到添加页面
	 * @param model
	 * @return String
	 * @throws Exception
	 * @date 2016-10-31 下午12:12:33
	 */
	@RequestMapping(value = "/add", method = RequestMethod.GET)
	public String addUI(HttpServletRequest request,Model model) throws Exception {
		
		List<DistrictEnum> districtList = DistrictEnum.getAll();
		List<CommonStateEnum> stateList = CommonStateEnum.getAll();
		model.addAttribute("districtList", districtList);
		model.addAttribute("stateList", stateList);
		
		return "backend/appoint/station_info";
	}

	/**
	 * @Description: 执行添加的方法
	 */
	@RequestMapping(value = "/add", method = RequestMethod.POST)
	public String add(HttpServletRequest request, Model model, Station station)throws Exception {
		try {
			String code = station.getCode();
			String name = station.getName();
			if(StringUtils.isBlank(code) ||StringUtils.isBlank(name))
			{
				model.addAttribute("message","编码和名称不能为空！");
				return addUI(request, model);
			} 
			
			if(!this.checkCodeExisted(code)){
				model.addAttribute("message","检测站编码已存在！");
				return addUI(request, model);
			}
			if(!this.checkNameExisted(name)){
				model.addAttribute("message","检测站名称已存在！");
				return addUI(request, model);
			}
			int count = stationService.insert(station);
			if(count > 0){
				initData.reloadWxConfig();
				model.addAttribute("message","添加检测站信息【" + station.getName() + "】成功！");
				return list(request, model,station);
			}else{
				model.addAttribute("message","添加检测站信息【" + station.getName() + "】失败！");
				return addUI(request,model);
			}
		} catch (Exception e) {
			e.printStackTrace();
			model.addAttribute("message","添加检测站信息【" + station.getName() + "】失败！");
			return addUI(request,model);
		}
	}

	/**
	 * @Description: 跳转到修改页面
	 */
	@RequestMapping(value = "/edit", method = RequestMethod.GET)
	public String editUI(HttpServletRequest request, Model model,String stationId) throws Exception {
		
		Station station = stationService.selectByPrimaryKey(stationId);
		if(null == station ){
			model.addAttribute("message","修改用户信息失败，未找到对应的用户信息！");
			return list(request,model,null);
		}
		List<DistrictEnum> districtList = DistrictEnum.getAll();
		List<CommonStateEnum> stateList = CommonStateEnum.getAll();
		
		model.addAttribute("station", station);
		model.addAttribute("districtList", districtList);
		model.addAttribute("stateList", stateList);
		
		return "backend/appoint/station_info";
	}

	/**
	 * @Description: 执行修改的方法
	 */
	@RequestMapping(value = "/edit", method = RequestMethod.POST)
	public String edit(HttpServletRequest request,Model model,Station station) throws Exception {
		try {
			// 获取当前要修改的用户
			Station currentstationInfo = stationService.selectByPrimaryKey(station.getId());
			if(null == currentstationInfo)
			{
				model.addAttribute("message","修改检车站信息失败，未找到对应的检车站信息！");
				return list(request, model,null);
			}
			station.setName(null);
			station.setCode(null);
			int count = stationService.updateByPrimaryKeySelective(station);
			if(count > 0){
				initData.reloadWxConfig();
				model.addAttribute("message", "修改检测站成功！");
				return list(request, model,station);
			}else{
				 model.addAttribute("message", "修改检测站失败！");
				 return editUI(request,model,station.getId());
			}
		}catch (Exception e) {
			 e.printStackTrace();
			 model.addAttribute("message", "修改检测站失败！");
			 return editUI(request,model,station.getId());
		}
	}
	
	/**
	 * 删除
	 * @param stationId
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/delete", method = RequestMethod.GET)
	public String delete(HttpServletRequest request,Model model,String stationId) throws Exception{
		if(StringUtils.isNotBlank(stationId)){
			int count = stationService.deleteByPrimaryKey(stationId);
			if(count > 0){
				initData.reloadWxConfig();
				model.addAttribute("message", "删除检查站成功");
			}else{
				model.addAttribute("message", "删除检测站失败！");
			}
			model.addAttribute("message", "删除检查站成功");
		}else{
			model.addAttribute("message", "删除检查站失败，传入参数为空！");
		}
		return list(request,model,null);
	}
	
	@RequestMapping(value = "/checkCode")
	@ResponseBody
	public boolean checkCodeExisted(String code) throws Exception{
		boolean result = false;
		if(StringUtils.isNotBlank(code) && null == stationService.findByCode(code)){
			result = true;
		}
		return result;
	}
	
	@RequestMapping(value = "/checkName")
	@ResponseBody
	public boolean checkNameExisted(String name) throws Exception{
		boolean result = false;
		if(StringUtils.isNotBlank(name)){
			name = java.net.URLDecoder.decode(java.net.URLDecoder.decode(name, "UTF-8"), "UTF-8");
			if(null ==stationService.findByName(name)){
				result = true;
			}
		}
		return result;
	}
}
