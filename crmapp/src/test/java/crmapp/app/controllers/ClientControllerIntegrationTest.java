package crmapp.app.controllers;

import crmapp.app.entities.Client;
import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class ClientControllerIntegrationTest {

    @Autowired
    ClientController clientController;

    Client client;

    @Before
    public void setup() {
        client = new Client();
        client.setCode("kievstar");
        client.setTitle("KIEVSTAR LLC");
        client.setEdrpou("123456");
        client.setInn("123456789");
        client.setVatCertificate("36475891243");
    }

    @Test
    public void testAddClient() {
        ResponseEntity<Client> savedClient = clientController.addClient(client);

        assertThat(savedClient.getStatusCode().is2xxSuccessful()).isTrue();
    }

    @Test
    public void testUpdateClient() {
        ResponseEntity<Client> respSavedClient = clientController.addClient(client);

        Client savedClient = respSavedClient.getBody();
        savedClient.setCode("updated kievstar");
        savedClient.setTitle("updated KIEVSTAR LLC");
        savedClient.setEdrpou("654321");
        savedClient.setInn("987654321");
        savedClient.setVatCertificate("09090909090");

        ResponseEntity<Client> respUpdClient = clientController.updateEntity(savedClient.getId(), savedClient);
        System.out.println("respUpdClient = " + respUpdClient);
        assertThat(respUpdClient.getStatusCode().is2xxSuccessful()).isTrue();
        assertThat(respUpdClient.getBody().equals(savedClient)).isTrue();
    }

    @Test
    public void testDeleteClient() {
        ResponseEntity<Client> respSavedClient = clientController.addClient(client);

        Client savedClient = respSavedClient.getBody();
        ResponseEntity<Void> response = clientController.deleteClient(savedClient.getId());
        assertThat(response.getStatusCode().is2xxSuccessful()).isTrue();
    }
}
