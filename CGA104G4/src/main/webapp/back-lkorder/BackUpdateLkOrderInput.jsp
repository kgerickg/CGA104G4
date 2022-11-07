<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.lkorder.model.*"%>

<%
LkOrderVO lkorderVO = (LkOrderVO) request.getAttribute("lkorderVO"); //EmpServlet.java (Concroller) 存入req的empVO物件 (包括幫忙取出的empVO, 也包括輸入資料錯誤時的empVO物件)
%>


<html>
<head>
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1"/>
<title>每日福袋訂單資料修改</title>


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


<style>
  a {
    color: #5c636a;
    display: inline;
    text-decoration: none;
  }
  
  .btn-secondary {
  color: #fff;
  background-color: #6c757d;
  border: 1px solid #6c757d;
  border-radius: 5px;
}

.btn-secondary:hover {
  color: #fff;
  background-color: #5c636a;
  border: 1px solid #6c757d;
  border-radius: 5px;
}

table tr td:nth-of-type(odd) {
	display: block;
	padding: auto 5%;
	background: #35544E;
	color: white;
	font-size: 14px;
	line-height: 25px;
	border-top-left-radius: 10px;
	border-bottom-left-radius: 10px;
}

.table tr td input {
	display: block;
	background: white;
	border: 3px solid #35544E;
	border-left: 1;
	border-radius: 0 2px 2px 0;
	box-shadow: none;
	margin: 0;
	width: 100%;
	color: #35544E;
	
}


.table{
	width: 50%;
	height: auto;
	margin: 2% auto;
	padding: 30px 90px;
	border-radius: 10px;
	box-shadow: 0 0 20px rgba(0, 0, 0, .6);
	background: white;
}
#table-1{
margin: auto;
padding: 2%;

}

#table-1 td{
	width:50vw;
	text-align: center;
	background-color: #F0F0F0;
	margin: 2% auto;
	border-top-right-radius: 10px;
	border-bottom-right-radius: 10px;
	padding: 15px;
	box-shadow: 0 0 20px rgba(0, 0, 0, .6);
	color: #2F3237;
	font-weight: 700;
}
</style>

</head>
<body>

<script src="../resources/js/storenav.js"></script>
    <!-- 上面是NAV載入 請一定要放在BODY開始的位置 -->
    <!--下面可自由新增內容 -->
	<div style="padding: 3.5%;"></div>


<table id="table-1">
	<tr><td>
		 <h1>每日福袋訂單資料修改</h1>
		 <h4><a href=${pageContext.request.contextPath}/back-lkorder/BackIndexLkorder.jsp>回首頁</a></h4>
	</td></tr>
</table>




<FORM METHOD="post" ACTION=${pageContext.request.contextPath}/LkOrderServlet  name="form1" class="table">
<table>
<%-- 錯誤表列 --%>
<c:if test="${not empty errorMsgs}">
	<font style="color:red">請修正以下錯誤:</font>
	<ul>
		<c:forEach var="message" items="${errorMsgs}">
			<li style="color:red">${message}</li>
		</c:forEach>
	</ul>
</c:if>
	<tr>
		<td style="width:200px">每日福袋訂單編號:<font color=red><b>*</b></font></td>
		<td><%=lkorderVO.getLkOrderId()%></td>
	</tr>
	<tr>
		<td>福袋編號:</td>
		<td><input type="TEXT" name="lkId" size="45" value="<%=lkorderVO.getLkId()%>"/></td>
	</tr>
	<tr>
		<td>會員編號:</td>
		<td><input type="TEXT" name="memId" size="45" value="<%=lkorderVO.getMemId()%>"/></td>
	</tr>
	<tr>
		<td>每日福袋編號:</td>
		<td><input type="TEXT" name="lkTodayId" size="45" value="<%=lkorderVO.getLkTodayId()%>"/></td>
	</tr>
	<tr>
		<td>結帳金額:</td>
		<td><input type="TEXT" name="lkOrdAmt" size="45" value="<%=lkorderVO.getLkOrdAmt()%>"/></td>
	</tr>
	<tr>
		<td>訂單狀態:</td>
		<td><input type="TEXT" name="lkOrdStat" size="45" value="<%=lkorderVO.getLkOrdStat()%>"/></td>
	</tr>
	<tr>
		<td>訂單成立日期:</td>
		<td><input type="TEXT" name="lkOrdTimeS" size="45" value="<%=lkorderVO.getLkOrdTimeS()%>"/></td>
<%-- 		<td><%=lkorderVO.getLkOrdTimeS()%></td> --%>
	</tr>
	<tr>
		<td>取貨日期:</td>
		<td><input name="lkOrdTaketime" id="lkOrdTaketime" type="text" value="<%=lkorderVO.getLkOrdTaketime()%>"></td>
	</tr>
	<tr>
		<td>訂單完成日期:</td>
		<td><input name="lkOrdTimeE" id="lkOrdTimeE" type="text" value="<%=lkorderVO.getLkOrdTimeE()%>"></td>
	</tr>

	<jsp:useBean id="lkorderSvc " scope="page" class="com.lkorder.model.LkOrderService" />


</table>
<br>
<input type="hidden" name="action" value="update">
<input type="hidden" name="lkOrderId" value="<%=lkorderVO.getLkOrderId()%>">
<input type="submit" value="送出修改" class="btn-secondary"></FORM>


<!-- 下面是這個版需要的js可添加各自需要的js檔-->
    <script src="../resources/js/bootstrap.min.js"></script>
    <script src="../resources/js/slick.js"></script>
    <script src="../resources/js/scripts.js"></script>
    <script src="../resources/js/isotope.js"></script>

</body>






</html>