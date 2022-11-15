<%@page import="com.lkcompl.model.LkComplVO"%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.lkorder.model.*"%>

<% LkComplVO lkComplVO = (LkComplVO) request.getAttribute("lkComplVO"); %>

<html>
<head>
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />
<title>每日福袋訂單資料修改</title>


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
a {
	color: #5c636a;
	display: inline;
	text-decoration: none;
}

.btn-secondary {
	color: #fff;
	background-color: #6c757d;
	border: 1px solid #6c757d;
	border-radius: 5px;
}

.btn-secondary:hover {
	color: #fff;
	background-color: #5c636a;
	border: 1px solid #6c757d;
	border-radius: 5px;
}

table tr td:nth-of-type(odd) {
	display: block;
	padding-left: 20px;
	background: #35544E;
	color: white;
	font-size: 14px;
	line-height: 35px;
	border-top-left-radius: 10px;
	border-bottom-left-radius: 10px;
	margin-bottom: 10px;
}

.table tr td input {
	background: white;
	border: 3px solid #35544E;
	border-left: 1;
	border-radius: 0 2px 2px 0;
	box-shadow: none;
	margin: 0;
	width: 100%;
	color: #35544E;
	margin-bottom: 10px;
	line-height: 27px;
}

.table {
	width: 50%;
	height: auto;
	margin: 2% auto;
	padding: 30px 90px;
	border-radius: 10px;
	box-shadow: 0 0 20px rgba(0, 0, 0, .6);
	background: white;
}

#table-1 {
	margin: auto;
	padding: 2%;
}

#table-1 td {
	width: 50vw;
	text-align: center;
	background-color: #F0F0F0;
	margin: 2% auto;
	border-top-right-radius: 10px;
	border-bottom-right-radius: 10px;
	padding: 15px;
	box-shadow: 0 0 20px rgba(0, 0, 0, .6);
	color: #2F3237;
	font-weight: 700;
}
.radio-container .input {
	width: 5%;
}


</style>

</head>
<body>

	<script src=${pageContext.request.contextPath}/resources/js/storenav.js></script>
	<!-- 上面是NAV載入 請一定要放在BODY開始的位置 -->
	<!--下面可自由新增內容 -->
	<div style="padding-bottom: 10%"></div>


	<table id="table-1">
		<tr>
			<td>
				<h1>客訴訂單資料修改</h1>
				<h4>
					<a
						href=${pageContext.request.contextPath}/front-lkcompl/StoreIndexLkCompl.jsp>回首頁</a>
				</h4>
			</td>
		</tr>
	</table>


	<%-- <form:form method="POST" commandName="team" action="${pageContext.request.contextPath}/team/edit/${team.id}.html"> --%>

	<form method="post" action=${pageContext.request.contextPath}/store-lkcompl/lkcompl.do?action=updating  class="table">
		<table>

			<tr>
				<td style="width: 200px">客訴編號:<font color=red><b>*</b></font></td>
				<td>${lkComplVO.lkCcId}</td>

			</tr>
			<tr>
				<td>福袋訂單編號:<font color=red></td>
				<td>${lkComplVO.lkOrdId}</td>
			</tr>
			<tr>
				<td>處理狀態:</td>
				<td class="radio-container">
				<input class="input" type="radio" name="lkCcStat" value="0"> 待處理
				<input class="input"  type="radio" name="lkCcStat" value="1"> 處理中
				<input class="input"  type="radio" name="lkCcStat" value="2"> 已完成
				</td>
			</tr>
			<tr>
				<td>退款狀態:</td>
				<td class="radio-container">
				<input class="input" type="radio" name="lkRfdStat" value="0"> 待處理
				<input class="input" type="radio" name="lkRfdStat" value="1"> 處理中
				<input class="input" type="radio" name="lkRfdStat" value="2"> 已完成
				</td>

			</tr>
			<tr>
				<td>客訴內容:</td>
				<td>${lkComplVO.lkCcCont}</td>
			</tr>


		</table>
		<br> <input type="hidden" name="action" value="updating"> 
		<input type="submit" value="送出修改" class="btn-secondary">
	</form>









	<!-- 下面是這個版需要的js可添加各自需要的js檔-->
	<script
		src=${pageContext.request.contextPath}/resources/js/bootstrap.min.js></script>
	<script src=${pageContext.request.contextPath}/resources/js/slick.js></script>
	<script src=${pageContext.request.contextPath}/resources/js/scripts.js></script>
	<script src=${pageContext.request.contextPath}/resources/js/isotope.js></script>


</body>






</html>