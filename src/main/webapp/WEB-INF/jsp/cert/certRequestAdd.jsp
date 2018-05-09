<%@ page contentType="text/html;charset=utf8" pageEncoding="utf8" %>
<%@ taglib prefix="c" uri="/WEB-INF/c-1_0-rt.tld"%>
<!DOCTYPE html>
<%@ include file="../left.jsp" %>
<%@ include file="../footer.jsp" %>
<body>

        <div class="templatemo-content">
	          <ol class="breadcrumb">
	            <li><a href="<%=request.getContextPath()%>/main">主页面</a></li>
	            <li class="active">证书模板列表</li>
	             <li><a href="javascript:searchByCondition()">查询</a></li>
	          </ol>
        <div class="row">
            <div class="col-md-12">
            	 <c:if test="${success != null && success != ''}">
	                   <div class="alert alert-success alert-dismissible" role="alert">
	                      <button type="button" class="close" data-dismiss="alert"><span aria-hidden="true">&times;</span><span class="sr-only">Close</span></button>
	                      <strong>${msg}</strong> 
	                    </div>
	             </c:if> 
	             
	             <form action="insert.do" id="templatemo-preferences-form" method="post">
	             
	             	   <div class="row">		            
		                  <div class="col-md-6 margin-bottom-15">
		                    <label for="kpgAlgorithm" class="control-label">证书模板</label>
		                      <select class="form-control margin-bottom-15" name="ctmlId.id" id="ctmlId" required="required" onchange="ocra(this.value)">
		                    	<option value="">--请选择--</option>
		                    	<c:forEach var="rct" items="${raCertTemplates}">
		                    		<option value="${rct.id}">${rct.ctmlName}</option>
		                    		<%-- <option value="${rct.validity}">${rct.ctmlName}</option> --%>
		                    	</c:forEach>
		                    </select>
		                  </div>
		                  
		                  <div class="col-md-6 margin-bottom-15">
		                    <label for="validity" class="control-label">证书有效天数</label>
		                    <input type="text" class="form-control" id="validity" name="validity" required="required"/>             
		                  </div>
		                  
				     </div> 
				     <div class="row">		            
		                  
		                   <div class="col-md-6 margin-bottom-15">
		                    <label for="validity" class="control-label">生效时间</label>
		                    <select class="form-control margin-bottom-15" name="startTimeType" id="startTimeType"  required="required">
		                    	<option value="">--请选择--</option>
		                    		<option value="1">审核通过时</option>
									<option value="3">指定生效时间</option>
		                    </select>
		                    <input type="date" class="form-control" id="startTime" name="startTime" />
		                  </div>
				     </div> 
	             		
	               	  <div class="row"> 
		                  <div class="col-md-6 margin-bottom-15">
		                    <label for="certCn" class="control-label">用户名</label>
		                    <input type="text" class="form-control" id="certCn" name="certCn" required="required"/>                 
		                  </div>
		                  <div class="col-md-6 margin-bottom-15">
		                    <label for="certDn" class="control-label">邮箱</label>
		                    <input type="text" class="form-control" id="email" name="email" required="required"/>
		                  </div>
				      </div>
				      
				      <div class="row"> 
		                  <div class="col-md-6 margin-bottom-15">
		                    <label for="company" class="control-label">单位名称</label>
		                    <input type="text" class="form-control" id="company" name="company" required="required"/>                 
		                  </div>
		                  <div class="col-md-6 margin-bottom-15">
		                    <label for="location" class="control-label">证书拓展1</label>
		                    <input type="text" class="form-control" id="extend" name="extend" required="required"/>
		                  </div>
				      </div>
				      
				      <div class="row"> 
		                  <div class="col-md-6 margin-bottom-15">
		                    <label for="state" class="control-label">证书拓展2</label>
		                    <input type="text" class="form-control" id="extend" name="extend" required="required"/>                 
		                  </div>
		                  <div class="col-md-6 margin-bottom-15">
		                    <label for="country" class="control-label">证书拓展3</label>
		                    <input type="text" class="form-control" id="extend" name="extend" required="required"/>
		                  </div>
				      </div>
				      
				      
				     
	               <div class="row">
	                  <div class="col-md-12 margin-bottom-15">
	                    <label for="notes" class="control-label">备注 </label>
	                    <textarea class="form-control" rows="3" id="notes" name="notes"></textarea>
	                  </div>
				   </div>
				     
	               <div class="modal-footer">
			            <button type="submit" class="btn btn-primary">保存</button>
			            <button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
			        </div>
	             </form>
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
  

  
  /*选择证书模板触发*/
/*   function ocra(value){
	  $("#validity").val(value);
  } */

</script>

<c:if test="${success != null && success != ''}">     
<script type="text/javascript">
 document.getElementById("upd_list_row_id_${success}").className="success";
</script>
</c:if> 
 
<c:if test="${message != null && message != ''}">     
	<script type="text/javascript">
	 	$("#modal_insert").modal('show');
	</script>
</c:if> 
