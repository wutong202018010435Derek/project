package com.edu.squash.ui.login;


import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
public class RegisterUserActivityTest {

    private RegisterUserActivity registerUserActivity;

    @Before
    public void setUp() {
        registerUserActivity  = new RegisterUserActivity();
    }

    @Test
    public void testOnInitView() {
        assertNotNull(registerUserActivity.etName);
        assertNotNull(registerUserActivity.etCode);

        // 测试初始化后确认密码文本框是否为空
        assertNotNull(registerUserActivity.etConfirmPsw);
    }

}
