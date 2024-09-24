package tests.loginTest;
import constant.Constant;
import core.BaseTest;
import io.qameta.allure.Severity;
import locator.Locator;
import org.openqa.selenium.JavascriptExecutor;
import org.testng.annotations.*;
import pages.homePage.HomePage;
import pages.loginPage.LoginPage;
import static io.qameta.allure.SeverityLevel.CRITICAL;
import static io.qameta.allure.SeverityLevel.NORMAL;

public class LoginTest extends BaseTest {
    public LoginPage loginPage;
    public HomePage homePage;
    public LoginTest(){
        loginPage = new LoginPage(this.keyword);
        homePage = new HomePage(this.keyword);
    }
    @Test(description = "Kiểm tra text ẩn, nhập sđt bỏ trống, >,< 10 số và đầu số khác 0")
    public void LG_11_12_13_14_42(){
        loginPage.goToLogin();
        loginPage.checkHiddenText(Locator.LOGIN_TXT_USER_NAME, Constant.TEXT_BOX_USERNAME);
        loginPage.inputUserName("PHONE_NUMBER_INVALID_1");
        loginPage.inputUserName("PHONE_NUMBER_INVALID_2");
        loginPage.inputUserName("PHONE_NUMBER_INVALID_3");
    }
    @Test(priority = 1, description = "Đăng nhập thất bại với mật khẩu sai và check text ẩn")
    public void LG_9_43(){
        loginPage.inputUserName("PHONE_NUMBER");
        loginPage.checkHiddenText(Locator.LOGIN_TXT_PASSWORD, Constant.TEXT_BOX_PASSWORD);
        loginPage.inputPassWord("PASS_WORD_INCORRECT");
        loginPage.loginIncorrectPass();
    }
    @Severity(CRITICAL)
    @Test(priority = 1, dependsOnMethods = "LG_9_43", description = "Đăng nhập thành công")
    public void LG_1_2(){
        loginPage.inputPassWord("PASS_WORD");
        loginPage.loginSuccess();
    }
    @Severity(CRITICAL)
    @Test(priority = 2, dependsOnMethods = "LG_1_2" ,description = "Đăng xuất thất bại, thành công")
    public void LG_44_45(){
        loginPage.viewUserInform();
        loginPage.logOut("thất bại");
        loginPage.logOut("thành công");
    }
    @Severity(CRITICAL)
    @Test(priority = 3, dependsOnMethods = "LG_44_45" ,description = "Đăng nhập thành công qua 'Đăng nhập SmartTv Website'")
    public void LG_3(){
        loginPage.logInBy("Smart Tv, Website");
        loginPage.checkScreenInform("Smart Tv, Website");
        loginPage.viewUserInform();
        loginPage.logOut("thành công");
    }
    @Severity(CRITICAL)
    @Test(priority = 4, dependsOnMethods = "LG_3" ,description = "Đăng nhập thành công qua 'Quản lý thiết bị'")
    public void LG_4(){
        loginPage.logInBy("Quản lý thiết bị");
        loginPage.checkScreenInform("Quản lý thiết bị");
        loginPage.viewUserInform();
        loginPage.logOut("thành công");
    }
    @Severity(CRITICAL)
    @Test(priority = 5, dependsOnMethods = "LG_4" ,description = "Đăng nhập thành công qua 'Thông báo'")
    public void LG_5(){
        loginPage.logInBy("Thông báo");
        loginPage.checkScreenInform("Thông báo");
        loginPage.viewUserInform();
        loginPage.logOut("thành công");
    }
    @Severity(CRITICAL)
    @Test(priority = 6 ,description = "Kiểm tra đăng nhập với sdt chưa đăng ký")
    public void LG_10(){
        loginPage.goToLogin();
        loginPage.inputUserName("PHONE_NUMBER_NOT_REGISTER");
    }
    @Test(priority = 7, dependsOnMethods = "LG_10" ,description = "Kiểm tra nhập mã OTP sai")
    public void LG_21(){
        loginPage.inputOtp("OTP_INVALID","không tồn tại");
    }

}
