<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.lkorder.model.*"%>

<%
LkOrderVO lkOrderVO = (LkOrderVO) request.getAttribute("lkOrderVO");
%>
<%-- --<%= lkOrderVO==null %>--${lkOrderVO.lkOrderId}-- --%>
<html>
<head>
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />
<title>���u��Ʒs�W - addEmp.jsp</title>

<meta charset="UTF-8">
<!-- �T�������� -->
<meta name="viewport" content="width=device-width, initial-scale=1.0" />

 <!-- GOOGLEFONT -->
    <link rel="preconnect" href="https://fonts.googleapis.com">
    <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
    <link href="https://fonts.googleapis.com/css2?family=Noto+Sans+TC:wght@100;300;400;500;700;900&display=swap"
        rel="stylesheet">

    <!-- �U���O�o�ӼҪO�ݭn��css�Фŧ�� �Y���ƪ��ݭn�Ъ����g�s��css�\�L�h�N�i�H�F -->
    <link rel="stylesheet" type="text/css" href="../resources/css/all.min.css">
    <link rel="stylesheet" type="text/css" href="../resources/css/animate.min.css">
    <link rel="stylesheet" type="text/css" href="../resources/css/slick.css">
    <link rel="stylesheet" type="text/css" href="../resources/css/slick-theme.css">
    <link rel="stylesheet" type="text/css" href="../resources/css/flaticon.css">
    <link rel="stylesheet" type="text/css" href="../resources/css/style.css">
    <link rel="stylesheet" type="text/css" href="../resources/css/nav.css">
    <!-- �w�g�w���Jjquery�F���ݭnjquery�i�H�����ϥ� -->
    <script src="https://code.jquery.com/jquery-3.6.1.min.js"></script>

    <!-- �бN�л\�Ϊ�css��m�����ѤU�� -->

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
	display: block;
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
	font-family: '�L�n������';
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
</style>

</head>
<body>

    <script src="../resources/js/storenav.js"></script>
    <!-- �W���ONAV���J �Ф@�w�n��bBODY�}�l����m -->
    <!--�U���i�ۥѷs�W���e -->
    <div style="padding: 5%"></div>



				<table id="table-1">
					<tr>
						<td>
							<h1>�s�W�q��</h1>
							<h4>
								<a
									href=${pageContext.request.contextPath}/front-lkorder/StoreIndexLkorder.jsp>�^����</a>
							</h4>
						</td>
					</tr>
				</table>

				<div class="table">

					<!-- <table style="margin:auto"> -->
					<!-- 	<tr> -->
					<!-- 		<th><h3>�C��ֳU�q��s�W</h3></th> -->
					<!-- 		<th><h4><a href=${pageContext.request.contextPath}/back-lkorder/BackIndexLkorder.jsp>�^����</a></h4></th> -->
					<!-- 	</tr> -->
					<!-- </table> -->

					<h3>��Ʒs�W:</h3>
					<br>

					<%-- ���~���C --%>
					<c:if test="${not empty errorMsgs}">
						<font style="color: red">�Эץ��H�U���~:</font>
						<ul>
							<c:forEach var="message" items="${errorMsgs}">
								<li style="color: red">${message}</li>
							</c:forEach>
						</ul>
					</c:if>



					<FORM METHOD="post"
						ACTION=${pageContext.request.contextPath}/LkOrderServlet
						name="form1">
						<table>
							<tr>
								<td style="width: 200px">�ֳU�s��:</td>
								<td><input type="TEXT" name="lkId" size="600" /></td>
							</tr>
							<tr>
								<td>�|���s��:</td>
								<td><input type="TEXT" name="memId" size="45" /></td>
							</tr>
							<tr>
								<td>�C��ֳU�s��:</td>
								<td><input type="TEXT" name="lkTodayId" size="45" /></td>
							</tr>
							<tr>
								<td>���b���B:</td>
								<td><input type="TEXT" name="lkOrdAmt" size="45" /></td>
							</tr>
							<tr>
								<td>�q�檬�A:</td>
								<td><input type="TEXT" name="lkOrdStat" size="45" /></td>
							</tr>
							<tr>
								<td>�q�榨�ߤ��:</td>
								<td><input name="lkOrdTimeS" id="lkOrdTimeS" type="text"></td>
							</tr>
							<tr>
								<td>���f���:</td>
								<td><input name="lkOrdTaketime" id="lkOrdTaketime"
									type="text"></td>
							</tr>
							<tr>
								<td>�q�槹�����:</td>
								<td><input name="lkOrdTimeE" id="lkOrdTimeE" type="text"></td>
							</tr>

							<jsp:useBean id="lkorderSvc " scope="page"
								class="com.lkorder.model.LkOrderService" />


						</table>
						<br> <input type="hidden" name="action" value="insert">
						<input type="submit" value="�e�X�s�W" class="btn-secondary">
					</FORM>
				</div>










	<!-- �U���O�o�Ӫ��ݭn��js�i�K�[�U�ۻݭn��js��-->
    <script src="../resources/js/bootstrap.min.js"></script>
    <script src="../resources/js/slick.js"></script>
    <script src="../resources/js/scripts.js"></script>
    <script src="../resources/js/isotope.js"></script>
    
</body>




</html>