package Screens;

import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.Status;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.TouchAction;
import io.appium.java_client.touch.LongPressOptions;
import io.appium.java_client.touch.offset.ElementOption;
import io.appium.java_client.touch.offset.PointOption;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.io.File;
import java.io.IOException;
import java.time.Duration;

import static Tests.Main.test;


public class IntroAndRegistrationScreen {

    private AppiumDriver driver;

    @FindBy(id = "il.co.mintapp.buyme:id/skipButton")
    private WebElement registerButton;

    @FindBy(id = "il.co.mintapp.buyme:id/googleButton")
    private WebElement googleRegisterButton;

    @FindBy (id = "com.google.android.gms:id/account_display_name")
    private WebElement chooseUserRegister;

    public IntroAndRegistrationScreen(AppiumDriver driver){
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }




    //Click register button
    private void clickRegisterButton() {
        registerButton.click();
    }
    //Click register with google
    private void clickRegisterWithGoogle() {
        googleRegisterButton.click();
    }
    //Click on user to register with
    private void clickChooseUser(){
        chooseUserRegister.click();
    }


    public void registerToBuyMeSteps() throws IOException { //used elements are here to be called easily from main
        this.clickRegisterButton();
        test.log(Status.INFO, "After click register button");
        test.pass("IntroAndRegistrationScreenCapture", MediaEntityBuilder.createScreenCaptureFromPath(takeScreenShot(driver, "/Users/AlmogMaoz/Desktop/1")).build());
        test.log(Status.INFO, "After first page screen capture");
        this.clickRegisterWithGoogle();
        test.log(Status.INFO, "After click to register with google");
        this.clickChooseUser();
        test.log(Status.INFO, "After click to choose user");

    }
    //Set up for screen captures
    private String takeScreenShot(AppiumDriver driver, String ImagesPath) throws IOException {
        TakesScreenshot takesScreenshot = (TakesScreenshot) driver;
        File screenShotFile = takesScreenshot.getScreenshotAs(OutputType.FILE);
        File destinationFile = new File(ImagesPath+".png");
        try {
            FileUtils.copyFile(screenShotFile, destinationFile);
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
        return ImagesPath+".png";
    }
}
