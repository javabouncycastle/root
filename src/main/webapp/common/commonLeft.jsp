<!DOCTYPE html>
<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<html lang="en">
	<head>
		<title>确信信息身份认证系统</title>
		<meta charset="UTF-8" />
        <meta name="viewport" content="width=device-width, initial-scale=1.0" />
		<%@include file="common.jsp" %>
	</head>
	<script type="text/javascript">
		function forward(pageName){
			var url="forward.do?page="+pageName;//跳转页面的路径
			$("#contentIframe").attr("src",url);//给iframe动态配置跳转的url
		}
	</script>
	<body>
		
		<div id="header">
			<h1></h1>		
		</div>
		
		<div id="sidebar">
			<ul>
				<li class="submenu">
					<a href="#"><i class="icon icon-th-list"></i> <span>密钥管理</span><span class="label"></span> </a>
					<ul>
						<li><a href="javascript:forward('keypair/keypairInsert')">密钥生成</a></li>
						<li><a href="javascript:forward('keypair/keypairList')">密钥查询</a></li>
					</ul>
				</li>
				<li class="submenu">
					<a href="#"><i class="icon icon-th-list"></i> <span>算法管理</span><span class="label"></span> </a>
					<ul>
						<!-- javascript:forward()跳转时，不去找对应的controller的路径，是根据javascript:forward()括号里边的路径跳转 -->
						<li><a href="<%=request.getContextPath()%>/algorithm/selectAll.do">密钥算法列表</a></li>
					</ul>
				</li>
				<li class="submenu">
					<a href="#"><i class="icon icon-th-list"></i> <span>日志管理</span> <span class="label"></span></a>
					<ul>
						<li><a href="form-common.html">Common elements</a></li>
						<li><a href="form-validation.html">Validation</a></li>
						<li><a href="form-wizard.html">Wizard</a></li>
					</ul>
				</li>
				<li class="submenu">
					<a href="#"><i class="icon icon-th-list"></i> <span>管理员管理</span> <span class="label">3</span></a>
					<ul>
						<li><a href="form-common.html">Common elements</a></li>
						<li><a href="form-validation.html">Validation</a></li>
						<li><a href="form-wizard.html">Wizard</a></li>
					</ul>
				</li>
			</ul>
		</div>
	</body>
</html>
