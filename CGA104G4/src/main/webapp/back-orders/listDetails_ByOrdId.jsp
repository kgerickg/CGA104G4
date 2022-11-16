<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.detail.model.*"%>

<jsp:useBean id="listDetails_ByOrdId" scope="request" type="java.util.Set<DetailVO>" /> <!-- 於EL此行可省略 -->
<jsp:useBean id="ordersSvc" scope="page" class="com.orders.model.OrdersService" />


<html>
<head>
<style>
  table#table-2 {
	width: 100%;
	margin-top: 5px;
	margin-bottom: 5px;
	margin-left:auto; 
	margin-right:auto;
  }
  
  th, td {
    padding: 5px;
    text-align: center;
  }
</style>

</head>
<body>

<table id="table-2">
	<tr>
		<th>訂單編號</th>
		<th>商品編號</th>
		<th>商品名稱</th>
		<th>商品單價</th>
		<th>商品數量</th>
	</tr>
	
	<c:forEach var="detailVO" items="${listDetails_ByOrdId}" >
		<tr>
			<td>${detailVO.ordId}</td>
			<td>${detailVO.prodId}</td>
			<td>${detailVO.prodVO.prodName}</td>
			<td>${detailVO.prodVO.prodPrc}</td>
			<td>${detailVO.prodQty}</td>
			<td>
		</tr>
	</c:forEach>
</table>
</body>
</html>