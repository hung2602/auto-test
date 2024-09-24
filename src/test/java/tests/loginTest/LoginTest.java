package tests.loginTest;
import core.BaseTest;
import core.MyListener;
import io.qameta.allure.Link;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import org.openqa.selenium.JavascriptExecutor;
import org.testng.annotations.*;
import pages.homePage.HomePage;
import pages.loginPage.LoginPage;
import javax.management.Descriptor;

import static core.MyListener.saveScreenshotPNG;
import static io.qameta.allure.SeverityLevel.CRITICAL;

public class LoginTest extends BaseTest {
    public LoginPage loginPage;
    public HomePage homePage;
    public LoginTest(){
        loginPage = new LoginPage(this.keyword);
        homePage = new HomePage(this.keyword);
    }
    @Test(description = "Đăng nhập thất bại với mật khẩu sai")
    public void LG_9(){
//        homePage.skipBanner();
        loginPage.login("PHONE_NUMBER","PASS_WORD_INCORRECT");
        loginPage.loginIncorrectPass();
    }
    @Severity(CRITICAL)
    @Test(priority = 1, dependsOnMethods = "LG_9", description = "Đăng nhập thành công")
    public void LG_1_2(){
        loginPage.inputPassWord("PASS_WORD");
        loginPage.loginSuccess();
    }
    @Severity(CRITICAL)
    @Test(priority = 2, dependsOnMethods = "LG_1_2" ,description = "Đăng xuất thất bại, thành công")
    public void LG_41_42(){
        loginPage.viewUserInform();
        loginPage.logOut("thất bại");
        loginPage.logOut("thành công");
    }
    @Severity(CRITICAL)
    @Test(priority = 3, dependsOnMethods = "LG_41_42" ,description = "Đăng nhập thành công qua 'Đăng nhập SmartTv Website'")
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
}
