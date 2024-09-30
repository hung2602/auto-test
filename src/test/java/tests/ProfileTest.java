package tests;

import constant.Constant;
import core.BaseTest;
import helpers.DataBase;
import locator.Locator;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import pages.HomePage;
import pages.LoginPage;
import pages.ProfilePage;

import static utilities.DateTime.getCurrentDate;
import static utilities.DateTime.getCurrentDateTime;

public class ProfileTest extends BaseTest {
    public LoginPage loginPage;
    public ProfilePage profilePage;
    public DataBase dataBase ;

    public ProfileTest(){
        loginPage = new LoginPage();
        profilePage = new ProfilePage();
        dataBase = new DataBase();
    }
    @BeforeClass
    public void connectDb(){
        dataBase.setUpDB("POSTGRES_DB_URL","POSTGRES_DB_USER","POSTGRES_DB_PASSWORD");
    }
    @Test(priority = 1, description = "Kiểm tra cập nhật profile khi nhập các thông tin hợp lệ")
    public void PF_1(){
        loginPage.loginSuccess("PHONE_NUMBER","PASS_WORD");
        loginPage.viewUserInform();
        profilePage.clickEdit();
        profilePage.updateFullInform("USER_NAME","DB_EMAIL");
        profilePage.saveInform("Thành công");
        loginPage.checkUserInform("PHONE_NUMBER","all");
    }
    @Test(priority = 2, description = "Kiểm tra cập nhật profile nhưng không lưu")
    public void PF_2(){
        profilePage.clickEdit();
        String userInform = loginPage.getUserInform("all");
        profilePage.updateFullInform("USER_NAME","DB_EMAIL");
        loginPage.goBack();
        loginPage.viewUserInform();
        keyword.assertEqualMultiData(userInform, loginPage.getUserInform("all"));
    }
    @Test(priority = 3, description = "Kiểm tra cập nhật tên hiển thị thành công")
    public void PF_3(){
        profilePage.clickEdit();
        profilePage.editFullName("USER_NAME");
        profilePage.saveInform("Thành công");
        loginPage.checkUserInform("PHONE_NUMBER","name");
    }
    @Test(priority = 4, description = "Kiểm tra cập nhật tên nhưng không lưu")
    public void PF_5(){
        profilePage.clickEdit();
        String name = loginPage.getUserInform("name");
        profilePage.editEmail("DB_EMAIL");
        loginPage.goBack();
        loginPage.viewUserInform();
        keyword.assertEqualMultiData(name, loginPage.getUserInform("name"));
    }
    @Test(priority = 5, description = "Kiểm tra cập nhật profile khi để trống tên")
    public void PF_4(){
        profilePage.clickEdit();
        profilePage.editFullName("");
        profilePage.saveInform("Thất bại");
    }
    @Test(priority = 6, description = "Kiểm tra cập nhật email thành công")
    public void PF_6(){
        loginPage.goBack();
        loginPage.viewUserInform();
        profilePage.clickEdit();
        profilePage.editEmail("DB_EMAIL");
        profilePage.saveInform("Thành công");
        loginPage.checkUserInform("PHONE_NUMBER","email");
    }
    @Test(priority = 7, description = "Kiểm tra khi để trống mail")
    public void PF_8(){
        profilePage.clickEdit();
        profilePage.editEmail("");
        profilePage.saveInform("Thành công");
    }
    @Test(priority = 8, description = "Kiểm tra  khi nhập sai định dạng email")
    public void PF_9(){
        profilePage.clickEdit();
        profilePage.editEmail("EMAIL_INVALID_1");
        profilePage.saveInform("Thất bại");
    }
    @Test(priority = 9, description = "Kiểm tra  khi nhập sai định dạng email")
    public void PF_10(){
        profilePage.clickEdit();
        profilePage.editEmail("EMAIL_INVALID_2");
        profilePage.saveInform("Thất bại");
    }
    @Test(priority = 10, description = "Kiểm tra  khi nhập sai định dạng email")
    public void PF_11(){
        profilePage.clickEdit();
        profilePage.editEmail("EMAIL_INVALID_3");
        profilePage.saveInform("Thất bại");
    }
    @Test(priority = 11, description = "Kiểm tra  khi đổi email nhưng không lưu")
    public void PF_7(){
        profilePage.clickEdit();
        String name = loginPage.getUserInform("name");
        profilePage.editEmail("DB_EMAIL");
        loginPage.goBack();
        loginPage.viewUserInform();
        keyword.assertEqualMultiData(name, loginPage.getUserInform("name"));

//        profilePage.clickEdit();
//        profilePage.editEmail("DB_EMAIL");
//        profilePage.saveInform("Thành công");
    }

    @Test(priority = 3, description = "Kiểm tra cập nhật ngày sinh thành công")
    public void PF_12(){
        profilePage.clickEdit();
        profilePage.editBirthDay(getCurrentDateTime("dd MMMM yyyy"));
        profilePage.saveInform("Thành công");
    }
    @Test(priority = 3, description = "Kiểm tra cập nhật ngày sinh nhưng không lưu")
    public void PF_13(){
        profilePage.clickEdit();
        profilePage.editBirthDay(getCurrentDateTime("dd MMMM yyyy"));
        profilePage.saveInform("Thành công");
    }
    @Test(priority = 3, description = "Kiểm tra hủy cập nhật ngày sinh")
    public void PF_14(){
        profilePage.clickEdit();
        profilePage.editBirthDay(getCurrentDateTime("dd MMMM yyyy"));
        profilePage.saveInform("Thành công");
    }
    @Test(priority = 4, description = "Kiểm tra cập nhật giới tính thành công")
    public void PF_15(){
        profilePage.clickEdit();
        profilePage.editGender();
        profilePage.saveInform("Thành công");
    }
    @Test(priority = 4, description = "Kiểm tra cập nhật giới tính nhưng không lưu")
    public void PF_16(){
        profilePage.clickEdit();
        profilePage.editGender();
        profilePage.saveInform("Thành công");
    }
    @Test(priority = 11, description = "Kiểm tra thay đổi avatar thất bại")
    public void PF_18(){
        profilePage.clickEdit();
        profilePage.editEmail("");
        profilePage.saveInform("Thất bại");
    }
    @Test(priority = 12, description = "Kiểm tra thay đổi avatar thành công")
    public void PF_17(){
        profilePage.clickEdit();
        profilePage.editEmail("");
        profilePage.saveInform("Thành công");
    }
}
