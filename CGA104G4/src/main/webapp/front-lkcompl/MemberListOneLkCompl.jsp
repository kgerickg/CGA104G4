<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.lkcompl.model.*"%>



<html>
<head>
<!-- 頁籤顯示的title -->
<title>查詢客訴訂單</title>
<meta charset="UTF-8">
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
	margin: 25px auto;
	font-size: 0.9em;
	font-family: sans-serif;
	min-width: 400px;
	box-shadow: 0 0 20px rgba(0, 0, 0, .6);
	text-align: center;
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
	width: 36vw;
	text-align: center;
	background-color: #F0F0F0;
	border-radius: 10px;
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
	<script src=${pageContext.request.contextPath}/resources/js/membernav.js></script>
	<!-- 上面是NAV載入 請一定要放在BODY開始的位置 -->
	<!--下面可自由新增內容 -->
	<div style="padding:4%"></div>

	<table id="upper-table" style="border-radius: 10px;">
		<tr>
			<td>
				<h3 style="font-size: 40px; font-weight: 700;color:#2F3237">客訴訂單資料</h3>
				<h4>
					<a style="color: #6c757d"
						href=${pageContext.request.contextPath}/front-lkcompl/MemberIndexLkCompl.jsp>回首頁</a>
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
			</tr>
		</thead>

			<tr>
				<td>${lkComplVO.lkCcId}</td>
				<td>${lkComplVO.lkOrdId}</td>

				<c:if test="${lkComplVO.lkCcStat == '0'}">
					<td>待處理</td>
				</c:if>
				<c:if test="${lkComplVO.lkCcStat == '1'}">
					<td>處理中</td>
				</c:if>
				<c:if test="${lkComplVO.lkCcStat == '2'}">
					<td>已完成</td>
				</c:if>

				<td>${lkComplVO.lkCcCont}</td>

				<c:if test="${lkComplVO.lkRfdStat == '0'}">
					<td>待處理</td>
				</c:if>
				<c:if test="${lkComplVO.lkRfdStat == '1'}">
					<td>處理中</td>
				</c:if>
				<c:if test="${lkComplVO.lkRfdStat == '2'}">
					<td>已完成</td>
				</c:if>

			</tr>
	</table>

	<!-- 下面是這個版需要的js可添加各自需要的js檔-->
	<script src=${pageContext.request.contextPath}/resources/js/bootstrap.min.js></script>
	<script src=${pageContext.request.contextPath}/resources/js/slick.js></script>
	<script src=${pageContext.request.contextPath}/resources/js/scripts.js></script>
	<script src=${pageContext.request.contextPath}/resources/js/isotope.js></script>
</body>
</html>