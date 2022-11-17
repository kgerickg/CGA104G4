<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.detail.model.*"%>

<jsp:useBean id="ordersSvc" scope="page" class="com.orders.model.OrdersService" />

<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    
    <!-- 響應式頁面 -->
    <meta name="viewport" content="width=device-width, initial-scale=1.0">

    <!-- GOOGLEFONT -->
    <link rel="preconnect" href="https://fonts.googleapis.com">
    <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
    <link href="https://fonts.googleapis.com/css2?family=Noto+Sans+TC:wght@100;300;400;500;700;900&display=swap"
        rel="stylesheet">

    <!-- 下面是這個模板需要的css請勿改動 若有排版需要請直接寫新的css蓋過去就可以了 -->
    <link rel="stylesheet" type="text/css" href="../resources/css/all.min.css">
    <link rel="stylesheet" type="text/css" href="../resources/css/animate.min.css">
    <link rel="stylesheet" type="text/css" href="../resources/css/slick.css">
    <link rel="stylesheet" type="text/css" href="../resources/css/slick-theme.css">
    <link rel="stylesheet" type="text/css" href="../resources/css/flaticon.css">
    <link rel="stylesheet" type="text/css" href="../resources/css/style.css">
    <link rel="stylesheet" type="text/css" href="../resources/css/nav.css">
   
    <!-- 已經預載入jquery了有需要jquery可以直接使用 -->
    <script src="https://code.jquery.com/jquery-3.6.1.min.js"></script>
    
    <!-- 請將覆蓋用的css放置此註解下方 -->
   
    <!-- 頁籤顯示的title -->
	<title>我的訂單</title>
	
</head>

<body>
    <script src="../resources/js/membernav.js"></script>
    <!-- 上面是NAV載入 請一定要放在BODY開始的位置 -->
    
    <!--下面可自由新增內容 -->  
<div class="page-loading">
	<img src="../resources/images/loader.gif" alt="">
</div><!--page-loading end-->

<div class="wrapper">
 <section class="sec-block">
  <div class="container">
   <div class="row">
    <div class="col-lg-8">
     <div class="profile-section">
      <ul class="nav nav-tabs" id="myTab" role="tablist">
       <li class="nav-item">
        <a class="nav-link active" id="orders-tab" data-toggle="tab" href="#orders" role="tab" aria-controls="orders" aria-selected="true">我的訂單</a>
       </li>
      </ul>
     <div class="tab-content" id="myTabContent">
      <div class="tab-pane fade show active" id="orders" role="tabpanel" aria-labelledby="orders-tab">
       <div class="order-tables-sec">
       
        <div class="ord-head">
         <ul>
          <li class="date">訂單成立時間</li>
          <li class="delivery">商店（商店編號）</li>
          <li class="amount">訂單金額</li>
          <li>訂單狀態</li>
         </ul>
        </div><!--ord-head end-->
                                        
        <div class="ord-tablez">

         <c:forEach var="ordersVO" items="${ordersSvc.getOrdersByMemId(memId)}">
         <div class="oc-table">
      
          <div class="oct-table-head">
           <ul>
             <li class="date">${ordersVO.ordTime}</li>
             <li class="delivery">${ordersVO.storeVO.storeName}（商店編號：${ordersVO.storeVO.storeId}）
             </li>
             <li class="amount">$${ordersVO.ordAmt}</li>
             <li class="status">
              <c:if test="${ordersVO.ordStat==0}">正在等待商家接單</c:if>
			  <c:if test="${ordersVO.ordStat==1}">商家已接單，訂單準備中</c:if>
			  <c:if test="${ordersVO.ordStat==2}">訂單已備妥，請前往領取</c:if>
			  <c:if test="${ordersVO.ordStat==3}">訂單已完成</c:if>
			  <c:if test="${ordersVO.ordStat==4}">訂單已取消</c:if>
			  <c:if test="${ordersVO.ordStat==5}">客訴處理中</c:if>
             </li>
             
           </ul>
          <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/orders/orders.do" style="">
			    <input type="submit" value="訂單明細"> 
			    <input type="hidden" name="ordId" value="${ordersVO.ordId}">
			    <input type="hidden" name="action" value="listDetails_ByOrdId_B"></FORM>
            <a href="#" title="" class="tog-down"><i class="fa fa-angle-down"></i></a>
		  </div><!--oct-table-head end-->
         
          
		  <div class="oct-table-body">
		   <ul>
      	<c:forEach var="detailVO" items="${listDetails_ByOrdId}" >
		    <li>
		     <h4>${detailVO.prodVO.prodName}&nbsp;&nbsp;$${detailVO.prodVO.prodPrc}&nbsp;&nbsp;&nbsp;<span>x${detailVO.prodQty}</span></h4>
		    </li>
		     </c:forEach>	  
		   </ul>
		  </div><!--oct-table-body end-->	
		  
          </div><!--oc-table end-->
          </c:forEach>
       
        </div><!--ord-tablez end-->
         
		  
       </div><!--order-tables-sec end-->
      </div>     
      <div class="tab-pane fade" id="info" role="tabpanel" aria-labelledby="info-tab">
       <div class="order-tables-sec">
        <div class="ord-tablez">
        </div><!--ord-tablez end-->
       </div><!--order-tables-sec end-->
      </div>
     </div>
    </div><!--profile-section end-->
   </div>             
    <div class="col-lg-4">
     <div class="sidebar">
      <div class="widget widget-help">
       <h3 class="widget-title">Need help?</h3>
       <p>If you have more questions please let us know. We will answer as soon as possible.</p>
       <a href="#" title="" class="btn-default height-2">Contact us <span></span></a>
      </div><!--widget-help end-->
     </div><!--sidebar end-->
    </div>
   </div>
  </div>
 </section>
</div><!--wrapper end-->
<%if (request.getAttribute("listDetails_ByOrdId")!=null){%>
       <jsp:useBean id="listDetails_ByOrdId" scope="request" type="java.util.Set<DetailVO>" />
<%} %>
<!-- 下面是這個版需要的js可添加各自需要的js檔-->
<script src="../resources/js/bootstrap.min.js"></script>
<script src="../resources/js/slick.js"></script>
<script src="../resources/js/scripts.js"></script>
<script src="../resources/js/isotope.js"></script>

</body>
</html>