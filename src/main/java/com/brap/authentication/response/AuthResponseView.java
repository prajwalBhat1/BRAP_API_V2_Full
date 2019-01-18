package com.brap.authentication.response;

import org.apache.commons.lang3.builder.ToStringBuilder;

public class AuthResponseView {

    private String accessToken;
    private String grant;
    private String id;

    public AuthResponseView() {
        //
    }

    public AuthResponseView(String accessToken, String role) {
        this.accessToken = accessToken;
        this.grant = role;
    }

    public AuthResponseView(String accessToken, String grant, String id) {
        this.accessToken = accessToken;
        this.id = id;
        this.grant = grant;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getAccessToken() {
        return accessToken;
    }


    public String getGrant() {
        return grant;
    }

    @Override
    public String toString() {
		return new ToStringBuilder(this).append("accessToken", accessToken).append("grant", grant).toString();
    }

}
