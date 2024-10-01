package tests;
import core.BaseTest;
import helpers.DataBase;
import io.qameta.allure.Severity;
import locator.Locator;
import org.testng.annotations.*;
import pages.HomePage;
import pages.LoginPage;
import static constant.Constant.*;
import static io.qameta.allure.SeverityLevel.CRITICAL;

public class LoginTest extends BaseTest {
    public DataBase dataBase ;
    public LoginPage loginPage;
    public HomePage homePage;
    public LoginTest(){
        loginPage = new LoginPage();
        homePage = new HomePage();
        dataBase = new DataBase();
    }
    @BeforeClass
    public void connectDb(){
        dataBase.setUpDB("POSTGRES_DB_URL","POSTGRES_DB_USER","POSTGRES_DB_PASSWORD");
    }
    @Test(description = "Kiểm tra text ẩn, nhập sđt bỏ trống, >,< 10 số và đầu số khác 0")
    public void LG_11_12_13_14_42(){
        loginPage.goToLogin();
        loginPage.checkHiddenText(Locator.LOGIN_TXT_USER_NAME,TEXT_BOX_USERNAME);
        loginPage.inputUserName("PHONE_NUMBER_INVALID_1");
        loginPage.inputUserName("PHONE_NUMBER_INVALID_2");
        loginPage.inputUserName("PHONE_NUMBER_INVALID_3");
    }
    @Test(priority = 1, description = "Đăng nhập thất bại với mật khẩu sai và check text ẩn")
    public void LG_9_43(){
        loginPage.inputUserName("PHONE_NUMBER");
        loginPage.checkHiddenText(Locator.LOGIN_TXT_PASSWORD,TEXT_BOX_PASSWORD);
        loginPage.inputPassWord("PASS_WORD_INCORRECT");
        loginPage.compareMessLoginIncorrectPass();
    }
    @Severity(CRITICAL)
    @Test(priority = 1, dependsOnMethods = "LG_9_43", description = "Đăng nhập thành công")
    public void LG_1_2(){
        loginPage.inputPassWord("PASS_WORD");
        loginPage.compareMessLoginSuccess();
    }
    @Severity(CRITICAL)
    @Test(priority = 2, dependsOnMethods = "LG_1_2" ,description = "Đăng xuất thất bại")
    public void LG_44(){
        loginPage.viewUserInform();
        loginPage.logOut("Thất bại");
    }
    @Severity(CRITICAL)
    @Test(priority = 3, dependsOnMethods = "LG_44" ,description = "Đăng xuất thành công")
    public void LG_45(){
        loginPage.logOut("Thành công");
    }
    @Severity(CRITICAL)
    @Test(priority = 3, dependsOnMethods = "LG_45" ,description = "Đăng nhập thành công qua 'Đăng nhập SmartTv Website'")
    public void LG_3(){
        loginPage.logInBy("Smart Tv, Website");
        loginPage.checkScreenInform("Smart Tv, Website");
        loginPage.viewUserInform();
        loginPage.logOut("Thành công");
    }
    @Severity(CRITICAL)
    @Test(priority = 4, dependsOnMethods = "LG_3" ,description = "Đăng nhập thành công qua 'Quản lý thiết bị'")
    public void LG_4(){
        loginPage.logInBy("Quản lý thiết bị");
        loginPage.checkScreenInform("Quản lý thiết bị");
        loginPage.viewUserInform();
        loginPage.logOut("Thành công");
    }
    @Severity(CRITICAL)
    @Test(priority = 5, dependsOnMethods = "LG_4" ,description = "Đăng nhập thành công qua 'Thông báo'")
    public void LG_5(){
        loginPage.logInBy("Thông báo");
        loginPage.checkScreenInform("Thông báo");
        loginPage.viewUserInform();
        loginPage.logOut("Thành công");
    }
    @Test(priority = 6 ,description = "Đăng xuất thiết bị không thành công")
    public void LG_47(){
        loginPage.loginSuccess("PHONE_NUMBER","PASS_WORD");
        keyword.click(Locator.SETTING_BTN_QUAN_LY);
        loginPage.logOutDevice("Thất bại");
    }
    @Test(priority = 7, dependsOnMethods = "LG_47" ,description = "Đăng xuất thiết bị thành công")
    public void LG_46(){
        loginPage.logOutDevice("Thành công");
    }
    @Severity(CRITICAL)
    @Test(priority = 8, description = "Kiểm tra đăng nhập 2 sdt trên 1 thiết bị")
    public void LG_37(){
        loginPage.loginSuccess("PHONE_NUMBER","PASS_WORD");
        loginPage.viewUserInform();
        loginPage.checkUserInform("PHONE_NUMBER","all");
    }
    @Severity(CRITICAL)
    @Test(priority = 9,description = "Kiểm tra đăng nhập với sdt chưa đăng ký")
    public void LG_10(){
        loginPage.logOut("Thành công");
        loginPage.goToLogin();
        loginPage.inputUserName("PHONE_NUMBER_NOT_REGISTER");
    }
    @Test(priority = 10, dependsOnMethods = "LG_10" ,description = "Kiểm tra nhập mã OTP sai")
    public void LG_21(){
        loginPage.inputOtp("OTP_INVALID","không tồn tại");
    }
    @Test(priority = 12 ,description = "Kiểm tra đăng nhập khi tài khoản đăng nhập 3 thiết bị")
    public void LG_39(){
    }
    @Test(priority = 13 ,description = "Kiểm tra hủy login khi tài khoản đăng nhập 3 thiết bị")
    public void LG_40(){

    }
}
