package pages.home;
import core.BasePage;
import io.qameta.allure.Step;
import locator.Locator;

public class HomePage extends BasePage {
    public HomePage() {
    }
    @Step("Bỏ qua quảng cáo")
    public void visibleHome(){
        keyword.verifyElementDisplay(Locator.HOME_BTN_HOME, true);
    }
    @Step("Bỏ qua quảng cáo")
    public void visibleLiveScore(){
        keyword.verifyElementDisplay(Locator.HOME_BTN_LIVE_SCORE, true);
    }
    @Step("Bỏ qua quảng cáo")
    public void visibleLike(){
        keyword.verifyElementDisplay(Locator.HOME_BTN_FOLLOWING, true);
    }

    @Step("Bỏ qua quảng cáo")
    public void skipBanner(){
        keyword.click(Locator.HOME_BTN_SKIP);
    }

}
