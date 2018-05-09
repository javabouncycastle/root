<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@include file="/public/taglib.jsp" %>
<!-- 人员管理_管理员管理 -->
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>添加管理员</title>
<%@include file="/public/taglib_css.jsp" %>
<link href="${ctx }/AdminLTE-2.3.11/plugins/zTreeStyle/zTreeStyle.css" type="text/css" rel="stylesheet"/>
<style type="text/css">
body{background-color: #fff;}
body>div{margin: 0 auto;}
</style>
</head>
<body>

<div id="content" class="container">

<div class="row">
<div class="col-xs-12">
	<input type="hidden" value="${orgRootId}" id="orgRootId"/>
    <form class="form-horizontal" id="f">
        <div class="form-group">
            <label for="login_name" class="col-xs-3 control-label">登录账号名称</label>
            <div class="col-xs-6">
                <input type="text" class="form-control"  id="login_name" name="login_name"  placeholder="登录账号名称">
            </div>
        </div>
        <div class="form-group">
            <label for="admin_name" class="col-xs-3 control-label">管理员姓名</label>
            <div class="col-xs-6">
                <input type="text" class="form-control"  id="admin_name" name="admin_name" placeholder="管理员姓名">
            </div>
        </div>
        <div class="form-group">
            <label for="admin_type" class="col-xs-3 control-label">管理员类型</label>
            <div class="col-xs-6">
            	<select id="admin_type" name="admin_type" class="form-control" onchange="org_show(value)">
    			<!-- 	<option value="0" style="display: none">超级管理员</option> -->
	    			<c:if test="${admin.admin_type == 0}">
	    				<option value="1">系统管理员</option>
	    			</c:if>			
    					<option value="2">分级管理员</option>
    				<c:if test="${admin.admin_type == 0}">
    					<option value="3">日志操作员</option>
    				</c:if>
            	</select>
            </div>
        </div>
        <div class="form-group" style="display:none;" id="org_id_div">
            <label for="org_id" class="col-xs-3 control-label">组织机构</label>
            <div class="col-xs-6">
             <input type="text" readonly="readonly" class="form-control" style="background-color: #fff" id="org_name" name="org_name"  placeholder="组织机构"/>   
             <input type="hidden" id = "org_id" name="org_id"/>        
				<div class="box-body box-profile" id="org_id_tree" style="display: none;float: left;
							position:absolute;  z-index:1;border: 1px solid #3C8DBC; background-color: white;">
						<ul id="tree" class="ztree"></ul>
				</div>       	
            </div>
        </div>       
       <%--  <div class="form-group">
            <label for="cert_id" class="col-xs-3 control-label" >用户证书id</label>
            <div class="col-xs-6">
                <select class="form-control"id="cert_id" name="cert_id">             
	               	<c:forEach items="${userCert}" var="userCert">
	               		<option value="${userCert.id}">${userCert.id}</option>
	               	</c:forEach>
                </select>
            </div>
        </div> --%>
        <div class="form-group">
            <label for="idcard_num" class="col-xs-3 control-label">身份证号</label>
            <div class="col-xs-6">
                <input type="text" class="form-control"  id="idcard_num" name="idcard_num" placeholder="身份证号">
            </div>
        </div>
        <div class="form-group">
            <label for="admin_email" class="col-xs-3 control-label">管理员email</label>
            <div class="col-xs-6">
                <input type="text" class="form-control"  id="admin_email" name="admin_email" placeholder="管理员email">
            </div>
        </div>
        <div class="form-group">
            <label for="admin_phone" class="col-xs-3 control-label">管理员手机</label>
            <div class="col-xs-6">
                <input type="text" class="form-control"  id="admin_phone" name="admin_phone" placeholder="管理员手机">
            </div>
        </div>
        <input  type="hidden" value="1" name="access"/>
        <!-- <div class="form-group">
            <label for="access" class="col-xs-3 control-label">访问权限</label>
            <div class="col-xs-6">
                <input type="text" class="form-control"  id="access" name="access" placeholder="访问权限">
            </div>
        </div> -->
      <%--   <div class="form-group">
            <label for="key_id" class="col-xs-3 control-label">key序列号</label>
            <div class="col-xs-6">
            	<select class="form-control" id="key_id" name="key_id" >
	            	<c:forEach items="${userkey}" var="key">
	            		<option value="${key.key_id }">${key.key_id }</option>
	            	</c:forEach>
            	</select>          
            </div>
        </div> --%>
       <%--  <div class="form-group">
            <label for="vcert_sn" class="col-xs-3 control-label" >签名证书sn</label>          
            <div class="col-xs-6">
               	<select class="form-control"  id="vcert_sn" name="vcert_sn">
	               	<c:forEach items="${userCert}" var="userCert">
	               		<option value="${userCert.vcert_sn}">${userCert.vcert_sn}</option>
	               	</c:forEach>		
               	</select>
            </div>
        </div> --%>
        <div class="form-group">
            <label for="remark" class="col-xs-3 control-label">备注</label>
            <div class="col-xs-6">
                <input type="text" class="form-control"  id="remark" name="remark" placeholder="备注">
            </div>
        </div>
		<div class="box-footer text-right">		   
		    <button type="button" class="btn btn-default" onclick="close_()">
		        <i class="fa fa-reply">&nbsp;取消</i>
		    </button>
		    <button type="button" id="save_admin_btn" class="btn btn-primary" onclick="save_admin()" >
		        <i class="fa fa-save">&nbsp;保存</i>
		    </button>
		</div>
		
    </form>
</div>
</div>
</div>
<%@include file="/public/taglib_js.jsp" %>
<input type="hidden" id="org_list_json" value='${org_list_json}'/>
<script src="${ctx}/js/proc-form.js"></script>
<script type="text/javascript" src="${ctx }/AdminLTE-2.3.11/plugins/zTreeStyle/jquery.ztree.all.js"></script>
<script type="text/javascript" src="${ctx }/jsp/user/org_tree.js"></script>
<script type="text/javascript">
$(function(){
	//选择默认的组织机构
	if("${admin.admin_type}" == 1){
		$("#org_id_div").show();
	};

	$('#f').validate({
		errorPlacement: function(error, element) {  //错误显示的位置
		    error.appendTo(element.parent().parent());  
		},
		errorElement: "label",
		success:function(label){
			label.html("<i class='fa  fa-check-circle fa iadd'></i>");
		},
		rules: {//验证规则
			admin_name:{required:true},              
			admin_type:{required:true}, 
			idcard_num:{idcard:true},
			admin_email:{email:true},
			admin_phone:{phone:true},
			login_name:{
				required:true,
				remote:{
					type:"post",
					url :"${ctx}/user_admin/exist_admin_name.html",
					dataType:"text",
					data:{
						login_name: function() {
                            return $("#login_name").val();    
                        }
					},
					dataFilter: function(data) { //返回结果
                        if (data != "1")
                            return true;
                        else
                            return false;
                    }
				}
			}
		},
	 	messages: {//错误提示文字
	 		admin_name: {required: "不能为空！"},
	 		admin_type: {required: "不能为空！"},
	 		idcard_num: {idcard:"格式不正确！"},
	 		admin_email:{email:"格式不正确！"},
	 		admin_phone:{phone:"格式不正确！"},
	 		login_name:{required:"不能为空！",remote:"登录名已存在！"},
	 	}
	});
	// 判断身份证正格式 
    jQuery.validator.addMethod("idcard", function(value, element) {      
         return this.optional(element) || /(^\d{15}$)|(^\d{18}$)|(^\d{17}(\d|X|x)$)/.test(value);       
    });
	// 判断身份证正格式 
    jQuery.validator.addMethod("phone", function(value, element) {      
         return this.optional(element) || /^(1\d{10})$/.test(value);       
    });
});

function addlabel(id,text){
	  var warnlab='<label class="lab" style="padding-top:7px;"></label>';
	  $('[name='+id+']').parents('.form-group').append(warnlab).addClass('has-error').find('.lab').text(text);
	}
//非空判断
function isNull(value){
	if(value == null || value == ""){
		return true;
	}
	return false;
}
function check(){
	$('.form-group').removeClass('has-error').find('.lab').remove();
	if($("#admin_type").val() ==  2){
		var orgId = $("#org_id").val();
		if(isNull(orgId)){
			addlabel("org_name","不能为空！");
			return false;
		}
	}
	return true;
}


//保存
function save_admin(){
	 if($("#f").valid() && check()){
		$("#save_admin_btn").prop("disabled",true);
		save("${ctx}/user_admin/add_admin.html","添加");	
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