<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.detail.model.*"%>

<jsp:useBean id="listDetails_ByOrdId" scope="request" type="java.util.Set<DetailVO>" /> <!-- 於EL此行可省略 -->
<jsp:useBean id="ordersSvc" scope="page" class="com.orders.model.OrdersService" />


<html>
<head><title>訂單明細</title>

<style>
  table#table-2 {
    text-align: center;
    margin-left:auto; 
	margin-right:auto;
  }
  table#table-2 h4 {
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
	width: 1100px;
	background-color: white;
	margin-top: 5px;
	margin-bottom: 5px;
	margin-left:auto; 
	margin-right:auto;
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

<table id="table-2">
	<tr><td>
		 <h3>訂單明細</h3>
	</td></tr>
</table>

<table>
	<tr>
		<th>訂單編號</th>
		<th>商品編號</th>
		<th>商品名稱</th>
		<th>商品單價</th>
		<th>商品數量</th>
		<th>修改</th>
		<th>刪除</th>
	</tr>
	
	<c:forEach var="detailVO" items="${listDetails_ByOrdId}" >
		<tr ${(detailVO.prodId==param.prodId) ? 'bgcolor=#CCCCFF':''}><!--將修改的那一筆加入對比色-->
			<td>${detailVO.ordId}</td>
			<td>${detailVO.prodId}</td>
			<td>${detailVO.prodVO.prodName}</td>
			<td>${detailVO.prodVO.prodPrc}</td>
			<td>${detailVO.prodQty}</td>
			<td>
			  <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/detail/detail.do" style="margin-bottom: 0px;">
			    <input type="submit" value="修改"> 
			    <input type="hidden" name="prodId"      value="${detailVO.prodId}">
			    <input type="hidden" name="requestURL" value="<%=request.getServletPath()%>"><!--送出本網頁的路徑給Controller--><!-- 目前尚未用到  -->
			    <input type="hidden" name="action"	   value="getOne_For_Update"></FORM>
			</td>
			<td>
			  <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/detail/detail.do" style="margin-bottom: 0px;">
			    <input type="submit" value="刪除">
			    <input type="hidden" name="prodId"      value="${detailVO.prodId}">
			    <input type="hidden" name="requestURL" value="<%=request.getServletPath()%>"><!--送出本網頁的路徑給Controller-->
			    <input type="hidden" name="action"     value="delete"></FORM>
			</td>
		</tr>
	</c:forEach>
</table>
</body>
</html>