package tests;

import core.BaseTest;
import helpers.DataBase;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import pages.HomePage;
import pages.LoginPage;
import pages.ProfilePage;
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
    @Test(priority = 3, dependsOnMethods = "PF_2", description = "Kiểm tra cập nhật tên hiển thị thành công")
    public void PF_3(){
        profilePage.clickEdit();
        profilePage.editFullName("USER_NAME");
        profilePage.saveInform("Thành công");
        loginPage.checkUserInform("PHONE_NUMBER","name");
    }
    @Test(priority = 4, dependsOnMethods = "PF_3", description = "Kiểm tra cập nhật tên nhưng không lưu")
    public void PF_5(){
        profilePage.clickEdit();
        String name = loginPage.getUserInform("name");
        profilePage.editEmail("DB_EMAIL");
        loginPage.goBack();
        loginPage.viewUserInform();
        keyword.assertEqualMultiData(name, loginPage.getUserInform("name"));
    }
    @Test(priority = 6, dependsOnMethods = "PF_5",description = "Kiểm tra cập nhật profile khi để trống tên")
    public void PF_4(){
        profilePage.clickEdit();
        profilePage.editFullName("");
        profilePage.saveInform("Tên thất bại");
    }
    @Test(priority = 7, dependsOnMethods = "PF_4",description = "Kiểm tra cập nhật email thành công")
    public void PF_6(){
        loginPage.goBack();
        loginPage.viewUserInform();
        profilePage.clickEdit();
        profilePage.editEmail("DB_EMAIL");
        profilePage.saveInform("Thành công");
        loginPage.checkUserInform("PHONE_NUMBER","email");
    }
    @Test(priority = 8, dependsOnMethods = "PF_6",description = "Kiểm tra khi để trống mail")
    public void PF_8(){
        profilePage.clickEdit();
        profilePage.editEmail("");
        profilePage.saveInform("Thành công");
    }
    @Test(priority = 9,dependsOnMethods = "PF_8", description = "Kiểm tra  khi nhập sai định dạng email")
    public void PF_9(){
        profilePage.clickEdit();
        profilePage.editEmail("EMAIL_INVALID_1");
        profilePage.saveInform("Email thất bại");
    }
    @Test(priority = 10, dependsOnMethods = "PF_9", description = "Kiểm tra  khi nhập sai định dạng email")
    public void PF_10(){
        profilePage.clickEdit();
        profilePage.editEmail("EMAIL_INVALID_2");
        profilePage.saveInform("Email thất bại");
    }
    @Test(priority = 11, dependsOnMethods = "PF_10",description = "Kiểm tra  khi nhập sai định dạng email")
    public void PF_11(){
        profilePage.clickEdit();
        profilePage.editEmail("EMAIL_INVALID_3");
        profilePage.saveInform("Email thất bại");
    }
    @Test(priority = 12, dependsOnMethods = "PF_11",description = "Kiểm tra  khi đổi email nhưng không lưu")
    public void PF_7(){
        profilePage.clickEdit();
        String name = loginPage.getUserInform("name");
        profilePage.editEmail("DB_EMAIL");
        loginPage.goBack();
        loginPage.viewUserInform();
        keyword.assertEqualData(name, loginPage.getUserInform("name"));
    }

    @Test(priority = 13, dependsOnMethods = "PF_7",description = "Kiểm tra cập nhật ngày sinh thành công")
    public void PF_12(){
        profilePage.clickEdit();
        profilePage.editBirthDay(getCurrentDateTime("dd MMMM yyyy"),"oke");
        profilePage.saveInform("Thành công");
        loginPage.checkUserInform("PHONE_NUMBER","birth day");
    }
    @Test(priority = 14, dependsOnMethods = "PF_12",description = "Kiểm tra cập nhật ngày sinh nhưng không lưu, hủy cập nhật ngày sinh")
    public void PF_13_14(){
        profilePage.clickEdit();
        String birthDay = loginPage.getUserInform("birth day");
        profilePage.editBirthDay(getCurrentDateTime("dd MMMM yyyy"), "oke");
        loginPage.goBack();
        loginPage.viewUserInform();
        keyword.assertEqualData(birthDay, loginPage.getUserInform("birth day"));
        profilePage.clickEdit();
        profilePage.editBirthDay(getCurrentDateTime("dd MMMM yyyy"),"cancel");
        keyword.assertEqualData(birthDay, loginPage.getUserInform("birth day"));
    }
    @Test(priority = 15, dependsOnMethods = "PF_13_14",description = "Kiểm tra cập nhật giới tính thành công")
    public void PF_15(){
        profilePage.editGender("male");
        profilePage.saveInform("Thành công");
        loginPage.checkUserInform("PHONE_NUMBER","gender");
    }
    @Test(priority = 16, dependsOnMethods = "PF_15",description = "Kiểm tra cập nhật giới tính nhưng không lưu")
    public void PF_16(){
        String gender = loginPage.getUserInform("gender");
        profilePage.clickEdit();
        profilePage.editGender("female");
        loginPage.goBack();
        loginPage.viewUserInform();
        keyword.assertEqualData(gender, loginPage.getUserInform("gender"));
    }
    @Test(priority = 18, description = "Kiểm tra thay đổi avatar thành công")
    public void PF_17(){
        profilePage.clickEdit();
        profilePage.editAvatar();
        profilePage.saveInform("Thành công");
    }
}
