<%@ page language="java" import="java.util.*" pageEncoding="GB2312"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    <title>确信身份认证系统</title>
  </head>
  <frameset rows="90,*,30" cols="*" framespacing="0" frameborder="no" border="0">
	  <frame src="common/head.jsp" name="topFrame" scrolling="No" noresize="noresize" id="topFrame" />
	  <frame src="common/body.jsp" name="mainFrame" id="mainFrame" />
	  <frame src="common/footer.jsp" name="bottomFrame" scrolling="No" noresize="noresize" id="bottomFrame" />
  </frameset>
</html>
