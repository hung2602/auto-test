package pages.loginsignup;
import core.BasePage;
import core.DataBase;
import helpers.LogHelper;
import io.qameta.allure.Step;
import locator.Locator;
import org.slf4j.Logger;

import java.util.Random;

import static constant.Constant.*;

public class SignUpPage extends BasePage {
    private static Logger logger = LogHelper.getLogger();
    public DataBase dataBase ;
    public LoginPage loginPage;
    public SignUpPage() {
        dataBase = new DataBase();
        loginPage = new LoginPage();
    }
    public void backFromOTPScreen(){
        keyword.click(Locator.INPUT_OTP_BTN_BACK);
        keyword.sleep(0.2);
    }
    public String getPassWord(){
        String chars = "?!@#$%^&*()+";
        Random random = new Random();
        char c = chars.charAt(random.nextInt(chars.length()));
        int ranNum = random.nextInt(1,10);
        logger.info("Get pass word " + ranNum);
        return "AutoTest" + c + ranNum;
    }
    @Step("Hiển thị thông báo lỗi mật khẩu")
    public void verifyMessPassWord(String mess){
        keyword.sleep(0.5);
        keyword.assertEqual(Locator.LOGIN_LBL_ERROR, mess);
    }
    @Step("Kiểm tra màn hình đặt mật khẩu")
    public void inFormSetPassWord(){
        keyword.webDriverWaitForElementPresent(Locator.SET_PASSWORD_LBL_PHONE, 10);
        keyword.assertEqual(Locator.SET_PASSWORD_LBL_PHONE2, MESSAGE_SET_PASSWORD);
        keyword.assertEqual(Locator.SET_PASSWORD_LBL_PHONE, MESSAGE_HELLO_SET_PASSWORD);
    }
    @Step("Nhập mật khẩu: {0}")
    public void inputPassWord(String passWord){
        logger.info("Input pass word " + passWord);
        keyword.clearTextAndSendKey(Locator.LOGIN_TXT_PASSWORD, passWord);
    }
    @Step("Xác nhận lại mật khẩu: {0}")
    public void inputConfirmPassWord(String passWord){
        logger.info("Input confirm pass word " + passWord);
        keyword.clearTextAndSendKey(Locator.LOGIN_TXT_CONFIRM_PASSWORD, passWord);
    }
    public void setPassWord(String passWord, String confirmPass){
        logger.info("set pass word " + passWord);
        inputPassWord(passWord);
        inputConfirmPassWord(confirmPass);
        keyword.sleep(1);
        keyword.click(Locator.LOGIN_BTN_CONTINUE);
    }
    @Step("Hiện thị mật khẩu: {0}")
    public void showPass(String passWord){
        keyword.click(Locator.SIGN_UP_BTN_SHOW_PASS);
        keyword.sleep(0.5);
        keyword.assertEqualData(keyword.getText(Locator.LOGIN_TXT_PASSWORD), passWord);
    }
    @Step("Hiển thị mật khẩu nhập lại: {0}")
    public void showAgainPass(String passWord){
        keyword.click(Locator.SIGN_UP_BTN_SHOW_AGAIN_PASS);
        keyword.sleep(0.5);
        keyword.assertEqualData(keyword.getText(Locator.LOGIN_TXT_CONFIRM_PASSWORD), passWord);
    }
    @Step("Xóa mật khẩu")
    public void clearPass(){
        keyword.click(Locator.SIGN_UP_BTN_CLEAR_PASS);
        keyword.assertEqualData(keyword.getText(Locator.LOGIN_TXT_PASSWORD), TEXT_INPUT_PASSWORD);
    }
    @Step("Xóa mật khẩu")
    public void clearAgainPass(){
        keyword.click(Locator.SIGN_UP_BTN_CLEAR_AGAIN_PASS);
        keyword.assertEqualData(keyword.getText(Locator.LOGIN_TXT_CONFIRM_PASSWORD), TEXT_INPUT_CONFIRM_PASSWORD);
    }
}
