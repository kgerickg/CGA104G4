<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.lucky.model.*"%>

<!DOCTYPE html>
<html lang="en" >

<head>

  <meta charset="UTF-8">
  

  <title>福袋預約</title>  
  
  <link rel='stylesheet' href='https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css'>
  
<style>
h1{text-align:center;}
.badge-notify{
    background:red;
    position:relative;
    top: -20px;
    right: 10px;
  }
  .my-cart-icon-affix {
    position: fixed;
    z-index: 999;
  }
</style>    
  
  <script>
  if (document.location.search.match(/type=embed/gi)) {
    window.parent.postMessage("resize", "*");
  }
</script>

</head>

<body translate="no" >
  <div class="page-header">
    <h1>福袋預約
      <div style="float: right; cursor: pointer;">
        <span class="glyphicon glyphicon-shopping-cart my-cart-icon"><span class="badge badge-notify my-cart-badge"></span></span>
      </div>
    </h1>
  </div> 
  
<ul>
  <li>
    <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/lucky/lucky.do" >
        <b>顯示全部</b>
        <input type="hidden" name="action" value="getAllForMember">
        <input type="submit" value="送出">  
   </FORM>  
  </li>
   
  <jsp:useBean id="luckySvc" scope="page" class="com.lucky.model.LuckyService" />
  
  <li>
     <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/lucky/lucky.do" >
       <b>選擇福袋名稱:</b>
       <select size="1" name="luckyId">
         <c:forEach var="luckyVO" items="${luckySvc.all}" > 
          <option value="${luckyVO.luckyId}">${luckyVO.lkName}
         </c:forEach>   
       </select>
       <input type="hidden" name="action" value="getOneForMember">
       <input type="submit" value="送出">
     </FORM>
  </li>

</ul>
  
<div class="row">

	<c:forEach var="lucky" items="${list}">
		<div class="col-sm-6 col-md-3 text-center">
	      <img src="<%=request.getContextPath()%>/lucky/lucky.do?action=getImg&luckyId=${lucky.luckyId}" width="150px" height="150px">
	      <br>
	      ${lucky.lkName}<strong>$${lucky.lkPrc}</strong>
	      <br>
	      <button class="btn btn-danger my-cart-btn" data-id="${lucky.luckyId}" data-name="${lucky.lkName}" data-summary="${lucky.lkCont}" data-price="${lucky.lkPrc}" data-quantity="1" data-image="<%=request.getContextPath()%>/lucky/lucky.do?action=getImg&luckyId=${lucky.luckyId}">我要預約</button>
	      ${lucky.lkCont}
	    </div>
	</c:forEach>
    
</div>
    <script src="https://cpwebassets.codepen.io/assets/common/stopExecutionOnTimeout-1b93190375e9ccc259df3a57c1abc0e64599724ae30d7ea4c6877eb615f89387.js"></script>

  <script src='https://mwt.tw/lib/js/jquery-2.2.3.min.js'></script>
<script src='https://mwt.tw/lib/js/bootstrap.min.js'></script>
<script src='https://mwt.tw/lib/js/jquery.mycart.js'></script>
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
<%--改這段來傳資料
    checkoutCart: function (products, totalPrice, totalQuantity) {
        let checkoutString = "Total Price: " + totalPrice + "\nTotal Quantity: " + totalQuantity;
        checkoutString += "\n\n id \t name \t summary \t price \t quantity \t image path";
        $.each(products, function () {
          checkoutString += "\n " + this.id + " \t " + this.name + " \t " + this.summary + " \t " + this.price + " \t " + this.quantity + " \t " + this.image;
        });
        alert(checkoutString);
        console.log("checking out", products, totalPrice, totalQuantity);
    },    
--%>    
    getDiscountPrice: function (products, totalPrice, totalQuantity) {
      console.log("calculating discount", products, totalPrice, totalQuantity);
      return totalPrice * 1;
    } });

  $("#addNewProduct").click(function (event) {
    let currentElementNo = $(".row").children().length + 1;
    $(".row").append('<div class="col-md-3 text-center"><img src="images/img_empty.png" width="150px" height="150px"><br>product ' + currentElementNo + ' - <strong>$' + currentElementNo + '</strong><br><button class="btn btn-danger my-cart-btn" data-id="' + currentElementNo + '" data-name="product ' + currentElementNo + '" data-summary="summary ' + currentElementNo + '" data-price="' + currentElementNo + '" data-quantity="1" data-image="images/img_empty.png">Add to Cart</button><a href="#" class="btn btn-info">Details</a></div>');
  });
});
//# sourceURL=pen.js
    </script>

  <script src="https://cpwebassets.codepen.io/assets/editor/iframe/iframeRefreshCSS-5e03f34e38152f20eb79c96b0b89c2d99c5085e9ae9386dc71e2f0b3c30bf513.js"></script>
</body>

</html>