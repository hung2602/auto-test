package tests;

import core.BaseTest;
import helpers.DataBase;
import helpers.PropertiesFile;
import locator.Locator;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import pages.HomePage;
import pages.LoginPage;
import pages.MenuPage;
import utilities.ReadExcel;

import static constant.Constant.TEXT_BOX_USERNAME;

public class MenuTest extends BaseTest {
    public DataBase dataBase ;
    public Object[][] dataLogin;
    public LoginPage loginPage;
    public MenuPage menuPage;
    public MenuTest(){
        menuPage = new MenuPage();
        loginPage = new LoginPage();
        dataBase = new DataBase();
    }
    @BeforeClass
    public void connectDb(){
        dataLogin = ReadExcel.getExcelData("Login");
        dataBase.setUpDB("POSTGRES_DB_URL","POSTGRES_DB_USER","POSTGRES_DB_PASSWORD");
    }
    @Test(description = "Kiểm tra lịch sử mua gói")
    public void MN_4(){
        loginPage.loginSuccess((String)dataLogin[1][1],(String)dataLogin[1][2]);
        menuPage.goToSubHistory();
        menuPage.checkSubHistory();
        loginPage.goBack();
    }
    @Test(description = "Kiểm tra màn hình mã khuyến mãi")
    public void MN_5(){
        menuPage.goToDiscountCode();
        menuPage.checkDiscountCode();
        loginPage.goBack();
    }
    @Test(description = "Kiểm tra màn hình quản lý thiết bị")
    public void MN_7(){
        String query = PropertiesFile.getPropValue("POSTGRES_DB_QUERY_USER").replace("phone", PropertiesFile.getPropValue("PHONE_NUMBER"));
        dataBase.queryDb(query);
        query = PropertiesFile.getPropValue("MONGO_DB_QUERY_DEVICE").replace("idUser", dataBase.getDataBase("id"));
        dataBase.setUpMonGoDb("MONGO_DB_URL","MONGO_DB_USER","MONGO_DB_PASSWORD");
        dataBase.queryDb(query);
        menuPage.goToDeviceManage();
        keyword.assertEqualData(dataBase.getDataBase("device_name"),menuPage.getDeviceInform());
        loginPage.goBack();
    }
    @Test(description = "Kiểm tra màn hình điều khoản chính sách")
    public void MN_9(){
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
