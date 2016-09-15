package com.qtpselenium.test;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.annotations.Test;


public class PreTest {
	public WebDriver driver;
  
	@Test
  public void Pretest() throws InterruptedException {
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
	  WebElement element = driver.findElement(By.xpath(".//*[@id='slotContainer']/table/tbody/tr[12]/td/div"));  // //*[@id='slotContainer']/table/tbody/tr[17]/td/div
	//action.moveToElement(driver.findElement(By.xpath("//*[@id='slotContainer']/table/tbody/tr[12]/td"))).doubleClick().build().perform();
	Thread.sleep(2000);
	/**right click code**/
	action.click();
	action.contextClick(element).sendKeys(Keys.ARROW_DOWN).sendKeys(Keys.ARROW_DOWN).sendKeys(Keys.ARROW_DOWN).sendKeys(Keys.ARROW_DOWN).sendKeys(Keys.ENTER).perform();
	System.out.println("performed");
	Thread.sleep(3000);
	
	driver.findElement(By.xpath("//*[@id='checkinoutdiv']/div/div/div[2]/div/div/div[1]/div[1]/div/button[2]")).click(); // Open dropdown
	Thread.sleep(3000);
	driver.findElement(By.xpath("//*[@id='checkinoutdiv']/div/div/div[2]/div/div/div[1]/div[1]/div/ul/li[1]/a")).click();  //Selects Pre Test as status from dropdown
	driver.findElement(By.xpath("//*[@id='checkinoutdiv']/div/div/div[4]/button[2]")).click();   //click on OK 
	Thread.sleep(3000);
	driver.findElement(By.xpath("//*[@id='progress_content']/div/div/h4[1]/a")).click(); // open pre test
	Thread.sleep(8000);
	driver.findElement(By.xpath("//div[@id='q0']/div/table/tbody/tr/td[2]")).click(); // opens existing rx1 for adding values
	driver.findElement(By.xpath("//*[@id='btn-more']")).click(); // clicks on more and expands the panel
	Thread.sleep(8000);
	
	/*if(driver.findElement(By.xpath("/html/body/div[6]/div/div/div[3]/button[1]")).isDisplayed())  //for changing appointment status to arrived
    {
    driver.findElement(By.xpath("/html/body/div[6]/div/div/div[3]/button[1]")).click();
    }*/ 

	
	
	
	double i[]={1.75,1.2,88,-1.5,-2.5,94,11,8,6,5,7,8,3,4,5,6,10.5,11.75,8.8,7.7,9.6,6.4,49,58};
	 for(int j=0; j<24; j++)
		{
		
		  driver.findElement(By.xpath("//*[@id='slidervalExs']")).sendKeys(""+i[j]); // input text for value
		  Thread.sleep(2000);
		driver.findElement(By.xpath("//*[@id='sliderokExs']")).click();  //click OK after entering the value
		}	
	
	 driver.findElement(By.xpath("//*[@id='existingEdit']/div[3]/button")).click();
	 Thread.sleep(3000);
	 driver.findElement(By.xpath("//*[@id='PreTestMain']/form/div[1]/div[1]/div/div[3]/div[2]/div[1]/div[3]/table/tbody/tr[1]/td[2]")).click();
	System.out.println(driver.findElement(By.xpath("//input[@id='radioMM']")).getAttribute("checked")+":"+"Checked"); 
	System.out.println(driver.findElement(By.xpath("//input[@id='radioDiopter']")).getAttribute("value")+":"+"UnChecked");
	
	//Auto K
	if(driver.findElement(By.xpath("//input[@id='radioMM']")).isSelected())
	{
	double autok[] = {9,68.4,7.2,39.4,7.2,8.6};
	for(int j=0; j<6; j++)
	{
		driver.findElement(By.xpath("//*[@id='slidervalAuto']")).sendKeys(""+autok[j]);
		Thread.sleep(1000);
		driver.findElement(By.xpath("//*[@id='sliderokAuto']")).click();
	}
	
	driver.findElement(By.xpath("//*[@id='autoKEdit']/div[1]/button")).click(); //clse the x button
	
	driver.findElement(By.xpath("//*[@id='PreTestMain']/div[1]/button")).click(); //clse the x button
	
	JavascriptExecutor je = (JavascriptExecutor)driver;
	WebElement element1=driver.findElement(By.xpath("//*[@id='progress_content']/div/div/h4[2]/a"));  //scrolls down the page till history and consult
	je.executeScript("arguments[0].scrollIntoView(true);",element1);
	}
	
		
	 
  }
	

}
