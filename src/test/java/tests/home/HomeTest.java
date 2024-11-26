package tests.home;

import core.BaseTest;
import helpers.DataBase;
import org.testng.annotations.BeforeClass;
import pages.ForgotPasswordPage;
import pages.loginsignup.LoginPage;
import pages.loginsignup.SignUpPage;

import java.sql.Statement;
import java.util.HashMap;

import static utilities.ReadExcel.ExcelOperations;

public class HomeTest extends BaseTest {
    public DataBase dataBase ;
    public LoginPage loginPage;
    public ForgotPasswordPage forgotPasswordPage;
    public SignUpPage signUpPage;
    private HashMap<String, String> dataHome;
    private Statement stmt ;
    public HomeTest(){
        loginPage = new LoginPage();
        forgotPasswordPage = new ForgotPasswordPage();
        signUpPage = new SignUpPage();
    }
    @BeforeClass
    public void setDb(){
        stmt = dataBase.setUpDB("POSTGRES_DB_URL","POSTGRES_DB_USER","POSTGRES_DB_PASSWORD");
        loginPage.isUserLogout();
    }
}
