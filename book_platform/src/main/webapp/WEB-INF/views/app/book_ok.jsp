<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/common/taglibs.jsp"%>
<!DOCTYPE html>
<html>
<head>
	<%@ include file="/WEB-INF/common/meta.jsp"%>
	<title>预约完成</title>
	<%@ include file="/WEB-INF/common/app_common.jsp"%>
</head>
	<body>
		<div class="mui-content">
			<div class="mui-table-view">
				<ul class="mui-table-view">
					<li class="mui-table-view-cell">
						预约号码
						<div class="i-content-text">${bookInfo.bookNumber }</div>
					</li>
					<li class="mui-table-view-cell">
						预约日期
						<div class="i-content-text">${bookInfo.bookDate }</div>
					</li>
					<li class="mui-table-view-cell">
						预约时间
						<div class="i-content-text">${bookInfo.bookTime }</div>
					</li>
					<li class="mui-table-view-cell">
						受理单位
						<div class="i-content-text">${station.name }</div>
					</li>
					<li class="mui-table-view-cell">
						详细地址
						<div class="i-content-text">${station.address }</div>
					</li>
					<li class="mui-table-view-cell">
						<p style="color: red">说明：</p>
						<p>如您是AB类驾驶人，请您下载"交管12123"手机软件,并进行注册后，方可到窗口办理驾驶证业务。</p>
						<p style="color: red">温馨提示：</p>
						<p>1.请在预约时间段内前来办理,逾期将无法办理。 </p>
						<p>2.请打印预约回执或用手机或其他方式拍摄本页面。 </p>
						<p>3.当日预约，工作人员可通过扫描二维码的方式获得预约信息。请用手机拍摄下二维码。 </p>
					</li>
				</ul>
				<div class="i-btn-box i-content-padded">
					<button type="button" class="mui-btn i-btn-bule" onclick="window.location.href = '${ctx }/app/index'">主页</button>
				</div>
		</div>
	</div>
</body>
	<script data-main="${ctx }/static/app/js/main" id="currentPage" target-module="book_ok"  src="${ctx }/static/app/js/require.min.js"></script>
</html>
