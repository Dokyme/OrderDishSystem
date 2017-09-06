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
        resolver = new InternalResourceViewResolver(); //在test中重新配置视图解析器
        resolver.setPrefix("/WEB_INF/views");
        resolver.setSuffix(".jsp");
    }

    @Test
    public void testGetLogin() throws Exception {
        AuthenticService authenticService = new AuthenticServiceImpl();
        LoginService loginService = new LoginServiceImpl();
        LoginController loginController = new LoginController(loginService, authenticService);
        mockMvc = MockMvcBuilders.standaloneSetup(loginController).setViewResolvers(resolver).build();
        mockMvc.perform(MockMvcRequestBuilders.get("/login"))
                .andExpect(view().name("login"));
    }
}
