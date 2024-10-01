package tests;

import core.BaseTest;
import helpers.DataBase;
import org.testng.annotations.BeforeClass;
import pages.HomePage;
import pages.LoginPage;

public class MenuTest extends BaseTest {
    public DataBase dataBase ;
    public LoginPage loginPage;
    public MenuTest(){
        loginPage = new LoginPage();
        dataBase = new DataBase();
    }
    @BeforeClass
    public void connectDb(){
        dataBase.setUpMonGoDb("MONGO_DB_URL","MONGO_DB_USER","MONGO_DB_PASSWORD");
//        dataBase.queryDb("SELECT * FROM session_device Where device_name = 'iPhone 13'");
    }
}
