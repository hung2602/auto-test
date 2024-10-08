package tests.LoginSignUp;

import core.BaseTest;
import helpers.DataBase;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import pages.LoginSignUp.LoginPage;
import pages.ProfilePage;

import java.util.HashMap;

import static helpers.PathHelper.getNameMethod;
import static utilities.ReadExcel.*;

public class SignUpTest extends BaseTest {
    public DataBase dataBase ;
    public LoginPage loginPage;
    public ProfilePage profilePage;
    private HashMap<String, String> dataSignUp;
    public SignUpTest(){
        loginPage = new LoginPage();
        profilePage = new ProfilePage();
    }
    @BeforeClass
    public void setDb(){
        ExcelOperations("Sign up");
        dataBase.setUpDB("POSTGRES_DB_URL","POSTGRES_DB_USER","POSTGRES_DB_PASSWORD");
    }
    @Test(description = "Kiểm tra nhập sdt chưa đăng ký")
    public void SU_1(){
        dataSignUp = getTestDataInMap(getIndexRowFromKey(getNameMethod()));
        loginPage.goToLogin();
        loginPage.inputUserName(dataSignUp.get("User name"));
    }
    @Test(description = "Kiểm tra bỏ trống mã OTP")
    public void SU_2(){
        dataSignUp = getTestDataInMap(getIndexRowFromKey(getNameMethod()));
    }
    @Test(description = "Kiểm tra nhập mã OTP sai")
    public void SU_3(){
        dataSignUp = getTestDataInMap(getIndexRowFromKey(getNameMethod()));
        loginPage.inputOtp(dataSignUp.get("OTP"),"không tồn tại");
    }
    @Test(description = "Kiểm tra nhập mã OTP đã dùng")
    public void SU_4(){
        dataSignUp = getTestDataInMap(getIndexRowFromKey(getNameMethod()));
        loginPage.inputOtp(dataSignUp.get("OTP"),"không tồn tại");
    }

}
