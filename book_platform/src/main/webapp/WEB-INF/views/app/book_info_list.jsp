<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/common/taglibs.jsp"%>
<!DOCTYPE html>
<html>
<head>
	<%@ include file="/WEB-INF/common/meta.jsp"%>
	<title>预约信息</title>
	<%@ include file="/WEB-INF/common/app_common.jsp"%>
</head>
	<body>
		<div class="mui-content">
			<div class="i-content-padded">
				<ul class="mui-table-view">
					<c:forEach items="${bookInfoList }"  var="bookInfo">
						<li class="mui-table-view-cell">
							<div>
								<span class="mui-h4">预约号：${bookInfo.bookNumber }</span>
								<div style="float: right;color:#666;margin-left: 10px;font-size: 12px;">${bookInfo.bookState.description }</div>
							</div>
							<div>
								<span class="mui-h6">预约日期：${bookInfo.bookDate }</span>
							</div>
							<div>
								<span class="mui-h6">预约时间：${bookInfo.bookTime }</span>
							</div>
							<div>
								<span class="mui-h6">订单号：${bookInfo.bookerName }</span>
								<div style="float: right;color:#666;margin-left: 10px;font-size: 12px;">删除<span style="font-size: 16px;" class="mui-icon mui-icon-trash"></span></div>
							</div>
						</li>
					</c:forEach>
				</ul>
			</div>
		</div>
	</body>
	<script data-main="${ctx }/static/app/js/main" id="currentPage" target-module="book_info_list"  src="${ctx }/static/app/js/require.min.js"></script>
</html>