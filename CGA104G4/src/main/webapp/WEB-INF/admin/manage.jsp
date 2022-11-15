<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" href="../index/css/nav.css" />
<link rel="stylesheet" href="css/member.css" />
<title>manager.jsp</title>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta3/dist/css/bootstrap.min.css"
	rel="stylesheet"
	integrity="sha384-eOJMYsd53ii+scO/bJGFsiCZc+5NDVN2yr8+0RDqr0Ql0h+rP48ckxlpbzKgwra6"
	crossorigin="anonymous">
</head>
<body>
	<table class="table">
		<thead>
			<tr>
				<th scope="col">編號</th>
				<th scope="col">管理員帳號</th>
				<th scope="col">密碼</th>
				<th scope="col">姓名</th>
				<th scope="col">狀態</th>
			</tr>
		</thead>
		<tbody>
			<c:if test="${not empty adminList}">
				<c:forEach var="admin" items="${adminList}">
					<tr>
						<td>${admin.admId}</td>
						<td id="admAcc${admin.admId}">${admin.admAcc}</td>
						<td id="admPwd${admin.admId}">${admin.admPwd}</td>
						<td id="admName${admin.admId}" contenteditable="true">${admin.admName}</td>
						<td>
							<select id="admAccStat${admin.admId}">
								<option value="0">停權</option>
								<option value="1" <c:if test="${admin.admAccStat == 1}">selected</c:if>>正常</option>
							</select>
						</td>
						<td>
							<button type="button" class="btn btn-dark" onclick="onSaveClick(${admin.admId})">儲存</button>
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