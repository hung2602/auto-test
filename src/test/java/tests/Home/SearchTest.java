package tests.Home;
import core.BaseTest;
import helpers.DataBase;
import io.qameta.allure.Severity;
import locator.Locator;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import pages.Home.SearchPage;
import pages.LoginSignUp.LoginPage;
import pages.LoginSignUp.SignUpPage;
import java.util.HashMap;
import static constant.Constant.*;
import static helpers.PathHelper.getNameMethod;
import static io.qameta.allure.SeverityLevel.CRITICAL;
import static utilities.ReadExcel.*;
import static utilities.ReadExcel.getTestDataInMap;

public class SearchTest extends BaseTest {
    public DataBase dataBase ;
    public LoginPage loginPage;
    public SearchPage searchPage;
    private HashMap<String, String> dataSearch;

    public SearchTest(){
        loginPage = new LoginPage();
        searchPage = new SearchPage();
        dataBase = new DataBase();
    }
    @BeforeClass
    public void firstSteps(){
        ExcelOperations("Search");
        loginPage.isUserLogout();
    }
    @Test(description = "Kiểm tra tìm kiếm nhưng không nhập dữ liệu")
    public void SA_1_2(){
        searchPage.search();
        loginPage.checkHiddenText(Locator.HOME_BTN_SEARCH, TEXT_INPUT_SEARCH);
        dataSearch = getTestDataInMap(getIndexRowFromKey(getNameMethod()));
        searchPage.inputSearch(dataSearch.get("Key"));
        searchPage.noResultFound();
    }
    @Test(priority = 1, dependsOnMethods = "SA_1_2",description = "Kiểm tra tìm kiếm theo tag")
    public void SA_3(){
        dataSearch = getTestDataInMap(getIndexRowFromKey(getNameMethod()));
        searchPage.inputSearch(dataSearch.get("Key"));
        searchPage.checkResult(dataSearch.get("Key"));
    }
    @Test(description = "Kiểm tra tìm kiếm theo tên")
    public void SA_4(){
        dataSearch = getTestDataInMap(getIndexRowFromKey(getNameMethod()));
        searchPage.inputSearch(dataSearch.get("Key"));
        searchPage.checkResult(dataSearch.get("Key"));
    }
    @Test(description = "Kiểm tra tìm kiếm với chữ in hoa có dấu")
    public void SA_5(){
        dataSearch = getTestDataInMap(getIndexRowFromKey(getNameMethod()));
        searchPage.inputSearch(dataSearch.get("Key"));
        searchPage.checkResult(dataSearch.get("Key"));
    }
    @Test(description = "Kiểm tra tìm kiếm với chữ thường có dấu")
    public void SA_6(){
        dataSearch = getTestDataInMap(getIndexRowFromKey(getNameMethod()));
        searchPage.inputSearch(dataSearch.get("Key"));
        searchPage.checkResult(dataSearch.get("Key"));
    }
    @Test(description = "Kiểm tra tìm kiếm với chữ in hoa không dấu")
    public void SA_7(){
        dataSearch = getTestDataInMap(getIndexRowFromKey(getNameMethod()));
        searchPage.inputSearch(dataSearch.get("Key"));
        searchPage.checkResult(dataSearch.get("Key"));
    }
    @Test(description = "Kiểm tra tìm kiếm với chữ thường không dấu")
    public void SA_8(){
        dataSearch = getTestDataInMap(getIndexRowFromKey(getNameMethod()));
        searchPage.inputSearch(dataSearch.get("Key"));
        searchPage.checkResult(dataSearch.get("Key"));
    }
    @Test(description = "Kiểm tra tìm kiếm khi có khoảng trắng đầu cuối")
    public void SA_9(){
        dataSearch = getTestDataInMap(getIndexRowFromKey(getNameMethod()));
        searchPage.inputSearch(dataSearch.get("Key"));
        searchPage.checkResult(dataSearch.get("Key"));
    }
    @Test(description = "Kiểm tra tìm kiếm tương đối")
    public void SA_10(){
        dataSearch = getTestDataInMap(getIndexRowFromKey(getNameMethod()));
        searchPage.inputSearch(dataSearch.get("Key"));
        searchPage.checkResult(dataSearch.get("Key"));
    }
    @Test(description = "Kiểm tra tìm kiếm tuyệt đối")
    public void SA_11_12_13(){
        dataSearch = getTestDataInMap(getIndexRowFromKey(getNameMethod()));
    }
    @Test(description = "Kiểm tra tìm kiếm với loại VOD")
    public void SA_14(){
        dataSearch = getTestDataInMap(getIndexRowFromKey(getNameMethod()));
    }
    @Test(description = "Kiểm tra click vào video tìm kiếm có drm chưa login")
    public void SA_15(){
        dataSearch = getTestDataInMap(getIndexRowFromKey(getNameMethod()));
    }
    @Test(description = "Kiểm tra click vào live tìm kiếm có drm chưa login")
    public void SA_16(){
        dataSearch = getTestDataInMap(getIndexRowFromKey(getNameMethod()));
    }
    @Test(description = "Kiểm tra click vào video tìm kiếm có drm đã login")
    public void SA_17(){
        dataSearch = getTestDataInMap(getIndexRowFromKey(getNameMethod()));
    }
    @Test(description = "Kiểm tra click vào video tìm kiếm không drm đã login")
    public void SA_18(){
        dataSearch = getTestDataInMap(getIndexRowFromKey(getNameMethod()));
    }
    @Test(description = "Kiểm tra click vào live tìm kiếm có drm đã login")
    public void SA_19(){
        dataSearch = getTestDataInMap(getIndexRowFromKey(getNameMethod()));
    }
    @Test(description = "Kiểm tra click vào live tìm kiếm khng drm đã login")
    public void SA_20(){
        dataSearch = getTestDataInMap(getIndexRowFromKey(getNameMethod()));
    }
    @Test(description = "Kiểm tra click vào live tìm kiếm khng drm đã login")
    public void SA_22(){
        dataSearch = getTestDataInMap(getIndexRowFromKey(getNameMethod()));
    }
}
