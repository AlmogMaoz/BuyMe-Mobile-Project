package Screens;


import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.Status;
import io.appium.java_client.AppiumDriver;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.io.File;
import java.io.IOException;

import static Tests.Main.test;

public class SenderAndReceiverInfoScreen {
    private AppiumDriver driver;


    @FindBy (id = "il.co.mintapp.buyme:id/toEditText")
    private WebElement receiverBox;

    @FindBy (id = "il.co.mintapp.buyme:id/blessEditText")
    private WebElement blessingBox;

    @FindBy (id = "il.co.mintapp.buyme:id/userFrom")
    private WebElement senderBox;

    @FindBy (id = "il.co.mintapp.buyme:id/fabAlikeContainer")
    private WebElement scrollDownButton;

    @FindBy (id = "il.co.mintapp.buyme:id/goNextButton")
    private WebElement senderAndReceiverContinueButton;

    public SenderAndReceiverInfoScreen(AppiumDriver driver){
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }
    //Write receiver name in box
    private void inputReceiverBox(){
        receiverBox.sendKeys("ReceiverPlaceHolder");
        driver.hideKeyboard();
    }
    //Write blessing in blessing box
    private void inputBlessingBox(){
        blessingBox.click();  //Added a click inside to avoid a click that makes it open the dropdown in the element above
        blessingBox.sendKeys("BlessingPlaceHolder");
        driver.hideKeyboard();
    }
    //Clear box and write user name
    private void inputSenderBox(){
        senderBox.clear();
        senderBox.sendKeys(Constants.USER_NAME);
        driver.hideKeyboard();
    }
    //Click scroll down arrow
    private void clickScrollDown(){
        scrollDownButton.click();
    }
    //Click continue button
    private void clickContinueButton(){
        senderAndReceiverContinueButton.click();
    }


    public void senderAndReceiverHomeScreenSteps() throws IOException { //used elements are here to be called easily from main
        this.inputReceiverBox();
        test.log(Status.INFO, "After entering receiver name");
        this.inputBlessingBox();
        test.log(Status.INFO, "After entering blessing");
        test.pass("SenderAndReceiverInfoScreenScreenCapture", MediaEntityBuilder.createScreenCaptureFromPath(takeScreenShot(driver, "/Users/AlmogMaoz/Desktop/3")).build());
        test.log(Status.INFO, "After third page screen capture");
        this.inputSenderBox();
        test.log(Status.INFO, "After inputting sender name");
        this.clickScrollDown();
        test.log(Status.INFO, "After scrolling down with the button");
        this.clickContinueButton();
        test.log(Status.INFO, "After clicking continue button");
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
