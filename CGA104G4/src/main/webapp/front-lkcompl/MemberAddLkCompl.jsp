<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<html>
<head>
<title>客訴頁面</title>

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

<style>

#upper-table{
	width:40%;
	text-align: center;
	background-color: #F0F0F0;
	margin: auto;
	border-radius: 10px;
	box-shadow: 0 0 20px rgba(0, 0, 0, .6);
	padding: 10px;
	margin-bottom: 10px;
}

#contain-table {
	width: 40%;
	height: 70%;
 	padding: 50px 20px; 
	margin: auto auto 5% auto;
	border-radius: 10px;
	box-shadow: 0 0 20px rgba(0, 0, 0, .6);
	background: white;
}

ul li{
margin-bottom: 20px;
}

.btn-secondary {
	color: #fff;
	background-color: #6c757d;
	border: 1px solid #6c757d;
	border-radius: 10%;
	margin: auto 10px;
}

.btn-secondary:hover {
	color: #fff;
	background-color: #5c636a;
/* 	border: 1px solid #6c757d; */
}


</style>

</head>
<body>
	<script src="../resources/js/membernav.js"></script>
	<!-- 上面是NAV載入 請一定要放在BODY開始的位置 -->
	<!--下面可自由新增內容 -->
<div style="padding:5vw"></div>

	<div id="upper-table">
		<tr>
			<td>
				<h3 style="font-size: 40px; font-weight: 700;color: black;">會員客訴</h3>
				<h4>
					<a style="color: #6c757d"
						href=${pageContext.request.contextPath}/front-lkcompl/MemberIndexLkCompl.jsp>回首頁</a>
				</h4>
			</td>
		</tr>
	</div>

	<br>

	<div  id="contain-table">

		<ul>
			<li>

				<FORM METHOD="post" ACTION=${pageContext.request.contextPath}/member-lkcompl/lkcompl.do>
					<b style="color: black">輸入訂單編號：</b> <br><!-- 抓取會員編號中的訂單 -->
					<input type="text" name="lkOrderId" style="border-radius: 8px ; border: .5px solid grey"> 
				<br><br>
					<b style="color: black">輸入客訴內容：</b><br>
					<textarea name="lkcompltext" rows="6" cols="40" required style="border-radius: 10px"></textarea>
					<br><br>
					<input type="submit" value="送出" class="btn-secondary">
					<input type="reset" value="清除" class="btn-secondary">

				</FORM>
			</li>
		</ul>
	</div>

	<!-- 下面是這個版需要的js可添加各自需要的js檔-->
	<script src="../resources/js/bootstrap.min.js"></script>
	<script src="../resources/js/slick.js"></script>
	<script src="../resources/js/scripts.js"></script>
	<script src="../resources/js/isotope.js"></script>
</body>
</html>