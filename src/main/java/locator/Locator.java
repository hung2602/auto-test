package locator;
import io.appium.java_client.AppiumBy;
import io.appium.java_client.android.options.UiAutomator2Options;
import org.openqa.selenium.By;
public class Locator {
    public static final By HOME_BTN_SKIP = By.id("com.vtvcab.onsports.dev:id/skip");
    public static final By HOME_BTN_MENU = new AppiumBy.ByAccessibilityId("Menu");
    public static final By LOGOUT_BTN = By.id("com.vtvcab.onsports.dev:id/layout_logout");
    public static final By LOGOUT_BTN_CONFIRM = By.id("com.vtvcab.onsports.dev:id/btnLogout");
    public static final By MENU_BTN_LAYOUT_AVATAR = By.id("com.vtvcab.onsports.dev:id/layout_avatar");
    public static final By LOGOUT_TOAST_SUCCESS = By.id("com.vtvcab.onsports.dev:id/tv_message");
    public static final By LOGOUT_BTN_CANCEL = By.id("com.vtvcab.onsports.dev:id/btnCancel");
    public static final By LOGIN_BTN = By.id("com.vtvcab.onsports.dev:id/button_login");
    public static final By LOGIN_TXT_USER_NAME = By.id("com.vtvcab.onsports.dev:id/ed_phone");
    public static final By LOGIN_TXT_PASSWORD = By.id("com.vtvcab.onsports.dev:id/ed_password");
    public static final By LOGIN_BTN_CONTINUE = By.id("com.vtvcab.onsports.dev:id/button_next");
    public static final By LOGIN_TOAST_SUCCESS = By.xpath("//android.widget.TextView[@resource-id='com.vtvcab.onsports.dev:id/tv_message']");
    public static final By LOGIN_TOAST_INCORRECT_PASSWORD = By.id("com.vtvcab.onsports.dev:id/tv_message");
    public static final By SETTING_BTN_SMART_TV = By.xpath("(//android.view.ViewGroup[@resource-id=\"com.vtvcab.onsports.dev:id/layout_information\"])[1]");
    public static final By SETTING_BTN_QUAN_LY = By.xpath("(//android.view.ViewGroup[@resource-id=\"com.vtvcab.onsports.dev:id/layout_information\"])[2]");
    public static final By SETTING_BTN_THONG_BAO = By.xpath("(//android.view.ViewGroup[@resource-id=\"com.vtvcab.onsports.dev:id/layout_information\"])[3]");
    public static final By LOGIN_BTN_ACCEPT = By.id("com.vtvcab.onsports.dev:id/btnOk");
    public static final By LOGIN_SMART_TV_LBL = By.id("com.vtvcab.onsports.dev:id/tv_title");
    public static final By LOGIN_SMART_TV_TXT_OTP = By.id("com.vtvcab.onsports.dev:id/layout_otp");
    public static final By LOGIN_SMART_TV_BTN_QR = By.xpath("//android.view.ViewGroup[@resource-id=\"com.vtvcab.onsports.dev:id/scan_qrcode\"]");
    public static final By LOGIN_SMART_TV_BTN_BACK = By.xpath("//android.widget.ImageView[@resource-id=\"com.vtvcab.onsports.dev:id/image_back\"]");
    public static final By DEVICE_MANAGE_BTN_LOGOUT_DEVICE = By.id("com.vtvcab.onsports.dev:id/buttonKick");
    public static final By DEVICE_MANAGE_BTN_LAYOUT_DEVICE = By.id("com.vtvcab.onsports.dev:id/layoutDevice");
    public static final By SET_INFORM_BTN_COMPLETE = By.id("com.vtvcab.onsports.dev:id/button_done");
    public static final By SIGN_UP_TXT_EDIT_OPT = By.className("android.widget.EditText");
    public static final By LBL_TIME_EXPIRED_OTP = By.id("com.vtvcab.onsports.dev:id/text_countdown");
    public static final By SIGN_UP_BTN_RESEND_OTP = By.id("com.vtvcab.onsports.dev:id/re_send_otp");
    public static final By LOGOUT_DEVICE_TOAST_SUCCESS = By.id("com.vtvcab.onsports.dev:id/tv_message");
    public static final By USER_INFORM_LBL_PHONE = By.id("com.vtvcab.onsports.dev:id/edtPhone");
    public static final By USER_INFORM_LBL_FULL_NAME = By.id("com.vtvcab.onsports.dev:id/edtFullName");
    public static final By USER_INFORM_LBL_EMAIL = By.id("com.vtvcab.onsports.dev:id/edtEmail");
    public static final By USER_INFORM_LBL_BIRTH_DAY = By.id("com.vtvcab.onsports.dev:id/tvBirthday");
    public static final By USER_INFORM_LBL_GENDER = By.id("com.vtvcab.onsports.dev:id/tvGender");
    public static final By USER_INFORM_BTN_EDIT = By.id("com.vtvcab.onsports.dev:id/tvEdit");
    public static final By USER_INFORM_BTN_SAVE = By.xpath("Lưu");
    public static final By USER_INFORM_TOAST_UPDATE_SUCCESS = By.linkText("//android.widget.Toast[@text=\"Cập nhật thành công\"]");

}

