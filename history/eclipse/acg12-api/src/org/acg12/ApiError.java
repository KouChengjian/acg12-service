package org.acg12;

public class ApiError {

	public static final int DATA_REQUEST_SUCCESS = 20000;
	public static final String DATA_REQUEST_SUCCESS_MSG = "成功";
	public static final int DATA_REQUEST_FAILURE = 20100;
	public static final String DATA_REQUEST_FAILURE_MSG = "失败";
	

	public static final int HTTP_JSOUP_PARSE_EXCEPTION = 20101;
	public static final String HTTP_JSOUP_PARSE_EXCEPTION_MSG = "jsoup解析失败";
	public static final int HTTP_JSOUP_PARSE_JSON_EXCEPTION = 20102;
	public static final String HTTP_JSOUP_PARSE_JSON_EXCEPTION_MSG = "json解析失败";
	public static final int HTTP_JSOUP_GET_DATA_FAILUER = 20103;
	public static final String HTTP_JSOUP_GET_DATA_FAILUER_MSG = "jsoup获取数据失败为空";
	public static final String HTTP_GET_DATA_NULL_MSG = "http获取数据失败为空";
}
