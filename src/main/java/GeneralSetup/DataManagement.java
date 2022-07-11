package GeneralSetup;

import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;

public abstract class DataManagement extends GeneralView {
    public DataManagement(AppiumDriver<WebElement> appiumDriver, WebDriverWait wait) {
        super(appiumDriver, wait);
    }
}
