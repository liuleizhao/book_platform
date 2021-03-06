<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/common/taglibs.jsp"%>
<!DOCTYPE html>
<html>
<head>
	<%@ include file="/WEB-INF/common/meta.jsp"%>
	<title>年审预约车辆选择</title>
	<%@ include file="/WEB-INF/common/app_common.jsp"%>
</head>
	<body> 
		<div class="mui-content">
			<div class="i-content-padded">
				<div class="mui-h5">选择年检车辆</div>
				<ul class="mui-table-view mui-table-view-chevron i-ul" >
					<c:forEach items="${carInfoList }" var="item" >
						<li class="mui-table-view-cell i-li">
							<a href="${ctx }/app/book/input?carInfoId=${item.id }" class="mui-navigate-right">
								<img class="mui-media-object mui-pull-left" src="${ctx }/static/app/images/img2.jpg">
								<div class="mui-media-body">
									<c:choose>
										<c:when test="${'' eq item.platNumber || null == item.platNumber }">
											车架号：${item.frameNumber }
										</c:when>
										<c:otherwise>
											车牌号：${item.platNumber }
										</c:otherwise>
									</c:choose>
									<p class='mui-ellipsis'>创建时间：<fmt:formatDate value="${item.createDate }" pattern="yyyy-MM-dd" /></p>
								</div>
							</a>
						</li>
					</c:forEach>
				</ul>
			</div>
		</div>
	</body>
	<script data-main="${ctx }/static/app/js/main" id="currentPage" target-module="own_book_car" src="${ctx }/static/app/js/require.min.js"></script>
</html>