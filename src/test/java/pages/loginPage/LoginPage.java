package pages.loginPage;
import core.BasePage;
import core.KeywordWeb;
import core.PropertiesFile;
import io.qameta.allure.Step;
import locator.Locator;
import constant.Constant;
import org.openqa.selenium.By;

import static core.MyListener.saveScreenshotPNG;

public class LoginPage extends BasePage {
    public LoginPage(KeywordWeb key) {
        super(key);
    }

    @Step("Nhập tên đăng nhập: {0}")
    public void inputUserName(String name){
        keyword.sendKeys(Locator.LOGIN_TXT_USER_NAME,name);
        keyword.click(Locator.LOGIN_BTN_CONTINUE);
    }
    @Step("Nhập mật khẩu: {0}")
    public void inputPassWord(String pass){
        keyword.sendKeys(Locator.LOGIN_TXT_PASSWORD,pass);
        keyword.click(Locator.LOGIN_BTN_CONTINUE);
    }
    @Step("Vào màn hình login")
    public void login(String userName, String passWord) {
        keyword.click(Locator.HOME_BTN_MENU);
        keyword.click(Locator.LOGIN_BTN);
        inputUserName(userName);
        inputPassWord(passWord);
    }
    public void loginSuccess(){
        keyword.assertEqual(Locator.LOGIN_MESS_SUCCESS, Constant.MESSAGE_SUCCESS_LOGIN);
    }
    public void loginIncorrectPass(){
        keyword.assertEqual(Locator.LOGIN_MESS_INCORRECT_PASSWORD, Constant.MESSAGE_FAIL_LOGIN);
    }
    @Step("Hiển thị text ẩn {0}")
    public void checkHiddenText(By locator, String text){
        keyword.assertEqual(locator, text);
//        keyword.assertEqual(Locator.LOGIN_TXT_USER_NAME, Constant.TEXT_BOX_USERNAME);
//        keyword.assertEqual(Locator.LOGIN_TXT_PASSWORD, Constant.TEXT_BOX_PASSWORD);
    }
    @Step("Xem thông tin tài khoản")
    public void viewUserInform(){
        keyword.webDriverWaitInvisibleElement(Locator.LOGIN_MESS_SUCCESS,10);
        keyword.click(Locator.MENU_BTN_LAYOUT_AVATAR);
    }
    @Step("Đăng xuất: {0}")
    public void logOut(String flag){
        keyword.click(Locator.LOGOUT_BTN);
        if(flag.equals("thành công")) {
            keyword.click(Locator.LOGOUT_BTN_CONFIRM);
            keyword.assertEqual(Locator.LOGOUT_MESS_SUCCESS, Constant.MESSAGE_SUCCESS_LOGOUT);
        }
        else {
            keyword.click(Locator.LOGOUT_BTN_CANCEL);
        }
    }
    @Step("Đăng nhập qua: {0}")
    public void logInBy(String flag){
        keyword.click(Locator.HOME_BTN_MENU);
        switch (flag) {
            case "Smart Tv, Website":
                keyword.click(Locator.SETTING_BTN_SMART_TV);
                break;
            case "Quản lý thiết bị":
                keyword.click(Locator.SETTING_BTN_QUAN_LY);
                break;
            case "Thông báo":
                keyword.click(Locator.SETTING_BTN_THONG_BAO);
                break;
            default:
        }
        keyword.click(Locator.LOGIN_BTN_ACCEPT);
        inputUserName("PHONE_NUMBER");
        inputPassWord("PASS_WORD");
        loginSuccess();
    }
    @Step("Hiển thị màn {0}")
    public void checkScreenInform(String flag) {
        keyword.webDriverWaitInvisibleElement(Locator.LOGIN_MESS_SUCCESS,10);
        switch (flag) {
            case "Smart Tv, Website":
                keyword.assertEqual(Locator.LOGIN_SMART_TV_LBL, Constant.TITLE_LOGIN_SMART_TV);
                keyword.verifyElementPresent(Locator.LOGIN_SMART_TV_BTN_QR);
                keyword.verifyElementPresent(Locator.LOGIN_SMART_TV_TXT_OTP);
                break;
            case "Quản lý thiết bị":
                keyword.assertEqual(Locator.LOGIN_SMART_TV_LBL, Constant.TITLE_DEVICE_MANAGE);
                keyword.verifyElementPresent(Locator.DEVICE_MANAGE_BTN_LOGOUT_DEVICE);
                keyword.verifyElementPresent(Locator.DEVICE_MANAGE_BTN_LAYOUT_DEVICE);
                break;
            case "Thông báo":
                keyword.assertEqual(Locator.LOGIN_SMART_TV_LBL, Constant.TITLE_INFORM_SETTING);
                keyword.verifyElementPresent(Locator.SET_INFORM_BTN_COMPLETE);
                break;
            default:
        }
        keyword.click(Locator.LOGIN_SMART_TV_BTN_BACK);
    }
}

