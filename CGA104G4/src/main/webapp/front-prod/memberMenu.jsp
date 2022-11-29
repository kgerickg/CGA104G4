<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.prod.model.*"%>

<jsp:useBean id="listProdTypeIds_ByStoreId_M" scope="request" type="java.util.Set<ProdVO>" />
<jsp:useBean id="prodSvc" scope="page" class="com.prod.model.ProdService" />

<!DOCTYPE html>
<html lang="en">

<head>
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


<!-- 頁籤顯示的title -->
<title>MENU</title>

</head>

<body>
	<script src="../resources/js/membernav.js"></script>
	<!-- 上面是NAV載入 請一定要放在BODY開始的位置 -->
	<!--下面可自由新增內容 -->
	<div class="page-loading">
		<img src="../resources/images/loader.gif" alt="">
	</div>
	<!--page-loading end-->
	<div class="wrapper">
		<section class="sec-block"></section>
		<section class="sec-block p-0">
			<div class="container">
				<div class="section-title text-center">
					<span>Amazing taste </span>
					<h2 class="text-capitalize">Menu</h2>
				</div>
				<!--sec-title end-->
				<div class="popular-dishes style2 text-center">
					<div class="options">
						<div class="option-isotop">
							<ul id="filter" class="option-set filters-nav"
								data-option-key="filter">
								<c:forEach var="prodVO"	items="${listProdTypeIds_ByStoreId_M}">
									<li><a href="../prod/prod.do?action=listProds_ByStoreIdAndProdTypeId_M&storeId=${param.storeId}" data-option-value=".${prodVO.prodTypeId}">${prodVO.prodTypeVO.prodTypeName}</a></li> 									
								</c:forEach>
							</ul>
						</div>
					</div>
					<!--options end-->
					<div class="row">
						<div class="masonary">
							<c:forEach var="prodVO"
								items="${prodSvc.getProdsByStoreIdAndProdTypeId(storeId, 1)}">
								<div class="col-lg-4 col-md-4 col-sm-6 meat 1">
									<div class="pd-item">
										<div class="pd-thumbnail">
											<img src="../ShowImageFromDB?photoId=${prodVO.prodId}" alt="" class="w-100">
										</div>
										<h3 class="semi-bold text-capitalize">
											<a href="#" title="">${prodVO.prodName}</a>
										</h3>
										<p>${prodVO.prodCont}</p>
										<strong class="semi-bold">$${prodVO.prodPrc}</strong> <a
											href="#" id="${prodVO.prodId}"
											class="btn-default gradient-bg half-radius height-2 cart">加入購物車
											<span></span>
										</a>
									</div>
								</div>
							</c:forEach>
							<c:forEach var="prodVO"
								items="${prodSvc.getProdsByStoreIdAndProdTypeId(storeId, 2)}">
								<div class="col-lg-4 col-md-4 col-sm-6 meat 2">
									<div class="pd-item">
										<div class="pd-thumbnail">
											<img src="../ShowImageFromDB?photoId=${prodVO.prodId}" alt="" class="w-100">
										</div>
										<h3 class="semi-bold text-capitalize">
											<a href="#" title=""></a>${prodVO.prodName}</h3>
										<p>${prodVO.prodCont}</p>
										<strong class="semi-bold">$${prodVO.prodPrc}</strong> <a
											href="#" id="${prodVO.prodId}"
											class="btn-default gradient-bg half-radius height-2 cart">加入購物車
											<span></span>
										</a>
									</div>
								</div>
							</c:forEach>
							<c:forEach var="prodVO"
								items="${prodSvc.getProdsByStoreIdAndProdTypeId(storeId, 3)}">
								<div class="col-lg-4 col-md-4 col-sm-6 meat 3">
									<div class="pd-item">
										<div class="pd-thumbnail">
											<img src="../ShowImageFromDB?photoId=${prodVO.prodId}" alt="" class="w-100">
										</div>
										<h3 class="semi-bold text-capitalize">
											<a href="#" title="">${prodVO.prodName}</a>
										</h3>
										<p>${prodVO.prodCont}</p>
										<strong class="semi-bold">$${prodVO.prodPrc}</strong> <a
											href="#" id="${prodVO.prodId}"
											class="btn-default gradient-bg half-radius height-2 cart">加入購物車
											<span></span>
										</a>
									</div>
								</div>
							</c:forEach>
							<c:forEach var="prodVO"
								items="${prodSvc.getProdsByStoreIdAndProdTypeId(storeId, 4)}">
								<div class="col-lg-4 col-md-4 col-sm-6 meat 4">
									<div class="pd-item">
										<div class="pd-thumbnail">
											<img src="../ShowImageFromDB?photoId=${prodVO.prodId}" alt="" class="w-100">
										</div>
										<h3 class="semi-bold text-capitalize">
											<a href="#" title="">${prodVO.prodName}</a>
										</h3>
										<p>${prodVO.prodCont}</p>
										<strong class="semi-bold">$${prodVO.prodPrc}</strong> <a
											href="#" id="${prodVO.prodId}"
											class="btn-default gradient-bg half-radius height-2 cart">加入購物車
											<span></span>
										</a>
									</div>
								</div>
							</c:forEach>
							<c:forEach var="prodVO"
								items="${prodSvc.getProdsByStoreIdAndProdTypeId(storeId, 5)}">
								<div class="col-lg-4 col-md-4 col-sm-6 meat 5">
									<div class="pd-item">
										<div class="pd-thumbnail">
											<img src="../ShowImageFromDB?photoId=${prodVO.prodId}" alt="" class="w-100">
										</div>
										<h3 class="semi-bold text-capitalize">
											<a href="#" title="">${prodVO.prodName}</a>
										</h3>
										<p>${prodVO.prodCont}</p>
										<strong class="semi-bold">$${prodVO.prodPrc}</strong> <a
											href="#" id="${prodVO.prodId}"
											class="btn-default gradient-bg half-radius height-2 cart">加入購物車
											<span></span>
										</a>
									</div>
								</div>
							</c:forEach>
							<c:forEach var="prodVO"
								items="${prodSvc.getProdsByStoreIdAndProdTypeId(storeId, 6)}">
								<div class="col-lg-4 col-md-4 col-sm-6 meat 6">
									<div class="pd-item">
										<div class="pd-thumbnail">
											<img src="../ShowImageFromDB?photoId=${prodVO.prodId}" alt="" class="w-100">
										</div>
										<h3 class="semi-bold text-capitalize">
											<a href="#" title="">${prodVO.prodName}</a>
										</h3>
										<p>${prodVO.prodCont}</p>
										<strong class="semi-bold">$${prodVO.prodPrc}</strong> <a
											href="#" id="${prodVO.prodId}"
											class="btn-default gradient-bg half-radius height-2 cart">加入購物車
											<span></span>
										</a>
									</div>
								</div>
							</c:forEach>
							<c:forEach var="prodVO"
								items="${prodSvc.getProdsByStoreIdAndProdTypeId(storeId, 7)}">
								<div class="col-lg-4 col-md-4 col-sm-6 meat 7">
									<div class="pd-item">
										<div class="pd-thumbnail">
											<img src="../ShowImageFromDB?photoId=${prodVO.prodId}" alt="" class="w-100">
										</div>
										<h3 class="semi-bold text-capitalize">
											<a href="#" title="">${prodVO.prodName}</a>
										</h3>
										<p>${prodVO.prodCont}</p>
										<strong class="semi-bold">$${prodVO.prodPrc}</strong> <a
											href="#" id="${prodVO.prodId}"
											class="btn-default gradient-bg half-radius height-2 cart">加入購物車
											<span></span>
										</a>
									</div>
								</div>
							</c:forEach>
							<c:forEach var="prodVO"
								items="${prodSvc.getProdsByStoreIdAndProdTypeId(storeId, 8)}">
								<div class="col-lg-4 col-md-4 col-sm-6 meat 8">
									<div class="pd-item">
										<div class="pd-thumbnail">
											<img src="../ShowImageFromDB?photoId=${prodVO.prodId}" alt="" class="w-100">
										</div>
										<h3 class="semi-bold text-capitalize">
											<a href="#" title="">${prodVO.prodName}</a>
										</h3>
										<p>${prodVO.prodCont}</p>
										<strong class="semi-bold">$${prodVO.prodPrc}</strong> <a
											href="#" id="${prodVO.prodId}"
											class="btn-default gradient-bg half-radius height-2 cart">加入購物車
											<span></span>
										</a>
									</div>
								</div>
							</c:forEach>

						</div>
						<!--masonary end-->
					</div>
				</div>
				<!--popular-dishes end-->
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
	<script>	
	
	let acart =  document.querySelectorAll(".cart");
	acart.forEach(e=>{
		e.addEventListener("click",function(e){

			prodId = this.id;
			let path = window.location.pathname;
			let webCtx = path.substring(0, path.indexOf('/', 1));
			let url = webCtx + "/cart/add?prodId=" + prodId + "&storeId=${param.storeId}";
			e.preventDefault();
			alert(this.id);
			fetch(url, {method: 'get'});
		})
	})
	
	</script>

<%if (request.getAttribute("listProds_ByStoreIdAndProdTypeId_M")!=null){%>
       <jsp:useBean id="listProds_ByStoreIdAndProdTypeId_M" scope="request" type="java.util.Set<ProdVO>" />
<%} %>
</body>
</html>