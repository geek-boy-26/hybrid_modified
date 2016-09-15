package com.qtpselenium.test;
import static com.qtpselenium.test.DriverScript.APP_LOGS;
import static com.qtpselenium.test.DriverScript.CONFIG;
import static com.qtpselenium.test.DriverScript.OR;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.NoSuchElementException;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import com.google.common.base.Function;
import com.qtpselenium.xls.read.Xls_Reader;
import com.thoughtworks.selenium.Wait;

import static com.qtpselenium.test.DriverScript.currentTestSuiteXLS;
import static com.qtpselenium.test.DriverScript.currentTestCaseName;
import static com.qtpselenium.test.DriverScript.currentTestStepID;

//one function per keyword
public class keywords {

	public WebDriver driver;


	
	//data holding key value in excel
	//object column holding value of OR properties file
		public  String openBrowser(String object, String data)	{
		APP_LOGS.debug("Opening the browser");
		//Selenium Code
		try
		{
		 		if(data.equals("Mozilla"))
				driver = new FirefoxDriver();
				else if(data.equals("IE"))
					driver = new InternetExplorerDriver();
				else if(data.equals("Chrome"))
				System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir")+"//driver//chromedriver.exe");
				ChromeOptions chromeOptions = new ChromeOptions();
				chromeOptions.addArguments(Arrays.asList("--test-type"));
				DesiredCapabilities capabilities = DesiredCapabilities.chrome();
				capabilities.setCapability("chrome.switches", Arrays.asList("--incognito"));
				capabilities.setCapability(ChromeOptions.CAPABILITY, chromeOptions);
				driver = new ChromeDriver(capabilities);
					 
				driver.manage().window().maximize();
							
		}catch(Exception e)
		{
			return Constants.KEYWORD_FAIL+"--"+"Not able to open browser"+e.getMessage() ;
			
		}
		return Constants.KEYWORD_PASS;
		
	}
		public  String navigate(String object, String data){		
			try
			{
			APP_LOGS.debug("Navigating to URL");
			driver.navigate().to(data);  //simulate back and front button of browser
			}catch(Exception e)
			{
				return Constants.KEYWORD_FAIL+"--"+"Not able to navigate"+e.getMessage() ;
			}
			return Constants.KEYWORD_PASS;
		}
		
	
		
		
		public  String clickButton(String object, String data){
	       
	        try
	        {
	        	
	        	 APP_LOGS.debug("Clicking on Button");
	        	 long implicitWaitTime=Long.parseLong(CONFIG.getProperty("implicitwait"));
	     		driver.manage().timeouts().implicitlyWait(implicitWaitTime, TimeUnit.SECONDS);
	        	driver.findElement(By.xpath(OR.getProperty(object))).click(); 
	        	
	        	
	        }catch(Exception e)
	        {
	        	return Constants.KEYWORD_FAIL+"--"+"Not able to click button";
	        	
	        	
	        }
			
			return Constants.KEYWORD_PASS;
		}
		
		public  String verifyButtonText(String object, String data){
			APP_LOGS.debug("Verifying the button text");
			try
			{
			//	String data_flag = currentTestSuiteXLS.getCellData(currentTestCaseName, "Data_Correctness", currentTestStepID);
				
				String actual = driver.findElement(By.xpath(OR.getProperty(object))).getText();
				String expected = data;
						
					
				
				if(actual.equals(expected))
					return Constants.KEYWORD_PASS;
				else
					return Constants.KEYWORD_FAIL+"--"+actual+"::"+expected+"not matching";
				
			}catch(Exception e)
			
			{
	        	return Constants.KEYWORD_FAIL+"--"+"Not able to verify text"+e.getMessage() ;
			}
			
			
				
		}
		
		public  String selectList(){
			APP_LOGS.debug("Selecting from list");
			
			return Constants.KEYWORD_PASS;	
		}
		
