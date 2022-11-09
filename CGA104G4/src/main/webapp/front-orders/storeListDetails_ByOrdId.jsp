<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.detail.model.*"%>

<jsp:useBean id="storeListDetails_ByOrdId" scope="request" type="java.util.Set<DetailVO>" /> <!-- 於EL此行可省略 -->
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
    <title>訂單明細</title>
<style>
  table#table-2 {
    text-align: center;
    margin-left:auto; 
	margin-right:auto;
  }
  table#table-2 h4 {
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
    <script src="../resources/js/storenav.js"></script>
    <!-- 上面是NAV載入 請一定要放在BODY開始的位置 -->
    <!--下面可自由新增內容 -->
<table id="table-2">
	<tr><td><h3>訂單明細</h3></td></tr>
</table>

<table>
	<tr>
		<th>訂單編號</th>
		<th>商品編號</th>
		<th>商品名稱</th>
		<th>商品單價</th>
		<th>商品數量</th>
	</tr>
	
	<c:forEach var="detailVO" items="${storeListDetails_ByOrdId}" >
	<tr>
			<td>${detailVO.ordId}</td>
			<td>${detailVO.prodId}</td>
			<td>${detailVO.prodVO.prodName}</td>
			<td>${detailVO.prodVO.prodPrc}</td>
			<td>${detailVO.prodQty}</td>
		</tr>
	</c:forEach>
</table>
    <!-- 下面是這個版需要的js可添加各自需要的js檔-->
    <script src="../resources/js/bootstrap.min.js"></script>
    <script src="../resources/js/slick.js"></script>
    <script src="../resources/js/scripts.js"></script>
    <script src="../resources/js/isotope.js"></script>
</body>

</html>