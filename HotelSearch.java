import java.util.List;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

public class HotelSearch {

	public static void main(String[] args) throws InterruptedException {
		// TODO Auto-generated method stub

		System.setProperty("webdriver.chrome.driver", "C:\\Users\\Natalia\\chromedriver.exe");
		WebDriver driver= new ChromeDriver();
		
		driver.get("https://www.phptravels.net");
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		driver.findElement(By.id("cookyGotItBtn")).click();
		
		driver.findElement(By.cssSelector("#s2id_location > a")).click();
		Thread.sleep(2000);
		driver.findElement(By.xpath("//*[@id='select2-drop']/div/input")).sendKeys("duba");
		Thread.sleep(5000);
		driver.findElement(By.xpath("//*[@id='select2-drop']/div/input")).sendKeys(Keys.ENTER);
		
		driver.findElement(By.cssSelector("input[class='form form-control input-lg hcheckin']")).click();
		
		int count = driver.findElements(By.xpath("/html/body/div[13]/div[1]/table/tbody/tr/td")).size();
		for (int i=0; i<count; i++) {
			List<WebElement> date = driver.findElements(By.xpath("/html/body/div[13]/div[1]/table/tbody/tr/td"));
			if (date.get(i).getAttribute("class").contains("active")) {
				date.get(i+1).click();
				break;
			}
		}
		
		int count2 = driver.findElements(By.xpath("/html/body/div[13]/div[1]/table/tbody/tr/td")).size();
		for (int j=0; j<count2; j++) {
			List<WebElement> date2 = driver.findElements(By.xpath("/html/body/div[14]/div[1]/table/tbody/tr/td"));
			if (date2.get(j).getAttribute("class").contains("active")) {
				String a = date2.get(j).getAttribute("cellIndex");
				int x = Integer.parseInt(a);
				int y = 7-x;
				date2.get(j+y).click();
				break;
			}
		}
		
		driver.findElement(By.id("htravellersInput")).click();
		WebDriverWait w = new WebDriverWait(driver, 20);
		w.until(ExpectedConditions.visibilityOfElementLocated(By.id("hadultMinusBtn")));
		driver.findElement(By.id("hadultMinusBtn")).click();
		driver.findElement(By.id("hchildPlusBtn")).click();
		driver.findElement(By.cssSelector("button[class='btn btn-lg btn-block btn-primary pfb0 loader']")).click();
		
		Thread.sleep(5000);
		int results = driver.findElements(By.xpath("//*[@id=\"listing\"]/tbody/tr")).size();
		if(results > 1) {
			System.out.println("Search successful - more than one result");
			Assert.assertTrue(true);
		}
		else {
			Assert.assertTrue(false);
		}
		
		int prices = driver.findElements(By.cssSelector("[class='fs26 text-center']")).size();
		int descr = driver.findElements(By.cssSelector("[class='grey RTL fs12 hidden-xs']")).size();
		int titles = driver.findElements(By.cssSelector("[class='RTL go-text-right mt0 list_title']")).size();
		int details = driver.findElements(By.cssSelector("[class='grey RTL fs12 hidden-xs']")).size();
		
		Assert.assertTrue(results == prices
				&& results == descr
				&& results == titles
				&& results == details);
		
	}

}
