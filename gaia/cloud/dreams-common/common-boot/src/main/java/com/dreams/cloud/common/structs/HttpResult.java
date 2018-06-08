package com.dreams.cloud.common.structs;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel
public class HttpResult<T> {
	public static final int OK = 0;
	@ApiModelProperty(value="消息码",required=true,example="0")
	private Integer code = 0;
	@ApiModelProperty(value="提示信息。主要用于开发调试，不建议显示给用户",required=true)
	private String message;
	@ApiModelProperty(value="返回包体",required=true)
	private T data;
	@ApiModelProperty(value="是否正常返回",required=true,example="true",notes="主要预留给某些不方便判断code的场景，只要code不是0，这个字段就是false")
	private boolean success = true;
	
	public HttpResult() {}
	public HttpResult(T data) {
		this.data = data;
	}
	
	public Integer getCode() {
		return code;
	}
	
	public void setCode(Integer code) {
		this.code = code;
		if (code != OK) {
			this.success = false;
		}
	}
	
	public String getMessage() {
		return message;
	}
	
	public void setMessage(String message) {
		this.message = message;
	}
	
	public T getData() {
		return data;
	}
	
	public void setData(T data) {
		this.data = data;
	}
	
	public boolean isSuccess() {
		return this.success;
	}

    @Override
    public String toString() {
        return "HttpResult [code=" + code + ", message=" + message + ", data=" + data + ", success=" + success + "]";
    }
	
}
