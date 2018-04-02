package Tests;

import Screens.HomeScreen;
import Screens.HowToSendScreen;
import Screens.IntroAndRegistrationScreen;
import Screens.SenderAndReceiverInfoScreen;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.remote.MobileCapabilityType;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.w3c.dom.Document;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.concurrent.TimeUnit;
@FixMethodOrder(MethodSorters.NAME_ASCENDING)

public class Main {

    private static AppiumDriver driver;
    private IntroAndRegistrationScreen objIntroAndRegistrationScreen;
    private HomeScreen objHomeScreen;
    private SenderAndReceiverInfoScreen objSenderAndReceiverInfoScreen;
    private HowToSendScreen objHowToSendScreen;
    private static ExtentReports extent;
    public static ExtentTest test;


    @BeforeClass
    public static void beforeTest() throws IOException {

        ExtentHtmlReporter htmlReporter = new ExtentHtmlReporter(xmlGrab("report"));

        //Append each test
        htmlReporter.setAppendExisting(true);
        //Attach reporter
        extent = new ExtentReports();
        extent.attachReporter(htmlReporter);
        //Name test and add description
        test = extent.createTest("BuyMe Mobile Test");
        //Log results
        test.log(Status.INFO, "Before class");

        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability(MobileCapabilityType.PLATFORM_NAME, "Android");
        capabilities.setCapability(MobileCapabilityType.DEVICE_NAME, "My Nexus 6p device");
        capabilities.setCapability("appPackage", xmlGrab("package"));
        capabilities.setCapability("appActivity", xmlGrab("activity"));
        capabilities.setCapability(MobileCapabilityType.NEW_COMMAND_TIMEOUT, 120);
        driver = new AndroidDriver(new URL("http://0.0.0.0:4723/wd/hub/"), capabilities);
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);

    }
    @Test
    public void Test1() throws InterruptedException, IOException {
        objIntroAndRegistrationScreen = new IntroAndRegistrationScreen(driver);
        objIntroAndRegistrationScreen.registerToBuyMeSteps();
    }

    @Test
    public void Test2() throws IOException, InterruptedException {
        objHomeScreen = new HomeScreen(driver);
        objHomeScreen.chooseGiftHomeScreenSteps();
    }

    @Test
    public void Test3() throws IOException {
        objSenderAndReceiverInfoScreen = new SenderAndReceiverInfoScreen(driver);
        objSenderAndReceiverInfoScreen.senderAndReceiverHomeScreenSteps();
    }

    @Test
    public void Test4() throws IOException {
        objHowToSendScreen = new HowToSendScreen(driver);
        objHowToSendScreen.howToSendScreenSteps();
    }

    @AfterClass
    public static void afterClass(){
        test.log(Status.INFO, "After test");
        extent.flush();
    }


    private static String xmlGrab (String thisClass) {

        //Get xml file
        File xmlFile = new File("MyXML.xml");

        try{
            //Prepare XML
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(xmlFile);
            doc.getDocumentElement().normalize();
            return doc.getElementsByTagName(thisClass).item(0).getTextContent();

        } catch (Exception e){
            e.printStackTrace();
        }
        return thisClass;
    }


}


