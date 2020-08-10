package com.guidi.customermicroservice.model;

import java.util.List;

public class GenericResponse<T> {

	private int code;
	private String status;
    private String message;
    private List<T> payload;
    
    public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public List<T> getPayload() {
		return payload;
	}

	public void setPayload(List<T> payload) {
		this.payload = payload;
	}

    public GenericResponse(int code, String status, String message, List<T> payload) {
        this.code = code;
    	this.status = status;
        this.message = message;
        this.payload = payload;
    }
}