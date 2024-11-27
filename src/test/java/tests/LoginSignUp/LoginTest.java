package tests.LoginSignUp;
import core.BaseTest;
import core.DataBase;
import driver.DriverManager;
import io.qameta.allure.Severity;
import locator.Locator;
import org.testng.annotations.*;
import pages.ProfilePage;
import pages.home.HomePage;
import pages.loginsignup.LoginPage;
import pages.loginsignup.SignUpPage;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;

import static constant.Constant.*;
import static helpers.PathHelper.getNameMethod;
import static io.qameta.allure.SeverityLevel.CRITICAL;
import static utilities.ReadExcel.*;
import org.apache.poi.ss.usermodel.*;
public class LoginTest extends BaseTest {
    public DataBase dataBase ;
    public LoginPage loginPage;
    public SignUpPage signUpPage;
    public HomePage homePage;
    public ProfilePage profilePage;
    private HashMap<String, String> dataLogin;
    private  static Sheet sh = null;
    private  static Statement stmt ;
    private static Connection con ;
    public LoginTest(){
        loginPage = new LoginPage();
        profilePage = new ProfilePage();
        homePage = new HomePage();
        dataBase = new DataBase();
        signUpPage = new SignUpPage();
    }
    @BeforeClass
    public void firstSteps(){
        sh = readSheet(workbook , "Login");
        con = dataBase.setUpDB("POSTGRES_DB_URL","POSTGRES_DB_USER","POSTGRES_DB_PASSWORD");
        stmt = dataBase.createStatement(con);
        loginPage.isUserLogout();
    }
    @Test(description = "Kiểm tra text ẩn, nhập sđt bỏ trống")
    public void LG_1(){
        dataLogin = getTestDataInMap(sh, getIndexRowFromKey(sh, getNameMethod()));
        loginPage.goToLogin();
        loginPage.checkHiddenText(Locator.LOGIN_TXT_USER_NAME,TEXT_BOX_USERNAME);
        loginPage.inputUserName(dataLogin.get("User name"));
    }
    @Test(description = "Kiểm tra sđt > 10 số")
    public void LG_2(){
        dataLogin = getTestDataInMap(sh, getIndexRowFromKey(sh, getNameMethod()));
        loginPage.inputUserName(dataLogin.get("User name"));
    }
    @Test(description = "Kiểm tra sđt < 10 số ")
    public void LG_3(){
        dataLogin = getTestDataInMap(sh, getIndexRowFromKey(sh, getNameMethod()));
        loginPage.inputUserName(dataLogin.get("User name"));
    }
    @Test(description = "Kiểm tra sđt đầu số khác 0")
    public void LG_4(){
        dataLogin = getTestDataInMap(sh, getIndexRowFromKey(sh, getNameMethod()));
        loginPage.inputUserName(dataLogin.get("User name"));
    }
    @Test(priority = 1, description = "Đăng nhập thất bại với mật khẩu sai")
    public void LG_5(){
        dataLogin = getTestDataInMap(sh, getIndexRowFromKey(sh, getNameMethod()));
        loginPage.inputUserName(dataLogin.get("User name"));
        loginPage.inputPassWord(dataLogin.get("Pass word"));
        loginPage.compareMessLoginIncorrectPass(dataLogin.get("User name"));
    }
    @Test(priority = 2, dependsOnMethods = "LG_5", description = "Kiểm tra text ẩn của trường mật khẩu")
    public void LG_6(){
        loginPage.checkHiddenText(Locator.LOGIN_TXT_PASSWORD,TEXT_BOX_PASSWORD);
    }
    @Severity(CRITICAL)
    @Test(priority = 3, dependsOnMethods = "LG_5", description = "Đăng nhập thành công")
    public void LG_7_8(){
        loginPage.goBack();
        dataLogin = getTestDataInMap(sh, getIndexRowFromKey(sh, getNameMethod()));
        loginPage.inputUserName(dataLogin.get("User name"));
        loginPage.inputPassWord(dataLogin.get("Pass word"));
        loginPage.compareMessLoginSuccess();
    }
    @Severity(CRITICAL)
    @Test(priority = 4, dependsOnMethods = "LG_7_8" ,description = "Đăng xuất thất bại")
    public void LG_24(){
        loginPage.viewUserInform();
        loginPage.logOut("Thất bại");
    }
    @Severity(CRITICAL)
    @Test(priority = 5, dependsOnMethods = "LG_24" ,description = "Đăng xuất thành công")
    public void LG_25(){
        loginPage.logOut("Thành công");
    }
    @Severity(CRITICAL)
    @Test(priority = 6, dependsOnMethods = "LG_25" ,description = "Đăng nhập thành công qua 'Đăng nhập SmartTv Website'")
    public void LG_9(){
        loginPage.logInBy("Smart Tv, Website", dataLogin.get("User name"), dataLogin.get("Pass word"));
        loginPage.checkScreenInform("Smart Tv, Website");
        loginPage.viewUserInform();
        loginPage.logOut("Thành công");
    }
    @Severity(CRITICAL)
    @Test(priority = 7, dependsOnMethods = "LG_9" ,description = "Đăng nhập thành công qua 'Quản lý thiết bị'")
    public void LG_10(){
        loginPage.logInBy("Quản lý thiết bị",dataLogin.get("User name"), dataLogin.get("Pass word"));
        loginPage.checkScreenInform("Quản lý thiết bị");
        loginPage.viewUserInform();
        loginPage.logOut("Thành công");
    }
    @Severity(CRITICAL)
    @Test(priority = 8, dependsOnMethods = "LG_10" ,description = "Đăng nhập thành công qua 'Thông báo'")
    public void LG_11(){
        loginPage.logInBy("Thông báo",dataLogin.get("User name"), dataLogin.get("Pass word"));
        loginPage.checkScreenInform("Thông báo");
        loginPage.viewUserInform();
        loginPage.logOut("Thành công");
    }
    @Test(priority = 9 ,dependsOnMethods = "LG_10", description = "Đăng xuất thiết bị không thành công")
    public void LG_27(){
        loginPage.goToLogin();
        loginPage.login(dataLogin.get("User name"), dataLogin.get("Pass word"));
        keyword.click(Locator.MENU_BTN_QUAN_LY);
        loginPage.logOutDevice("Thất bại");
    }
    @Test(priority = 10, dependsOnMethods = "LG_27" ,description = "Đăng xuất thiết bị thành công")
    public void LG_26(){
        loginPage.logOutDevice("Thành công");
    }
    @Severity(CRITICAL)
    @Test(priority = 11, dependsOnMethods = "LG_26", description = "Kiểm tra đăng nhập 2 sdt trên 1 thiết bị")
    public void LG_12(){
        dataLogin = getTestDataInMap(sh, getIndexRowFromKey(sh, getNameMethod()));
        loginPage.goToLogin();
        loginPage.login(dataLogin.get("User name") ,dataLogin.get("Pass word"));
        loginPage.viewUserInform();
        profilePage.checkUserInform(stmt, dataLogin.get("User name"),"all");
    }
    @Severity(CRITICAL)
    @Test(priority = 12,dependsOnMethods = "LG_12", description = "Kiểm tra đăng nhập với sdt chưa đăng ký")
    public void LG_13(){
        loginPage.logOut("Thành công");
        loginPage.goToLogin();
        int indexRow = getIndexRowFromKey(sh, getNameMethod());
        String phone = loginPage.getPhoneNumber();
        setCell(workbook ,sh ,phone, indexRow ,getIndexCellFromKey(sh,"User name"));
        dataLogin = getTestDataInMap(sh, indexRow);
        loginPage.inputUserName(dataLogin.get("User name"));
    }
    @Test(priority = 13, dependsOnMethods = "LG_13" ,description = "Kiểm tra nhập mã OTP sai")
    public void LG_14(){
        dataLogin = getTestDataInMap(sh, getIndexRowFromKey(sh, getNameMethod()));
        loginPage.inputOtp(dataLogin.get("OTP"));
        loginPage.continueOtp("không tồn tại");
    }
    @Test(priority = 14 ,description = "Kiểm tra đăng nhập khi tài khoản đăng nhập 3 thiết bị")
    public void LG_21(){
    }
    @Test(priority = 13 ,description = "Kiểm tra hủy login khi tài khoản đăng nhập 3 thiết bị")
    public void LG_22(){

    }
    @AfterClass
    public void closeConnect() throws SQLException {
        if(con != null) {
            con.close();
        }
    }
}
