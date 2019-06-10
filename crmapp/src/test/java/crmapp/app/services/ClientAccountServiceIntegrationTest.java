package crmapp.app.services;

import crmapp.app.entities.Bank;
import crmapp.app.entities.Client;
import crmapp.app.entities.ClientAccount;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.NONE)
public class ClientAccountServiceIntegrationTest {

    private ClientAccount clientAccount;

    @Autowired
    private ClientAccountService clientAccountService;

    @Before
    public void setup() {
        Date date = new GregorianCalendar(2014, Calendar.FEBRUARY, 11).getTime();
        Client client = new Client();
        client.setId(1);

        clientAccount = new ClientAccount();
        clientAccount.setNumber("26007017134344");
        clientAccount.setBank(new Bank("АТ \"Сити-Банк\"", "311009"));
        clientAccount.setDateStart(date);
        clientAccount.setClient(client);
    }

    @Test
    @Transactional
    public void testAddClientAccount() {
        ClientAccount savedAccount = clientAccountService.save(this.clientAccount);
        System.out.println(savedAccount);
        assertNotNull(savedAccount);
        assertNotNull(savedAccount.getId());
        assertEquals("26007017134344", savedAccount.getNumber());
        assertEquals(new Integer(1), savedAccount.getClient().getId());
    }

    @Test
    @Transactional
    public void testUpdateClientAccount() {
        ClientAccount savedAccount = clientAccountService.save(this.clientAccount);
        savedAccount.setBank(new Bank("PrivatBank JSPC", "300711"));
        savedAccount.setNumber("260001313131");
        savedAccount.setDateStart(new GregorianCalendar(2017, Calendar.MARCH, 25).getTime());

        ClientAccount updatedAccount = clientAccountService.update(savedAccount);

        assertEquals("PrivatBank JSPC", updatedAccount.getBank().getTitle());
        assertEquals("300711", updatedAccount.getBank().getMfo());
        assertEquals("260001313131", updatedAccount.getNumber());

    }

    @Test
    @Transactional
    public void testDeleteClientAccount() {
        ClientAccount savedAccount = clientAccountService.save(this.clientAccount);
        clientAccountService.delete(savedAccount.getId());
        assertNull(clientAccountService.findById(savedAccount.getId()));
    }

}
