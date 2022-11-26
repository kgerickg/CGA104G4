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
    <title>商品變更</title>


</head>

<body>
<script src="../resources/js/storenav.js"></script>
<!-- 上面是NAV載入 請一定要放在BODY開始的位置 -->
<!--下面可自由新增內容 -->

<div class="wrapper">
    <section class="pager-section text-center" style="padding-bottom: 30px; padding-top: 140px;">
        <div class="container">
            <div class="pager-head">
                <h2 style="color: #ffa500; font-size: 2rem; font-weight: 400;">商品變更</h2>
            </div>
            <!--pager-head end-->
        </div>
    </section>
    <!--pager-section end-->

    <section class="">
        <div class="container">
            <div class="row justify-content-center">
                <div class="col-lg-7">
                    <div class="delitaste-form text-center">
                        <div>
                            <h4 class="text-left" style="font-size: 1rem;">商品名稱:</h4>

                            <div class="row">
                                <div class="col-md-12">
                                    <div class="form-group">
                                        <input type="text" name="prodName" id="prodName" placeholder="商品名稱*"
                                               class="form-control" readonly>
                                    </div>
                                </div>
                            </div>
                                <div class="col-md-6">
                                    <div class="form-group">
                                        <input type="text" name="prodStat" id="prodStat" placeholder="商品狀態*"
                                               class="form-control" readonly>
                                    </div>
                                    <!--form-group end-->
                                </div>
                            </div>
                            <div class="row">
                                <div class="col-md-6">
                                    <div class="form-group">
                                        <select style="height: 55px;" class="form-control" name="prodTypeName"
                                                id="prodTypeName" disabled>
                                            <option disabled selected>商品類型*</option>
                                        </select>
                                    </div>
                                    <!--form-group end-->
                                </div>
                                <div class="col-md-6">
                                    <div class="form-group">
                                        <select style="height: 55px;" class="form-control" name="prodStat"
                                                id="prodStat" disabled>
                                            <option disabled selected>商品狀態</option>
                                        </select>
                                    </div>
                                </div>
                                <div class="col-md-12">
                                    <div class="form-group">
                                        <input type="text" placeholder="商品介紹" class="form-control" name="prodCont"
                                               id="prodCont" readonly>
                                    </div>
                                    <!--form-group end-->
                                </div>
                            </div>
                            <div class="col-md-12">
                                <div>
                                    <h4 class="text-left" style="font-size: 1rem; margin-left: 8px;margin-right: 9rem">
                                        商品照片
                                    </h4>
                                </div>
                                <div class="form-group" style="position: relative">
                                        <span id="imgbtnspan" style="position: absolute;display:none; left: 10%">
                                        <label class="btn btn-info">
                                            <input type="file" style="display:none;" id="imgInput"></iuput>
                                            <i class="fa fa-photo"></i> 上傳照片
                                        </label>
                                        </span>
                                    <img src="" id="prodImage" width="200px" height="auto"
                                         style="background: #F5F5F5">
                                </div>
                            </div>

                            <div class="row">
                                <div class="col-md-12">
                                    <div class="form-group">
                                        <button type="button" class="btn-default w-100" style="outline: none;"
                                                id="update">
                                            修改
                                            <span></span></button>
                                    </div>
                                </div>
                            </div>
                            <div class="row" style="display:none;" id="checkRow">
                                <div class="col-md-6">
                                    <div class="form-group">
                                        <button type="button" class="btn-default w-100" style="outline: none;"
                                                id="confirm">
                                            確認<span></span></button>
                                    </div>
                                </div>
                                <div class="col-md-6">
                                    <div class="form-group">
                                        <button type="button" class="btn-default w-100" style="outline: none;"
                                                id="cancel">
                                            取消<span></span></button>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </section>
</div>


<!-- 下面是這個版需要的js可添加各自需要的js檔-->
<script src="../resources/js/bootstrap.min.js"></script>
<script src="../resources/js/slick.js"></script>
<script src="../resources/js/scripts.js"></script>
<script src="../resources/js/isotope.js"></script>
</body>
</html>