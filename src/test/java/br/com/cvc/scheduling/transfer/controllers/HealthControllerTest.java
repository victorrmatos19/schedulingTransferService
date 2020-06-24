package br.com.cvc.scheduling.transfer.controllers;

import br.com.cvc.scheduling.transfer.util.ControllerTest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * Class to test the {@link HealthController} class.
 */
@DisplayName("Integration-Test: HealthController")
@ControllerTest(HealthController.class)
public class HealthControllerTest {

    @Autowired
    private MockMvc mvc;

    private String RESOURCE_PATH = "/health";

    @Test
    @DisplayName("check.1: should check health")
    public void checkTest() throws Exception {

        mvc.perform(get(RESOURCE_PATH))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.status", is("UP")))
                .andExpect(jsonPath("$.spring-boot", is("UP")));
    }
}
