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
         <label for="id" class="col-xs-3 control-label">id</label>
            <div class="col-xs-6">
                <input type="text" class="form-control"  id="id" name="id" value="${certificateTemplate.id}" readonly="readonly">
        	</div>
    </div>
        
 	<div class="form-group">
       <label for="ctmlName" class="col-xs-3 control-label">模板名称</label>
            <div class="col-xs-6">
                <input type="text" class="form-control"  id="ctmlName" name="ctmlName"  value="${certificateTemplate.ctmlName}">
            </div>
        </div>
        <div class="form-group">
            <label for="keySize" class="col-xs-3 control-label">密钥长度</label>
            <div class="col-xs-6">
                <input type="text" class="form-control"  id="keySize" name="keySize" value="${certificateTemplate.keySize}">
            </div>
        </div>
        <div class="form-group">
            <label for="validity" class="col-xs-3 control-label">证书有效天数</label>
            <div class="col-xs-6">
                <input type="text" class="form-control"  id="validity" name="validity" value="${certificateTemplate.validity}">
            </div>
        </div>
        <div class="form-group">
            <label for="kpgAlgorithm" class="col-xs-3 control-label">密钥算法</label>
            <div class="col-xs-6">
            	<select id="kpgAlgorithm" name="kpgAlgorithm.id" class="form-control" onchange="org_show(value)">
	                	<c:forEach var="frs" items="${sysCodes}">
	                		<option value="${frs.id}">${certificateTemplate.id}</option>
	                		<option value="${frs.id}">${frs.paraValue}</option>
	                	</c:forEach>
            	</select>
            </div>
        </div>
        <div class="form-group">
            <label for="baseDn" class="col-xs-3 control-label">BaseDN</label>
            <div class="col-xs-6">
                <input type="text" class="form-control"  id="baseDn" name="baseDn" value="${certificateTemplate.baseDn}">
            </div>
        </div>
        <div class="form-group">
            <label for="hash" class="col-xs-3 control-label">HSAH算法</label>
            <div class="col-xs-6">
                <input type="text" class="form-control"  id="hash" name="hash" value="${certificateTemplate.hash}">
            </div>
        </div>
        <div class="form-group">
            <label for="reviewedType" class="col-xs-3 control-label">审核类型</label>
            <div class="col-xs-6">
            	<select id="reviewedType" name="reviewedType" class="form-control" onchange="org_show(value)">
					<option value="1">自动</option>
			      	<option value="0">手动</option>
            	</select>
            </div>
        </div>
        <div class="form-group">
            <label for="isValid" class="col-xs-3 control-label">是否有效</label>
            <div class="col-xs-6">
            	<select id="isValid" name="isValid" class="form-control" onchange="org_show(value)">
					<option value="1"  >是</option>
			      	<option value="0"  >否</option>
            	</select>
            </div>
        </div>
        <div class="form-group">
            <label for="paraType" class="col-xs-3 control-label">签名证书用法</label>
                <div class="col-xs-6">
	                 <label class="checkbox-inline" id="signCertKeyUsage">
                    	<c:forEach var="scsu" items="${sysCodeSignUse}">
                			<input type="checkbox" id="inlineCheckbox1" name="signCertKeyUsage" value="${scsu.id}">${scsu.paraCode}
                    	</c:forEach>
                  	 </label>
                </div>
        </div>
        <div class="form-group">
            <label for="paraType" class="col-xs-3 control-label">加密证书用法</label>
                <div class="col-xs-6">
	                 <label class="checkbox-inline" id="encCertKeyUsage" >
                    	<c:forEach var="sceu" items="${sysCodeEncUse}">
                    		<div class="col-xs-6" style="float:left"> 
	                    		<input type="checkbox" id="inlineCheckbox0" name="encCertKeyUsage" value="${sceu.id}">${sceu.paraCode}
	                    	</div> 
	                    </c:forEach>
                  	 </label>
                </div>
        </div>
        <div class="form-group">
            <label for="paraType" class="col-xs-3 control-label">证书拓展</label>
                <div class="col-xs-6">
	                 <label  id="eku" >
                    	<c:forEach var="sceu" items="${sysCodeExtend}">
                    		<div class="col-xs-6" style="float:left">
	                    		<input type="checkbox" id="inlineCheckbox1" name="eku" value="${sceu.id}">${sceu.paraCode}
	                    	</div>
	                    </c:forEach>
                  	 </label>
                </div>
        </div>
        <div class="form-group">
            <label for="paraType" class="col-xs-3 control-label">增强型密钥用法</label>
                <div class="col-xs-6">
	                 <label   id="certExtend" >
                    	<c:forEach var="sceu" items="${sysCodeExKeyUse}" >
                    	    <!-- <div class="col-xs-6" style="float:left"> -->
                    	    	<input type="checkbox" id="inlineCheckbox2" name="certExtend" value="${sceu.id}">${sceu.paraCode}
                    	   <!--  </div> -->
	                    </c:forEach>
                  	 </label>
                </div>
        </div>
        <div class="form-group">
            <label for="notes" class="col-xs-3 control-label">备注</label>
            <div class="col-xs-6">
                 <textarea class="form-control"  id="notes" name="notes"></textarea>
            </div>
        </div>



		<div class="box-footer text-right">		   
		    <button type="button" class="btn btn-default" onclick="close_()">
		        <i class="fa fa-reply">&nbsp;取消</i>
		    </button>
		    <button type="button" id="save_btn" class="btn btn-primary" onclick="update()" >
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
			paraCode:{required:true},    
			paraValue:{required:true}, 
			paraType:{required:true},
			isValid:{required:true},
		},
	 	messages: {//错误提示文字
	 		paraCode: {required: "不能为空！"},
	 		paraValue: {required: "不能为空！"},
	 		paraType:{required:true},
			isValid:{required:true},
	 	}
	}); 

});


//保存
function update(){
	var a  =  $("#f").valid();
	if(a==true){
		var data = $("#f").serialize(); 
		var url = "${ctx}/syscode/update.do";
		console.log(data);
		
		$.ajax({
			type : "post",
			data : data,
			url : url,
			dataType:"json",
			success:function(data){ 
				debugger;
				alert(data.returnCode);
				if(data.returnCode=="1"){
					zeroModal.success({
						content:data.des,
						content:url,
					    width: '30%',
					    height: '30%',
					    okFn: function() {
					    	parent.refreshframe();//关闭弹出窗口，并关闭父页面
				    		parent.window.show_div(data);//成功的回调方法
					    }
					});
				}else{
					zeroModal.error({
						content:data.des,
						content:url,
					    width: '30%',
					    height: '30%',
					    okFn: function() {
					    	parent.refreshframe();//关闭弹出窗口，并关闭父页面
				    		parent.window.show_div(data);//成功的回调方法
					    }
					});
				}
			 	},

		});
	}
}
</script>
</body>
</html>