		public  String verifyListSelection(){
			APP_LOGS.debug("Verifying the selection of the list");
			
			return Constants.KEYWORD_PASS;	
		}
		
		public  String verifyAllListElements(){
			APP_LOGS.debug("Verifying all the list elements");
			
			return Constants.KEYWORD_PASS;	

		}
		
		public  String selectRadio(){
			APP_LOGS.debug("Selecting a radio button");
			
			return Constants.KEYWORD_PASS;	

		}
		
		public  String verifyRadioSelected(String object, String data){
			APP_LOGS.debug("Verify Radio Selected");
			try
			{
				String checked=driver.findElement(By.xpath(OR.getProperty(object))).getAttribute("checked");
				//System.out.println(checked);
				if(checked==null)
				{
					return Constants.KEYWORD_FAIL+"- Radio not selected";	
				}
				
			}catch(Exception e)
			{
				return Constants.KEYWORD_FAIL +"- Not able to find radio button";	
			}
			
			
			
			return Constants.KEYWORD_PASS;	

		}
		
		public  String verifyCheckBoxSelected(){
			APP_LOGS.debug("Verifying checkbox selected");
			
			return Constants.KEYWORD_PASS;
			
		}
		
		
		public  String verifyText(String object, String data){
			APP_LOGS.debug("Verifying the text");
			try
			{

				String actual = driver.findElement(By.xpath(OR.getProperty(object))).getText();
				String expected =data;
				long implicitWaitTime=Long.parseLong(CONFIG.getProperty("implicitwait"));
				driver.manage().timeouts().implicitlyWait(implicitWaitTime, TimeUnit.SECONDS);
				
			
			
				
				if(actual.equals(expected))
					return Constants.KEYWORD_PASS;
				else
					return Constants.KEYWORD_FAIL+"--"+actual+"::"+expected+"not matching";
				
			}catch(Exception e)
			{
	        	return Constants.KEYWORD_FAIL+"--"+"Not able to verify text"+ ":" +e.getMessage() ;
			}
			
			
		}
		
		public  String writeInInput(String object, String data){
			APP_LOGS.debug("Writing in text box");
			
			try
			{
				driver.findElement(By.xpath(OR.getProperty(object))).sendKeys(data);
				
			}catch(Exception e)
			{
				return Constants.KEYWORD_FAIL+e.getMessage();
			}
			return Constants.KEYWORD_PASS;
			
		}
		
		public  String verifyTextinInput(String object, String data){
	       APP_LOGS.debug("Verifying the text in input box");
	       try
			{
				String actual = driver.findElement(By.xpath(OR.getProperty(object))).getAttribute("value");
				String expected =data;
				if(actual.equals(expected))
				{
					return Constants.KEYWORD_PASS;
				}
				else
				{
					return Constants.KEYWORD_FAIL+"Not Matching";
				}
				
				
			}catch(Exception e)
			{
				return Constants.KEYWORD_FAIL+"Unable to find element"+e.getMessage();
			}
			
			
		}
		
		public  String clickImage(){
		       APP_LOGS.debug("Clicking the image");
				
				return Constants.KEYWORD_PASS;
		}
		
		public  String verifyFileName(){
		       APP_LOGS.debug("Verifying inage filename");
				
				return Constants.KEYWORD_PASS;
		}
		
		public  String pause(String object, String data) throws InterruptedException{
		       APP_LOGS.debug("Waiting");
		       long time = (long)Double.parseDouble(object);
				Thread.sleep(time*1000L);
				return Constants.KEYWORD_PASS;
		}
		
		
		public  String store(){
		       APP_LOGS.debug("Storing value");
				
				return Constants.KEYWORD_PASS;
		}
		
		public  String verifyTitle(){
		       APP_LOGS.debug("Verifying title");
				
				return Constants.KEYWORD_PASS;
		}
		
		public  String exist(){
		       APP_LOGS.debug("Checking existance of element");
				
				return Constants.KEYWORD_PASS;
		}
		
