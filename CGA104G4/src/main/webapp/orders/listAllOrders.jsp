<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.orders.model.*"%>
<%-- 此頁練習採用 EL 的寫法取值 --%>

<%
    OrdersService ordersSvc = new OrdersService();
    List<OrdersVO> list = ordersSvc.getAll();
    pageContext.setAttribute("list",list);
%>


<html>
<head>
<title>歷史訂單</title>

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


<table id="table-1">
	<tr><td>
		 <h3>歷史訂單</h3>
	</td></tr>
</table>

<table>
	<tr>
		<th>訂單編號</th>
		<th>會員編號</th>
		<th>商家編號</th>
		<th>結帳金額</th>
		<th>訂單狀態</th>
		<th>訂單成立日期</th>
		<th>修改</th>
		<th>刪除</th>		
	</tr>
	<%@ include file="page1.file" %> 
	<c:forEach var="ordersVO" items="${list}" begin="<%=pageIndex%>" end="<%=pageIndex+rowsPerPage-1%>">
		
		<tr>
			<td>${ordersVO.ordId}</td>
			<td>${ordersVO.memId}</td>
			<td>${ordersVO.storeId}</td>
			<td>${ordersVO.ordAmt}</td>
			<td>${ordersVO.ordStat}</td>
			<td>${ordersVO.ordTime}</td>
			<td>
			  <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/orders/orders.do" style="margin-bottom: 0px;">
			     <input type="submit" value="修改">
			     <input type="hidden" name="ordId"  value="${ordersVO.ordId}">
			     <input type="hidden" name="action"	value="getOne_For_Update"></FORM>
			</td>
			<td>
			  <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/orders/orders.do" style="margin-bottom: 0px;">
			     <input type="submit" value="刪除">
			     <input type="hidden" name="ordId"  value="${ordersVO.ordId}">
			     <input type="hidden" name="action" value="delete"></FORM>
			</td>			 
		</tr>
	</c:forEach>
</table>
<%@ include file="page2.file" %>

</body>
</html>