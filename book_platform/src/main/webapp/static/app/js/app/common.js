define(['jquery',"mui"],function($,mui){
	
	//变量声明区
	var obj = {}; //推荐方式
	var name = "js公共方法类";
	
	//声明方法区
	/*信息弹出*/
	var toast = function (msg){
		if(msg!=""){
			mui.toast(msg)
		}
    }
	
	/*实现返回按钮页面可控*/
	var pushHistory = function (url) {
		window.onpopstate = function() {
            location.href=url;
        };
        var state = {
            title: "title",
            url: "#"    };
        window.history.pushState(state, "title", "#");
    };
    /*获取页面名称*/
    function getPageName()
    {
      var a = location.href;
      var b = a.split("/");
      var c = b.slice(b.length-1, b.length).toString(String).split(".");
      return c.slice(0, 1);
    }
	
	//提供外部调用区
	obj.toast = toast;
	obj.pushHistory = pushHistory;
	obj.getPageName = getPageName;
	
	//返回供调用的对象
	return obj;
	
})