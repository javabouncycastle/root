<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ include file="/public/taglib.jsp" %>
<%@ include file="/public/taglib_js.jsp" %>
<%@ include file="/public/taglib_css.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>syscodetype</title>
</head>
<body>
<div class="col-xs-12">
	 <div class="box-body ">
	    <table id="syscodetype_table"  class="table table-striped table-bordered table-hover">
	        <thead>
	        <tr>
	            <th><input type="checkbox" class="checkAll"/></th>
	            <th>主键</th>
                <th>参数类型</th>
                <th>备注</th>
                <th>操作</th>
	        </tr>
	        </thead>
	        <tbody>
	        </tbody>
	    </table>
	</div>			   		
  </div>
</body>
<script type="text/javascript" charset="utf-8">
$(function(){
	var flag = true;
	var data = ${sysCodeTypes};
	$("#syscodetype_table").dataTable({
		language : {url : '${ctx}/json/datatables/zh_CN.json'},//中文汉化
		order: [[ 1, 'asc' ]], //因为复选框一列会出现小箭头，所以改为第二列排序
		iDisplayLength : 10, //默认显示的记录数
		aLengthMenu : [10,50,100],//更改显示记录数选项
		autoWidth: false,
		dom: '<"top"f>rt<"floatleft"l><"floatleft"i><"floatright"p>',//页面布局
		data : data,
		drawCallback: function( settings ){ //表格绘制完成的回调函数
			if(flag){
				 var btnText='<button class="btn btn-success btnmargin" onclick=add()><i class="fa fa-plus"></i>&nbsp;添加数据字典类型</button>';					  
				     btnText = btnText +'<button class="btn  btn-warning btnmargin" onclick=edit()><i class="fa fa-pencil"></i>&nbsp;修改数据字典类型</button>';    
				    $(".dataTables_filter").prepend(btnText);
			}
			//翻页时，数据加载之后，去出全选
			$(".checkAll").prop("checked",false);
			icheck_(); //美化复选框 	
			flag = false;
			},
		columns : [
			{
				"sClass": "text-center",
			    "data": "id",
			    "render": function (data, type, full, meta) {
			    	 return '<input type="checkbox" class="checkChild"  value="' + data + '">';
			    },	
			   "bSortable": false //不显示排序按钮
			},
			{"data":"id"}, 
			{"data":"paraType"},
			{"data":"notes"},
			{
				"sClass": "text-center", //添加class
				"data":"id",
				"render": function (data, type, row, meta) {
					if (row.isValid == 0){
						return '<button type="button" class="btn btn-xs btn-danger" onclick=remove('+row.id+')>删除</button>';			  	 

					}else{
						return '<button type="button" class="btn btn-xs btn-danger" onclick=remove('+row.id+')>删除</button>';			  	 
					}
			    				    },
			    "bSortable": false //不显示排序按钮
			},
		] 
	});
	//单选
 	$("tbody").on("click","tr",function() {
 		var check_ = $(this).find("input[type=checkbox]").prop("checked");
 		if(!check_){
 			$(this).find("input").iCheck('check'); 
 	    }else{
 	    	$(this).find("input").iCheck('uncheck'); 
 	    }
 	});
	//多选
	$(document).on("ifChanged",'.checkAll',function(){
		//ifCreated 事件应该在插件初始化之前绑定 
		var check = $(this).prop("checked");
	    if(check){
	    	$('.checkChild').iCheck('check'); 
	    }else{
	    	$('.checkChild').iCheck('uncheck'); 
	    }
	});
	
});

function show(){
	var checked_row = $(".checkChild:checked");//获取选中的行
}

function remove(id){
	parent.window.show_div(id);
	parent.zeroModal.confirm("确定删除该算法吗？", function(){ 
		$.ajax({
		    url:'${ctx}/syscodetype/remove.do?id='+id,
		    type:'POST', //GET
		    async:true,    //或false,是否异步
		    dataType:'json',    //返回的数据格式：json/xml/html/script/jsonp/text
		    success:function(data){
		    	zeroModal.success({
					content:data.des,
				    width: '30%',
				    height: '30%',
				    okFn: function() {
				    	 location.reload();
				    }
					});
		    },
		    error:function(data){
		    	zeroModal.error(data.des);
		    }
		});
     });
}   

function add() {
	 zeroModal.show({
		title: '增加密钥算法',
		url: '${ctx}/syscodetype/forWardInsert.do',
		width: '70%',
		height: '40%',
		opacity: 1,
		}); 
	} 

function edit() {
	//首先获取选中的列
	var checked_row = $(".checkChild:checked");
	if(checked_row.length==0){
		//alert('请选择数据进行操作!');
		zeroModal.error('请选择数据进行操作!');
		return false;
	}
	if(checked_row.length>1){
		zeroModal.error("只能选择一行进行修改！");
		return false;
	}
	var id=checked_row[0].value;
    zeroModal.show({
        title: '修改密钥算法',
        url: '${ctx}/syscodetype/forWardUpdate.do?id='+id,
        width: '70%',
        height: '60%',
        opacity: 1
    });
}
//复选框
function icheck_(){
	//初始化复选框
	$('input[type="checkbox"]').iCheck({ 
		  labelHover : false, 
		  cursor : true, 
		  checkboxClass : 'icheckbox_square-blue', 
		  radioClass : 'iradio_square-blue', 
		  increaseArea : '20%' 
	}); 
}
</script>
</html>