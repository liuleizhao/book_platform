/*配置模块信息*/
define(function(){
   require.config({
	   	urlArgs: "r=1.3",
		baseUrl: ctx + "/static/app/js",
		paths: {
			"jquery": "lib/jquery/jquery-3.3.1.min",
			"mui":"lib/mui/mui.min",
			"picker":"lib/mui/picker",
			"poppicker":"lib/mui/poppicker",
			"wx":"lib/wx/jweixin-1.2.0",
			
			"index":"app/index",
			"common":"app/common",
			
			"offic_book_agreement":"app/offic_book_agreement",
			"offic_book_input":"app/offic_book_input",
			"offic_book_login":"app/offic_book_login",
			"offic_book_org":"app/offic_book_org",
			
			"own_book_chancel":"app/own_book_chancel",
			"own_book_car":"app/own_book_car",
			"own_book_input":"app/own_book_input",
			"book_ok":"app/book_ok",
			
			"car_info_list":"app/car_info_list",
			"car_info_detail":"app/car_info_detail",
			
			"book_info_list":"app/book_info_list",
			"book_info_detail":"app/book_info_detail"
			
		},shim:{
			'picker': {
	            deps: ['mui'],
	            exports: 'picker'
	        },
	        'poppicker': {
	            deps: ['mui'],
	            exports: 'poppicker'
	        }
		}
	})
});
