package GeneralSetup;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.remote.MobileCapabilityType;
import io.appium.java_client.service.local.AppiumDriverLocalService;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import utils.Browser;
import views.HomeView;
import views.PowerwallView;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;

public class TestBasisMobile {
    public static AppiumDriver<WebElement> appiumDriver;
    public static WebDriverWait wait;

    public DesiredCapabilities capabilities = new DesiredCapabilities();
    public URL serverAddress;
    public String browserName = Browser.getRequiredBrowser().getBrowserName();
    private static final AppiumDriverLocalService appiumDriverLocalService = AppiumDriverLocalService.buildDefaultService();

    public HomeView homeView;
    public PowerwallView powerwallView;

    @BeforeClass
    public void beforeClass() throws MalformedURLException {
//выбор браузера
        capabilities.setCapability(MobileCapabilityType.BROWSER_NAME, browserName);
        capabilities.setCapability(MobileCapabilityType.DEVICE_NAME, "Pixel 4 API 30");
        capabilities.setCapability(MobileCapabilityType.UDID, "emulator-5554");
        capabilities.setCapability(MobileCapabilityType.NEW_COMMAND_TIMEOUT, 1000);
        capabilities.setCapability(MobileCapabilityType.PLATFORM_NAME, "Android");
        capabilities.setCapability(MobileCapabilityType.AUTOMATION_NAME, "UiAutomator2");
// для автоматического запуска эмулятора
        capabilities.setCapability("avd", "Pixel_4_API_30");
        capabilities.setCapability("avdLaunchTimeout", 180000);  //3 minutes
// сколько сохранять активность сессии в дебаге
        capabilities.setCapability("newCommandTimeout", 300);  //5 minutes
        String driverPath = System.getProperty("user.dir") + File.separator + "src" + File.separator + "main"
                + File.separator + "java" + File.separator + "chromedriver";
        capabilities.setCapability("chromedriverExecutableDir", driverPath);
        capabilities.setCapability("unlockType", "pin");
        capabilities.setCapability("unlockKey", "0000");

        serverAddress = new URL("http://127.0.0.1:4723/wd/hub");
        appiumDriverLocalService.start();
        initializeDriver();
    }

    @AfterClass
    public void actionsAfterClass() {
        appiumDriver.quit();
        appiumDriverLocalService.stop();
    }

    private void initializeDriver() {
        appiumDriver = new AndroidDriver<>(serverAddress, capabilities);
        wait = new WebDriverWait(appiumDriver, 10);
        initializeClasses();
    }

    private void initializeClasses() {
        homeView = new HomeView(appiumDriver, wait);
        powerwallView = new PowerwallView(appiumDriver, wait);
    }
}