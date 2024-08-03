package Fitpeo_Assessment;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;

public class Fitpeo {
		ChromeDriver driver;
		@BeforeMethod
		public void SetUp() {
			WebDriverManager.chromedriver().setup();
			driver=new ChromeDriver();
			driver.manage().window().maximize();
			//step 1: Navigate to the FitPeo HomePage
			driver.navigate().to("https://www.fitpeo.com");
			
			
		}
		public  String total_reembusment= "$110700";
		@Test
		public void Revenue_Calculator() throws InterruptedException{
			//step 2: Navigate to the Revenue Calculator Page 
			WebElement revenuecalculator = driver.findElement(By.linkText("Revenue Calculator"));
			revenuecalculator.click();
			Thread.sleep(2000);
			
			//Step 3:
			
			WebElement Slider1  = driver.findElement(By.xpath("//*[@class=\"MuiTypography-root MuiTypography-body1 inter css-k0m0w\"]"));
			JavascriptExecutor js = (JavascriptExecutor) driver;
			js.executeScript("arguments[0].scrollIntoView();", Slider1);
			
			// Adjust the Slider to Set Value to 820
	        WebElement slider = driver.findElement(By.xpath("//span[contains(@class,'MuiSlider-thumb')]"));
	        Actions actions = new Actions(driver);
	        actions.clickAndHold(slider).moveByOffset(107, 0).release().perform();

	        // Validate the slider value
	        WebElement sliderValueElement = driver.findElement(By.xpath("//input[contains(@class,'MuiInputBase-inputSizeSmall')]"));
	        String sliderValue = sliderValueElement.getAttribute("value");
	        Assert.assertEquals(sliderValue, "820", "Slider value is not updated to 820");

	        // Update the Text Field to 560 and verify the slider position
	        sliderValueElement.clear();
	        sliderValueElement.sendKeys("560");
	        Thread.sleep(3000);
	        String updatedSliderValue = sliderValueElement.getAttribute("value");
	        Assert.assertEquals(updatedSliderValue, "560", "Slider value is not updated to 560");


			
//			//step 4:
//			WebElement Slider  = driver.findElement(By.xpath("//*[contains(@class,\"MuiSlider-thumb MuiSlider-thumbSizeMedium MuiSlider-thumbColorPrimary MuiSlider-thumb MuiSlider-thumbSizeMedium MuiSlider-thumbColorPrimary css-sy3s50\")]"));
//			Slider.isDisplayed();
//			js.executeScript("arguments[0].setAttribute('style', 'left: 41.01%;')",Slider);
//			
//			Thread.sleep(3000);
//			Slider.click();
//			String get_slider_value=Slider.getAttribute("value");
//			Thread.sleep(5000);
//			Thread.sleep(3000);
			//step 7
			int temp=0;

			int count = driver.findElements(By.xpath("//*[@class=\"MuiTypography-root MuiTypography-body1 inter css-1s3unkt\"]")).size();
			for(int i=1;i<=count;i++) {
				String box =driver.findElement(By.xpath("(//*[@class=\"MuiTypography-root MuiTypography-body1 inter css-1s3unkt\"])["+i+"]")).getText();
				if(box.contains("CPT-99473")||box.contains("CPT-99453")||box.contains("CPT-99454")||box.contains("CPT-99474")) {
					driver.findElement(By.xpath("(//*[@class=\"MuiBox-root css-4o8pys\"])["+i+"]//label//input")).click();
					temp++;
					System.out.println(temp);
				}
			}
			//step 8 & 9
			String total_Reimbursement =driver.findElement(By.xpath("//*[@class=\"MuiTypography-root MuiTypography-body1 inter css-hocx5c\"]")).getText();

			Assert.assertEquals(total_Reimbursement, "total_reembusment","total_Reimbursement is not as expected amount");
			
}
}
