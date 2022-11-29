<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.lucky.model.*"%>

<html>
<head>
<title>所有福袋資料 - listAllLucky.jsp</title>

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
	width: 800px;
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

<h4>此頁練習採用 EL 的寫法取值:</h4>
<table id="table-1">
	<tr><td>
		 <h3>所有福袋資料 - listAllLucky.jsp</h3>
		 <h4><a href="<%=request.getContextPath()%>/front-lucky/select_page.jsp"><img src="<%=request.getContextPath()%>/front-lucky/images/back1.gif" width="100" height="32" border="0">回首頁</a></h4>
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
		<th>修改</th>
		<th>刪除</th>
	</tr>
	<c:forEach var="luckyVO" items="${list}">
		<tr>
			<td>${luckyVO.luckyId}</td>
			<td>${luckyVO.storeId}</td>
			<td>${luckyVO.lkStat}</td>
			<td>${luckyVO.lkName}</td>
			<td>${luckyVO.lkCont}</td>
			<td>${luckyVO.lkPrc}</td> 
			<td>${luckyVO.lkTime}</td>
			<td>
			  <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/lucky/lucky.do" style="margin-bottom: 0px;">
			     <input type="submit" value="修改">
			     <input type="hidden" name="luckyId"  value="${luckyVO.luckyId}">
			     <input type="hidden" name="action"	value="getOne_For_Update"></FORM>
			</td>
			<td>
			  <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/lucky/lucky.do" style="margin-bottom: 0px;">
			     <input type="submit" value="刪除">
			     <input type="hidden" name="luckyId"  value="${luckyVO.luckyId}">
			     <input type="hidden" name="action" value="delete"></FORM>
			</td>
		</tr>
	</c:forEach>
</table>
</body>
</html>