package com.example.demo2.user;

import com.fasterxml.jackson.databind.json.JsonMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(UserController.class)
class UserControllerWebMvcTest {

    @Autowired
    private MockMvc mvc;

    @MockBean
    private UserService userService;

    @Test
    void getUserById() throws Exception {
        // Stub
        UserDomain userDomain = new UserDomain();
        userDomain.setId(1);
        userDomain.setName("Demo name");
        given(userService.getData(1))
                .willReturn(userDomain);

        MvcResult mvcResult = this.mvc.perform(get("/user/1")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn();

        String result = mvcResult.getResponse().getContentAsString();

        JsonMapper mapper = new JsonMapper();
        UserResponse userResponse = mapper.readValue(result, UserResponse.class);
        assertEquals(1, userResponse.getId());
        assertEquals("Demo name", userResponse.getName());
    }
}