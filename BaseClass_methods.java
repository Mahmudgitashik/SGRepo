package BaseClass;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.RandomStringUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.io.File;
import java.io.IOException;
import java.util.concurrent.TimeUnit;


public class BaseClass_methods {

    private WebDriver driver;
    //public WebDriver driver;
    public WebDriverWait wait;
    public static int timeout = 50;

    public String PageURL;




    public ExtentHtmlReporter reporter;
    public ExtentReports extent;
    public ExtentTest logger;

    public BaseClass_methods() throws IOException {

        try
        {

            System.setProperty("webdriver.chrome.driver","C:\\Users\\mahmud.ashik\\Desktop\\stadiumGoods_Login\\chromedriver.exe");
            this.driver =  new ChromeDriver();
            this.driver.manage().window().maximize();

            //extent report
            reporter = new ExtentHtmlReporter("C:\\Users\\mahmud.ashik\\Desktop\\stadiumGoods_Login\\Reports\\StadiumGoods.html");
            extent = new ExtentReports();
            extent.attachReporter(reporter);

        }
        catch(Exception e)
        {
            Object ee = e;
        }
    }

    public BaseClass_methods(WebDriver driver) throws IOException{
        this.driver = driver;
    }

    //get webDriver
    public WebDriver GetWebDriver()
    {
        return this.driver;
    }

    //logger to log into extent report
    public void LogIt(ExtentTest logger,String StepInfo){
        logger.log(Status.INFO,StepInfo);
    }


    //load the browser and navigate to website
    public void loadPage(){

        driver.get(getPAGE_Url());
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);

    }
    //get page url
    public String getPAGE_Url(){
        return PageURL; }


    //get webElement
    public WebElement Element(String element_name,String method){

        if(method.equals("xpath"))
            return driver.findElement(By.xpath(element_name));
        else
            return driver.findElement(By.id(element_name));

    }

    //Click elements
    public void clickElement(WebElement element){
        WebDriverWait wait = new WebDriverWait(driver,timeout);
        wait.until(ExpectedConditions.visibilityOf(element));
        element.click();
    }

    //Write into
    public void SendKeysText(WebElement element,String text){
        WebDriverWait wait = new WebDriverWait(driver,timeout);
        wait.until(ExpectedConditions.visibilityOf(element));
        element.clear();
        element.sendKeys(text);
        Assert.assertEquals(element.getAttribute("value"),text);
    }

    //screenshot
    public void getScreenshot(String Accesscode) throws IOException {
        String path = "C:\\Users\\mahmud.ashik\\Desktop\\stadiumGoods_Login\\Reports\\StadiumGoods.PNG";
        String fileName = Accesscode+ RandomStringUtils.randomNumeric(2) + ".png";
        File sourceFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
        //Now you can do whatever you need to do with, for example copy somewhere
        FileUtils.copyFile(sourceFile, new File(path + fileName));
        //String imgPath = directory + fileName;
    }//end of screenshot







}//end of main class









