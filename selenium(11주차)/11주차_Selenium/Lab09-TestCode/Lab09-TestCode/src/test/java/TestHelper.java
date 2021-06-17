import org.junit.After;
import org.junit.Before;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.Select;

import java.util.List;
import java.util.concurrent.TimeUnit;

public class TestHelper {

    static WebDriver driver;
    final int waitForResposeTime = 2000;
	
	// here write a link to your admin website (e.g. http://my-app.herokuapp.com/admin)
    String baseUrlAdmin = "https://secure-lake-33315.herokuapp.com/admin";
	
	// here write a link to your website (e.g. http://my-app.herokuapp.com/)
    String baseUrl = "https://secure-lake-33315.herokuapp.com/";

    @Before
    public void setUp(){

        // if you use Chrome:
        System.setProperty("webdriver.chrome.driver", "C:\\Users\\Yoonho\\chromedriver_win32\\chromedriver.exe");
        driver = new ChromeDriver();

        // if you use Firefox:
        //System.setProperty("webdriver.gecko.driver", "C:\\Users\\...\\geckodriver.exe");
        //driver = new FirefoxDriver();

        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.get(baseUrl);

    }

    void goToPage(String page){
        WebElement elem = driver.findElement(By.linkText(page));
        elem.click();
        waitForElementById(page);
    }

    void waitForElementById(String id){
        new WebDriverWait(driver, waitForResposeTime).until(ExpectedConditions.presenceOfElementLocated(By.id(id)));
    }

    public boolean isElementPresent(By by) {
        try {
            driver.findElement(by);
            return true;
        }
        catch (NoSuchElementException e) {
            return false;
        }
    }

    void login(String username, String password){

        driver.get(baseUrlAdmin);

        driver.findElement(By.linkText("Login")).click();

        driver.findElement(By.id("name")).sendKeys(username);
        
        driver.findElement(By.id("password")).sendKeys(password);
        
        // ...

        By loginButtonXpath = By.xpath("//input[@value='Login']");
        
        // click on the button
        driver.findElement(loginButtonXpath).click();
    }

    void logout(){
        WebElement logout = driver.findElement(By.linkText("Logout"));
        logout.click();

        waitForElementById("Admin");
    }
    
    void register(String name, String password, String confirm)
    {
    	driver.get(baseUrlAdmin);
    
    	driver.findElement(By.linkText("Register")).click();
    	driver.findElement(By.id("user_name")).sendKeys(name);
    	driver.findElement(By.id("user_password")).sendKeys(password);
    	driver.findElement(By.id("user_password_confirmation")).sendKeys(confirm);
    	
    	By RegisterButtonXpath = By.xpath("//input[@value='Create User']");
    	driver.findElement(RegisterButtonXpath).click();
    }
    
    void deleteAccount(String name, String password)
    {
    	driver.get(baseUrlAdmin);
    	
    	login(name,password);
        
    	goToPage("Admin");
    	
    	//By DeleteButtonXpath = By.xpath("//a[@data-method='delete']");
    	driver.findElement(By.linkText("Delete")).click();
    	waitForElementById("Admin");
    }
    
    void addItem(String title, String Description, int price, int p_type)
    {	
    	driver.findElement(By.linkText("Products")).click();
    	driver.findElement(By.xpath("//a[@href='/products/new']")).click();
    	
    	driver.findElement(By.id("product_title")).sendKeys(title);
    	driver.findElement(By.id("product_description")).sendKeys(Description);
    	driver.findElement(By.id("product_price")).sendKeys(String.valueOf(price));
    	
    	
    	Select PType = new Select(driver.findElement(By.id("product_prod_type")));
    	PType.selectByIndex(p_type);
    	
    	By addItemButtonXpath = By.xpath("//input[@value='Create Product']");
    	driver.findElement(addItemButtonXpath).click();    
    }
    
