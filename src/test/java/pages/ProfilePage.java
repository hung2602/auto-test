package pages;
import core.BasePage;
import helpers.DataBase;
import helpers.LogHelper;
import helpers.PropertiesFile;
import io.appium.java_client.AppiumBy;
import io.qameta.allure.Step;
import keyword.KeywordWeb;
import locator.Locator;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.slf4j.Logger;
import java.time.Duration;
import java.util.List;
import static constant.Constant.*;
import static constant.Query.SPORTS_ID_QUERY_USER;
import static utilities.DateTime.getCurrentDateTime;

public class ProfilePage extends BasePage {
    public DataBase dataBase ;
    private static Logger logger = LogHelper.getLogger();
    public ProfilePage() {
        dataBase = new DataBase();
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
        keyword.sleep(0.5);
        if (keyword.verifyElementPresent(Locator.ALLOW_BTN)){
            keyword.click(Locator.ALLOW_BTN);
        }
        keyword.click(Locator.USER_INFORM_BTN_LIST_AVT);
        keyword.click(Locator.USER_INFORM_BTN_AVT);
        keyword.webDriverWaitInvisibleElement(Locator.USER_INFORM_TOAST_UPDATE_AVATAR,10);
    }
    @Step("Click sửa thông tin")
    public void clickEdit(){
//        keyword.webDriverWaitForElementPresent(Locator.USER_INFORM_BTN_EDIT,10);
        keyword.sleep(0.5);
        keyword.click(Locator.USER_INFORM_BTN_EDIT);
        keyword.webDriverWaitForElementPresent(Locator.USER_INFORM_BTN_EDIT_BIRTH_DAY,10);
    }
    @Step("Lưu thông tin: {0}")
    public void saveInform(String flag){
        keyword.click(Locator.USER_INFORM_BTN_EDIT);
        switch (flag) {
            case "Thành công":
                keyword.sleep(0.3);
                keyword.assertEqual(Locator.USER_INFORM_TOAST_UPDATE_SUCCESS, MESSAGE_UPDATE_SUCCESS_INFORM);
                keyword.webDriverWaitInvisibleElement(Locator.USER_INFORM_TOAST_UPDATE_SUCCESS,10);
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
    public String getGender(String gender){
        if(gender.equals("Nam")){
            return "MALE";
        }
        else if(gender.equals("Nữ")){
            return "FEMALE";
        }
        else {
            return "OTHER";
        }
    }
    public String getBirthDay(String day){
        String[] date = day.split("-");
        return date[2] + "-" + date[1] + "-" + date[0];
    }
    @Step("Kiểm tra thông tin user: {0} với trường: {1}")
    public void checkUserInform(String key, String cases){
        logger.info("checkUserInform ");
        String getKey = PropertiesFile.getPropValue(key);
        if(getKey == null){
            getKey = key;
        }
        String query = SPORTS_ID_QUERY_USER.replace("key", getKey);
        dataBase.queryDb(query);
        String birthDay = ""; String gender = "";
        dataBase.getResultDataBase();
        switch (cases) {
            case "name":
                dataBase.checkDataBase("fullname", keyword.getText(Locator.USER_INFORM_LBL_FULL_NAME));
                break;
            case "email":
                dataBase.checkDataBase("email", keyword.getText(Locator.USER_INFORM_LBL_EMAIL));
                break;
            case "birth day":
                birthDay = getBirthDay(keyword.getText(Locator.USER_INFORM_LBL_BIRTH_DAY));
                dataBase.checkDataBase("dob", birthDay);
                break;
            case "gender":
                gender = getGender(keyword.getText(Locator.USER_INFORM_LBL_GENDER));
                dataBase.checkDataBase("gender", gender);
                break;
            case "all":
                birthDay = getBirthDay(keyword.getText(Locator.USER_INFORM_LBL_BIRTH_DAY));
                gender = getGender(keyword.getText(Locator.USER_INFORM_LBL_GENDER));
                dataBase.checkDataBase("name,fullname,email,dob,gender",
                        keyword.getText(Locator.USER_INFORM_LBL_PHONE) + "," + keyword.getText(Locator.USER_INFORM_LBL_FULL_NAME)
                                + "," + keyword.getText(Locator.USER_INFORM_LBL_EMAIL) + "," + birthDay + "," + gender);
                break;
        }
    }
    public String getUserInform(String flag) {
        logger.info("getUserInform ");
        String inform = "";
        switch (flag) {
            case "phone":
                inform =  keyword.getText(Locator.USER_INFORM_LBL_PHONE);
                break;
            case "name":
                inform =  keyword.getText(Locator.USER_INFORM_LBL_FULL_NAME);
                break;
            case "email":
                inform = keyword.getText(Locator.USER_INFORM_LBL_EMAIL);
                break;
            case "birth day":
                inform = keyword.getText(Locator.USER_INFORM_LBL_BIRTH_DAY);
                break;
            case "gender":
                inform = keyword.getText(Locator.USER_INFORM_LBL_GENDER);
                break;
            case "all":
                inform = keyword.getText(Locator.USER_INFORM_LBL_PHONE)
                        + "," + keyword.getText(Locator.USER_INFORM_LBL_FULL_NAME) + "," +
                        keyword.getText(Locator.USER_INFORM_LBL_EMAIL) + "," + keyword.getText(Locator.USER_INFORM_LBL_BIRTH_DAY) + "," +
                        keyword.getText(Locator.USER_INFORM_LBL_GENDER);
                break;
        }
        return inform;
    }

}
