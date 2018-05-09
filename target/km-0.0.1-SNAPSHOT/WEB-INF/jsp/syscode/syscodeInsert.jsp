<%@ page contentType="text/html;charset=utf8" pageEncoding="utf8" %>
<%@ taglib prefix="c" uri="/WEB-INF/c-1_0-rt.tld"%>

</head>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<link rel="stylesheet" type="text/css" href="styles.css">
	
	<link rel="stylesheet" href="../css/rightTop.css" type="text/css"></link>
	<link rel="stylesheet" href="../css/rightBody.css" type="text/css"></link>
	
</head>

<body>
<!-- right上面部分 start -->
<table class="right-table1">
  <tr>
    <td class="right-td1">
    <table class="right-table2">
      <tr>
        <td width="1%" height="26" style="width:5px;">&nbsp;</td>
        <td width="99%" valign="bottom">
        <table class="right-table2">
          <tr>
            <td width="102" height="26" valign="bottom">
            <table class="right-table3">
              <tr>
                <td class="right-td3" align="center"><img src="../images/top/1.gif" width="9" height="9" /><span class="STYLE1">显示所有</span></td>
              </tr>
            </table></td>
            <td width="102"><table class="right-table3">
              <tr>
                <td class="right-td2" align="center"><a href=""><img src="../images/top/2_1.gif" width="9" height="9" /></a> <span class="STYLE3">增加</span></td>
              </tr>
            </table></td>
            <td width="102"><table class="right-table3">
              <tr>
                <td class="right-td2" align="center"><img src="../images/top/3.gif" width="9" height="9" /> <span class="STYLE1">删除</span></td>
              </tr>
            </table></td>
             <td width="102"><table class="right-table3">
              <tr>
                <td class="right-td2" align="center"><img src="../images/top/4.gif" width="9" height="9" /> <span class="STYLE1">修改</span></td>
              </tr>
            </table></td>
            <td>&nbsp;</td>
          </tr>
        </table></td>
      </tr>
    </table></td>
  </tr>
  <tr>
    <td background="../images/top/right_09.gif" style="line-height:5px;">&nbsp;</td>
  </tr>
</table>
<!-- right上面部分 end -->


<!-- rightBody start -->
<table class="right-body-table1">
  <tr>
    <td class="right-body-td1" class="STYLE3"><div align="center">数据字典列表</div></td>
  </tr>
</table>

<table class="right-body-table2  my_table">
 <tr height="30px">
	 <th width="8%">主键</th>
	 <th width="10%">参数名</th>
	 <th width="10%">参数值</th>
	 <th width="10%">所属类型</th>
	 <th width="10%">是否有效</th>
	 <th width="10%">备注</th>
	 <th width="16%">操作</th>
 </tr>
 <c:forEach var="row" items="${sysCodes}">
     <tr  class="right-body-tableLine right-body-table2 ">
      <td class="right-body-tdTableLine tr:nth-child(odd)" >${row.id}</td>
      <td class="right-body-tdTableLine" >${row.paraCode}</td>
      <td class="right-body-tdTableLine" > ${row.paraValue}</td>
      <td class="right-body-tdTableLine" >${row.paraType.paraType}</td>
   	  <td class="right-body-tdTableLine" >${row.isValid=='0'?'否':'是'}</td>
      <td class="right-body-tdTableLine" >${row.notes}</td>
      <td class="right-body-tdTableLine" > 
	      <a href="javascript:remove('${row.id}')"  class="btn btn-link">删除</a>
	      <c:if test="${row.isValid==1}">
			<a href="javascript:suspend('${row.id}')"  class="btn btn-link">停用</a> 
		  </c:if>
		  <c:if test="${row.isValid==0}">
		  	<a href="javascript:activate('${row.id}')"  class="btn btn-link">启用</a>
		  </c:if> 
	  </td>      		
     </tr>
 </c:forEach>
</table>

</body>

 <script type="text/javascript">
 
 //根据单双数选择背景颜色
 
 </script>