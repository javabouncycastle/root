<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@include file="/public/taglib.jsp" %>
<%@include file="/public/taglib_js.jsp" %>
<!DOCTYPE html>
<html>
<head>
<title></title>
<%@include file="/public/taglib_css.jsp" %>
<%-- <link href="${ctx }/AdminLTE-2.3.11/plugins/zTreeStyle/zTreeStyle.css" type="text/css" rel="stylesheet"/> --%>
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
                <input type="text" class="form-control"  id="id" name="id" value="${syscodeType.id}" readonly="readonly">
        </div>
        </div>
        <div class="form-group">
            <label for="name" class="col-xs-3 control-label">数据字典类别类型</label>
            <div class="col-xs-6">
                <input type="text" class="form-control"  id="paraType" name="paraType" value="${syscodeType.paraType}" >
            </div>
        </div>
        <div class="form-group">
            <label for="admin_name" class="col-xs-3 control-label">备注</label>
            <div class="col-xs-6">
                <textarea class="form-control"  id="notes" name="notes" >${syscodeType.notes}</textarea>
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
			paraType:{required:true},              
		},
	 	messages: {//错误提示文字
	 		paraType: {required: "不能为空！"},
	 	}
	}); 

});


//保存
function update(){
	var a  =  $("#f").valid();
	if(a==true){
		var data = $("#f").serialize(); 
		var url = "${ctx}/syscodetype/update.do";
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
	}else if(a==false){
		alter("11111");
	}
}
</script>
</body>
</html>