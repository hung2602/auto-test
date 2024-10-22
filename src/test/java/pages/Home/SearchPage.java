package pages.Home;

import core.BasePage;
import helpers.DataBase;
import helpers.LogHelper;
import io.qameta.allure.Step;
import locator.Locator;
import org.openqa.selenium.WebElement;
import org.slf4j.Logger;
import org.testng.Assert;
import pages.LoginSignUp.LoginPage;

import java.util.List;

import static constant.Constant.*;

public class SearchPage extends BasePage {
    private static Logger logger = LogHelper.getLogger();
    public LoginPage loginPage;
    public SearchPage() {
        loginPage = new LoginPage();
    }
    @Step("Click Tìm kiếm")
    public void search(){
        logger.info("Click tìm kiếm");
        keyword.webDriverWaitForElementPresent(Locator.HOME_BTN_SEARCH, 10);
        keyword.click(Locator.HOME_BTN_SEARCH);
    }
    @Step("Nhập nội dung cần tìm: {0}")
    public void inputSearch(String content){
        logger.info("Nhập nội dung cần tìm");
        keyword.sleep(0.3);
        keyword.clearTextAndSendKey(Locator.SEARCH_TXT_INPUT, content);
    }
    @Step("Chọn nội dung")
    public void selectSearch(){
        logger.info("Chọn nội dung");
        keyword.sleep(1);
        keyword.click(Locator.SEARCH_LBL_TITLE_RESULT);
    }

    @Step("Không hiển thị kết quả")
    public void noResultFound(){
        keyword.verifyElementPresent(Locator.SEARCH_LBL_NO_RESULT);
        keyword.assertEqual(Locator.SEARCH_LBL_NO_RESULT, MESS_NO_RESULT_FOUND);
        keyword.verifyElementPresent(Locator.SEARCH_LBL_FIND_OTHER_RESULT);
        keyword.assertEqual(Locator.SEARCH_LBL_FIND_OTHER_RESULT, MESS_FIND_OTHER_RESULT);
    }
    @Step("Kiểm tra kết quả hiển thị: {0}")
    public void checkResult(String content){
        logger.info("Kiểm tra kết quả hiển thị");
        keyword.sleep(1);
        Boolean check = false;
        List<WebElement> weblist = keyword.getListElement(Locator.SEARCH_LBL_TITLE_RESULT);
        for (int i = 0; i < weblist.size(); i++) {
            if(weblist.get(i).getText().contains(content)){
                check = true;
                break;
            }
        }
        Assert.assertTrue(check);
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
    @Step("Đăng nhập để sử dụng tính năng : {0}")
    public void scrollHiddenVideo(){
        keyword.sleep(1);
        keyword.webDriverWaitInvisibleElement(Locator.LOGIN_TOAST_SUCCESS,10);
        keyword.scrollDownTo(100, 500);
        keyword.click(Locator.VIEW_VIDEO_BTN_CLOSE);
    }




}
