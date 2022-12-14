<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.detail.model.*"%>

<jsp:useBean id="ordersSvc" scope="page" class="com.orders.model.OrdersService" />
<jsp:useBean id="detailSvc" scope="request" class="com.detail.model.DetailService" />

<html>
<head><title>訂單管理</title>

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
  table {
	width: 100%;
	margin-top: 5px;
	margin-bottom: 5px;
	margin-left:auto; 
	margin-right:auto;
  }
  th, td {
    padding: 5px;
    text-align: center;
  }
  tr:nth-child(odd){
  background:#D7FFEE;
}

tr:nth-child(even){
  background:white;
}
tr:nth-child(1){
  background:none;
}
td:first-child{
  border-top-left-radius: 10px;
  border-bottom-left-radius: 10px;
}

td:last-child{
  border-top-right-radius: 10px;
  border-bottom-right-radius: 10px;
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
<table>
	<tr>
		<th>訂單編號</th>
		<th>會員編號</th>
		<th>會員姓名</th>
		<th>商家編號</th>
		<th>商家名稱</th>
		<th>訂單金額</th>
		<th>訂單成立時間</th>
		<th>訂單狀態</th>
		<th>修改訂單狀態</th>
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
			<td>${ordersVO.ordTime}</td>
			<td>
				<c:if test="${ordersVO.ordStat==0}">正在等待商家接單</c:if>
				<c:if test="${ordersVO.ordStat==1}">商家已接單，訂單準備中</c:if>
				<c:if test="${ordersVO.ordStat==2}">訂單已備妥，待領取</c:if>
				<c:if test="${ordersVO.ordStat==3}">訂單已完成</c:if>
				<c:if test="${ordersVO.ordStat==4}">訂單已取消</c:if>
				<c:if test="${ordersVO.ordStat==5}">客訴處理中</c:if>
			</td>
			<td>
			  <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/orders/orders.do" style="margin-bottom: 0px;">
			  <select name=ordStat style=width:100>
			  	<option value="0">正在等待商家接單</option>
			  	<option value="1">商家已接單，訂單準備中</option>
			  	<option value="2">訂單已備妥，待領取</option>
			  	<option value="3">訂單已完成</option>
			  	<option value="4">訂單已取消</option>
			  	<option value="5">客訴處理中</option>
			  </select>
			    <input type="submit" value="點我修改" style="border-style:none; background-color:transparent; color:blue;"> 
			    <input type="hidden" name="ordId" value="${ordersVO.ordId}">
			    <input type="hidden" name="action" value="updateOrdStat_C">
			  </FORM>
			</td>
			<td>
			  <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/detail/detail.do" style="margin-bottom: 0px;">
			    <input type="submit" value="點我查詢" style="border-style:none; background-color:transparent; color:blue;"> 
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