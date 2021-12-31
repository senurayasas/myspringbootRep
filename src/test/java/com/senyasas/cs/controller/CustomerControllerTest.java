package com.senyasas.cs.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.senyasas.cs.model.api.request.my.CustomerRequest;
import com.senyasas.cs.model.entity.CustomerModel;
import com.senyasas.cs.model.mapping.CustomerMapper;
import com.senyasas.cs.repository.CustomerRepository;
import com.gifted.test.FixturesHelper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;

import java.util.Arrays;
import java.util.Optional;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.lessThanOrEqualTo;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ActiveProfiles("test")
@WithMockUser
@WebMvcTest(controllers = CustomerController.class)
class CustomerControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private CustomerRepository customerRepository;

    @MockBean
    private CustomerMapper mapper;

    private final ObjectMapper objectMapper = new ObjectMapper().findAndRegisterModules();
    private static final String BASE_PATH = "/customer";

    @Test
    void create__valid_request__is_persisted() throws Exception {
        final var request = CustomerRequest.builder()
                .name("senura")
                .age("32")
                .email("abc@gmail.com")
                .build();

        final var customerModel = new CustomerModel();

        when(mapper.fromRequest(request))
                .thenReturn(customerModel);

        //When
        mockMvc.perform(post(BASE_PATH)
                        .content(objectMapper.writeValueAsString(request))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
//                .andExpect(content().string(FixturesHelper.fixture("customer/customer_success.json")));

        verify(mapper).fromRequest(request);
        verify(customerRepository).save(customerModel);

    }

    @Test
    void get__valid_id__valid_response() throws Exception {
        // given
        final var id = 1L;
        final var customer = new CustomerModel().withId(1L);


        when(customerRepository.findById(id)).thenReturn(java.util.Optional.ofNullable(customer));

        // when
        mockMvc.perform(get(BASE_PATH + "/" + id)
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().string(FixturesHelper.fixture("customer/customer_success.json")));

    }

    @Test
    void findAll__valid_returnsAll() throws Exception {
        // given
        final var customers = Arrays.asList(
                new CustomerModel().withId(1l),
                new CustomerModel().withId(2L).withName("senura")
        );

        when(customerRepository.findAll()).thenReturn(customers);

        // when
        mockMvc.perform((get(BASE_PATH))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$", hasSize(lessThanOrEqualTo(5))))
                //.andExpect(jsonPath("$..[1]..name", hasValue("senura")))
                .andDo(MockMvcResultHandlers.print());

    }

    @Test
    void update__valid_customer__update_success() throws Exception {

        // given
        final Long id = 1L;
        final var customerModel = new CustomerModel().withId(1L);
        final var customer = CustomerRequest.builder().build();

        when(customerRepository.findById(id)).thenReturn(Optional.empty());
        when((customerRepository.save(any()))).thenReturn(customerModel);

        mockMvc.perform(put(BASE_PATH + "/" + id)
                        .content(objectMapper.writeValueAsString(customer))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andDo(MockMvcResultHandlers.print());

    }
}
