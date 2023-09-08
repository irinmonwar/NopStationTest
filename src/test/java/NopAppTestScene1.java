import io.appium.java_client.AppiumDriver;
import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.touch.WaitOptions;
import io.appium.java_client.touch.offset.PointOption;
import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Story;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;


public class NopAppTestScene1 {
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

    @Epic("Nop Ecommerce App") // Represents a high-level category
    @Feature("Feature Automation") // Represents a feature within the epic


    @Test
    @Story("Splash Screen")
    @Description(value = "Splash Screen Functionality")
    public void click_Agreement_Button() {


        Duration implicitWaitTimeout = Duration.ofSeconds(10);
        WebDriverWait wait = new WebDriverWait(driver, implicitWaitTimeout);
        WebElement acceptButton = wait.until(ExpectedConditions.elementToBeClickable(By.id("com.nopstation.nopcommerce.nopstationcart:id/btnAccept")));


        acceptButton.click();


    }

    @Test(dependsOnMethods = "click_Agreement_Button")
    @Story("Our Categories")
    @Description(value = "Product Selection Activity")
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
            System.out.println("Mattress 2 Clicked");
            viewCart.click();
            Thread.sleep(3000);


        } catch (Exception e) {
            System.err.println("Error: " + e.getMessage());
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

    @AfterTest
    public void tearDown() {
        if (null != driver) {
            driver.quit();
        }


    }
}


