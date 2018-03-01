<%@ page contentType="text/html;charset=utf8" pageEncoding="utf8" %>
<%@ include file="WEB-INF/jsp/header.jsp" %>
<!DOCTYPE html>
 <body>
    <div class="template-page-wrapper" >
    	<div class="navbar-collapse collapse templatemo-sidebar" style="height:auto">
        	<ul class="templatemo-sidebar-menu">
          		<li>
            		<form class="navbar-form" name="templatemo_search_box_form" action="<%=request.getContextPath()%>/cert/certsearch/doCertSearch" method="post" >
              			<input type="text" name="cn" class="form-control" id="templatemo_search_box" placeholder="查询条件...">
             			<span class="btn btn-default" onclick="document.templatemo_search_box_form.submit();">Go</span>
            		</form>
          		</li>
          		<li>
          			<a href="<%=request.getContextPath()%>/main"><i class="fa fa-home"></i><font style='font-family:"微软雅黑"'>欢迎页面</font></a>
          		</li>
          		<li class="sub">
            		<a href="javascript:;">
              			<i class="fa fa-database"></i>
              			<font style='font-family:"微软雅黑"'>密钥管理</font>
              			<div class="pull-right"><span class="caret"></span>
              		</div>
            		</a>
            		<ul class="templatemo-submenu">
	              		<li>
	              			<a href="<%=request.getContextPath()%>/algorithm/selectAll.do"><font style='font-family:"微软雅黑"'>密钥算法列表</font></a>
	              		</li>
            		</ul>
          		</li>
          		<li class="sub">
            		<a href="javascript:;">
              			<i class="fa fa-database"></i>
              			<font style='font-family:"微软雅黑"'>系统参数</font>
              			<div class="pull-right"><span class="caret"></span>
              		</div>
            		</a>
            		<ul class="templatemo-submenu">
	              		<li>
	              			<a href="<%=request.getContextPath()%>/syscode/selectAll.do"><font style='font-family:"微软雅黑"'>数据字典列表</font></a>
	              		</li>
            		</ul>
          		</li>
         		<li class="sub">
		            <a href="javascript:;">
		              <i class="fa fa-database"></i><font style='font-family:"微软雅黑"'>日志管理</font><div class="pull-right"><span class="caret"></span></div>
		            </a>
		            <ul class="templatemo-submenu">
		              <li  ><a href="<%=request.getContextPath()%>/log/selectAll.do"><font style='font-family:"微软雅黑"'>日志列表</font></a></li>
		            </ul>               
          		</li>
            	<li class="sub">
		            <a href="javascript:;">
		              <i class="fa fa-database"></i><font style='font-family:"微软雅黑"'>管理员管理</font><div class="pull-right"><span class="caret"></span></div>
		            </a>
		            <ul class="templatemo-submenu">
		              <li  ><a href="<%=request.getContextPath()%>/conf/syscode/list"><font style='font-family:"微软雅黑"'>管理员列表</font></a></li>
		            </ul>               
         	   </li>
          	   <li>
          	   	   <a href="javascript:;" data-toggle="modal" data-target="#confirmModal"><i class="fa fa-sign-out"></i>Sign Out</a>
          	   </li>
        	</ul>
      	</div><!--/.navbar-collapse -->
   </div>
   <!-- Modal -->
   <div class="modal fade" id="confirmModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
     <div class="modal-dialog">
       <div class="modal-content">
         <div class="modal-header">
           <button type="button" class="close" data-dismiss="modal"><span aria-hidden="true">&times;</span><span class="sr-only">Close</span></button>
           <h4 class="modal-title" id="myModalLabel">您确定要退出系统吗?</h4>
         </div>
         <div class="modal-footer">
           <a href="../sign-in.html" class="btn btn-primary">确定</a>
           <button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
         </div>
       </div>
     </div>
   </div>
</body>