package ActiTime;

import java.time.Duration;


import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;

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

public class Case2 {

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

		// Test CASE 2

		createCustomerCopyAllFromGalaxy(driver, wait, "Test Case 2", "Don't Click Select Customerand ");

		System.out.println("\n=== Automation Completed (Case 2) ===");
		driver.quit();
	}

	private static void createCustomerCopyAllFromGalaxy(WebDriver driver, WebDriverWait wait, String customerName,
			String description) throws InterruptedException {

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

        // Create Customer using click() method only

		WebElement createBtn = wait
				.until(ExpectedConditions.elementToBeClickable(By.xpath("//div[contains(text(),'Create Customer')]")));
		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", createBtn);
		createBtn.click();

		Thread.sleep(2000);
		System.out.println("Customer created → " + customerName);
	}
}
