package pages;
import core.BasePage;
import helpers.PropertiesFile;
import io.appium.java_client.AppiumBy;
import io.qameta.allure.Step;
import locator.Locator;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;
import static constant.Constant.*;
import static core.BaseTest.driver;
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
    public void editBirthDay(String date, String flag){
        PropertiesFile.setDataPropValue("ID_DATE", date);
        keyword.click(Locator.USER_INFORM_BTN_EDIT_BIRTH_DAY);
        keyword.click(new AppiumBy.ByAccessibilityId(PropertiesFile.getPropValue("ID_DATE")));
        if(flag.equals("oke")) {
            keyword.click(Locator.USER_INFORM_BTN_OKE_EDIT_DATE);
        }
        else {
            keyword.click(Locator.USER_INFORM_BTN_CANCEL_EDIT_DATE);
        }
    }
    @Step("Sửa giới tính")
    public void editGender(String option) {
        keyword.click(Locator.USER_INFORM_BTN_GENDER);
        if (option.equals("nam")) {
            keyword.click(Locator.USER_INFORM_BTN_MALE_GENDER);
        }
        else {
            keyword.click(Locator.USER_INFORM_BTN_FEMALE_GENDER);
            // USER_INFORM_BTN_OTHER_GENDER
        }
    }
    @Step("Sửa ảnh đại diện")
    public void editAvatar() {
        keyword.click(Locator.USER_INFORM_BTN_EDIT_AVT);
        keyword.click(Locator.USER_INFORM_BTN_LIST_AVT);
        keyword.click(Locator.USER_INFORM_BTN_AVT);
    }
    @Step("Click sửa thông tin")
    public void clickEdit(){
        keyword.click(Locator.USER_INFORM_BTN_EDIT);
        keyword.webDriverWaitForElementPresent(Locator.USER_INFORM_BTN_EDIT_BIRTH_DAY,5);
    }
    @Step("Lưu thông tin: {0}")
    public void saveInform(String flag){
        keyword.click(Locator.USER_INFORM_BTN_EDIT);
        switch (flag) {
            case "Thành công":
                keyword.assertEqual(Locator.USER_INFORM_TOAST_UPDATE_SUCCESS, MESSAGE_UPDATE_SUCCESS_INFORM);
                break;
            case "Email thất bại":
                keyword.assertEqual(Locator.USER_INFORM_TOAST_UPDATE_FAIL_EMAIL, MESSAGE_UPDATE_FAIL_EMAIL_INFORM);
                break;
            case "Tên thất bại":
                keyword.assertEqual(Locator.USER_INFORM_TOAST_UPDATE_FAIL_EMAIL, MESSAGE_UPDATE_FAIL_NAME);
                break;
        }
    }
    @Step("Cập nhật tất cả thông tin")
    public void updateFullInform(String name, String mail, String gender){
        editFullName(name);
        editEmail(mail);
        editBirthDay(getCurrentDateTime("dd MMMM yyyy"),"oke");
        editGender(gender);
    }
}
