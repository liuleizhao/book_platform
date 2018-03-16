define(['jquery',"mui","common","picker","poppicker"],function($,mui,common){
	
	//变量声明区
	var a_model = {}; //推荐方式
	var name = "ajs 文件";
	var version = "1.0.0";
	
	var datePicker;//日期控件对象
	var timePicker;//时间控件对象
	
	var stationId_val,vehicleType_val,vehicleingCharacter_val,carTypeId_val,
		driverType_val,platNumber_val,newflag_val,fuelType_val,bookingChannel_val;
	
	var mobile_box,bookerName_box,idNumber_box,idTypeId_box,bookDate_box,bookTime_box
	
	//声明方法区
	var init = function() {
		stationId_val = $("#stationId").val();
		vehicleType_val = $("#vehicleType").val();
		vehicleCharacter_val = $("#vehicleCharacter").val();
		carTypeId_val = $("#carTypeId").val();
		driverType_val = $("#driverType").val();
		platNumber_val = $("#platNumber").val();
		newflag_val = $("#newflag").val();
		fuelType_val = $("#fuelType").val();
		bookingChannel_val = $("#bookingChannel").val();
		bookingChannel_val = $("#frameNumber").val();
		
		
		mobile_box = $("#mobile");
		bookerName_box = $("#bookerName");
		idNumber_box = $("#idNumber");
		idTypeId_box = $("#idTypeId");
		bookDate_box = $("#bookDate");
		bookTime_box = $("#bookTime");
		
		getDate();
		
		common.toast(message);
	}
	/*封装的post方法*/
	var post = function(url,data,fun) {
		var mask=mui.createMask();//遮罩层
		mui.ajax(url,{
		    data:data,
		        type: 'post', //HTTP请求类型
		        timeout: 10000, //超时时间设置为10秒；
		    beforeSend: function() {
		        mask.show();//显示遮罩层
		    },
		    complete: function() {
		        mask.close();//关闭遮罩层
		    },
		    success: function(data) {
		    	fun(JSON.parse(data))
		        //服务器返回响应； 
		    },
		    error: function(xhr, type, errorThrown) {
		        mui.alert('服务器连接超时，请稍后再试');
		    }
		})
	}
	
	var getDate = function() {
		post(ctx+"/app/book/getDate",{
			"stationId":stationId_val,
			"vehicleType":vehicleType_val,
			"vehicleCharacter":vehicleCharacter_val,
			"carTypeId":carTypeId_val,
			"driverType":driverType_val,
		},function(data){
			if(data.code != "00"){
				mui.toast(data.msg)
				return;
			}
			//声明方法区
			datePicker = new mui.PopPicker();
			datePicker.setData(data.result.string);
			$("#bookDate").attr("disable",false);
			$("#bookDate").on("click",function(){
				var date = $(this);
				$("#bookTime").val("");
				datePicker.show(function(items) {
					date.val(items[0])
					getTime(items[0])
				});
			});
		});
	}
	
	var getTime = function(bookDate) {
		post(ctx+"/app/book/getTime",{
			"stationId":stationId_val,
			"vehicleType":vehicleType_val,
			"vehicleCharacter":vehicleCharacter_val,
			"carTypeId":carTypeId_val,
			"driverType":driverType_val,
			"bookDate":bookDate
		},function(data){
			if(data.code != "00"){
				mui.toast(data.msg)
				return;
			}
			timePicker = new mui.PopPicker();
			timePicker.setData(data.result.AppTimeHelper);
			$("#bookTime").attr("disable",false);
			$("#bookTime").on("click",function(){
				var time = $(this)
				timePicker.show(function(items) {
					if(items[0].yetnumber >= items[0].maxnumber){
						return false;
					}
					time.val(items[0].apptime)
				});
			});
		});
	}
	
	var validate = function() {
		return true;
	}
	
	$(".submit").on("click",function(){
		if(validate()){
			$("#form").submit();
		}
	})
	
	
	//提供外部调用区
	init()
	
	//返回供调用的对象
	return a_model;
	
})
