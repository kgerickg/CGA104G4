<%@page import="org.hibernate.internal.build.AllowSysOut"%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.lucky.model.*"%>


<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<!-- 響應式頁面 -->
<meta name="viewport" content="width=device-width, initial-scale=1.0">

<!-- GOOGLEFONT -->
<link rel="preconnect" href="https://fonts.googleapis.com">
<link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
<link rel="stylesheet" href="https://fonts.googleapis.com/css2?family=Noto+Sans+TC:wght@100;300;400;500;700;900&display=swap">

<!-- 下面是這個模板需要的css請勿改動 若有排版需要請直接寫新的css蓋過去就可以了 -->
<link rel="stylesheet" type="text/css" href=${pageContext.request.contextPath}/resources/css/all.min.css>
<link rel="stylesheet" type="text/css" href=${pageContext.request.contextPath}/resources/css/animate.min.css>
<link rel="stylesheet" type="text/css" href=${pageContext.request.contextPath}/resources/css/slick.css>
<link rel="stylesheet" type="text/css" href=${pageContext.request.contextPath}/resources/css/slick-theme.css>
<link rel="stylesheet" type="text/css" href=${pageContext.request.contextPath}/resources/css/flaticon.css>
<link rel="stylesheet" type="text/css" href=${pageContext.request.contextPath}/resources/css/style.css>
<link rel="stylesheet" type="text/css" href=${pageContext.request.contextPath}/resources/css/nav.css>

<!-- 已經預載入jquery了有需要jquery可以直接使用 -->
<script src="https://code.jquery.com/jquery-3.6.1.min.js"></script>

<!-- 請將覆蓋用的css放置此註解下方 -->
<link rel='stylesheet' href='https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css'>


<!-- 頁籤顯示的title -->
<title>福袋瀏覽</title>


<style>
table  {
    border-collapse: collapse;
    font-size: 0.9em;
    font-family: sans-serif;
    min-width: 400px;
    box-shadow: 0 0 20px rgba(0, 0, 0, .6);
    text-align: center;
    border-radius: 1%;
    margin: 1vw auto;
    }
    
table  tr:nth-of-type(even) {
    background-color: #f3f3f3;
    color: black;
    border-bottom: 1px solid #dddddd;
}

table th,td {
    padding: 5px 5px;
    color: black;
}

#table-1 td{
	width:60vw;
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
a{
  text-decoration: none;
  font-weight: bold;
}

thead th{
  color:#D5DDE5;;
  background:#1b1e24;
  border-bottom:4px solid #9ea7af;
  border-right: 1px solid #343a45;
  font-size:1rem;
  font-weight: 100;
  padding:10px; 
}
</style>

</head>

<body>
<script src=${pageContext.request.contextPath}/resources/js/membernav.js></script>
<!-- 上面是NAV載入 請一定要放在BODY開始的位置 -->
<!--下面可自由新增內容 -->

<div style="padding: 4%"></div>
    
<table id="table-1">

<ul>
  <li>
    <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/lucky/lucky.do" >
        <b>顯示全部</b>
        <input type="hidden" name="action" value="getAllForMember">
        <input type="submit" value="送出">  
   </FORM>  
  </li>
   
  <jsp:useBean id="luckySvc" scope="page" class="com.lucky.model.LuckyService" />

  
</ul>
  
<div class="row">

<c:forEach var="lucky" items="${list}">

	<div class="col-sm-6 col-md-3 text-center">
<%-- 	      
	<img src="<%=request.getContextPath()%>/lucky/lucky.do?action=getImg&luckyId=${lucky.luckyId}" width="150px" height="150px">
--%>
		<br>
		<img src="<%=request.getContextPath()%>/front-lucky/images/4.png" width="150px" height="150px">	      
		<br>
		${lucky.lkName}<strong>$${lucky.lkPrc}</strong>
		<br>
		<button class="btn btn-danger my-cart-btn" data-id="${lucky.luckyId}" data-name="${lucky.lkName}" data-summary="${lucky.lkCont}" data-price="${lucky.lkPrc}" data-quantity="1" data-image="<%=request.getContextPath()%>/front-lucky/images/4.png">我要預約</button>
		${lucky.lkCont}
		<br>
		<br>
	</div>
</c:forEach>
	
