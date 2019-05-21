package crmapp.app.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import crmapp.app.Application;
import crmapp.app.entities.Client;
import crmapp.app.services.ClientService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.json.JacksonTester;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.mockito.Matchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class)
public class ClientControllerUnitTest {

    private MockMvc mockMvc;

    private JacksonTester<Client> json;

    @Autowired
    WebApplicationContext webApplicationContext;

    @MockBean
    private ClientService clientService;

    private Client mockClient;

    @Before
    public void setUp() {
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
        ObjectMapper objectMapper = new ObjectMapper();
        JacksonTester.initFields(this, objectMapper);

        mockClient = new Client();
        mockClient.setId(123);
        mockClient.setCode("kievstar");
        mockClient.setTitle("KIEVSTAR LLC");
        mockClient.setEdrpou("123456");
        mockClient.setInn("123456789");
        mockClient.setVatCertificate("36475891243");

        MockitoAnnotations.initMocks(this);
        when(clientService.save(any(Client.class))).thenReturn(mockClient);
    }

    @Test
    public void testAddClient() throws Exception {

        Client client = new Client();
        client.setCode("kievstar");
        client.setTitle("KIEVSTAR LLC");
        client.setEdrpou("123456");
        client.setInn("123456789");
        client.setVatCertificate("36475891243");

        String jsonClient = json.write(client).getJson();

        mockMvc
                .perform(
                        post("/api/clients/")
                                .contentType(APPLICATION_JSON_VALUE)
                                .content(jsonClient)
                )
                .andDo(print())
                .andExpect(status().isCreated());
    }

    @Test
    public void testUpdateClient() throws Exception {
        Client client = new Client();
        client.setId(123);
        client.setCode("kievstar");
        client.setTitle("KIEVSTAR LLC");
        client.setEdrpou("123456");
        client.setInn("123456789");
        client.setVatCertificate("36475891243");

        String jsonClient = json.write(client).getJson();

        mockMvc
                .perform(
                        put("/api/clients/123")
                                .contentType(APPLICATION_JSON_VALUE)
                                .content(jsonClient)
                )
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    public void testDeleteClient() throws Exception {
        mockMvc
                .perform(
                        delete("/api/clients/123")
                                .contentType(APPLICATION_JSON_VALUE)

                )
                .andDo(print())
                .andExpect(status().isNoContent());
    }

}
