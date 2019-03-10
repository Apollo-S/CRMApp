package crmapp.app.entities;

import org.junit.Before;
import org.junit.Test;

import java.util.Date;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertEquals;

public class AddressPresentationTest {

    Address addressOne;
    Address addressTwo;
    Address addressThree;
    Address addressFour;
    Address emptyAddress;

    @Before
    public void initEntities() {
        addressOne = new Address();
        addressOne.setCountry(new Country("Украина", "UA"));
        addressOne.setZip("03150");
        addressOne.setRegion("");
        addressOne.setCity("г. Киев");
        addressOne.setStreet("ул. Большая Васильковская");
        addressOne.setBuilding("дом 65");
        addressOne.setApartment("офис 1056");

        addressTwo = new Address();
        addressTwo.setCountry(new Country("Украина", "UA"));
        addressTwo.setZip("09934");
        addressTwo.setRegion("Черниговская обл.");
        addressTwo.setCity("г. Чернигов");
        addressTwo.setStreet("ул. Пушкина");
        addressTwo.setBuilding("дом Колотушкина");
        addressTwo.setApartment("кв. 25");

        addressThree = new Address();
        addressThree.setCountry(new Country("Украина", "UA"));
        addressThree.setZip("");
        addressThree.setRegion("");
        addressThree.setCity("");
        addressThree.setStreet("");
        addressThree.setBuilding("");
        addressThree.setApartment("");

        addressFour = new Address();
        addressFour.setCountry(new Country("", ""));

        emptyAddress = new Address();
        emptyAddress.setId(1);
        emptyAddress.setDateStart(new Date());
        emptyAddress.setCountry(new Country("", ""));
        emptyAddress.setZip("");
        emptyAddress.setRegion("");
        emptyAddress.setCity("");
        emptyAddress.setStreet("");
        emptyAddress.setBuilding("");
        emptyAddress.setApartment("");
    }

    @Test
    public void isAddressCorrectTest() {
        assertThat(addressOne.getPresentation().equals("Украина, 03150, г. Киев, ул. Большая Васильковская, дом 65, офис 1056")).isTrue();
        assertThat(addressTwo.getPresentation().equals("Украина, 09934, Черниговская обл., г. Чернигов, ул. Пушкина, дом Колотушкина, кв. 25")).isTrue();
        assertThat(addressThree.getPresentation().equals("Украина")).isTrue();
        assertThat(addressThree.getPresentation().equals("Украина")).isTrue();
    }

    @Test
    public void isEmptyAddressTest() {
        assertThat(emptyAddress.getPresentation().equals("")).isTrue();
        assertThat("".equals(emptyAddress.getPresentation())).isTrue();
        assertThat(addressFour.getPresentation().equals("")).isTrue();
    }

    @Test
    public void hasAddressProperUrlAccordingType() {
        Client client = new Client();
        client.setId(123);
        Address clientAddress = new Address();
        clientAddress.setId(231);
        clientAddress.setClient(client);
        assertEquals("clients/123/addresses/231", clientAddress.getUrl());

        Employee employee = new Employee();
        employee.setId(98);
        Address employeeAddress = new Address();
        employeeAddress.setId(567);
        employeeAddress.setEmployee(employee);
        assertEquals("employees/98/addresses/567", employeeAddress.getUrl());

        OurCompany company = new OurCompany();
        company.setId(23);
        Address companyAddress = new Address();
        companyAddress.setId(970);
        companyAddress.setOurCompany(company);
        assertEquals("our-companies/23/addresses/970", companyAddress.getUrl());
    }

}
