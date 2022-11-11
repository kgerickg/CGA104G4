<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<html>
<head>
<meta charset="utf-8" />
<title>訂單後台</title>

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
table#table-1 {
	width: 450px;
	background-color: #CCCCFF;
	margin-top: 5px;
	margin-bottom: 10px;
	border: 3px ridge Gray;
	height: 80px;
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

a {
	color: #5c636a;
	text-decoration: none;
	font-weight: bold;
	text-decoration: none;
}

.table-order {
	width: 40%;
	height: 70%;
	margin: auto;
	padding: 5% 20px;
	border-radius: 10px;
	box-shadow: 0 0 20px rgba(0, 0, 0, .6);
	background: white;
}

.table-order ul li {
	color: black;
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
</style>

</head>



<body>
	<script src=${pageContext.request.contextPath}/resources/js/storenav.js></script>
	<!-- 上面是NAV載入 請一定要放在BODY開始的位置 -->
	<!--下面可自由新增內容 -->

	<div style="padding-bottom: 10%"></div>



	<div class="table-order">


		<%-- 錯誤表列 --%>
		<c:if test="${not empty errorMsgs}">
			<font style="color: red">請修正以下錯誤:</font>
			<ul>
				<c:forEach var="message" items="${errorMsgs}">
					<li style="color: red">${message}</li>
				</c:forEach>
			</ul>
		</c:if>

		<ul>
			<li style="font-weight: bolder; font-size: 1.2em;"><a
				style="text-decoration: none"
				href=${pageContext.request.contextPath}/front-lkorder/StoreListAllLkorder.jsp>查詢所有訂單</a></li>
			<br>

			<li style="font-weight: bolder; font-size: 1.2em"><a
				style="text-decoration: none"
				href=${pageContext.request.contextPath}/front-lkorder/StoreAddLkorder.jsp>新增福袋訂單</a></li>

			<br>
			<li>
				<FORM METHOD="post"
					ACTION=${pageContext.request.contextPath}/LkOrderServlet>
					<b style="color: black">輸入福袋訂單編號：</b> <input type="text"
						name="lkOrderId"> <input type="hidden" name="action"
						value="getOne_For_Display"> <input type="submit"
						value="送出" class="btn-secondary">
				</FORM>
			</li>

			<jsp:useBean id="lkorderSvc " scope="page"
				class="com.lkorder.model.LkOrderService" />

		</ul>

		<hr>

	</div>






	<!-- 下面是這個版需要的js可添加各自需要的js檔-->
	<script
		src=${pageContext.request.contextPath}/resources/js/bootstrap.min.js></script>
	<script src=${pageContext.request.contextPath}/resources/js/slick.js></script>
	<script src=${pageContext.request.contextPath}/resources/js/scripts.js></script>
	<script src=${pageContext.request.contextPath}/resources/js/isotope.js></script>
</body>
</html>