package crmapp.app.services;

import crmapp.app.entities.Client;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.NONE)
public class ClientServiceIntegrationTest {

    @Autowired
    private ClientService clientService;

    @Test
    public void testAddClient() {
        Client client = new Client();
        client.setAlias("kievstar");
        client.setTitle("KIEVSTAR LLC");
        client.setEdrpou("123456");
        client.setInn("123456789");
        client.setVatCertificate("36475891243");

        Client savedClient = clientService.save(client);

        assertNotNull(savedClient);
        assertNotNull(savedClient.getId());
        assertEquals("kievstar", savedClient.getAlias());
    }

}
