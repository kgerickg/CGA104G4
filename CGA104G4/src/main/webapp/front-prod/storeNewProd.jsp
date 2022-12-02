<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.prod.model.*"%>

<!DOCTYPE html>
<html lang="en">

<head>
<meta charset="UTF-8">
<!-- 響應式頁面 -->
<meta name="viewport" content="width=device-width, initial-scale=1.0">

<!-- GOOGLEFONT -->
<link rel="preconnect" href="https://fonts.googleapis.com">
<link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
<link href="https://fonts.googleapis.com/css2?family=Noto+Sans+TC:wght@100;300;400;500;700;900&display=swap" rel="stylesheet">

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
<title>上架新產品！</title>
</head>

<body>
	<script src="../resources/js/storenav.js"></script>
	<!-- 上面是NAV載入 請一定要放在BODY開始的位置 -->
	<!--下面可自由新增內容 -->
	<div class="page-loading">
		<img src="../resources/images/loader.gif" alt="">
	</div>
	<!--page-loading end-->
	<div class="wrapper">
		<section class="sec-block">
			<div class="container">
				<div class="row justify-content-center">
					<div class="col-lg-7">
						<div class="delitaste-form text-center">
							<div class="lg-text">
								<h3 style="color: #ffa500;">上架新產品！</h3>
							</div>
							<!--lg-text end-->
							<form method="post" action="<%=request.getContextPath()%>/prod/prod.do" enctype="multipart/form-data">
								<h4 class="text-left">&emsp;商品最後更新時間：
									&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;
									商家編號：</h4>
								<div class="row">
									<div class="col-md-6">
										<div class="form-group">
											<input type="datetime" id="f_date1" name="prodTime"
												class="form-control" readonly="readonly">${errorMsgs.prodTime}
										</div>
										<!--form-group end-->
									</div>
									<div class="col-md-6">
										<div class="form-group">
											<input type="text" name="storeId" value="${sessionScope.storeId}"
												class="form-control" readonly="readonly">${errorMsgs.storeId}
										</div>
										<!--form-group end-->
									</div>
								</div>
								<h4 class="text-left">&emsp;商品類型：
									&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;
									商品狀態：</h4>
								<jsp:useBean id="prodTypeSvc" scope="page"
									class="com.prodtype.model.ProdTypeService" />
								
								<div class="row">
									<div class="col-md-6">
										<div class="form-group">
											<select name="prodTypeId" class="form-control">											
												<option value="1">麵類</option>												
												<option value="2">飯類</option>												
												<option value="3">麵包類</option>												
												<option value="4">小菜類</option>												
												<option value="5">飲品</option>												
												<option value="6">點心</option>												
												<option value="7">湯品</option>												
												<option value="8">其他</option>												
											</select>
										</div>
										<!--form-group end-->
									</div>
									<div class="col-md-6">
										<div class="form-group">
											<select name="prodStat" class="form-control">
												<option value="1">上架販售中</option>
												<option value="0">下架不顯示</option>
											</select>
										</div>
										<!--form-group end-->
									</div>
								</div>
								<h4 class="text-left">&emsp;商品名稱：
									&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;
									商品售價：</h4>
								<div class="row">
									<div class="col-md-6">
										<div class="form-group">
											<input type="text" name="prodName" value="${param.prodName}"
												class="form-control">${errorMsgs.prodName}
										</div>
										<!--form-group end-->
									</div>
									<div class="col-md-6">
										<div class="form-group">
											<input type="text" name="prodPrc" value="${param.prodPrc}"
												class="form-control">${errorMsgs.prodPrc}
										</div>
										<!--form-group end-->
									</div>
								</div>
								<h4 class="text-left">&emsp;商品介紹：</h4>
								<div class="row">
									<div class="col-md-12">
										<div class="form-group">
											<textarea name="prodCont" style="height: 170px;">${param.prodCont}</textarea>${errorMsgs.prodCont}
										</div>
										<!--form-group end-->
									</div>
								</div>
								
							
								<jsp:useBean id="photoSvc" scope="page" class="com.photo.model.PhotoService" />
								<h4 class="text-left">&emsp;商品相片：&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;
									<label for="photoPic" class="btn btn-info" style="background-color: #66B3FF; border: none; color: black;">
										<input id="photoPic" type="file" name="photoPic" onclick="previewImage()" multiple="multiple" style="display: none;">
											<i class="fa fa-photo" style="background-color: #66B3FF">上傳相片</i>
									</label>
								</h4>
								<div class="row">
									<div class="col-md-6">
										<div class="form-group">
											<div>
												<img src="../resources/images/new.jpg" width="300px">
											</div>
										</div>
									
									</div>

									<div class="col-md-6">
										<div class="form-group">
											<div id="blob_holder"></div>
										</div>
									</div>
						
								</div>
								<div>
									<div class="col-md-12">
										<div class="form-group">
											<input type="hidden" name="action" value="insert"> 
											<button type="submit" class="btn-default w-100">
												上架新產品！ <span></span>
											</button>
										</div>
									</div>
								</div>
							</form>
						</div>
					</div>
				</div>
			</div>
		</section>
	</div>

	<!--wrapper end-->
	<footer>
		<div class="top-footer">
			<div class="fixed-bg bg3"></div>

			<div class="container">
				<div class="row align-items-center">
					<div class="col-lg-3 col-md-6"></div>
					<div class="col-lg-6 col-md-6"></div>
					<div class="col-lg-3 col-md-6"></div>
				</div>
			</div>
		</div>
		<div class="bottom-footer">
			<div class="container">
				<div class="row align-items-center">
					<div class="col-lg-6">
						<div class="copyright">
							<p>Copyright &copy; 2022.TibaMe All rights reserved.</p>
						</div>
						<!--copyright end-->
					</div>
					<div class="col-lg-6"></div>
				</div>
			</div>
		</div>


	</footer>
	<!--footer end-->


	<!-- 下面是這個版需要的js可添加各自需要的js檔-->
	<script src="../resources/js/bootstrap.min.js"></script>
	<script src="../resources/js/slick.js"></script>
	<script src="../resources/js/scripts.js"></script>
	<script src="../resources/js/isotope.js"></script>
