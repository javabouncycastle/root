<%@ page contentType="text/html;charset=utf8" pageEncoding="utf8" %>
<%@ taglib prefix="c" uri="/WEB-INF/c-1_0-rt.tld"%>
<!DOCTYPE html>
<%@ include file="../left.jsp" %>
<%@ include file="../footer.jsp" %>
<body>

        <div class="templatemo-content">
	          <ol class="breadcrumb">
	            <li><a href="<%=request.getContextPath()%>/main">主页面</a></li>
	            <li class="active">密钥算法容</li>
	            <li><a href="../../sign-in.html">Sign In Form</a></li>
	             <li><a href="javascript:add()">增加密钥算法 / </a><a href="javascript:searchByCondition()"> 查询</a></li>
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
                <h4 class="margin-bottom-15">密钥算法列表</h4>
                <!-- 查询条件div -->
                 <div class="row" id="searchCondition" style="display:none">
		            <div class="col-md-12">
		              <form id="templatemo-preferences-form" action="searchByCondition.do" method="post" >
		                <div class="row">
	                	  <div class="col-md-6 margin-bottom-15">
	                  	     <label for="id" class="control-label">主键标识</label>
			                 <input type="number" min="0" class="form-control" name="id" id="id"/>     
		                  </div>
		                   <div class="col-md-6 margin-bottom-15">
			                    <label for="name" class="control-label">别名 </label>
			                    <input type="text" class="form-control" id="name" name="name" />                 
		                  </div>
		                </div>
		                  <div class="row">
		                  <div class="col-md-6 margin-bottom-15">
			                    <label for="algorithmOid" class="control-label">算法OID </label>
			                    <input type="text" class="form-control" id="algorithmOid" name="algorithmOid" />                 
		                  </div>
		                  <div class="col-md-6 margin-bottom-15">
		                  	<label for="keysize" class="control-label">密钥长度 </label>
			                    <input type="text" class="form-control" id="keysize" name="keysize" />           
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
		                      <th width="8%">主键</th>
		                      <th width="10%">别名</th>
		                      <th width="10%">算法OID</th>
		                      <th width="10%">算法英文缩写</th>
		                      <th width="10%">密钥长度</th>
		                      <th width="8%">是否有效</th>
		                      <th width="10%">备注</th>
		                      <th width="16%">操作</th>
		                    </tr>
		                  </thead>
                    <tbody id="id_tbody_upd_list"> 
                    <c:forEach var="row" items="${keyPairAlgorithms}">
                    	<!--  修改密钥算法-->
	                    <tr id="upd_list_row_id_${row.id}" >
		                    <td>
		                    	<a href="javascript:edit('${row.id}','${row.name}','${row.algorithmOid}','${row.algorithmName}','${row.keysize}','${row.notes}','${row.isValid}')" class="btn btn-link">${row.id}</a>
		                    </td>
		                    <td>${row.name}</td>
		                    <td>${row.algorithmOid}</td>
		                    <td>${row.algorithmName}</td>
		                    <td>${row.keysize}</td>
		                    <td>${row.isValid}</td>
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
				            	 <c:if test="${messageInsert != null && messageInsert != ''}">               
				                    <div class="alert alert-danger alert-dismissible" role="alert">
				                      <button type="button" class="close" data-dismiss="alert"><span aria-hidden="true">&times;</span>
				                      <span class="sr-only">Close</span></button>
				                      <strong>${messageInsert}</strong> 
				                    </div>  
					            </c:if> 

				                <div class="row"> 
                  
				                  <div class="col-md-6 margin-bottom-15">
				                    <label for="paraValue" class="control-label">别名 </label>
				                    <input type="text" class="form-control" id="name" name="name" value="${keyPairAlgorithm.name}" required="required"/>                 
				                  </div>
				                  <div class="col-md-6 margin-bottom-15">
				                    <label for="notes" class="control-label">算法OID</label>
				                    <input type="text" class="form-control" id="algorithmOid" name="algorithmOid" value="${keyPairAlgorithm.algorithmOid}"  required="required"/>     
				                  </div>
				                </div>
				                
				                <div class="row">
				                  <div class="col-md-6 margin-bottom-15">
				                    <label for="paraCode" class="control-label">算法英文缩写 </label>
				                    <input type="text" class="form-control" id="algorithmName" name="algorithmName" value="${keyPairAlgorithm.algorithmName}" required="required" />                 
				                  </div>
				                  <div class="col-md-6 margin-bottom-15">
				                    <label for="color" class="control-label">密钥长度</label>
				                    <input type="text" class="form-control" id="keysize" name="keysize" value="${keyPairAlgorithm.keysize}" required="required" />                 
				                  </div>			
				                </div>	
                  
				                <div class="row">		            
				                  <div class="col-md-6 margin-bottom-15">
				                    <label for="isValid" class="control-label">是否有效</label>
				                     <select class="form-control margin-bottom-15" name="isValid" id="isValid" value="${keyPairAlgorithm.isValid}"  >
									      <option value="1"  >是</option>
									      <option value="0"  >否</option>
				                  	   </select>                 
				                  </div>
				                </div>
				                <div class="row">
				                  <div class="col-md-12 margin-bottom-15">
				                    <label for="notes" class="control-label">备注 </label>
				                    <textarea class="form-control" rows="3" id="notes" name="notes">${keyPairAlgorithm.notes}</textarea>
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
				             <c:if test="${updateSuccess != null && message != ''}">               
					               <div class="alert alert-danger alert-dismissible" role="alert">
						                <button type="button" class="close" data-dismiss="alert"><span aria-hidden="true">&times;</span>
							                 <span class="sr-only">Close</span></button>
							                 <strong>${message}</strong> 
					               </div>  
					           </c:if> 
				                <div class="row">
				                  <div class="col-md-6 margin-bottom-15">
				                    <label for="id" class="control-label">主键</label>
				                    <input type="number" min="0" class="form-control" name="id" id="id" readonly="true"  required="required">                  
				                  </div>
                  
				                  <div class="col-md-6 margin-bottom-15">
				                    <label for="paraValue" class="control-label">别名</label>
				                    <input type="text" class="form-control" id="name" name="name" required="required"/>                 
				                  </div>
				                </div>
				                
				                <div class="row">
				                  <div class="col-md-6 margin-bottom-15">
				                    <label for="notes" class="control-label">算法OID</label>
				                    <input type="text" class="form-control" id="algorithmOid" name="algorithmOid" required="required"/>     
				                  </div>
				                  <div class="col-md-6 margin-bottom-15">
				                    <label for="paraCode" class="control-label">算法英文缩写 </label>
				                    <input type="text" class="form-control" id="algorithmName" name="algorithmName" required="required" />                 
				                  </div>
				                </div>	
                  
				                <div class="row">
				                  <div class="col-md-6 margin-bottom-15">
				                    <label for="color" class="control-label">密钥长度</label>
				                    <input type="text" class="form-control" id="keysize" name="keysize" required="required" />                 
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
function edit(id,name,algorithmOid,algorithmName,keysize,notes,isValid){
			$("#modal_update input[name='id']").val(id);
			$("#modal_update input[name='name']").val(name);	
			$("#modal_update input[name='algorithmOid']").val(algorithmOid);	
			$("#modal_update input[name='algorithmName']").val(algorithmName);
			$("#modal_update input[name='keysize']").val(keysize);	
			$("#modal_update [id='notes']").val(notes);
			$("#modal_update input[name='isValid']").val(isValid);
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
 
<c:if test="${messageInsert != null && messageInsert != ''}">     
	<script type="text/javascript">
	 	$("#modal_insert").modal('show');
	</script>
</c:if> 