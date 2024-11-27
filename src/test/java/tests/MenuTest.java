package tests;

import core.BaseTest;
import core.DataBase;
import driver.DriverManager;
import locator.Locator;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import pages.loginsignup.LoginPage;
import pages.loginsignup.SignUpPage;
import pages.MenuPage;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;
import static constant.Query.*;

public class MenuTest extends BaseTest {
    public DataBase dataBase ;
    public LoginPage loginPage;
    public MenuPage menuPage;
    public SignUpPage signUpPage;
    private static Statement stmt ;
    private static ResultSet res ;
    private static Connection con ;
    public MenuTest(){
        menuPage = new MenuPage();
        loginPage = new LoginPage();
        signUpPage = new SignUpPage();
        dataBase = new DataBase();
    }
    @BeforeClass
    public void firstSteps(){
        con = dataBase.setUpDB("POSTGRES_DB_URL","POSTGRES_DB_USER","POSTGRES_DB_PASSWORD");
        stmt = dataBase.createStatement(con);
        loginPage.isUserLogout();
    }
    @Test(description = "Kiểm tra lịch sử mua gói")
    public void MN_4(){
        keyword.sleep(3);
        keyword.click(Locator.HOME_BTN_MENU);
        loginPage.login("PHONE_NUMBER","PASS_WORD");
        menuPage.goToSubHistory();
        menuPage.checkSubHistory();
    }
    @Test(description = "Kiểm tra màn hình mã khuyến mãi")
    public void MN_5(){
        loginPage.goBack();
        menuPage.goToDiscountCode();
        menuPage.checkDiscountCode();
    }
    @Test(description = "Kiểm tra màn hình quản lý thiết bị")
    public void MN_7(){
        loginPage.goBack();
        res = dataBase.queryDb(stmt, SPORTS_ID_QUERY_USER.replace("key", "PHONE_NUMBER"));
        HashMap<String, String> dbData = dataBase.getResultDataBase(res);
        con = dataBase.setUpDB("MONGO_DB_URL","MONGO_DB_USER","MONGO_DB_PASSWORD");
        stmt = dataBase.createStatement(con);
        res = dataBase.queryDb(stmt, MON_GO_DB_QUERY_DEVICE.replace("key", dbData.get("id")));
        dbData = dataBase.getResultDataBase(res);
        menuPage.goToDeviceManage();
        keyword.assertEqualData(dbData.get("device_name"), menuPage.getDeviceInform());
    }
    @Test(description = "Kiểm tra màn hình điều khoản chính sách")
    public void MN_9(){
        loginPage.goBack();
        menuPage.goToPolicyTerm();
        menuPage.checkPolicyTerm();
        loginPage.goBack();
    }
    @Test(description = "Kiểm tra màn hình mua gói cước")
    public void MN_10(){
        menuPage.goToBuySub();
        menuPage.checkBuySub();
    }
    @AfterClass
    public void closeConnect() throws SQLException {
        if(con != null) {
            con.close();
        }
    }

}