</body>
<link rel="stylesheet" type="text/css"
	href="../resources/css/jquery.datetimepicker.css" />
<script src="../resources/js/jquery.datetimepicker.full.js"></script>

<style>
.xdsoft_datetimepicker .xdsoft_datepicker {
	width: 300px; /* width:  300px; */
}

.xdsoft_datetimepicker .xdsoft_timepicker .xdsoft_time_box {
	height: 151px; /* height:  151px; */
}
</style>

<script type="text/javascript">
	$.datetimepicker.setLocale('zh');
	$('#f_date1').datetimepicker({
		theme : '',
		timepicker : true,
		step : 1,
		format : 'Y-m-d H:i:s',
		value : new Date(),
	});

	//清除提示信息
	function hideContent(d) {
		document.getElementById(d).style.display = "none";
	}

	//照片上傳-預覽用
	let filereader_support = typeof FileReader != 'undefined';
	if (!filereader_support) {
		alert("No FileReader support");
	}
	acceptedTypes = {
		'image/png' : true,
		'image/jpeg' : true,
		'image/gif' : true
	};
	function previewImage() {
		let photoPic = document.getElementById("photoPic");
		photoPic.addEventListener("change", function(event) {
			let files = event.target.files || event.dataTransfer.files;
			for (let i = 0; i < files.length; i++) {
				previewfile(files[i])
			}
		}, false);
	}
	function previewfile(file) {
		if (filereader_support === true && acceptedTypes[file.type] === true) {
			let reader = new FileReader();
			reader.onload = function(event) {
				let image = new Image();
				image.src = event.target.result;
				image.width = 300;

				if (blob_holder.hasChildNodes()) {
					blob_holder.removeChild(blob_holder.childNodes[0]);
				}
				blob_holder.appendChild(image);
			};
			reader.readAsDataURL(file);
			document.getElementById('submit').disabled = false;
		} else {
			blob_holder.innerHTML = "<div  style='text-align: left;'>"
					+ "● filename: "
					+ file.name
					+ "<br>"
					+ "● ContentTyp: "
					+ file.type
					+ "<br>"
					+ "● size: "
					+ file.size
					+ "bytes"
					+ "<br>"
					+ "● 上傳ContentType限制: <b> <font color=red>image/png、image/jpeg、image/gif </font></b></div>";
			document.getElementById('submit').disabled = true;
		}
	}
</script>
</html>