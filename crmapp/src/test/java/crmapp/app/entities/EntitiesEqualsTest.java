package crmapp.app.entities;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Calendar;
import java.util.GregorianCalendar;

import org.junit.Before;
import org.junit.Test;

@SuppressWarnings("unlikely-arg-type")
public class EntitiesEqualsTest {
	Client client1;
	Client client2;
	Client client3;
	OurCompany company;
	ClientDirector clientDirector1;
	ClientDirector clientDirector2;
	ClientDirector clientDirector3;
	OurCompanyDirector ourCompanyDirector1;
	
	@Before
	public void initEntities() {
		client1 = new Client("ClientWithId=123", "abrakadabra123", "13243546", null, null);
		client1.setId(123);
		client2 = new Client("ClientWithId=32123", "abrakadabra321", "56453423", null, null);
		client2.setId(321);
		client3 = new Client("ClientWithId=123", "abrakadabra123", "13243546", null, null);
		client3.setId(123);
		company = new OurCompany("ClientWithId=123", "abrakadabra123", "13243546", null, null);
		company.setId(123);
		clientDirector1 = new ClientDirector(client1, new Post(), "Petrov A.G.", "Petrov A.G.",
				new GregorianCalendar(2014, Calendar.FEBRUARY, 11).getTime());
		clientDirector1.setId(234);
		clientDirector2 = new ClientDirector(client2, new Post(), "Petrov A.V.", "Petrov A.V.",
				new GregorianCalendar(2014, Calendar.JULY, 12).getTime());
		clientDirector2.setId(234);
		clientDirector3 = new ClientDirector(client3, new Post(), "Petrov A.G.", "Petrov A.G.",
				new GregorianCalendar(2014, Calendar.FEBRUARY, 11).getTime());
		clientDirector3.setId(234);
		ourCompanyDirector1 = new OurCompanyDirector(company, new Post(), "Petrov A.G.", "Petrov A.G.",
				new GregorianCalendar(2014, Calendar.FEBRUARY, 11).getTime());
		ourCompanyDirector1.setId(234);
	}

	@Test
	public void isClientEqualsTest() {
		assertThat(client1.equals(client2)).isFalse();
		assertThat(client1.equals(client1) && client1.equals(client3) && client3.equals(client1) && client3.equals(client3)).isTrue();
		assertThat(client3.equals(company)).isFalse();
	}

	@Test
	public void isCLientDirectorEqualsTest() {
		assertThat(clientDirector1.equals(clientDirector2)).isFalse();
		assertThat(clientDirector1.equals(clientDirector1)).isTrue();
		assertThat(clientDirector3.equals(clientDirector3)).isTrue();
		assertThat(clientDirector1.equals(clientDirector3)).isTrue();
		assertThat(clientDirector1.equals(ourCompanyDirector1)).isFalse();
	}
}