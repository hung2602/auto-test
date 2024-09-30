package keyword;
import helpers.PropertiesFile;
import io.qameta.allure.Step;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.*;
import org.slf4j.Logger;
import org.testng.Assert;
import utilities.LogHelper;
import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;
import java.util.function.Function;
import static core.BaseTest.driver;

public class KeywordWeb {
    private static Logger logger = LogHelper.getLogger();
    public KeywordWeb() {
    }
    public void sleep(double second) {
        try {
            Thread.sleep((long) (1000 * second));
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
    @Step("Nhập giá trị: {0}  text: {1}")
    public void sendKeys(By by, String key){
        webDriverWaitForElementPresent(by, 20);
        String content = PropertiesFile.getPropValue(key);
        logger.info("Send key " + by);
        if (content == null) {
            content = key;
        }
        driver.findElement(by).sendKeys(content);
    }
    @Step("Cuộn tới vị trí: {0}")
    public void scrollToPositionByScript(String jsScript) {
        logger.info(" scrolling to position ");
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript(jsScript);
    }
    @Step("Lấy giá trị: {0}")
    public String getText(By by){
        logger.info("Get Text " + by);
        return driver.findElement(by).getText();
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
    @Step("So sánh data: {0} với giá trị: {1}")
    public void assertEqualData(String db, String expect){
        logger.info("Compare message: " + db + " with " + expect);
        Assert.assertEquals(db, expect);
    }
    @Step("So sánh data: {0} với giá trị: {1}")
    public void assertEqualMultiData(String db, String expect){
        logger.info("Compare message: " + db + " with " + expect);
        String[] arrDb = db.split(",");
        String[] arrExp = db.split(",");
        for (int i = 0; i < arrExp.length; i++) {
            Assert.assertEquals(arrDb[i], arrExp[i]);
        }
    }
    @Step("Chờ element hiển thị: {0}")
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
    @Step("Xác thực element hiển thị: {0}")
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
    @Step("Lấy danh sách elementị: {0}")
    public List<WebElement> getListElement(By by) {
        logger.info("get list element: " + by);
        webDriverWaitForElementPresent(by, 20);
        List<WebElement> weblist = driver.findElements(by);
        return weblist;
    }

    public void executeJsHorizontal(String command, WebElement element) {
        logger.info("Executing JavaScript");
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript(command, element);
    }
    @Step("Chọn từ drop list: {1}")
    public void selectByText(By by, String text){
        logger.info("Select By Text " + by);
        Select singleSelect = new Select(driver.findElement(by));
        singleSelect.selectByVisibleText(text);
    }
    @Step("Xóa: {0} và nhập giá trị {1}")
    public void clearTextAndSendKey(By by, String contents){
        logger.info("Clear and send keys" + by + "with " + contents);
        webDriverWaitForElementPresent(by, 20);
        String content = PropertiesFile.getPropValue(contents);
        if (content == null) {
            content = contents;
        }
        driver.findElement(by).clear();
        driver.findElement(by).sendKeys(content);
    }
    @Step("Xóa giá trị: {0}")
    public void clearText(By by) {
        logger.info("clearText");
        driver.findElement(by).clear();
    }
    @Step("Xác thực element có thể tương tác: {0}")
    public boolean isElementEnable(By by) {
        logger.info("verifyElementPresent: " + by);
        try {
            driver.findElement(by).isEnabled();
            return true;
        } catch (NoSuchElementException e) {
            e.printStackTrace();
            return false;
        }
    }
    @Step("Lấy ngẫu nhiên element: {0}")
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
    @Step("Double click: {0}")
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
    @Step("Xác thực element hiển thị: {0}")
    public boolean isDisplayElement(By by) {
        logger.info("Check element display " + by );
        boolean stt = driver.findElement(by).isDisplayed();
        return stt;
    }
    @Step("Lấy giá trị alert: {0}")
    public String getAlertText() {
        logger.info("Getting alert text...");
        Alert alert = driver.switchTo().alert();
        return alert.getText();
    }
    @Step("Chuyển sang web context: {0}")
    public boolean switchToWebContext() {
        ArrayList<String> contexts = new ArrayList<>(driver.getContextHandles());
        for (String context : contexts) {
            System.out.println(context);
            if(context.contains("WEBVIEW")){   // NATIVE_APP
                driver.context(context);
                return true;
            }
        }
        return false;
    }


    public void addDataToList(List<String> list,String element){
        String data = PropertiesFile.getPropValue(element);
        if (data == null) {
            data = element;
        }
        list.add(data);
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
    public void acceptAlert() {
        logger.info("Accepting alert...");
        Alert alert = driver.switchTo().alert();
        alert.accept();
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
            Assert.assertTrue(false);
        } catch (NoSuchElementException e) {
            e.printStackTrace();
            Assert.assertTrue(true);
        }
    }
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
    public void scrollToTheBottomPage() {
        logger.info("scrollDownToElementWithJavaExecutor");
        JavascriptExecutor js = ((JavascriptExecutor) driver);
        js.executeScript("window.scrollTo(0, document.body.scrollHeight)");
    }
}

