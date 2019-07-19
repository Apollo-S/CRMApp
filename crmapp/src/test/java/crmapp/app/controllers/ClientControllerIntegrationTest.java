package crmapp.app.controllers;

import crmapp.app.entities.dto.ClientDTO;
import crmapp.app.entities.Client;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class ClientControllerIntegrationTest {

    @Autowired
    ClientController clientController;

    Client client;
    ClientDTO clientDTO;

    @Before
    public void setup() {
        client = new Client();
        client.setCode("kievstar");
        client.setTitle("KIEVSTAR LLC");
        client.setEdrpou("123456");
        client.setInn("123456789");
        client.setVatCertificate("36475891243");
        clientDTO = new ClientDTO();
        BeanUtils.copyProperties(client, clientDTO);
    }

    @Test
    public void testAddClient() {
        ResponseEntity<ClientDTO> savedClient = clientController.addClient(clientDTO);

        assertThat(savedClient.getStatusCode().is2xxSuccessful()).isTrue();
    }

    @Test
    public void testUpdateClient() {
        ResponseEntity<ClientDTO> respSavedClient = clientController.addClient(clientDTO);

        ClientDTO savedClient = respSavedClient.getBody();
        savedClient.setCode("updated kievstar");
        savedClient.setTitle("updated KIEVSTAR LLC");
        savedClient.setEdrpou("654321");
        savedClient.setInn("");
        savedClient.setVatCertificate("");

        ResponseEntity<Void> respUpdClient = clientController.updateEntity(savedClient.getId(), savedClient);
        System.out.println("respUpdClient = " + respUpdClient);
        assertThat(respUpdClient.getStatusCode().is2xxSuccessful()).isTrue();
    }

    @Test
    public void testDeleteClient() {
        ResponseEntity<ClientDTO> respSavedClient = clientController.addClient(clientDTO);

        ClientDTO savedClient = respSavedClient.getBody();
        ResponseEntity<Void> response = clientController.deleteClient(savedClient.getId());
        assertThat(response.getStatusCode().is2xxSuccessful()).isTrue();
    }
}
