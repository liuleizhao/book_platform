package com.cs.app.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.cs.book.entity.BookInfo;
import com.cs.book.entity.WxUser;
import com.cs.book.service.BookInfoService;
import com.cs.common.entityenum.BookStateEnum;
import com.cs.common.entityenum.InterfaceCodeEnum;
import com.cs.common.entityenum.InterfaceEnum;
import com.cs.common.utils.json.JSONObject;
import com.cs.common.utils.json.XML;
import com.cs.common.utils.webservice.InterfaceUtils;
import com.cs.mvc.web.BaseController;

/**
 * 手机端预约信息
 * @author LLZ
 *
 */
@Controller
@RequestMapping(value = "/app/bookInfo")
public class AppBookInfoController extends BaseController{
	
	
	@Autowired
	private BookInfoService bookInfoService;
	
	@RequestMapping(value="/list")
	public String list(HttpServletRequest request,Model model) throws Exception{
		WxUser wxuser = getAppUser(request);
		List<BookInfo> bookInfoList= bookInfoService.findbyUserId(wxuser.getId());
		model.addAttribute("bookInfoList", bookInfoList);
		return "app/book_info_list";
	}
	
	
	@RequestMapping(value="/info",method = RequestMethod.POST)
	public String edit(HttpServletRequest request,RedirectAttributes model,String bookInfoId) throws Exception{
		
		BookInfo bookInfo=bookInfoService.selectByPrimaryKey(bookInfoId);
		if(bookInfo == null){
			model.addFlashAttribute("message", "获取预约信息失败");
			return redirect("/app/bookInfo/list");
		}
		model.addFlashAttribute("bookInfo", bookInfo);
		return "app/book_info_detail";
	}
	
	@ResponseBody
	@RequestMapping(value="/cancel",method = RequestMethod.GET)
	public String cancel(HttpServletRequest request,RedirectAttributes model,String bookInfoId) throws Exception{
		BookInfo bookInfo = bookInfoService.selectByPrimaryKey(bookInfoId);
		String cancelXml = InterfaceUtils.callInterface(InterfaceEnum.JK07,bookInfo);
		JSONObject cancelJSONObj = XML.toJSONObject(cancelXml);
		if(InterfaceCodeEnum.SUCCEED.equals(cancelJSONObj.getJSONObject("ResponseMessage").getString("code"))){
			bookInfo.setBookState(BookStateEnum.YYQX);
			int count = bookInfoService.updateByPrimaryKeySelective(bookInfo);
			if(count>0){
				model.addFlashAttribute("message", "预约取消成功");
				return redirect("/app/bookInfo/list");
			}
		}
		model.addFlashAttribute("message", "预约取消失败");
		return redirect("/app/bookInfo/list");
	}

}
