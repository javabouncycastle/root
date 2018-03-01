<%@ page contentType="text/html;charset=utf8" pageEncoding="utf8" %>
<%@ taglib prefix="c" uri="/WEB-INF/c-1_0-rt.tld"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<%=((HttpServletRequest)pageContext.getRequest()).getServletPath()%>
<!DOCTYPE html>
<head>
  <meta charset="utf-8">
  <title>确信身份认证系统-密钥管理模块</title>
  <meta name="keywords" content="" />
  <meta name="description" content="" />
  <meta name="viewport" content="width=device-width">        
  <link rel="stylesheet" href="<%=request.getContextPath()%>/css/templatemo_main.css">
  <link rel="stylesheet" href="<%=request.getContextPath()%>/css/uniform.css">
  <link rel="stylesheet" href="<%=request.getContextPath()%>/css/unicorn.main.css" type="text/css"></link>
</head>
<body>
  <div class="navbar navbar-inverse" role="navigation">
   <div class="templatemo-copyright">
      <div class="navbar-header">
        <div class="logo"><h1><font color="white" style='font-family:"微软雅黑"'>确信身份认证系统-密钥管理模块</font></h1></div>
      </div>   
      </div>
    </div>
    <script type="text/javascript" src="<%=request.getContextPath()%>/js/jquery-1.7.2.js"></script>
    <script type="text/javascript" src="<%=request.getContextPath()%>/js/jquery.min.js"></script>
    <script type="text/javascript" src="<%=request.getContextPath()%>/js/bootstrap.min.js"></script>
    <script type="text/javascript" src="<%=request.getContextPath()%>/js/templatemo_script.js"></script>
    <script type="text/javascript" src="<%=request.getContextPath()%>/js/WdatePicker.js"></script> 
	<script type="text/javascript" src="<%=request.getContextPath()%>/js/jquery.dataTables.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath()%>/js/unicorn.tables.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath()%>/js/jquery.dataTables.min.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath()%>/js/My97DatePicker/WdatePicker.js"></script> 
	<script type="text/javascript" src="<%=request.getContextPath()%>/js/select2.min.js"></script> 
