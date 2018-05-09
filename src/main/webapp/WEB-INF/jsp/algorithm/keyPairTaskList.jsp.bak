<%@ page contentType="text/html;charset=utf8" pageEncoding="utf8" %>
<%@ taglib prefix="c" uri="/WEB-INF/c-1_0-rt.tld"%>
<%@ include file="../left.jsp" %>
<%@ include file="../footer.jsp" %>
<body>

        <div class="templatemo-content">
	          <ol class="breadcrumb">
	            <li><a href="<%=request.getContextPath()%>/main">主页面</a></li>
	            <li class="active">密钥任务</li>
	            <li><a href="../../sign-in.html">Sign In Form</a></li>
	             <li><a href="javascript:add()">增加密钥任务 / </a><a href="javascript:searchByCondition()"> 查询</a></li>
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
                <h4 class="margin-bottom-15">密钥任务列表</h4>
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
	                  	     <label for="name" class="control-label">别名</label>
			                 <input type="number" min="0" class="form-control" name="name" id="name"/>     
		                  </div>
		                </div>
		                  <div class="row">
		                  <div class="col-md-6 margin-bottom-15">
			                    <label for="KeyPairAlgorithm" class="control-label">密钥算法 </label>
			                    <input type="text" class="form-control" id="KeyPairAlgorithm" name="KeyPairAlgorithm" />                 
		                  </div>
		                  <div class="col-md-6 margin-bottom-15">
		                  	<label for="taskStartTime" class="control-label">创建时间 </label>
			                    <input type="text" class="form-control" id="taskStartTime" name="taskStartTime" />             
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
		                      <th width="10%">别名</th>
		                      <th width="10%">算法</th>
		                      <th width="10%">生成数量</th>
		                      <th width="10%">已生成数量</th>
		                      <th width="10%">任务状态</th>
		                      <th width="10%">任务新建时间</th>
		                      <th width="10%">任务说明</th>
		                      <th width="5">操作</th>
		                    </tr>
		                  </thead>
                    <tbody id="id_tbody_upd_list"> 
                    <c:forEach var="row" items="${kpgTasks}">
                    	<!--  修改密钥任务-->
	                    <tr id="upd_list_row_id_${row.id}" >
		                     <td>
		                    	<a href="javascript:edit('${row.id}','${row.name}','${row.keyPairAlgorithm.id}','${row.kpgKeyAmount}','${row.generatedKeyAmount}','${row.dbCommitBufsize.paraValue}','${row.taskStatus.paraValue}',
		                    	'${row.taskStartTime}','${row.exeTaskStartTime}', '${row.exeTaskEndTime}','${row.taskExeResult}','${row.taskNotes }')" class="btn btn-link">${row.id}</a>
		                    </td>
		                    <td>${row.name}</td>
		                    <td>${row.keyPairAlgorithm.name}</td>
		                    <td>${row.kpgKeyAmount}</td>
		                    <td>${row.generatedKeyAmount}</td>
		                    <td>${row.taskStatus.paraCode}</td>
		                    <td><fmt:formatDate value="${row.taskStartTime}" type="date" pattern="yyyy-MM-dd HH:mm:ss"/></td>
		                    <td>${row.taskNotes}</td>
		                    <td> <a href="javascript:remove('${row.id}')"  class="btn btn-link">删除</a>
		                    <c:if test="${row.taskStatus.paraValue==20}"><!-- 20状态为 任务新建完成未 -->
		                   		<a href="javascript:genKeypair('${row.id}')"  class="btn btn-link">启动</a>
		                    </c:if>
		                    <c:if test="${row.taskStatus.paraValue==22}">
		                    	<a href="javascript:suspend('${row.id}')"  class="btn btn-link">暂停</a><!-- 22 任务正在执行 -->
		                    	<a href="javascript:stop('${row.id}')"  class="btn btn-link">停止</a>
		                    </c:if>
		                    <c:if test="${row.taskStatus.paraValue==26}"><!-- 26人工暂停 -->
		                    	<a href="javascript:continute('${row.id}')"  class="btn btn-link">继续</a>
		                    </c:if>
							</td>      		
	                    </tr>
                	</c:forEach>
               </table>
            </div>
          </div>
        </div>
       </div>
       
       
        <!-- 弹出窗口 新增密钥任务内容-->
 	<div class="modal fade" id="modal_insert" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
        <div class="modal-dialog">
           <div class="modal-content">
            	<div class="modal-header">
             	  <button type="button" class="close" data-dismiss="modal"><span aria-hidden="true">&times;</span><span class="sr-only">Close</span></button>
              	  <h4 class="modal-title" >新增密钥任务</h4>
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
				                    <input type="text" class="form-control" id="name" name="name" required="required"/>                 
				                  </div>
				                  <div class="col-md-6 margin-bottom-15">
				                    <label for="notes" class="control-label">密钥算法</label>
				                    <select class="form-control margin-bottom-15" name="keyPairAlgorithm.id" id="keyPairAlgorithmInfo" required="required">
				                    	<option value="">--请选择--</option>
				                    	<c:forEach var="kpgAlg" items="${keyPairAlgorithms}">
				                    		<option value="${kpgAlg.id}">${kpgAlg.algorithmName}</option>
				                    	</c:forEach>
				                    </select>
				                  </div>
				                </div>
				                
				                <div class="row">
				                
				                 <div class="col-md-6 margin-bottom-15">
				                    <label for="kpgKeyAmount" class="control-label">生成密钥数量 </label>
				                    <input type="text" class="form-control" id="kpgKeyAmount" name="kpgKeyAmount" value="${kpgTask.kpgKeyAmount}" required="required" />                 
				                  </div>
				                
				                  <div class="col-md-6 margin-bottom-15">
				                    <label for="kpgKeyAmount" class="control-label">缓存数量 </label>
				                    <select class="form-control margin-bottom-15" name="dbCommitBufsize.paraValue" id="paratypeInfo" required="required">
				                    	<option value="">--请选择--</option>
				                    	<c:forEach var="scBuf" items="${codeBuf}">
				                    		<option value="${scBuf.paraValue}">${scBuf.paraValue}</option>
				                    	</c:forEach>
				                    </select>
				                  </div>
				                </div>	
				                  
				                <div class="row">
				                  <div class="col-md-12 margin-bottom-15">
				                    <label for="notes" class="control-label">备注 </label>
				                    <textarea class="form-control" rows="3" id="notes" name="notes">${kpgTask.taskNotes}</textarea>
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
       
      <!-- 弹出窗口 ,修改密钥任务内容-->
 	<div class="modal fade" id="modal_update" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
        <div class="modal-dialog">
          <div class="modal-content">
            <div class="modal-header">
              <button type="button" class="close" data-dismiss="modal"><span aria-hidden="true">&times;</span><span class="sr-only">Close</span></button>
              <h4 class="modal-title" >密钥任务修改</h4>
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
				                    <label for="notes" class="control-label">算法</label>
				                    	<select class="form-control margin-bottom-15" name="keyPairAlgorithm.id" id="keyPairAlgorithmInfo" required="required">
					                    	<option value="">--请选择--</option>
						                    	<c:forEach var="kpgAlg" items="${keyPairAlgorithms}">
						                    		<option value="${kpgAlg.id}">${kpgAlg.algorithmName}</option>
						                    	</c:forEach>
				                   	   </select>
				                  </div>
				                  <div class="col-md-6 margin-bottom-15">
				                    <label for="kpgKeyAmount" class="control-label">总生成数量</label>
				                    <input type="text" class="form-control" id="kpgKeyAmount" name="kpgKeyAmount" required="required" />                 
				                  </div>
				                </div>	
                  
				                <div class="row">
				                  <div class="col-md-6 margin-bottom-15">
				                    <label for="generatedKeyAmount" class="control-label">已生成数量</label>
				                    <input type="text" class="form-control" id="generatedKeyAmount" name="generatedKeyAmount" required="required" />                 
				                  </div>
				                   <div class="col-md-6 margin-bottom-15">
				                    <label for="color" class="control-label">缓存数量</label>
				                    <select class="form-control margin-bottom-15" name="dbCommitBufsize.paraValue" id="paratypeInfo" required="required">
				                    	<option value="">--请选择--</option>
				                    	<c:forEach var="sc" items="${sysCodes}">
				                    		<option value="${sc.paraValue}">${sc.paraCode}</option>
				                    	</c:forEach>
				                    </select>
				                  </div>					            
				                  <div class="col-md-6 margin-bottom-15">
				                    <label for="color" class="control-label">任务状态</label>
				                    <select class="form-control margin-bottom-15" name="taskStatus.paraValue" id="paratypeInfo" required="required">
				                    	<option value="">--请选择--</option>
				                    	<c:forEach var="sc" items="${sysCodes}">
				                    		<option value="${sc.paraValue}">${sc.paraCode}</option>
				                    	</c:forEach>
				                    </select>                
				                  </div>
				                </div>	
								<div class="row">
				                  <div class="col-md-6 margin-bottom-15">
				                    <label for="notes" class="control-label">任务新建时间</label>
				                    <input type="datetime" class="form-control" id="taskStartTime" name="taskStartTime"  required="required" />                 
				                       
				                  </div>
				                  <div class="col-md-6 margin-bottom-15">
				                    <label for="paraCode" class="control-label">执行开始时间</label>
				                    <input type="date" class="form-control" id="exeTaskStartTime" onfocus="WdatePicker({skin:'default',dateFmt:'yyyy-MM-dd'})" class="MyWdate" name="exeTaskStartTime"  required="required" />                 
				                  </div>
				                </div>	 
				                <div class="row">
				                  <div class="col-md-6 margin-bottom-15">
				                    <label for="notes" class="control-label">执行结束时间</label>
				                    <input type="date" class="form-control" id="exeTaskEndTime" onfocus="WdatePicker({skin:'default',dateFmt:'yyyy-MM-dd '})" class="MyWdate" name="exeTaskEndTime"  required="required" />                 
				                  </div>
				                  <div class="col-md-6 margin-bottom-15">
				                    <label for="paraCode" class="control-label">任务结果</label>
				                    <input type="text" class="form-control" id="taskExeResult" name="taskExeResult"  />                 
				                  </div>
				                </div>
				                <div class="row">
				                  <div class="col-md-12 margin-bottom-15">
				                    <label for="notes" class="control-label">任务说明 </label>
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
//修改的初始化页面            
function edit(id,name,keyPairAlgorithm,kpgKeyAmount,generatedKeyAmount,dbCommitBufsize,paraValue,taskStartTime,exeTaskStartTime,exeTaskEndTime,taskExeResult,taskNotes){
			$("#modal_update input[name='id']").val(id);
			$("#modal_update input[name='name']").val(name);	
			$("#modal_update select[name='keyPairAlgorithm.id']").val(keyPairAlgorithm);	
			$("#modal_update input[name='kpgKeyAmount']").val(kpgKeyAmount);
		 	$("#modal_update input[name='generatedKeyAmount']").val(generatedKeyAmount);
		 	$("#modal_update input[name='dbCommitBufsize']").val(dbCommitBufsize);
			$("#modal_update select[name='taskStatus.paraValue']").val(paraValue);	
			$("#modal_update input[name='taskStartTime']").val(FormatDate(taskStartTime));
			$("#modal_update input[name='exeTaskStartTime']").val(FormatDate(exeTaskStartTime));
			$("#modal_update input[name='exeTaskEndTime']").val(FormatDate(exeTaskEndTime));
			$("#modal_update input[name='taskExeResult']").val(taskExeResult);	
			$("#modal_update [id='taskNotes']").text(taskNotes);
			$("#modal_update").modal('show');
}

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
function add(){
	$("#modal_insert").modal('show');
}

function genKeypair(id){
	window.location.href="start.do?&id="+id;
}
function suspend(id){
	window.location.href="suspend.do?&id="+id;
}
function stop(id){
	window.location.href="stop.do?&id="+id;
}
function continute(id){
	window.location.href="continute.do?&id="+id;
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