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
    public static final By LOGOUT_MESS_SUCCESS = By.id("com.vtvcab.onsports.dev:id/tv_message");
    public static final By LOGOUT_BTN_CANCEL = By.id("com.vtvcab.onsports.dev:id/btnCancel");
    public static final By LOGIN_BTN = By.id("com.vtvcab.onsports.dev:id/button_login");
    public static final By LOGIN_TXT_USER_NAME = By.id("com.vtvcab.onsports.dev:id/ed_phone");
    public static final By LOGIN_TXT_PASSWORD = By.id("com.vtvcab.onsports.dev:id/ed_password");
    public static final By LOGIN_BTN_CONTINUE = By.id("com.vtvcab.onsports.dev:id/button_next");
    public static final By LOGIN_MESS_SUCCESS = By.xpath("//android.widget.TextView[@resource-id='com.vtvcab.onsports.dev:id/tv_message']");
    public static final By LOGIN_MESS_INCORRECT_PASSWORD = By.id("com.vtvcab.onsports.dev:id/tv_message");
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

}

