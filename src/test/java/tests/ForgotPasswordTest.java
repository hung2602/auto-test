package tests;

import core.BaseTest;
import helpers.DataBase;
import locator.Locator;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import pages.ForgotPasswordPage;
import pages.loginsignup.LoginPage;
import pages.loginsignup.SignUpPage;

import java.util.HashMap;
import static constant.Constant.*;
import static constant.Query.*;
import static helpers.PathHelper.getNameMethod;
import static utilities.ReadExcel.*;
import static utilities.ReadExcel.getIndexRowFromKey;

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
        dataBase = new DataBase();
    }
    @BeforeClass
    public void firstSteps(){
        ExcelOperations("ForgotPassword");
        dataBase.setUpDB("POSTGRES_DB_URL","POSTGRES_DB_USER","POSTGRES_DB_PASSWORD");
        loginPage.isUserLogout();
        loginPage.goToLogin();
    }
    @Test(priority = 1, description = "Kiểm tra nhấn quên pass")
    public void FP_1(){
        dataForgot = getTestDataInMap(getIndexRowFromKey(getNameMethod()));
        loginPage.inputUserName(dataForgot.get("User name"));
        forgotPasswordPage.clickForgot();
        forgotPasswordPage.checkOTPScreen(dataForgot.get("User name"));
    }
    @Test(priority = 2, description = "Kiểm tra nhập OTP đã dùng", dependsOnMethods = "FP_1")
    public void FP_2(){
        dataForgot = getTestDataInMap(getIndexRowFromKey(getNameMethod()));
        loginPage.inputOtp(dataForgot.get("OTP"));
        loginPage.continueOtp("không tồn tại");
    }
    @Test(priority = 2, description = "Kiểm tra nhập OTP hết hạn")
    public void FP_3(){
        dataForgot = getTestDataInMap(getIndexRowFromKey(getNameMethod()));
        loginPage.waitTimeOtp();
        HashMap<String, String> dbData = dataBase.queryAndGetDb(SPORTS_ID_QUERY_USER, dataForgot.get("User name"));
        loginPage.deleteOtp();
        loginPage.inputOtp(dbData.get("otp_code"));
        loginPage.continueOtp("hết hạn");
    }
    @Test(priority = 3, description = "Kiểm tra gửi lại OTP", dependsOnMethods = "FP_3")
    public void FP_4(){
        loginPage.resendOtp();
    }
    @Test(priority = 4, description = "Kiểm tra nhập OTP hợp lệ rồi back lại")
    public void FP_5(){
        HashMap<String, String> dbData = dataBase.queryAndGetDb(SPORTS_ID_QUERY_USER, dataForgot.get("User name"));
        loginPage.deleteOtp();
        loginPage.inputOtp(dbData.get("otp_code"));
        signUpPage.backFromOTPScreen();
        keyword.verifyElementDisplay(Locator.LOGIN_TXT_PASSWORD, true);
    }
    @Test(priority = 5, description = "Kiểm tra back khi ở màn hình đổi mật khẩu", dependsOnMethods = "FP_5")
    public void FP_6(){
        forgotPasswordPage.clickForgot();
        HashMap<String, String> dbData = dataBase.queryAndGetDb(SPORTS_ID_QUERY_USER, dataForgot.get("User name"));
        loginPage.deleteOtp();
        loginPage.inputOtp(dbData.get("otp_code"));
        loginPage.continueOtp("hợp lệ");
        signUpPage.backFromOTPScreen();
        keyword.verifyElementDisplay(Locator.LOGIN_TXT_PASSWORD, true);
    }
    @Test(priority = 6, description = "Kiểm tra nhập otp hợp lệ")
    public void FP_7(){
        forgotPasswordPage.clickForgot();
        HashMap<String, String> dbData = dataBase.queryAndGetDb(SPORTS_ID_QUERY_USER, dataForgot.get("User name"));
        loginPage.deleteOtp();
        loginPage.inputOtp(dbData.get("otp_code"));
        loginPage.continueOtp("hợp lệ");
    }
    @Test(priority = 7, description = "Kiểm tra nhập mật khẩu lớn hơn 6 ký tự",dependsOnMethods = "FP_7")
    public void FP_8(){
        dataForgot = getTestDataInMap(getIndexRowFromKey(getNameMethod()));
        loginPage.inputPassWord(dataForgot.get("Pass word"));
    }
    @Test(priority = 8, description = "Kiểm tra nhập mật khẩu bằng 6 ký tự")
    public void FP_9(){
        dataForgot = getTestDataInMap(getIndexRowFromKey(getNameMethod()));
        loginPage.inputPassWord(dataForgot.get("Pass word"));
    }
    @Test(priority = 9, description = "Kiểm tra nhập mật khẩu ít hơn 6 ký tự")
    public void FP_10(){
        dataForgot = getTestDataInMap(getIndexRowFromKey(getNameMethod()));
        loginPage.inputPassWord(dataForgot.get("Pass word"));
        signUpPage.verifyMessPassWord(MESSAGE_ERROR_PASS);
    }
    @Test(priority = 10, description = "Kiểm tra nhập mật khẩu chỉ gồm chữ hoặc số")
    public void FP_11(){
        dataForgot = getTestDataInMap(getIndexRowFromKey(getNameMethod()));
        loginPage.inputPassWord(dataForgot.get("Pass word"));
    }
    @Test(priority = 11, description = "Kiểm tra nhập mật khẩu gồm chữ, số, ký tự đb, hoa...")
    public void FP_12(){
        dataForgot = getTestDataInMap(getIndexRowFromKey(getNameMethod()));
        loginPage.inputPassWord(dataForgot.get("Pass word"));
    }
    @Test(priority = 12, description = "Kiểm tra nhập mật khẩu gồm khoảng trắng")
    public void FP_13(){
        dataForgot = getTestDataInMap(getIndexRowFromKey(getNameMethod()));
        signUpPage.setPassWord(dataForgot.get("Pass word"), dataForgot.get("Password confirm"));
        keyword.assertEqual(Locator.LOGOUT_TOAST_SUCCESS, MESS_ERROR_FORGOT_PASS);
    }
    @Test(priority = 13, description = "Kiểm tra nhập mật khẩu khác mật khẩu nhập lại")
    public void FP_14(){
        dataForgot = getTestDataInMap(getIndexRowFromKey(getNameMethod()));
        signUpPage.setPassWord(dataForgot.get("Pass word"), dataForgot.get("Password confirm"));
        signUpPage.verifyMessPassWord(MESS_SIGN_UP_COMPARE_PASS);
    }
    @Test(priority = 14, description = "Kiểm tra nhập mật khẩu trùng pass nhập lại")
    public void FP_15_16(){
        String passWord = signUpPage.getPassWord();
        int index = getIndexRowFromKey(getNameMethod());
        dataForgot = getTestDataInMap(index);
        setCell(dataForgot.get("Pass word"), getIndexRowFromKey("FP_17") ,getIndexCellFromKey("Pass word"));
        setCell(dataForgot.get("Password confirm"), getIndexRowFromKey("FP_17") ,getIndexCellFromKey("Password confirm"));
        setCell(passWord, index ,getIndexCellFromKey("Pass word"));
        setCell(passWord, index ,getIndexCellFromKey("Password confirm"));
        dataForgot = getTestDataInMap(index);
        signUpPage.setPassWord(dataForgot.get("Pass word"), dataForgot.get("Password confirm"));
        loginPage.compareMessLoginSuccess();
    }
    @Test(priority = 15, description = "Kiểm tra đăng nhập bằng mật khẩu cũ", dependsOnMethods = "FP_15_16")
    public void FP_17(){
        loginPage.viewUserInform();
        loginPage.logOut("Thành công");
        dataForgot = getTestDataInMap(getIndexRowFromKey(getNameMethod()));
        loginPage.login(dataForgot.get("User name"), dataForgot.get("Pass word"));
        loginPage.compareMessLoginIncorrectPass(dataForgot.get("User name"));
    }
    @Test(priority = 16, description = "Kiểm tra đăng nhập bằng mật khẩu mới", dependsOnMethods = "FP_17")
    public void FP_18(){
        dataForgot = getTestDataInMap(getIndexRowFromKey("FP_15_16"));
        loginPage.inputPassWord(dataForgot.get("Pass word"));
        loginPage.compareMessLoginSuccess();
    }
    @Test(priority = 17, description = "Kiểm tra gửi quá 3 lần otp trong ngày", dependsOnMethods = "FP_18")
    public void FP_19(){
    }

}
