<%@ page language="java" contentType="text/html; charset=BIG5"
    pageEncoding="BIG5"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="BIG5">
<title>Insert title here</title>
</head>
<body>
<table id="table-1">
	<tr><td>
		<h3>訂單資訊</h3>
		<FORM><input type="hidden" name="action" value="listOrders_ByMemId"></FORM>
	</td></tr>
</table>
<table>
	<tr>
		<th>會員編號</th>
		<th>會員姓名</th>
		<th>訂單編號</th>
		<th>商家編號</th>
		<th>商家名稱</th>
		<th>訂單金額</th>
		<th>訂單成立時間</th>
		<th>訂單狀態</th>
		<th>申請客訴</th>
		<th>查詢訂單明細</th>
	</tr>
	<c:forEach var="ordersVO" items="${ordersSvc.getOrdersByMemId(memId)}">
		<tr>
			<td>${ordersVO.memId}</td>
			<td>${ordersVO.memberVO.memName}</td>
			<td>${ordersVO.ordId}</td>
			<td>${ordersVO.storeId}</td>
			<td>${ordersVO.storeVO.storeName}</td>
			<td>${ordersVO.ordAmt}</td>
			<td>${ordersVO.ordTime}</td>
			<td>
				<c:if test="${ordersVO.ordStat==0}">正在等待商家接單</c:if>
				<c:if test="${ordersVO.ordStat==1}">商家已接單，訂單準備中</c:if>
				<c:if test="${ordersVO.ordStat==2}">訂單已備妥，請前往領取</c:if>
				<c:if test="${ordersVO.ordStat==3}">訂單已完成</c:if>
				<c:if test="${ordersVO.ordStat==4}">訂單已取消</c:if>
				<c:if test="${ordersVO.ordStat==5}">客訴處理中</c:if>
			</td>
			<td>
			   <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/orders/orders.do" style="margin-bottom: 0px;">
			    <input type="submit" value="申請客訴"> 
			    <input type="hidden" name="ordId" value="${ordersVO.ordId}">
			    <input type="hidden" name="ordStat" value="5">
			    <input type="hidden" name="action" value="updateOrdStat_B"></FORM>
			</td>
			<td>
			  <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/orders/orders.do" style="margin-bottom: 0px;">
			    <input type="submit" value="訂單明細"> 
			    <input type="hidden" name="ordId" value="${ordersVO.ordId}">
			    <input type="hidden" name="action" value="listDetails_ByOrdId_B"></FORM>
			</td>
		</tr>
	</c:forEach>
</table>

<%if (request.getAttribute("listDetails_ByOrdId")!=null){%>
       <jsp:include page="memberListDetails_ByOrdId.jsp" />
<%} %>
</body>
</html>