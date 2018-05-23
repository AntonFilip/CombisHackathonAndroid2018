package com.immutables.trycodecatch.trycodecatchtest.Models.BackendModels;

public class Token
{
    public String accessToken;
    public String refreshToken;

    public Token (String accessToken, String refreshToken)
    {
        this.accessToken = accessToken;
        this.refreshToken = refreshToken;
    }
}
