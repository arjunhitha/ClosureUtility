import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
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
		WebDriverWait w=new WebDriverWait(driver,50);
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
		driver.manage().window().maximize();
		List<WebElement> profiles=driver.findElements(By.cssSelector("li[class='list-group-item ng-scope'] a"));
		for(WebElement profile :profiles)
		{
			if(profile.getText().contains("Administrator"))
			{
				profile.click();
				break;
			}
		}

		w.until(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector("div#loading-bar--fullscreen")));
		driver.findElement(By.xpath("//button[text()='close']")).click();
		driver.findElement(By.cssSelector("ul[id='nav'] li:nth-child(7)")).click();	
		driver.findElement(By.cssSelector("div[class='col-sm-12 col-md-12'] button:nth-child(7)")).click();	
		Thread.sleep(2000);
		
		driver.findElement(By.cssSelector("span [class='input-group-addon']")).click();
		driver.findElement(By.cssSelector("input[placeholder='Filter table . . .']")).sendKeys("HC_B_008");
		Thread.sleep(2000);
		driver.findElement(By.cssSelector("input[placeholder='Filter table . . .']")).sendKeys(Keys.ENTER);
		Thread.sleep(2000);
		WebElement table=driver.findElement(By.tagName("tbody"));
		table.findElement(By.cssSelector("tbody tr td")).click();
		Thread.sleep(2000);
		WebElement agendaDropdown=driver.findElement(By.cssSelector("div [class='input-group margin-bottom-sm col-sm-12'] select[name='agenda']"));
		Select agenda=new Select(agendaDropdown); 	
		agenda.selectByVisibleText("[BHPATHCN1BL2] BHC 450 BLOOD INVESTIGATIONS");
		Thread.sleep(6000);
		
		JavascriptExecutor js=(JavascriptExecutor)driver;
		js.executeScript("window.scrollBy(0,1000)");

	}
}
