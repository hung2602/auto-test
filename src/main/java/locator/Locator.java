package locator;
import helpers.PropertiesFile;
import io.appium.java_client.AppiumBy;
import io.appium.java_client.android.options.UiAutomator2Options;
import org.openqa.selenium.By;
public class Locator {
    public static final By HOME_BTN_SKIP = By.id("com.vtvcab.onsports.dev:id/skip");
    public static final By HOME_BTN_MENU = new AppiumBy.ByAccessibilityId("Menu");
    public static final By HOME_BTN_HOME = new AppiumBy.ByAccessibilityId("Trang chủ");
    public static final By HOME_BTN_LIVE_SCORE = new AppiumBy.ByAccessibilityId("Livescore");
    public static final By HOME_BTN_FOLLOWING = new AppiumBy.ByAccessibilityId("Yêu thích");
    public static final By HOME_BTN_SEARCH = By.id("com.vtvcab.onsports.dev:id/imageButtonSearch");
    public static final By LOGOUT_BTN = By.id("com.vtvcab.onsports.dev:id/layout_logout");
    public static final By LOGOUT_BTN_CONFIRM = By.id("com.vtvcab.onsports.dev:id/btnLogout");
    public static final By PROFILE_BTN_VIEW_USER_INFORM = By.id("com.vtvcab.onsports.dev:id/text_show_profile");
    public static final By LOGOUT_TOAST_SUCCESS = By.id("com.vtvcab.onsports.dev:id/tv_message");
    public static final By LOGOUT_BTN_CANCEL = By.id("com.vtvcab.onsports.dev:id/btnCancel");
    public static final By LOGIN_BTN = By.id("com.vtvcab.onsports.dev:id/button_login");
    public static final By LOGIN_TXT_USER_NAME = By.id("com.vtvcab.onsports.dev:id/ed_phone");
    public static final By LOGIN_TXT_PASSWORD = By.id("com.vtvcab.onsports.dev:id/ed_password");
    public static final By LOGIN_TXT_CONFIRM_PASSWORD = By.id("com.vtvcab.onsports.dev:id/ed_password_again");
    public static final By LOGIN_BTN_CONTINUE = By.id("com.vtvcab.onsports.dev:id/button_next");
    public static final By LOGIN_TOAST_SUCCESS = By.xpath("//android.widget.TextView[@resource-id='com.vtvcab.onsports.dev:id/tv_message']");
    public static final By LOGIN_TOAST_INCORRECT_PASSWORD = By.id("com.vtvcab.onsports.dev:id/tv_message");
    public static final By MENU_BTN_SMART_TV = By.xpath("(//android.view.ViewGroup[@resource-id=\"com.vtvcab.onsports.dev:id/layout_information\"])[1]");
    public static final By MENU_BTN_QUAN_LY = By.xpath("(//android.view.ViewGroup[@resource-id=\"com.vtvcab.onsports.dev:id/layout_information\"])[2]");
    public static final By MENU_BTN_THONG_BAO = By.xpath("(//android.view.ViewGroup[@resource-id=\"com.vtvcab.onsports.dev:id/layout_information\"])[3]");
    public static final By LOGIN_BTN_ACCEPT = By.id("com.vtvcab.onsports.dev:id/btnOk");
    public static final By LOGIN_SMART_TV_LBL = By.id("com.vtvcab.onsports.dev:id/tv_title");
    public static final By LOGIN_SMART_TV_TXT_OTP = By.id("com.vtvcab.onsports.dev:id/layout_otp");
    public static final By LOGIN_SMART_TV_BTN_QR = By.xpath("//android.view.ViewGroup[@resource-id=\"com.vtvcab.onsports.dev:id/scan_qrcode\"]");
    public static final By LOGIN_SMART_TV_BTN_BACK = By.id("com.vtvcab.onsports.dev:id/image_back");
    public static final By DEVICE_MANAGE_BTN_LOGOUT_DEVICE = By.id("com.vtvcab.onsports.dev:id/buttonKick");
    public static final By DEVICE_MANAGE_BTN_LAYOUT_DEVICE = By.id("com.vtvcab.onsports.dev:id/layoutDevice");
    public static final By SET_INFORM_BTN_COMPLETE = By.id("com.vtvcab.onsports.dev:id/button_done");
    public static final By SIGN_UP_TXT_EDIT_OPT = By.className("android.widget.EditText");
    public static final By LBL_TIME_EXPIRED_OTP = By.id("com.vtvcab.onsports.dev:id/text_countdown");
    public static final By SIGN_UP_BTN_RESEND_OTP = By.id("com.vtvcab.onsports.dev:id/re_send_otp");
    public static final By LOGOUT_DEVICE_TOAST_SUCCESS = By.id("com.vtvcab.onsports.dev:id/tv_message");
    public static final By USER_INFORM_LBL_PHONE = By.id("com.vtvcab.onsports.dev:id/edtPhone");
    public static final By USER_INFORM_LBL_FULL_NAME = By.id("com.vtvcab.onsports.dev:id/edtFullName");
    public static final By USER_INFORM_BTN_EDIT_FULL_NAME = By.id("com.vtvcab.onsports.dev:id/imgEditFullName");
    public static final By USER_INFORM_BTN_EDIT_EMAIL = By.id("com.vtvcab.onsports.dev:id/imgEditEmail");
    public static final By USER_INFORM_LBL_EMAIL = By.id("com.vtvcab.onsports.dev:id/edtEmail");
    public static final By USER_INFORM_BTN_EDIT_BIRTH_DAY = By.id("com.vtvcab.onsports.dev:id/imgChoiceCal");
    public static final By USER_INFORM_LBL_BIRTH_DAY = By.id("com.vtvcab.onsports.dev:id/tvBirthday");
    public static final By USER_INFORM_BTN_GENDER = By.id("com.vtvcab.onsports.dev:id/imgChoiceGender");
    public static final By USER_INFORM_LBL_GENDER = By.id("com.vtvcab.onsports.dev:id/tvGender");
    public static final By USER_INFORM_BTN_MALE_GENDER = By.id("com.vtvcab.onsports.dev:id/rela_male");
    public static final By USER_INFORM_BTN_OTHER_GENDER = By.id("com.vtvcab.onsports.dev:id/rela_other");
    public static final By USER_INFORM_BTN_FEMALE_GENDER = By.id("com.vtvcab.onsports.dev:id/rela_female");
    public static final By USER_INFORM_BTN_EDIT = By.id("com.vtvcab.onsports.dev:id/tvEdit");
    public static final By USER_INFORM_TOAST_UPDATE_SUCCESS = By.xpath("//android.widget.Toast[@text=\"Cập nhật thành công\"]");
    public static final By USER_INFORM_TOAST_UPDATE_AVATAR = By.xpath("//android.widget.Toast[@text=\"Cập nhật ảnh thành công\"]");
    public static final By USER_INFORM_TOAST_UPDATE_FAIL_EMAIL = By.xpath("//android.widget.Toast[1]");
    public static final By USER_INFORM_BTN_EDIT_AVT = By.id("com.vtvcab.onsports.dev:id/imgEditAvatar");
    public static final By USER_INFORM_BTN_LIST_AVT = By.xpath("//android.support.v7.widget.RecyclerView/android.widget.RelativeLayout[3]");
    public static final By USER_INFORM_BTN_AVT = By.className("android.widget.ImageView");
    public static final By USER_INFORM_BTN_EDIT_DATE = new AppiumBy.ByAccessibilityId(PropertiesFile.getPropValue("ID_DATE"));
    public static final By USER_INFORM_BTN_OKE_EDIT_DATE = By.xpath("//android.widget.Button[@text=\"OK\"]");
    public static final By USER_INFORM_BTN_CANCEL_EDIT_DATE = By.xpath("//android.widget.Button[@text=\"CANCEL\"]");
    public static final By MENU_LBL_LOGIN_NOTICE = By.id("com.vtvcab.onsports.dev:id/body");
    public static final By MENU_BTN_SUB_HISTORY = By.id("com.vtvcab.onsports.dev:id/sub_history");
    public static final By MENU_BTN_INPUT_COUPON = By.id("com.vtvcab.onsports.dev:id/coupon_layout");
    public static final By HISTORY_SUB_TITLE = By.id("com.vtvcab.onsports.dev:id/tv_title");
    public static final By HISTORY_SUB_BTN_ALL = By.xpath("//android.widget.TextView[@resource-id=\"com.vtvcab.onsports.dev:id/textview\" and @text=\"Tất cả\"]");
    public static final By HISTORY_SUB_BTN_SUB = By.xpath("//android.widget.TextView[@resource-id=\"com.vtvcab.onsports.dev:id/textview\" and @text=\"Đăng ký\"]");
    public static final By HISTORY_SUB_BTN_RENEW = By.xpath("//android.widget.TextView[@resource-id=\"com.vtvcab.onsports.dev:id/textview\" and @text=\"Gia hạn\"]");
    public static final By HISTORY_SUB_BTN_CANCEL = By.xpath("//android.widget.TextView[@resource-id=\"com.vtvcab.onsports.dev:id/textview\" and @text=\"Huỷ gói\"]");
    public static final By LIST_SUB_BTN_CHOOSE = By.xpath("com.vtvcab.onsports.dev:id/button_buy");
    public static final By LIST_SUB_BTN_SELECT_SUB = By.xpath("(//android.widget.ImageView[@resource-id=\"com.vtvcab.onsports.dev:id/test1\"])[2]");
    public static final By DEVICE_MANAGE_LBL_NAME_DEVICE = By.xpath("(//android.widget.TextView[@resource-id=\"com.vtvcab.onsports.dev:id/deviceName\"])[2]");
    public static final By POLICY_TERM_LBL_TITLE  = By.xpath("//android.view.View[@text=\"CHÍNH SÁCH BẢO MẬT & ĐIỀU KHOẢN SỬ DỤNG\"]");
    public static final By MENU_BTN_POLICY_TERM  = By.xpath("//android.widget.TextView[@resource-id=\"com.vtvcab.onsports.dev:id/txt_info\" and @text=\"Điều khoản & chính sách\"]");
    public static final By DISCOUNT_CODE_BTN_APPLY  = By.xpath("//android.widget.TextView[@resource-id=\"com.vtvcab.onsports.dev:id/button_next\"]");
    public static final By DISCOUNT_CODE_TXT_INPUT  = By.xpath("//android.widget.EditText[@resource-id=\"com.vtvcab.onsports.dev:id/ed_coupon\"]");
    public static final By DISCOUNT_CODE_LBL_INPUT_CODE  = By.xpath("//android.widget.TextView[@resource-id='com.vtvcab.onsports.dev:id/textView']");
    public static final By DISCOUNT_CODE_LBL_TITLE  = By.xpath("//android.widget.TextView[@resource-id='com.vtvcab.onsports.dev:id/tv_title']");
    public static final By MENU_BTN_BUY_SUB  = By.id("com.vtvcab.onsports.dev:id/buy_sub");
    public static final By POLICY_TERM_LBL_TITLE_MAIN  = By.xpath("//android.widget.TextView[@resource-id=\"com.vtvcab.onsports.dev:id/tv_title\"]");
    public static final String POLICY_TERM_LBL_TITLE_1  = "//android.widget.TextView[@text=\"1. KHAI BÁO KHI SỬ DỤNG\"]";
    public static final By POLICY_TERM_BASE_VIEW = By.id("com.vtvcab.onsports.dev:id/base_view");
    public static final By LOGIN_LBL_ERROR = By.id("com.vtvcab.onsports.dev:id/error_text");
    public static final By SIGN_UP_BTN_SHOW_PASS = By.id("com.vtvcab.onsports.dev:id/show_pass_btn");
    public static final By SIGN_UP_BTN_SHOW_AGAIN_PASS = By.id("com.vtvcab.onsports.dev:id/show_password_again_btn");
    public static final By SIGN_UP_BTN_CLEAR_PASS = By.id("com.vtvcab.onsports.dev:id/clear_pass_btn");
    public static final By SIGN_UP_BTN_CLEAR_AGAIN_PASS = By.id("com.vtvcab.onsports.dev:id/clear_pass_again_btn");
    public static final By INPUT_OTP_BTN_BACK = By.id("com.vtvcab.onsports.dev:id/back_image");
    public static final By SET_PASSWORD_LBL_PHONE = By.id("com.vtvcab.onsports.dev:id/label_phone");
    public static final By SET_PASSWORD_LBL_PHONE2 = By.xpath("//android.widget.TextView[@text=\"Vui lòng tạo mật khẩu từ 6 ký tự trở lên\"]");
    public static final By ALLOW_BTN = By.id("com.android.permissioncontroller:id/permission_allow_button");
    public static final By NOT_ALLOW_BTN = By.id("com.android.permissioncontroller:id/permission_deny_button");
    public static final By NOTICE_BTN_LATE = By.id("com.vtvcab.onsports.dev:id/buttonLatter");
    public static final By FORGOT_PASS_BTN = By.id("com.vtvcab.onsports.dev:id/text_forgot_password");
    public static final By CONFIRM_OTP_TITLE = By.id("com.vtvcab.onsports.dev:id/title_phone");
    public static final By NOTICE_LBL_MORE_3_DEVICE = By.id("com.vtvcab.onsports.dev:id/textLogout");
    public static final By SEARCH_TXT_INPUT = By.id("com.vtvcab.onsports.dev:id/search_src_text");
    public static final By SEARCH_LBL_NO_RESULT = By.xpath("//android.widget.TextView[@text=\"Không có kết quả tìm kiếm\"]");
    public static final By SEARCH_LBL_FIND_OTHER_RESULT = By.xpath("//android.widget.TextView[@text=\"Hãy thử tìm kiếm với từ khóa khác.\"]");
    public static final By SEARCH_LBL_TITLE_RESULT = By.id("com.vtvcab.onsports.dev:id/title_text");
    public static final By SEARCH_BTN_TAG = By.id("com.vtvcab.onsports.dev:id/textview");
    public static final By SEARCH_BTN_BACK = By.id("com.vtvcab.onsports.dev:id/imageButtonBack");
    public static final By SEARCH_BTN_TAG_ALL = By.xpath("//android.widget.TextView[@resource-id=\"com.vtvcab.onsports.dev:id/textview\" and @text=\"Tất cả\"]");
    public static final By VIEW_VIDEO_BTN_CLOSE = By.id("com.vtvcab.onsports.dev:id/close_imageView");
    public static final By VIEW_VIDEO_BTN_PLAY = By.id("com.vtvcab.onsports.dev:id/play_imageView");
    public static final By MENU_LBL_SIGNING = By.id("com.vtvcab.onsports.dev:id/info");
    public static final By PLAYER_VIEW_BTN_PLAY = By.id("com.vtvcab.onsports.dev:id/onsport_play");
    public static final By PLAYER_VIEW_BTN_PAUSE = By.id("com.vtvcab.onsports.dev:id/onsport_pause");
    public static final By PLAYER_VIEW_BTN_REWIND = By.id("com.vtvcab.onsports.dev:id/ivRewind");
    public static final By PLAYER_VIEW_BTN_FORWARD = By.id("com.vtvcab.onsports.dev:id/ivForward");
    public static final By PLAYER_VIEW_LBL_TIME_LINE = By.id("com.vtvcab.onsports.dev:id/onsport_position");
    public static final By PLAYER_VIEW_LBL_TIME_DURATION = By.id("com.vtvcab.onsports.dev:id/onsport_duration");
    public static final By PLAYER_VIEW_BTN_FULL_SCREEN = By.id("com.vtvcab.onsports.dev:id/btnFullScreen");
    public static final By PLAYER_VIEW_BTN_FRAME_SCREEN = By.id("com.vtvcab.onsports.dev:id/exo_content_frame");
    public static final By CONTENT_LBL_LIVE = By.id("com.vtvcab.onsports.dev:id/include_live_text");
    public static final String CONTENT_IMG_LIVE  = "//androidx.appcompat.widget.LinearLayoutCompat[@resource-id=\"com.vtvcab.onsports.dev:id/include_live_text\"]/ancestor::android.widget.FrameLayout[@resource-id=\"com.vtvcab.onsports.dev:id/searchCardView\"]";











}

