package pages.home;
import core.BasePage;
import driver.DriverManager;
import helpers.DataBase;
import helpers.LogHelper;
import io.qameta.allure.Step;
import locator.Locator;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.slf4j.Logger;
import org.testng.Assert;
import pages.loginsignup.LoginPage;
import java.util.HashMap;
import java.util.List;
import static constant.Constant.*;
import static constant.Query.*;
import static utilities.DateTime.*;


public class SearchPage extends BasePage {
    private static Logger logger = LogHelper.getLogger();
    public LoginPage loginPage;
    public DataBase dataBase ;

    public SearchPage() {
        loginPage = new LoginPage();
        dataBase = new DataBase();
    }

    @Step("Click Tìm kiếm")
    public void search() {
        logger.info("Click tìm kiếm");
        keyword.webDriverWaitForElementPresent(Locator.HOME_BTN_SEARCH, 10);
        keyword.click(Locator.HOME_BTN_SEARCH);
    }

    @Step("Nhập nội dung cần tìm: {0}")
    public void inputSearch(String content) {
        logger.info("Nhập nội dung cần tìm");
        keyword.sleep(0.5);
        keyword.clearTextAndSendKey(Locator.SEARCH_TXT_INPUT, content);
    }

    @Step("Chọn tag tất cả")
    public void selectTagAll() {
        logger.info("Chọn tag tất cả");
        keyword.sleep(0.5);
        keyword.click(Locator.SEARCH_BTN_TAG_ALL);
    }

    @Step("Chọn ngẫu nhiên {0}")
    public String selectRandom(String flag) {
        List<WebElement> webList;
        if(flag.equals("tag")) {
            webList = keyword.getListElement(Locator.SEARCH_BTN_TAG);
        }
        else {
            webList = keyword.getListElement(Locator.SEARCH_LBL_TITLE_RESULT);
        }
        int randNumber = keyword.randomNumber(webList.size());
        webList.get(randNumber).click();
        return webList.get(randNumber).getText();
    }

    @Step("Chọn nội dung")
    public void selectSearch() {
        logger.info("Chọn nội dung");
        keyword.webDriverWaitForElementPresent(Locator.SEARCH_LBL_TITLE_RESULT,10);
        keyword.click(Locator.SEARCH_LBL_TITLE_RESULT);
    }

    @Step("Không hiển thị kết quả")
    public void noResultFound() {
        keyword.verifyElementPresent(Locator.SEARCH_LBL_NO_RESULT);
        keyword.assertEqual(Locator.SEARCH_LBL_NO_RESULT, MESS_NO_RESULT_FOUND);
        keyword.verifyElementPresent(Locator.SEARCH_LBL_FIND_OTHER_RESULT);
        keyword.assertEqual(Locator.SEARCH_LBL_FIND_OTHER_RESULT, MESS_FIND_OTHER_RESULT);
    }

    @Step("Lấy danh sách kết quả: {0}")
    public List<WebElement> getListResult() {
        logger.info("Lấy danh sách kết quả");
        List<WebElement> weblist = keyword.getListElement(Locator.SEARCH_LBL_TITLE_RESULT);
        return weblist;
    }

    @Step("Kiểm tra kết quả tìm kiếm: {0}")
    public void checkResult(String flag, String content) {
        logger.info("Kiểm tra kết quả hiển thị");
        keyword.sleep(0.5);
        List<WebElement> webList = keyword.getListElement(Locator.SEARCH_LBL_TITLE_RESULT);
        if (flag.equals("tuyệt đối")) {
            Assert.assertTrue(webList.get(0).getText().equals(content));
        }
        else {
            Boolean check = false;
            for (int i = 0; i < webList.size(); i++) {
                System.out.println(webList.get(i).getText());
                if (webList.get(i).getText().contains(content)) {
                    check = true;
                    break;
                }
            }
            Assert.assertTrue(check);
        }
    }

    @Step("Kiểm tra kết quả từ tag đã chọn")
    public void checkTagResult(String idTag, List<WebElement> listResult) {
        logger.info("Kiểm tra kết quả từ tag đã chọn");
        HashMap<String, String> data = new HashMap<>();
        for (int i = 0; i < listResult.size(); i++) {
            data = dataBase.queryAndGetDb(SPORT_TV_QUERY_EVENT_TV_NAME, listResult.get(i).getText());
            System.out.println("ID: " + data.get("id"));
            data = dataBase.queryAndGetDb(SPORTS_QUERY_EVENT_TV_SCREEN_BLOCK, data.get("id"));
            keyword.assertEqualData(idTag, data.get("screenblock_id"));
        }
        data.clear();
    }
    @Step("Hiện thị thông báo cần đăng nhập")
    public void showNotice() {
        keyword.assertEqual(Locator.MENU_LBL_LOGIN_NOTICE, MESSAGE_LOGIN_NOTICE);
    }

