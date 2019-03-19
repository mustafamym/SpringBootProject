package com.spring.demo.dto;

/**
 * Created by mustafa
 */
public class AuthTokenDto {
	private String access_token;
	private Long expiration;

	public AuthTokenDto() {
		this.access_token = null;
		this.expiration = null;
	}

	public AuthTokenDto(String access_token, long expiration) {
		this.access_token = access_token;
		this.expiration = expiration;
	}

	public String getAccess_token() {
		return access_token;
	}

	public void setAccess_token(String access_token) {
		this.access_token = access_token;
	}

	public Long getExpiration() {
		return expiration;
	}

	public void setExpiration(Long expiration) {
		this.expiration = expiration;
	}

}