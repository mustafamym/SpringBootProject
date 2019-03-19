package com.demo.spring.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonInclude(Include.NON_EMPTY)
public class RestResponseDto {

	@JsonProperty("Success")
	private Boolean success;

	private Long id;
	private String message;
	private Integer status;

	public RestResponseDto() {
	}

	public RestResponseDto(Boolean success) {
		this.success = success;
	}
	
	public RestResponseDto(Boolean success, Long id) {
		this.success = success;
		this.id = id;
	}

	public RestResponseDto(Boolean success, String message) {
		this.success = success;
		this.message = message;
	}

	public Boolean getSuccess() {
		return success;
	}

	public void setSuccess(Boolean success) {
		this.success = success;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

}
