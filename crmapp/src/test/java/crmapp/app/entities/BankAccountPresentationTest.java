package crmapp.app.entities;

import org.junit.Before;
import org.junit.Test;

import java.util.Calendar;
import java.util.GregorianCalendar;

import static org.junit.Assert.assertEquals;

public class BankAccountPresentationTest {

    ClientAccount account;

    @Before
    public void setUp() {
        account = new ClientAccount();

        account.setBank(new Bank("PrivatBank JSPC", "300711"));
        account.setNumber("260001313131");
        account.setDateStart(new GregorianCalendar(2017, Calendar.MARCH, 25).getTime());
        account.setCurrencyType(new CurrencyType("980", "Grivna", "UAH"));

    }

    @Test
    public void testIsAccountCorrect() {
        assertEquals("260001313131 (UAH), PrivatBank JSPC (МФО 300711)", account.getPresentation());
    }

}
