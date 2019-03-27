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
import static org.mockito.Matchers.anyInt;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.NONE)
public class ClientServiceUnitTest {

    @Mock
    private ClientRepository clientRepository;

    @InjectMocks
    private ClientService clientService;

    private Client mockClient;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);

        mockClient = new Client();
        mockClient.setId(134);
        mockClient.setCode("kievstar");
        mockClient.setTitle("KIEVSTAR LLC");
        mockClient.setEdrpou("123456");
        mockClient.setInn("123456789");
        mockClient.setVatCertificate("36475891243");
    }

    @Test
    public void testAddClient() {
        when(clientRepository.save(any(Client.class))).thenReturn(mockClient);
        Client savedClient = clientService.save(new Client());
        assertEquals("kievstar", savedClient.getCode());
    }

    @Test
    public void testUpdateClient() {
        when(clientRepository.save(any(Client.class))).thenReturn(mockClient);

        Client savedClient = clientService.save(mockClient);
        savedClient.setCode("updated kievstar");
        savedClient.setTitle("updated KIEVSTAR LLC");
        savedClient.setEdrpou("654321");
        savedClient.setInn("987654321");
        savedClient.setVatCertificate("09090909090");

        when(clientRepository.fetchVersion(anyInt())).thenReturn(0);
        when(clientRepository.save(any(Client.class))).thenReturn(savedClient);
        Client updatedClient = clientService.update(savedClient.getId(), mockClient);

        assertEquals(updatedClient, mockClient);
        assertEquals("updated kievstar", updatedClient.getCode());
        assertEquals("updated KIEVSTAR LLC", updatedClient.getTitle());
        assertEquals("654321", updatedClient.getEdrpou());
        assertEquals("987654321", updatedClient.getInn());
        assertEquals("09090909090", updatedClient.getVatCertificate());
    }

    @Test
    public void testDeleteClient() {
        when(clientRepository.save(any(Client.class))).thenReturn(mockClient);
        Client savedClient = clientService.save(mockClient);
        clientService.delete(savedClient.getId());
    }

}
