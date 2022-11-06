<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.store.model.*"%>

<%
StoreVO storeVO = (StoreVO) request.getAttribute("storeVO");
%>
<html>
<head>
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />
<title>店家資料新增 - addStore.jsp</title>

<style>
table#table-1 {
	background-color: lightyellow;
	border: 2px solid black;
	text-align: center;
}

table#table-1 h4 {
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
	width: 450px;
	background-color: white;
	margin-top: 1px;
	margin-bottom: 1px;
}

table, th, td {
	word-break: keep-all;
	word-wrap: break-word;
	border: 0px solid #CCCCFF;
}

th, td {
	padding: 1px;
}
</style>

</head>
<body bgcolor='white'>

	<table id="table-1">
		<tr>
			<td>
				<h3>店家資料新增</h3>
			</td>
			<td>
				<h4>
					<a href="select_page.jsp">回首頁</a>
				</h4>
			</td>
		</tr>
	</table>

	<h3>資料新增:</h3>

	<%-- 錯誤表列 --%>
	<c:if test="${not empty errorMsgs}">
		<font style="color: red">請修正以下錯誤:</font>
		<ul>
			<c:forEach var="message" items="${errorMsgs}">
				<li style="color: red">${message}</li>
			</c:forEach>
		</ul>
	</c:if>

	<FORM METHOD="post" ACTION="store.do" name="form1">
		<table>
			<tr>
				<td>會員帳號:</td>
				<td><input type="TEXT" name="storeAcc" size="55"
					placeholder="請輸入帳號"
					value="<%=(storeVO == null) ? "" : storeVO.getStoreAcc()%>" /></td>
			</tr>
			<tr>
				<td>會員密碼:</td>
				<td><input type="password" name="storePwd" size="55"
					placeholder="至少一個數字,一個小寫英文字母,一個大寫英文字母,長度 6 ~ 16字元"
					value="<%=(storeVO == null) ? "" : storeVO.getStorePwd()%>" /></td>
			</tr>
			<tr>
				<td>會員名稱:</td>
				<td><input type="TEXT" name="storeName" size="55"
					placeholder="請輸入會員名稱"
					value="<%=(storeVO == null) ? "" : storeVO.getStoreName()%>" /></td>
			</tr>
			<tr>
				<td>會員統編:</td>
				<td><input type="TEXT" name="storeGui" size="55"
					placeholder="請輸入統編"
					value="<%=(storeVO == null) ? "" : storeVO.getStoreGui()%>" /></td>
			</tr>
			<tr>
				<td>負責人:</td>
				<td><input type="TEXT" name="storeRep" size="55"
					placeholder="請輸入負責人"
					value="<%=(storeVO == null) ? "" : storeVO.getStoreRep()%>" /></td>
			</tr>
			<tr>
				<td>店家電話:</td>
				<td><input type="TEXT" name="storeTel" size="55"
					placeholder="請輸入電話"
					value="<%=(storeVO == null) ? "" : storeVO.getStoreTel()%>" /></td>
			</tr>
			<tr>
				<td>店家傳真:</td>
				<td><input type="TEXT" name="storeFax" size="55"
					placeholder="請輸入傳真號碼"
					value="<%=(storeVO == null) ? "" : storeVO.getStoreFax()%>" /></td>
			</tr>
			<tr>
				<td>店家地址:</td>
				<td><div id="twzipcode"></div>
					<input type="TEXT" name="storeAd" size="10" placeholder="請輸入店家地址"
					value="<%=(storeVO == null) ? "" : storeVO.getStoreAd()%>" /></td>

			</tr>
			<tr>
				<td>聯絡人:</td>
				<td><input type="TEXT" name="storeCon" size="55"
					placeholder="請輸入聯絡人"
					value="<%=(storeVO == null) ? "" : storeVO.getStoreCon()%>" /></td>
			</tr>
			<tr>
				<td>聯絡人電話:</td>
				<td><input type="TEXT" name="storeConTel" size="55"
					placeholder="請輸入聯絡人電話"
					value="<%=(storeVO == null) ? "" : storeVO.getStoreConTel()%>" /></td>
			</tr>
			<tr>
				<td>聯絡人地址:</td>
				<td><input type="TEXT" name="storeConAd" size="55"
					placeholder="請輸入聯絡人地址"
					value="<%=(storeVO == null) ? "" : storeVO.getStoreConAd()%>" /></td>
			</tr>
			<tr>
				<td>店家信箱:</td>
				<td><input type="TEXT" name="storeEmail" size="55"
					placeholder="請輸入信箱"
					value="<%=(storeVO == null) ? "" : storeVO.getStoreEmail()%>" /></td>
			</tr>

		</table>
		<br> <input type="hidden" name="storeId" value="1"> <input
			type="hidden" name="action" value="insert"> <input
			type="submit" value="送出新增">
	</FORM>
	<script
		src="https://ajax.googleapis.com/ajax/libs/jquery/2.0.0/jquery.min.js"></script>
	<script
		src="https://cdn.jsdelivr.net/npm/jquery-twzipcode@1.7.14/jquery.twzipcode.min.js"></script>
	<script>
		$("#twzipcode").twzipcode();
		$("input[name='zipcode']").hide();
	</script>
</body>