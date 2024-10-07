package tests;
import core.BaseTest;
import helpers.DataBase;
import io.qameta.allure.Severity;
import locator.Locator;
import org.testng.annotations.*;
import pages.HomePage;
import pages.LoginPage;
import utilities.ReadExcel;

import java.util.HashMap;

import static constant.Constant.*;
import static helpers.PathHelper.getNameMethod;
import static io.qameta.allure.SeverityLevel.CRITICAL;
import static utilities.ReadExcel.*;

public class LoginTest extends BaseTest {
    public DataBase dataBase ;
    public LoginPage loginPage;
    public HomePage homePage;
    private HashMap<String, String> dataLogin;

    public LoginTest(){
        loginPage = new LoginPage();
        homePage = new HomePage();
        dataBase = new DataBase();
    }
    @BeforeClass
    public void setDb(){
        ExcelOperations("Login");
        dataBase.setUpDB("POSTGRES_DB_URL","POSTGRES_DB_USER","POSTGRES_DB_PASSWORD");
    }
    @Test(description = "Kiểm tra text ẩn, nhập sđt bỏ trống")
    public void LG_11(){
        dataLogin = getTestDataInMap(getRowFromKey(getNameMethod()));
        loginPage.goToLogin();
        loginPage.checkHiddenText(Locator.LOGIN_TXT_USER_NAME,TEXT_BOX_USERNAME);
        loginPage.inputUserName(dataLogin.get("User name"));
    }
    @Test(description = "Kiểm tra sđt > 10 số")
    public void LG_12(){
        dataLogin = getTestDataInMap(getRowFromKey(getNameMethod()));
        loginPage.inputUserName(dataLogin.get("User name"));
    }
    @Test(description = "Kiểm tra sđt < 10 số ")
    public void LG_13(){
        dataLogin = getTestDataInMap(getRowFromKey(getNameMethod()));
        loginPage.inputUserName(dataLogin.get("User name"));
    }
    @Test(description = "Kiểm tra sđt đầu số khác 0")
    public void LG_14(){
        dataLogin = getTestDataInMap(getRowFromKey(getNameMethod()));
        loginPage.inputUserName(dataLogin.get("User name"));
    }
    @Test(priority = 1, description = "Đăng nhập thất bại với mật khẩu sai")
    public void LG_9(){
        dataLogin = getTestDataInMap(getRowFromKey(getNameMethod()));
        loginPage.inputUserName(dataLogin.get("User name"));
        loginPage.inputPassWord(dataLogin.get("Pass word"));
        loginPage.compareMessLoginIncorrectPass();
    }
    @Severity(CRITICAL)
    @Test(priority = 2, dependsOnMethods = "LG_9", description = "Kiểm tra text ẩn của trường mật khẩu")
    public void LG_43(){
        loginPage.checkHiddenText(Locator.LOGIN_TXT_PASSWORD,TEXT_BOX_PASSWORD);
    }
    @Severity(CRITICAL)
    @Test(priority = 3, dependsOnMethods = "LG_9", description = "Đăng nhập thành công")
    public void LG_1_2(){
        loginPage.goBack();
        dataLogin = getTestDataInMap(getRowFromKey(getNameMethod()));
        loginPage.inputUserName(dataLogin.get("User name"));
        loginPage.inputPassWord(dataLogin.get("Pass word"));
        loginPage.compareMessLoginSuccess();
    }
    @Severity(CRITICAL)
    @Test(priority = 4, dependsOnMethods = "LG_1_2" ,description = "Đăng xuất thất bại")
    public void LG_44(){
        loginPage.viewUserInform();
        loginPage.logOut("Thất bại");
    }
    @Severity(CRITICAL)
    @Test(priority = 5, dependsOnMethods = "LG_44" ,description = "Đăng xuất thành công")
    public void LG_45(){
        loginPage.logOut("Thành công");
    }
    @Severity(CRITICAL)
    @Test(priority = 6, dependsOnMethods = "LG_45" ,description = "Đăng nhập thành công qua 'Đăng nhập SmartTv Website'")
    public void LG_3(){
        loginPage.logInBy("Smart Tv, Website", dataLogin.get("User name"), dataLogin.get("Pass word"));
        loginPage.checkScreenInform("Smart Tv, Website");
        loginPage.viewUserInform();
        loginPage.logOut("Thành công");
    }
    @Severity(CRITICAL)
    @Test(priority = 7, dependsOnMethods = "LG_3" ,description = "Đăng nhập thành công qua 'Quản lý thiết bị'")
    public void LG_4(){
        loginPage.logInBy("Quản lý thiết bị",dataLogin.get("User name"), dataLogin.get("Pass word"));
        loginPage.checkScreenInform("Quản lý thiết bị");
        loginPage.viewUserInform();
        loginPage.logOut("Thành công");
    }
    @Severity(CRITICAL)
    @Test(priority = 8, dependsOnMethods = "LG_4" ,description = "Đăng nhập thành công qua 'Thông báo'")
    public void LG_5(){
        loginPage.logInBy("Thông báo",dataLogin.get("User name"), dataLogin.get("Pass word"));
        loginPage.checkScreenInform("Thông báo");
        loginPage.viewUserInform();
        loginPage.logOut("Thành công");
    }
    @Test(priority = 9 ,description = "Đăng xuất thiết bị không thành công")
    public void LG_47(){
        loginPage.loginSuccess(dataLogin.get("User name"), dataLogin.get("Pass word"));
        keyword.click(Locator.MENU_BTN_QUAN_LY);
        loginPage.logOutDevice("Thất bại");
    }
    @Test(priority = 10, dependsOnMethods = "LG_47" ,description = "Đăng xuất thiết bị thành công")
    public void LG_46(){
        loginPage.logOutDevice("Thành công");
    }
    @Severity(CRITICAL)
    @Test(priority = 11, description = "Kiểm tra đăng nhập 2 sdt trên 1 thiết bị")
    public void LG_37(){
        dataLogin = getTestDataInMap(getRowFromKey(getNameMethod()));
        loginPage.loginSuccess(dataLogin.get("User name") ,dataLogin.get("Pass word"));
        loginPage.viewUserInform();
        loginPage.checkUserInform(dataLogin.get("User name"),"all");
    }
    @Severity(CRITICAL)
    @Test(priority = 12,description = "Kiểm tra đăng nhập với sdt chưa đăng ký")
    public void LG_10(){
        loginPage.logOut("Thành công");
        loginPage.goToLogin();
        dataLogin = getTestDataInMap(getRowFromKey(getNameMethod()));
        loginPage.inputUserName(dataLogin.get("User name"));
    }
    @Test(priority = 13, dependsOnMethods = "LG_10" ,description = "Kiểm tra nhập mã OTP sai")
    public void LG_21(){
        loginPage.inputOtp("OTP_INVALID","không tồn tại");
    }
    @Test(priority = 14 ,description = "Kiểm tra đăng nhập khi tài khoản đăng nhập 3 thiết bị")
    public void LG_39(){
    }
    @Test(priority = 13 ,description = "Kiểm tra hủy login khi tài khoản đăng nhập 3 thiết bị")
    public void LG_40(){

    }
}
