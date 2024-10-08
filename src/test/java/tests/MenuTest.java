package tests;

import core.BaseTest;
import helpers.DataBase;
import helpers.PropertiesFile;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import pages.LoginSignUp.LoginPage;
import pages.MenuPage;

import java.util.HashMap;

public class MenuTest extends BaseTest {
    public DataBase dataBase ;
    public LoginPage loginPage;
    public MenuPage menuPage;
    private static String query;
    public MenuTest(){
        menuPage = new MenuPage();
        loginPage = new LoginPage();
        dataBase = new DataBase();
    }
    @BeforeClass
    public void connectDb(){
        dataBase.setUpDB("POSTGRES_DB_URL","POSTGRES_DB_USER","POSTGRES_DB_PASSWORD");
    }
    @Test(description = "Kiểm tra lịch sử mua gói")
    public void MN_4(){
        loginPage.loginSuccess("PHONE_NUMBER","PASS_WORD");
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
        query = PropertiesFile.getPropValue("POSTGRES_DB_QUERY_USER").replace("phone", PropertiesFile.getPropValue("PHONE_NUMBER"));
        dataBase.queryDb(query);
        HashMap<String, String> dbData = dataBase.getResultDataBase();
        query = PropertiesFile.getPropValue("MONGO_DB_QUERY_DEVICE").replace("idUser", dbData.get("id"));
        dataBase.setUpMonGoDb("MONGO_DB_URL","MONGO_DB_USER","MONGO_DB_PASSWORD");
        dataBase.queryDb(query);
        dbData = dataBase.getResultDataBase();
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
