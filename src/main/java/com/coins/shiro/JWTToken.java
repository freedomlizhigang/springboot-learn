package com.coins.shiro;

import org.apache.shiro.authc.AuthenticationToken;

public class JWTToken implements AuthenticationToken {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	// 密钥
    private String token;

    public JWTToken(String token) {
        this.token = token;
    }

    
    public Object getPrincipal() {
        return token;
    }

    
    public Object getCredentials() {
        return token;
    }
}
