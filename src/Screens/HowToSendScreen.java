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
import java.util.List;

import static Tests.Main.test;


public class HowToSendScreen {

    private AppiumDriver driver;

    @FindBy(id = "il.co.mintapp.buyme:id/nowRadioButton")
    private WebElement nowRadioButton;

    @FindBy(id = "il.co.mintapp.buyme:id/optionCheckBox")
    private List<WebElement> chooseHowCheckBox;

    @FindBy (className = "android.widget.EditText")
    private WebElement emailInputField;

    @FindBy (id = "il.co.mintapp.buyme:id/goNextButton")
    private WebElement howToSendContinueButton;

    public HowToSendScreen(AppiumDriver driver){
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }
    //Click now radio button
    private void clickNowRadioButton(){
        nowRadioButton.click();
    }
    //Click on contact choice check box
    private void clickChooseCheckBox(){
        chooseHowCheckBox.get(2).click();
    }
    //Write email in field
    private void inputEmailField(){
        emailInputField.sendKeys(Constants.USER_EMAIL);
        driver.hideKeyboard();
    }
    //Click on continue button
    private void clickContinueButton(){
        howToSendContinueButton.click();

    }


    public void howToSendScreenSteps() throws IOException { //used elements are here to be called easily from main
        this.clickNowRadioButton();
        test.log(Status.INFO, "After pressing radio button");
        test.pass("HowToSendScreenCapture", MediaEntityBuilder.createScreenCaptureFromPath(takeScreenShot(driver, "/Users/AlmogMaoz/Desktop/4")).build());
        test.log(Status.INFO, "After fourth page screen capture");
        this.clickChooseCheckBox();
        test.log(Status.INFO, "After choosing email checkbox");
        this.inputEmailField();
        test.log(Status.INFO, "After inputting email");
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
