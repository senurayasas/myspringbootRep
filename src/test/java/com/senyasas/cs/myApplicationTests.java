package com.senyasas.cs;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.senyasas.cs.model.api.request.my.myRequest;
import com.senyasas.cs.repository.myRepository;
import com.gifted.test.db.AbstractDatabaseTest;
import org.junit.jupiter.api.AfterEach;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WithMockUser("user")
@AutoConfigureMockMvc
class myApplicationTests extends AbstractDatabaseTest {

    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private myRepository myRepository;

    private static final String BASE_PATH = "/example";
    private final ObjectMapper objectMapper = new ObjectMapper();

    @AfterEach
    void afterEach() {
        myRepository.deleteAll();
    }

    //@Test
    void method_name__conditions__expected_outcome() throws Exception {
        final var request = myRequest.builder()
                .input("input string")
                .build();

        //when(exampleClient.call("input string"))
        //        .thenReturn("client response");

        mockMvc.perform(post(BASE_PATH)
                        .content(objectMapper.writeValueAsString(request))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().json("{\"text\":\"client response\"}"));
    }
}
