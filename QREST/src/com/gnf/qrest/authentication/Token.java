package com.gnf.qrest.authentication;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Token {
  @JsonProperty("access_token")
  private String accessToken;
  @JsonProperty("expires_in")
  private int expiresIn;
  @JsonProperty("token_type")
  private String tokenType;
  private long expiration;

  public String getAccessToken() {
    return accessToken;
  }

  public void setAccessToken(String accessToken) {
    this.accessToken = accessToken;
  }

  public int getExpiresIin() {
    return expiresIn;
  }

  public void setExpiresIn(int expiresIn) {
    this.expiresIn = expiresIn;
  }

  public String getTokenType() {
    return tokenType;
  }

  public void setToken_type(String tokenType) {
    this.tokenType = tokenType;
  }

  public long getExpiration() {
    return expiration;
  }

  public void setExpiration(long expiration) {
    this.expiration = expiration;
  }
}
