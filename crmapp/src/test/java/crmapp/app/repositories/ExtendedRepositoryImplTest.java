package crmapp.app.repositories;

import javax.annotation.Resource;

import crmapp.app.services.ClientService;
import org.junit.Before;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import crmapp.app.ApplicationConfiguration;
import crmapp.app.entities.Client;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = { ApplicationConfiguration.class })
public class ExtendedRepositoryImplTest {

	@Resource
	private ClientService clientService;

	@Before
	public void setup() {
		Client client1 = new Client("Client 1", "client1", "12345678", "12345678", "12345678");
		clientService.save(client1);
		Client client2 = new Client("Client 2", "client2", "87654321", "87654321", "87654321");
		clientService.save(client2);
		Client client3 = new Client("Client 3", "client3", "21324354", "21324354", "21324354");
		clientService.save(client3);
		Client client4 = new Client("Client 4", "client4", "89786756", "89786756", "89786756");
		clientService.save(client4);
	}

}