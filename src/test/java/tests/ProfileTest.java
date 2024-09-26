package tests;

import constant.Constant;
import core.BaseTest;
import locator.Locator;
import org.testng.annotations.Test;
import pages.HomePage;
import pages.LoginPage;
import pages.ProfilePage;

public class ProfileTest extends BaseTest {
    public LoginPage loginPage;
    public ProfilePage profilePage;

    public ProfileTest(){
        loginPage = new LoginPage();
        profilePage = new ProfilePage();
    }
    @Test(description = "Kiểm tra cập nhật profile khi nhập các thông tin hợp lệ")
    public void PF_1(){
        loginPage.loginSuccess("PHONE_NUMBER","PASS_WORD");
        loginPage.viewUserInform();
        //        profilePage.updateFullInform("USER_NAME","DB_EMAIL","","");
//        profilePage.updateInformSuccess();
    }
    @Test(priority = 1, description = "Kiểm tra cập nhật tên hiển thị thành công")
    public void PF_2(){
        profilePage.clickEdit();
        profilePage.editFullName("USER_NAME");
        profilePage.clickSave();
    }
    @Test(priority = 2, description = "Kiểm tra cập nhật email thành công")
    public void PF_3(){
        profilePage.clickEdit();
        profilePage.editEmail("DB_EMAIL");
        profilePage.clickSave();
    }
    @Test(priority = 3, description = "Kiểm tra cập nhật ngày sinh thành công")
    public void PF_4(){
        profilePage.clickEdit();
        profilePage.editBirthDay("");
        profilePage.clickSave();
    }
    @Test(priority = 4, description = "Kiểm tra cập nhật giới tính thành công")
    public void PF_5(){
        profilePage.clickEdit();
        profilePage.editBirthDay("");
        profilePage.clickSave();
    }
    @Test(priority = 5, description = "Kiểm tra khi để trống tên")
    public void PF_6(){
        profilePage.clickEdit();
        profilePage.editFullName("");
        profilePage.clickSave();
    }
    @Test(priority = 5, description = "Kiểm tra khi để trống mail")
    public void PF_7(){
        profilePage.clickEdit();
        profilePage.editEmail("");
        profilePage.clickSave();
    }
}
