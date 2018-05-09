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
		                    <label for="ctmlName" class="control-label">模板名称</label>
		                    <input type="text" class="form-control" id="ctmlName" name="ctmlName" required="required"/>                 
		                  </div>
		                  <div class="col-md-6 margin-bottom-15">
		                    <label for="validity" class="control-label">有效天数</label>
		                    <select class="form-control margin-bottom-15" name="validity.id" id="validity" value="${certTemplate.validity}" required="required">
		                    	<option value="">--请选择--</option>
		                    	<c:forEach var="code" items="${code}">
		                    		<option value="${code.id}">${code.paraValue}</option>
		                    	</c:forEach>
		                    </select>
		                  </div>
				      </div>
				      
				      
				      <div class="row">		            
		                  <div class="col-md-6 margin-bottom-15">
		                    <label for="kpgAlgorithm" class="control-label">密钥类型</label>
		                      <select class="form-control margin-bottom-15" name="kpgAlgorithm.id" id="kpgAlgorithm" value="${certTemplate.kpgAlgorithm}" required="required">
		                    	<option value="">--请选择--</option>
		                    	<c:forEach var="sysCodes" items="${sysCodes}">
		                    		<option value="${sysCodes.id}">${sysCodes.paraValue}</option>
		                    	</c:forEach>
		                    </select>
		                  </div>
		                   <div class="col-md-6 margin-bottom-15">
		                    <label for="kpgAlgorithm" class="control-label">哈希算法</label>
		                      <select class="form-control margin-bottom-15" name="isValid" id="isValid"  required="required">
			                      <option value="">--请选择--</option>
							      <option value="sha1"  >sha1</option>
							      <option value="sha256" >sha256</option>
		                  	   </select>   
		                  </div>
				     </div> 
				     
				     <div class="row"> 
	                  <div class="col-md-6 margin-bottom-15">
	                    <label for="isValid" class="control-label">是否有效</label>
	                    	<select class="form-control margin-bottom-15" name="isValid" id="isValid"  required="required">
						      <option value="1"  >是</option>
						      <option value="0"  >否</option>
	                  	   </select>               
	                  </div>
	                  <div class="col-md-6 margin-bottom-15">
	                    <label for="reviewedType" class="control-label">审核方式</label>
	                    	<select class="form-control margin-bottom-15" name="reviewedType" id="reviewedType" required="required" >
						      <option value="0">自动</option>
						      <option value="1">手动</option>
	                  	   </select>               
	                  </div>
				   </div>
	               	
	               <div class="row"> 
		                  <div class="col-md-6 margin-bottom-15">
		                    <label for="baseDn" class="control-label">BaseDN</label>
		                    <input type="text" class="form-control" id="baseDn" name="baseDn" required="required"/>                 
		                  </div>
				      </div>	
	               
	               	
	               <div class="row">
	                  <div class="col-md-12 margin-bottom-15">
	                    <label for="notes" class="control-label">备注 </label>
	                    <textarea class="form-control" rows="3" id="notes" name="notes"></textarea>
	                  </div>
				   </div>
				     
				   <div class="row"> 
		                  <div class="col-md-6 margin-bottom-15">
		                    <label for="signCertKeyUsage" class="control-label">签名证书用法</label>
		                        <div class="row">
					                <div class="col-md-12 margin-bottom-15">
						                 <label class="checkbox-inline" required="required">
					                    	<c:forEach var="scsu" items="${sysCodeSignUse}">
					                    		<input type="checkbox" id="inlineCheckbox1" value="${scsu.id}">${scsu.paraValue}
					                    	</c:forEach>
					                  	 </label>
					                </div>
              				   </div>
		                  </div>
		                  <div class="col-md-6 margin-bottom-15">
		                   <div class="row">
		                    <label for="signCertKeyUsage" class="control-label">签名证书用法扩展</label>
			                <div class="col-md-12 margin-bottom-15">
			                  <label class="checkbox-inline" required="required">
					                 <c:forEach var="scseu" items="${sysCodeSignExtUse}">
					                    <input type="checkbox" id="inlineCheckbox1" value="${scseu.id}">${scseu.paraValue}
					                  </c:forEach>
					           </label>
			                </div>
              			  </div>
		                  </div>
		             </div>
		             
		            <div class="row"> 
	                  <div class="col-md-6 margin-bottom-15">
	                    <label for="paraValue" class="control-label">加密证书用法</label>
		                   <div class="row">
			                <div class="col-md-12 margin-bottom-15">
			                  <label class="checkbox-inline" required="required">
					                 <c:forEach var="sceu" items="${sysCodeEncUse}">
					                    <input type="checkbox" id="inlineCheckbox1" value="${sceu.id}">${sceu.paraValue}
					                  </c:forEach>
					           </label>
			                </div>
              			  </div>
	                  </div>
	                  <div class="col-md-6 margin-bottom-15">
	                    <label for="notes" class="control-label">加密证书用法扩展</label>
		                   <div class="row">
			                <div class="col-md-12 margin-bottom-15">
			                  <label class="checkbox-inline" required="required">
					                 <c:forEach var="sceeu" items="${sysCodeExKeyUse}">
					                    <input type="checkbox" id="inlineCheckbox1" value="${sceeu.id}">${sceeu.paraValue}
					                  </c:forEach>
					           </label>
			                </div>
              			  </div>  
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
