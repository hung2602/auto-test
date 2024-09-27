package tests;

import constant.Constant;
import core.BaseTest;
import helpers.DataBase;
import locator.Locator;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import pages.HomePage;
import pages.LoginPage;
import pages.ProfilePage;

public class ProfileTest extends BaseTest {
    public LoginPage loginPage;
    public ProfilePage profilePage;
    public DataBase dataBase ;

    public ProfileTest(){
        loginPage = new LoginPage();
        profilePage = new ProfilePage();
        dataBase = new DataBase();
    }
    @BeforeTest
    public void connectDb(){
        dataBase.setUpDB("POSTGRES_DB_URL","POSTGRES_DB_USER","POSTGRES_DB_PASSWORD");
    }
    @Test(description = "Kiểm tra cập nhật profile khi nhập các thông tin hợp lệ")
    public void PF_1(){
        loginPage.loginSuccess("PHONE_NUMBER","PASS_WORD");
        loginPage.viewUserInform();
        profilePage.updateFullInform("USER_NAME","DB_EMAIL");
        profilePage.saveInform("Thành công");
        loginPage.checkUserInform("PHONE_NUMBER");
    }
    @Test(priority = 1, description = "Kiểm tra cập nhật tên hiển thị thành công")
    public void PF_2(){
        profilePage.clickEdit();
        profilePage.editFullName("USER_NAME");
        profilePage.saveInform("Thành công");
    }
    @Test(priority = 2, description = "Kiểm tra cập nhật email thành công")
    public void PF_3(){
        profilePage.clickEdit();
        profilePage.editEmail("DB_EMAIL");
        profilePage.saveInform("Thành công");
    }
    @Test(priority = 3, description = "Kiểm tra cập nhật ngày sinh thành công")
    public void PF_4(){
        profilePage.clickEdit();
        profilePage.editBirthDay();
        profilePage.saveInform("Thành công");
    }
    @Test(priority = 4, description = "Kiểm tra cập nhật giới tính thành công")
    public void PF_5(){
        profilePage.clickEdit();
        profilePage.editGender();
        profilePage.saveInform("Thành công");
    }
    @Test(priority = 5, description = "Kiểm tra khi để trống tên")
    public void PF_6(){
        profilePage.clickEdit();
        profilePage.editFullName("");
        profilePage.saveInform("Thất bại");
    }
    @Test(priority = 5, description = "Kiểm tra khi để trống mail")
    public void PF_7(){
        profilePage.clickEdit();
        profilePage.editEmail("");
        profilePage.saveInform("Thất bại");
    }
    @Test(priority = 5, description = "Kiểm tra  khi nhập sai định dạng email")
    public void PF_8(){
        profilePage.clickEdit();
        profilePage.editEmail("EMAIL_INVALID_1");
        profilePage.saveInform("Thất bại");
    }
    @Test(priority = 5, description = "Kiểm tra  khi nhập sai định dạng email")
    public void PF_9(){
        profilePage.clickEdit();
        profilePage.editEmail("EMAIL_INVALID_2");
        profilePage.saveInform("Thất bại");
    }
    @Test(priority = 5, description = "Kiểm tra  khi nhập sai định dạng email")
    public void PF_10(){
        profilePage.clickEdit();
        profilePage.editEmail("EMAIL_INVALID_3");
        profilePage.saveInform("Thất bại");
    }
    @Test(priority = 11, description = "Kiểm tra thay đổi avatar thất bại")
    public void PF_12(){
        profilePage.clickEdit();
        profilePage.editEmail("");
        profilePage.saveInform("Thất bại");
    }
    @Test(priority = 12, description = "Kiểm tra thay đổi avatar thành công")
    public void PF_11(){
        profilePage.clickEdit();
        profilePage.editEmail("");
        profilePage.saveInform("Thành công");
    }
}
