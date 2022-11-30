(async function getCart() {
    alert("123")
    let path = window.location.pathname;
    let webCtx = path.substring(0, path.indexOf('/', 1));
    let url = webCtx + "/cart/front";
    let response = await fetch(url, {method: 'get'});
    let carts = await response.json();
    let storeMap = JSON.stringify(carts.storeMap);
    listCarts(carts);
})();

//動態生成表格會員表格
function listCarts(carts) {
    alert("123");
    //移除原本存在的表格
    $("#listTable").empty();

    //生成表格
    let table = document.createElement("table");

    //生成表頭
    table.innerHTML += "<thead><tr><th>商店名稱</th><th>商品名稱</th>"
        + "<th>單品價格</th><th>商品數量</th><th>商品價格</th>"
        + "</tr></thead>";

    //生成表格內容
    let tbody = document.createElement("tbody");

    //循環依人數生成表格數量
    for (let i = 0; i < carts.length; i++) {


        let tr = document.createElement("tr");
        tr.insertAdjacentHTML("beforeend", `<td>${carts[i].storeName}</td>`);
        tr.insertAdjacentHTML("beforeend", `<td>${carts[i].prodName}</td>`);
        tr.insertAdjacentHTML("beforeend", `<td>${carts[i].prodPrc}</td>`);
        tr.insertAdjacentHTML("beforeend", `<td>${carts[i].prodQty}</td>`);
        tr.insertAdjacentHTML("beforeend", `<td>${carts[i].prodTotalPrc}</td>`);
        tr.insertAdjacentHTML("beforeend", `<td>${carts[i].prodTotalPrc}</td>`);

        tbody.append(tr);

    }
    table.append(tbody);
    table.setAttribute("class", "tableclass");
    $("#listTable").append(table);

}