    @Step("Đăng nhập để sử dụng tính năng : {0}")
    public void confirmLoginToUseFeature(String flag) {
        if (flag.equals("yes")) {
            keyword.click(Locator.LOGIN_BTN_ACCEPT);
        } else {
            keyword.click(Locator.LOGOUT_BTN_CANCEL);
        }
    }

    @Step("Ẩn video")
    public void floatingVideo() {
        keyword.sleep(1);
        keyword.webDriverWaitInvisibleElement(Locator.LOGIN_TOAST_SUCCESS, 10);
        keyword.scrollDownTo(100, 2000);
        keyword.sleep(1);
    }

    @Step("Back")
    public void backSearch() {
        keyword.click(Locator.SEARCH_BTN_BACK);
        keyword.webDriverWaitForElementPresent(Locator.HOME_BTN_SEARCH, 10);
    }

    @Step("Không hiện nút play")
    public void invisiblePlayButton() {
        keyword.verifyElementDisplay(Locator.VIEW_VIDEO_BTN_PLAY, false);
    }

    @Step("Tắt video")
    public void closeVideo() {
        keyword.click(Locator.VIEW_VIDEO_BTN_CLOSE);
    }

    @Step("Xem full màn hình")
    public void fullScreen() {
        keyword.click(Locator.PLAYER_VIEW_BTN_FULL_SCREEN);
    }

    @Step("Đợi đến khi show video")
    public void waitBtnPlay() {
        keyword.webDriverWaitForElementPresent(Locator.PLAYER_VIEW_BTN_PLAY, 10);
    }
    @Step("Đợi đến khi show video")
    public void waitBtnPause() {
        keyword.webDriverWaitForElementPresent(Locator.PLAYER_VIEW_BTN_PAUSE, 10);
    }
    @Step("Play video")
    public void pauseVideo() {
        keyword.click(Locator.PLAYER_VIEW_BTN_PAUSE);
    }
    @Step("Play video")
    public void playVideo() {
        keyword.click(Locator.PLAYER_VIEW_BTN_PLAY);
    }

    @Step("Tua lại video")
    public void rewindVideo() {
        keyword.click(Locator.PLAYER_VIEW_BTN_REWIND);
    }

    @Step("Tua video")
    public void forwardVideo() {
        keyword.click(Locator.PLAYER_VIEW_BTN_FORWARD);
    }

    @Step("Lấy thời gian đang play của video")
    public String getTimeLine() {
        return keyword.getText(Locator.PLAYER_VIEW_LBL_TIME_LINE);
    }

    @Step("Lấy thời gian của video")
    public String getDurationTime() {
        return keyword.getText(Locator.PLAYER_VIEW_LBL_TIME_DURATION);
    }

    @Step("Kiểm tra thời gian thay đổi khi xem video")
    public void checkTimePlay() {
        String startTime = getTimeLine();
        keyword.sleep(3);
        keyword.click(Locator.PLAYER_VIEW_BTN_FRAME_SCREEN);
        String endTime = getTimeLine();
        keyword.assertTrue(getSeconds(endTime) - getSeconds(startTime) > 3);
    }
    @Step("Kiểm tra thời gian không thay đổi khi dừng xem video")
    public void checkTimeAfterStopVideo() {
        keyword.click(Locator.PLAYER_VIEW_BTN_FRAME_SCREEN);
        pauseVideo();
        String startTime = getTimeLine();
        keyword.sleep(3);
        String endTime = getTimeLine();
        keyword.assertTrue(getSeconds(endTime) == getSeconds(startTime) || getSeconds(endTime) - getSeconds(startTime) == 1);
    }
    @Step("Kiểm tra thời gian thay đổi khi tua forward video")
    public void checkTimeAfterForwardVideo() {
        String startTime = getTimeLine();
        forwardVideo();
        String endTime = getTimeLine();
        keyword.assertTrue(getSeconds(endTime) - getSeconds(startTime) > 9);
    }
    @Step("Kiểm tra thời gian thay đổi khi tua back video")
    public void checkTimeAfterBackVideo() {
        String startTime = getTimeLine();
        keyword.click(Locator.PLAYER_VIEW_BTN_FRAME_SCREEN);
        rewindVideo();
        String endTime = getTimeLine();
        keyword.assertTrue(getSeconds(startTime) - getSeconds(endTime) > 7);
    }
    @Step("Chọn live video")
    public void chooseLiveVideo() {
        keyword.webDriverWaitForElementPresent(Locator.CONTENT_LBL_LIVE, 10);
        keyword.sleep(0.5);
        List<WebElement> webList = DriverManager.getDriver().findElements(By.xpath(Locator.CONTENT_IMG_LIVE));
        int random = keyword.randomNumber(webList.size());
        webList.get(random).click();
        keyword.sleep(0.5);
    }
}

