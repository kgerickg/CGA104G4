<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<html>
<head>
<meta charset="utf-8" />
<title>後台</title>

<!-- 響應式頁面 -->
<meta name="viewport" content="width=device-width, initial-scale=1.0" />

<!-- ↓↓↓下面是這個版需要的css可添加各自需要的css檔-->

<!-- Bootstrap Styles-->
<link href=${pageContext.request.contextPath}/resources/back-stage/assets/css/bootstrap.css	rel="stylesheet" />
<!-- FontAwesome Styles-->
<link href=${pageContext.request.contextPath}/resources/back-stage/assets/css/font-awesome.css	rel="stylesheet" />
<!-- Google Fonts-->
<link href='http://fonts.googleapis.com/css?family=Open+Sans'
	rel='stylesheet' type='text/css' />
<!-- Custom Styles-->
<link href=${pageContext.request.contextPath}/resources/back-stage/assets/css/custom-styles.css	rel="stylesheet" />

<!-- ↑↑↑下面是這個版需要的css可添加各自需要的css檔-->

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
	margin: 5% auto auto auto;
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


	<!-- 從這複製↓↓↓ -->

	<div id="wrapper">
		<!-- 上方Nav ↓↓↓  -->
		<nav class="navbar navbar-default top-navbar" role="navigation">
			<div class="navbar-header">
				<button type="button" class="navbar-toggle" data-toggle="collapse"
					data-target=".sidebar-collapse">
					<span class="sr-only">Toggle navigation</span> <span
						class="icon-bar"></span> <span class="icon-bar"></span> <span
						class="icon-bar"></span>
				</button>
				<a class="navbar-brand" href="index.html"><strong>後台管理</strong></a>
			</div>

			<ul class="nav navbar-top-links navbar-right">
				<!-- /.dropdown -->
				<li class="dropdown"><a class="dropdown-toggle"
					data-toggle="dropdown" href="#" aria-expanded="false"> <i
						class="fa fa-user fa-fw"></i> <i class="fa fa-caret-down"></i>
				</a>
					<ul class="dropdown-menu dropdown-user">
						<li><a href="#"><i class="fa fa-sign-in fa-fw"></i>登入</a></li>
						<li><a href="#"><i class="fa fa-sign-out fa-fw"></i> 登出</a></li>
					</ul> <!-- /.dropdown-user --></li>
				<!-- /.dropdown -->
			</ul>
		</nav>
		<!-- 上方Nav ↑↑↑  -->
		<!-- 左側Nav ↓↓↓  -->
		<nav class="navbar-default navbar-side" role="navigation">
			<div class="sidebar-collapse">
				<ul class="nav" id="main-menu">

					<li><a class="active-menu" href="#"><i
							class="fa fa-dashboard"></i> 吉食享樂</a></li>

					<li><a href="#">一般商品<span class="fa arrow"></span></a>
						<ul class="nav nav-second-level">
							<li><a href="#">一般商品管理</a></li>
							<li><a href="#">一般訂單管理</a></li>
						</ul></li>

					<li><a href="#">福袋商品<span class="fa arrow"></span></a>
						<ul class="nav nav-second-level">
							<li><a href="#">福袋商品管理</a></li>
							<li><a href="#">福袋訂單管理</a></li>
						</ul></li>


					<li><a href="#">員工帳號管理<span class="fa arrow"></span></a>
						<ul class="nav nav-second-level">
							<li><a href="#">員工帳號管理</a></li>
							<li><a href="#">員工權限管理</a></li>
						</ul></li>
					<li><a href="#">代幣系統管理<span class="fa arrow"></span></a>
						<ul class="nav nav-second-level">
							<li><a href="#">客訴處理</a></li>
							<li><a href="#">優惠活動管理</a></li>
							<li><a href="#">代幣儲值</a></li>
						</ul></li>
					<li><a href="#">會員管理<span class="fa arrow"></span></a>
						<ul class="nav nav-second-level">
							<li><a href="#">合作店家資格審核</a></li>
							<li><a href="#">合作店家帳號管理</a></li>
							<li><a href="#">一般會員管理</a></li>
						</ul></li>

					<li><a href="#"> 評論檢舉管理 </a></li>

				</ul>

			</div>

		</nav>
		<!-- 左側Nav ↑↑↑  -->


		<!-- 內容寫在這邊 ↓↓↓ -->

		<div id="page-wrapper">
			<div id="wrapper-container">



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
					<li style="font-weight: bolder; font-size: 1.2em;"><a style="text-decoration: none"
						href=${pageContext.request.contextPath}/back-lkorder/BackListAllLkorder.jsp>查詢所有訂單</a></li>
					<br>

					<li style="font-weight: bolder; font-size: 1.2em"><a style="text-decoration: none"
						href=${pageContext.request.contextPath}/back-lkorder/BackAddLkorder.jsp>新增福袋訂單</a></li>

					<br>
					<li>
						<FORM METHOD="post"
							ACTION=${pageContext.request.contextPath}/LkOrderServlet>
							<b style="color: black">輸入福袋訂單編號：</b> 
							<input type="text"	name="lkOrderId"> 
							<input type="hidden" name="action"	value="getOne_For_Display">
							<input type="submit" value="送出" class="btn-secondary">
						</FORM>
					</li>

					<jsp:useBean id="lkorderSvc " scope="page" class="com.lkorder.model.LkOrderService" />

				</ul>

				<hr>

			</div>
			</div>

		</div>


		<!-- 內容寫在這邊 ↑↑↑ -->

	</div>
	<!-- 複製到這裡↑↑↑ -->





	<!-- ↓↓↓下面是這個版需要的js可添加各自需要的js檔-->

	<!-- jQuery Js -->
	<script src=${pageContext.request.contextPath}/resources/back-stage/assets/js/jquery-1.10.2.js></script>
	<!-- Bootstrap Js -->
	<script src=${pageContext.request.contextPath}/resources/back-stage/assets/js/bootstrap.min.js></script>
	<!-- 左側Nav Dropdown -->
	<script src=${pageContext.request.contextPath}/resources/back-stage/assets/js/jquery.metisMenu.js></script>
	<!-- Custom Js -->
	<script src=${pageContext.request.contextPath}/resources/back-stage/assets/js/custom-scripts.js></script>

	<!-- ↑↑↑下面是這個版需要的js可添加各自需要的js檔-->
</body>
</html>