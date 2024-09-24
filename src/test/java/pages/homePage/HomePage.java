package pages.homePage;

import core.BasePage;
import core.KeywordWeb;
import io.qameta.allure.Step;
import locator.Locator;

public class HomePage extends BasePage {
    public HomePage(KeywordWeb key) {
        super(key);
    }

    @Step("Bỏ qua quảng cáo")
    public void skipBanner(){
        keyword.click(Locator.HOME_BTN_SKIP);
    }



}
