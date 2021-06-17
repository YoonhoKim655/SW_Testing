package original;

import junit.framework.TestCase;
import org.easymock.EasyMock;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;

public class CurrencyTest extends TestCase {
	 ExchangeRate mock;
	 Currency Test_C1;
	 Currency Test_C2;
	 
	 @Before
	protected void setUp() throws Exception {
		 mock = EasyMock.createMock(ExchangeRate.class);
		 Test_C1 = new Currency(1.50, "WON");
	     Test_C2 = new Currency(3.00, "EUR");
	}
	
	@Test
    public void testCurrency() throws IOException 
    {
        EasyMock.expect(mock.getRate("WON", "EUR")).andReturn(2.0);
        EasyMock.replay(mock);
        
        Currency actual = Test_C1.toEuros(mock);
        assertEquals(Test_C2, actual);
        EasyMock.verify(mock);
    }
}