package crmapp.app.services;

import crmapp.app.entities.Bank;
import crmapp.app.entities.Client;
import crmapp.app.entities.ClientAccount;
import crmapp.app.entities.CurrencyType;
import crmapp.app.repositories.ClientAccountRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyInt;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.NONE)
public class ClientAccountServiceUnitTest {

    @Mock
    private ClientAccountRepository clientAccountRepository;

    @InjectMocks
    private ClientAccountService clientAccountService;

    private ClientAccount mockClientAccount;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);

        Client client = new Client();
        client.setId(1);

        mockClientAccount = new ClientAccount();
        mockClientAccount.setId(1354);
        mockClientAccount.setNumber("26000034564");
        mockClientAccount.setClient(client);
        mockClientAccount.setBank(new Bank("Privat", "300711"));
        mockClientAccount.setCurrencyType(new CurrencyType("980", "Grivna", "UAH"));
    }

    @Test
    public void testAddClientAccount() {
        when(clientAccountRepository.save(any(ClientAccount.class))).thenReturn(mockClientAccount);
        ClientAccount savedClientAccount = clientAccountService.save(new ClientAccount());

        assertNotNull(savedClientAccount);
        assertNotNull(savedClientAccount.getId());
        assertEquals("Privat", savedClientAccount.getBank().getTitle());
    }

    @Test
    public void testUpdateClientAccount() {
        when(clientAccountRepository.fetchVersion(anyInt())).thenReturn(0);
        when(clientAccountRepository.save(any(ClientAccount.class))).thenReturn(mockClientAccount);

        ClientAccount savedClientAccount = clientAccountService.save(new ClientAccount());

        assertEquals(savedClientAccount, mockClientAccount);

        savedClientAccount.setNumber("261111111111");
        savedClientAccount.setClient(mockClientAccount.getClient()); // id == 1354
        savedClientAccount.setBank(new Bank("Privat24", "300711"));
        savedClientAccount.setCurrencyType(new CurrencyType("980", "Grivna", "UAH"));

        when(clientAccountRepository.save(any(ClientAccount.class))).thenReturn(savedClientAccount);
        clientAccountService.update(savedClientAccount);
        when(clientAccountRepository.findOne(anyInt())).thenReturn(mockClientAccount);
        ClientAccount updatedClientAccount = clientAccountService.findById(1354);

        assertEquals(updatedClientAccount, savedClientAccount);

        assertEquals("261111111111", updatedClientAccount.getNumber());
        assertEquals(savedClientAccount.getClient(), updatedClientAccount.getClient());
        assertEquals(new Bank("Privat24", "300711"), updatedClientAccount.getBank());
        assertEquals(savedClientAccount.getCurrencyType(), updatedClientAccount.getCurrencyType());
    }

    @Test
    public void testDeleteClientAccount() {
        when(clientAccountRepository.save(any(ClientAccount.class))).thenReturn(mockClientAccount);
        ClientAccount savedClientAccount = clientAccountService.save(new ClientAccount());

        clientAccountService.delete(savedClientAccount.getId());
    }

}
