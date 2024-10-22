package pages.Home;
import core.BasePage;
import io.qameta.allure.Step;
import locator.Locator;

public class HomePage extends BasePage {
    public HomePage() {
    }
    @Step("Bỏ qua quảng cáo")
    public void skipBanner(){
        keyword.click(Locator.HOME_BTN_SKIP);
    }

}
