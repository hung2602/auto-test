package pages;

import constant.Constant;
import core.BasePage;
import core.KeywordWeb;
import io.qameta.allure.Step;
import locator.Locator;
import org.bouncycastle.crypto.params.Blake3Parameters;

public class ProfilePage extends BasePage {
    public ProfilePage() {
    }
    @Step("Sửa tên hiển thi: {0}")
    public void editFullName(String name){
        keyword.sendKeys(Locator.USER_INFORM_LBL_FULL_NAME, name);
    }
    @Step("Sửa email: {0}")
    public void editEmail(String name){
        keyword.sendKeys(Locator.USER_INFORM_LBL_FULL_NAME, name);
    }
    @Step("Sửa ngày sinh: {0}")
    public void editBirthDay(String name){
        keyword.sendKeys(Locator.USER_INFORM_LBL_FULL_NAME, name);
    }
    @Step("Sửa giới tính: {0}")
    public void editGender(String name){
        keyword.selectByText(Locator.USER_INFORM_LBL_FULL_NAME, name);
    }
    @Step("Cập nhật thông tin thành công")
    public void updateInformSuccess(){
        keyword.assertEqual(Locator.USER_INFORM_TOAST_UPDATE_SUCCESS, Constant.MESSAGE_UPDATE_SUCCESS_INFORM);
    }
    @Step("Click sửa thông tin")
    public void clickEdit(){
        keyword.click(Locator.USER_INFORM_BTN_EDIT);
        keyword.webDriverWaitForElementPresent(Locator.USER_INFORM_BTN_SAVE,5);
    }
    @Step("Click lưu thông tin")
    public void clickSave(){
        keyword.click(Locator.USER_INFORM_BTN_SAVE);
        keyword.webDriverWaitForElementPresent(Locator.USER_INFORM_BTN_EDIT,5);
    }
    @Step("Cập nhật tất cả thông tin")
    public void updateFullInform(String name, String mail, String birthDay, String gender){
        clickEdit();
        editFullName(name);
        editEmail(mail);
        editBirthDay(birthDay);
        editGender(gender);
        clickSave();
    }
}
