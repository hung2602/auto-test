package tests;

import core.BaseTest;
import helpers.DataBase;
import org.apache.poi.ss.usermodel.Sheet;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import pages.loginsignup.LoginPage;
import pages.ProfilePage;

import java.sql.Statement;
import java.util.HashMap;

import static helpers.PathHelper.getNameMethod;
import static utilities.DateTime.getCurrentDateTime;
import static utilities.ReadExcel.*;

public class ProfileTest extends BaseTest {
    private Sheet sh = null;
    public LoginPage loginPage;
    public ProfilePage profilePage;
    public DataBase dataBase ;
    private HashMap<String, String> dataProfile;
    private Statement stmt ;
    public ProfileTest(){
        loginPage = new LoginPage();
        profilePage = new ProfilePage();
        dataBase = new DataBase();
    }
    @BeforeClass
    public void firstSteps(){
        sh = readSheet(workbook , "Profile");
        stmt = dataBase.setUpDB("POSTGRES_DB_URL","POSTGRES_DB_USER","POSTGRES_DB_PASSWORD");
//        loginPage.isUserLogout();
    }
    @Test(priority = 1, description = "Kiểm tra cập nhật profile khi nhập các thông tin hợp lệ")
    public void PF_1(){
        loginPage.goToLogin();
        loginPage.login("PHONE_NUMBER","PASS_WORD");
        loginPage.viewUserInform();
        profilePage.clickEdit();
        dataProfile = getTestDataInMap(sh,getIndexRowFromKey(sh,getNameMethod()));
        profilePage.updateFullInform(dataProfile.get("Name"),dataProfile.get("Email"),dataProfile.get("Gender"));
        profilePage.saveInform("Thành công");
        profilePage.checkUserInform(stmt, "PHONE_NUMBER","all");
    }
    @Test(priority = 2, description = "Kiểm tra cập nhật profile nhưng không lưu")
    public void PF_2(){
        dataProfile = getTestDataInMap(sh, getIndexRowFromKey(sh, getNameMethod()));
        profilePage.clickEdit();
        String userInform = profilePage.getUserInform("all");
        profilePage.updateFullInform(dataProfile.get("Name"),dataProfile.get("Email"),dataProfile.get("Gender"));
        loginPage.goBack();
        loginPage.viewUserInform();
        keyword.assertEqualMultiData(userInform, profilePage.getUserInform("all"));
    }
    @Test(priority = 3, dependsOnMethods = "PF_2", description = "Kiểm tra cập nhật tên hiển thị thành công")
    public void PF_3(){
        dataProfile = getTestDataInMap(sh,getIndexRowFromKey(sh,getNameMethod()));
        profilePage.clickEdit();
        profilePage.editFullName(dataProfile.get("Name"));
        profilePage.saveInform("Thành công");
        profilePage. checkUserInform(stmt,"PHONE_NUMBER","name");
    }
    @Test(priority = 4, dependsOnMethods = "PF_3", description = "Kiểm tra cập nhật tên nhưng không lưu")
    public void PF_5(){
        profilePage.clickEdit();
        String name = profilePage.getUserInform("name");
        profilePage.editFullName(dataProfile.get("Name"));
        loginPage.goBack();
        loginPage.viewUserInform();
        keyword.assertEqualMultiData(name, profilePage.getUserInform("name"));
    }
    @Test(priority = 6, dependsOnMethods = "PF_5",description = "Kiểm tra cập nhật profile khi để trống tên")
    public void PF_4(){
        profilePage.clickEdit();
        profilePage.editFullName("");
        profilePage.saveInform("Tên thất bại");
    }
    @Test(priority = 7, dependsOnMethods = "PF_4",description = "Kiểm tra cập nhật email thành công")
    public void PF_6(){
        dataProfile = getTestDataInMap(sh,getIndexRowFromKey(sh,getNameMethod()));
        loginPage.goBack();
        loginPage.viewUserInform();
        profilePage.clickEdit();
        profilePage.editEmail(dataProfile.get("Email"));
        profilePage.saveInform("Thành công");
        profilePage.checkUserInform(stmt,"PHONE_NUMBER","email");
    }
    @Test(priority = 8, dependsOnMethods = "PF_6",description = "Kiểm tra khi để trống mail")
    public void PF_8(){
        profilePage.clickEdit();
        profilePage.editEmail("");
        profilePage.saveInform("Thành công");
    }
    @Test(priority = 9,dependsOnMethods = "PF_8", description = "Kiểm tra  khi nhập sai định dạng email")
    public void PF_9(){
        dataProfile = getTestDataInMap(sh,getIndexRowFromKey(sh,getNameMethod()));
        profilePage.clickEdit();
        profilePage.editEmail(dataProfile.get("Email"));
        profilePage.saveInform("Email thất bại");
    }
    @Test(priority = 10, dependsOnMethods = "PF_9", description = "Kiểm tra  khi nhập sai định dạng email")
    public void PF_10(){
        dataProfile = getTestDataInMap(sh,getIndexRowFromKey(sh,getNameMethod()));
        profilePage.clickEdit();
        profilePage.editEmail(dataProfile.get("Email"));
        profilePage.saveInform("Email thất bại");
    }
    @Test(priority = 11, dependsOnMethods = "PF_10",description = "Kiểm tra  khi nhập sai định dạng email")
    public void PF_11(){
        dataProfile = getTestDataInMap(sh,getIndexRowFromKey(sh,getNameMethod()));
        profilePage.clickEdit();
        profilePage.editEmail(dataProfile.get("Email"));
        profilePage.saveInform("Email thất bại");
    }
    @Test(priority = 12, dependsOnMethods = "PF_11",description = "Kiểm tra  khi đổi email nhưng không lưu")
    public void PF_7(){
        dataProfile = getTestDataInMap(sh,getIndexRowFromKey(sh,getNameMethod()));
        profilePage.clickEdit();
        String name = profilePage.getUserInform("name");
        profilePage.editEmail(dataProfile.get("Email"));
        loginPage.goBack();
        loginPage.viewUserInform();
        keyword.assertEqualData(name, profilePage.getUserInform("name"));
    }

