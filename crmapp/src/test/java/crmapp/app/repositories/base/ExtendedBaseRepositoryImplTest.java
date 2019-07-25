package crmapp.app.repositories.base;

import crmapp.app.entities.Bank;
import crmapp.app.entities.Client;
import crmapp.app.entities.ClientAccount;
import crmapp.app.entities.EmployeePost;
import crmapp.app.repositories.EmployeePostRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.junit.Assert.assertNotNull;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.NONE)
public class ExtendedBaseRepositoryImplTest {

    @Autowired(required=true)
    private EmployeePostRepository repository;

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

    @Test
    public void test_fetchDataByNamedQuery() {
        int employeeId = 1;
        List<EmployeePost> result = repository.fetchDataByNamedQuery(EmployeePost.FIND_ALL_POSTS_BY_EMPLOYEE_ID, EmployeePost.class, employeeId);

        assertNotNull(result);
    }

}