package views;

import GeneralSetup.DataManagement;
import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.WebDriverWait;

public class PowerwallView extends DataManagement {
    @FindBy(xpath = "(//a[@href='/powerwall/design'])[1]")
    private WebElement orderNowButton;
    @FindBy(xpath = "//*[@id=\"main-content\"]/div/div[4]/button")
    private WebElement newHomeLink;
    @FindBy(xpath = "//span[contains(text(),'Home Address')]")
    private WebElement homeAddressText;
    @FindBy(xpath = "//input[@id='homeSqFt']")
    private WebElement homeSquareFootage;

    public PowerwallView(AppiumDriver<WebElement> appiumDriver, WebDriverWait wait) {
        super(appiumDriver, wait);
    }

    @Override
    public void getDirectPage() {
        appiumDriver.get("https://www.tesla.com/" + urls.get(getClass().getSimpleName()));
    }

    public void orderNow() {
        orderNowButton.click();
    }

    public void clickNewHomeLink() {
        newHomeLink.click();
    }

    public void fillFootage() {
        waitUtils.webElementExplicitWait(homeAddressText);
        scrollMethod(homeAddressText);
        homeSquareFootage.sendKeys("123");
    }
}
