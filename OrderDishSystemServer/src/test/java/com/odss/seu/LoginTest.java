package com.odss.seu;

import com.odss.seu.controller.LoginController;
import com.odss.seu.service.AuthenticService;
import com.odss.seu.service.AuthenticServiceImpl;
import com.odss.seu.service.LoginService;
import com.odss.seu.service.LoginServiceImpl;
import org.junit.Before;
import org.junit.Test;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

public class LoginTest {

    InternalResourceViewResolver resolver;
    MockMvc mockMvc;

    @Before
    public void setup() {

    }

    @Test
    public void testGetLogin() throws Exception {

    }
}