		public  String click_and_clear(String object,String data){
		       APP_LOGS.debug("Clicking on any element");
				try{
					
					driver.findElement(By.xpath(OR.getProperty(object))).clear();
					
				}
				catch(Exception e)
				{
					return Constants.KEYWORD_FAIL+"Unable to click" + e.getMessage();
				}
				return Constants.KEYWORD_PASS;
		}
		
		public  String click_and_clear1(String object,String data){
		       APP_LOGS.debug("Clicking on any element");
				try{
					
					driver.findElement(By.xpath(OR.getProperty(object))).clear();
					
				}
				catch(Exception e)
				{
					return Constants.KEYWORD_FAIL+"Unable to click" + e.getMessage();
				}
				return Constants.KEYWORD_PASS;
		}
		
		public  String synchronize(){
			APP_LOGS.debug("Waiting for page to load");
			
			return Constants.KEYWORD_PASS;
		}
		
		public  String waitForElementVisibility(String object, String data){
			APP_LOGS.debug("Waiting for an element to be visible");
			int start=0;
			int time=(int)Double.parseDouble(data);
			try{
			 while(time == start){
				if(driver.findElements(By.xpath(OR.getProperty(object))).size() == 0){
						Thread.sleep(1000L);
					start++;
				}else{
					break;
				}
			 }
			}catch(Exception e){
				return Constants.KEYWORD_FAIL+"Unable to close browser. Check if its open"+e.getMessage();
			}
			
			return Constants.KEYWORD_PASS;
		}
		
		public  String closeBroswer(String object, String data){
			APP_LOGS.debug("Closing the browser");
			try
			{
				driver.quit();
			}
			catch(Exception e)
			{
				return Constants.KEYWORD_FAIL+"Unable to close browser. Check if its open"+e.getMessage();
			}
						
			return Constants.KEYWORD_PASS;

		}
		
		
		/************************APPLICATION SPECIFIC KEYWORDS********************************/
		
		public  String verifyLaptops(){
	        APP_LOGS.debug("Verifying the laptops in app");
			
			return Constants.KEYWORD_PASS;
		}
		
		// not used 
		public String ValidateLogin(String data, String object)
		{
			//object of current test XLS
			// name of my current test case 
			
			APP_LOGS.debug("Validating Login");
		
			try
			{
				if(driver.findElement(By.id(object)).isDisplayed()== true)
				{
					String actual =		driver.findElement(By.id(object)).getText();
					System.out.println(data+"::"+"here is it "+"::"+actual);
				}
				else
				{
					return Constants.KEYWORD_PASS;
				}
					
			}
			catch(Exception e)
			{
				return Constants.KEYWORD_FAIL+e.getMessage();
			}
			return Constants.KEYWORD_PASS;
		}
		
		public void captureScreenshot(String filename, String keyword_execution_result) throws IOException{
			// take screen shots
			if(CONFIG.getProperty("screenshot_everystep").equals("Y")){
				// capturescreen
				
				File scrFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
			    FileUtils.copyFile(scrFile, new File(System.getProperty("user.dir") +"//screenshots//"+filename+".jpg"));
				
			}else if (keyword_execution_result.startsWith(Constants.KEYWORD_FAIL) && CONFIG.getProperty("screenshot_error").equals("Y") ){
			// capture screenshot
				File scrFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
			    FileUtils.copyFile(scrFile, new File(System.getProperty("user.dir") +"//screenshots//"+filename+".jpg"));
			}
		}
		
public String read_popup(String object, String data)
{
	try
	{
		APP_LOGS.debug("Reading popup of "+":"+object);
		String pop_up = driver.findElement(By.xpath(OR.getProperty("Contact_lens_popup"))).getText();
		APP_LOGS.debug("Pop up"+":"+pop_up);
	}
	catch(Exception e)
	{
		return Constants.KEYWORD_FAIL+e.getMessage();
	}
	return Constants.KEYWORD_PASS;
}
/**---------------delete code from here to--------------------------------------**/


/**------------here -------**/
	public String double_click(String object, String data)
	{
		APP_LOGS.debug("Double Click on Object");
		try
		{
			Actions act = new Actions(driver);
			act.doubleClick(driver.findElement(By.xpath(OR.getProperty(object)))).build().perform();
			
		}
		catch(Exception e)
		{
			return Constants.KEYWORD_FAIL+e.getMessage();
		}
		
		return Constants.KEYWORD_PASS;
	}
	
