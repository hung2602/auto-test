package pages;
import core.BasePage;
import helpers.PropertiesFile;
import io.qameta.allure.Step;
import locator.Locator;
import static utilities.DateTime.getCurrentDateTime;

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
    public void editBirthDay(String date){
        keyword.click(Locator.USER_INFORM_BTN_EDIT_BIRTH_DAY);
        PropertiesFile.setDataPropValue("ID_DATE", date);
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
        editFullName(name);
        editEmail(mail);
        editBirthDay(getCurrentDateTime("dd MMMM yyyy"));
        editGender();
    }
}
