<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/common/taglibs.jsp"%>
<!DOCTYPE html>
<html>
<head>
	<%@ include file="/WEB-INF/common/meta.jsp"%>
	<title>${station.viewName }</title>
	<%@ include file="/WEB-INF/common/app_common.jsp"%>
</head>
	<body> 
		<header class="i-index-header"></header>
		<div class="mui-content">
			<input id="appid" type="hidden" value="${wxJsapiConfig.appid }">
			<input id="noncestr" type="hidden" value="${wxJsapiConfig.noncestr }">
			<input id="timestamp" type="hidden" value="${wxJsapiConfig.timestamp }">
			<input id="signature" type="hidden" value="${wxJsapiConfig.signature }">
			<input id="jsApiList" type="hidden" value="${wxJsapiConfig.jsApiList }">
			
			<ul class="mui-table-view mui-grid-view i-business-list">
				<li class="ic1 mui-table-view-cell mui-media mui-col-xs-6">
					<a href="http://qi45324239.cn.zhsho.com/">公司简介</a>
				</li>
				<li class="ic2 mui-table-view-cell mui-media mui-col-xs-6">
					<a href="${ctx }/app/book/chancel">年审预约</a>
				</li>
				<li class="ic4 mui-table-view-cell mui-media mui-col-xs-6">
					<a href="${ctx }/app/bookInfo/list">预约记录</a>
				</li>
				<li class="ic3 mui-table-view-cell mui-media mui-col-xs-6">
					<a href="${ctx }/app/carInfo/list">车辆管理</a>
				</li>
				<li class="ic5 mui-table-view-cell mui-media mui-col-xs-6">
					<a id="navigation" href="javascript:;" x="${station.longitude }" y="${station.latitude }" _name="${station.name }" address="${station.address }">一键导航</a>
				</li>
				
				<li class="ic6 mui-table-view-cell mui-media mui-col-xs-6">
					<a href="tel://${station.phone }">联系我们</a>
				</li>
			</ul>
		</div>
	</body>
	<script data-main="${ctx }/static/app/js/main" id="currentPage" target-module="index" src="${ctx }/static/app/js/require.min.js"></script>
</html>