<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.lkorder.model.*"%>


<%
LkOrderService lkOrderService = new LkOrderService();
List<LkOrderSelectVO> list = lkOrderService.findAll();
pageContext.setAttribute("list", list);
%>

<!DOCTYPE html>
<html>
<head>
<meta charset="BIG5">
<title>查詢所有訂單</title>

<meta charset="UTF-8">
<!-- 響應式頁面 -->
<meta name="viewport" content="width=device-width, initial-scale=1.0" />


<!-- ↓↓↓下面是這個版需要的css可添加各自需要的css檔-->

<!-- Bootstrap Styles-->
<link
	href=${pageContext.request.contextPath}/resources/back-stage/assets/css/bootstrap.css
	rel="stylesheet" />
<!-- FontAwesome Styles-->
<link
	href=${pageContext.request.contextPath}/resources/back-stage/assets/css/font-awesome.css
	rel="stylesheet" />
<!-- Google Fonts-->
<link href='http://fonts.googleapis.com/css?family=Open+Sans'
	rel='stylesheet' type='text/css' />
<!-- Custom Styles-->
<link
	href=${pageContext.request.contextPath}/resources/back-stage/assets/css/custom-styles.css
	rel="stylesheet" />

<!-- ↑↑↑下面是這個版需要的css可添加各自需要的css檔-->


<style>
table {
	border-collapse: collapse;
	margin: 25px auto;
	font-size: 0.9em;
	font-family: sans-serif;
	min-width: 400px;
	box-shadow: 0 0 20px rgba(0, 0, 0, .6);
	text-align: center;
	border-radius: 1%;
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
			<div id="wrapper"
				style="min-height: 90vh; padding-top: 15px; padding-bottom: 30px">
				<!-- ******內容寫在這邊 ↓↓↓****** -->


				<table id="table-1">
					<tr>
						<td>
							<h1>查詢所有訂單</h1>
							<h4>
								<a
									href=${pageContext.request.contextPath}/back-lkorder/BackIndexLkorder.jsp>回首頁</a>
							</h4>
						</td>
					</tr>
				</table>

				<table>
					<thead>
						<tr>
							<th>福袋訂單編號</th>
							<th>福袋名稱</th>
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
					<%-- 		<%@ include file="page1.file"%> --%>
					<c:forEach var="LkOrderSelectVO" items="${list}">

						<tr>
							<td>${LkOrderSelectVO.lkOrderId}</td>
							<td>${LkOrderSelectVO.lkName}</td>
							<td>${LkOrderSelectVO.memName}</td>
							<td>${LkOrderSelectVO.lkTodayId}</td>
							<td>${LkOrderSelectVO.lkOrdAmt}</td>

							<c:if test="${LkOrderSelectVO.lkOrdStat == '0'}">
								<td>待取貨</td>
							</c:if>
							<c:if test="${LkOrderSelectVO.lkOrdStat == '1'}">
								<td>已取貨</td>
							</c:if>
							<c:if test="${LkOrderSelectVO.lkOrdStat == '2'}">
								<td>完成</td>
							</c:if>
							<c:if test="${LkOrderSelectVO.lkOrdStat == '3'}">
								<td>客訴處理中</td>
							</c:if>

							<td>${LkOrderSelectVO.lkOrdTimeS}</td>
							<td>${LkOrderSelectVO.lkOrdTaketime}</td>
							<td>${LkOrderSelectVO.lkOrdTimeE}</td>
							<td>
								<FORM METHOD="post"
									ACTION=${pageContext.request.contextPath}/back-lkorder/lkorderback.do
									style="margin-bottom: 0px;">
									<input type="hidden" name="action" value="getOne_For_Update">
									<input type="submit" value="修改" class="btn-secondary">
									<input type="hidden" name="lkOrderId"
										value="${LkOrderSelectVO.lkOrderId}">
									<!-- 															value="getOne_For_Update" -->
								</FORM>
							</td>
							<td>
								<FORM METHOD="post"
									ACTION=${pageContext.request.contextPath}/back-lkorder/lkorderback.do
									style="margin-bottom: 0px;">
									<input type="submit" value="刪除" class="btn-secondary">
									<input type="hidden" name="lkOrderId"
										value="${LkOrderSelectVO.lkOrderId}"> <input
										type="hidden" name="action" value="delete">
								</FORM>
							</td>
						</tr>
					</c:forEach>
				</table>
				<%-- 	<%@ include file="page2.file"%> --%>




				<!-- ******內容寫在這邊 ↑↑↑****** -->
			</div>
		</div>

	</div>
	<!-- 複製到這裡↑↑↑ -->



	<!-- ↓↓↓下面是這個版需要的js可添加各自需要的js檔-->

	<!-- jQuery Js -->
	<script
		src=${pageContext.request.contextPath}/resources/back-stage/assets/js/jquery-1.10.2.js></script>
	<!-- Bootstrap Js -->
	<script
		src=${pageContext.request.contextPath}/resources/back-stage/assets/js/bootstrap.min.js></script>
	<!-- 左側Nav Dropdown -->
	<script
		src=${pageContext.request.contextPath}/resources/back-stage/assets/js/jquery.metisMenu.js></script>
	<!-- Custom Js -->
	<script
		src=${pageContext.request.contextPath}/resources/back-stage/assets/js/custom-scripts.js></script>

	<!-- ↑↑↑下面是這個版需要的js可添加各自需要的js檔-->

</body>
</html>