package pages;
import core.BasePage;
import io.qameta.allure.Step;
import locator.Locator;
import org.bouncycastle.crypto.params.Blake3Parameters;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.List;

import static constant.Constant.*;
import static core.BaseTest.driver;

public class ProfilePage extends BasePage {
    public ProfilePage() {
    }
    @Step("Sửa tên hiển thị: {0}")
    public void editFullName(String name){
        keyword.click(Locator.USER_INFORM_BTN_EDIT_FULL_NAME);
        keyword.clearTextAndSendKey(Locator.USER_INFORM_LBL_FULL_NAME, name);
    }
    @Step("Sửa email: {0}")
    public void editEmail(String email){
        keyword.click(Locator.USER_INFORM_BTN_EDIT_EMAIL);
        keyword.clearTextAndSendKey(Locator.USER_INFORM_LBL_EMAIL, email);
    }
    @Step("Sửa ngày sinh: {0}")
    public void editBirthDay(){
        keyword.click(Locator.USER_INFORM_BTN_EDIT_BIRTH_DAY);
        keyword.click(Locator.USER_INFORM_BTN_EDIT_DATE);
        keyword.click(Locator.USER_INFORM_BTN_OKE_EDIT_DATE);
    }
    @Step("Sửa giới tính")
    public void editGender(){
        keyword.click(Locator.USER_INFORM_BTN_GENDER);
        keyword.click(Locator.USER_INFORM_BTN_MALE_GENDER);
    }
    @Step("Click sửa thông tin")
    public void clickEdit(){
        keyword.click(Locator.USER_INFORM_BTN_EDIT);
        keyword.webDriverWaitForElementPresent(Locator.USER_INFORM_BTN_EDIT_BIRTH_DAY,5);
    }
    @Step("Lưu thông tin: {0}")
    public void saveInform(String flag){
        keyword.click(Locator.USER_INFORM_BTN_EDIT);
        keyword.sleep(0.5);
        if (flag.equals("Thành công")){
            keyword.sleep(0.2);
//            keyword.webDriverWaitForElementPresent(Locator.USER_INFORM_TOAST_UPDATE_SUCCESS,10);
        }
        else {

        }
    }
    @Step("Cập nhật tất cả thông tin")
    public void updateFullInform(String name, String mail){
        clickEdit();
        editFullName(name);
        editEmail(mail);
        editBirthDay();
        editGender();
        saveInform("Thành công");
    }
}
