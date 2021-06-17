import org.junit.Test;

import static junit.framework.TestCase.assertEquals;

public class BasicTest extends TestHelper {


    private String username = "kim";
    private String password = "1234";

    //@Test
    public void titleExistsTest(){
        String expectedTitle = "ST Online Store";
        String actualTitle = driver.getTitle();
        
        assertEquals(expectedTitle, actualTitle);
    }


    /*
    In class Exercise

    Fill in loginLogoutTest() and login mehtod in TestHelper, so that the test passes correctly.

     */
    //@Test
    public void loginTest() throws InterruptedException{

        login(username, password);

    	Thread.sleep(2000);
    }

    /*
    In class Exercise

     Write a test case, where you make sure, that one can’t log in with a false password

     */
    // @Test
    public void loginFalsePassword() throws InterruptedException {
    	login(username, "15151");

    	Thread.sleep(2000);
    }
    
    // @Test
    public void loginFalseID() throws InterruptedException {
    	login("fghjk", password);

    	Thread.sleep(2000);
    }
    
    //@Test
    public void logoutTest() throws InterruptedException{

        login(username, password);

        logout();
    	Thread.sleep(2000);
    }
    
    //@Test
    public void Resiter() throws InterruptedException {
    	register("ho","1234","1234");	
    	Thread.sleep(2000);
    }
    
    //@Test
    public void ResiterAlreadyID() throws InterruptedException {
    	register(username,"aaaa","aaaa");	
    	Thread.sleep(2000);
    }
    
    //@Test
    public void ResiterBlankPassword() throws InterruptedException {
    	register("KKaaa","","");	
    	Thread.sleep(2000);
    }
    
    //@Test
    public void ResiterNoneMatchPassword() throws InterruptedException {
    	register("KKaaa","1234","2222");	
    	Thread.sleep(2000);
    }
    
    //@Test
    public void DeleteAcc() throws InterruptedException {
    	deleteAccount("ho","1234");	
    	Thread.sleep(2000);
    }
    
    //@Test
    public void AddItem() throws InterruptedException {
    	login(username, password);
    	addItem("title2", "discription", 100, 2);
    	Thread.sleep(5000);
    }
    
    //@Test
    public void AddAlreadyItem() throws InterruptedException {
    	login(username, password);
    	addItem("title2", "discription", 100, 2);
    	Thread.sleep(5000);
    }
    
    //@Test
    public void AddBlankItem() throws InterruptedException {
    	login(username, password);
    	addItem("", "", 10, 1);
    	Thread.sleep(5000);
    }
    
    //@Test
    public void AddBlankTypeItem() throws InterruptedException {
    	login(username, password);
    	addItem("42tia", "aaaa", 10, 0);
    	Thread.sleep(5000);
    }
    
    //@Test
    public void AddMinPriceItem() throws InterruptedException {
    	login(username, password);
    	addItem("tia", "aaaa", 0, 0);
    	Thread.sleep(5000);
    }
    
    //@Test
    public void EditItem() throws InterruptedException {
    	login(username, password);
    	editItem("title3",  "title3", "description", 30, 1);
    	Thread.sleep(2000);
    }//edit 할 때, Type은 변경이 되지 않음 - BUG
    
    //@Test
    public void DelItem() throws InterruptedException {
    	login(username, password);
    	deleteItem("title2");
    	Thread.sleep(2000);
    }

    //UserTest
    
    //@Test
    public void AddToCart() throws InterruptedException {
    	addToCart("fdgsg");
    	Thread.sleep(2000);
    }
    
    //@Test
    public void icrdec() throws InterruptedException {
    	addToCart("fdgsg");
    	incOrDec("increase", "fdgsg");
    	Thread.sleep(2000);
    }
    
    //@Test
    public void DelCartItem() throws InterruptedException {
    	addToCart("fdgsg");
    	incOrDec("increase", "fdgsg");
    	deleteCartItem("fdgsg");
    	Thread.sleep(2000);
    }
    
    //@Test
    public void DelAll() throws InterruptedException {
    	addToCart("fdgsg");
    	deleteAllItems();
    	Thread.sleep(2000);
    }
    
    //@Test
    public void SearchItem() throws InterruptedException {
    	searchItem("fdg");
    	Thread.sleep(2000);
    }
    
    //@Test
    public void SearchCategory() throws InterruptedException {
    	searchCategory("Books");
    	Thread.sleep(2000);
    }
    
    @Test
    public void Purchase() throws InterruptedException {
    	addToCart("fdgsg");
    	purchase("name", "Address", "Email", 0);
    	Thread.sleep(2000);
    }
}
