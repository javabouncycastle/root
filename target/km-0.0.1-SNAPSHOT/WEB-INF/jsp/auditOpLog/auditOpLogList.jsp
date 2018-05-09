<%@ page contentType="text/html;charset=utf8" pageEncoding="utf8" %>
<%@ taglib prefix="c" uri="/WEB-INF/c-1_0-rt.tld"%>
<!DOCTYPE html>
<%@ include file="../left.jsp" %>
<%@ include file="../footer.jsp" %>
<body>

        <div class="templatemo-content">
	          <ol class="breadcrumb">
	            <li><a href="<%=request.getContextPath()%>/main">主页面</a></li>
	            <li class="active">日志列表</li>
	            <li><a href="../../sign-in.html">Sign In Form /</a><a href="javascript:searchByCondition()"> 查询/</a><a href="javascript:exportExcel()"> 导出</a></li>
	          </ol>
        <div class="row">
            <div class="col-md-12">
            	 <c:if test="${success != null && success != ''}">               
	                   <div class="alert alert-success alert-dismissible" role="alert">
	                      <button type="button" class="close" data-dismiss="alert"><span aria-hidden="true">&times;</span><span class="sr-only">Close</span></button>
	                      <strong>${msg}</strong> 
	                    </div>
	             </c:if> 
              <div class="table-responsive">
                <h4 class="margin-bottom-15">日志列表</h4>
                <!-- 查询条件div -->
                 <div class="row" id="searchCondition" style="display:none">
		            <div class="col-md-12">
		              <form id="templatemo-preferences-form" action="searchByCondition.do" method="post" >
		                <div class="row">
	                	  <div class="col-md-6 margin-bottom-15">
	                  	     <label for="operator" class="control-label">管理员</label>
			                 <input type="text" class="form-control" id="operator" name="operator" value="${auditOpLog.operator}"/>   
		                  </div>
		                   <div class="col-md-6 margin-bottom-15">
	                  	     <label for="actionExt1" class="control-label">操作扩展</label>
			                 <input type="text" class="form-control" id="actionExt1" name="actionExt1" value="${auditOpLog.actionExt1}" />   
		                  </div>
		                </div>
		            <%--     <div class="row">
	                	  <div class="col-md-6 margin-bottom-15">
	                  	     <label for="startTime" class="control-label">开始时间</label>
			                 <input type="date" class="form-control" id="startTime" onfocus="WdatePicker({skin:'default',dateFmt:'yyyy-MM-dd '})" class="MyWdate" name="startTime" value="${auditOpLog.startTime}"/>   
		                  </div>
		                   <div class="col-md-6 margin-bottom-15">
	                  	     <label for="endTime" class="control-label">结束时间</label>
	                  	     <input type="date" class="form-control" id="startTime" onfocus="WdatePicker({skin:'default',dateFmt:'yyyy-MM-dd '})" class="MyWdate" name="startTime" value="${auditOpLog.endTime}"/>   
		                  </div>
		                </div> --%>
		                  <div class="row">
		                  <div class="col-md-6 margin-bottom-15">
				                <label for="action" class="control-label">执行的操作 </label>
			                    <input type="text" class="form-control" id="action" name="action" value="${auditOpLog.action}"/>  
		                  </div>
		                </div>
		                <div class="row templatemo-form-buttons">
			                <div class="col-md-12">
			                  <button type="submit" class="btn btn-primary">查询</button>
			               	 </div>
		            	 </div>
		               </form> 
		             </div>
		          </div>
                <table class="table table-striped table-hover table-bordered data-table">
		                  <thead>
		                    <tr bgcolor="CFCFCF">
		                      <th width="5%">主键</th>
		                      <th width="10%">操作类型</th>
		                      <th width="10%">执行的操作</th>
		                      <th width="10%">操作扩展</th>
		                      <th width="10%">本地化消息</th>
		                      <th width="10%">操作时间</th>
		                      <th width="10%">IP地址</th>
		                      <th width="10%">管理员</th>
		                      <th width="10%">结果</th>
		                    </tr>
		                  </thead>
                    <tbody id="id_tbody_upd_list"> 
                    <c:forEach var="row" items="${auditOpLogs}">
                    	<!--  修改数据字典-->
	                    <tr id="upd_list_row_id_${row.id}" >
		                    <td>
		                    	<a href="javascript:edit('${row.id}','${row.type}','${row.action}','${row.actionExt1}','${row.actionExt2},'${row.actionExt3},'${row.actionExt4},
		                    	'${row.message},'${row.timestamp},'${row.ip},'${row.operator},'${row.isOpSucc}')" class="btn btn-link">${row.id}</a>
		                    </td>
		                    <td>${row.type}</td>
		                    <td>${row.action}</td>
		                    <td>${row.actionExt1}</td>
		                    <td>${row.message}</td>
		                    <td><fmt:formatDate value="${row.timestamp}" type="date" pattern="yyyy-MM-dd HH:mm:ss"/></td>
		                    <td>${row.ip}</td>
		                    <td>${row.operator}</td>
		                    <td>${row.isOpSucc}</td>
	                    </tr>
                	</c:forEach>
               </table>
            </div>
          </div>
        </div>
       </div>
   </body>     
 <script type="text/javascript">
 
 $(document).ready(function(){
	
	$('.data-table').dataTable({
		"bJQueryUI": true,
		"sPaginationType": "full_numbers",
		"sDom": '<""l>t<"F"fp>'
	});
 });

function searchByCondition(){
	if($("#searchCondition").is(":hidden")){
		$("#searchCondition").show();
	}else{
		$("#searchCondition").hide();
	}
}
function exportExcel(){
	window.location.href="exportExcel.do?operator="+$("#operator").val()+"&actionExt1="+$("#actionExt1").val()+"&action="+$("#action").val();
}
</script>

<c:if test="${success != null && success != ''}">     
<script type="text/javascript">
 document.getElementById("upd_list_row_id_${success}").className="success";
</script>
</c:if> 
 
<c:if test="${message != null && message != ''}">     
	<script type="text/javascript">
	 	//$("#modal_insert").modal('show');
	</script>
</c:if> 
