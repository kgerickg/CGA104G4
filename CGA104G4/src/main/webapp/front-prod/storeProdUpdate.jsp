<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.prod.model.*"%>
<%
Integer prodId = (Integer) request.getAttribute("prodId"); //ProdServlet.java (Concroller) 存入req的prodVO物件 (包括幫忙取出的prodVO, 也包括輸入資料錯誤時的prodVO物件)
%>
<!DOCTYPE html>
<html lang="en">

<head>
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


<!-- 頁籤顯示的title -->
<title>商品變更</title>



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
								<h3 style="color: #ffa500;">商品變更</h3>

							</div>
							<!--lg-text end-->

							<form method="post" action="prod.do">
								<h4 class="text-left">&emsp;商品類型：
									&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;
									商品狀態：</h4>
								<jsp:useBean id="prodTypeSvc" scope="page"
									class="com.prodtype.model.ProdTypeService" />
								<div class="row">
									<div class="col-md-6">
										<div class="form-group">
											<select name="prodTypeId" class="form-control">
												<c:forEach var="prodTypeVO" items="${prodTypeSvc.all}">
													<option value="${prodTypeVO.prodTypeId}"
														${(param.prodTypeId==prodTypeVO.prodTypeId)? 'selected':''}>${prodTypeVO.prodTypeName}
												</c:forEach>
											</select>
										</div>
										<!--form-group end-->
									</div>
									<div class="col-md-6">
										<div class="form-group">
											<select name="prodStat" class="form-control">
												<option value="1" selected>上架</option>
												<option value="0">下架</option>
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
								<h4 class="text-left">&emsp;商品上架時間：
									&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;
									商家編號：</h4>
								<div class="row">
									<div class="col-md-6">
										<div class="form-group">
											<input type="text" id="f_date1" name="prodTime"
												class="form-control">${errorMsgs.prodTime}
										</div>
										<!--form-group end-->
									</div>
									<div class="col-md-6">
										<div class="form-group">
											<input type="text" name="storeId" value="${param.storeId}"
												class="form-control">${errorMsgs.storeId}
										</div>
										<!--form-group end-->
									</div>
								</div>
								<h4 class="text-left">&emsp;商品介紹：</h4>
								<div class="col-sm-12">
									<div class="form-group">
										<textarea name="prodCont">${param.prodCont}</textarea>${errorMsgs.prodCont}
									</div>
									<!--form-group end-->
								</div>

								<div class="row">
									<div class="col-md-6"></div>
									<div class="col-md-6"></div>
									<div class="col-md-12">
										<div class="form-group">
											<input type="hidden" name="action" value="update">
											<input type="hidden" name="prodId" value="${param.prodId}">
											<button type="submit" class="btn-default w-100">
												儲存商品資訊 <span></span>
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

	</div>
	<!--wrapper end-->


	<!-- 下面是這個版需要的js可添加各自需要的js檔-->
	<script src="../resources/js/bootstrap.min.js"></script>
	<script src="../resources/js/slick.js"></script>
	<script src="../resources/js/scripts.js"></script>
	<script src="../resources/js/isotope.js"></script>
</body>
	<link rel="stylesheet" type="text/css"
		href="../resources/css/jquery.datetimepicker.css" />
	<script
		src="../resources/js/jquery.datetimepicker.full.js"></script>

	<style>
.xdsoft_datetimepicker .xdsoft_datepicker {
	width: 300px; /* width:  300px; */
}

.xdsoft_datetimepicker .xdsoft_timepicker .xdsoft_time_box {
	height: 151px; /* height:  151px; */
}
</style>

	<script>
		$.datetimepicker.setLocale('zh');
		$('#f_date1').datetimepicker({
			theme : '', //theme: 'dark',
			timepicker : true, //timepicker:true,
			step : 1, //step: 60 (這是timepicker的預設間隔60分鐘)
			format : 'Y-m-d H:i:s', //format:'Y-m-d H:i:s',
			value : new Date(), // value:   new Date(),
		//disabledDates:        ['2017/06/08','2017/06/09','2017/06/10'], // 去除特定不含
		//startDate:	            '2017/07/10',  // 起始日
		//minDate:               '-1970-01-01', // 去除今日(不含)之前
		//maxDate:               '+1970-01-01'  // 去除今日(不含)之後
		});
	</script>

</html>