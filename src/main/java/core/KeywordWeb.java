package core;
import io.appium.java_client.AppiumBy;
import io.qameta.allure.Step;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.*;
import org.slf4j.Logger;
import org.testng.Assert;
import javax.imageio.ImageIO;
import java.awt.Rectangle;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.TimeUnit;
import java.util.function.Function;
import static core.BaseTest.driver;

public class KeywordWeb {
    private static Logger logger = LogHelper.getLogger();
    public KeywordWeb() {
    }
    public String getUrl() {
        return driver.getCurrentUrl();
    }
    public static void sleep(double second) {
        try {
            Thread.sleep((long) (1000 * second));
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
    @Step("Send key: {0}  text: {1}")
    public void sendKeys(By by, String key){
        webDriverWaitForElementPresent(by, 20);
        String content = PropertiesFile.getPropValue(key);
        logger.info("Send key " + by);
        if (content == null) {
            content = key;
        }
        driver.findElement(by).sendKeys(content);
    }
    @Step("Scroll to position: {0}")
    public void scrollToPositionByScript(String jsScript) {
        logger.info(" scrolling to position ");
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript(jsScript);
    }
    @Step("Get text: {1}")
    public void getText(By by){
        logger.info("Get Text " + by);
        driver.findElement(by).getText();
    }
    @Step("Click: {0}")
    public void click(By by){
        webDriverWaitForElementPresent(by, 20);
        logger.info("click " + by);
        driver.findElement(by).click();
    }
    @Step("So sánh message: {1}")
    public void assertEqual(By by, String text){
        String content = PropertiesFile.getPropValue(text);
        webDriverWaitForElementPresent(by, 20);
        logger.info("Compare message" + by + " with " + text);
        if (content == null) {
            content = text;
        }
        Assert.assertEquals(driver.findElement(by).getText(), content);
    }
    @Step("Chờ element: {0}")
    public void webDriverWaitForElementPresent(By by, long timeout){
        logger.info("Wait For Element Present" + by);
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeout));
        wait.until(ExpectedConditions.elementToBeClickable(by));
    }
    @Step("Chờ element không hiển thị: {0}")
    public void webDriverWaitInvisibleElement(By by, long timeout){
        logger.info("Wait For Element Not Present" + by);
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeout));
        wait.until(ExpectedConditions.invisibilityOfElementLocated(by));
    }
    @Step("Xác thực hiển thị and click: {0}")
    public void verifyPresentAndClick(By by){
        logger.info("Verify Present And Click" + by);
        if(verifyElementPresent(by)) {
            click(by);
        }
    }
    @Step("Xác thực hiển thị phần tử: {0}")
    public boolean verifyElementPresent(By by) {
        logger.info("verifyElementPresent: " + by);
        try {
            driver.findElement(by);
            return true;
        } catch (NoSuchElementException e) {
            e.printStackTrace();
            return false;
        }
    }
    public void executeJsHorizontal(String command, WebElement element) {
        logger.info("Executing JavaScript");
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript(command, element);
    }
    public void selectByText(By by, String text){
        logger.info("Select By Text " + by);
        Select singleSelect = new Select(driver.findElement(by));
        singleSelect.selectByVisibleText(text);
    }

    public void addDataToList(List<String> list,String element){
        String data = PropertiesFile.getPropValue(element);
        if (data == null) {
            data = element;
        }
        list.add(data);
    }
    public void clearTextAndSendKey(String element, String content){
        logger.info("Clear and send keys" + element + "with " + content);
        String xPathElement1 = PropertiesFile.getPropValue(element);
        String xPathElement2 = PropertiesFile.getPropValue(content);
        if (xPathElement1 == null) {
            xPathElement1 = element;
        }
        if (xPathElement2 == null) {
            xPathElement2 = content;

        }
        driver.findElement(By.xpath(xPathElement1)).clear();
        driver.findElement(By.xpath(xPathElement1)).sendKeys(xPathElement2);
    }
    public void clearText(String element) {
        logger.info("clearText");
        String xPathElement = PropertiesFile.getPropValue(element);
        if (xPathElement == null) {
            xPathElement = element;
        }
        driver.findElement(By.xpath(xPathElement)).clear();
    }
    public void randomElement(String element) {
        String xPathElement = PropertiesFile.getPropValue(element);
        if (xPathElement == null) {
            xPathElement = element;
        }
        List<WebElement> weblist = driver.findElements(By.xpath(xPathElement));
        int size = weblist.size();
        int randNumber = ThreadLocalRandom.current().nextInt(0, size);
        weblist.get(randNumber).click();
    }

    public void doubleClick(String element) {
        logger.info("double click" + element);
        String xPathElement = PropertiesFile.getPropValue(element);
        if (xPathElement == null) {
            xPathElement = element;
        }
        Actions builder = new Actions(driver);
        WebElement elementRep = driver.findElement(By.xpath(xPathElement));
        builder.doubleClick(elementRep).perform();
    }

    public void dragAndDropToObj(String startElement, String endElement) {
        logger.info("drag from" + startElement + " to" + endElement);
        String xPathElement1 = PropertiesFile.getPropValue(startElement);
        if (xPathElement1 == null) {
            xPathElement1 = startElement;
        }
        String xPathElement2 = PropertiesFile.getPropValue(endElement);
        if (xPathElement2 == null) {
            xPathElement2 = endElement;
        }
        Actions builder = new Actions(driver);
        WebElement source = driver.findElement(By.xpath(xPathElement1));
        WebElement target = driver.findElement(By.xpath(xPathElement2));
        builder.dragAndDrop(source, target).perform();
    }

    public void rightClick(String element, String menuItem) {
        logger.info("rightClick" + element);
        String xPathElement1 = PropertiesFile.getPropValue(element);
        if (xPathElement1 == null) {
            xPathElement1 = element;
        }
        String xPathElement2 = PropertiesFile.getPropValue(menuItem);
        if (xPathElement2 == null) {
            xPathElement2 = menuItem;
        }
        Actions builder = new Actions(driver);
        WebElement clickMe = driver.findElement(By.xpath(xPathElement1));
        WebElement editMenuItem = driver.findElement(By.xpath(xPathElement2));
        builder.contextClick(clickMe).moveToElement(editMenuItem).click().perform();
    }

    public void hoverAndClick(String element) {
        logger.info("Move To Element" + element);
        String xPathElement = PropertiesFile.getPropValue(element);
        if (xPathElement == null) {
            xPathElement = element;
        }
        Actions action = new Actions(driver);
        WebElement elementRep = driver.findElement(By.xpath(xPathElement));
        action.moveToElement(elementRep).perform();
    }

    public void hoverAndClicks(String element) {
        logger.info("Move To Element" + element);
        String xPathElement = PropertiesFile.getPropValue(element);
        if (xPathElement == null) {
            xPathElement = element;
        }
        Actions action = new Actions(driver);
        WebElement elementRep = driver.findElement(By.xpath(xPathElement));
        action.moveToElement(elementRep).clickAndHold();
    }

    public void executeJavaScript(String command) {
        logger.info("Executing JavaScript");
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript(command);
    }

    public void maximizeWindow() {
        logger.info("Maximizing browser window...");
        driver.manage().window().maximize();
    }
    public void back() {
        logger.info("Back window...");
        driver.navigate().back();
    }
    public void reLoadPage() {
        logger.info("ReLoad Page...");
        driver.navigate().refresh();
    }
    public void navigateToUrl(String url) {
        logger.info("Navigating to URL..." + url);
        String xPathElement = PropertiesFile.getPropValue(url);
        if (xPathElement == null) {
            xPathElement = url;
        }
        driver.navigate().to(xPathElement);
    }

    public void getCurrentPageUrl() {
        logger.info("Navigating to URL...");
        driver.getCurrentUrl();
    }

    public void acceptAlert() {
        logger.info("Accepting alert...");
        Alert alert = driver.switchTo().alert();
        alert.accept();
    }

    public String getAlertText() {
        logger.info("Getting alert text...");
        Alert alert = driver.switchTo().alert();
        return alert.getText();

    }

    public void dismissAlert() {
        logger.info("Dismissing alert...");
        Alert alert = driver.switchTo().alert();
        alert.dismiss();
    }

    public void setAlertText(String alertText) {
        logger.info("Setting alert text...");
        Alert alert = driver.switchTo().alert();
        alert.sendKeys(alertText);
        alert.accept();
    }

    //send username and password to alert login
    public void handleLoginPopup(String username, String password, String authenUrl) {
        logger.info("login by authen link with" + username + " " + password);
        String xPathElement1 = PropertiesFile.getPropValue(username);
        if (xPathElement1 == null) {
            xPathElement1 = username;
        }
        String xPathElement2 = PropertiesFile.getPropValue(password);
        if (xPathElement2 == null) {
            xPathElement2 = password;
        }
        String xPathElement3 = PropertiesFile.getPropValue(authenUrl);
        if (xPathElement3 == null) {
            xPathElement3 = authenUrl;
        }
        String url = "https://" + xPathElement1 + ":" + xPathElement2 + "@" + xPathElement3;
        driver.navigate().to(url);
    }


    public void switchToFrame(String frame) {
        logger.info("Switching to frame...");
        driver.switchTo().frame(frame);
    }

    public void switchToFrameByIndex(int index) {
        logger.info("Switching to frame by index...");
        driver.switchTo().frame(index);
    }

    public void switchToIFrame() {
        logger.info("Switching to Iframe");
        WebElement iframe = driver.findElement(By.tagName("iframe"));
        driver.switchTo().frame(iframe);
    }

    public void listWindowID() {
        for (String windowid : driver.getWindowHandles()) {
            logger.info("Listing window ID..." + windowid);
        }
    }
    public void switchToIFrameByXpath(String element) {
        logger.info("Switching to Iframe");
        String xPathElement = PropertiesFile.getPropValue(element);
        if (xPathElement == null) {
            xPathElement = element;
        }
        WebElement iframe = driver.findElement(By.xpath(xPathElement));
        driver.switchTo().frame(iframe);
    }
    public void switchToWindowByIndex(int index) {
        logger.info("switchToWindowByIndex");
        driver.switchTo().window(new ArrayList<>(driver.getWindowHandles()).get(index));
    }

    public void getTitleWindowByIndex(int index) {
        logger.info("switchToWindowByIndex");
        driver.switchTo().window(new ArrayList<>(driver.getWindowHandles()).get(index)).getTitle();
    }

    public void switchToParentWindow() {
        logger.info("switchToParentWindow");
        String parentWindowId = driver.getWindowHandle();
        driver.switchTo().window(parentWindowId);
    }

    public void simpleAssertEquals(String expected, String actual) {
        logger.info("compare from " + expected + " with " + actual);
        String xPathElement1 = PropertiesFile.getPropValue(expected);
        if (xPathElement1 == null) {
            xPathElement1 = expected;
        }
        String xPathElement2 = PropertiesFile.getPropValue(actual);
        if (xPathElement2 == null) {
            xPathElement2 = actual;
        }
        Assert.assertEquals(xPathElement2, xPathElement1);

    }
    public void scrollDownToElement(String xPath) {
        logger.info("scrollDownToElement" + xPath);
        String xPathElement = PropertiesFile.getPropValue(xPath);
        if (xPathElement == null) {
            xPathElement = xPath;
        }
        WebElement element = driver.findElement(By.xpath(xPathElement));
        Actions actions = new Actions(driver);
        actions.moveToElement(element);
        actions.perform();
    }

    public void scrollDownToElementByCss(String element) {
        logger.info("scroll to element");
        String xPathElement = PropertiesFile.getPropValue(element);
        if (xPathElement == null) {
            xPathElement = element;
        }
        WebElement elements = driver.findElement(By.cssSelector(xPathElement));
        Actions actions = new Actions(driver);
        actions.moveToElement(elements);
        actions.perform();
    }

    public void scrollToPosition() {
        logger.info(" scrolling to position ");
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("window.scrollBy(0,5000)");
    }
    public void selectDropDownListByName(String ddlPath, String itemName) {
        logger.info("select item by visibe text");
        String xPathElement1 = PropertiesFile.getPropValue(ddlPath);
        if (xPathElement1 == null) {
            xPathElement1 = ddlPath;
        }
        String xPathElement2 = PropertiesFile.getPropValue(itemName);
        if (xPathElement2 == null) {
            xPathElement2 = itemName;
        }
        Select dropDownList = new Select(driver.findElement(By.xpath(xPathElement1)));
        dropDownList.selectByVisibleText(xPathElement2);

    }
    public void verifyElement(String element, boolean check) {
        logger.info("verifyElement" + element);
        String xPathElement = PropertiesFile.getPropValue(element);
        boolean confirm = true;
        if (xPathElement == null) {
            xPathElement = element;
        }
        try {
            driver.findElement(By.xpath(xPathElement)).isDisplayed();
        } catch (NoSuchElementException e) {
            e.printStackTrace();
            confirm = false;
        }
        Assert.assertEquals(confirm, check);
    }

    public void checkElementIsNotDisplayed(String element) {
        logger.info("checkElementVisibleOrNot" + element);
        String xPathElement = PropertiesFile.getPropValue(element);
        if (xPathElement == null) {
            xPathElement = element;
        }
        try {
            driver.findElement(By.xpath(xPathElement));
            //I want to fail the test here if above element is found
            Assert.assertTrue(false);
        } catch (NoSuchElementException e) {
            e.printStackTrace();
            //pass the test if element is not found in try statement
            Assert.assertTrue(true);
        }
    }

    //if element is displayed, testcase will keep running, otherwise it will stop
    public void checkElementIsDisplayed(String element) {
        logger.info("checkElementVisibleOrNot" + element);
        String xPathElement = PropertiesFile.getPropValue(element);
        if (xPathElement == null) {
            xPathElement = element;
        }
        try {
            driver.findElement(By.xpath(xPathElement));
            //I want to pass the test here if above element is found
            Assert.assertTrue(true);
        } catch (NoSuchElementException e) {
            e.printStackTrace();
            //fail the test if element is not found in try statement
            Assert.assertTrue(false);
        }
    }

    public boolean CheckIsDisplayElement(String element) {
        logger.info("Check status element btn radio");
        String xPathElement = PropertiesFile.getPropValue(element);
        if (xPathElement == null) {
            xPathElement = element;
        }
        boolean stt = driver.findElement(By.xpath(xPathElement)).isDisplayed();
        if (!stt) {
            System.out.println("Not selected");
        } else {
            driver.navigate().back();
            System.out.println("Checkbox selected");
        }
        return stt;
    }

    public void deleteAllCookies() {
        logger.info("deleteAllCookies");
        driver.manage().deleteAllCookies();
    }

    public boolean checkStatusIsDisplay(String element) {
        logger.info("Check status ");
        String xPathElement = PropertiesFile.getPropValue(element);
        if (xPathElement == null) {
            xPathElement = element;
        }
        boolean status = driver.findElement(By.xpath(xPathElement)).isDisplayed();
        if (status) {
            System.out.println("Is Display" + "\t" + element);
        } else {
            System.out.println("Is not Display" + "\t" + element);
        }
        return status;

    }

    public Integer countNumberOfElement(String element) {
        logger.info("count the number of element " + element);
        String xPathElement = PropertiesFile.getPropValue(element);
        if (xPathElement == null) {
            xPathElement = element;
        }
        int xpathCount = driver.findElements(By.xpath(xPathElement)).size();
        return xpathCount + 1;
    }

    public void chooseFile(String element, String path){
        logger.info("choose file from " + path);
        String xPathElement = PropertiesFile.getPropValue(element);
        if (xPathElement == null) {
            xPathElement = element;
        }
        WebElement chooseFile = driver.findElement(By.xpath(xPathElement));
        chooseFile.sendKeys(path);
    }
    public void webDriverWaitForElementPresent(String element, long timeout) {
        logger.info("webDriverWaitForElementPresent" + element);
        String xPathElement = PropertiesFile.getPropValue(element);
        if (xPathElement == null) {
            xPathElement = element;
        }
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeout));
        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(xPathElement)));
    }
    public String splitEnterCharacters(String text, Integer index) {
        String[] words = text.split("\n");
        return words[index];
    }

    public void waitForAjaxToFinish() throws InterruptedException {
        logger.info("waitForAjaxToFinish");

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(3000));

        wait.until((ExpectedCondition<Boolean>) wdriver -> ((JavascriptExecutor) driver).executeScript(
                "return !!window.jQuery && !!window.jQuery.active == 0;").equals(true));
        Thread.sleep(150);
    }

    private static void until(Function<WebDriver, Boolean> waitCondition, Long timeoutInSeconds) {
        WebDriverWait webDriverWait = new WebDriverWait(driver, Duration.ofSeconds(timeoutInSeconds));
//        webDriverWait.withTimeout(timeoutInSeconds, TimeUnit.SECONDS);
        try {
            webDriverWait.until(waitCondition);
        } catch (Exception e) {
            System.out.println(e.getMessage());

        }
    }
    public void untilJqueryIsDone(Long timeoutInSeconds) throws InterruptedException {
        until((d) ->
        {
            Boolean isJqueryCallDone = (Boolean) ((JavascriptExecutor) driver).executeScript("return jQuery.active==0");
            if (!isJqueryCallDone) System.out.println("JQuery call is in Progress");
            return isJqueryCallDone;
        }, timeoutInSeconds);
        Thread.sleep(1000);
    }

    public String waitForElementNotVisible(int timeOutInSeconds, String elementXPath) {
        logger.info("elemnt " + elementXPath + " not visible");
        String xPathElement = PropertiesFile.getPropValue(elementXPath);
        if (xPathElement == null) {
            xPathElement = elementXPath;
        }
        try {
            (new WebDriverWait(driver, Duration.ofSeconds(timeOutInSeconds))).until(ExpectedConditions.invisibilityOfElementLocated(By
                    .xpath(xPathElement)));
            return null;
        } catch (TimeoutException e) {
            return "Build your own errormessage...";
        }
    }

    public void webDriverWaitForElementPresentByCss(String element, long timeout) {
        logger.info("webDriverWaitForElementPresentByCss");
        String xPathElement = PropertiesFile.getPropValue(element);
        if (xPathElement == null) {
            xPathElement = element;
        }
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeout));
        wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector(xPathElement)));
    }

    public void fluentWaitForElementPresent(String element, Duration polling, Duration timeout) {
        logger.info("fluentWaitForElementPresent");
        Wait<WebDriver> wait = new FluentWait<WebDriver>(driver)
                .withTimeout(timeout)
                .pollingEvery(polling)
                .ignoring(NoSuchElementException.class);
        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(element)));
    }

    public void copyPaste(String element1, String element2, String content) throws InterruptedException {
        String xPathElement1 = PropertiesFile.getPropValue(element1);
        String xPathElement2 = PropertiesFile.getPropValue(element2);
        String xPathContent = PropertiesFile.getPropValue(content);
        if (xPathElement1 == null) {
            xPathElement1 = element1;
        }
        if (xPathElement2 == null) {
            xPathElement2 = element2;
        }
        if (xPathContent == null) {
            xPathContent = content;
        }
        Actions act = new Actions(driver);
        WebElement ele1 = driver.findElement(By.xpath(xPathElement1));
        WebElement ele2 = driver.findElement(By.xpath(xPathElement2));

        act.moveToElement(ele1).click().sendKeys(xPathContent);
        act.keyDown(Keys.CONTROL).sendKeys("a");
        act.sendKeys("c");
        ele2.clear();
        untilJqueryIsDone(30L);
        act.moveToElement(ele2).click().keyDown(Keys.CONTROL).sendKeys("v");
        act.keyUp(Keys.CONTROL).build().perform();
    }

    public String getAttribute(String element, String tag) {
        logger.info("get Attribute of" + element);
        String xPathElement = PropertiesFile.getPropValue(element);
        if (xPathElement == null) {
            xPathElement = element;
        }
        WebElement b = driver.findElement(By.xpath(xPathElement));
        String c = b.getAttribute(tag);
        logger.info(c);
        return c;
    }

    public String getAttributeWithValue(String element) {
        logger.info("get Attribute of" + element);
        String xPathElement = PropertiesFile.getPropValue(element);
        if (xPathElement == null) {
            xPathElement = element;
        }
        WebElement b = driver.findElement(By.xpath(xPathElement));
        String c = b.getAttribute("value");
        logger.info(c);
        return c;
    }

    public void keysBoardWithDOWN(String element1) throws InterruptedException {
        String xPathElement1 = PropertiesFile.getPropValue(element1);

        if (xPathElement1 == null) {
            xPathElement1 = element1;
        }
        WebElement tutorial = driver.findElement(By.xpath(xPathElement1));
        Actions act = new Actions(driver);
        act.moveToElement(tutorial).build().perform();
        act.contextClick(tutorial).sendKeys(Keys.ARROW_DOWN).build().perform();
    }

    public void verifyAttributeValues(String expect, String elementGetValue) {
        // getAttribute() to get value as displayed in GUI // no value attribute for the field in the DOM.
        String xPathElement1 = PropertiesFile.getPropValue(elementGetValue);
        String xPathElement2 = PropertiesFile.getPropValue(expect);
        if (xPathElement1 == null) {
            xPathElement1 = elementGetValue;
        }
        if (xPathElement2 == null) {
            xPathElement2 = expect;
        }
        String valueElement = driver.findElement(By.xpath(xPathElement1)).getAttribute("value");
        Assert.assertEquals(valueElement, xPathElement2);
    }
    public void clearLocalStorage() {
        logger.info("clearLocalStorage");
        JavascriptExecutor js = ((JavascriptExecutor) driver);
        js.executeScript("localStorage.removeItem(\"mage-cache-storage\")");
    }
    public void scrollToTheBottomPage() {
        logger.info("scrollDownToElementWithJavaExecutor");
        JavascriptExecutor js = ((JavascriptExecutor) driver);
        js.executeScript("window.scrollTo(0, document.body.scrollHeight)");
    }
}

