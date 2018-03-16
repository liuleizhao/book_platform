require(['config'],function(){
	require(["common"], function(common) {
		
		var targetModule = $("#currentPage").attr("target-module");
		
		require([targetModule], function(page) {
			common.toast("加载["+targetModule+"]js完成")
		})
	})
});