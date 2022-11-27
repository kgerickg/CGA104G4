<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.lkorder.model.*"%>

<%
LkOrderSelectVO lkorderVO = (LkOrderSelectVO) request.getAttribute("lkorderVO"); //EmpServlet.java (Concroller) 存入req的empVO物件 (包括幫忙取出的empVO, 也包括輸入資料錯誤時的empVO物件)
%>




<html>
<head>
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />
<title>每日福袋訂單資料修改</title>


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
	href=${pageContext.request.contextPath}/resources/css/all.min.css>
<link rel="stylesheet" type="text/css"
	href=${pageContext.request.contextPath}/resources/css/animate.min.css>
<link rel="stylesheet" type="text/css"
	href=${pageContext.request.contextPath}/resources/css/slick.css>
<link rel="stylesheet" type="text/css"
	href=${pageContext.request.contextPath}/resources/css/slick-theme.css>
<link rel="stylesheet" type="text/css"
	href=${pageContext.request.contextPath}/resources/css/flaticon.css>
<link rel="stylesheet" type="text/css"
	href=${pageContext.request.contextPath}/resources/css/style.css>
<link rel="stylesheet" type="text/css"
	href=${pageContext.request.contextPath}/resources/css/nav.css>
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

.table tr td:nth-of-type(odd) {
	display: block;
	padding-left: 20px;
	background: #35544E;
	color: white;
	font-size: 14px;
	line-height: 35px;
	border-top-left-radius: 10px;
	border-bottom-left-radius: 10px;
	margin-bottom: 10px;
}

.table tr td input {
/* 	display: block; */
	background: white;
	border: 3px solid #35544E;
	border-left: 1;
	border-radius: 0 2px 2px 0;
	box-shadow: none;
	margin: 0;
	width: 100%;
	color: #35544E;
	margin-bottom: 10px;
	line-height: 27px;
}

.table {
	width: 50%;
	height: auto;
	margin: 2% auto;
	padding: 30px 90px;
	border-radius: 10px;
	box-shadow: 0 0 20px rgba(0, 0, 0, .6);
	background: white;
}

#table-1 {
	margin: auto;
	padding: 2%;
}

#table-1 td {
	width: 50vw;
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

.radio-container .input {
	width: 5%;
}
</style>

</head>
<body>

	<script src=${pageContext.request.contextPath}/resources/js/storenav.js></script>
	<!-- 上面是NAV載入 請一定要放在BODY開始的位置 -->
	<!--下面可自由新增內容 -->
	<div style="padding-bottom: 10%"></div>


	<table id="table-1">
		<tr>
			<td>
				<h1>福袋訂單資料修改</h1>
				<h4>
					<a
						href=${pageContext.request.contextPath}/front-lkorder/StoreIndexLkorder.jsp>回首頁</a>
				</h4>
			</td>
		</tr>
	</table>




	<FORM METHOD="post"
		ACTION=${pageContext.request.contextPath}/LkOrderServlet name="form1"
		class="table">
		<table>
			<%-- 錯誤表列 --%>
			<c:if test="${not empty errorMsgs}">
				<font style="color: red">請修正以下錯誤:</font>
				<ul>
					<c:forEach var="message" items="${errorMsgs}">
						<li style="color: red">${message}</li>
					</c:forEach>
				</ul>
			</c:if>
			<input type="hidden" name="lkName" size="45"
				value="<%=lkorderVO.getLkName()%>" />
			<input type="hidden" name="memName" size="45"
				value="<%=lkorderVO.getMemName()%>" />
			<tr>
				<td style="width: 200px">福袋訂單編號:<font color=red><b>*</b></font></td>
				<td><%=lkorderVO.getLkOrderId()%></td>

			</tr>
			<tr>
				<td>福袋編號:</td>
				<td><input type="TEXT" name="lkId" size="45"
					value="<%=lkorderVO.getLkId()%>" /></td>
			</tr>
			<tr>
				<td>會員編號:</td>
				<td><input type="TEXT" name="memId" size="45"
					value="<%=lkorderVO.getMemId()%>" /></td>
			</tr>
			<tr>
				<td>每日福袋編號:</td>
				<td><input type="TEXT" name="lkTodayId" size="45"
					value="<%=lkorderVO.getLkTodayId()%>" /></td>
			</tr>
			<tr>
				<td>結帳金額:</td>
				<td><input type="TEXT" name="lkOrdAmt" size="45"
					value="<%=lkorderVO.getLkOrdAmt()%>" /></td>
			</tr>
			<tr>
