package com.example.onlinenurserystore.serverresponse;

public class SignUpResponse {
    private String id;
    private String status;
    private String token;

    public SignUpResponse(String status, String token,String id) {
        this.status = status;
        this.token = token;
        this.id=id;
    }
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }


    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
