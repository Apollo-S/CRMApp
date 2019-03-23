package crmapp.app.controllers;

import crmapp.app.entities.Client;
import crmapp.app.services.ClientService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import static org.mockito.Matchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(MockitoJUnitRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.NONE)
public class ClientControllerUnitTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ClientService clientService;

    @InjectMocks
    private ClientController clientController;

    private Client mockClient;

    public void setUp() {
        MockitoAnnotations.initMocks(this);
        when(clientService.save(any(Client.class))).thenReturn(mockClient);

        mockClient = new Client();
        mockClient.setId(134);
        mockClient.setAlias("kievstar");
        mockClient.setTitle("KIEVSTAR LLC");
        mockClient.setEdrpou("123456");
        mockClient.setInn("123456789");
        mockClient.setVatCertificate("36475891243");
    }

    @Test
    public void testAddClient() throws Exception {
//
//
//        Client client = new Client();
//        client.setId(134);
//        client.setAlias("kievstar");
//        client.setTitle("KIEVSTAR LLC");
//        client.setEdrpou("123456");
//        client.setInn("123456789");
//        client.setVatCertificate("36475891243");
//
//        System.out.println("client = " + client);
//
//        mockMvc
//                .perform(
//                        post("/api/clients/", client))
//                .andExpect(status().isOk()).andReturn();

    }

}
