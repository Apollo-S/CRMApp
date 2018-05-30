package crmapp.app.repositories;

import static org.junit.Assert.assertEquals;

import java.util.List;

import javax.annotation.Resource;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import crmapp.app.ApplicationConfiguration;
import crmapp.app.entities.Client;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = { ApplicationConfiguration.class })
public class ExtendedRepositoryImplTest {

	@Resource
	private ClientRepository clientRepository;

	@Before
	public void setup() {
		Client client1 = new Client("Client 1", "client1", "12345678", "12345678", "12345678");
		clientRepository.save(client1);
		Client client2 = new Client("Client 2", "client2", "87654321", "87654321", "87654321");
		clientRepository.save(client2);
		Client client3 = new Client("Client 3", "client3", "21324354", "21324354", "21324354");
		clientRepository.save(client3);
		Client client4 = new Client("Client 4", "client4", "89786756", "89786756", "89786756");
		clientRepository.save(client4);
	}

	@Test
	public void givenStudents_whenFindByName_thenOk() {
		List<Client> clients = clientRepository.findByIds(1, 4);
		assertEquals("size incorrect", 2, clients.size());
	}

}