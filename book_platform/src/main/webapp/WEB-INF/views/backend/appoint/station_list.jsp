<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/common/taglibs.jsp"%>
<!DOCTYPE html>
<html>
<head>
<%@ include file="/WEB-INF/common/meta.jsp"%>
<title>${appName}-检测站列表</title>
<%@ include file="/WEB-INF/common/common.jsp"%>
    

<link href="${ctx }/static/backend/css/plugins/bootstrap-table/bootstrap-table.min.css" rel="stylesheet">
<script src="${ctx }/static/backend/js/plugins/bootstrap-table/bootstrap-table.min.js"></script>
<script src="${ctx }/static/backend/js/plugins/bootstrap-table/bootstrap-table-mobile.min.js"></script>
<script src="${ctx }/static/backend/js/plugins/bootstrap-table/locale/bootstrap-table-zh-CN.min.js"></script>
</head>
<style>
	.float-e-margins .btn {
     margin-bottom: 0px; 
}
</style>
<body class="gray-bg">
<div class="wrapper wrapper-content animated fadeIn">
	<div class="ibox float-e-margins">
		<div class="ibox-content">
			 <form role="form" class="form-inline" action="${ctx }/backend/appoint/station/list" method="get" name="form" id="form">
			 	<input type="hidden" id="currentPage" name="currentPage" value="${currentPage }" />
                  <div class="form-group">
                  	  <label class="control-label">编号</label>
                      <input type="text" name="code" value="${station.code }" class="form-control">
                  </div>
                  <div class="form-group">
                  	  <label class="control-label">名称</label>
                      <input type="text" name="name" value="${station.name }" class="form-control">
                  </div>
                  <button class="btn btn-white" type="submit">查询</button>
                  <button class="btn btn-white" type="button" onclick="javascript:window.location.href='${ctx }/backend/appoint/station/add'">新增</button>
              </form>
		</div>
		<div class="ibox-content">
			<div class="row row-lg">
				<div class="col-sm-12">
					<div class="context">
						<div class="table_box">
							<table data-toggle="table">
								<thead>
									<tr>
										<th>编号</th>
										<th>名称</th>
										<th>联系方式</th>
										<th>状态</th>
										<th>操作</th>
									</tr>
								</thead>
								<tbody>
									<c:forEach items="${pageView.list }" var="station" varStatus="status">
										<tr>
											<td>${station.code }</td>
											<td>${station.viewName }(${station.orderNum }站)</td>
											<td>${station.phone }</td>
											<%-- <td><fmt:formatDate value="${station.createDate }" pattern="yyyy-MM-dd HH:mm:ss" /></td> --%>
											<td>${station.state.description }</td>
											<td>
												<a href="${ctx }/backend/appoint/station/edit?stationId=${station.id}">编辑</a>
												&nbsp; &nbsp;
												<a href="javascript:deleteUser('${station.id}','${station.name}');">删除</a>
											</td>
										</tr>
									</c:forEach>
								</tbody>
							</table>
						</div>
						<%@ include file="/WEB-INF/common/pagination.jsp"%>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
</body>
	<!-- 将common.js放置在最后 -->
	<script type="text/javascript">
		function deleteUser(id,name){
			art.dialog.confirm("您确定要删除用户【"+name+"】吗？", function() {
					location.href = "${ctx}/backend/appoint/station/delete?stationId="+id;
				})
		}
	</script>

</html>