	public String rightclick_appointment(String object, String data)
	{
		APP_LOGS.debug("Right click Action");
		try
		{
			Actions action = new Actions(driver);
			WebElement element = driver.findElement(By.xpath(OR.getProperty(object)));
			action.click();
			Thread.sleep(5000);
			action.contextClick(element).sendKeys(Keys.ARROW_DOWN).sendKeys(Keys.ENTER).perform();
			
			
		}catch(Exception e)
		{
			return Constants.KEYWORD_FAIL+"Unable to do right click"+e.getMessage();
		}
		return Constants.KEYWORD_PASS;
	}
	
	public String verify_alert(String object,String data)
	{
		APP_LOGS.debug("Verfiying alert box title");
		try
		{
			String text = driver.findElement(By.xpath(OR.getProperty(object))).getText();
			Assert.assertEquals(data, text);
		}
		catch(Exception e)
		{
			return Constants.KEYWORD_FAIL+"Unable to verify"+":"+"  "+e.getMessage();
		}
		
		return Constants.KEYWORD_PASS;
	}
	
	
	public String visit_type_selection(String object, String data)
	{
		APP_LOGS.debug("Selecting visit type");
		try
		{
			driver.findElement(By.xpath(OR.getProperty(object))).click();
			
		}
		catch(Exception e)
		{
			return Constants.KEYWORD_FAIL+":"+"Unable to select visit type" +"  "+ e.getMessage();
		}
		return Constants.KEYWORD_PASS;
	}
	
	public String check_exisiting_appointment(String object, String data)
	{
		try
		{
			APP_LOGS.debug("Alert box checking");
			
			if(driver.findElement(By.xpath(OR.getProperty(object))).isDisplayed()==true)
			{
				APP_LOGS.debug("Alert box Found");
				Thread.sleep(3000);
				clickButton(object, data);
				Thread.sleep(2000);
				clickButton(object, data);
			}
			else 
			{
				APP_LOGS.debug("No Alert box found");
				return Constants.KEYWORD_SKIP;
			}

		}
		catch(Exception e)
		{
			return Constants.KEYWORD_SKIP;
		}
		return Constants.KEYWORD_PASS;
	}
	
	public String Right_Click_for_Clinical_Notes(String object, String data)
	{
		APP_LOGS.debug("Clicking Right Click");
		try
		{
			
			  Actions action = new Actions(driver); 
			  WebElement element = driver.findElement(By.xpath(OR.getProperty(object)));  // //*[@id='slotContainer']/table/tbody/tr[17]/td/div
			//action.moveToElement(driver.findElement(By.xpath("//*[@id='slotContainer']/table/tbody/tr[12]/td"))).doubleClick().build().perform();
			Thread.sleep(2000);
			/**right click code**/
			action.click();
			action.contextClick(element).sendKeys(Keys.ARROW_DOWN).sendKeys(Keys.ARROW_DOWN).sendKeys(Keys.ARROW_DOWN).sendKeys(Keys.ARROW_DOWN).sendKeys(Keys.ENTER).perform();
			Thread.sleep(3000);
			
		}
		catch(Exception e)
		{
			return Constants.KEYWORD_FAIL+"Unable to perform Right Click"+ "  " + e.getMessage();
					
		}
		return Constants.KEYWORD_PASS;
	}
	
