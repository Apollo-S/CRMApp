package crmapp.app.repositories.base;

import crmapp.app.entities.Bank;
import crmapp.app.entities.Client;
import crmapp.app.entities.ClientAccount;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.NONE)
public class ExtendedBaseRepositoryImplTest {

    @Resource
    private ExtendedBaseRepositoryImpl clientRepository;

    @Test
    public void testSaveEntityWith() {
        Client client = new Client();
        client.setId(123);
        client.setCode("clientTest");
        client.setEdrpou("12345678");

        ClientAccount account = new ClientAccount();
        account.setBank(new Bank("PrivatBank", "320355"));
        account.setNumber("2600000032230");
        account.setClient(client);
    }
}