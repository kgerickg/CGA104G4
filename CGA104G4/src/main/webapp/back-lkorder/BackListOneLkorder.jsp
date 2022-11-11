<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.lkorder.model.*"%>

<%
LkOrderSelectVO lkOrderSelectVO = (LkOrderSelectVO) request.getAttribute("lkOrderSelectVO"); //EmpServlet.java(Concroller), 存入req的empVO物件
%>

<html>
<head>
<title>每日福袋訂單資料</title>
<meta charset="UTF-8">

<!-- 響應式頁面 -->
<meta name="viewport" content="width=device-width, initial-scale=1.0" />

<!-- ↓↓↓下面是這個版需要的css可添加各自需要的css檔-->

<!-- Bootstrap Styles-->
<link href=${pageContext.request.contextPath}/resources/back-stage/assets/css/bootstrap.css rel="stylesheet" />
<!-- FontAwesome Styles-->
<link href=${pageContext.request.contextPath}/resources/back-stage/assets/css/font-awesome.css rel="stylesheet" />
<!-- Google Fonts-->
<link href='http://fonts.googleapis.com/css?family=Open+Sans' rel='stylesheet' type='text/css' />
<!-- Custom Styles-->
<link href=${pageContext.request.contextPath}/resources/back-stage/assets/css/custom-styles.css rel="stylesheet" />

<!-- ↑↑↑下面是這個版需要的css可添加各自需要的css檔-->

<style>
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
	border-radius: 10px;
}

a {
	color: #5c636a;
	display: inline;
	text-decoration: none;
	font-weight: bold;
}

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
}

#table-1 td {
	width: 60vw;
	text-align: center;
	background-color: #F0F0F0;
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

		<div id="page-wrapper">
			<div id="wrapper-container">
				<!-- ******內容寫在這邊 ↓↓↓****** -->


				<table id="table-1">
					<tr>
						<td>
							<h1 style="font-weight: 700;">福袋訂單資料</h1>
							<h4>
								<a style="text-decoration: none;"
									href=${pageContext.request.contextPath}/back-lkorder/BackIndexLkorder.jsp>回首頁</a>
							</h4>
						</td>
					</tr>
				</table>

				<table>
					<thead>
						<tr>
							<th>福袋訂單編號</th>
							<th>福袋編號</th>
							<th>福袋名稱</th>
							<th>會員編號</th>
							<th>會員名稱</th>
							<th>每日福袋編號</th>
							<th>結帳金額</th>
							<th>訂單狀態</th>
							<th>訂單成立日期</th>
							<th>取貨日期</th>
							<th>訂單完成日期</th>
							<th>修改</th>
							<th>刪除</th>
						</tr>
					</thead>
					<tr>
						<td>${lkOrderSelectVO.lkOrderId}</td>
						<td>${lkOrderSelectVO.lkId}</td>
						<td>${lkOrderSelectVO.lkName}</td>
						<td>${lkOrderSelectVO.memId}</td>
						<td>${lkOrderSelectVO.memName}</td>
						<td>${lkOrderSelectVO.lkTodayId}</td>
						<td>${lkOrderSelectVO.lkOrdAmt}</td>
						
						<c:if test="${lkOrderSelectVO.lkOrdStat == '0'}">
									<td>待取貨</td>
							</c:if> 
							<c:if test="${lkOrderSelectVO.lkOrdStat == '1'}">
									<td>已取貨</td>
							</c:if> 
							<c:if test="${lkOrderSelectVO.lkOrdStat == '2'}">
									<td>完成</td>
							</c:if> 
							<c:if test="${lkOrderSelectVO.lkOrdStat == '3'}">
									<td>客訴處理中</td>
						</c:if> 

						<td>${lkOrderSelectVO.lkOrdTimeS}</td>
						<td>${lkOrderSelectVO.lkOrdTaketime}</td>
						<td>${lkOrderSelectVO.lkOrdTimeE}</td>
						<td>
								<FORM METHOD="post"
									ACTION=${pageContext.request.contextPath}/back-lkorder/lkorderback.do
									style="margin-bottom: 0px;">
									<input type="submit" value="修改" class="btn-secondary" class="btn-secondary">
									<input type="hidden" name="lkOrderId" value="${lkOrderSelectVO.lkOrderId}">
									<input type="hidden" name="action" value="getOne_For_Update">
									<!-- 															value="getOne_For_Update" -->
								</FORM>
							</td>
							<td>
								<FORM METHOD="post"
									ACTION=${pageContext.request.contextPath}/back-lkorder/lkorderback.do
									style="margin-bottom: 0px;">
									<input type="submit" value="刪除" class="btn-secondary" class="btn-secondary">
									<input type="hidden" name="lkOrderId" value="${lkOrderSelectVO.lkOrderId}"> 
									<input type="hidden" name="action" value="delete">
								</FORM>
							</td>
					</tr>
				</table>



				<!-- ******內容寫在這邊 ↑↑↑****** -->
			</div>
		</div>

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