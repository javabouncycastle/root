<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ include file="/public/taglib.jsp" %>
<!DOCTYPE html>
<html>
<head>
  <meta charset="utf-8">
  <meta http-equiv="X-UA-Compatible" content="IE=edge">
<!-- 引入css -->
<%@ include file="/public/taglib_css.jsp" %>
<!-- 引入js -->
<%@ include file="/public/taglib_js.jsp" %>
<title>${title}</title>
<!-- Tell the browser to be responsive to screen width -->
<meta content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no" name="viewport">
</head>
<body class="hold-transition skin-blue-light sidebar-mini">
<div class="wrapper">
<div id="test_div" style="display:none;z-index:99999;height:auto;width:100px;margin-left: 45%;position:absolute;top:5%;border: 1px red solid;text-align:center;">成功</div>
<!-- 顶部导航 -->
<%@ include file="WEB-INF/jsp/index/index_top.jsp" %>
<!-- 左侧菜单 -->
<%@ include file="WEB-INF/jsp/index/index_left_menu.jsp" %>
<!--选项卡插件-->
<script src="${pageContext.request.contextPath}/js/b.tabs.js"></script>
<script src="${pageContext.request.contextPath}/js/bTabs-style.js"></script>
<!-- Content -->
<div class="content-wrapper">
<!--需要选项卡-->
	<div class="col-md-12" id="mainFrameTabs" style="padding:0px;margin:0px;">  
    	<ul class="nav nav-tabs" role="tablist">
     	 	<li role="presentation" class="active noclose index"><a href="#bTabs_navTabsMainPage" data-toggle="tab" style="padding:6px 15px !important;">首页</a></li>
    	</ul>
    		<div class="tab-content" style="position:relative;">
     			<div class="tab-pane active"  id="bTabs_navTabsMainPage" style="background-color:#FFFFFF;overflow:auto">
     				<%@ include file="WEB-INF/jsp/index/index_content.jsp" %>
       			</div>
     		</div>
    	</div>
	</div>
</div>
<script type="text/javascript" charset="utf-8">
function show_div(content){
	$("#test_div").html(content);
	setTimeout(function(){
		$("#test_div").fadeIn(1000);
		$("#test_div").fadeOut(2000);
	},100);
}
//关闭Zeromodal弹出框iframe，刷新bTabs页iframe
function refreshframe(){
	closeZeromodal();
	window.frames[$(".tab-content > .active").index()-1].location.reload();
}
//关闭弹窗
function closeZeromodal(){
	$(".zeromodal-close").trigger("click");
}

</script>
</body>
</html>
