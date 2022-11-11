<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.lkcompl.model.*, java.util.*"%>



<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<!-- 頁籤顯示的title -->
<title>查詢所有客訴訂單</title>

<!-- 響應式頁面 -->
<meta name="viewport" content="width=device-width, initial-scale=1.0">

<!-- GOOGLEFONT -->
<link rel="preconnect" href="https://fonts.googleapis.com">
<link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
<link
	href="https://fonts.googleapis.com/css2?family=Noto+Sans+TC:wght@100;300;400;500;700;900&display=swap"
	rel="stylesheet">

<!-- 下面是這個模板需要的css請勿改動 若有排版需要請直接寫新的css蓋過去就可以了 -->
<link rel="stylesheet" type="text/css"
	href=${pageContext.request.contextPath}/resources/css/all.min.css>
<link rel="stylesheet" type="text/css"
	href=${pageContext.request.contextPath}/resources/css/animate.min.css>
<link rel="stylesheet" type="text/css"
	href=${pageContext.request.contextPath}/resources/css/slick.css>
<link rel="stylesheet" type="text/css"
	href=${pageContext.request.contextPath}/resources/css/slick-theme.css>
<link rel="stylesheet" type="text/css"
	href=${pageContext.request.contextPath}/resources/css/flaticon.css>
<link rel="stylesheet" type="text/css"
	href=${pageContext.request.contextPath}/resources/css/style.css>
<link rel="stylesheet" type="text/css"
	href=${pageContext.request.contextPath}/resources/css/nav.css>
<!-- 已經預載入jquery了有需要jquery可以直接使用 -->
<script src="https://code.jquery.com/jquery-3.6.1.min.js"></script>

<!-- 請將覆蓋用的css放置此註解下方 -->


<style>
table {
	border-collapse: collapse;
	font-size: 0.9em;
	font-family: sans-serif;
	min-width: 400px;
	box-shadow: 0 0 20px rgba(0, 0, 0, .6);
	text-align: center;
	border-radius: 1%;
	margin: 1vw auto;
}

table  tr:nth-of-type(even) {
	background-color: #f3f3f3;
	color: black;
	border-bottom: 1px solid #dddddd;
}

table th, td {
	padding: 5px 5px;
}

#upper-table td {
	width: 60vw;
	text-align: center;
	background-color: #F0F0F0;
}

.btn-secondary {
	color: #fff;
	background-color: #6c757d;
	border: 1px solid #6c757d;
	border-radius: 10%;
}

.btn-secondary:hover {
	color: #fff;
	background-color: #5c636a;
	border: 1px solid #6c757d;
	border-radius: 10%;
}

a {
	text-decoration: none;
	font-weight: bold;
}

thead th {
	color: #D5DDE5;;
	background: #1b1e24;
	border-bottom: 4px solid #9ea7af;
	border-right: 1px solid #343a45;
	font-size: 1rem;
	font-weight: 100;
	padding: 10px;
}
</style>

</head>

<body>

	<script src=${pageContext.request.contextPath}/resources/js/storenav.js></script>
	<!-- 上面是NAV載入 請一定要放在BODY開始的位置 -->
	<!--下面可自由新增內容 -->
	<div style="padding: 3vw;"></div>
	


	<table id="upper-table">
		<tr>
			<td>
				<h1>查詢所有客訴訂單</h1>
				<h4>
					<a
						href=${pageContext.request.contextPath}/front-lkorder/FrontIndexLkorder.jsp>回首頁</a>
				</h4>
			</td>
		</tr>
	</table>

	<table>
		<thead>
			<tr>
				<th>客訴編號</th>
				<th>福袋訂單編號</th>
				<th>處理狀態</th>
				<th>客訴內容</th>
				<th>退款狀態</th>
				<th>修改</th>
			</tr>
		</thead>
		<c:forEach var="" items="">
			<%-- <c:forEach var="LkOrderSelectVO" items="${list}"> --%>

			<tr>
				<td>${LkComplVO.lkCcId}</td>
				<td>${LkComplVO.lkOrdId}</td>

				<c:if test="${LkComplVO.lkCcStat == '0'}">
					<td>待處理</td>
				</c:if>
				<c:if test="${LkComplVO.lkCcStat == '1'}">
					<td>處理中</td>
				</c:if>
				<c:if test="${LkComplVO.lkCcStat == '2'}">
					<td>已完成</td>
				</c:if>

				<td>${LkComplVO.lkCcCont}</td>

				<c:if test="${LkComplVO.lkRfdStat == '0'}">
					<td>待處理</td>
				</c:if>
				<c:if test="${LkComplVO.lkRfdStat == '1'}">
					<td>處理中</td>
				</c:if>
				<c:if test="${LkComplVO.lkRfdStat == '2'}">
					<td>已完成</td>
				</c:if>

				<td>
					<FORM METHOD="post"
						ACTION=${pageContext.request.contextPath}/back-lkcompl/lkcompl.do
						style="margin-bottom: 0px;">
						<input type="hidden" name="action" value="getOne_For_Update">
						<input type="submit" value="修改" class="btn-secondary">
						 <input type="hidden" name="lkOrderId" value="${LkOrderSelectVO.lkOrderId}">
					</FORM>
				</td>

			</tr>
		</c:forEach>
	</table>


	<!-- 下面是這個版需要的js可添加各自需要的js檔-->
	<script
		src=${pageContext.request.contextPath}/resources/js/bootstrap.min.js></script>
	<script src=${pageContext.request.contextPath}/resources/js/slick.js></script>
	<script src=${pageContext.request.contextPath}/resources/js/scripts.js></script>
	<script src=${pageContext.request.contextPath}/resources/js/isotope.js></script>
</body>
</html>