<!-- 				<td>訂單狀態:</td> -->
<!-- 				<td><input type="TEXT" name="lkOrdStat" size="45" -->
<%-- 					value="<%=lkorderVO.getLkOrdStat()%>" /></td> --%>
								<td>訂單狀態:</td>
				<td class="radio-container">	
					<input class="input" type="radio" name="lkOrdStat" value="0"
					${(lkorderVO.lkOrdStat==0)? 'checked':'' } />待取貨　 
					<input class="input" type="radio" name="lkOrdStat" value="1"
					${(lkorderVO.lkOrdStat==1)? 'checked':'' } />已取貨 <br>
					<input class="input" type="radio" name="lkOrdStat" value="2"
					${(lkorderVO.lkOrdStat==2)? 'checked':'' } />完成　	
					<input class="input" type="radio" name="lkOrdStat" value="3"
					${(lkorderVO.lkOrdStat==3)? 'checked':'' } />客訴處理中	
				</td>
					
					
			</tr>
			<tr>
				<td>訂單成立日期:</td>
				<td><input type="TEXT" name="lkOrdTimeS" size="45"  class="f_date1"
					value="<%=lkorderVO.getLkOrdTimeS()%>" /></td>
				<%-- 		<td><%=lkorderVO.getLkOrdTimeS()%></td> --%>
			</tr>
			<tr>
				<td>取貨日期:</td>
				<td><input name="lkOrdTaketime" id="lkOrdTaketime" type="text"  class="f_date2"
					value="<%=lkorderVO.getLkOrdTaketime()%>"></td>
			</tr>
			<tr>
				<td>訂單完成日期:</td>
				<td><input name="lkOrdTimeE" id="lkOrdTimeE" type="text"  class="f_date3"
					value="<%=lkorderVO.getLkOrdTimeE()%>"></td>
			</tr>

			<jsp:useBean id="lkorderSvc " scope="page"
				class="com.lkorder.model.LkOrderService" />


		</table>
		<br> <input type="hidden" name="action" value="update"> <input
			type="hidden" name="lkOrderId" value="<%=lkorderVO.getLkOrderId()%>">
		<input type="submit" value="送出修改" class="btn-secondary">
	</FORM>









	<!-- 下面是這個版需要的js可添加各自需要的js檔-->
	<script
		src=${pageContext.request.contextPath}/resources/js/bootstrap.min.js></script>
	<script src=${pageContext.request.contextPath}/resources/js/slick.js></script>
	<script src=${pageContext.request.contextPath}/resources/js/scripts.js></script>
	<script src=${pageContext.request.contextPath}/resources/js/isotope.js></script>



	 <script src="https://cdn.bootcdn.net/ajax/libs/jquery-datetimepicker/2.5.20/jquery.datetimepicker.js"></script>
	<script src="https://cdn.bootcdn.net/ajax/libs/jquery-datetimepicker/2.5.20/jquery.datetimepicker.full.js"></script>


	<script>
        $.datetimepicker.setLocale('zh');
        $('.f_date1').datetimepicker({
           theme: '',              //theme: 'dark',
 	       timepicker:false,       //timepicker:true,
 	       step: 1,                //step: 60 (這是timepicker的預設間隔60分鐘)
 	       format:'Y-m-d',         //format:'Y-m-d H:i:s',
 		   value: '<%=lkorderVO.getLkOrdTimeS()%>', // value:   new Date(),
           //maxDate:               '+1970-01-01'  // 去除今日(不含)之後
        });
    </script>
    <script>
        $.datetimepicker.setLocale('zh');
        $('.f_date2').datetimepicker({
           theme: '',              //theme: 'dark',
 	       timepicker:false,       //timepicker:true,
 	       step: 1,                //step: 60 (這是timepicker的預設間隔60分鐘)
 	       format:'Y-m-d',         //format:'Y-m-d H:i:s',
 		   value: '<%=lkorderVO.getLkOrdTaketime()%>', // value:   new Date(),
           //maxDate:               '+1970-01-01'  // 去除今日(不含)之後
        });
    </script>
    <script>
        $.datetimepicker.setLocale('zh');
        $('.f_date3').datetimepicker({
           theme: '',              //theme: 'dark',
 	       timepicker:false,       //timepicker:true,
 	       step: 1,                //step: 60 (這是timepicker的預設間隔60分鐘)
 	       format:'Y-m-d',         //format:'Y-m-d H:i:s',
 		   value: '<%=lkorderVO.getLkOrdTimeE()%>', // value:   new Date(),
           //maxDate:               '+1970-01-01'  // 去除今日(不含)之後
        });
    </script>
</body>

<link rel="stylesheet" type="text/css" href="https://cdn.bootcdn.net/ajax/libs/jquery-datetimepicker/2.5.20/jquery.datetimepicker.css" />




</html>