package GeneralSetup;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.nativekey.AndroidKey;
import io.appium.java_client.android.nativekey.KeyEvent;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;
import utils.ActionsWithDeviceAndApp;
import utils.TouchActionClass;
import utils.WaitUtils;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import static org.testng.Assert.assertTrue;

public abstract class GeneralView {
    public AppiumDriver<WebElement> appiumDriver;
    public WebDriverWait wait;

    public final WaitUtils waitUtils = new WaitUtils();
    public final TouchActionClass touchAction = new TouchActionClass();
    public final ActionsWithDeviceAndApp actionsWithDeviceAndApp = new ActionsWithDeviceAndApp();

    public final Map<String, String> urls = getUrlsMap();
    public Map<String, Map<String, String>> urlMaps;

    public GeneralView(AppiumDriver<WebElement> appiumDriver, WebDriverWait wait) {
        this.appiumDriver = appiumDriver;
        this.wait = wait;
        PageFactory.initElements(new AppiumFieldDecorator(this.appiumDriver), this);
    }

    public WaitUtils getWaitUtils() {
        return waitUtils;
    }

    public TouchActionClass getTouchAction() {
        return touchAction;
    }

    public ActionsWithDeviceAndApp getActionsWithDeviceAndApp() {
        return actionsWithDeviceAndApp;
    }

    public void isKeyboardShown(){
        assertTrue(((AndroidDriver) appiumDriver).isKeyboardShown(), "Keyboard is not shown");
    }

    public void enterKeyUsingKeyboard(AndroidKey... androidKey){
        for (AndroidKey key : androidKey) {
            ((AndroidDriver) appiumDriver).pressKey(new KeyEvent().withKey(key));
        }
    }

    protected void scrollMethod(WebElement requiredElement) {
        ((JavascriptExecutor) appiumDriver).executeScript("arguments[0].scrollIntoView(true);", requiredElement);
        appiumDriver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);
    }

    private Map<String, String> getUrlsMap() {
        initUrlMaps();
        Map<String, String> map = urlMaps.get("Pages");
        if (map == null) {
            throw new RuntimeException("Url map for '" + "Pages" + "' was not found.");
        }
        return map;
    }

    private void initUrlMaps() {
        InputStream stream = GeneralView.class.getResourceAsStream("/PageUrls.json");
        if (stream == null) {
            throw new RuntimeException("Could not find AllUrls.json");
        }
        BufferedReader reader = new BufferedReader(new InputStreamReader(stream));
        try {
           urlMaps = new ObjectMapper().enable(JsonParser.Feature.ALLOW_COMMENTS).readValue(reader, HashMap.class);
        } catch (Exception e) {
            throw new RuntimeException("Was not able to parse portal URL's. Details: " + e.getMessage());
        }
    }

    public abstract void getDirectPage();
}