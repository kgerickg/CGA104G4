package com.message.jedis;
import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;

import com.message.model.ChatMessage;

import redis.clients.jedis.Jedis;

public class TestJedisString {

	public static void main(String[] args) {
		Jedis jedis = new Jedis("localhost", 6379);		
		
		// JSON格式
		ChatMessage chatMessage1 = new ChatMessage("1", "history", "1", "2", "0", "您好，請問鮮蔬低卡餐盒是否為全素呢？", "2022-10-16 11:23:45");
		ChatMessage chatMessage2 = new ChatMessage("2", "history", "1", "2", "1", "您好，鮮蔬低卡餐盒是全素的唷！", "2022-10-16 11:24:25");
		ChatMessage chatMessage3 = new ChatMessage("3", "history", "1", "2", "0", "好的，剛剛已經下單囉，麻煩請幫我確認一下，謝謝！", "2022-10-16 11:24:47");
		ChatMessage chatMessage4 = new ChatMessage("4", "history", "1", "2", "1", "已經確認有收到訂單了，大約10分鐘後就可以取餐了！", "2022-10-16 11:25:29");
		ChatMessage chatMessage5 = new ChatMessage("5", "history", "1", "2", "0", "好的，謝謝！", "2022-10-16 11:25:36");
		ChatMessage chatMessage6 = new ChatMessage("6", "history", "3", "5", "0", "不好意思，請問牛肉麵會加蔥嗎？", "2022-10-16 11:23:45");
		ChatMessage chatMessage7 = new ChatMessage("7", "history", "3", "5", "1", "是的，牛肉麵都會加蔥喔！", "2022-10-16 11:24:25");
		ChatMessage chatMessage8 = new ChatMessage("8", "history", "3", "5", "0", "那我可以不要加蔥，改加香菜嗎？", "2022-10-16 11:24:47");
		ChatMessage chatMessage9 = new ChatMessage("9", "history", "3", "5", "1", "可以的，麻煩請將您的需求填寫在訂單備註欄喔！", "2022-10-16 11:25:29");
		ChatMessage chatMessage10 = new ChatMessage("10", "history", "3", "5", "0", "太好了！我超愛香菜的！太感謝了！", "2022-10-16 11:25:36");
		ChatMessage chatMessage11 = new ChatMessage("11", "history", "3", "5", "1", "不客氣，好吃的話要記得幫我們介紹客人來吃唷！", "2022-10-16 11:25:29");
 
		List<ChatMessage> chatMessageList = new ArrayList<>();
		
		chatMessageList.add(chatMessage1);
		chatMessageList.add(chatMessage2);
		chatMessageList.add(chatMessage3);
		chatMessageList.add(chatMessage4);
		chatMessageList.add(chatMessage5);
		chatMessageList.add(chatMessage6);
		chatMessageList.add(chatMessage7);
		chatMessageList.add(chatMessage8);
		chatMessageList.add(chatMessage9);
		chatMessageList.add(chatMessage10);
		chatMessageList.add(chatMessage11);
		
		String jObjStr1 = new JSONObject(chatMessage1).toString();
		String jObjStr2 = new JSONObject(chatMessage2).toString();
		String jObjStr3 = new JSONObject(chatMessage3).toString();
		String jObjStr4 = new JSONObject(chatMessage4).toString();
		String jObjStr5 = new JSONObject(chatMessage5).toString();
		String jObjStr6 = new JSONObject(chatMessage6).toString();
		String jObjStr7 = new JSONObject(chatMessage7).toString();
		String jObjStr8 = new JSONObject(chatMessage8).toString();
		String jObjStr9 = new JSONObject(chatMessage9).toString();
		String jObjStr10 = new JSONObject(chatMessage10).toString();
		String jObjStr11 = new JSONObject(chatMessage11).toString();
		
		String jArrayStr = new JSONArray(chatMessageList).toString();
		
		StringBuilder sb1 = new StringBuilder("chatMessage:").append(chatMessage1.getMsgId());
		StringBuilder sb2 = new StringBuilder("chatMessage:").append(chatMessage2.getMsgId());
		StringBuilder sb3 = new StringBuilder("chatMessage:").append(chatMessage3.getMsgId());
		StringBuilder sb4 = new StringBuilder("chatMessage:").append(chatMessage4.getMsgId());
		StringBuilder sb5 = new StringBuilder("chatMessage:").append(chatMessage5.getMsgId());
		StringBuilder sb6 = new StringBuilder("chatMessage:").append(chatMessage6.getMsgId());
		StringBuilder sb7 = new StringBuilder("chatMessage:").append(chatMessage7.getMsgId());
		StringBuilder sb8 = new StringBuilder("chatMessage:").append(chatMessage8.getMsgId());
		StringBuilder sb9 = new StringBuilder("chatMessage:").append(chatMessage9.getMsgId());
		StringBuilder sb10= new StringBuilder("chatMessage:").append(chatMessage10.getMsgId());
		StringBuilder sb11 = new StringBuilder("chatMessage:").append(chatMessage11.getMsgId());
		
		jedis.set(sb1.toString(), jObjStr1);
		jedis.set(sb2.toString(), jObjStr2);
		jedis.set(sb3.toString(), jObjStr3);
		jedis.set(sb4.toString(), jObjStr4);
		jedis.set(sb5.toString(), jObjStr5);
		jedis.set(sb6.toString(), jObjStr6);
		jedis.set(sb7.toString(), jObjStr7);
		jedis.set(sb8.toString(), jObjStr8);
		jedis.set(sb9.toString(), jObjStr9);
		jedis.set(sb10.toString(), jObjStr10);
		jedis.set(sb11.toString(), jObjStr11);
		
		jedis.set("chatMessages", jArrayStr);
		
		jedis.close();
	}

}
