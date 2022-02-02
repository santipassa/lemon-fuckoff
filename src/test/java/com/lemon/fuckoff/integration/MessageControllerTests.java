package com.lemon.fuckoff.integration;


import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockserver.client.MockServerClient;
import org.mockserver.model.MediaType;
import org.mockserver.springtest.MockServerTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.mockserver.model.HttpRequest.request;
import static org.mockserver.model.HttpResponse.response;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@ActiveProfiles("test")
@MockServerTest("rest.foaas-base-url=http://localhost:${mockServerPort}")
@ExtendWith(SpringExtension.class)
@AutoConfigureMockMvc
@SpringBootTest
class MessageControllerTests {

    @Value("${rest.foaas-base-url}")
    private String serverUrl;

    private MockServerClient mockServerClient;

    @Autowired
    private MockMvc mockMvc;

    /**
     * Create apicall mock
     *
     * @param uri
     * @param responseBody
     * @param statusCode
     */
    private void createGetMock(String uri, String responseBody, int statusCode) {
        mockServerClient.when(request().withMethod("GET").withPath(uri))
                .respond(response().withStatusCode(statusCode).withContentType(MediaType.APPLICATION_JSON).withBody(responseBody));
    }

    @Test
    public void testSuccessResponseWhenCallToFoaas() throws Exception {
        String responseBodyFoaas = "{\"message\": \"Fuck YEAH!\"}";
        createGetMock("/yeah/lemon", responseBodyFoaas, 200);
        mockMvc.perform((MockMvcRequestBuilders.get("/message")).header("X-User-Id", 1234)).andExpect(status().isOk()).andExpect(jsonPath("$.message", Matchers.is("Fuck YEAH!")));
    }

    @Test
    public void getMessageWithoutClientIdHeaderMustThrowUnauthorized() throws Exception {
        mockMvc.perform((MockMvcRequestBuilders.get("/message")
                        .contentType(org.springframework.http.MediaType.APPLICATION_JSON)))
                .andExpect(status().isUnauthorized());
    }

}
