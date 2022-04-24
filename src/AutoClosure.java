import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class AutoClosure 
{
	public static void main(String[] args) throws InterruptedException 
	{
		System.setProperty("webdriver.chrome.driver", "C:\\Drivers\\chromedriver.exe");
		
		ChromeOptions options=new ChromeOptions();
		options.setAcceptInsecureCerts(true);
		
		WebDriver driver=new ChromeDriver(options);
		driver.manage().timeouts().implicitlyWait(10,TimeUnit.SECONDS);
		WebDriverWait w=new WebDriverWait(driver,100);
		driver.get("https://epatientrecords-phctest.gov.mt/");
		
		driver.findElement(By.linkText("OpenID")).click();
		Thread.sleep(2000);
		driver.findElement(By.cssSelector("button[id='CorpExchange']")).click();
		driver.findElement(By.id("userNameInput")).sendKeys("corp\\gopia001");
		driver.findElement(By.id("passwordInput")).sendKeys("mita@epr22");
		driver.findElement(By.cssSelector("span[id='submitButton']")).click();
		driver.findElement(By.cssSelector("div[class='startup app ']")).click();

		Set<String> windows=driver.getWindowHandles();
		Iterator<String> it=windows.iterator();
		String w1=it.next();
		String w2=it.next();
		driver.switchTo().window(w2);

		List<WebElement> profiles=driver.findElements(By.cssSelector("li[class='list-group-item ng-scope'] a"));
		for(WebElement profile :profiles)
		{
			if(profile.getText().contains("Administrator"))
			{
				profile.click();
				break;
			}
		}
		Thread.sleep(5000);
		w.until(ExpectedConditions.elementToBeClickable(By.cssSelector("ul[id='nav'] li:nth-child(7)")));
		driver.findElement(By.cssSelector("ul[id='nav'] li:nth-child(7)")).click();
		Thread.sleep(5000);
		w.until(ExpectedConditions.elementToBeClickable(By.cssSelector("div[class='col-sm-12 col-md-12'] button:nth-child(7)")));
		driver.findElement(By.cssSelector("div[class='col-sm-12 col-md-12'] button:nth-child(7)")).click();	

		
	}
}