	public String Fill_rx_data(String object, String data)
	{
		String temp = object; 
		String[] str = temp.split("\\|");
		
		
		APP_LOGS.debug("Adding data in Rx");
		try
		{
			double i[]={1.75,1.2,88,-1.5,-2.5,94,11,8,6,5,7,8,3,4,5,6,10.5,11.75,8.8,7.7,9.6,6.4,49,58};
			for(int j=0; j<24; j++)
				{
					object = str[0];
					Thread.sleep(1000);
					driver.findElement(By.xpath(OR.getProperty(object))).sendKeys(""+i[j]); // input text for value
					Thread.sleep(2000);
					object = str[1];
					//driver.findElement(By.xpath(OR.getProperty(object))).click();  //click OK after entering the value
					clickButton(object, data);
				}	
			
			
		}
		catch(Exception e)
		{
			return Constants.KEYWORD_FAIL+"Unable to add data" + "--" + e.getMessage();
		}
		return Constants.KEYWORD_PASS;
				
	}
	
	
	public String Fill_Auto_Refractor_data(String object, String data)
	{
		String temp = object; 
		String[] str = temp.split("\\|");
		APP_LOGS.debug("Adding data in Auto Refractor");
		try
		{
			double i[]={17,12,108,20,15,94,65};
			for(int j=0; j<7; j++)
				{
					object = str[0];
					Thread.sleep(1000);
					driver.findElement(By.xpath(OR.getProperty(object))).sendKeys(""+i[j]); // input text for value
					Thread.sleep(2000);
					object = str[1];
					//driver.findElement(By.xpath(OR.getProperty(object))).click();  //click OK after entering the value
					clickButton(object, data);
				}	
			
			
		}
		catch(Exception e)
		{
			return Constants.KEYWORD_FAIL+"Unable to add data" + "--" + e.getMessage();
		}
		return Constants.KEYWORD_PASS;
				
	}
	
	public String Auto_k(String object,String data)
	{
		APP_LOGS.debug("Entering Auto K values");
		try
		{
			String temp = object; 
			String[] str = temp.split("\\|");
			double autok[] = {9,68.4,7.2,39.4,7.2,8.6};
			for(int j=0; j<6; j++)
			{
				object = str[0];
				driver.findElement(By.xpath(OR.getProperty(object))).sendKeys(""+autok[j]);
				Thread.sleep(1000);
				object = str[1];
				clickButton(object, data);
			}
			
			
		}
		catch(Exception e)
		{
			
			return Constants.KEYWORD_FAIL+":"+"Unable to add values"+e.getMessage();
		}
		
		return Constants.KEYWORD_PASS;
	}
	
	public String IOP_values(String object,String data)
	{
		APP_LOGS.debug("Entering IOP_values ");
		try
		{
			String temp = object; 
			String[] str = temp.split("\\|");
			double autok[] = {23,17,46,32,18,45,69,85};
			for(int j=0; j<8; j++)
			{
				object = str[0];
				driver.findElement(By.xpath(OR.getProperty(object))).sendKeys(""+autok[j]);
				Thread.sleep(1000);
				object = str[1];
				clickButton(object, data);
			}
			
			
		}
		catch(Exception e)
		{
			
			return Constants.KEYWORD_FAIL+":"+"Unable to add values"+e.getMessage();
		}
		
		return Constants.KEYWORD_PASS;
	}
	
	public String scroll_down(String object,String data)
	{
		APP_LOGS.debug("scroll down to element");
		try
		{
			
			JavascriptExecutor je = (JavascriptExecutor)driver;
			WebElement element=driver.findElement(By.xpath(OR.getProperty(object)));  //scrolls down the page till history and consult
			je.executeScript("arguments[0].scrollIntoView(true);",element);
			
			
		}
		catch(Exception e)
		{
			return Constants.KEYWORD_FAIL+"Unable to find Element"+":"+"  "+e.getMessage();
		}
		
		return Constants.KEYWORD_PASS;
	}
	
