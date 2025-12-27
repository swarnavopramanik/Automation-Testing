package ActiTime;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;
import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

@Test

public class Case3 {

	public static void loginTest() throws InterruptedException {

		WebDriver driver = new ChromeDriver();
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

		driver.get("https://online.actitime.com/spramanik1/login.do");

		driver.findElement(By.id("username")).sendKeys("swarnavo");
		driver.findElement(By.name("pwd")).sendKeys("Swar1234");
		driver.findElement(By.id("loginButton")).click();

		Thread.sleep(3000);

		driver.findElement(
				By.xpath("//span[@class='components-TruncatedText-oneLine--k4DPsC7f'][normalize-space()='Tasks']"))
				.click();

		// Test CASE 3

		createCustomer(driver, wait, "Test Case 3",
				"Click Select Customerand & select anyone then Uncheck all checkbox then select random 2 checkbox");

		System.out.println("\n=== Automation Completed (Case 3) ===");
		driver.quit();
	}

	private static void createCustomer(WebDriver driver, WebDriverWait wait, String customerName, String description)
			throws InterruptedException {

		// open New Customer Pop UP
		wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("div.addNewButton"))).click();
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//div[text()='New Customer']"))).click();

		// enter name
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("input.inputNameField.newNameField")))
				.sendKeys(customerName);

		// enter description
		wait.until(ExpectedConditions
				.visibilityOfElementLocated(By.xpath("//textarea[@placeholder='Enter Customer Description']")))
				.sendKeys(description);

		((JavascriptExecutor) driver).executeScript("window.scrollBy(0, document.body.scrollHeight)");

		// click Drop Down
		try {
			driver.findElement(By.xpath("//div[@class='emptySelection']")).click();
		} catch (NoSuchElementException e) {
			driver.findElement(By.xpath("//div[@class='itemRow selected']")).click();
		}

		// select Customer

		wait.until(ExpectedConditions.elementToBeClickable(
				By.xpath("//div[contains(@class,'itemRow cpItemRow')][normalize-space()='Big Bang Company']"))).click();

		Thread.sleep(2000);

		JavascriptExecutor js = (JavascriptExecutor) driver;

		// UNSELECT ALL CHECKBOXES

		List<WebElement> checkboxes = driver
				.findElements(By.xpath("//div[@class='customerImportSettingsDiv']//div[@class='settingsPlaceholder']"));

		for (WebElement cb : checkboxes) {
			if (cb.isSelected()) {

				// use click OR js click
				// cb.click();

				js.executeScript("arguments[0].click();", cb);
			}
		}

		// SELECT ANY RANDOM 2 CHECKBOXES
		// ** It will randomly selected this check box **
		// ** Assigned Users & Project Description verify using manual testing **

		checkboxes = driver
				.findElements(By.xpath("//div[@class='customerImportSettingsDiv']//div[@class='settingsPlaceholder']"));

		java.util.Collections.shuffle(checkboxes); // randomize order

		int selectedCount = 0;
		for (WebElement cb : checkboxes) {
			if (!cb.isSelected()) {
				js.executeScript("arguments[0].click();", cb); // or cb.click();
				selectedCount++;
				if (selectedCount == 2) {
					break; // select only 2 then break the loop
				}
			}
		}

		// click Create Customer using click() method only

		WebElement createBtn = wait
				.until(ExpectedConditions.elementToBeClickable(By.xpath("//div[contains(text(),'Create Customer')]")));
		js.executeScript("arguments[0].scrollIntoView(true);", createBtn);
		createBtn.click();

		Thread.sleep(2000);
		System.out.println("Customer created → " + customerName);
	}

}
