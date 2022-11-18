<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<html>
<head>
<title>首頁</title>

<style>
table#table-1 {
	width: 450px;
	background-color: lightyellow;
	margin-top: 5px;
	margin-bottom: 10px;
	border: 3px ridge Gray;
	height: 80px;
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
</head>
<body bgcolor='white'>
	<a href="http://localhost:8080/CGA104G4/index.html">123</a>
	<table id="table-1">
		<tr>
			<td><h3>首頁</h3></td>
		</tr>
	</table>

	<h3>店家搜尋:</h3>

	<%-- 錯誤表列 --%>
	<c:if test="${not empty errorMsgs}">
		<font style="color: red">請修正以下錯誤:</font>
		<ul>
			<c:forEach var="message" items="${errorMsgs}">
				<li style="color: red">${message}</li>
			</c:forEach>
		</ul>
	</c:if>

	<ul>
		<li><a href='listAllStore.jsp'>店家總表</a><br> <br></li>


		<li>
			<FORM METHOD="post" ACTION="store.do">
				<b>輸入店家編號 :</b> <input type="text" name="storeId"
					placeholder="請輸入店家編號"> <input type="hidden" name="action"
					value="getOne_For_Display"> <input type="submit" value="送出">
			</FORM>
		</li>

		<jsp:useBean id="storeSvc" scope="page"
			class="com.store.model.StoreService" />

		<li>
			<FORM METHOD="post" ACTION="store.do">
				<b>選擇店家編號:</b> <select size="1" name="storeId">
					<option disabled selected>請選擇店家編號</option>
					<c:forEach var="storeVO" items="${storeSvc.all}">
						<option value="${storeVO.storeId}">${storeVO.storeId}
					</c:forEach>
				</select> <input type="hidden" name="action" value="getOne_For_Display">
				<input type="submit" value="送出">
			</FORM>
		</li>

		<li>
			<FORM METHOD="post" ACTION="store.do">
				<b>選擇店家名稱:</b> <select size="1" name="storeId">
					<option disabled selected>請選擇店家名稱</option>
					<c:forEach var="storeVO" items="${storeSvc.all}">
						<option value="${storeVO.storeId}">${storeVO.storeName}
					</c:forEach>
				</select> <input type="hidden" name="action" value="getOne_For_Display">
				<input type="submit" value="送出">
			</FORM>
		</li>
		<li>
			<FORM METHOD="post" ACTION="store.do">
				<b>選擇店家地區:</b>
				<div id="twzipcode"></div>
			    <input type="hidden" name="action" value="getOne_For_Display">
				<input type="submit" value="送出">
			</FORM>
		</li>
		
	</ul>



	<h3>店家管理</h3>

	<ul>
		<li><a href='addStore.jsp'>新增店家</a></li>
	</ul>
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/2.0.0/jquery.min.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/jquery-twzipcode@1.7.14/jquery.twzipcode.min.js"></script>
	<script>
		$("#twzipcode").twzipcode();
		$("input[name='zipcode']").hide();
	</script>
</body>
</html>