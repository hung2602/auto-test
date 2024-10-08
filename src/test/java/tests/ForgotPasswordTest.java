package tests;

import core.BaseTest;
import helpers.DataBase;
import locator.Locator;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import pages.LoginSignUp.LoginPage;
import pages.ProfilePage;

import java.util.HashMap;

import static constant.Constant.TEXT_BOX_USERNAME;
import static helpers.PathHelper.getNameMethod;
import static utilities.ReadExcel.*;

public class ForgotPasswordTest extends BaseTest {
    public DataBase dataBase ;
    public LoginPage loginPage;
    public ProfilePage profilePage;
    private HashMap<String, String> dataForgot;
    public ForgotPasswordTest(){
        loginPage = new LoginPage();
        profilePage = new ProfilePage();
    }
    @BeforeClass
    public void setDb(){
        ExcelOperations("Forgot password");
        dataBase.setUpDB("POSTGRES_DB_URL","POSTGRES_DB_USER","POSTGRES_DB_PASSWORD");
    }
    @Test(description = "Kiểm tra nhấn quên pass")
    public void FP_1(){
        dataForgot = getTestDataInMap(getIndexRowFromKey(getNameMethod()));
        loginPage.goToLogin();
        loginPage.inputUserName(dataForgot.get("User name"));
    }

    // otp_code
    // otp_expiration_time
    // updated_at

}
