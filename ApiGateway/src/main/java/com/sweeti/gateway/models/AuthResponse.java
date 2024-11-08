package com.sweeti.gateway.models;

import java.util.Collection;

public class AuthResponse {
	private String userId;
	private String accessToken;
	private String refreshToken;
	private Long expireAt;
	private Collection<String> authorites;

	public AuthResponse(String userId, String accessToken, String refreshToken, Long expireAt,
			Collection<String> authorites) {
		this.userId = userId;
		this.accessToken = accessToken;
		this.refreshToken = refreshToken;
		this.expireAt = expireAt;
		this.authorites = authorites;
	}

	public AuthResponse() {
		// TODO Auto-generated constructor stub
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getAccessToken() {
		return accessToken;
	}

	public void setAccessToken(String accessToken) {
		this.accessToken = accessToken;
	}

	public String getRefreshToken() {
		return refreshToken;
	}

	public void setRefreshToken(String refreshToken) {
		this.refreshToken = refreshToken;
	}

	public long getExpireAt() {
		return expireAt;
	}

	public void setExpireAt(Long expireAt) {
		this.expireAt = expireAt;
	}

	public Collection<String> getAuthorites() {
		return authorites;
	}

	public void setAuthorites(Collection<String> authorites) {
		this.authorites = authorites;
	}

}
