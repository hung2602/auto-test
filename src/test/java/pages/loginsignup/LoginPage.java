package pages.loginsignup;
import core.BasePage;
import core.DataBase;
import helpers.LogHelper;
import io.qameta.allure.Step;
import locator.Locator;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.slf4j.Logger;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;
import static constant.Constant.*;

public class LoginPage extends BasePage {
    private static Logger logger = LogHelper.getLogger();
    public DataBase dataBase ;
    public LoginPage() {
        dataBase = new DataBase();
    }
    @Step("Hiển thị text ẩn {0}")
    public void checkHiddenText(By locator, String text){
        keyword.webDriverWaitForElementPresent(locator, 10);
        keyword.clearText(locator);
        keyword.sleep(0.3);
        keyword.assertEqual(locator, text);
    }
    @Step("Tài khoản đã logout")
    public void isUserLogout() {
        keyword.click(Locator.HOME_BTN_MENU);
        if (keyword.verifyElementPresent(Locator.PROFILE_BTN_VIEW_USER_INFORM)) {
            viewUserInform();
            logOut("Thành công");
        } else {
            keyword.click(Locator.HOME_BTN_HOME);
        }
    }
    public String getPhoneNumber(){
        String number = "0363";
        int ranNum = ThreadLocalRandom.current().nextInt(100000,999999);
        logger.info("Get phone number " + ranNum);
        return number + ranNum;
    }
    @Step("Vào màn hình đăng nhập")
    public void goToLogin() {
        logger.info("goToLogin ");
        keyword.click(Locator.HOME_BTN_MENU);
        keyword.click(Locator.LOGIN_BTN);
    }
    @Step("Click quay lại")
    public void goBack() {
        keyword.click(Locator.LOGIN_SMART_TV_BTN_BACK);
    }
    @Step("Đăng nhập thành công")
    public void login(String phoneNumber, String passWord) {
        logger.info("loginSuccess ");
        inputUserName(phoneNumber);
        inputPassWord(passWord);
        isMore3Devices();
    }
    @Step("Nhập số điện thoại: {0}")
    public void inputUserName(String name){
        logger.info("inputUserName ");
        keyword.clearTextAndSendKey(Locator.LOGIN_TXT_USER_NAME, name);
        keyword.click(Locator.LOGIN_BTN_CONTINUE);
    }
    @Step("Nhập mật khẩu: {0}")
    public void inputPassWord(String pass){
        logger.info("inputPassWord ");
        keyword.clearTextAndSendKey(Locator.LOGIN_TXT_PASSWORD,pass);
        keyword.click(Locator.LOGIN_BTN_CONTINUE);
    }
    public void compareMessLoginSuccess(){
        keyword.webDriverWaitForElementPresent(Locator.LOGIN_TOAST_SUCCESS, 10);
//        keyword.sleep(1);
        keyword.assertEqual(Locator.LOGIN_TOAST_SUCCESS, MESSAGE_SUCCESS_LOGIN);
    }
    public void compareMessLoginIncorrectPass(String phone){
        keyword.assertEqual(Locator.LOGIN_TOAST_INCORRECT_PASSWORD, MESSAGE_FAIL_LOGIN + phone);
        keyword.webDriverWaitInvisibleElement(Locator.LOGIN_TOAST_INCORRECT_PASSWORD,10);
    }
    public void isMore3Devices(){
        if(keyword.verifyElementPresent(Locator.NOTICE_LBL_MORE_3_DEVICE))
        {
            keyword.assertEqual(Locator.NOTICE_LBL_MORE_3_DEVICE, MESS_NOTICE_MORE_3_DEVICES);
            keyword.click(Locator.LOGOUT_BTN_CONFIRM);
        }
    }
    @Step("Xem thông tin tài khoản")
    public void viewUserInform(){
        logger.info("viewUserInform ");
        keyword.webDriverWaitInvisibleElement(Locator.LOGIN_TOAST_SUCCESS,10);
        keyword.click(Locator.PROFILE_BTN_VIEW_USER_INFORM);
    }
    @Step("Đăng xuất: {0}")
    public void logOut(String flag){
        logger.info("logOut ");
        keyword.click(Locator.LOGOUT_BTN);
        if(flag.equals("Thành công")) {
            keyword.click(Locator.LOGOUT_BTN_CONFIRM);
            keyword.assertEqual(Locator.LOGOUT_TOAST_SUCCESS, MESSAGE_SUCCESS_LOGOUT);
            keyword.webDriverWaitInvisibleElement(Locator.LOGOUT_TOAST_SUCCESS,15);
        }
        else {
            keyword.click(Locator.LOGOUT_BTN_CANCEL);
        }
    }
    @Step("Đăng nhập qua: {0}")
    public void logInBy(String flag, String phone, String passWord){
        logger.info("logInBy ");
        keyword.click(Locator.HOME_BTN_MENU);
        switch (flag) {
            case "Smart Tv, Website":
                keyword.click(Locator.MENU_BTN_SMART_TV);
                break;
            case "Quản lý thiết bị":
                keyword.click(Locator.MENU_BTN_QUAN_LY);
                break;
            case "Thông báo":
                keyword.click(Locator.MENU_BTN_THONG_BAO);
                keyword.sleep(0.5);
                if (keyword.verifyElementPresent(Locator.NOT_ALLOW_BTN)){
                    keyword.click(Locator.ALLOW_BTN);
                }
                break;
        }
        keyword.assertEqual(Locator.MENU_LBL_LOGIN_NOTICE, MESSAGE_LOGIN_NOTICE);
        keyword.click(Locator.LOGIN_BTN_ACCEPT);
        login(phone, passWord);
        compareMessLoginSuccess();
    }
    @Step("Hiển thị màn {0}")
    public void checkScreenInform(String flag) {
        keyword.webDriverWaitInvisibleElement(Locator.LOGIN_TOAST_SUCCESS,10);
        switch (flag) {
            case "Smart Tv, Website":
                keyword.assertEqual(Locator.LOGIN_SMART_TV_LBL, TITLE_LOGIN_SMART_TV);
                keyword.verifyElementPresent(Locator.LOGIN_SMART_TV_BTN_QR);
                keyword.verifyElementPresent(Locator.LOGIN_SMART_TV_TXT_OTP);
                break;
            case "Quản lý thiết bị":
                keyword.assertEqual(Locator.LOGIN_SMART_TV_LBL, TITLE_DEVICE_MANAGE);
                keyword.verifyElementPresent(Locator.DEVICE_MANAGE_BTN_LOGOUT_DEVICE);
                keyword.verifyElementPresent(Locator.DEVICE_MANAGE_BTN_LAYOUT_DEVICE);
                break;
            case "Thông báo":
                keyword.assertEqual(Locator.LOGIN_SMART_TV_LBL, TITLE_INFORM_SETTING);
                keyword.verifyElementPresent(Locator.SET_INFORM_BTN_COMPLETE);
                break;
        }
        goBack();
    }
    @Step("Nhập mã otp: {0} ")
    public void inputOtp(String otp){
        System.out.println("OTP " + otp);
        List<WebElement> weblist = keyword.getListElement(Locator.SIGN_UP_TXT_EDIT_OPT);
        for (int i = 0; i < weblist.size(); i++) {
            weblist.get(i).sendKeys(otp.split("")[i]);
        }
    }
    @Step("Nhấn tiếp tục xác thực otp: {0} ")
    public void continueOtp(String flag){
        keyword.click(Locator.LOGIN_BTN_CONTINUE);
        if(flag.equals("hợp lệ")){
//            keyword.webDriverWaitForElementPresent(Locator.LOGIN_TOAST_INCORRECT_PASSWORD,15);
            keyword.webDriverWaitForElementPresent(Locator.LOGIN_TXT_PASSWORD,10);
        }
        else {
            keyword.assertEqual(Locator.LOGIN_TOAST_INCORRECT_PASSWORD, MESSAGE_INVALID_OTP);
        }
    }
    @Step("Xóa otp")
    public void deleteOtp(){
        List<WebElement> weblist = keyword.getListElement(Locator.SIGN_UP_TXT_EDIT_OPT);
        if(!weblist.get(0).getText().equals("")) {
            for (int i = 0; i < weblist.size(); i++) {
                weblist.get(i).clear();
            }
        }
    }
    @Step("Đăng xuất khỏi thiết bị: {0}")
    public void logOutDevice(String option){
        logger.info("logOutDevice ");
        keyword.click(Locator.DEVICE_MANAGE_BTN_LAYOUT_DEVICE);
        keyword.click(Locator.DEVICE_MANAGE_BTN_LOGOUT_DEVICE);
        if (option.equals("Thành công")){
            keyword.click(Locator.LOGOUT_BTN_CONFIRM);
            keyword.assertEqual(Locator.LOGOUT_DEVICE_TOAST_SUCCESS, MESSAGE_LOGOUT_SUCCESS_DEVICE);
            keyword.webDriverWaitInvisibleElement(Locator.LOGOUT_DEVICE_TOAST_SUCCESS,10);
        }
        else {
            keyword.click(Locator.LOGOUT_BTN_CANCEL);
        }
    }
    @Step("Đợi đến khi otp hết hạn")
    public void waitTimeOtp(){
        while (true){
            keyword.sleep(5);
            if(keyword.verifyElementPresent(Locator.SIGN_UP_BTN_RESEND_OTP)){
                break;
            }
            keyword.click(Locator.LBL_TIME_EXPIRED_OTP);
        }
        keyword.assertEqual(Locator.LBL_TIME_EXPIRED_OTP, MESSAGE_EXPIRED_OTP);
    }
    @Step("Gửi lại mã opt")
    public void resendOtp(){
        keyword.click(Locator.SIGN_UP_BTN_RESEND_OTP);
    }
}

