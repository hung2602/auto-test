package pages;
import core.BasePage;
import helpers.DataBase;
import helpers.PropertiesFile;
import io.qameta.allure.Step;
import locator.Locator;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import java.util.List;
import static constant.Constant.*;

public class LoginPage extends BasePage {
    public DataBase dataBase ;
    public LoginPage() {
        dataBase = new DataBase();
    }
    @Step("Đăng nhập thành công")
    public void loginSuccess(String phoneNumber, String passWord) {
        keyword.click(Locator.HOME_BTN_MENU);
        keyword.click(Locator.LOGIN_BTN);
        inputUserName(phoneNumber);
        inputPassWord(passWord);
    }
    @Step("Nhập số điện thoại: {0}")
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
    @Step("Click quay lại")
    public void goBack() {
        keyword.click(Locator.LOGIN_SMART_TV_BTN_BACK);
    }
    public void compareMessLoginSuccess(){
        keyword.assertEqual(Locator.LOGIN_TOAST_SUCCESS, MESSAGE_SUCCESS_LOGIN);
    }
    public void compareMessLoginIncorrectPass(){
        keyword.assertEqual(Locator.LOGIN_TOAST_INCORRECT_PASSWORD, MESSAGE_FAIL_LOGIN);
    }
    @Step("Hiển thị text ẩn {0}")
    public void checkHiddenText(By locator, String text){
        keyword.clearText(locator);
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
            keyword.assertEqual(Locator.LOGIN_TOAST_INCORRECT_PASSWORD, MESSAGE_INVALID_OTP);
        }
        else {
            keyword.webDriverWaitForElementPresent(Locator.LOGIN_TXT_PASSWORD,10);
        }
    }
    @Step("Đăng xuất khỏi thiết bị: {0}")
    public void logOutDevice(String option){
        keyword.click(Locator.DEVICE_MANAGE_BTN_LAYOUT_DEVICE);
        keyword.click(Locator.DEVICE_MANAGE_BTN_LOGOUT_DEVICE);
        if (option.equals("Thành công")){
            keyword.click(Locator.LOGOUT_BTN_CONFIRM);
            keyword.assertEqual(Locator.LOGOUT_DEVICE_TOAST_SUCCESS, MESSAGE_LOGOUT_SUCCESS_DEVICE);
        }
        else {
            keyword.click(Locator.LOGOUT_BTN_CANCEL);
        }
    }
    @Step("Đợi đến khi otp hết hạn")
    public void waitTimeOtp(String otp, String flag){
        for (int i = 0; i < 10; i++) {
            keyword.sleep(5);
            keyword.click(Locator.LBL_TIME_EXPIRED_OTP);
        }
        keyword.webDriverWaitForElementPresent(Locator.SIGN_UP_BTN_RESEND_OTP,10);
        keyword.assertEqual(Locator.LBL_TIME_EXPIRED_OTP, MESSAGE_EXPIRED_OTP);
    }
    @Step("Gửi lại mã opt")
    public void resendOtp(){
        keyword.click(Locator.SIGN_UP_BTN_RESEND_OTP);
    }
    public String getGender(String gender){
        if(gender.equals("Nam")){
            return "MALE";
        }
        else if(gender.equals("Nữ")){
            return "FEMALE";
        }
        else {
            return "OTHER";
        }
    }
    public String getBirthDay(String day){
        String[] date = day.split("-");
        return date[2] + "-" + date[1] + "-" + date[0];
    }
    public String getUserInform(String flag) {
        String inform = "";
        switch (flag) {
            case "phone":
                inform =  keyword.getText(Locator.USER_INFORM_LBL_PHONE);
                break;
            case "name":
                inform =  keyword.getText(Locator.USER_INFORM_LBL_FULL_NAME);
                break;
            case "email":
                inform = keyword.getText(Locator.USER_INFORM_LBL_EMAIL);
                break;
            case "birth day":
                inform = keyword.getText(Locator.USER_INFORM_LBL_BIRTH_DAY);
                break;
            case "gender":
                inform = keyword.getText(Locator.USER_INFORM_LBL_GENDER);
                break;
            case "all":
                inform = keyword.getText(Locator.USER_INFORM_LBL_PHONE)
                        + "," + keyword.getText(Locator.USER_INFORM_LBL_FULL_NAME) + "," +
                        keyword.getText(Locator.USER_INFORM_LBL_EMAIL) + "," + keyword.getText(Locator.USER_INFORM_LBL_BIRTH_DAY) + "," +
                        keyword.getText(Locator.USER_INFORM_LBL_GENDER);
                break;
        }
        return inform;
    }
    @Step("Kiểm tra thông tin user: {0} với trường: {1}")
    public void checkUserInform(String key, String cases){
        String query = PropertiesFile.getPropValue("POSTGRES_DB_QUERY_USER").replace("phone", PropertiesFile.getPropValue(key));
        dataBase.queryDb(query);
        String birthDay = getBirthDay(keyword.getText(Locator.USER_INFORM_LBL_BIRTH_DAY));
        String gender = getGender(keyword.getText(Locator.USER_INFORM_LBL_GENDER));
        switch (cases) {
            case "all":
                dataBase.checkDataBase("DB_USERS_LBL_PHONE,DB_USERS_LBL_FULL_NAME,DB_USERS_LBL_EMAIL,DB_USERS_LBL_DATE,DB_USERS_LBL_GENDER",
                        keyword.getText(Locator.USER_INFORM_LBL_PHONE) + "," + keyword.getText(Locator.USER_INFORM_LBL_FULL_NAME) + "," + keyword.getText(Locator.USER_INFORM_LBL_EMAIL) + "," +
                                birthDay + "," + gender);
                break;
            case "name":
                dataBase.checkDataBase("DB_USERS_LBL_FULL_NAME", keyword.getText(Locator.USER_INFORM_LBL_FULL_NAME));
                break;
            case "email":
                dataBase.checkDataBase("DB_USERS_LBL_EMAIL", keyword.getText(Locator.USER_INFORM_LBL_EMAIL));
                break;
            case "birth day":
                dataBase.checkDataBase("DB_USERS_LBL_DATE", birthDay);
                break;
            case "gender":
                dataBase.checkDataBase("DB_USERS_LBL_GENDER", gender);
                break;
        }
    }

}

