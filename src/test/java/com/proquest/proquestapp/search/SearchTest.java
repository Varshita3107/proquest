package com.proquest.proquestapp.search;

import java.io.BufferedWriter;
import java.io.File;
import org.apache.commons.io.FileUtils;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import cucumber.api.java.en.*;
import io.github.bonigarcia.wdm.WebDriverManager;

//import junit.framework.Assert;

public class SearchTest{
	
	public static WebDriver varDriver;
	
	@Given("^that User enter the \"([^\"]*)\" into the chrome browser and is on the Google page$")
	public void that_User_enter_the_into_the_chrome_browser_and_is_on_the_Google_page(String arg1) throws Throwable {
		// Write code here that turns the phrase above into concrete actions
		
		//Creating a file on C drive
        File f = new File("C:\\Temp\\SearchResults.txt");       // Only required if you do not have existing file   
        f.createNewFile(); 
		
        //Launching Google and perform search
        WebDriverManager.chromedriver().setup();
        varDriver = new ChromeDriver();
        varDriver.manage().window().maximize();
		Thread.sleep(3000);
		 	 
		//Redirect to your Google page
		varDriver.get("http://"+arg1+"/");
		 		
		 		              
	}
	
	@When("^the search phrase \"([^\"]*)\" is entered$")
	public void the_search_phrase_is_entered(String arg1) throws Throwable {
	    // Write code here that turns the phrase above into concrete actions
		
		//Search ProQuest
 		WebElement element = varDriver.findElement(By.name("q"));
 		//Enter ProQuest to search
 		element.sendKeys(arg1);
 		// Now submit the form. WebDriver will find the form from the element
 		element.submit();  
		
	}
	
	@Then("^write titles of all results on the first page to a text file$")
	public void write_titles_of_all_results_on_the_first_page_to_a_text_file() throws Throwable {
	    // Write code here that turns the phrase above into concrete actions
		
		//Get the list of Links from Search results and write to a File
	    List<WebElement> titleResult = varDriver.findElements(By.cssSelector("h3.LC20lb.DKV0Md"));
	    FileWriter w = new FileWriter("C:\\Temp\\SearchResults.txt");
   
	    for(int i=0; i< titleResult.size(); i++) {
	    	String text = titleResult.get(i).getText();	        
	    	System.out.println(text);
    	    
	    	BufferedWriter out = new BufferedWriter(w);
	    	out.write(text);
	    	out.newLine();	
	      	out.flush(); 	        	
	    	}
	    // w.close();
	    //throw new PendingException();
	}
	
	@Given("^that User clicked the Proquest website link from the results$")
	public void that_User_clicked_the_Proquest_website_link_from_the_results() throws Throwable {
	    // Write code here that turns the phrase above into concrete actions
		 		
 		//Click Proquest Website
	    String clickSearch = "//*[@id=\"rso\"]/div[1]/div/div/div/div/div[1]/a/h3";
	    //String clickSearch ="//*[@id=\"rso\"]/div[1]/div/div/table/tbody/tr[1]/td[1]/div/span/h3/a";
	    WebElement clickSearchlink= varDriver.findElement(By.xpath(clickSearch));
		Thread.sleep(3000);
		clickSearchlink.click();
		System.out.println("Clicked Successfully");
	    //throw new PendingException();
	}
	
	@When("^the search phrase \"([^\"]*)\" is entered in top nav$")
	public void the_search_phrase_is_entered_in_top_nav(String arg1) throws Throwable {
		
		//Handle cookie consent
		Thread.sleep(3000); 
			int size = varDriver.findElements(By.tagName("iframe")).size();
	        System.out.println("...."+size);
	        List<WebElement> frames = varDriver.findElements(By.tagName("iframe"));
	        for (WebElement webElement : frames) {
	        	System.out.println(webElement.getAttribute("id"));
	        	System.out.println(webElement.getAttribute("title"));
	        	}
	        varDriver.switchTo().frame(varDriver.findElement(By.cssSelector("iframe[title='TrustArc Cookie Consent Manager']")));
	        varDriver.findElement(By.cssSelector("a.call")).click();
	        Thread.sleep(1000);
	        varDriver.findElement(By.id("gwt-debug-close_id")).click();
		//Search QA in top nav	
	        varDriver.switchTo().defaultContent();
	        varDriver.findElement(By.xpath("/html/body/div[1]/nav[2]/div/div[2]/ul[1]/li[8]/a/i")).click();
			varDriver.findElement(By.xpath("//*[@id=\"search-form\"]/div/span[1]/input[2]")).sendKeys(arg1);
			varDriver.findElement(By.xpath("//*[@id=\"search-form\"]/div/span[2]/button/i")).click();
							    	
		
	}
	
	@Then("^take the screenshot$")
	public void take_the_screenshot() throws Throwable {
	    // Write code here that turns the phrase above into concrete actions
		
		//Call take screenshot function
			takeScreenShot(varDriver, "C:\\Temp\\snapshot.png");  
							   
		//Closes the current webdriver browser window
		varDriver.close();
		
	    //throw new PendingException();
	}
	
	//Function to take screenshot
		public static void takeScreenShot(WebDriver driver, String filePath) throws IOException {
	       
	        TakesScreenshot srcShot = ((TakesScreenshot) driver);
	        File srcFile=srcShot.getScreenshotAs(OutputType.FILE);
	        File targetFile = new File(filePath);
	        FileUtils.copyFile(srcFile, targetFile);
	      
	    }

	}
	    
	

