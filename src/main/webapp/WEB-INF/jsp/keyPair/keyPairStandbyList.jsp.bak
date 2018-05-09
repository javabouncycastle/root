<%@ page contentType="text/html;charset=utf8" pageEncoding="utf8" %>
<%@ taglib prefix="c" uri="/WEB-INF/c-1_0-rt.tld"%>
<!DOCTYPE html>
<%@ include file="../left.jsp" %>
<%@ include file="../footer.jsp" %>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/jquery.uniform.js"></script>
<body>

        <div class="templatemo-content">
	          <ol class="breadcrumb">
	            <li><a href="<%=request.getContextPath()%>/main">主页面</a></li>
	            <li class="active">密钥管理</li>
	            <li><a href="../../sign-in.html">Sign In Form</a></li>
	             <li><a href="javascript:searchByCondition()"> 查询</a></li>
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
                <h4 class="margin-bottom-15">密钥列表</h4>
                <!-- 查询条件div -->
                 <div class="row" id="searchCondition" style="display:none">
		            <div class="col-md-12">
		              <form id="templatemo-preferences-form" action="searchByCondition.do" method="post" >
		                <div class="row">
	                	  <div class="col-md-6 margin-bottom-15">
	                  	     <label for="" class="control-label">算法+长度</label>
			                 <input type="text" min="0" class="form-control"  id="keyPairAlgorithm.algorithmName" name="keyPairAlgorithm.algorithmName"/>     
		                  </div>
		                  <div class="col-md-6 margin-bottom-15">
	                  	     <label for="" class="control-label">任务</label>
			                 <input type="text" min="0" class="form-control"  id="kpgTask.name" name="kpgTask.name"/>     
		                  </div>
		                </div>
		                <div class="row">
		                  <div class="col-md-6 margin-bottom-15">
			                    <label for="startTime" class="control-label">开始时间</label>
			                    <input type="date" class="form-control" id="startTime" name="startTime" />                 
		                  </div>
		                  <div class="col-md-6 margin-bottom-15">
		                  	<label for="endTime" class="control-label">结束 </label>
			                    <input type="date" class="form-control" id="endTime" name="endTime" />           
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
                 
		          <div class="widget-box">
	                <table class="table table-striped table-hover table-bordered with-check data-table">
		                  <thead>
		                    <tr bgcolor="CFCFCF" >
		                      <th><input type="checkbox" id="title-table-checkbox" name="title-table-checkbox" width="1%"/>全选</th>
		                      <th width="10%" >主键</th>
		                      <th width="10%">算法+长度</th>
		                      <th width="10%">任务</th>
		                      <th width="10%">生成时间</th>
		                    </tr>
		                  </thead>
	                    <tbody id="id_tbody_upd_list"> 
	                    <c:forEach var="row" items="${keyPairStandbys}">
	                    	<tr>
	                    	<td><input type="checkbox"/></td>
	                    	<td>${row.id}</td>
		                    <td>${row.keyPairAlgorithm.algorithmName}</td>
		                    <td>${row.kpgTask.name}</td>
		                    <td><fmt:formatDate value="${row.genTime}" type="date" pattern="yyyy-MM-dd HH:mm:ss"/></td>
		                    </tr>
	                	</c:forEach>
	               </table>
	          </div>
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

function FormatDate (strTime) {
    var date = new Date(strTime);
    var month=date.getMonth()+1;
    if(month<10){
    	month="0"+month;
    }
    var dd=date.getDate();
    if(dd<10){
    	dd="0"+dd;
    }
    return date.getFullYear()+"-"+month+"-"+dd;
}

function searchByCondition(){
	if($("#searchCondition").is(":hidden")){
		$("#searchCondition").show();
	}else{
		$("#searchCondition").hide();
	}
}
</script>

<c:if test="${success != null && success != ''}">     
<script type="text/javascript">
 document.getElementById("upd_list_row_id_${success}").className="success";
</script>
</c:if> 
 
<c:if test="${messageInsert != null && messageInsert != ''}">     
	<script type="text/javascript">
	 	$("#modal_insert").modal('show');
	</script>
</c:if> 