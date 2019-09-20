import java.util.concurrent.TimeUnit;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;

public class WhishlistCheck {

	public static void main(String[] args) throws InterruptedException {
		// TODO Auto-generated method stub
		
		System.setProperty("webdriver.chrome.driver", "C:\\Users\\Natalia\\chromedriver.exe");
		WebDriver driver= new ChromeDriver();
		
		driver.get("https://www.phptravels.net");
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		driver.findElement(By.id("cookyGotItBtn")).click();
		
		JavascriptExecutor js = (JavascriptExecutor)driver;
		
		WebElement element1 = driver.findElement(By.xpath("//*[@id='li_myaccount']/a"));
		if (element1.getAttribute("innerText").contains(" MY ACCOUNT ")) {
			js.executeScript("arguments[0].click();", element1);
		}
		
		
		WebElement element2 = driver.findElement(By.xpath("//*[@id='li_myaccount']/ul/li[1]/a"));
		if (element2.getAttribute("innerText").contains("Login")) {
			js.executeScript("arguments[0].click();", element2);
		}
		
		driver.findElement(By.xpath("//input[@name='username']")).sendKeys("user@phptravels.com");
		driver.findElement(By.xpath("//input[@name='password']")).sendKeys("demouser");
		driver.findElement(By.xpath("//*[@id='loginfrm']/button")).click();
		
		driver.findElement(By.xpath("/html/body/nav/div/div[2]/ul[1]/li[1]/a")).click();
		driver.findElements(By.cssSelector("a[class='button btn-small']")).get(0).click();
		driver.findElement(By.className("wishtext")).click();
		driver.switchTo().alert().accept();
		Thread.sleep(10000);
		
		WebElement user = driver.findElement(By.xpath("/html/body/nav/div/div[2]/ul[2]/ul/li[1]/a"));
		js.executeScript("arguments[0].click();", user);
		
		
		WebElement account = driver.findElement(By.xpath("/html/body/nav/div/div[2]/ul[2]/ul/li[1]/ul/li[1]/a"));
		js.executeScript("arguments[0].click();", account);
		
		driver.findElement(By.className("wishlist-icon")).click();
						
		int count = driver.findElements(By.cssSelector("span[class*='removewish']")).size();
		System.out.println(count);
		
		driver.findElements(By.cssSelector("span[class*='removewish']")).get(0).click();
		
		driver.switchTo().alert().accept();
		driver.navigate().refresh();
		driver.findElement(By.className("wishlist-icon")).click();
		
		int count2 = driver.findElements(By.cssSelector("span[class*='removewish']")).size();
		System.out.println(count2);
		
		if ((count-1) == count2) {
			System.out.println("Item successfully removed");
			Assert.assertTrue(true);
		}
		else {
			System.out.println("Operation not successful");
			Assert.assertTrue(false);
		}

	}

}
