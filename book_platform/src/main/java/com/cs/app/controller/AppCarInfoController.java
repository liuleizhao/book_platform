package com.cs.app.controller;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.cs.book.entity.CarInfo;
import com.cs.book.entity.WxUser;
import com.cs.book.service.CarInfoService;
import com.cs.mvc.web.BaseController;
import com.cs.system.entity.User;

/**
 * 手机端登录控制类
 * @author LLZ
 *
 */
@Controller
@RequestMapping(value = "/app/carInfo")
public class AppCarInfoController extends BaseController{
	
	
	@Autowired
	private CarInfoService carInfoService;
	
	@RequestMapping(value="/list")
	public String list(HttpServletRequest request,Model model) throws Exception{
		WxUser wxuser = getAppUser(request);
		List<CarInfo> carInfoList= carInfoService.findbyUserId(wxuser.getId());
		model.addAttribute("carInfoList", carInfoList);
		return "app/car_info_list";
	}
	
	@RequestMapping(value="/add",method = RequestMethod.GET)
	public String addUI(HttpServletRequest request,Model model) throws Exception{
		return "app/car_info_detail";
	}
	
	@RequestMapping(value="/add",method = RequestMethod.POST)
	public String add(HttpServletRequest request,RedirectAttributes model, CarInfo carInfo) throws Exception{
		WxUser wxuser = getAppUser(request);
		carInfo.setUserId(wxuser.getId());
		carInfo.setCreateDate(new Date());
		Integer count = carInfoService.insert(carInfo);
		if(count>0){
			model.addFlashAttribute("message", "车辆信息添加成功");
		}else{
			model.addFlashAttribute("message", "车辆信息添加失败");
		}
		return redirect("/app/carInfo/list");
	}
	
	@RequestMapping(value="/edit",method = RequestMethod.GET)
	public String editUI(HttpServletRequest request,Model model,RedirectAttributes rmodel,String carInfoId) throws Exception{
		if(StringUtils.isNotBlank(carInfoId)){
			CarInfo carInfo = carInfoService.selectByPrimaryKey(carInfoId);
			if(carInfo != null){
				model.addAttribute("carInfo", carInfo);
				return "app/car_info_detail";
			}
		}
		rmodel.addFlashAttribute("message", "获取车辆信息失败");
		return redirect("/app/carInfo/list");
	}
	
	@RequestMapping(value="/edit",method = RequestMethod.POST)
	public String edit(HttpServletRequest request,RedirectAttributes model,CarInfo carInfo) throws Exception{
		
		Integer count = carInfoService.updateByPrimaryKeySelective(carInfo);
		if(count>0){
			model.addFlashAttribute("message", "车辆信息修改成功");
		}else{
			model.addFlashAttribute("message", "车辆信息修改失败");
		}
		return redirect("/app/carInfo/list");
	}
	
	@RequestMapping(value="/del",method = RequestMethod.GET)
	public String del(HttpServletRequest request,RedirectAttributes model,String carInfoId) throws Exception{
		if(StringUtils.isNotBlank(carInfoId)){
			Integer count = carInfoService.deleteByPrimaryKey(carInfoId);
			if(count>0){
				model.addFlashAttribute("message", "车辆信息删除成功");
				return redirect("/app/carInfo/list");
			}
		}
		model.addFlashAttribute("message", "车辆信息删除失败");
		return redirect("/app/carInfo/list");
	}

}
