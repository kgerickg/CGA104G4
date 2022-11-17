<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ page import="com.lucky.model.*"%>
<%-- 此頁暫練習採用 Script 的寫法取值 --%>

<%
  LuckyVO luckyVO = (LuckyVO) request.getAttribute("luckyVO"); //LuckyServlet.java(Concroller), 存入req的luckyVO物件
%>

<html>
<head>
<title>福袋資料 - listOneLucky.jsp</title>

<style>
  table#table-1 {
	background-color: #CCCCFF;
    border: 2px solid black;
    text-align: center;
  }
  table#table-1 h4 {
    color: red;
    display: block;
    margin-bottom: 1px;
  }
  h4 {
    color: blue;
    display: inline;
  }
</style>

<style>
  table {
	width: 600px;
	background-color: white;
	margin-top: 5px;
	margin-bottom: 5px;
  }
  table, th, td {
    border: 1px solid #CCCCFF;
  }
  th, td {
    padding: 5px;
    text-align: center;
  }
</style>

</head>
<body bgcolor='white'>

<h4>此頁暫練習採用 Script 的寫法取值:</h4>
<table id="table-1">
	<tr><td>
		 <h3>福袋資料 - ListOneLucky.jsp</h3>
		 <h4><a href="select_page.jsp"><img src="images/back1.gif" width="100" height="32" border="0">回首頁</a></h4>
	</td></tr>
</table>

<table>
	<tr>
		<th>福袋編號</th>
		<th>商家編號</th>
		<th>福袋狀態</th>
		<th>福袋名稱</th>
		<th>福袋內容</th>
		<th>福袋價格</th>
		<th>上架日期</th>
	</tr>
	<tr>
		<td><%=luckyVO.getLuckyId()%></td>
		<td><%=luckyVO.getStoreId()%></td>
		<td><%=luckyVO.getLkStat()%></td>
		<td><%=luckyVO.getLkName()%></td>
		<td><%=luckyVO.getLkCont()%></td>
		<td><%=luckyVO.getLkPrc()%></td>
		<td><%=luckyVO.getLkTime()%></td>
	</tr>
</table>

</body>
</html>