package com.immutables.trycodecatch.trycodecatchtest;

import com.immutables.trycodecatch.trycodecatchtest.Models.BackendModels.Token;
import com.immutables.trycodecatch.trycodecatchtest.Models.BackendModels.User;

public class ApplicationContext
{
    public static BackendService backendService = null;
    public static Token token = null;
    public static User loggedInUser = null;
}
