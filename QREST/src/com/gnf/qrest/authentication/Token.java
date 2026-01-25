package com.gnf.qrest.authentication;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Models a Token for authentication with IBM Quantum.
 */
public class Token {
  @JsonProperty("access_token")
  private String accessToken;
  @JsonProperty("expires_in")
  private int expiresIn;
  @JsonProperty("token_type")
  private String tokenType;
  private long expiration;

  /**
   * Gets the accessToken.
   *
   * @return The accessToken.
   */
  public String getAccessToken() {
    return accessToken;
  }

  /**
   * Sets the accessToken.
   *
   * @param accessToken The accessToken to set.
   */
  public void setAccessToken(String accessToken) {
    this.accessToken = accessToken;
  }

  /**
   * Gets the expiresIn.
   *
   * @return The expiresIn.
   */
  public int getExpiresIn() {
    return expiresIn;
  }

  /**
   * Sets the expiresIn.
   *
   * @param expiresIn The expiresIn to set.
   */
  public void setExpiresIn(int expiresIn) {
    this.expiresIn = expiresIn;
  }

  /**
   * Gets the tokenType.
   *
   * @return The tokenType.
   */
  public String getTokenType() {
    return tokenType;
  }

  /**
   * Sets the tokenType.
   *
   * @param tokenType The tokenType to set.
   */
  public void setTokenType(String tokenType) {
    this.tokenType = tokenType;
  }

  /**
   * Gets the expiration.
   *
   * @return The expiration.
   */
  public long getExpiration() {
    return expiration;
  }

  /**
   * Sets the expiration.
   *
   * @param expiration The expiration to set.
   */
  public void setExpiration(long expiration) {
    this.expiration = expiration;
  }

}
