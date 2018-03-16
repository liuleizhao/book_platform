<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/common/taglibs.jsp"%>
<!DOCTYPE html>
<html>
<head>
	<%@ include file="/WEB-INF/common/meta.jsp"%>
	<title>预约信息填写</title>
	<%@ include file="/WEB-INF/common/app_common.jsp"%>
	
	<link href="${ctx }/static/app/css/mui.picker.min.css" rel="stylesheet" />
	<link href="${ctx }/static/app/css/mui.poppicker.css" rel="stylesheet" />
</head>
	<body> 
		<div class="mui-content">
			<div class="i-content-padded">
				<form action="${ctx }/app/book/submit" method="post" id="form">
					<input id="carInfoId" name="carInfoId"  type="hidden" value="${carInfo.id }">
					<input id="vehicleType" name="vehicleType"  type="hidden" value="${carInfo.vehicleType }">
					<input id="vehicleCharacter" name="vehicleCharacter"  type="hidden" value="${carInfo.vehicleCharacter }">
					<input id="carTypeId" name="carTypeId"  type="hidden" value="${carInfo.carTypeId }">
					<input id="driverType" name="driverType"  type="hidden" value="${carInfo.driverType }">
					<input id="platNumber" name="platNumber"  type="hidden" value="${carInfo.platNumber }">
					<input id="frameNumber" name="frameNumber"  type="hidden" value="${carInfo.frameNumber }">
					<input id="fuelType" name="fuelType"  type="hidden" value="${carInfo.fuelType }">
					<input id="newflag" name="newflag"  type="hidden" value="${carInfo.newflag }">
					<input id="bookChannel" name="bookChannel"  type="hidden" value="7"><!-- 微信渠道 -->
					
					<h5>姓名：</h5>
					<div class="mui-input-row">
						<input id="bookerName" name="bookerName" type="text" class="mui-input-group" placeholder="请输入姓名">
					</div>
					<h5>手机号：</h5>
					<div class="mui-input-row">
						<input id="mobile" name="mobile" type="text" class="mui-input-group" placeholder="请输入手机号">
					</div>
					<h5>证件类型：</h5>
					<div class="mui-input-row">
						<select id="idTypeId" name="idTypeId" class="i-select">
						  	<option value="e4e48584399473d20139947f125e2b2c">居民身份证</option>
							<option value="40288282463ceca50146462942d3055c">组织机构代码证书</option>
							<option value="4028823f51d79d4d0151f1ebb1dc361e">统一社会信用代码</option>
							<option value="e4e48584399b293601399b60996b55e6">境外人员身份证明</option>
						</select>
					</div>
					<h5>证件号码：</h5>
					<div class="mui-input-row">
						<input id="idNumber" name="idNumber" type="text" class="mui-input-group" placeholder="证件号码">
					</div>
					<h5>预约日期：</h5>
					<div class="mui-input-row">
						<input id="bookDate" name="bookDate" type="text" class="mui-input-group" placeholder="请选择..." readonly="readonly">
					</div>
					<h5>预约时间：</h5>
					<div class="mui-input-row">
						<input id="bookTime" name="bookTime" type="text" class="mui-input-group" placeholder="请选择..." readonly="readonly">
					</div>
					<div class="i-btn-box">
						<button type="button" class="mui-btn i-btn-bule submit">立即预约</button>
					</div>
				</form>
			</div>
		</div>
	</body>
	<script data-main="${ctx }/static/app/js/main" id="currentPage" target-module="own_book_input" src="${ctx }/static/app/js/require.min.js"></script>
</html>