define(['jquery',"mui","common"],function($,mui,common){
	
	//变量声明区
	var obj = {}; //推荐方式
	var name = "车辆信息管理js文件";
	var version = "1.0.0";
	//声明方法区
	
	var init = function() {
		common.pushHistory(ctx+"/app/index");
	}
	
	//事件绑定
	$(".add").on("click",function(){
		window.location.href = ctx+"/app/carInfo/add";
	})
	
	$(".edit").on("click",function(){
		var id = $(this).parent().attr("car_id");
		window.location.href = ctx+"/app/carInfo/edit?carInfoId="+id;
	})
	
	$(".del").on("click",function(){
		var id = $(this).parent().attr("car_id");
		var btnArray = ['取消','删除'];
		mui.confirm('',"确认删除车辆信息", btnArray, function(e) {
			if (e.index == 1) {
				window.location.href = ctx+"/app/carInfo/del?carInfoId="+id;
			}
		})
	})
	
	
    init();
	//提供外部调用区
	
	//返回供调用的对象
	return obj;
	
})
