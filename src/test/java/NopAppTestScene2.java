import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileBy;
import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.touch.WaitOptions;
import io.appium.java_client.touch.offset.PointOption;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;


public class NopAppTestScene2 {
    AppiumDriver driver;

    @BeforeTest
    public void setUp() throws MalformedURLException {
        DesiredCapabilities caps = new DesiredCapabilities();
        caps.setCapability("platformName", "Android");
        caps.setCapability("automationName", "UiAutomator2");
        caps.setCapability("platformVersion", "13.0");
        caps.setCapability("deviceName", "Android Emulator");
        // caps.setCapability("app",System.getProperty("user.dir")+"/apps/nopstationCart.apk");
        caps.setCapability("appPackage", "com.nopstation.nopcommerce.nopstationcart");
        caps.setCapability("appActivity", "com.bs.ecommerce.main.SplashScreenActivity");
        caps.setCapability("app-wait-activity", "com.bs.ecommerce.main.SplashScreenActivity");
        caps.setCapability("autoWebviewTimeout", 20000);
        driver = new AndroidDriver(new URL("http://localhost:4723/wd/hub"), caps);

    }

    public void switchToActivity(String appPackage, String appActivity) {
        String command = "adb shell am start -n " + appPackage + "/" + appActivity;
        try {
            Runtime.getRuntime().exec(command);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void click_Agreement_Button() {


        Duration implicitWaitTimeout = Duration.ofSeconds(10);
        WebDriverWait wait = new WebDriverWait(driver, implicitWaitTimeout);
        WebElement acceptButton = wait.until(ExpectedConditions.elementToBeClickable(By.id("com.nopstation.nopcommerce.nopstationcart:id/btnAccept")));


        acceptButton.click();


    }

    @Test(dependsOnMethods = "click_Agreement_Button")
    public void clickElectronics() throws InterruptedException {


        switchToActivity("com.nopstation.nopcommerce.nopstationcart", "com.bs.ecommerce.main.MainActivity");

        System.out.println("Accepted the button click");
        Thread.sleep(3000);
        swipeLeft((AndroidDriver) driver);
        System.out.println("Swiped successfully");


        Thread.sleep(3000);
        try {


            Duration implicitWaitTimeout = Duration.ofSeconds(10);
            WebDriverWait wait = new WebDriverWait(driver, implicitWaitTimeout);
            WebElement itemToClick = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/androidx.drawerlayout.widget.DrawerLayout/android.widget.RelativeLayout/android.widget.FrameLayout[1]/android.widget.RelativeLayout/android.view.ViewGroup/android.widget.ScrollView/android.widget.RelativeLayout/android.view.ViewGroup[2]/androidx.recyclerview.widget.RecyclerView/android.view.ViewGroup[3]")));
            System.out.println("Electronics Clicked");
            itemToClick.click();
            Thread.sleep(3000);
            scrollDown((AndroidDriver) driver);
            System.out.println("Scrolled successfully");


            WebElement mattressToClick = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/androidx.drawerlayout.widget.DrawerLayout/android.widget.RelativeLayout/android.widget.FrameLayout[1]/android.view.ViewGroup/android.widget.RelativeLayout/androidx.drawerlayout.widget.DrawerLayout/androidx.recyclerview.widget.RecyclerView/android.widget.FrameLayout[4]/android.view.ViewGroup")));
            System.out.println("Mattress Clicked");
            mattressToClick.click();
            Thread.sleep(3000);

            scrollDown((AndroidDriver) driver);
            System.out.println("Scrolled 2 successfully");

            WebElement relativeLayout = driver.findElement(By.id("com.nopstation.nopcommerce.nopstationcart:id/productQuantityLayout"));
            WebElement qtyIncreaseBtn = relativeLayout.findElement(By.id("com.nopstation.nopcommerce.nopstationcart:id/btnPlus"));

            System.out.println("Quantity Increased");
            qtyIncreaseBtn.click();
            Thread.sleep(3000);

            WebElement linearLayout = driver.findElement(By.id("com.nopstation.nopcommerce.nopstationcart:id/addtoCartLayout"));
            WebElement addtoCartBtn = linearLayout.findElement(By.id("com.nopstation.nopcommerce.nopstationcart:id/btnAddToCart"));
            System.out.println("Product Added to Cart Successfully");
            addtoCartBtn.click();
            Thread.sleep(3000);


            WebElement viewCart = wait.until(ExpectedConditions.elementToBeClickable(By.id("com.nopstation.nopcommerce.nopstationcart:id/menu_cart")));
            System.out.println("Redirected to View Cart");
            viewCart.click();
            Thread.sleep(3000);

            WebElement relativeLayout2 = driver.findElement(By.id("com.nopstation.nopcommerce.nopstationcart:id/cartRootLayout"));
            WebElement clickCheckOut = relativeLayout2.findElement(By.id("com.nopstation.nopcommerce.nopstationcart:id/btnCheckOut"));

            System.out.println("Going to Checkout Page");
            clickCheckOut.click();
            Thread.sleep(3000);


            //WebElement frameLayout = driver.findElement(By.id("android:id/content"));
            WebElement clickAsGuest = wait.until(ExpectedConditions.elementToBeClickable(By.id("com.nopstation.nopcommerce.nopstationcart:id/btnGuestCheckout")));

            System.out.println("Clicked As Guest");
            clickAsGuest.click();
            Thread.sleep(3000);




        } catch (Exception e) {
            System.err.println("Error: " + e.getMessage());
        }


    }

    @Test(dependsOnMethods = "clickElectronics")
    public void checkOutActivity() throws InterruptedException {

        switchToActivity("com.nopstation.nopcommerce.nopstationcart", "com.bs.ecommerce.checkout.WebViewPaymentActivity");

        System.out.println("We are in Checkout Page now");
        Thread.sleep(3000);

     //   String excelFilePath = "C:\\Users\\hasan\\Downloads\\billingData.xlsx";

        try {

            // Load the Excel file using Apache POI
            FileInputStream fis = new FileInputStream("C:\\Users\\hasan\\Downloads\\billingData.xlsx");
            Workbook workbook = new XSSFWorkbook(fis);

            // Assuming you have the data in the first row of the first sheet
            Sheet sheet = workbook.getSheetAt(0);
            Row row = sheet.getRow(0);

            // Retrieve billing address data from Excel (you can use Apache POI here)
            String firstName = row.getCell(0).getStringCellValue();
            String lasttName = row.getCell(1).getStringCellValue();
            String email = row.getCell(2).getStringCellValue();
            String country = row.getCell(3).getStringCellValue();
            String company = row.getCell(4).getStringCellValue();
            String city = row.getCell(5).getStringCellValue();
            String streetAddress = row.getCell(6).getStringCellValue();
            String streetAddress2 = row.getCell(7).getStringCellValue();
            String postalCode = row.getCell(8).getStringCellValue();
            String phone = row.getCell(9).getStringCellValue();
            String fax = row.getCell(10).getStringCellValue();



            // Navigate to the screen where you want to input the data
            // (You might need to add code to reach this screen)

            // Input data into text boxes and dropdown field
            WebElement relativeLayoutCheckout = driver.findElement(By.id("com.nopstation.nopcommerce.nopstationcart:id/layoutCheckoutAddress"));

            WebElement name1Input = relativeLayoutCheckout.findElement(By.id("com.nopstation.nopcommerce.nopstationcart:id/etFirstName"));
            WebElement name2Input = relativeLayoutCheckout.findElement(By.id("com.nopstation.nopcommerce.nopstationcart:id/etLastName"));
            WebElement emailInput = relativeLayoutCheckout.findElement(By.id("com.nopstation.nopcommerce.nopstationcart:id/etEmail"));
            WebElement companyInput = relativeLayoutCheckout.findElement(By.id("com.nopstation.nopcommerce.nopstationcart:id/etCompanyName"));
            WebElement streetAddress1Input = relativeLayoutCheckout.findElement(By.id("com.nopstation.nopcommerce.nopstationcart:id/etStreetAddress"));
            WebElement streetAddress2Input = relativeLayoutCheckout.findElement(By.id("com.nopstation.nopcommerce.nopstationcart:id/etStreetAddress2"));
            WebElement cityInput = relativeLayoutCheckout.findElement(By.id("com.nopstation.nopcommerce.nopstationcart:id/etCity"));
          //  WebElement countryDropdown = relativeLayoutCheckout.findElement(By.id("com.nopstation.nopcommerce.nopstationcart:id/countrySpinner"));
            WebElement postalCodeInput = relativeLayoutCheckout.findElement(By.id("com.nopstation.nopcommerce.nopstationcart:id/etZipCode"));
            WebElement phoneInput = relativeLayoutCheckout.findElement(By.id("com.nopstation.nopcommerce.nopstationcart:id/etPhone"));
            WebElement faxInput = relativeLayoutCheckout.findElement(By.id("com.nopstation.nopcommerce.nopstationcart:id/etFax"));

            name1Input.sendKeys(firstName);
            name2Input.sendKeys(lasttName);
            emailInput.sendKeys(email);
            companyInput.sendKeys(company);


            // Click the dropdown to open it (assuming it's a click-to-open dropdown)
            Duration implicitWaitTimeout = Duration.ofSeconds(10);
            WebDriverWait wait = new WebDriverWait(driver, implicitWaitTimeout);
            WebElement countryDropdown = wait.until(ExpectedConditions.elementToBeClickable(By.id("com.nopstation.nopcommerce.nopstationcart:id/countrySpinner")));

            countryDropdown.click();

            // Select the state from the dropdown (assuming it's a list-based dropdown)
            Thread.sleep(3000);

            scrollDown((AndroidDriver) driver);
            scrollDown((AndroidDriver) driver);
            scrollDown((AndroidDriver) driver);
            System.out.println("Scrolled Country successfully");
            WebElement stateOption = driver.findElement(MobileBy.AndroidUIAutomator("text(\"" + country + "\")"));
            stateOption.click();

            cityInput.sendKeys(city);

            streetAddress1Input.sendKeys(streetAddress);

            Thread.sleep(3000);

            scrollDown((AndroidDriver) driver);
            System.out.println("Scrolled Street successfully");


            streetAddress2Input.sendKeys(streetAddress2);


            postalCodeInput.sendKeys(postalCode);

            phoneInput.sendKeys(phone);
            faxInput.sendKeys(fax);
            // Submit the form or perform other actions as needed

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

        private static void swipeLeft(AndroidDriver driver) {

        int maxSwipeAttempts = 1;

        for (int i = 0; i < maxSwipeAttempts; i++) {
            Dimension size = driver.manage().window().getSize();
            int startY = (int) (size.width * 0.8); // Start scroll from 80% down
            int endY = (int) (size.width * 0.2);   // Scroll to 20% down
            int centerY = size.height / 2;

            TouchAction<?> action = new TouchAction<>(driver)
                    .press(PointOption.point(startY, centerY))
                    .waitAction(WaitOptions.waitOptions(Duration.ofMillis(500)))
                    .moveTo(PointOption.point(endY, centerY))
                    .release()
                    .perform();

            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }
    }

    private static void scrollDown(AndroidDriver driver) {

        int maxScrollAttempts = 1;

        for (int i = 0; i < maxScrollAttempts; i++) {

            Dimension size = driver.manage().window().getSize();
            int startY = (int) (size.height * 0.5); // Start scroll from 80% down
            int endY = (int) (size.height * 0.2);   // Scroll to 20% down

            TouchAction<?> action = new TouchAction<>(driver)
                    .press(PointOption.point(size.width / 2, startY))
                    .waitAction(WaitOptions.waitOptions(Duration.ofMillis(500)))
                    .moveTo(PointOption.point(size.width / 2, endY))
                    .release()
                    .perform();

            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }


        }
    }

//    @AfterTest
//    public void tearDown() {
//        if (null != driver) {
//            driver.quit();
//        }
//
//
//    }
}


