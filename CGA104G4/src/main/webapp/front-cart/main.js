// var all = 0;			//用全局變量記錄總合計價格。
//
// function add_shoppingcart(btn) {			//增加到購物車
//     var tr = btn.parentNode.parentNode;
//     var info = tr.children;
//     var item = document.createElement("tr");
//     var name = info[0].innerHTML;
//     var price = parseInt(info[1].innerHTML);
//     item.innerHTML = 				//創建購物車項
//         '<td>'+name+'</td>'+
//         '<td>'+price+'</td>'+
//         '<td align="center">'+
//         '<input type="button" value="-" onclick="reduce(this);"/> '+	//要加空格的原因：因爲字符串用"+"連接，不會將換行符當作空格，所以和原生空格不對稱，所以手動加一個空格。
//         '<input type="text" size="3" readonly value="1"/>'+
//         '<input type="button" value="+" onclick="increase(this);"/> '+	//函數要傳入this，方便操作。
//         '</td>'+
//         '<td>'+price+'</td>'+
//         '<td align="center"><input type="button" value="x" onclick="remove_shoppingcart(this)"/></td>';
//     //做完這一步，就可以將cart.html中購物車的第一行刪除了
//     var cart = document.getElementById("goods");
//     cart.appendChild(item);
//     tr.remove();
//
//     all += price;		//改變全局總價
//     changeTotal();				//刷新總價
// }
//
// function remove_shoppingcart(btn) {
//     var tr = btn.parentNode.parentNode;
//     var children = tr.children;
//     var price = parseInt(children[3].innerHTML);
//     all -= price;
//     changeTotal();					//改變合計金額
//
//     tr.innerHTML = "";				//將祖節點置空
// }
//
// function reduce(btn) {			//減少商品數量
//     var amount = btn.nextElementSibling.value;
//     if(amount==0){
//         return;				//若商品等於0則退出
//     }
//     amount--;
//     btn.nextElementSibling.value = amount;		//更新商品數量
//
//     var value = parseInt(btn.parentNode.previousElementSibling.innerHTML);	//獲取商品單價
//     btn.parentNode.nextElementSibling.innerHTML = value*amount;		//更新商品總價
//     all -= value;		//更新全局總價。
//     changeTotal();		//刷新總價
//
// }
//
// function increase(btn) {		//增加商品數量
//     var amount = btn.previousElementSibling.value;
//     amount++;
//     btn.previousElementSibling.value = amount;		//更新商品數量
//
//     var value = parseInt(btn.parentNode.previousElementSibling.innerHTML);
//     btn.parentNode.nextElementSibling.innerHTML = value*amount;
//
//     all += value;		//更新全局總價
//     changeTotal();		//刷新總價
// }
//
// function changeTotal() {		//更新total函數，在每次改變購物車時使用。
//     var total = document.getElementById("total");
//     total.innerHTML = all;
// }