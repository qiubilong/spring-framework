package com.experiment.web.vo;

/**
 * @author chenxuegui
 * @since 2024/10/29
 */
public class ServiceResult<T> {
	private int code;
	private String message;
	private T data;


	public static <T> ServiceResult<T> success(T data){
		ServiceResult<T> result = new ServiceResult<T>();
		result.setCode(0);
		result.setMessage("success");
		result.setData(data);
		return  result;
	}

	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
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
}
