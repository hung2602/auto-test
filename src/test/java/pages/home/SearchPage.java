package pages.home;

import core.BasePage;
import helpers.DataBase;
import helpers.LogHelper;
import io.qameta.allure.Step;
import locator.Locator;
import org.openqa.selenium.WebElement;
import org.slf4j.Logger;
import org.testng.Assert;
import pages.loginsignup.LoginPage;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;
import static constant.Constant.*;

public class SearchPage extends BasePage {
    private static Logger logger = LogHelper.getLogger();
    private HashMap<String, String> dbData;
    public LoginPage loginPage;
    public SearchPage() {
        loginPage = new LoginPage();
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
        keyword.sleep(0.3);
        keyword.clearTextAndSendKey(Locator.SEARCH_TXT_INPUT, content);
    }
    @Step("Chọn tag tất cả")
    public void selectTagAll() {
        logger.info("Chọn tag tất cả");
        keyword.sleep(0.5);
        keyword.click(Locator.SEARCH_BTN_TAG_ALL);
    }
    @Step("Chọn ngẫu nhiên 1 tag")
    public String selectRandomATag() {
        List<WebElement> webList = keyword.getListElement(Locator.SEARCH_BTN_TAG);
        int randNumber = ThreadLocalRandom.current().nextInt(1, webList.size());
        webList.get(randNumber).click();
        return webList.get(randNumber).getText();
    }
    @Step("Chọn nội dung")
    public void selectSearch(){
        logger.info("Chọn nội dung");
        keyword.click(Locator.SEARCH_LBL_TITLE_RESULT);
    }
    @Step("Không hiển thị kết quả")
    public void noResultFound(){
        keyword.verifyElementPresent(Locator.SEARCH_LBL_NO_RESULT);
        keyword.assertEqual(Locator.SEARCH_LBL_NO_RESULT, MESS_NO_RESULT_FOUND);
        keyword.verifyElementPresent(Locator.SEARCH_LBL_FIND_OTHER_RESULT);
        keyword.assertEqual(Locator.SEARCH_LBL_FIND_OTHER_RESULT, MESS_FIND_OTHER_RESULT);
    }
    @Step("Lấy danh sách kết quả: {0}")
    public List<WebElement> getListResult(){
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
    public void checkTagResult(String tagName, DataBase dataBase){
        logger.info("Kiểm tra kết quả từ tag đã chọn");
        List<WebElement> webList = keyword.getListElement(Locator.SEARCH_LBL_TITLE_RESULT);
        dbData = dataBase.queryAndGetDb("SPORT_TV_QUERY_SCREEN_BLOCK", tagName);
        String idTag = dbData.get("id");
        for (int i = 0; i < webList.size(); i++) {
            dbData = dataBase.queryAndGetDb("SPORT_TV_QUERY_EVENT_TV_NAME", webList.get(i).getText());
            dbData = dataBase.queryAndGetDb("SPORTS_QUERY_EVENT_TV_SCREEN_BLOCK", dbData.get("id"));
            keyword.assertEqualData(idTag, dbData.get("screenblock_id"));
        }
    }
    @Step("Đăng nhập để sử dụng tính năng : {0}")
    public void confirmLoginToUseFeature(String flag){
        keyword.assertEqual(Locator.MENU_LBL_LOGIN_NOTICE, MESSAGE_LOGIN_NOTICE);
        if (flag.equals("yes")){
            keyword.click(Locator.LOGIN_BTN_ACCEPT);
        }
        else {
            keyword.click(Locator.LOGOUT_BTN_CANCEL);
        }
    }
    @Step("Ẩn video")
    public void scrollHiddenVideo(){
        keyword.sleep(1);
        keyword.webDriverWaitInvisibleElement(Locator.LOGIN_TOAST_SUCCESS,10);
        keyword.scrollDownTo(100, 500);
    }
    @Step("Back")
    public void backSearch(){
        keyword.click(Locator.SEARCH_BTN_BACK);
        keyword.webDriverWaitForElementPresent(Locator.HOME_BTN_SEARCH,10);
    }
    @Step("Không hiện nút play")
    public void invisiblePlayButton(){
        keyword.verifyElementDisplay(Locator.VIEW_VIDEO_BTN_PLAY, false);
    }
    @Step("Tắt video")
    public void closeVideo(){
        keyword.click(Locator.VIEW_VIDEO_BTN_CLOSE);
    }
}

