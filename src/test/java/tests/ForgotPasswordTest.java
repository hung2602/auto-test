package tests;

import core.BaseTest;
import helpers.DataBase;
import helpers.PropertiesFile;
import locator.Locator;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import pages.ForgotPasswordPage;
import pages.LoginSignUp.LoginPage;
import pages.LoginSignUp.SignUpPage;
import pages.ProfilePage;

import java.util.HashMap;

import static constant.Constant.TEXT_BOX_USERNAME;
import static helpers.PathHelper.getNameMethod;
import static utilities.ReadExcel.*;

public class ForgotPasswordTest extends BaseTest {
    public DataBase dataBase ;
    public LoginPage loginPage;
    public ForgotPasswordPage forgotPasswordPage;
    public SignUpPage signUpPage;
    private HashMap<String, String> dataForgot;
    public ForgotPasswordTest(){
        loginPage = new LoginPage();
        forgotPasswordPage = new ForgotPasswordPage();
        signUpPage = new SignUpPage();
    }
    @BeforeClass
    public void setDb(){
        ExcelOperations("Forgot password");
        dataBase.setUpDB("POSTGRES_DB_URL","POSTGRES_DB_USER","POSTGRES_DB_PASSWORD");
        loginPage.goToLogin();
    }
    @Test(description = "Kiểm tra nhấn quên pass")
    public void FP_1(){
        dataForgot = getTestDataInMap(getIndexRowFromKey(getNameMethod()));
        loginPage.inputUserName(dataForgot.get("User name"));
        forgotPasswordPage.clickForgot();
        forgotPasswordPage.checkOTPScreen(dataForgot.get("User name"));
    }
    @Test(priority = 1, description = "Kiểm tra nhập OTP đã dùng", dependsOnMethods = "FP_1")
    public void FP_2(){
        dataForgot = getTestDataInMap(getIndexRowFromKey(getNameMethod()));
        loginPage.inputOtp(dataForgot.get("OTP"));
        loginPage.continueOtp("không tồn tại");
    }
    @Test(priority = 2, description = "Kiểm tra nhập OTP hết hạn")
    public void FP_3(){
        loginPage.waitTimeOtp();
        signUpPage.queryAndInputOtp(dataForgot.get("User name"));
        loginPage.continueOtp("hết hạn");
    }
    @Test(description = "Kiểm tra gửi lại OTP", dependsOnMethods = "FP_3")
    public void FP_4(){
        loginPage.resendOtp();
    }
    @Test(description = "Kiểm tra nhập OTP hợp lệ rồi back lại")
    public void FP_5(){
        signUpPage.queryAndInputOtp(dataForgot.get("User name"));
        signUpPage.backFromOTPScreen();
        keyword.verifyElementDisplay(Locator.LOGIN_TXT_PASSWORD, true);
    }
    @Test(description = "Kiểm tra back khi ở màn hình đổi mk")
    public void FP_6(){
        forgotPasswordPage.clickForgot();
        signUpPage.queryAndInputOtp(dataForgot.get("User name"));
        loginPage.continueOtp("hợp lệ");
        signUpPage.backFromOTPScreen();
        keyword.verifyElementDisplay(Locator.LOGIN_TXT_PASSWORD, true);
    }
    @Test(description = "Kiểm tra nhập otp hợp lệ")
    public void FP_7(){
        forgotPasswordPage.clickForgot();
        signUpPage.queryAndInputOtp(dataForgot.get("User name"));
        loginPage.continueOtp("hợp lệ");
    }
    @Test(description = "Kiểm tra nhập pass lớn hơn 6 ký tự")
    public void FP_8(){
        dataForgot = getTestDataInMap(getIndexRowFromKey(getNameMethod()));
    }
    @Test(description = "Kiểm tra nhập pass lớn hơn 6 ký tự")
    public void FP_9(){
        dataForgot = getTestDataInMap(getIndexRowFromKey(getNameMethod()));
    }
    // otp_code
    // otp_expiration_time
    // updated_at
}
