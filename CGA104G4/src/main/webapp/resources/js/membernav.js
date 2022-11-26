//動態生成NAV-BAR
let body = document.querySelector("body");
let divwrapper = document.createElement("div");
divwrapper.setAttribute("class", "wrapper");
let header = document.createElement("header");
let path = window.location.pathname;
let webCtx = path.substring(0, path.indexOf('/', 1));
header.innerHTML =
    `<div class="container-fluid">
        <div class="header-content d-flex flex-wrap align-items-center">
            <div class="logo">
                <a href="${webCtx}/front-index/index.html" title="">
                    <img class="logoimg" src="${webCtx}/resources/images/1.png" alt="">
                </a>
            </div>
            <nav>
                <ul>
                    <li><a href="" >吉食專區</a></li>
                    <li><a href="" >最新消息</a></li>
                    <li><a href="${webCtx}/front-index/About.html" >關於我們</a></li>
                    <li><a href="" >Q&A</a></li>
                    <li><a href="" >消費資訊+</a>
                        <ul>
                            <li><a href="" >最愛店家</a></li>
                            <li><a href="" >預約福袋</a></li>
                            <li><a href="${webCtx}/front-orders/memberListAllOrders.jsp">訂單資訊</a></li>
                            <li><a href="${webCtx}/front-lkorder/FrontIndexLkorder.jsp">福袋訂單資訊</a></li>
                            <li><a href="${webCtx}/front-lkcompl/MemberIndexLkCompl.jsp">客訴訂單資訊</a></li>
                        </ul>
                    </li>
                    <li><a href="" >個人資訊+</a>
                        <ul>
                            <li><a href="${webCtx}/front-member/memberinfoupdate.html" class="">個人基本資料</a></li>
                            <li><a href="${webCtx}/front-member/memberupatepassword.html" >更改密碼</a></li>
                            <li><a href="${webCtx}/front-refill/refill.html">錢包專區</a></li>
                        </ul>
                    </li>
                </ul>
            </nav>
            <div class="menu-btn">
                <span class="bar1"></span>
                <span class="bar2"></span>
                <span class="bar3"></span>
            </div>
            <ul class="oth-lnks ml-auto">
                <li>
                    <a href="#" title="" class="">
                        <img alt="" src="" id="personImage" style="width:2.5rem;border-radius: 100%;margin-right: 0px">
                    </a>
                </li>
                <li>
                    <a href="#" title="" class="">
                        <img alt="" src="${webCtx}/resources/images/bell.svg" style="width:2rem">
                    </a>
                    <span class="cart-item-num">0</span>
                </li>
                <li>
                    <a href="#" title="" class="">
                        <img alt="" src="${webCtx}/resources/images/shopping-cart.svg" style="width:2rem">
                    </a>
                     <span class="cart-item-num">0</span>
                </li>
            </ul>
            <nav class="padding-remove">
                <ul id="logul">
                    <li><a href="" title="">登入|註冊</a>
                        <ul class="uladjust">
                            <li><a href="${webCtx}/front-member/membersignup.html" title="">會員註冊</a></li>
                            <li><a href="${webCtx}/front-member/memberlogin.html" title="">會員登入</a></li>
                            <li><a href="${webCtx}/front-store/storeSignup.html" title="">商家註冊</a></li>
                            <li><a href="${webCtx}/front-member/storelogin.html" title="">商家登入</a></li>
                        </ul>
                    </li>
                </ul>
            </nav>
        </div>
    </div >`

//動態生成response-navbar
let div = document.createElement("div");
div.setAttribute("class", "responsive-mobile-menu");
div.innerHTML =
    `<ul>
        <li><a href="">吉食專區</a></li>
        <li><a href="">最新消息</a></li>
        <li><a href="${webCtx}/front-index/About.html">關於我們</a></li>
        <li><a href="">Q&A</a></li>
        <li><a href="">消費資訊+</a>
            <ul>
                <li><a href="">最愛店家</a></li>
                <li><a href="">預約福袋</a></li>
                <li><a href="${webCtx}/front-orders/memberListAllOrders.jsp">訂單資訊</a></li>
                <li><a href="${webCtx}/front-lkorder/FrontIndexLkorder.jsp">福袋訂單資訊查詢</a></li>
                <li><a href="${webCtx}/front-lkcompl/MemberIndexLkCompl.jsp">客訴訂單資訊</a></li>
            </ul>
        </li>
        <li><a href="#" >個人資訊+</a>
            <ul>
                <li><a href="${webCtx}/front-member/memberinfoupdate.html" class="mem-info">個人基本資料</a></li>
                <li><a href="${webCtx}/front-member/memberupatepassword.html" >更改密碼</a></li>
                <li><a href="${webCtx}/front-refill/refill.html" >錢包專區</a></li>
            </ul>
        </li>
        <li id="log-il-response"><a href="">登入|註冊</a>
            <ul>
                <li><a href="${webCtx}/front-member/membersignup.html">會員註冊</a></li>
                <li><a href="${webCtx}/front-member/memberlogin.html">會員登入</a></li>
                <li><a href="${webCtx}/front-store/storeSignup.html" title="">商家註冊</a></li>
                <li><a href="${webCtx}/front-member/storelogin.html" title="">商家登入</a></li>
            </ul>
        </li>
    </ul>`

