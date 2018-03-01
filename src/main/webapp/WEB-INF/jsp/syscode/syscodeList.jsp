<%@ page contentType="text/html;charset=utf8" pageEncoding="utf8" %>
<%@ taglib prefix="c" uri="/WEB-INF/c-1_0-rt.tld"%>
<!DOCTYPE html>
<%@ include file="../left.jsp" %>
<%@ include file="../footer.jsp" %>
<body>

        <div class="templatemo-content">
	          <ol class="breadcrumb">
	            <li><a href="<%=request.getContextPath()%>/main">主页面</a></li>
	            <li class="active">数据字典列表</li>
	            <li><a href="../../sign-in.html">Sign In Form</a></li>
	             <li><a href="javascript:add()">增加数据字典 /</a><a href="javascript:searchByCondition()"> 查询</a></li>
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
                <h4 class="margin-bottom-15">数据字典列表</h4>
                <!-- 查询条件div -->
                 <div class="row" id="searchCondition" style="display:none">
		            <div class="col-md-12">
		              <form id="templatemo-preferences-form" action="searchByCondition.do" method="post" >
		                <div class="row">
	                	  <div class="col-md-6 margin-bottom-15">
	                  	     <label for="id" class="control-label">主键标识</label>
			                 <input type="number" min="0" class="form-control" name="id" id="id"/>     
		                  </div>
		                </div>
		                  <div class="row">
		                  <div class="col-md-6 margin-bottom-15">
			                    <label for="paraValue" class="control-label">设定值 </label>
			                    <input type="text" class="form-control" id="paraValue" name="paraValue" />                 
		                  </div>
		                  <div class="col-md-6 margin-bottom-15">
		                  	<label for="notes" class="control-label">所属类型</label>
				                    <select class="form-control margin-bottom-15" name="paraType.id" id="paratypeInfo" >
				                    	<option value="">--请选择--</option>
				                    	<c:forEach var="frs" items="${sysCodeTypes}">
				                    		<option value="${frs.id}">${frs.paraType}</option>
				                    	</c:forEach>
				                    </select>
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
                <table class="table table-bordered table-striped table-hover data-table">
		                  <thead>
		                    <tr bgcolor="CFCFCF">
		                      <th width="8%">主键</th>
		                      <th width="10%">参数名</th>
		                      <th width="10%">参数值</th>
		                      <th width="10%">所属类型</th>
		                      <th width="10%">是否有效</th>
		                      <th width="10%">备注</th>
		                      <th width="16%">操作</th>
		                    </tr>
		                  </thead>
                    <tbody id="id_tbody_upd_list"> 
                    <c:forEach var="row" items="${sysCodes}">
                    	<!--  修改数据字典-->
	                    <tr id="upd_list_row_id_${row.id}" >
		                    <td>
		                    	<a href="javascript:edit('${row.id}','${row.paraCode}','${row.paraValue}','${row.paraType.id }','${row.isValid}','${row.notes}')" class="btn btn-link">${row.id}</a>
		                    </td>
		                    <td>${row.paraCode}</td>
		                    <td>${row.paraValue}</td>
		                    <td>${row.paraType.paraType}</td>
		                 	<td>${row.isValid=='0'?'否':'是'}</td>
		                    <td>${row.notes}</td>
		                    <td> <a href="javascript:remove('${row.id}')"  class="btn btn-link">删除</a>
		                    <c:if test="${row.isValid==1}">
					      		<a href="javascript:suspend('${row.id}')"  class="btn btn-link">停用</a> 
						    </c:if>
						      <c:if test="${row.isValid==0}">
						            <a href="javascript:activate('${row.id}')"  class="btn btn-link">启用</a>
						      </c:if> 
							</td>      		
	                    </tr>
                	</c:forEach>
               </table>
            </div>
          </div>
        </div>
       </div>
       
       
        <!-- 弹出窗口 新增数据字典内容-->
 	<div class="modal fade" id="modal_insert" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
        <div class="modal-dialog">
           <div class="modal-content">
            	<div class="modal-header">
             	  <button type="button" class="close" data-dismiss="modal"><span aria-hidden="true">&times;</span><span class="sr-only">Close</span></button>
              	  <h4 class="modal-title" >新增密钥算法</h4>
                </div>
          		<form action="insert.do" method="post">
            		<div class="modal-header">
            			<div class="form">
            				<table id="infoTable"  class="table table-striped table-hover table-bordered">
				            	 <c:if test="${message != null && message != ''}">               
				                    <div class="alert alert-danger alert-dismissible" role="alert">
				                      <button type="button" class="close" data-dismiss="alert"><span aria-hidden="true">&times;</span>
				                      <span class="sr-only">Close</span></button>
				                      <strong>${message}</strong> 
				                    </div>  
					             </c:if> 

				                <div class="row"> 
				                  <div class="col-md-6 margin-bottom-15">
				                    <label for="paraValue" class="control-label">参数名称 </label>
				                    <input type="text" class="form-control" id="paraCode" name="paraCode" required="required"/>                 
				                  </div>
				                  <div class="col-md-6 margin-bottom-15">
				                    <label for="notes" class="control-label">参数值</label>
				                    <input type="text" class="form-control" id="paraValue" name="paraValue" required="required"/>     
				                  </div>
				                </div>
				                
				                <div class="row">		            
				                  <div class="col-md-6 margin-bottom-15">
				                    <label for="notes" class="control-label">所属类型</label>
				                    <select class="form-control margin-bottom-15" name="paraType.id" id="paratypeInfo" required="required">
				                    	<option value="">--请选择--</option>
				                    	<c:forEach var="frs" items="${sysCodeTypes}">
				                    		<option value="${frs.id}">${frs.paraType}</option>
				                    	</c:forEach>
				                    </select>
				                    </div>
				                  <div class="col-md-6 margin-bottom-15">
				                    <label for="isValid" class="control-label">是否有效</label>
				                     <select class="form-control margin-bottom-15" name="isValid" id="isValid"  >
									      <option value="1"  >是</option>
									      <option value="0"  >否</option>
				                  	   </select>                 
				                  </div>
				                </div>
				                <div class="row">
				                  <div class="col-md-12 margin-bottom-15">
				                    <label for="notes" class="control-label">备注 </label>
				                    <textarea class="form-control" rows="3" id="notes" name="notes">${sysCode.notes}</textarea>
				                  </div>
				                </div>	
            				</table>
            			</div>          
			            <div class="modal-footer">
			            	<button type="submit" class="btn btn-primary">保存</button>
			             <button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
			            </div>
          			</div>
         		</form>
       		 </div>
     	 </div>     
      </div>
       
      <!-- 弹出窗口 ,修改数据字典内容-->
 	<div class="modal fade" id="modal_update" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
        <div class="modal-dialog">
          <div class="modal-content">
            <div class="modal-header">
              <button type="button" class="close" data-dismiss="modal"><span aria-hidden="true">&times;</span><span class="sr-only">Close</span></button>
              <h4 class="modal-title" >数据字典修改</h4>
            </div>
       
			<form action="update.do" method="post" >
           		 <div class="modal-header">
            		<div class="form">
            			<table id="id_cert_detail"  class="table table-striped table-hover table-bordered">
				             <c:if test="${success != null && message != ''}">               
					               <div class="alert alert-danger alert-dismissible" role="alert">
					                <button type="button" class="close" data-dismiss="alert"><span aria-hidden="true">&times;</span>
					                 <span class="sr-only">Close</span></button>
					                 <strong>${msg}</strong> 
					               </div>  
					           </c:if> 
				                <div class="row">
				                  <div class="col-md-6 margin-bottom-15">
				                    <label for="id" class="control-label">主键</label>
				                    <input type="number" min="0" class="form-control" name="id" id="id" readonly="readonly"  required="required">                  
				                  </div>
                  
				                  <div class="col-md-6 margin-bottom-15">
				                    <label for="paraValue" class="control-label">参数名称</label>
				                    <input type="text" class="form-control" id="paraCode" name="paraCode" required="required"/>                 
				                  </div>
				                </div>
				                
				                <div class="row">
				                  <div class="col-md-6 margin-bottom-15">
				                    <label for="notes" class="control-label">参数值</label>
				                    <input type="text" class="form-control" id="paraValue" name="paraValue" required="required"/>     
				                  </div>
				                  <div class="col-md-6 margin-bottom-15">
				                    <label for="notes" class="control-label">所属类型</label>
				                    <select class="form-control margin-bottom-15" name="paraType.id" id="paratypeInfo" required="required">
				                    	<option value="">--请选择--</option>
				                    	<c:forEach var="frs" items="${sysCodeTypes}">
				                    		<option value="${frs.id}">${frs.paraType}</option>
				                    	</c:forEach>
				                    </select>    
				                  </div>
				                </div>
                  
				                <div class="row">
				                  <div class="col-md-6 margin-bottom-15">
				                    <label for="isValid" class="control-label">是否有效</label>
				                     <select class="form-control margin-bottom-15" name="isValid" id="isValid"  >
									      <option value="1"  >是</option>
									      <option value="0"  >否</option>
				                  	   </select>                 
				                  </div>
				                </div>	
				                  <div class="row">
				                  <div class="col-md-12 margin-bottom-15">
				                    <label for="notes" class="control-label">备注 </label>
				                    <textarea class="form-control" rows="3" id="notes" name="notes"></textarea>
				                  </div>
				                </div>	
            				</table>
            			</div>          
			            <div class="modal-footer">
			            	<button type="submit" class="btn btn-primary">保存</button>
			             <button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
			            </div>
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
 
function remove(id){
   if (confirm("您确实要刪除该记录吗？")){
     self.location.replace("remove.do?&id="+id);
   }
}   

function suspend(id){
     self.location.replace("suspend.do?&id="+id);
}

function activate(id){
     self.location.replace("activate.do?&id="+id);
}

//修改的初始化页面            
function edit(id,paraCode,paraValue,paraType,isValid,notes){
			debugger;
			$("#modal_update input[name='id']").val(id);
			$("#modal_update input[name='paraCode']").val(paraCode);
			$("#modal_update input[name='paraValue']").val(paraValue);
			$("#modal_update select[name='paraType.id']").val(paraType);
			$("#modal_update input[name='isValue']").val(isValid);	
			$("#modal_update [id='notes']").val(notes);
			$("#modal_update").modal('show');
}

function add(){
	$("#modal_insert").modal('show');
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
 
<c:if test="${message != null && message != ''}">     
	<script type="text/javascript">
	 	$("#modal_insert").modal('show');
	</script>
</c:if> 
