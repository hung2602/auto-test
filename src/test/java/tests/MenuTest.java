package tests;

import core.BaseTest;
import helpers.DataBase;
import helpers.PropertiesFile;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import pages.LoginSignUp.LoginPage;
import pages.LoginSignUp.SignUpPage;
import pages.MenuPage;

import java.util.HashMap;

public class MenuTest extends BaseTest {
    public DataBase dataBase ;
    public LoginPage loginPage;
    public MenuPage menuPage;
    public SignUpPage signUpPage;
    private static String query;
    public MenuTest(){
        menuPage = new MenuPage();
        loginPage = new LoginPage();
        signUpPage = new SignUpPage();
        dataBase = new DataBase();
    }
    @BeforeClass
    public void firstSteps(){
        dataBase.setUpDB("POSTGRES_DB_URL","POSTGRES_DB_USER","POSTGRES_DB_PASSWORD");
        loginPage.isUserLogout();
    }
    @Test(description = "Kiểm tra lịch sử mua gói")
    public void MN_4(){
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
        HashMap<String, String> dbData = signUpPage.queryAndGetDb("PostGre", PropertiesFile.getPropValue("PHONE_NUMBER"));
        dataBase.setUpMonGoDb("MONGO_DB_URL","MONGO_DB_USER","MONGO_DB_PASSWORD");
        dbData = signUpPage.queryAndGetDb("Mongo", dbData.get("id"));
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
}
