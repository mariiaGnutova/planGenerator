package com.plangenerator.controller;

import com.plangenerator.AbstractTest;

import org.json.JSONObject;
import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.Matchers.*;
import static org.hamcrest.Matchers.hasKey;
import static org.hamcrest.Matchers.hasSize;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;

import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {MainController.class })
@WebAppConfiguration
public class MainControllerTest  extends AbstractTest{


    private MockMvc mockMvc;

    @Autowired
    private WebApplicationContext wac;

    @Before
    public void setUp() {
        mockMvc = MockMvcBuilders.webAppContextSetup(this.wac).build();
        super.setUp();
    }


    @Test
    public void testCreateProduct() throws Exception {
            JSONObject payload = new JSONObject();
            payload.put("loanAmount", (double) 5000);
            payload.put("nominalRate", (double) 5);
            payload.put("duration", "24");
            payload.put("startDate", "2018-01-01T00:00:01Z");

            MockHttpServletRequestBuilder createMessage = post("/v1/repayment/response")
                    .param("loanAmount", "5000")
                    .param("nominalRate", "5")
                    .param("duration", "24")
                    .param("startDate", "2018-01-01T00:00:01Z");

            mockMvc.perform(createMessage)
                    .andExpect(status().isCreated())
                    .andExpect(jsonPath("$", hasSize(24)))
                    .andExpect(jsonPath("$[0]").value(hasKey("date")))
                    .andExpect(jsonPath("$[0]").value(hasKey("annuity")))
                    .andExpect(jsonPath("$[0]").value(hasKey("principal")))
                    .andExpect(jsonPath("$[0]").value(hasKey("interest")))
                    .andExpect(jsonPath("$[0]").value(hasKey("initialOutstandingPrincipal")))
                    .andExpect(jsonPath("$[0]").value(hasKey("remainingOutstandingPrincipal")));

    }

    @Test
    public void testCreateProductAndCheckValue() throws Exception {
        JSONObject payload = new JSONObject();
        payload.put("loanAmount", (double) 5000);
        payload.put("nominalRate", (double) 5);
        payload.put("duration", "24");
        payload.put("startDate", "2018-01-01T00:00:01Z");

        MockHttpServletRequestBuilder createMessage = post("/v1/repayment/response")
                .param("loanAmount", "5000")
                .param("nominalRate", "5")
                .param("duration", "24")
                .param("startDate", "2018-01-01T00:00:01Z");

        mockMvc.perform(createMessage)
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$[0].date[0]", is(2018)))
                .andExpect(jsonPath("$[0].date[1]", is(1)))
                .andExpect(jsonPath("$[0].date[2]", is(1)))
                .andExpect(jsonPath("$[0].annuity", is(219.36)))
                .andExpect(jsonPath("$[0].principal", is(198.53)))
                .andExpect(jsonPath("$[0].interest", is (20.83)))
                .andExpect(jsonPath("$[0].initialOutstandingPrincipal", is(5000.0)))
                .andExpect(jsonPath("$[0].remainingOutstandingPrincipal", is(4801.47)));

    }

    @Test
    public void testChecklastObject() throws Exception {
        JSONObject payload = new JSONObject();
        payload.put("loanAmount", (double) 5000);
        payload.put("nominalRate", (double) 5);
        payload.put("duration", "24");
        payload.put("startDate", "2018-01-01T00:00:01Z");

        MockHttpServletRequestBuilder createMessage = post("/v1/repayment/response")
                .param("loanAmount", "5000")
                .param("nominalRate", "5")
                .param("duration", "24")
                .param("startDate", "2018-01-01T00:00:01Z");

        mockMvc.perform(createMessage)
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$[23].date[0]", is(2019)))
                .andExpect(jsonPath("$[23].date[1]", is(12)))
                .andExpect(jsonPath("$[23].date[2]", is(1)))
                .andExpect(jsonPath("$[23].annuity", is(219.28)))
                .andExpect(jsonPath("$[23].principal", is(218.37)))
                .andExpect(jsonPath("$[23].interest", is (0.91)))
                .andExpect(jsonPath("$[23].initialOutstandingPrincipal", is(218.37)))
                .andExpect(jsonPath("$[23].remainingOutstandingPrincipal", is(0.0)));

    }
}
