package Screens;

import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.Status;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.TouchAction;
import io.appium.java_client.touch.LongPressOptions;
import io.appium.java_client.touch.offset.PointOption;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.io.File;
import java.io.IOException;
import java.time.Duration;
import java.util.List;

import static Tests.Main.test;

public class HomeScreen {

    private AppiumDriver driver;


    @FindBy(id = "il.co.mintapp.buyme:id/tab_title")
    private List<WebElement> categoryButton;

    @FindBy(id = "il.co.mintapp.buyme:id/businessImage")
    private List<WebElement> businessButton;

    @FindBy(id = "il.co.mintapp.buyme:id/priceEditText")
    private WebElement giftPriceBox;

    @FindBy(id = "il.co.mintapp.buyme:id/purchaseButton")
    private WebElement purchaseButton;

    public HomeScreen(AppiumDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    //Choose a category
    private void clickCategoryButton() {
        categoryButton.get(3).click();
    }

    //Choose a business
    private void clickBusinessButton() {
        businessButton.get(2).click();
    }

    //Input gift price
    private void enterGiftPrice() {
        giftPriceBox.sendKeys("100");
    }

    //Click purchase
    private void clickPurchaseButton() {
        purchaseButton.click();
    }

    //Creating a swipe TouchAction that swipes from X, Y coordinates to X, Y
    private void swipeAction(){
        TouchAction action = new TouchAction(driver);
        Duration swipeDuration = Duration.ofMillis(100);
        LongPressOptions longPressOptions = new LongPressOptions();
        PointOption fromPointOption = new PointOption();
        fromPointOption.withCoordinates(10, 280);
        PointOption toPointOption = new PointOption();
        toPointOption.withCoordinates(800, 280);
        longPressOptions.withDuration(swipeDuration).withPosition(fromPointOption).build();
        action.longPress(longPressOptions).moveTo(toPointOption).release().perform();
    }



    public void chooseGiftHomeScreenSteps() throws IOException, InterruptedException { //used elements are here to be called easily from main
        Thread.sleep(3000);  //Pause to prevent an error from early swipe
        this.swipeAction();
        test.log(Status.INFO, "After swipe");
        this.clickCategoryButton();
        test.log(Status.INFO, "After click category");
        test.pass("HomeScreenScreenCapture", MediaEntityBuilder.createScreenCaptureFromPath(takeScreenShot(driver, "/Users/AlmogMaoz/Desktop/2")).build());
        test.log(Status.INFO, "After second page screen capture");
        this.clickBusinessButton();
        test.log(Status.INFO, "After click business button");
        this.enterGiftPrice();
        test.log(Status.INFO, "After enter gift price");
        this.clickPurchaseButton();
        test.log(Status.INFO, "After click purchase button");
    }

    //Set up for screen captures
    private String takeScreenShot(AppiumDriver driver, String ImagesPath) throws IOException {
        TakesScreenshot takesScreenshot = (TakesScreenshot) driver;
        File screenShotFile = takesScreenshot.getScreenshotAs(OutputType.FILE);
        File destinationFile = new File(ImagesPath + ".png");
        try {
            FileUtils.copyFile(screenShotFile, destinationFile);
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
        return ImagesPath + ".png";
    }


}