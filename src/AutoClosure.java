import java.time.Duration;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.devtools.idealized.Javascript;
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
		//Thread.sleep(20000);
		//WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(120,1));
		//wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id='nav']/li[7]/a/i")));
		WebElement settingsbtn= driver.findElement(By.xpath("//li[7]/a/i"));		
		JavascriptExecutor je =(JavascriptExecutor)driver;
		je.executeScript("arguments[0].click();", settingsbtn);
		//settingsbtn.click();
		driver.findElement(By.xpath("//div[4]/button[6]")).click();
		//driver.findElement(By.xpath("//div/input[@name='servicePoint']")).sendKeys("abc");		
	}
}
