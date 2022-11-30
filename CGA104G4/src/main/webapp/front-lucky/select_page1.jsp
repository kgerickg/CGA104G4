<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<%@ page import="com.lucky.model.*"%>
<%@ page import="java.util.*"%>

<%
	//Integer storeId = (Integer) session.getAttribute("storeId");
	Integer storeId = 2;   //先寫死，測試用，正式用↑上面那行↑****************************************************************************************************這裡要改！
	LuckyService service = new LuckyService();
	List<LuckyVO> luckyVOList = service.getAll2(storeId);
%>

<html>
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
    <title>福袋管理</title>

<style>

</style>

</head>
<body bgcolor='white'>

    <script src="../resources/js/storenav.js"></script>
    <!-- 上面是NAV載入 請一定要放在BODY開始的位置 -->
    <!--下面可自由新增內容 -->

<h3>資料查詢:</h3>

<ul>

  <li><a href='<%=request.getContextPath()%>/lucky/lucky.do?action=getAllForStore'>顯示</a> 我的福袋  <br><br></li>

  <jsp:useBean id="luckySvc" scope="page" class="com.lucky.model.LuckyService" />
   
  <li>
     <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/lucky/lucky.do" >
       <b>選擇福袋編號:</b>
       <select size="1" name="luckyId">
         <c:forEach var="luckyVO" items="<%=luckyVOList%>" >
          <option value="${luckyVO.luckyId}">${luckyVO.luckyId}
         </c:forEach>   
       </select>
       <input type="hidden" name="action" value="getOne_For_Display">
       <input type="submit" value="送出">
    </FORM>
  </li>
  
  <li>
     <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/lucky/lucky.do" >
       <b>選擇福袋名稱:</b>
       <select size="1" name="luckyId">
         <c:forEach var="luckyVO" items="<%=luckyVOList%>" > 
          <option value="${luckyVO.luckyId}">${luckyVO.lkName}
         </c:forEach>   
       </select>
       <input type="hidden" name="action" value="getOne_For_Display">
       <input type="submit" value="送出">
     </FORM>
  </li>
</ul>

<h3>福袋管理</h3>

<ul>
  <li><a href='addLucky.jsp'>新增</a> 新的福袋</li>
</ul>

    <script src="../resources/js/bootstrap.min.js"></script>
    <script src="../resources/js/slick.js"></script>
    <script src="../resources/js/scripts.js"></script>
    <script src="../resources/js/isotope.js"></script>

</body>
</html>