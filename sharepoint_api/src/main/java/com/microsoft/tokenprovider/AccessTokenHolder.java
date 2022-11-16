package com.microsoft.tokenprovider;


public class AccessTokenHolder {
	
	static AccessTokenHolder instance = null;
	
	private AccessTokenHolder() {
		
	}
	
	public static AccessTokenHolder getInstance() {
		if(instance == null) {
			instance = new AccessTokenHolder();
		}
		return instance;
	}

    private boolean userActionPerformed = false;
    private String accessToken;
    private String refreshToken;
    private String tenantId;
    private String clientId;
    private String clientSecret;
    private String scope;
    private String state;
    public String getRefreshToken() {
		return refreshToken;
	}

	public void setRefreshToken(String refreshToken) {
		this.refreshToken = refreshToken;
	}

	public void actionPerformed(boolean userActionPerformed) {
        this.userActionPerformed = userActionPerformed;
    }

    public boolean isUserActionPerformed() {
        return this.userActionPerformed;
    }

    public void setToken(String token) {
        accessToken = token;
    }

    public String getToken() {
        return accessToken;
    }

	public String getTenantId() {
		return tenantId;
	}

	public void setTenantId(String tenantId) {
		this.tenantId = tenantId;
	}

	public String getClientId() {
		return clientId;
	}

	public void setClientId(String clientId) {
		this.clientId = clientId;
	}

	public String getClientSecret() {
		return clientSecret;
	}

	public void setClientSecret(String clientSecret) {
		this.clientSecret = clientSecret;
	}

	public String getScope() {
		return scope;
	}

	public void setScope(String scope) {
		this.scope = scope;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}
    
}
