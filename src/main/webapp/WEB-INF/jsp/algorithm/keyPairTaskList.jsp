<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ include file="/public/taglib.jsp" %>
<%@ include file="/public/taglib_js.jsp" %>
<%@ include file="/public/taglib_css.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>kpgTask</title>
</head>
<body>
<div class="col-xs-12">
	 <div class="box-body ">
	    <table id="kpgTasks_table"  class="table table-striped table-bordered table-hover">
	        <thead>
	        <tr>
	            <th><input type="checkbox" class="checkAll"/></th>
	            <th>主键</th>
                <th>别名</th>
                <th>算法</th>
                <th>生成数量</th>
                <th>已生成数量</th>
                <th>任务状态</th>
                <th>任务新建时间</th>
                <th>任务说明</th>
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
	$("#kpgTasks_table").dataTable({
		serverSide: true,//如果是服务器方式，必须要设置为true
		language : {url : '${ctx}/json/datatables/zh_CN.json'},//中文汉化
		order: [[ 1, 'asc' ]], //因为复选框一列会出现小箭头，所以改为第二列排序
		iDisplayLength : 10, //默认显示的记录数
		aLengthMenu : [10,50,100],//更改显示记录数选项
		autoWidth: false,
		dom: '<"top"f>rt<"floatleft"l><"floatleft"i><"floatright"p>',//页面布局
		//data : data,
		ajax:{
			url:"/kpgTask/selectAll.do", //请求数据url
			typy:"post"
		},
		paginationType:"full_numbers",  //分页样式
		drawCallback: function( settings ){ //表格绘制完成的回调函数
			if(flag){
				 var btnText='<button class="btn btn-success btnmargin" onclick=add()><i class="fa fa-plus"></i>&nbsp;添加密钥任务</button>';					  
				     btnText = btnText +'<button class="btn  btn-warning btnmargin" onclick=edit()><i class="fa fa-pencil"></i>&nbsp;修改密钥任务</button>';    
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
			{"data":"name"},
			{"data":"keyPairAlgorithm.name"},
			{"data":"kpgKeyAmount"},
			{"data":"generatedKeyAmount"},
			{"data":"taskStatus.paraCode"},
			/*<td><fmt:formatDate value="${row.taskStartTime}" type="date" pattern="yyyy-MM-dd HH:mm:ss"/></td> */
			{"data":"taskStartTime"}, 
			{"data":"taskNotes"},
			{
				"sClass": "text-center", //添加class
				"data":"id",
				"render": function (data, type, row, meta) {
					if (row.taskStatus.paraValue == '20'){
						return '<button type="button" class="btn btn-xs btn-danger" onclick=remove('+row.id+')>删除</button><button type="button" class="btn btn-xs btn-warning" onclick=start('+row.id+')>启动</button>';			  	 
					}if(row.taskStatus.paraValue == '22'){
						return '<button type="button" class="btn btn-xs btn-danger" onclick=remove('+row.id+')>删除</button><button type="button" class="btn btn-xs btn-warning" onclick=stop('+row.id+')>暂停</button>';			  	 
					}if(row.taskStatus.paraValue == '26'){
						return '<button type="button" class="btn btn-xs btn-danger" onclick=remove('+row.id+')>删除</button><button type="button" class="btn btn-xs btn-warning" onclick=continuation('+row.id+')>继续</button>';
					}if(row.taskStatus.paraValue == '24'){
						return '<button type="button" class="btn btn-xs btn-danger" onclick=remove('+row.id+')>删除</button>';
					}if(row.taskStatus.paraValue == '27'){
						return '<button type="button" class="btn btn-xs btn-danger" onclick=remove('+row.id+')>删除</button><button type="button" class="btn btn-xs btn-warning" onclick=stop('+row.id+')>暂停</button>';	
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
	parent.zeroModal.confirm("确定删除密钥任务吗？", function(){ 
		$.ajax({
		    url:'${ctx}/kpgTask/remove.do?id='+id,
		    type:'POST', //GET
		    async:true,    //或false,是否异步
		    dataType:'json',    //返回的数据格式：json/xml/html/script/jsonp/text
		    success:function(data){
		    	zeroModal.success({
					content:data.des,
				    width: '30%',
				    height: '30%',
				    okFn: function() {
				    	$("#kpgTasks_table").DataTable().draw(false);
				    }
					});
		    },
		    error:function(data){
		    	zeroModal.error(data.des)({
					content:data.des,
				    width: '30%',
				    height: '30%',
				    okFn: function() {
				    	$("#kpgTasks_table").DataTable().draw(false);
				    }
					});
		    }
		});
     });
}   

function start(id){
	$.ajax({
		url:'${ctx}/kpgTask/startTask.do?id='+id,
	    type:'POST', //GET
	    async:true,    //或false,是否异步
	    dataType:'json',    //返回的数据格式：json/xml/html/script/jsonp/text
	    success:function(data){
	    	zeroModal.success({
				content:data.des,
			    width: '30%',
			    height: '30%',
			    okFn: function() {
			    	$("#kpgTasks_table").DataTable().draw(false);
			    }
				});
	    },
	});
}
function stop(id){
	$.ajax({
		url:'${ctx}/kpgTask/stop.do?id='+id,
	    type:'POST', //GET
	    async:true,    //或false,是否异步
	    dataType:'json',    //返回的数据格式：json/xml/html/script/jsonp/text
	    success:function(data){
	    	zeroModal.success({
				content:data.des,
			    width: '30%',
			    height: '30%',
			    okFn: function() {
			    	$("#kpgTasks_table").DataTable().draw(false);
			    }
				});
	    },
	});
}

function continuation(id){
	$.ajax({
		url:'${ctx}/kpgTask/continuation.do?id='+id,
	    type:'POST', //GET
	    async:true,    //或false,是否异步
	    dataType:'json',    //返回的数据格式：json/xml/html/script/jsonp/text
	    success:function(data){
	    	zeroModal.success({
				content:data.des,
			    width: '30%',
			    height: '30%',
			    okFn: function() {
			    	$("#kpgTasks_table").DataTable().draw(false);
			    }
				});
	    },
	});
}

function add() {
	 zeroModal.show({
		title: '增加数据字典',
		url: '${ctx}/kpgTask/forWardInsert.do',
		width: '70%',
		height: '50%',
		opacity: 1,
		okFn: function() {
	    	$("#kpgTasks_table").DataTable().draw(false);
	    }
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
        url: '${ctx}/kpgTask/forWardUpdate.do?id='+id,
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