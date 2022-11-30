<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.lktoday.model.*"%>

<%
// 	Integer storeId = (Integer) session.getAttribute("storeId");
//	Integer storeId = 3;
//	LkTodayService service = new LkTodayService();
//	List<TodayLuckyVO> lktodayVOList = service.findByStore(storeId, new java.sql.Date(System.currentTimeMillis()));
//	List<TodayLuckyVO> selectList = lktodayVOList.stream().map(vo -> {
//		TodayLuckyVO selectVo = new TodayLuckyVO();
//		selectVo.setLkId(vo.getLkId());
//		selectVo.setLkName(vo.getLkName());
//		return selectVo;
//	}).distinct().toList();
%>

<!DOCTYPE html>
<html>
<head>
<title>福袋預約資料</title>

<meta charset="UTF-8">
<!-- 響應式頁面 -->
<meta name="viewport" content="width=device-width, initial-scale=1.0" />


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
	border-radius: 10px;
}

table  tr:nth-of-type(even) {
	background-color: #f3f3f3;
	color: black;
	border-bottom: 1px solid #dddddd;
}

table th, td {
	padding: 5px 5px;
	color: black;
}

#table-1 td {
	width: 60vw;
	text-align: center;
	background-color: #F0F0F0;
	border-radius: 10px;
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

<script src="../resources/js/storenav.js"></script>
<!-- 上面是NAV載入 請一定要放在BODY開始的位置 -->
<!--下面可自由新增內容 -->

<div style="padding-bottom: 10%"></div>

<table id="table-1">
	<tr><td>
		 <h1 style="font-weight: 700">福袋預約資料</h1>
		 <h4><a style="text-decoration: none; color: #5c636a" href="<%=request.getContextPath()%>/front-lktoday/selectLkToday.jsp">回上頁</a></h4>
	</td></tr>
</table>

<table>
	<thead>
	<tr>
		<th>福袋編號</th>
		<th>福袋名稱</th>
		<th>今日福袋編號</th>
		<th>會員編號</th>
		<th>商家編號</th>
		<th>福袋價格</th>
		<th>勾選</th>
	</tr>
	</thead>

	<c:forEach var="vo" items="${list}">
		<tr>
			<td>${vo.lkId}</td>
			<td>${vo.lkName}</td>
			<td>${vo.lkTodayId}</td>
			<td>${vo.memId}</td>
			<td>${vo.storeId}</td>
			<td>${vo.lkPrc}</td> 
			<td>
			  <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/lktoday/getlktoday.do" style="margin-bottom: 0px;">
			     <input type="submit" value="接受" class="btn-secondary">
			     <input type="hidden" name="lkTodayId" value="${vo.lkTodayId}">
			     <input type="hidden" name="lkId" value="${vo.lkId}">
			     <input type="hidden" name="memId" value="${vo.memId}">
			     <input type="hidden" name="storeId" value="${vo.storeId}">
			     <input type="hidden" name="lkPrc" value="${vo.lkPrc}">
			     <input type="hidden" name="action" value="insertlkorder"></FORM>
			</td>
		</tr>
	</c:forEach>
</table>
				<!-- 下面是這個版需要的js可添加各自需要的js檔-->
    <script src="../resources/js/bootstrap.min.js"></script>
    <script src="../resources/js/slick.js"></script>
    <script src="../resources/js/scripts.js"></script>
    <script src="../resources/js/isotope.js"></script>

</body>
</html>