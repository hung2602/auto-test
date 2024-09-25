package pages;
import core.BasePage;
import core.KeywordWeb;
import core.PropertiesFile;
import io.qameta.allure.Step;
import locator.Locator;
import constant.Constant;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.List;

public class LoginPage extends BasePage {
    public LoginPage(KeywordWeb key) {
        super(key);
    }

    @Step("Đăng nhập thành công")
    public void loginSuccess() {
        keyword.click(Locator.HOME_BTN_MENU);
        keyword.click(Locator.LOGIN_BTN);
        inputUserName("PHONE_NUMBER");
        inputPassWord("PASS_WORD");
    }
    @Step("Nhập tên số điện thoại: {0}")
    public void inputUserName(String name){
        keyword.sendKeys(Locator.LOGIN_TXT_USER_NAME,name);
        keyword.click(Locator.LOGIN_BTN_CONTINUE);
    }
    @Step("Nhập mật khẩu: {0}")
    public void inputPassWord(String pass){
        keyword.sendKeys(Locator.LOGIN_TXT_PASSWORD,pass);
        keyword.click(Locator.LOGIN_BTN_CONTINUE);
    }
    @Step("Vào màn hình đăng nhập")
    public void goToLogin() {
        keyword.click(Locator.HOME_BTN_MENU);
        keyword.click(Locator.LOGIN_BTN);
    }
    public void compareMessLoginSuccess(){
        keyword.assertEqual(Locator.LOGIN_TOAST_SUCCESS, Constant.MESSAGE_SUCCESS_LOGIN);
    }
    public void loginIncorrectPass(){
        keyword.assertEqual(Locator.LOGIN_TOAST_INCORRECT_PASSWORD, Constant.MESSAGE_FAIL_LOGIN);
    }
    @Step("Hiển thị text ẩn {0}")
    public void checkHiddenText(By locator, String text){
        keyword.assertEqual(locator, text);
    }
    @Step("Xem thông tin tài khoản")
    public void viewUserInform(){
        keyword.webDriverWaitInvisibleElement(Locator.LOGIN_TOAST_SUCCESS,10);
        keyword.click(Locator.MENU_BTN_LAYOUT_AVATAR);
    }
    @Step("Đăng xuất: {0}")
    public void logOut(String flag){
        keyword.click(Locator.LOGOUT_BTN);
        if(flag.equals("thành công")) {
            keyword.click(Locator.LOGOUT_BTN_CONFIRM);
            keyword.assertEqual(Locator.LOGOUT_TOAST_SUCCESS, Constant.MESSAGE_SUCCESS_LOGOUT);
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
        }
        keyword.click(Locator.LOGIN_BTN_ACCEPT);
        inputUserName("PHONE_NUMBER");
        inputPassWord("PASS_WORD");
        compareMessLoginSuccess();
    }
    @Step("Hiển thị màn {0}")
    public void checkScreenInform(String flag) {
        keyword.webDriverWaitInvisibleElement(Locator.LOGIN_TOAST_SUCCESS,10);
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
        }
        keyword.click(Locator.LOGIN_SMART_TV_BTN_BACK);
    }
    @Step("Nhập mã otp: {1} ")
    public void inputOtp(String otp, String flag){
        otp = PropertiesFile.getPropValue(otp);
        List<WebElement> weblist = keyword.getListElement(Locator.SIGN_UP_TXT_EDIT_OPT);
        for (int i = 0; i < weblist.size(); i++) {
            weblist.get(i).sendKeys(otp.split("")[i]);
        }
        keyword.click(Locator.LOGIN_BTN_CONTINUE);
        if(flag.equals("không tồn tại")){
            keyword.webDriverWaitForElementPresent(Locator.LOGIN_TOAST_INCORRECT_PASSWORD,10);
            keyword.assertEqual(Locator.LOGIN_TOAST_INCORRECT_PASSWORD,Constant.MESSAGE_INVALID_OTP);
        }
        else {
            keyword.webDriverWaitForElementPresent(Locator.LOGIN_TXT_PASSWORD,10);
        }
    }
    @Step("Đợi đến khi otp hết hạn")
    public void waitTimeOtp(String otp, String flag){
        for (int i = 0; i < 10; i++) {
            keyword.sleep(5);
            keyword.click(Locator.LBL_TIME_EXPIRED_OTP);
        }
        keyword.webDriverWaitForElementPresent(Locator.SIGN_UP_BTN_RESEND_OTP,10);
        keyword.assertEqual(Locator.LBL_TIME_EXPIRED_OTP, Constant.MESSAGE_EXPIRED_OTP);
    }
    @Step("Gửi lại mã opt")
    public void resendOtp(){
        keyword.click(Locator.SIGN_UP_BTN_RESEND_OTP);
    }

    @Step("Đăng xuất khỏi thiết bị {0}")
    public void logOutDevice(String option){
        keyword.click(Locator.DEVICE_MANAGE_BTN_LAYOUT_DEVICE);
        keyword.click(Locator.DEVICE_MANAGE_BTN_LOGOUT_DEVICE);
        if (option.equals("thành công")){
            keyword.click(Locator.LOGOUT_BTN_CONFIRM);
            keyword.assertEqual(Locator.LOGOUT_DEVICE_TOAST_SUCCESS, Constant.MESSAGE_LOGOUT_SUCCESS_DEVICE);
        }
        else {
            keyword.click(Locator.LOGOUT_BTN_CANCEL);
        }
    }
}

