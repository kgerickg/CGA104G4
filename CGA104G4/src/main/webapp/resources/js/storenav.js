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
                    <li><a href="${webCtx}/front-store/storeInfoUpdate.html" >基本資料維護</a></li>
                    <li><a href="" >我的評論</a></li>
                    <li><a href="" >帳務管理</a></li>
                    <li><a href="" >購買廣告</a></li>
                    <li><a href="" >一般商品管理+</a>
                        <ul>
                            <li><a href="" >商品變更</a></li>
                            <li><a href="${webCtx}/front-orders/storeListAllOrders.jsp" >訂單管理</a></li>
                        </ul>
                    </li>
                    <li><a href="" >福袋商品管理+</a>
                        <ul>
                            <li><a href="" >福袋變更</a></li>
                            <li><a href="${webCtx}/front-lkorder/StoreIndexLkorder.jsp" >福袋訂單管理</a></li>
                        </ul>
                     </li>
                 </ul>
             </nav>
            <div class="menu-btn">
                <span class="bar1"></span>
                <span class="bar2"></span>
                <span class="bar3"></span>
            </div>
            
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


let div = document.createElement("div");
div.setAttribute("class", "responsive-mobile-menu");
div.innerHTML =
    `<ul>
        <li><a href="${webCtx}/front-store/storeInfoUpdate.html" >基本資料維護</a></li>
        <li><a href="" >我的評論</a></li>
        <li><a href="" >帳務管理</a></li>
        <li><a href="" >購買廣告</a></li>
        <li><a href="" >一般商品管理+</a>
        <ul>
             <li><a href="" >商品變更</a></li>
             <li><a href="${webCtx}/front-orders/storeListAllOrders.jsp" >訂單管理</a></li>
        </ul>
        </li>
        <li><a href="" >福袋商品管理+</a>
              <ul>
                   <li><a href="" >福袋變更</a></li>
                   <li><a href="" >福袋訂單管理</a></li>
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

//頁面載入時判斷是否有登入與是否登入過期。
(async function logincheck() {
    let storeDataJson = sessionStorage.getItem("storedata");
    console.log(storeDataJson)
    if (!storeDataJson) {
        await loginCheckWithServer();
        return;
    }

    let storedata = JSON.parse(storeDataJson);
    let Now = new Date().getTime();
    if((Now-storedata.LoginTime)>=(60*30*1000)){
        await loginCheckWithServer();
        return;
    }

    loginNavChange();

})();


async function loginCheckWithServer(){
    let path = window.location.pathname;
    let webCtx = path.substring(0, path.indexOf('/', 1));
    let url = webCtx + "/store/storeFront.do?action=loginCheck";

    let response = await fetch(url, {method: 'get'}).then(e => e.json());
    if (response.state) {
        let storedata = {};
        storedata.storeId = response.storeId;
        storedata.storeName = response.storeName;
        storedata.storePic = response.storePic;
        storedata.LoginTime = new Date().getTime();
        sessionStorage.setItem("storedata", JSON.stringify(storedata));
        loginNavChange();
    }
}

//登入改變NAV顯示結果
function loginNavChange() {
    //改變一般navbar
    document.querySelector("#logul").innerHTML = "";
    document.querySelector("#logul").innerHTML = `<li><a href="" title="" class="logout">登出</a></li>`;
    // let storedata = JSON.parse(sessionStorage.getItem("storedata"));
    // if(storedata.storePic){
    //     document.querySelector("#personImage").setAttribute("src","data:image/png;base64,"+storedata.storePic);
    // }else {
    //     document.querySelector("#personImage").setAttribute("src","../resources/images/personal.jpg");
    // }

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
    let url = webCtx + "/store/storeFront.do?action=logout";

    await fetch(url, {method: 'get'})
        .then(response => {
            if (response.ok) {
                sessionStorage.removeItem("storedata");
                window.location.reload();
                window.location.href = webCtx+"/front-index/storeIndex.html";
            }
        })

}