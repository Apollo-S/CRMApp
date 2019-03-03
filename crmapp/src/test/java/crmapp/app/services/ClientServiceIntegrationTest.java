package crmapp.app.services;

import crmapp.app.entities.Client;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.NONE)
public class ClientServiceIntegrationTest {

    private Client client;

    @Autowired
    private ClientService clientService;

    @Before
    public void setup() {
        client = new Client();
        client.setAlias("kievstar");
        client.setTitle("KIEVSTAR LLC");
        client.setEdrpou("123456");
        client.setInn("123456789");
        client.setVatCertificate("36475891243");
    }

    @Test
    public void testAddClient() {
        Client savedClient = clientService.save(client);

        assertNotNull(savedClient);
        assertNotNull(savedClient.getId());
        assertEquals("kievstar", savedClient.getAlias());
    }

    @Test
    public void testUpdateClient() {
        Client savedClient = clientService.save(client);

        savedClient.setAlias("updated kievstar");
        savedClient.setTitle("updated KIEVSTAR LLC");
        savedClient.setEdrpou("654321");
        savedClient.setInn("987654321");
        savedClient.setVatCertificate("09090909090");
        Client updatedClient = clientService.update(savedClient.getId(), savedClient);

        assertNotNull(updatedClient);
        assertNotNull(updatedClient.getId());
        assertEquals("updated kievstar", updatedClient.getAlias());
        assertEquals("updated KIEVSTAR LLC", updatedClient.getTitle());
        assertEquals("654321", updatedClient.getEdrpou());
        assertEquals("987654321", updatedClient.getInn());
        assertEquals("09090909090", updatedClient.getVatCertificate());
    }

    @Test
    public void testDeleteClient() {
        Client savedClient = clientService.save(client);

        clientService.delete(savedClient.getId());
        assertNull(clientService.findById(savedClient.getId()));
    }

}
