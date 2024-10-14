package tests.LoginSignUp;
import core.BaseTest;
import helpers.DataBase;
import io.qameta.allure.Severity;
import locator.Locator;
import org.testng.annotations.*;
import pages.HomePage;
import pages.LoginSignUp.LoginPage;
import pages.LoginSignUp.SignUpPage;

import java.util.HashMap;

import static constant.Constant.*;
import static helpers.PathHelper.getNameMethod;
import static io.qameta.allure.SeverityLevel.CRITICAL;
import static utilities.ReadExcel.*;

public class LoginTest extends BaseTest {
    public DataBase dataBase ;
    public LoginPage loginPage;
    public SignUpPage signUpPage;
    public HomePage homePage;
    private HashMap<String, String> dataLogin;
    public LoginTest(){
        loginPage = new LoginPage();
        homePage = new HomePage();
        dataBase = new DataBase();
        signUpPage = new SignUpPage();
    }
    @BeforeClass
    public void firstSteps(){
        ExcelOperations("Login");
//        dataBase.setUpDB("POSTGRES_DB_URL","POSTGRES_DB_USER","POSTGRES_DB_PASSWORD");
        loginPage.isUserLogout();
    }
    @Test(description = "Kiểm tra text ẩn, nhập sđt bỏ trống")
    public void LG_1(){
        dataLogin = getTestDataInMap(getIndexRowFromKey(getNameMethod()));
        loginPage.goToLogin();
        loginPage.checkHiddenText(Locator.LOGIN_TXT_USER_NAME,TEXT_BOX_USERNAME);
        loginPage.inputUserName(dataLogin.get("User name"));
    }
    @Test(description = "Kiểm tra sđt > 10 số")
    public void LG_2(){
        dataLogin = getTestDataInMap(getIndexRowFromKey(getNameMethod()));
        loginPage.inputUserName(dataLogin.get("User name"));
    }
    @Test(description = "Kiểm tra sđt < 10 số ")
    public void LG_3(){
        dataLogin = getTestDataInMap(getIndexRowFromKey(getNameMethod()));
        loginPage.inputUserName(dataLogin.get("User name"));
    }
    @Test(description = "Kiểm tra sđt đầu số khác 0")
    public void LG_4(){
        dataLogin = getTestDataInMap(getIndexRowFromKey(getNameMethod()));
        loginPage.inputUserName(dataLogin.get("User name"));
    }
    @Test(priority = 1, description = "Đăng nhập thất bại với mật khẩu sai")
    public void LG_5(){
        dataLogin = getTestDataInMap(getIndexRowFromKey(getNameMethod()));
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
        dataLogin = getTestDataInMap(getIndexRowFromKey(getNameMethod()));
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
    @Test(priority = 9 ,description = "Đăng xuất thiết bị không thành công")
    public void LG_27(){
        loginPage.login(dataLogin.get("User name"), dataLogin.get("Pass word"));
        keyword.click(Locator.MENU_BTN_QUAN_LY);
        loginPage.logOutDevice("Thất bại");
    }
    @Test(priority = 10, dependsOnMethods = "LG_27" ,description = "Đăng xuất thiết bị thành công")
    public void LG_26(){
        loginPage.logOutDevice("Thành công");
    }
    @Severity(CRITICAL)
    @Test(priority = 11, description = "Kiểm tra đăng nhập 2 sdt trên 1 thiết bị")
    public void LG_12(){
        dataLogin = getTestDataInMap(getIndexRowFromKey(getNameMethod()));
        loginPage.login(dataLogin.get("User name") ,dataLogin.get("Pass word"));
        loginPage.viewUserInform();
        loginPage.checkUserInform(dataLogin.get("User name"),"all");
    }
    @Severity(CRITICAL)
    @Test(priority = 12,description = "Kiểm tra đăng nhập với sdt chưa đăng ký")
    public void LG_13(){
        loginPage.logOut("Thành công");
        loginPage.goToLogin();
        int indexRow = getIndexRowFromKey(getNameMethod());
        String phone = loginPage.getPhoneNumber();
        setCell(phone, indexRow ,getIndexCellFromKey("User name"));
        dataLogin = getTestDataInMap(indexRow);
        loginPage.inputUserName(dataLogin.get("User name"));
    }
    @Test(priority = 13, dependsOnMethods = "LG_13" ,description = "Kiểm tra nhập mã OTP sai")
    public void LG_14(){
        dataLogin = getTestDataInMap(getIndexRowFromKey(getNameMethod()));
        loginPage.inputOtp(dataLogin.get("OTP"));
        loginPage.continueOtp("không tồn tại");
    }
    @Test(priority = 14 ,description = "Kiểm tra đăng nhập khi tài khoản đăng nhập 3 thiết bị")
    public void LG_21(){
    }
    @Test(priority = 13 ,description = "Kiểm tra hủy login khi tài khoản đăng nhập 3 thiết bị")
    public void LG_22(){

    }
}
