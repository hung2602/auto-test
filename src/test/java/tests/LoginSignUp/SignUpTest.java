package tests.LoginSignUp;

import core.BaseTest;
import helpers.DataBase;
import helpers.PropertiesFile;
import io.qameta.allure.Severity;
import locator.Locator;
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
    private String query = "";

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
    @Test(description = "Kiểm tra nhập sdt chưa đăng ký - Bỏ trống mã otp")
    public void SU_1_2(){
        loginPage.goToLogin();
        int indexRow = getIndexRowFromKey(getNameMethod());
        String phone = loginPage.getPhoneNumber();
        setCell(phone, indexRow , getIndexCellFromKey("User name"));
        dataSignUp = getTestDataInMap(indexRow);
        loginPage.inputUserName(dataSignUp.get("User name"));
    }
    @Test(priority = 1, description = "Kiểm tra nhập mã OTP sai" , dependsOnMethods = "SU_1_2")
    public void SU_3(){
        dataSignUp = getTestDataInMap(getIndexRowFromKey(getNameMethod()));
        loginPage.inputOtp(dataSignUp.get("OTP"),"không tồn tại");
    }
    @Test(priority = 2, description = "Kiểm tra nhập mã OTP hợp lệ rồi back lại")
    public void SU_4(){
        loginPage.deleteOtp();
        dataSignUp = getTestDataInMap(getIndexRowFromKey("SU_1_2"));
        query = PropertiesFile.getPropValue("POSTGRES_DB_QUERY_USER").replace("phone", dataSignUp.get("User name"));
        dataBase.queryDb(query);
        HashMap<String, String> dbData = dataBase.getResultDataBase();
        loginPage.inputOtp(dbData.get("otp_code"),"hợp lệ");
        signUpPage.inFormSetPassWord();
        signUpPage.backFromOTPScreen();
        setCell(dbData.get("otp_code"), getIndexRowFromKey(getNameMethod()) ,getIndexCellFromKey("OTP"));
    }
    @Test(priority = 3, description = "Kiểm tra nhập mã OTP đã dùng", dependsOnMethods = "SU_4")
    public void SU_5(){
        HashMap<String, String> dbData = dataBase.getResultDataBase();
        loginPage.inputUserName(dataSignUp.get("User name"));
        loginPage.inputOtp(dbData.get("otp_code"),"không tồn tại");
        setCell(dbData.get("otp_code"), getIndexRowFromKey(getNameMethod()) ,getIndexCellFromKey("OTP"));
    }
    @Test(priority = 4, description = "Kiểm tra nhập mã OTP đã hết hạn", dependsOnMethods = "SU_5")
    public void SU_6(){
        loginPage.waitTimeOtp();
        query = PropertiesFile.getPropValue("POSTGRES_DB_QUERY_USER").replace("phone", dataSignUp.get("User name"));
        dataBase.queryDb(query);
        HashMap<String, String> dbData = dataBase.getResultDataBase();
        loginPage.deleteOtp();
        loginPage.inputOtp(dbData.get("otp_code"),"không tồn tại");
        setCell(dbData.get("otp_code"), getIndexRowFromKey(getNameMethod()) ,getIndexCellFromKey("OTP"));
    }
    @Severity(CRITICAL)
    @Test(priority = 5, description = "Kiểm tra gửi lại mã OTP và dùng mã Otp lần 1", dependsOnMethods = "SU_6")
    public void SU_7_8(){
        HashMap<String, String> dbData = dataBase.getResultDataBase();
        String oldOtp = dbData.get("otp_code");
        loginPage.resendOtp();
        setCell(oldOtp, getIndexRowFromKey(getNameMethod()) ,getIndexCellFromKey("OTP"));
        loginPage.inputOtp(oldOtp,"không tồn tại");
    }
    @Severity(CRITICAL)
    @Test(priority = 6, description = "Kiểm tra nhập mã OTP hợp lệ", dependsOnMethods = "SU_7_8")
    public void SU_9(){
        loginPage.deleteOtp();
        dataBase.queryDb(query);
        HashMap<String, String> dbData = dataBase.getResultDataBase();
        setCell(dbData.get("otp_code"), getIndexRowFromKey(getNameMethod()) ,getIndexCellFromKey("OTP"));
        loginPage.inputOtp(dbData.get("otp_code"),"tồn tại");
    }
    @Test(priority = 7, description = "Kiểm tra nhập pass > 6 ký tự")
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
        keyword.assertEqual(Locator.LOGIN_LBL_ERROR, MESSAGE_ERROR_PASS);
    }
    @Test(priority = 10, description = "Kiểm tra nhập pass chỉ gồm chữ hoặc số")
    public void SU_13(){
        dataSignUp = getTestDataInMap(getIndexRowFromKey(getNameMethod()));
    }
    @Test(priority = 11, description = "Kiểm tra nhập pass chỉ gồm khoảng trắng")
    public void SU_14(){
        dataSignUp = getTestDataInMap(getIndexRowFromKey(getNameMethod()));
    }
    @Test(priority = 12, description = "Kiểm tra nhập pass gồm số, chữ, và ký tự đặc biệt, in hoa thường")
    public void SU_15(){
        dataSignUp = getTestDataInMap(getIndexRowFromKey(getNameMethod()));
    }
    @Test(priority = 13, description = "Kiểm tra nhập pass khác pass nhập lại")
    public void SU_16(){
        dataSignUp = getTestDataInMap(getIndexRowFromKey(getNameMethod()));
    }
    @Test(priority = 14, description = "Kiểm tra nhập pass trùng pass nhập lại")
    public void SU_17(){
        dataSignUp = getTestDataInMap(getIndexRowFromKey(getNameMethod()));
    }
    @Test(priority = 15, description = "Kiểm tra button show mật khẩu", dependsOnMethods = "SU_17")
    public void SU_18(){

    }
    @Test(priority = 14, description = "Kiểm tra button xóa mật khẩu", dependsOnMethods = "SU_17")
    public void SU_19(){
        dataSignUp = getTestDataInMap(getIndexRowFromKey(getNameMethod()));
    }
    @Test(priority = 15, description = "Kiểm tra thông tin user khi đăng ký thành công", dependsOnMethods = "SU_17")
    public void SU_20(){

    }

}
