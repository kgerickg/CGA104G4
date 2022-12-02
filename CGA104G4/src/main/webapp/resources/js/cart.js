(async function getCart() {
    let path = window.location.pathname;
    let webCtx = path.substring(0, path.indexOf('/', 1));
    let url = webCtx + "/cart/front";

    let response = await fetch(url, {method: 'get'});
    let carts = await response.json();
    listCarts(carts);
})();

//動態生成表格
function listCarts(carts) {
    //移除原本存在的表格
    $("#listTable").empty();

    //生成表格
    let table = document.createElement("table");

    //生成表頭
    table.innerHTML += "<thead><tr><th>商店名稱</th><th>商品名稱</th>"
        + "<th>商品價格</th><th>加入結帳清單</th>"
        + "</tr></thead>";

    //生成表格內容
    let tbody = document.createElement("tbody");

    //循環依加入商品數量生成表格數量
    for (let storeId in carts.storeMap) {
        for (let prodId in carts.storeMap[storeId]) {
            console.log(carts.storeMap[storeId][prodId]);
            let tr = document.createElement("tr");
            tr.insertAdjacentHTML("beforeend", `<td>${carts.storeMap[storeId][prodId].storeName}</td>`);
            tr.insertAdjacentHTML("beforeend", `<td>${carts.storeMap[storeId][prodId].prodName}</td>`);
            tr.insertAdjacentHTML("beforeend", `<td>${carts.storeMap[storeId][prodId].prodPrc}</td>`);
            tr.insertAdjacentHTML("beforeend", `<td><input type="button" value="加入結帳清單" onclick="addCheckList(this);"/></td>`);
            tbody.append(tr);
        }
        table.append(tbody);
        table.setAttribute("class", "tableclass");
        table.setAttribute("id", "tableRows");
        $("#listTable").append(table);
    }


}

let all = 0;			//用全局變數記錄總合計價格。

function addCheckList(btn) {			//增加到結帳清單
    let tr = btn.parentNode.parentNode;
    let info = tr.children;
    let item = document.createElement("tr");
    let storeName = info[0].innerHTML;
    let prodName = info[1].innerHTML;
    let price = parseInt(info[2].innerHTML);
    item.innerHTML = 				//創建結帳清單
        '<td>' + storeName + '</td>' +
        '<td>' + prodName + '</td>' +
        '<td>' + price + '</td>' +
        '<td align="center">' +
        '<input type="button" value="-" onclick="reduce(this);"/> ' +
        '<input type="text" size="3" readonly value="1"/>' +
        '<input type="button" value="+" onclick="increase(this);"/> ' +	//函數要傳入this，方便操作。
        '</td>' +
        '<td>' + price + '</td>' +
        '<td align="center"><input type="button" value="x" onclick="removeCheckList(this)"/></td>';
    let cart = document.getElementById("goods");
    cart.appendChild(item);
    tr.remove();

    all += price;		//改變總價格
    changeTotal();				//刷新總價格
}

function removeCheckList(btn) {
    let tr = btn.parentNode.parentNode;
    let children = tr.children;
    let price = parseInt(children[4].innerHTML);
    all -= price;
    changeTotal();					//改變合計金額

    tr.innerHTML = "";
}

function reduce(btn) {			//減少商品數量
    let amount = btn.nextElementSibling.value;
    if (amount == 0) {
        return;				//若商品等於0則退出
    }
    amount--;
    btn.nextElementSibling.value = amount;		//更新商品數量

    let value = parseInt(btn.parentNode.previousElementSibling.innerHTML);	//獲取商品單價
    btn.parentNode.nextElementSibling.innerHTML = value * amount;		//更新商品總價
    all -= value;		//更新總價。
    changeTotal();		//刷新總價

}

function increase(btn) {		//增加商品數量
    let amount = btn.previousElementSibling.value;
    amount++;
    btn.previousElementSibling.value = amount;		//更新商品數量

    let value = parseInt(btn.parentNode.previousElementSibling.innerHTML);
    btn.parentNode.nextElementSibling.innerHTML = value * amount;

    all += value;		//更新總價
    changeTotal();		//刷新總價
}

function changeTotal() {		//更新total函數，在每次改變結帳清單時使用。
    let total = document.getElementById("total");
    total.innerHTML = all;
}

let checkout = document.getElementById("checkout");
checkout.addEventListener("click", cleanRedis)

async function cleanRedis() {
    let path = window.location.pathname;
    let webCtx = path.substring(0, path.indexOf('/', 1));
    let url = webCtx + "/cart/add?price=" + all

    fetch(url, {method: 'get'})
        .then(location.href = "/front-orders/memberListAllOrders.jsp") ;

}