		public String cover_test_without_rx(String object,String data)
	{
		APP_LOGS.debug("Enteringcover_test_without_rx values ");
		try
		{
			String temp = object; 
			String[] str = temp.split("\\|");
			double autok[] = {14.25,30.33,18.74,47};
			for(int j=0; j<4; j++)
			{
				object = str[0];
				driver.findElement(By.xpath(OR.getProperty(object))).clear();
				driver.findElement(By.xpath(OR.getProperty(object))).sendKeys(""+autok[j]);
				Thread.sleep(1000);
				object = str[1];
				clickButton(object, data);
			}
			
			
		}
		catch(Exception e)
		{
			
			return Constants.KEYWORD_FAIL+":"+"Unable to add values"+e.getMessage();
		}
		
		return Constants.KEYWORD_PASS;
	}
	
		
		public String cover_test_with_rx(String object,String data)
		{
			APP_LOGS.debug("Enteringcover_test_with_rx values ");
			try
			{
				String temp = object; 
				String[] str = temp.split("\\|");
				double autok[] = {14.25,30.33,18.74,47};
				for(int j=0; j<4; j++)
				{
					object = str[0];
					driver.findElement(By.xpath(OR.getProperty(object))).clear();
					driver.findElement(By.xpath(OR.getProperty(object))).sendKeys(""+autok[j]);
					Thread.sleep(1000);
					object = str[1];
					clickButton(object, data);
				}
					
			}
			catch(Exception e)
			{
				
				return Constants.KEYWORD_FAIL+":"+"Unable to add values"+e.getMessage();
			}
			
			return Constants.KEYWORD_PASS;
		}
		
		public String Refracted_values(String object,String data)
		{
			APP_LOGS.debug("Entering Refracted values ");
		try
		{
			String temp = object; 
			String[] str = temp.split("\\|");
			double autok[] = {1.19,2.79,56,-2.8,-3.95,111,6,8,11,5,7,8,3,4,5,6,10.5,11.75,8.8,7.7,4.4,6.78};
			for(int j=0; j<22; j++)
			{
				object = str[0];
				driver.findElement(By.xpath(OR.getProperty(object))).clear();
				driver.findElement(By.xpath(OR.getProperty(object))).sendKeys(""+autok[j]);
				Thread.sleep(1000);
				object = str[1];
				clickButton(object, data);
			}
			
			
		}
		catch(Exception e)
		{
			
			return Constants.KEYWORD_FAIL+":"+"Unable to add values"+e.getMessage();
		}
		
			return Constants.KEYWORD_PASS;
		}
	
		public String cover_distance_values(String object,String data)
		{
			APP_LOGS.debug("Entering cover distance values ");
		try
		{
			String temp = object; 
			String[] str = temp.split("\\|");
			double autok[] = {22.5,31.08,15.92,46.21};
			for(int j=0; j<4; j++)
			{
				object = str[0];
				driver.findElement(By.xpath(OR.getProperty(object))).clear();
				driver.findElement(By.xpath(OR.getProperty(object))).sendKeys(""+autok[j]);
				Thread.sleep(1000);
				object = str[1];
				clickButton(object, data);
			}
			
			
		}
		catch(Exception e)
		{
			
			return Constants.KEYWORD_FAIL+":"+"Unable to add values"+e.getMessage();
		}
		
			return Constants.KEYWORD_PASS;
		}
	
		public String phoria_distance_values(String object,String data)
		{
			APP_LOGS.debug("Entering phoria distance values ");
		try
		{
			String temp = object; 
			String[] str = temp.split("\\|");
			double autok[] = {22.5,31.08,15.92,46.21};
			for(int j=0; j<4; j++)
			{
				object = str[0];
				driver.findElement(By.xpath(OR.getProperty(object))).clear();
				driver.findElement(By.xpath(OR.getProperty(object))).sendKeys(""+autok[j]);
				Thread.sleep(1000);
				object = str[1];
				clickButton(object, data);
			}
			
			
		}
		catch(Exception e)
		{
			
			return Constants.KEYWORD_FAIL+":"+"Unable to add values"+e.getMessage();
		}
		
			return Constants.KEYWORD_PASS;
		}
	
