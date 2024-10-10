package tests.LoginSignUp;

import core.BaseTest;
import helpers.DataBase;
import helpers.PropertiesFile;
import io.qameta.allure.Severity;
import locator.Locator;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import pages.LoginSignUp.LoginPage;
import pages.LoginSignUp.SignUpPage;
import java.util.HashMap;
import static constant.Constant.*;
import static helpers.PathHelper.getNameMethod;
import static io.qameta.allure.SeverityLevel.CRITICAL;
import static utilities.ReadExcel.*;

public class SignUpTest extends BaseTest {
    public DataBase dataBase ;
    public LoginPage loginPage;
    public SignUpPage signUpPage;
    private HashMap<String, String> dataSignUp;

    private String phone = "";

    public SignUpTest(){
        loginPage = new LoginPage();
        signUpPage = new SignUpPage();
        dataBase = new DataBase();
    }
    @BeforeClass
    public void setDb(){
        ExcelOperations("SignUp");
        dataBase.setUpDB("POSTGRES_DB_URL","POSTGRES_DB_USER","POSTGRES_DB_PASSWORD");
    }
    @Severity(CRITICAL)
    @Test(description = "Kiểm tra nhập sdt chưa đăng ký - Bỏ trống mã OTP")
    public void SU_1_2(){
        loginPage.goToLogin();
        int indexRow = getIndexRowFromKey(getNameMethod());
        phone = loginPage.getPhoneNumber();
        setCell(phone, indexRow , getIndexCellFromKey("User name"));
        dataSignUp = getTestDataInMap(indexRow);
        loginPage.inputUserName(dataSignUp.get("User name"));
    }
    @Test(priority = 1, description = "Kiểm tra nhập mã OTP sai" , dependsOnMethods = "SU_1_2")
    public void SU_3(){
        dataSignUp = getTestDataInMap(getIndexRowFromKey(getNameMethod()));
        loginPage.inputOtp(dataSignUp.get("OTP"));
        loginPage.continueOtp("không tồn tại");
    }
    @Test(priority = 2, description = "Kiểm tra nhập mã OTP hợp lệ rồi back lại")
    public void SU_4(){
        loginPage.deleteOtp();
        dataSignUp = getTestDataInMap(getIndexRowFromKey("SU_1_2"));
        HashMap<String, String> dbData = signUpPage.queryAndInputOtp(dataSignUp.get("User name"));
        loginPage.continueOtp("hợp lệ");
        signUpPage.inFormSetPassWord();
        signUpPage.backFromOTPScreen();
        setCell(dbData.get("otp_code"), getIndexRowFromKey(getNameMethod()) ,getIndexCellFromKey("OTP"));
    }
    @Test(priority = 3, description = "Kiểm tra nhập mã OTP đã dùng", dependsOnMethods = "SU_4")
    public void SU_5(){
        HashMap<String, String> dbData = dataBase.getResultDataBase();
        loginPage.inputUserName(dataSignUp.get("User name"));
        loginPage.inputOtp(dbData.get("otp_code"));
        loginPage.continueOtp("không tồn tại");
        setCell(dbData.get("otp_code"), getIndexRowFromKey(getNameMethod()) ,getIndexCellFromKey("OTP"));
    }
    @Test(priority = 4, description = "Kiểm tra nhập mã OTP đã hết hạn", dependsOnMethods = "SU_5")
    public void SU_6(){
        loginPage.waitTimeOtp();
        HashMap<String, String> dbData = signUpPage.queryAndInputOtp(dataSignUp.get("User name"));
        loginPage.continueOtp("không tồn tại");
        setCell(dbData.get("otp_code"), getIndexRowFromKey(getNameMethod()) ,getIndexCellFromKey("OTP"));
    }
    @Severity(CRITICAL)
    @Test(priority = 5, description = "Kiểm tra gửi lại mã OTP và dùng mã OTP lần 1", dependsOnMethods = "SU_6")
    public void SU_7_8(){
        HashMap<String, String> dbData = dataBase.getResultDataBase();
        String oldOtp = dbData.get("otp_code");
        loginPage.resendOtp();
        setCell(oldOtp, getIndexRowFromKey(getNameMethod()) ,getIndexCellFromKey("OTP"));
        loginPage.inputOtp(oldOtp);
        loginPage.continueOtp("không tồn tại");
    }
    @Severity(CRITICAL)
    @Test(priority = 6, description = "Kiểm tra nhập mã OTP hợp lệ", dependsOnMethods = "SU_1_2")
    public void SU_9(){
        HashMap<String, String> dbData = signUpPage.queryAndInputOtp(dataSignUp.get("User name"));
        loginPage.continueOtp("hợp lệ");
        setCell(dbData.get("otp_code"), getIndexRowFromKey(getNameMethod()) ,getIndexCellFromKey("OTP"));
    }
    @Test(priority = 7, description = "Kiểm tra nhập pass > 6 ký tự" , dependsOnMethods = "SU_9")
    public void SU_10(){
        dataSignUp = getTestDataInMap(getIndexRowFromKey(getNameMethod()));
        signUpPage.inputPassWord(dataSignUp.get("Pass word"));
        keyword.click(Locator.LOGIN_BTN_CONTINUE);
    }
    @Test(priority = 8, description = "Kiểm tra nhập pass = 6 ký tự")
    public void SU_11(){
        dataSignUp = getTestDataInMap(getIndexRowFromKey(getNameMethod()));
        signUpPage.inputPassWord(dataSignUp.get("Pass word"));
        keyword.click(Locator.LOGIN_BTN_CONTINUE);
    }
    @Test(priority = 9, description = "Kiểm tra nhập pass < 6 ký tự")
    public void SU_12(){
        dataSignUp = getTestDataInMap(getIndexRowFromKey(getNameMethod()));
        signUpPage.inputPassWord(dataSignUp.get("Pass word"));
        keyword.sleep(0.5);
        keyword.assertEqual(Locator.LOGIN_LBL_ERROR, MESSAGE_ERROR_PASS);
    }
    @Test(priority = 10, description = "Kiểm tra nhập pass chỉ gồm chữ hoặc số")
    public void SU_13(){
        dataSignUp = getTestDataInMap(getIndexRowFromKey(getNameMethod()));
        signUpPage.inputPassWord(dataSignUp.get("Pass word"));
        keyword.click(Locator.LOGIN_BTN_CONTINUE);
    }
    @Test(priority = 11, description = "Kiểm tra nhập pass chỉ gồm khoảng trắng")
    public void SU_14(){
//        dataSignUp = getTestDataInMap(getIndexRowFromKey(getNameMethod()));
//        signUpPage.setPassWord(dataSignUp.get("Pass word"), dataSignUp.get("Pass word"));
    }
    @Test(priority = 12, description = "Kiểm tra nhập pass gồm số, chữ, và ký tự đặc biệt, in hoa thường")
    public void SU_15(){
        dataSignUp = getTestDataInMap(getIndexRowFromKey(getNameMethod()));
        signUpPage.inputPassWord(dataSignUp.get("Pass word"));
        keyword.click(Locator.LOGIN_BTN_CONTINUE);
    }
    @Test(priority = 13, description = "Kiểm tra nhập pass khác pass nhập lại")
    public void SU_16(){
        dataSignUp = getTestDataInMap(getIndexRowFromKey(getNameMethod()));
        signUpPage.setPassWord(dataSignUp.get("Pass word"), dataSignUp.get("Password confirm"));
        keyword.assertEqual(Locator.LOGIN_LBL_ERROR, MESS_SIGN_UP_COMPARE_PASS);
    }
    @Test(priority = 14, description = "Kiểm tra nhập pass trùng pass nhập lại")
    public void SU_17(){
        dataSignUp = getTestDataInMap(getIndexRowFromKey(getNameMethod()));
        signUpPage.inputPassWord(dataSignUp.get("Pass word"));
        signUpPage.inputConfirmPassWord(dataSignUp.get("Password confirm"));
    }
    @Test(priority = 15, description = "Kiểm tra button show mật khẩu", dependsOnMethods = "SU_17")
    public void SU_18(){
        signUpPage.showPass(dataSignUp.get("Pass word"));
        signUpPage.showAgainPass(dataSignUp.get("Password confirm"));
    }
    @Test(priority = 16, description = "Kiểm tra button xóa mật khẩu", dependsOnMethods = "SU_18")
    public void SU_19(){
        signUpPage.clearPass();
        signUpPage.clearAgainPass();
    }
    @Test(priority = 17, description = "Kiểm tra thông tin user khi đăng ký thành công")
    public void SU_20(){
        signUpPage.setPassWord(dataSignUp.get("Pass word"), dataSignUp.get("Password confirm"));
        keyword.verifyPresentAndClick(Locator.NOTICE_BTN_LATE);
        loginPage.viewUserInform();
        loginPage.checkUserInform("name", phone);
    }
    @AfterClass
    public void tearDown(){
        loginPage.logOut("Thành công");
    }

}
