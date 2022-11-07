<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.detail.model.*"%>

<jsp:useBean id="listDetails_ByOrdId" scope="request" type="java.util.Set<DetailVO>" /> <!-- 於EL此行可省略 -->
<jsp:useBean id="ordersSvc" scope="page" class="com.orders.model.OrdersService" />


<html>
<head><title>訂單明細 - listDetails_ByOrdId.jsp</title>

<style>
  table#table-2 {
	background-color: #CCCCFF;
    border: 2px solid black;
    text-align: center;
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

<table id="table-2">
	<tr><td>
		 <h3>訂單明細 - listDetails_ByOrdId.jsp</h3>
		 <h4><a href="<%=request.getContextPath()%>/select_page.jsp"><img src="images/back1.gif" width="100" height="32" border="0">回首頁</a></h4>
	</td></tr>
</table>

<table>
	<tr>
		<th>商品編號</th>
		<th>商品數量</th>
		<th>商品名稱</th>
		<th>商品單價</th>
		<th>訂單編號</th>
		<th>修改</th>
		<th>刪除</th>
	</tr>
	
	<c:forEach var="detailVO" items="${listDetails_ByOrdId}" >
		<tr ${(detailVO.prodId==param.prodId) ? 'bgcolor=#CCCCFF':''}><!--將修改的那一筆加入對比色-->
			<td>${detailVO.prodId}</td>
			<td>${detailVO.prodQty}</td>
			<td>${detailVO.prodVO.prodName}</td>
			<td>${detailVO.prodVO.prodPrc}</td>
			<td>${detailVO.ordId}</td>
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

<br>本網頁的路徑:<br><b>
   <font color=blue>request.getServletPath():</font> <%=request.getServletPath()%><br>
   <font color=blue>request.getRequestURI(): </font> <%=request.getRequestURI()%> </b>

</body>
</html>