    void editItem(String p_id,  String title, String Description,
    		int price, int p_type)
    {
    	driver.get(baseUrlAdmin);

    	driver.findElement(By.linkText("Products")).click();
    	driver.findElement(By.id(p_id)).findElement(By.linkText("Edit")) .click();
    	
    	driver.findElement(By.id("product_title")).clear();
    	driver.findElement(By.id("product_title")).sendKeys(title);
    	
    	driver.findElement(By.id("product_description")).clear();
    	driver.findElement(By.id("product_description")).sendKeys(Description);
    	
    	driver.findElement(By.id("product_price")).clear();
    	driver.findElement(By.id("product_price")).sendKeys(String.valueOf(price));
    	
    	Select PType = new Select(driver.findElement(By.id("product_prod_type")));
    	PType.selectByIndex(p_type);
    	
    	By editItemButtonXpath = By.xpath("//input[@value='Update Product']");
    	driver.findElement(editItemButtonXpath).click();
    }
    
    void deleteItem(String p_id)
    {
    	driver.findElement(By.linkText("Products")).click();
    	
    	driver.findElement(By.id(p_id)).findElement(By.linkText("Delete")) .click();
    }
    
    //User Test
    
    void addToCart(String p_id)
    {
    	driver.get(baseUrl);
    	
    	driver.findElement(By.xpath("//div[@id='"+p_id+"_entry']")).findElement(By.xpath("//input[@value='Add to Cart']")) .click();
    }
    
    void incOrDec(String decision, String p_id)
    {
    	driver.get(baseUrl);
    	
    	By incOrdecButtonXpath = By.xpath("//tr[@class='cart_row']");
    	List <WebElement> CartRow = driver.findElements(incOrdecButtonXpath);
    	
    	for(int i = 0; i < CartRow.size(); i++)
    	{
    		List <WebElement> Text = CartRow.get(i).findElements(By.xpath("//td"));
    		
    		if (p_id.equals(Text.get(1).getText()))
    		{
    			if(decision == "increase")
    				driver.findElement(By.linkText("↑")).click();
    			else
    				driver.findElement(By.linkText("↓")).click();
    			break;
    		}
    	}
    }
    
    void deleteCartItem(String p_id)
    {
    	driver.get(baseUrl);
    	
    	By incOrdecButtonXpath = By.xpath("//tr[@class='cart_row']");
    	List <WebElement> CartRow = driver.findElements(incOrdecButtonXpath);
    	
    	for(int i = 0; i < CartRow.size(); i++)
    	{
    		List <WebElement> Text = CartRow.get(i).findElements(By.xpath("//td"));
    		
    		if (p_id.equals(Text.get(1).getText()))
    		{
    			driver.findElement(By.linkText("X")).click();
    			break;
    		}
    	}
    }
    
    void deleteAllItems()
    {
    	driver.get(baseUrl);
    	
    	By DeleteAllButtonXpath = By.xpath("//input[@value='Empty cart']");
    	driver.findElement(DeleteAllButtonXpath).click();
    }
    
    void searchItem(String item)
    {
    	driver.get(baseUrl);
    	
    	driver.findElement(By.id("search_input")).sendKeys(item);
    }
    
    void searchCategory(String category)
    {
    	driver.get(baseUrl);
    	
    	By CategotyButtonXpath = By.xpath("//a[@href = '/store/filter?sort="+category+"']");
    	driver.findElement(CategotyButtonXpath).click();
    }
    
    void purchase(String name, String Address, String Email, int pay_type)
    {
    	driver.get(baseUrl);
    	
    	By CheckoutButtonXpath = By.xpath("//input[@value='Checkout']");
    	driver.findElement(CheckoutButtonXpath).click();
    	
    	driver.findElement(By.id("order_name")).sendKeys(name);
    	driver.findElement(By.id("order_address")).sendKeys(Address);
    	driver.findElement(By.id("order_email")).sendKeys(Email);
    	
    	Select PType = new Select(driver.findElement(By.id("order_pay_type")));
    	PType.selectByIndex(pay_type);
    	
    	By PlaceOrderButtonXpath = By.xpath("//input[@value='Place Order']");
    	driver.findElement(PlaceOrderButtonXpath).click();
    }
    
    @After
    public void tearDown(){
        driver.close();
    }

}