<c:forEach var="lucky" items="${listForST}">

	<div class="col-sm-6 col-md-3 text-center">
<%-- 	      
	<img src="<%=request.getContextPath()%>/lucky/lucky.do?action=getImg&luckyId=${lucky.luckyId}" width="150px" height="150px">
--%>
		<br>
		<img src="<%=request.getContextPath()%>/front-lucky/images/4.png" width="150px" height="150px">	      
		<br>
		${lucky.lkName}<strong>$${lucky.lkPrc}</strong>
		<br>
		<button class="btn btn-danger my-cart-btn" data-id="${lucky.luckyId}" data-name="${lucky.lkName}" data-summary="${lucky.lkCont}" data-price="${lucky.lkPrc}" data-quantity="1" data-image="<%=request.getContextPath()%>/front-lucky/images/4.png">我要預約</button>
		${lucky.lkCont}
		<br>
		<br>
	</div>
</c:forEach>
    
</div>

</table>

<!-- 下面是這個版需要的js可添加各自需要的js檔-->
<script src=${pageContext.request.contextPath}/resources/js/bootstrap.min.js></script>
<script src=${pageContext.request.contextPath}/resources/js/slick.js></script>
<script src=${pageContext.request.contextPath}/resources/js/scripts.js></script>
<script src=${pageContext.request.contextPath}/resources/js/isotope.js></script>

<script src="https://cpwebassets.codepen.io/assets/common/stopExecutionOnTimeout-1b93190375e9ccc259df3a57c1abc0e64599724ae30d7ea4c6877eb615f89387.js"></script>

<script src='https://mwt.tw/lib/js/jquery-2.2.3.min.js'></script>
<script src='https://mwt.tw/lib/js/bootstrap.min.js'></script>
<script src='https://mwt.tw/lib/js/jquery.mycart.js'></script>

<script src="https://cpwebassets.codepen.io/assets/editor/iframe/iframeRefreshCSS-5e03f34e38152f20eb79c96b0b89c2d99c5085e9ae9386dc71e2f0b3c30bf513.js"></script>

<script id="rendered-js" >
$(function () {

  let goToCartIcon = function ($addTocartBtn) {
    let $cartIcon = $(".my-cart-icon");
    let $image = $('<img width="30px" height="30px" src="' + $addTocartBtn.data("image") + '"/>').css({ "position": "fixed", "z-index": "999" });
    $addTocartBtn.prepend($image);
    let position = $cartIcon.position();
    $image.animate({
      top: position.top,
      left: position.left },
    500, "linear", function () {
      $image.remove();
    });
  };

  $('.my-cart-btn').myCart({
    currencySymbol: '$',
    classCartIcon: 'my-cart-icon',
    classCartBadge: 'my-cart-badge',
    classProductQuantity: 'my-product-quantity',
    classProductRemove: 'my-product-remove',
    classCheckoutCart: 'my-cart-checkout',
    affixCartIcon: true,
    showCheckoutModal: true,
    numberOfDecimals: 2,
    clickOnAddToCart: function ($addTocart) {
      goToCartIcon($addTocart);
    },
    afterAddOnCart: function (products, totalPrice, totalQuantity) {
      console.log("afterAddOnCart", products, totalPrice, totalQuantity);
    },
    clickOnCartIcon: function ($cartIcon, products, totalPrice, totalQuantity) {
      console.log("cart icon clicked", $cartIcon, products, totalPrice, totalQuantity);
    },
    checkoutCart: function (products, totalPrice, totalQuantity) {
      let checkoutString = "Total Price: " + totalPrice + "\nTotal Quantity: " + totalQuantity;
      checkoutString += "\n\n id \t name \t summary \t price \t quantity \t image path";
      $.each(products, function () {
        checkoutString += "\n " + this.id + " \t " + this.name + " \t " + this.summary + " \t " + this.price + " \t " + this.quantity + " \t " + this.image;
      });
      alert(checkoutString);
      console.log("checking out", products, totalPrice, totalQuantity);
    },
    getDiscountPrice: function (products, totalPrice, totalQuantity) {
      console.log("calculating discount", products, totalPrice, totalQuantity);
      return totalPrice * 1;
    } });

});
//# sourceURL=pen.js
</script>

</body>
</html>