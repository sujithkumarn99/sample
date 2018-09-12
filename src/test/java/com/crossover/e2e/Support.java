package test.java.com.crossover.e2e;

import java.io.File;
import java.io.IOException;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Support extends GMailTest {	
	public static void screenShot() throws IOException {
		File file = ((TakesScreenshot)getDriver()).getScreenshotAs(OutputType.FILE);
		FileUtils.copyFile(file, new File("Screenshot\\sample.jpeg"));
	}	
	public static void waitForElement(int mints, WebElement element) {
		WebDriverWait wait = new WebDriverWait(getDriver(), mints);
		wait.until(ExpectedConditions.visibilityOf(element));
	}
	public static void highLightError(WebElement element) {
		JavascriptExecutor js = ((JavascriptExecutor)getDriver());
		js.executeScript("arguments[0].style.border='3Px dashed red';", element);
	}
	public static void moveToElement(WebElement element) {
		Actions actions = new Actions(getDriver());
		actions.moveToElement(element).build().perform();
	}	
}