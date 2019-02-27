package crmapp.app.services;

import crmapp.app.entities.Client;
import crmapp.app.repositories.ClientRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.Assert.assertEquals;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.NONE)
public class ClientServiceUnitTest {

    @Mock
    private ClientRepository clientRepository;

    @InjectMocks
    private ClientService clientService;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testAddClient() {
        Client mockClient = new Client();
        mockClient.setAlias("kievstar");
        mockClient.setTitle("KIEVSTAR LLC");
        mockClient.setEdrpou("123456");
        mockClient.setInn("123456789");
        mockClient.setVatCertificate("36475891243");

        when(clientRepository.save(any(Client.class))).thenReturn(mockClient);

        Client savedClient = clientService.save(new Client());

        assertEquals("kievstar", savedClient.getAlias());
    }

}
