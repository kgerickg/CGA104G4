<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.detail.model.*"%>

<jsp:useBean id="ordersSvc" scope="page"
	class="com.orders.model.OrdersService" />
<jsp:useBean id="detailSvc" scope="page"
	class="com.detail.model.DetailService" />
<!DOCTYPE html>
<html lang="en">

<head>
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
	href="../resources/css/all.min.css">
<link rel="stylesheet" type="text/css"
	href="../resources/css/animate.min.css">
<link rel="stylesheet" type="text/css" href="../resources/css/slick.css">
<link rel="stylesheet" type="text/css"
	href="../resources/css/slick-theme.css">
<link rel="stylesheet" type="text/css"
	href="../resources/css/flaticon.css">
<link rel="stylesheet" type="text/css" href="../resources/css/style.css">
<link rel="stylesheet" type="text/css" href="../resources/css/nav.css">

<!-- 已經預載入jquery了有需要jquery可以直接使用 -->
<script src="https://code.jquery.com/jquery-3.6.1.min.js"></script>

<!-- 請將覆蓋用的css放置此註解下方 -->
<script src="https://kit.fontawesome.com/33764e6a90.js"
	crossorigin="anonymous"></script>
<!-- 頁籤顯示的title -->
<title>訂單管理</title>

</head>

<body>
	<script src="../resources/js/storenav.js"></script>
	<!-- 上面是NAV載入 請一定要放在BODY開始的位置 -->

	<!--下面可自由新增內容 -->
	<div class="page-loading">
		<img src="../resources/images/loader.gif" alt="">
	</div>
	<!--page-loading end-->

	<div class="wrapper">
		<section class="sec-block">
			<div class="container">
				<div class="row">
					<div class="col-lg-8">
						<div class="profile-section">
							<ul class="nav nav-tabs" id="myTab" role="tablist">
								<li class="nav-item"><a class="nav-link active"
									id="orders-tab" data-toggle="tab" href="#orders" role="tab"
									aria-controls="orders" aria-selected="true">訂單管理</a></li>
							</ul>
							<div class="tab-content" id="myTabContent">
								<div class="tab-pane fade show active" id="orders"
									role="tabpanel" aria-labelledby="orders-tab">
									<div class="order-tables-sec">
										<div class="ord-head">
											<ul>
												<li class="date" style="text-align: left; width: 140px;">訂單成立時間</li>
												<li class="delivery" style="text-align: left;width: 190px;">客戶（會員編號）</li>
												<li class="amount" style="text-align: left; width: 120px;">訂單金額</li>
												<li class="status" style="text-align: left; width: 200px;">訂單狀態</li>
											</ul>
										</div>
										<!--ord-head end-->

										<div class="ord-tablez">

											<c:forEach var="ordersVO"
												items="${ordersSvc.getOrdersByStoreId(storeId)}">
												<div class="oc-table">

													<div class="oct-table-head">
														&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;
														&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;
														<a
															href="../orders/orders.do?action=updateOrdStat_A&ordId=${ordersVO.ordId}&ordStat=1"><i
															class="fa-regular fa-calendar-check fa-xl" style="color: #02DF82"></i></a>&emsp;
														<a
															href="../orders/orders.do?action=updateOrdStat_A&ordId=${ordersVO.ordId}&ordStat=2"><i
															class="fa-solid fa-pizza-slice fa-xl" style="color: #FFD306"></i></a>&emsp;
														<a
															href="../orders/orders.do?action=updateOrdStat_A&ordId=${ordersVO.ordId}&ordStat=3"><i
															class="fa-regular fa-handshake fa-xl" style="color: #FF8000"></i></a>&emsp;
														<a
															href="../orders/orders.do?action=updateOrdStat_A&ordId=${ordersVO.ordId}&ordStat=4"><i
															class="fa-regular fa-calendar-xmark fa-xl" style="color: #FF5151"></i></a>
														<ul>
															<li class="date" style="text-align: left; width: 140px;">${ordersVO.ordTime}</li>
															<li class="delivery"
																style="width: 190px; text-align: left;">${ordersVO.memberVO.memName}（會員編號：${ordersVO.memberVO.memId}）</li>
															<li class="amount"
																style="text-align: left; width: 120px;">$${ordersVO.ordAmt}</li>
															<li class="status"
																style="text-align: left; width: 200px; color: #0072E3">
																<c:if test="${ordersVO.ordStat==0}">正在等待商家接單</c:if>
																<c:if test="${ordersVO.ordStat==1}">商家已接單，訂單準備中</c:if>
																<c:if test="${ordersVO.ordStat==2}">訂單已備妥，待領取</c:if>
																<c:if test="${ordersVO.ordStat==3}">訂單已完成</c:if>
																<c:if test="${ordersVO.ordStat==4}">訂單已取消</c:if>
														</ul>
														<a
															href="../detail/detail.do?action=listDetails_ByOrdId_A&ordId=${ordersVO.ordId}"
															class="tog-down"><i class="fa fa-angle-down"></i></a>
													</div>
													<!--oct-table-head end-->


													<div class="oct-table-body">
														<ul>
															<c:forEach var="detailVO" items="${listDetails_ByOrdId}">
																<li>
																	<h4>${detailVO.prodVO.prodName}&nbsp;&nbsp;$${detailVO.prodVO.prodPrc}&nbsp;&nbsp;&nbsp;<span>x${detailVO.prodQty}</span>
																	</h4>
																</li>
															</c:forEach>
														</ul>
													</div>
													<!--oct-table-body end-->

												</div>
												<!--oc-table end-->
											</c:forEach>

										</div>
										<!--ord-tablez end-->


									</div>
									<!--order-tables-sec end-->
								</div>
							</div>
						</div>
					</div>
				</div>
			</div>
		</section>
<!-- 		<footer> -->
<!-- 			<div class="bottom-footer"> -->
<!-- 				<div class="container"> -->
<!-- 					<div class="row align-items-center"> -->
<!-- 						<div class="col-lg-6"> -->
<!-- 							<div class="copyright"> -->
<!-- 								<p>Copyright &copy; 2022.TibaMe All rights reserved.</p> -->
<!-- 							</div> -->
<!-- 							copyright end -->
<!-- 						</div> -->
<!-- 						<div class="col-lg-6"></div> -->
<!-- 					</div> -->
<!-- 				</div> -->
<!-- 			</div> -->
<!-- 		</footer> -->
		<!--footer end-->
	</div>
	<!--wrapper end-->
	<!-- 下面是這個版需要的js可添加各自需要的js檔-->
	<script src="../resources/js/bootstrap.min.js"></script>
	<script src="../resources/js/slick.js"></script>
	<script src="../resources/js/scripts.js"></script>
	<script src="../resources/js/isotope.js"></script>
	<%
	if (request.getAttribute("listDetails_ByOrdId_A") != null) {
	%>
	<jsp:useBean id="listDetails_ByOrdId" scope="request"
		type="java.util.Set<DetailVO>" />
	<%
	}
	%>
</body>
</html>