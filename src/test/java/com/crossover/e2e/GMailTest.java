package test.java.com.crossover.e2e;

import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.datatransfer.StringSelection;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class GMailTest {		
	
	    private static WebDriver driver;
	    private Properties properties = new Properties();
	    public void setUp() throws Exception 
	    {
	    	System.setProperty("webdriver.chrome.driver", "E:\\selenium\\se jars\\chromedriver_win32\\chromedriver.exe");
	        driver = new ChromeDriver();
	        driver.manage().window().maximize();
	        // TO load the property file for reading values
	        properties.load(new FileReader(new File("test.properties")));
	    }    
	    
	    public void sendEmail() throws Exception {
	    	// Goto Gmail website
	        driver.get("https://mail.google.com/");
	        driver.manage().timeouts().implicitlyWait(50, TimeUnit.SECONDS);
	        //send user name 
	        WebElement user = driver.findElement(By.id("identifierId"));
	        user.sendKeys(properties.getProperty("username"));  
	        driver.findElement(By.id("identifierNext")).click();
	        Thread.sleep(2000);
	        //send password
	        WebElement password = driver.findElement(By.name("password"));
	        password.sendKeys(properties.getProperty("password"));
	        driver.findElement(By.id("passwordNext")).click();
	        Thread.sleep(5000);
	        WebElement compose = driver.findElement(By.xpath("//div[@class='T-I J-J5-Ji T-I-KE L3']"));//*[@id=":kb"]/div/div
	        compose.click();
	        Thread.sleep(2000);
	        driver.findElement(By.name("to")).clear();
	        driver.findElement(By.name("to")).sendKeys(properties.getProperty("username"));	        
	        driver.findElement(By.name("subjectbox")).sendKeys("Assessment for selenium with java");	          
	       Thread.sleep(2000);
	       driver.findElement(By.xpath("(//*[@aria-label='Message Body'])[2]")).sendKeys("this is auto generated email for testing.");	        
	        driver.findElement(By.xpath("//*[@aria-label='Attach files']")).click();	        
	        Thread.sleep(5000);
	        StringSelection s = new StringSelection("E:\\sujithwp\\qa-automation-selenium-java\\123.png");
	        Toolkit.getDefaultToolkit().getSystemClipboard().setContents(s,null);	        
	        Robot r = new Robot();
	        r.keyPress(KeyEvent.VK_CONTROL);
	        r.keyPress(KeyEvent.VK_V);
	        r.keyRelease(KeyEvent.VK_CONTROL);
	        r.keyRelease(KeyEvent.VK_V);	        
	        r.keyPress(KeyEvent.VK_ENTER);   
	        Thread.sleep(4000);
	        driver.findElement(By.xpath("//*[@role='button' and text()='Send']")).click();	       
	        Thread.sleep(4000);
	        //refresh the page
	        driver.navigate().refresh();	       
	    }	
	    public void verifySentMail() throws InterruptedException, IOException {			
	    	WebElement sentMail = driver.findElement(By.xpath("(//span[contains(@email,'@gmail.com')] [@name='me'] [text()='me'])[2]"));
	    	Support.highLightError(sentMail);
	    	Support.waitForElement(100, sentMail);
	    	sentMail.click();	    	
	    	WebElement sentMailSubject = driver.findElement(By.xpath("//h2[text()='Assessment for selenium with java']"));
	    	Support.highLightError(sentMailSubject);
	    	Support.waitForElement(30, sentMailSubject);	    	
	    	String subject = sentMailSubject.getText();	    	
	    	if(subject.equals("Assessment for selenium with java"))
	    	{
	    		System.out.println("subject is correct");
	    	}
	    	else
	    	{
	    		Support.screenShot();
	    		System.out.println("subject not is correct");
	    	}		    	
	    	Thread.sleep(5000);	    	
	    	WebElement body = driver.findElement(By.xpath("//img[@aria-label='Show details']/following::div[1]/following-sibling::div/div[2]/following::div[3]"));
	        Support.highLightError(body);
	    	Support.waitForElement(30, body);	    	
	    	String bodyText = body.getText();	    	
	    	if(bodyText.equals("this is auto generated email for testing."))
	    	{
	    		System.out.println("body text is correct");
	    	}
	    	else
	    	{
	    		Support.screenShot();
	    		System.out.println("body text is not correct");
	    	}	    	
	    	Thread.sleep(3000);
	    	Support.moveToElement(getDriver().findElement(By.xpath("//img[@title='Image']/preceding::div[4]")));
	    	WebElement file = driver.findElement(By.xpath("//img[@title='Image']/following::div[3]/span"));
	    	Support.waitForElement(50, file);
	    	Support.highLightError(file);	    	
	    	String attachment = file.getText();	    	
	    	if(attachment.equals("123.png"))
	    	{
	    		System.out.println("attachment name is correct");
	    	}
	    	else
	    	{
	    		Support.screenShot();
	    		System.out.println("attachment name not is correct");
	    	}	
		}
	    public void tearDown() throws Exception {
	        driver.quit();
	    }
	        
	    public static WebDriver getDriver() {
			return driver;
		}
}

