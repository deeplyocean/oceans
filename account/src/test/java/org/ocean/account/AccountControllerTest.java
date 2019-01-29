package org.ocean.account;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
public class AccountControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    @Rollback
    public void createAccountTest() throws Exception {
        AccountDto.Create account = AccountDto.Create.builder()
                .email(String.format("account%s@ocean.com", System.currentTimeMillis()))
                .password("test")
                .accountName("ethan")
                .build();
        ResultActions createResult = mockMvc.perform(post("/accounts")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(account)));
        createResult.andDo(print())
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.email").value(account.getEmail()))
                .andExpect(jsonPath("$.accountName").value(account.getAccountName()))
        ;
    }

    @Test
    public void getAccountListTest() throws Exception {
        ResultActions result = mockMvc.perform(get("/accounts"));
        result.andDo(print())
                .andExpect(status().isOk());
    }


}