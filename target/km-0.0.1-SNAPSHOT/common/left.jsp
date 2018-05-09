<%@ page contentType="text/html;charset=utf8" pageEncoding="utf8" %>
<%@ taglib prefix="c" uri="/WEB-INF/c-1_0-rt.tld"%>

</head>
<style type="text/css">
<!--
body {
	margin-left: 0px;
	margin-top: 0px;
	margin-right: 0px;
	margin-bottom: 0px;
}
.STYLE1 {
	color: #FFFFFF;
	font-weight: bold;
	font-size: 12px;
}
.STYLE2 {
	font-size: 12px;
	color: #03515d;
}
a:link {font-size:12px; text-decoration:none; color:#03515d;}
a:visited{font-size:12px; text-decoration:none; color:#03515d;}
-->
</style>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<link rel="stylesheet" type="text/css" href="styles.css">
	<link rel="stylesheet" href="../css/left.css" type="text/css"></link>
</head>

  
<body>
<table class="left-table1">
  <tr>
    <td class="left-td1 left-td5" ><table class="left-table4">
      <tr>
        <td height="33"  background="../images/top/main_21.gif"></td>
      </tr>
      <tr>
        <td class="left-td6" background="../images/top/main_25.gif"><table class="left-table5">
          <tr>
            <td class="left-td6 left-td7"><div align="center">
              <table class="left-table8">
                <tr>
                  <td><div align="center"><img src="../images/top/top_8.gif" width="16" height="16"></div></td>
                  <td valign="middle"><div align="center" class="STYLE1">密钥管理</div></td>
                </tr>
              </table>
            </div></td>
          </tr>
        </table></td>
      </tr>
      <tr>
        <td align="center" valign="top"><table class="left-table6">
          
          <tr>
            <td><table class="left-table7">
                <tr>
                  <td class="left-td3 left-td4"><img src="../images/top/left_2.gif" width="31" height="31"></td>
                  <td width="89" height="35">
                  <table class="left-table5">
                    <tr>
                      <td height="23" >
                      <a href="<%=request.getContextPath()%>/keypairStandby/selectAll.do" target="x">密钥备用列表</a></td>
                    </tr>
                  </table></td>
                </tr>
                <tr>
                  <td class="left-td3 left-td4"><img src="../images/top/left_2.gif" width="31" height="31"></td>
                  <td height="35"><table class="left-table5">
                    <tr>
                      <td height="23" ><a href="<%=request.getContextPath()%>/keypairStandby/selectAll.do" target="x">密钥在用列表</a></span></td>
                    </tr>
                  </table></td>
                </tr>
                <tr>
                  <td class="left-td3 left-td4"><img src="../images/top/left_2.gif" width="31" height="31"></td>
                  <td height="35"><table class="left-table5">
                    <tr>
                      <td height="23" ><a href="#">密钥历史列表</a></span></td>
                    </tr>
                  </table></td>
                </tr>
            </table></td>
          </tr>
        </table></td>
      </tr>
      <tr>
        <td height="22" background="../images/top/main_36.gif"><table class="left-table5">
          <tr>
            <td class="left-td6 left-td7"><div align="center">
                <table class="left-table8">
                  <tr>
                    <td align="center"><img src="../images/top/top_17.gif" width="16" height="16"></td>
                    <td valign="middle"><div align="center"><span class="STYLE1">参数管理</span></div></td>
                  </tr>
                </table>
            </div></td>
            <td width="15%">&nbsp;</td>
          </tr>
        </table></td>
      </tr>
      <tr>
        <td align="center" valign="top">
        <table class="left-table6">
          
          <tr>
            <td><table class="left-table7">
                <tr>
                  <td class="left-td3 left-td4"><div align="center"><img src="../images/top/left_3.gif" width="31" height="31"></div></td>
                  <td height="35"><table class="left-table5">
                    <tr>
                      <td height="23" ><a href="<%=request.getContextPath()%>/syscode/selectAll.do" target="x">数据字典列表</a></td>
                    </tr>
                  </table></td>
                </tr>
                <tr>
                  <td class="left-td3 left-td4"><div align="center"><img src="../images/top/left_4.gif" width="31" height="31"></div></td>
                  <td height="35"><table class="left-table5">
                    <tr>
                      <td height="23"><a href="#">系统服务管理</a></td>
                    </tr>
                  </table></td>
                </tr>
            </table></td>
          </tr>
        </table></td>
      </tr>
      <tr>
        <td height="22" background="../images/top/main_36.gif"><table class="left-table5">
          <tr>
            <td class="left-td6 left-td7">
                <table class="left-table8">
                  <tr>
                    <td><div align="center"><img src="../images/top/top_16.gif" width="16" height="16"></div></td>
                    <td valign="middle"><div align="center">日志管理</div></td>
                  </tr>
                </table>
          </tr>
        </table></td>
      </tr>
           <tr>
        <td align="center" valign="top">
        <table class="left-table6">
          
          <tr>
            <td><table class="left-table7">
                <tr>
                  <td class="left-td3 left-td4"><div align="center"><img src="../images/top/left_4.gif" width="31" height="31"></div></td>
                  <td height="35"><table class="left-table5">
                    <tr>
                      <td height="23"><a href="#">授权导入</a></td>
                    </tr>
                  </table></td>
                </tr>
            </table></td>
          </tr>
        </table></td>
      </tr>
      <tr>
        <td height="22" background="../images/top/main_36.gif">
        <table class="left-table5">
          <tr>
            <td width="72%" height="20"><div align="center">
                <table class="left-table8">
                  <tr>
                    <td align="center"><img src="../images/top/top_18.gif" width="16" height="16"></td>
                    <td valign="middle" align="center" >退出</td>
                  </tr>
                </table>
            </div></td>
          </tr>
        </table>
      </tr>
      <tr>
        <td height="39" background="../images/top/main_37.png"></td>
      </tr>
    </table></td>
  </tr>
</table>
</body>
</html>