    @Test(priority = 13, dependsOnMethods = "PF_7",description = "Kiểm tra cập nhật ngày sinh thành công")
    public void PF_12(){
        profilePage.clickEdit();
        profilePage.editBirthDay(getCurrentDateTime("dd MMMM yyyy"),"oke");
        profilePage.saveInform("Thành công");
        profilePage.checkUserInform(stmt,"PHONE_NUMBER","birth day");
    }
    @Test(priority = 14, dependsOnMethods = "PF_12",description = "Kiểm tra cập nhật ngày sinh nhưng không lưu, hủy cập nhật ngày sinh")
    public void PF_13_14(){
        profilePage.clickEdit();
        String birthDay = profilePage.getUserInform("birth day");
        profilePage.editBirthDay(getCurrentDateTime("dd MMMM yyyy"), "oke");
        loginPage.goBack();
        loginPage.viewUserInform();
        keyword.assertEqualData(birthDay, profilePage.getUserInform("birth day"));
        profilePage.clickEdit();
        profilePage.editBirthDay(getCurrentDateTime("dd MMMM yyyy"),"cancel");
        keyword.assertEqualData(birthDay, profilePage.getUserInform("birth day"));
    }
    @Test(priority = 15, dependsOnMethods = "PF_13_14",description = "Kiểm tra cập nhật giới tính thành công")
    public void PF_15(){
        dataProfile = getTestDataInMap(sh,getIndexRowFromKey(sh,getNameMethod()));
        profilePage.editGender(dataProfile.get("Gender"));
        profilePage.saveInform("Thành công");
        profilePage.checkUserInform(stmt,"PHONE_NUMBER","gender");
    }
    @Test(priority = 16, dependsOnMethods = "PF_15",description = "Kiểm tra cập nhật giới tính nhưng không lưu")
    public void PF_16(){
        String gender = profilePage.getUserInform("gender");
        profilePage.clickEdit();
        profilePage.editGender("nữ");
        loginPage.goBack();
        loginPage.viewUserInform();
        keyword.assertEqualData(gender, profilePage.getUserInform("gender"));
    }
    @Test(priority = 18,dependsOnMethods = "PF_16", description = "Kiểm tra thay đổi avatar thành công")
    public void PF_17(){
        profilePage.clickEdit();
        profilePage.editAvatar();
        profilePage.saveInform("Thành công");
    }
}
