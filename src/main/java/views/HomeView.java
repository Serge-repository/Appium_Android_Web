package views;

import GeneralSetup.DataManagement;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.FindsByAndroidUIAutomator;
import io.appium.java_client.MobileBy;
import io.appium.java_client.MobileElement;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

public class HomeView extends DataManagement {

    @FindBy(xpath = "//span[@class='tds-site-nav-item-text']")
    private WebElement menuButton;
    @FindBy(xpath = "//a[@title='Powerwall']")
    private WebElement powerwall;

    public HomeView(AppiumDriver<WebElement> appiumDriver, WebDriverWait wait) {
        super(appiumDriver, wait);
    }

    @Override
    public void getDirectPage() {
        appiumDriver.get("https://www.tesla.com/" + urls.get(getClass().getSimpleName()));
    }

    public void differentSelectorsStrategies() {
        System.out.println(appiumDriver.findElementByAccessibilityId("Accessibility").getText());

        System.out.println(appiumDriver.findElementById("android:id/text1").getText());

        System.out.println(appiumDriver.findElementByClassName("android.widget.TextView").getText());

        System.out.println(appiumDriver.findElementByXPath("//android.widget.TextView[@content-desc=\"Accessibility\"]").getText());

        System.out.println(appiumDriver.findElementsById("android:id/text1").get(2).getText());
    }

    public void selectorsUsingUiAutomator2() {
        // see selector methods for UI automator 2 here
        // https://developer.android.com/reference/androidx/test/uiautomator/UiSelector
        MobileElement oneUiAutomatorElement = (MobileElement) ((FindsByAndroidUIAutomator) appiumDriver).findElementByAndroidUIAutomator("new UiSelector().description(\"Accessibility\")");
        System.out.println(oneUiAutomatorElement); // content-desc in Appium Inspector

        List<MobileElement> elementsByUiAutomator = (List<MobileElement>) ((FindsByAndroidUIAutomator) appiumDriver).findElementsByAndroidUIAutomator("new UiSelector().className(\"android.widget.TextView\")");
        System.out.println(elementsByUiAutomator.get(3).getText());

        MobileElement elementsByUiAutomator2 = (MobileElement) ((FindsByAndroidUIAutomator) appiumDriver).findElementsByAndroidUIAutomator("new UiSelector().className(\"android.widget.TextView\")").get(3);
        System.out.println(elementsByUiAutomator2.getText());
    }

    public void byExampleForHybridApp() {
        By hybridAppBy = MobileBy.AccessibilityId("Accessibility");
        System.out.println(appiumDriver.findElement(hybridAppBy).getText());
    }

    public void chooseModelX() {
        menuButton.click();
        powerwall.click();
    }
}
