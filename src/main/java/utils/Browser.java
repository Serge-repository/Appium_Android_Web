package utils;

public enum Browser {
    CHROME("Chrome"),
    FIREFOX("Firefox");

    String browserName;

    Browser(String browserName) {
        this.browserName = browserName;
    }

    public String getBrowserName() {
        return browserName;
    }

    public static Browser getRequiredBrowser() {
        return Browser.valueOf(System.getProperty("browser", "chrome").toUpperCase());
    }
}
