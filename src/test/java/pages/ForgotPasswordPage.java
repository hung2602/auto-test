package pages;

import core.BasePage;
import helpers.DataBase;
import helpers.LogHelper;
import io.qameta.allure.Step;
import locator.Locator;
import org.slf4j.Logger;
import static constant.Constant.*;

public class ForgotPasswordPage extends BasePage {
    private static Logger logger = LogHelper.getLogger();
    public ForgotPasswordPage() {
    }
    @Step("Click quên mật khẩu")
    public void clickForgot(){
        logger.info("Click forgot pass");
        keyword.click(Locator.FORGOT_PASS_BTN);
    }
    @Step("Hiển thị màn hình nhập mã OTP")
    public void checkOTPScreen(String phone){
        logger.info("Check otp screen");
        keyword.webDriverWaitForElementPresent(Locator.CONFIRM_OTP_TITLE,10);
       String title =  keyword.getText(Locator.CONFIRM_OTP_TITLE);
       keyword.assertEqualData(title, TITLE_INPUT_OTP + phone);
    }



}
