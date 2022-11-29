<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.lucky.model.*"%>

<%
	LuckyVO luckyVO = (LuckyVO) request.getAttribute("lucky");
	String storeName = "香香麵包"; //先寫死測試用，正式時刪除***************************************************************這裡要改！
%>

<!-- =========================================以下為 datetimepicker 之相關設定========================================== -->
<% 
  java.sql.Date lkTime = null;
  try {
	    lkTime = luckyVO.getLkTime();
   } catch (Exception e) {
	   lkTime = new java.sql.Date(System.currentTimeMillis());
   }
%>
<!-- =========================================以上為 datetimepicker 之相關設定========================================== -->

<html>
<head>
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />
<title>新增福袋</title>

<meta charset="UTF-8">
<!-- 響應式頁面 -->
<meta name="viewport" content="width=device-width, initial-scale=1.0" />

<!-- GOOGLEFONT -->
<link rel="preconnect" href="https://fonts.googleapis.com">
<link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
<link rel="stylesheet" href="https://fonts.googleapis.com/css2?family=Noto+Sans+TC:wght@100;300;400;500;700;900&display=swap">

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
<!-- =========================================以下為 datetimepicker 之相關設定========================================== -->
<link rel="stylesheet" type="text/css" href=${pageContext.request.contextPath}/resources/css/jquery.datetimepicker.css />

<style>
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
}

#table-1 h1, h4 {
	font-family: '微軟正黑體';
	color: black;
	font-weight: bolder;
}

#table-1 td {
	width: 39vw;
	text-align: center;
	background-color: #F0F0F0;
	margin: 2% auto;
	border-top-right-radius: 10px;
	border-bottom-right-radius: 10px;
	padding: 15px;
	box-shadow: 0 0 20px rgba(0, 0, 0, .6);
}

#table-1 a {
	color: #5c636a;
	display: inline;
	text-decoration: none;
}

.radio-container .input {
	width: 5%;
}

<!-- =========================================以下為 datetimepicker 之相關設定========================================== -->
  .xdsoft_datetimepicker .xdsoft_datepicker {width:  300px;}
  .xdsoft_datetimepicker .xdsoft_timepicker .xdsoft_time_box {height: 151px;}
<!-- =========================================以上為 datetimepicker 之相關設定========================================== -->
  
</style>

</head>

<body>

<script src="../resources/js/storenav.js"></script>
<!-- 上面是NAV載入 請一定要放在BODY開始的位置 -->
<!--下面可自由新增內容 -->

<div style="padding: 5%"></div>

<table id="table-1">
					<tr><td>
							<h1>新增福袋</h1>
							<h4><a href="<%=request.getContextPath()%>/front-lucky/select_page.jsp">回首頁</a></h4>
					</td></tr>
</table>

<div class="table">

<h3>資料新增:</h3>
<br>

<%-- 錯誤表列 --%>
<c:if test="${not empty errorMsgs}">
		<font style="color: red">請修正以下錯誤:</font>
		<ul>
		<c:forEach var="message" items="${errorMsgs}">
		<li style="color: red">${message}</li>
		</c:forEach>
		</ul>
</c:if>

<FORM METHOD="post" ACTION="<%=request.getContextPath()%>/lucky/lucky.do" name="form1">
					
<table>

	<jsp:useBean id="storeSvc" scope="page" class="com.store.model.StoreService" />
	<tr>
		<td>商家:</td>
		<td><%=storeName%></td>
	</tr>
		<tr>
		<td>福袋狀態:</td>
		<td><input type="TEXT" name="lkStat" size="45"
			 value="<%= (luckyVO==null)? "1" : luckyVO.getLkStat()%>" /></td>
	</tr>
	<tr>
		<td>福袋名稱:</td>
		<td><input type="TEXT" name="lkName" size="45" 
			 value="<%= (luckyVO==null)? "雙喜臨門" : luckyVO.getLkName()%>" /></td>
	</tr>
	<tr>
		<td>福袋內容:</td>
		<td><input type="TEXT" name="lkCont" size="45"
			 value="<%= (luckyVO==null)? "便當2個" : luckyVO.getLkCont()%>" /></td>
	</tr>
		<tr>
		<td>價格:</td>
		<td><input type="TEXT" name="lkPrc" size="45"
			 value="<%= (luckyVO==null)? "100" : luckyVO.getLkPrc()%>" /></td>
	</tr>
	<tr>
		<td>上架日期:</td>
		<td><input name="lkTime" id="f_date1" type="text"></td>
	</tr>

</table>
<br> 

<input type="hidden" name="action" value="insert">
<input type="submit" value="送出新增" class="btn-secondary">

</FORM>
</div>

<!-- 下面是這個版需要的js可添加各自需要的js檔-->
<script src="../resources/js/bootstrap.min.js"></script>
<script src="../resources/js/slick.js"></script>
<script src="../resources/js/scripts.js"></script>
<script src="../resources/js/isotope.js"></script>

<!-- =========================================以下為 datetimepicker 之相關設定========================================== -->    
<script src="<%=request.getContextPath()%>/front-lucky/datetimepicker/jquery.js"></script>
<script src="<%=request.getContextPath()%>/front-lucky/datetimepicker/jquery.datetimepicker.full.js"></script>
<script>
        $.datetimepicker.setLocale('zh');
        $('#f_date1').datetimepicker({
	       theme: '',              
	       timepicker:false,       
	       step: 1,                
	       format:'Y-m-d',         
		   value: '<%=lkTime%>', 
        });        
</script>
    
</body>

</html>