		public String cover_test_near_values(String object,String data)
		{
			APP_LOGS.debug("Entering cover test near values ");
		try
		{
			String temp = object; 
			String[] str = temp.split("\\|");
			double autok[] = {22.5,31.08,15.92,46.21};
			for(int j=0; j<4; j++)
			{
				object = str[0];
				driver.findElement(By.xpath(OR.getProperty(object))).clear();
				driver.findElement(By.xpath(OR.getProperty(object))).sendKeys(""+autok[j]);
				Thread.sleep(1000);
				object = str[1];
				clickButton(object, data);
			}
			
			
		}
		catch(Exception e)
		{
			
			return Constants.KEYWORD_FAIL+":"+"Unable to add values"+e.getMessage();
		}
		
			return Constants.KEYWORD_PASS;
		}
	
		
		public String waitForElement(String object,String data)
		{
			APP_LOGS.debug("Waiting for Element, explicit wait ");
			try
			{
				
				WebDriverWait wait = new WebDriverWait(driver,25);
				wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(OR.getProperty(object))));
				
				}
			catch(Exception e)
			{
			  return Constants.KEYWORD_FAIL+":"+"Unable to wait";	
			}
			return Constants.KEYWORD_PASS;
		}
		
		
		public String FluentWait(final String object,String data)
		{
			try
			{
			org.openqa.selenium.support.ui.FluentWait<WebDriver> wait = new org.openqa.selenium.support.ui.FluentWait<WebDriver>(driver).withTimeout(30, TimeUnit.SECONDS).pollingEvery(5, TimeUnit.SECONDS).ignoring(NoSuchElementException.class);
			
			WebElement element= wait.until(new Function<WebDriver, WebElement>() {
				public WebElement apply(WebDriver driver)
				{
					return driver.findElement(By.xpath(OR.getProperty(object)));
				}
			});
						
			}
			catch(Exception e)
			{
				return Constants.KEYWORD_FAIL+"unable to wait"+e.getMessage();
			}
			
			return Constants.KEYWORD_PASS;
		}
		
		public  String clickButton1(String object, String data){
		       
	        try
	        {
	        	
	        	 APP_LOGS.debug("Clicking on Button");
	        	 long implicitWaitTime=Long.parseLong(CONFIG.getProperty("implicitwait"));
	     		driver.manage().timeouts().implicitlyWait(implicitWaitTime, TimeUnit.SECONDS);
	        	driver.findElement(By.xpath(OR.getProperty(object))).click(); 
	        	
	        	
	        }catch(Exception e)
	        {
	        	return Constants.KEYWORD_FAIL+"--"+"Not able to click button";
	        	
	        	
	        }
			
			return Constants.KEYWORD_PASS;
		}
		
		public String Check_popup(String object,String data)
		{
			APP_LOGS.debug("Check pop up present or not");
			try
			{
			if(driver.findElement(By.xpath(OR.getProperty(object))).isDisplayed())
			{
			
				driver.findElement(By.xpath(object)).click();
				
			}
			}
			catch(Exception e)
			{
				return Constants.KEYWORD_FAIL;
			}
	return Constants.KEYWORD_PASS;
		}
		
		public String Check_popup1(String object,String data)
		{
			APP_LOGS.debug("Check pop up present or not");
			try
			{
			if(driver.findElement(By.xpath(OR.getProperty(object))).isDisplayed())
			{
			
				driver.findElement(By.xpath(object)).click();
				
			}
			}
			catch(Exception e)
			{
				return Constants.KEYWORD_FAIL;
			}
	return Constants.KEYWORD_PASS;
		}
		
	}
