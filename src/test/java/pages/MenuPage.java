package pages;

import core.BasePage;
import helpers.PropertiesFile;
import io.qameta.allure.Step;
import locator.Locator;
import org.bouncycastle.crypto.params.Blake3Parameters;

import static constant.Constant.*;

public class MenuPage extends BasePage {
    @Step("Vào lịch sử mua gói")
    public void goToSubHistory() {
        keyword.webDriverWaitInvisibleElement(Locator.LOGIN_TOAST_SUCCESS,10);
        keyword.click(Locator.MENU_BTN_SUB_HISTORY);
    }
    @Step("Kiểm tra thông tin lịch sử mua gói")
    public void checkSubHistory() {
        keyword.assertEqual(Locator.HISTORY_SUB_TITLE, TITLE_HISTORY_BY_SUB);
        keyword.assertEqual(Locator.HISTORY_SUB_BTN_ALL, NAME_HISTORY_BY_ALL);
        keyword.assertEqual(Locator.HISTORY_SUB_BTN_SUB, NAME_HISTORY_SUB);
        keyword.assertEqual(Locator.HISTORY_SUB_BTN_RENEW, NAME_HISTORY_RENEW);
        keyword.verifyElementDisplay(Locator.HISTORY_SUB_BTN_CANCEL, true);
    }
    @Step("Vào nhập mã khuyến mại")
    public void goToDiscountCode() {
        keyword.click(Locator.MENU_BTN_INPUT_COUPON);
    }
    @Step("Kiểm tra màn nhập mã khuyến mại")
    public void checkDiscountCode() {
        keyword.assertEqual(Locator.DISCOUNT_CODE_LBL_TITLE, TITLE_DISCOUNT_CODE);
        keyword.assertEqual(Locator.DISCOUNT_CODE_LBL_INPUT_CODE, TITLE_INPUT_CODE);
        keyword.assertEqual(Locator.DISCOUNT_CODE_TXT_INPUT, TEXT_BOX_INPUT_CODE);
        keyword.verifyElementDisplay(Locator.DISCOUNT_CODE_BTN_APPLY, true);
    }
    @Step("Vào quản lý thiết bị")
    public void goToDeviceManage() {
        keyword.click(Locator.MENU_BTN_QUAN_LY);
    }
    @Step("Lấy thông tin thiết bị")
    public String getDeviceInform() {
        return keyword.getText(Locator.DEVICE_MANAGE_LBL_NAME_DEVICE);
    }
    @Step("Vào điều khoản chính sách")
    public void goToPolicyTerm() {
//        keyword.scrollDownToElement(Locator.MENU_BTN_THONG_BAO);
        keyword.click(Locator.MENU_BTN_POLICY_TERM);
    }
    @Step("Kiểm tra màn điều khoản chính sách")
    public void checkPolicyTerm() {
        keyword.assertEqual(Locator.POLICY_TERM_LBL_TITLE_MAIN, TITLE_MAIN_POLICY_TERM);
//        keyword.sleep(1);
//        keyword.assertEqual(Locator.POLICY_TERM_LBL_TITLE, TITLE_POLICY_TERM);
//        keyword.assertEqual(Locator.POLICY_TERM_LBL_TITLE_1, TITLE_POLICY_TERM_1);
    }
    @Step("Vào mua gói cước")
    public void goToBuySub() {
        keyword.click(Locator.MENU_BTN_BUY_SUB);
    }
    @Step("Kiểm tra màn danh sách gói cước")
    public void checkBuySub() {
        keyword.webDriverWaitForElementPresent(Locator.LOGIN_SMART_TV_LBL,10);
        keyword.assertEqual(Locator.LOGIN_SMART_TV_LBL, TITLE_LIST_SUB);
        keyword.verifyElementDisplay(Locator.LIST_SUB_BTN_CHOOSE, true);
        keyword.verifyElementDisplay(Locator.LIST_SUB_BTN_SELECT_SUB, true);
    }




}
