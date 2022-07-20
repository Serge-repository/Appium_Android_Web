package appium_tests;

import GeneralSetup.TestBasisMobile;
import org.testng.annotations.Test;

public class DemoApkTests extends TestBasisMobile {

    @Test
    public void firstTest(){
        homeView.getDirectPage();
        homeView.chooseModelX();
        powerwallView.orderNow();
        powerwallView.clickNewHomeLink();
        powerwallView.fillFootage();
    }
}