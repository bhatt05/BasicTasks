package def;
import java.io.*;
import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

import junit.framework.Assert;

public class TestSel {
	WebDriver driver;
	@Test
	public void a() throws IOException {
		System.setProperty("webdriver.chrome.driver","C:\\Users\\jagdishbhatt\\Downloads\\chromedriver_win32\\chromedriver.exe");
		driver=new ChromeDriver();
		driver.manage().window().maximize();
		driver.get("https://hris.qainfotech.com/login.php");
		
		FileReader read=new FileReader("C:\\Users\\jagdishbhatt\\eclipse-workspace\\def\\src\\test\\java\\def\\cre.txt");
		Properties p=new Properties();
		p.load(read);
		String username=p.getProperty("user");
		String password=p.getProperty("pass");
		
		WebElement uname=driver.findElement(By.id("txtUserName"));
		WebElement pass=driver.findElement(By.id("txtPassword"));
		uname.sendKeys(username);
		pass.sendKeys(password);
		driver.findElement(By.xpath("//input[@type=\"submit\"]")).click();
		String title=driver.getTitle();
		Assert.assertEquals("Title not match", "QAIT Resource Management Tool ", title);
		

	}
	@AfterMethod
	public void after(ITestResult result) throws IOException {
		if(ITestResult.FAILURE==result.getStatus()) {
			try{TakesScreenshot ts=(TakesScreenshot)driver;
			File screenshort=ts.getScreenshotAs(OutputType.FILE);
			FileUtils.copyFile(screenshort, new File("./ScreenShots/"+result.getName()+".png"));
			System.out.println("Taken");
			}
			catch(Exception e) {}
			
		}
	}
	
}
