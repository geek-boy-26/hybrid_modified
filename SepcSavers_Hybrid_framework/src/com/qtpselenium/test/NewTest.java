package com.qtpselenium.test;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.annotations.Test;

public class NewTest {
public WebDriver driver;
	@Test
  public void f() throws InterruptedException {
	  

System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir")+"//driver//chromedriver.exe");
driver = new ChromeDriver();

driver.get("http://10.100.20.45:9090/mobiledoc/jsp/visionemr/visionlogin.jsp");
driver.manage().window().maximize();
driver.findElement(By.id("doctorID")).sendKeys("sam");
driver.findElement(By.id("password")).sendKeys("password$1");
driver.findElement(By.xpath("//*[@id='loginForm']/button")).click();
Thread.sleep(5000);
driver.findElement(By.xpath("//div[@id='jellyBeanDiv']/div/a")).click();
Thread.sleep(5000);
driver.findElement(By.xpath("//*[@id='leftnav']/nav/ul[1]/li[1]/a")).click();

Thread.sleep(5000);
//double click on element in the resource schedule screen
Actions action = new Actions(driver);
WebElement element = driver.findElement(By.xpath(".//*[@id='slotContainer']/table/tbody/tr[15]/td/div"));
//action.moveToElement(driver.findElement(By.xpath("//*[@id='slotContainer']/table/tbody/tr[12]/td"))).doubleClick().build().perform();
Thread.sleep(2000);
/**right click code**/
action.click();
action.contextClick(element).sendKeys(Keys.ARROW_DOWN).sendKeys(Keys.ENTER).perform();
System.out.println("performed");
	  Thread.sleep(5000);
driver.findElement(By.xpath("(//input[@id='custLookup'])[4]")).sendKeys("Gupte");
Thread.sleep(5000);
driver.findElement(By.xpath("//*[@id='patientLkpcls']/div/div[2]/ul/div/li[2]/div[2]/h4/span")).click();

driver.findElement(By.xpath("(//button[@type='button'])[68]")).click();
Thread.sleep(5000);
String text = driver.findElement(By.xpath("/html/body/div[7]/div/div/div[2]/div/div")).getText();
System.out.println(text);
Assert.assertEquals("Please select a visit Type", text);
driver.findElement(By.xpath("(//button[@type='button'])[72]")).click();
Thread.sleep(3000);


driver.findElement(By.xpath("(//button[@type='button'])[60]")).click();
driver.findElement(By.xpath("(//a[contains(@href, '#')])[89] ")).click();

driver.findElement(By.xpath("//*[@id='startTime']")).sendKeys("11:00");
Thread.sleep(3000);
driver.findElement(By.xpath("//div[2]/div/div/ul/li/a")).click();
//save button
driver.findElement(By.xpath("(//button[@type='button'])[68]")).click();
Thread.sleep(3000);

if(driver.findElement(By.xpath("html/body/div[7]/div/div/div[2]/div")).isDisplayed())
{
	System.out.println(driver.findElement(By.xpath("html/body/div[7]/div/div/div[2]/div")).getText());
	driver.findElement(By.xpath("html/body/div[7]/div/div/div[3]/button[2]")).click();
	Thread.sleep(2000);
	driver.findElement(By.xpath("(//button[@type='button'])[73]")).click();
}
  }
}
