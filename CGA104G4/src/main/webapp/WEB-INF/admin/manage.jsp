<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
  <meta charset="UTF-8" />
  <link href=../resources/back-stage/assets/css/bootstrap.css rel="stylesheet" />
    <!-- FontAwesome Styles-->
    <link href=../resources/back-stage/assets/css/font-awesome.css rel="stylesheet" />
    <!-- Custom Styles-->
    <link href=../resources/back-stage/assets/css/custom-styles.css rel="stylesheet" />
    <!-- Google Fonts-->
    <link href='http://fonts.googleapis.com/css?family=Open+Sans' rel='stylesheet' type='text/css' />
    <link rel="stylesheet" href=/resources/back-stage/assets/js/Lightweight-Chart/cssCharts.css>
    <title>manager.jsp</title>
</head>
<body>
	<table class="table">
		<thead>
			<tr>
				<th scope="col">ID</th>
				<th scope="col">USERNAME</th>
				<th scope="col">PASSWORD</th>
				<th scope="col">NICKNAME</th>
				<th scope="col">PASS</th>
				<th scope="col">ROLE</th>
				<th scope="col"></th>
				<th scope="col"></th>
			</tr>
		</thead>
		<tbody>
			<c:if test="${not empty adminList}">
				<c:forEach var="admin" items="${adminList}">
					<tr>
						<td>${admin.admId}</td>
						<td id="admAcc${admin.admId}">${admin.admAcc}</td>
						<td id="admPwd${admin.admId}">${admin.adminPwd}</td>
						<td id="admName${admin.admId}" contenteditable="true">${admin.adminName}</td>
						<td>
							<select id="pass${admin.admId}">
								<option value="0">false</option>
								<option value="1" <c:if test="${admin.admAccStat}">selected</c:if>>true</option>
							</select>
						</td>
						<td>
							<button type="button" class="btn btn-dark" onclick="onSaveClick(${admin.admiId})">Save</button>
						</td>
						<td>
							<button type="button" class="btn btn-danger" onclick="onRemoveClick(${admin.admId})">Remove</button>
						</td>
					</tr>
				</c:forEach>
			</c:if>
		</tbody>
	</table>
	<div id="msg" class="error"></div>
	<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta3/dist/js/bootstrap.bundle.min.js"
		integrity="sha384-JEW9xMcG8R+pH31jmWH6WWP0WintQrMb4s7ZOdauHnUtxwoG2vI5DkLtS3qm9Ekf"
		crossorigin="anonymous"></script>
	<script src="../index/js/nav.js"></script>
	<script src="js/manage.js"></script>
</body>
</html>