divwrapper.append(header);
divwrapper.append(div);
body.insertAdjacentElement("afterbegin", divwrapper);

divwrapper.insertAdjacentHTML("afterend",
    "<div style='position: absolute;top: 12%;z-index: 1000;color: green;font-size: 1.5rem; width: 100%;height: 2rem;overflow: hidden'>" +
        "<div style='position: relative;' >" +
            "<div id='promoDiv' style='position: absolute;right: 0%;'>" +
    "</div></div></div>");

//頁面載入時載入廣告判斷是否有登入與是否登入過期。
(async function logincheck() {
    await showPromo();
    let memDataJson = sessionStorage.getItem("memData");
    if (!memDataJson) {
        await loginCheckWithServer();
        return;
    }

    let memData = JSON.parse(memDataJson);
    let Now = new Date().getTime();
    if((Now-memData.LoginTime)>=(60*30*1000)){
        await loginCheckWithServer();
        return;
    }

    loginNavChange();

})();


async function loginCheckWithServer(){
    let path = window.location.pathname;
    let webCtx = path.substring(0, path.indexOf('/', 1));
    let url = webCtx + "/member/memberfront.do?action=loginCheck";

    let response = await fetch(url, {method: 'get'}).then(e => e.json());
    if (response.state) {
        let memData = {};
        memData.memId = response.memId;
        memData.memName = response.memName;
        memData.memPic = response.memPic;
        memData.LoginTime = new Date().getTime();
        sessionStorage.setItem("memData", JSON.stringify(memData));
        loginNavChange();
    }
}

//登入改變NAV顯示結果
function loginNavChange() {
    //改變一般navbar
    document.querySelector("#logul").innerHTML = "";
    document.querySelector("#logul").innerHTML = `<li><a href="" title="" class="logout">登出</a></li>`;
    let memData = JSON.parse(sessionStorage.getItem("memData"));
    if(memData.memPic){
        document.querySelector("#personImage").setAttribute("src","data:image/png;base64,"+memData.memPic);
    }else {
		let path = window.location.pathname;
   		let webCtx = path.substring(0, path.indexOf('/', 1));
        document.querySelector("#personImage").setAttribute("src",`${webCtx}/resources/images/personal.jpg`);
    }

    //改變response-navbar
    document.querySelector("#log-il-response").innerHTML = "";
    document.querySelector("#log-il-response").innerHTML = `<a href="" class="logout">登出</a>`;
    //綁定登出連結觸發事件(resposne與一般NAR綁定同事件)
    document.querySelectorAll("a.logout").forEach(e => e.addEventListener("click", function (e) {
        e.preventDefault();
        logout();
    }));
}

//登出時刪除SESSION與sessionStorage資料
async function logout() {
    let path = window.location.pathname;
    let webCtx = path.substring(0, path.indexOf('/', 1));
    let url = webCtx + "/member/memberfront.do?action=logout";

    await fetch(url, {method: 'get'})
        .then(response => {
            if (response.ok) {
                sessionStorage.removeItem("memData");
                window.location.reload();
                window.location.href = webCtx+"/front-index/index.html";
            }
        })

}

let promoDiv = document.querySelector("#promoDiv");

async function showPromo(){
    let path = window.location.pathname;
    let webCtx = path.substring(0, path.indexOf('/', 1));
    let url = webCtx + "/promo/promo.do?action=isInPromo";
    let response = await fetch(url, {method: 'get'}).then(res => res.json())
    if(response.promoCont){
        promoDiv.textContent = response.promoCont;
        window.setTimeout(animate,200);
    }
}


let pos = 0;
function animate(){
    if(pos>=100){
        pos = 0;
    }else {
        pos+=3;
    }
    promoDiv.style.right = pos +"%";
    window.setTimeout(animate,200);
}









