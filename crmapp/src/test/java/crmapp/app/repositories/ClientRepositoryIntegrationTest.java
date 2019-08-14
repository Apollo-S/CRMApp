package crmapp.app.repositories;

import crmapp.app.entities.Client;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.NONE)
public class ClientRepositoryIntegrationTest {

    @Resource
    private ClientRepository clientRepository;

    Client client1;

    @Before
    public void setup() {
        this.client1 = new Client("Client 1", "client1", "12345678", "12345678", "12345678");
    }

    @Test
    public void testClientRepositoryAddClient() {
        Client savedClient = this.clientRepository.save(this.client1);

        assertThat(savedClient.getEdrpou().equals(this.client1.getEdrpou())).isTrue();
        assertThat(savedClient.getCode().equals(this.client1.getCode())).isTrue();
        assertThat(savedClient.getTitle().equals(this.client1.getTitle())).isTrue();
    }

    @Test
    public void testClientRepositoryUpdateClient() {
        Client savedClient = this.clientRepository.save(this.client1);
        savedClient.setEdrpou("12345678");
        savedClient.setCode("client232");
        savedClient.setTitle("Client 232");
        Client updatedClient = this.clientRepository.save(savedClient);

        assertThat(updatedClient.getEdrpou().equals("12345678")).isTrue();
        assertThat(updatedClient.getCode().equals("client232")).isTrue();
        assertThat(updatedClient.getTitle().equals("Client 232")).isTrue();
    }

    @Test
    @Transactional
    public void testClientRepositoryDeleteClient() {
        Client savedClient = this.clientRepository.save(this.client1);
        Client client = null;
        this.clientRepository.deleteById(savedClient.getId());
        try {
            client = this.clientRepository.getOne(savedClient.getId());
        } catch (RuntimeException e) {
            e.printStackTrace();
        }
        assertThat(client == null).isTrue();
    }

}
