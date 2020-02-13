package com.example.onlinenurserystore;

import com.example.onlinenurserystore.bll.LoginBll;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class LoginTest {

    public void testLogin() {
        LoginBll loginBll = new LoginBll();
        boolean result = loginBll.checkUser("d", "d");
        assertEquals(true, result);
    }

    //Fail Login
    @Test
    public void testLoginFail() {
        LoginBll loginBll = new LoginBll();
        boolean result = loginBll.checkUser("a", "b");
        assertEquals(true, result);
    }
}
