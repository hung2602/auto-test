package tests.home;

import core.BaseTest;
import core.DataBase;
import driver.DriverManager;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import pages.ForgotPasswordPage;
import pages.loginsignup.LoginPage;
import pages.loginsignup.SignUpPage;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;

public class HomeTest extends BaseTest {
    public DataBase dataBase ;
    public LoginPage loginPage;
    public ForgotPasswordPage forgotPasswordPage;
    public SignUpPage signUpPage;
    private HashMap<String, String> dataHome;
    private static Statement stmt ;
    private static Connection con ;
    public HomeTest(){
        loginPage = new LoginPage();
        forgotPasswordPage = new ForgotPasswordPage();
        signUpPage = new SignUpPage();
    }
    @BeforeClass
    public void setDb(){
        con = dataBase.setUpDB("POSTGRES_DB_URL","POSTGRES_DB_USER","POSTGRES_DB_PASSWORD");
        stmt = dataBase.createStatement(con);
        loginPage.isUserLogout();
    }
    @AfterClass
    public void closeConnect() throws SQLException {
        if(con != null) {
            con.close();
        }
    }
}
