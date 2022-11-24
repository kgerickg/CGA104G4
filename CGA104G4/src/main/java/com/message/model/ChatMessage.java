package com.message.model;

public class ChatMessage {
	private String msgId;
	private String type;
	private String memId;
	private String storeId;
	private String msgDir;
	private String message;
	private String msgTime;

	public ChatMessage(String msgId, String type, String memId, String storeId, String msgDir, String message, String msgTime) {
		this.msgId = msgId;
		this.type = type;
		this.memId = memId;
		this.storeId = storeId;
		this.msgDir = msgDir;
		this.message = message;
		this.msgTime = msgTime;
	}

	public String getMemId() {
		return memId;
	}

	public void setMemId(String memId) {
		this.memId = memId;
	}

	public String getStoreId() {
		return storeId;
	}

	public void setStoreId(String storeId) {
		this.storeId = storeId;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getMsgId() {
		return msgId;
	}

	public void setMsgId(String msgId) {
		this.msgId = msgId;
	}

	public String getMsgDir() {
		return msgDir;
	}

	public void setMsgDir(String msgDir) {
		this.msgDir = msgDir;
	}

	public String getMsgTime() {
		return msgTime;
	}

	public void setMsgTime(String msgTime) {
		this.msgTime = msgTime;
	}
	
}