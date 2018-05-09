<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@include file="/public/taglib.jsp" %>
<%@include file="/public/taglib_js.jsp" %>
<!DOCTYPE html>
<html>
<head>
<title></title>
<%@include file="/public/taglib_css.jsp" %>
<style type="text/css">
body{background-color: #fff;}
body>div{margin: 0 auto;}
</style>
</head>
<body>

<div id="content" class="container">

<div class="row">
<div class="col-xs-12">
	<input type="hidden" value="${id}" id="id"/>
    <form class="form-horizontal" id="f">
        
        <div class="form-group">
            <label for="name" class="col-xs-3 control-label">别名</label>
            <div class="col-xs-6">
                <input type="text" class="form-control"  id="name" name="name" placeholder="别名">
            </div>
        </div>
		<div class="form-group">
 			<label for="keyPairAlgorithmInfo" class="col-xs-3 control-label">密钥算法</label>
 			 <div class="col-xs-6">
	             <select class="form-control margin-bottom-15" name="keyPairAlgorithm.id" id="keyPairAlgorithmInfo">
	             	<option value="">--请选择--</option>
	             	<c:forEach var="kpgAlg" items="${keyPairAlgorithms}">
	             		<option value="${kpgAlg.id}">${kpgAlg.name}</option>
	             	</c:forEach>
	             </select>
	          </div>
        </div>
       <div class="form-group">
            <label for="kpgKeyAmount" class="col-xs-3 control-label">生成密钥数量</label>
            <div class="col-xs-6">
                <input type="text" class="form-control"  id="kpgKeyAmount" name="kpgKeyAmount" placeholder="生成密钥数量">
            </div>
        </div>
 		<div class="form-group">
			<label for="keyPairAlgorithmInfo" class="col-xs-3 control-label">密钥缓存数量</label>
			<div class="col-xs-6">
	           <select class="form-control margin-bottom-15" name="dbCommitBufsize.id" id="paratypeInfo" required="required">
		           	<option value="">--请选择--</option>
		           	<c:forEach var="scBuf" items="${codeBuf}">
		           		<option value="${scBuf.id}">${scBuf.paraValue}</option>
		           	</c:forEach>
	           </select>
	        </div>
        </div>
        <div class="form-group">
            <label for="taskNotes" class="col-xs-3 control-label">任务说明</label>
            <div class="col-xs-6">
                <textarea class="form-control"  id="taskNotes" name="taskNotes"></textarea>
            </div>
        </div>


		<div class="box-footer text-right">		   
		    <button type="button" class="btn btn-default" onclick="close_()">
		        <i class="fa fa-reply">&nbsp;取消</i>
		    </button>
		    <button type="button" id="save_btn" class="btn btn-primary" onclick="add()" >
		        <i class="fa fa-save">&nbsp;保存</i>
		    </button>
		</div>
		
    </form>
</div>
</div>
</div>
<script type="text/javascript">
$(function(){
	$('#f').validate({
		errorPlacement: function(error, element) {  //错误显示的位置
		    error.appendTo(element.parent().parent());  
		},
		errorElement: "label",
		success:function(label){
			label.html("<i class='fa  fa-check-circle fa iadd'></i>");
		},
		rules: {//验证规则
			name:{required:true},              
			algorithmOid:{required:true}, 
			algorithmName:{required:true},
			keysize:{required:true,number:true},
			isValid:{required:true},
			
		},
	 	messages: {//错误提示文字
	 		name: {required: "不能为空！"},
	 		algorithmOid: {required: "不能为空！"},
	 		algorithmName: {required:"不能为空！"},
	 		keysize:{required: "必须为数字"},
	 		isValid:{required: "不能为空！"},
	 	}
	});

});


//保存
function add(){
	var a  =  $("#f").valid();
	if(a==true){
		var data = $("#f").serialize(); 
		var url = "${ctx}/kpgTask/insert.do";
		$.ajax({
			type : "post",
			data : data,
			url : url,
			success:function(data){      
	    		zeroModal.success({
					content:data.des,
				    width: '30%',
				    height: '30%',
				    okFn: function() {
				    	parent.refreshframe();//关闭弹出窗口，并关闭父页面
			    		parent.window.show_div(data);//成功的回调方法
				    }
				});
			},
		    error:function(){ 
		    	zeroModal.success({
					content:data.des,
				    width: '30%',
				    height: '30%',
				    okFn: function() {
				    	parent.refreshframe();//关闭弹出窗口，并关闭父页面
			    		parent.window.show_div(data);//成功的回调方法
				    }
				});
		  	}
		}); 
	}if(a==false){
		alter("11111");
	}
	
}

//分级管理员选择组织结构
function org_show(value){
	if(value == 2){
		$("#org_id_div").show();
	}else{
		$("#org_id_div").hide();
	}
}
</script>
</body>
</html>