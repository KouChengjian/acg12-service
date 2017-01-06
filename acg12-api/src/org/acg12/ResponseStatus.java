package org.acg12;

import org.json.JSONObject;

public class ResponseStatus {

	private String msg;
	private int code;
	private JSONObject data = new JSONObject();

	public JSONObject getData() {
		return data;
	}

	public void setData(JSONObject data) {
		this.data = data;
	}

	public ResponseStatus(int code, String msg) {
		this.msg = msg;
		this.code = code;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public int getStatusCode() {
		return code;
	}

	public void setStatusCode(int code) {
		this.code = code;
	}
	
}
