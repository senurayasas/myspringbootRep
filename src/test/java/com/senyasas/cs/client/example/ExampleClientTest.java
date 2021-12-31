package com.senyasas.cs.client.example;

import com.github.tomakehurst.wiremock.WireMockServer;
import com.github.tomakehurst.wiremock.client.WireMock;
import com.github.tomakehurst.wiremock.matching.EqualToPattern;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.web.reactive.function.client.WebClient;

import static com.github.tomakehurst.wiremock.client.WireMock.*;
import static org.assertj.core.api.Assertions.assertThat;

@ActiveProfiles("test")
class ExampleClientTest {

    private static WireMockServer wireMockServer = new WireMockServer(0);


    @BeforeEach
    public void setup() {
        var webClient = WebClient.builder()
                .baseUrl(wireMockServer.baseUrl())
                .build();


    }

    @BeforeAll
    static void beforeAll() {
        wireMockServer.start();
        WireMock.configureFor("localhost", wireMockServer.port());
    }


    //@Test
    void method_name__conditions__expected_outcome() {
        //Given
        final var input = "Hello!";
        final var expectedResult = "Hello world!";

        stubFor(get(urlPathMatching("/hello"))
                .withQueryParam("someParam", new EqualToPattern("Hello!"))
                .willReturn(aResponse()
                        .withHeader("Content-Type", "application/json")
                        .withBody(expectedResult)));

        //When


        //Then
        verify(getRequestedFor(urlPathMatching("/hello"))
                .withHeader("Accept", equalTo("application/json"))
                .withQueryParam("someParam", new EqualToPattern("Hello!")));
        //assertThat(result).isEqualTo(expectedResult);
    }
}
