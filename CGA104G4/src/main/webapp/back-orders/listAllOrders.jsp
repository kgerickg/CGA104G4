<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.detail.model.*"%>

<jsp:useBean id="ordersSvc" scope="page" class="com.orders.model.OrdersService" />

<html>
<head><title>所有訂單</title>

<!-- 響應式頁面 -->
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />

    <!-- ↓↓↓下面是這個版需要的css可添加各自需要的css檔-->

    <!-- Bootstrap Styles-->
    <link href=../resources/back-stage/assets/css/bootstrap.css rel="stylesheet" />
    <!-- FontAwesome Styles-->
    <link href=../resources/back-stage/assets/css/font-awesome.css rel="stylesheet" />
    <!-- Google Fonts-->
    <link href='http://fonts.googleapis.com/css?family=Open+Sans' rel='stylesheet' type='text/css' />
    <!-- Custom Styles-->
    <link href=../resources/back-stage/assets/css/custom-styles.css rel="stylesheet" />

    <!-- ↑↑↑下面是這個版需要的css可添加各自需要的css檔-->

<style>
  table#table-1 {
  	margin-left:auto; 
	margin-right:auto;
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
</style>

<style>
  table {
	width: 1100px;
	background-color: white;
	margin-top: 5px;
	margin-bottom: 5px;
	margin-left:auto; 
	margin-right:auto;
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
<body>

 <div id="wrapper">
        <!-- 上方Nav ↓↓↓  -->
        <nav class="navbar navbar-default top-navbar" role="navigation">
            <div class="navbar-header">
                <button type="button" class="navbar-toggle" data-toggle="collapse" data-target=".sidebar-collapse">
                    <span class="sr-only">Toggle navigation</span>
                    <span class="icon-bar"></span>
                    <span class="icon-bar"></span>
                    <span class="icon-bar"></span>
                </button>
                <a class="navbar-brand" href="index.html"><strong>後台管理</strong></a>
            </div>

            <ul class="nav navbar-top-links navbar-right">
                <!-- /.dropdown -->
                <li class="dropdown">
                    <a class="dropdown-toggle" data-toggle="dropdown" href="#" aria-expanded="false">
                        <i class="fa fa-user fa-fw"></i> <i class="fa fa-caret-down"></i>
                    </a>
                    <ul class="dropdown-menu dropdown-user">
                        <li><a href="../back-index/storelogin.html"><i class="fa fa-sign-in fa-fw"></i>登入</a>
                        </li>
                        <li><a href="#"><i class="fa fa-sign-out fa-fw"></i> 登出</a>
                        </li>
                    </ul>
                    <!-- /.dropdown-user -->
                </li>
                <!-- /.dropdown -->
            </ul>
        </nav>
        <!-- 上方Nav ↑↑↑  -->
        <!-- 左側Nav ↓↓↓  -->
        <nav class="navbar-default navbar-side" role="navigation">
            <div class="sidebar-collapse">
                <ul class="nav" id="main-menu">

                    <li>
                        <a class="active-menu" href="#"><i class="fa fa-dashboard"></i> 吉食享樂</a>
                    </li>

                    <li>
                        <a href="#">一般商品<span class="fa arrow"></span></a>
                        <ul class="nav nav-second-level">
                            <li>
                                <a href="#">一般商品管理</a>
                            </li>
                            <li>
                                <a href="../back-orders/listAllOrders.jsp">一般訂單管理</a>
                            </li>
                        </ul>
                    </li>

                    <li>
                        <a href="#">福袋商品<span class="fa arrow"></span></a>
                        <ul class="nav nav-second-level">
                            <li>
                                <a href="#">福袋商品管理</a>
                            </li>
                            <li>
                                <a href="#">福袋訂單管理</a>
                            </li>
                        </ul>
                    </li>


                    <li>
                        <a href="#">員工帳號管理<span class="fa arrow"></span></a>
                        <ul class="nav nav-second-level">
                            <li>
                                <a href="#">員工帳號管理</a>
                            </li>
                            <li>
                                <a href="#">員工權限管理</a>
                            </li>
                        </ul>
                    </li>
                    <li>
                        <a href="#">代幣系統管理<span class="fa arrow"></span></a>
                        <ul class="nav nav-second-level">
                            <li>
                                <a href="#">客訴處理</a>
                            </li>
                            <li>
                                <a href="#">優惠活動管理</a>
                            </li>
                            <li>
                                <a href="#">代幣儲值</a>
                            </li>
                        </ul>
                    </li>
                    <li>
                        <a href="#">會員管理<span class="fa arrow"></span></a>
                        <ul class="nav nav-second-level">
                            <li>
                                <a href="#">合作店家資格審核</a>
                            </li>
                            <li>
                                <a href="#">合作店家帳號管理</a>
                            </li>
                            <li>
                                <a href="#">一般會員管理</a>
                            </li>
                        </ul>
                    </li>

                    <li>
                        <a href="#"> 評論檢舉管理 </a>
                    </li>

                </ul>

            </div>

        </nav>
        <!-- 左側Nav ↑↑↑  -->

       <div id="page-wrapper">
			<div id="wrapper-container">
			<!-- ******內容寫在這邊 ↓↓↓****** -->

<table id="table-1">
	<tr><td>
		 <h3>所有訂單</h3>
	</td></tr>
</table>

<table>
	<tr>
		<th>訂單編號</th>
		<th>會員編號</th>
		<th>會員姓名</th>
		<th>商家編號</th>
		<th>商家名稱</th>
		<th>訂單金額</th>
		<th>訂單狀態</th>
		<th>訂單成立時間</th>
		<th>修改</th>
		<th>刪除</th>
		<th>查詢訂單明細</th>
	</tr>
	
	<c:forEach var="ordersVO" items="${ordersSvc.all}">
		<tr>
			<td>${ordersVO.ordId}</td>
			<td>${ordersVO.memId}</td>
			<td>${ordersVO.memberVO.memName}</td>
			<td>${ordersVO.storeId}</td>
			<td>${ordersVO.storeVO.storeName}</td>
			<td>${ordersVO.ordAmt}</td>
			<td>${ordersVO.ordStat}</td>
			<td>${ordersVO.ordTime}</td>
			<td>
			  <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/orders/orders.do" style="margin-bottom: 0px;">
			    <input type="submit" value="修改"> 
			    <input type="hidden" name="ordId" value="${ordersVO.ordId}">
			    <input type="hidden" name="action" value="getOne_For_Update_Orders"></FORM>
			</td>
			<td>
			  <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/orders/orders.do" style="margin-bottom: 0px;">
			    <input type="submit" value="刪除">
			    <input type="hidden" name="ordId" value="${ordersVO.ordId}">
			    <input type="hidden" name="action" value="delete_Orders"></FORM>
			</td>
			<td>
			  <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/orders/orders.do" style="margin-bottom: 0px;">
			    <input type="submit" value="送出查詢"> 
			    <input type="hidden" name="ordId" value="${ordersVO.ordId}">
			    <input type="hidden" name="action" value="listDetails_ByOrdId_C"></FORM>
			</td>
		</tr>
	</c:forEach>
</table>

<%if (request.getAttribute("listDetails_ByOrdId")!=null){%>
       <jsp:include page="listDetails_ByOrdId.jsp" />
<%} %>

 <!-- ↓↓↓下面是這個版需要的js可添加各自需要的js檔-->

    <!-- jQuery Js -->
    <script src=../resources/back-stage/assets/js/jquery-1.10.2.js></script>
    <!-- Bootstrap Js -->
    <script src=../resources/back-stage/assets/js/bootstrap.min.js></script>
    <!-- 左側Nav Dropdown -->
    <script src=../resources/back-stage/assets/js/jquery.metisMenu.js></script>
    <!-- Custom Js -->
    <script src=../resources/back-stage/assets/js/custom-scripts.js></script>

    <!-- ↑↑↑下面是這個版需要的js可添加各自需要的js檔-->

</body>
</html>