package utils;

import io.appium.java_client.android.AndroidDriver;

import java.time.Duration;

import static GeneralSetup.TestBasisMobile.appiumDriver;

public class ActionsWithDeviceAndApp {

    public void runAppInBackground() {
        appiumDriver.runAppInBackground(Duration.ofMillis(4000));
    }

    public void activateApp(String appPackage) {
        appiumDriver.activateApp(appPackage); // switch between apps
    }

    public void lockDevice(Integer seconds) {
        // lock device for some time, then unlock
        ((AndroidDriver) appiumDriver).lockDevice(Duration.ofSeconds(seconds)); // only available in AndroidDriver class
    }

    public void lockDeviceForever() {
        // lock device forever
        ((AndroidDriver) appiumDriver).lockDevice(); // only available in AndroidDriver class
        System.out.println(((AndroidDriver) appiumDriver).isDeviceLocked());
    }

    public void unlockDevice() {
        ((AndroidDriver) appiumDriver).unlockDevice(); // only available in AndroidDriver class
    }
}
