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
import static utilities.removeAccent.removeAccent;

public class SearchTest extends BaseTest {
    public DataBase dataBase ;
    public LoginPage loginPage;
    public SearchPage searchPage;
    private HashMap<String, String> dbData;
    private HashMap<String, String> dataSearch;
    public SearchTest(){
        loginPage = new LoginPage();
        searchPage = new SearchPage();
        dataBase = new DataBase();
    }
    @BeforeClass
    public void firstSteps(){
        ExcelOperations("Search");
        dataBase.setUpDB("POSTGRES_ON_TV_URL","POSTGRES_ON_TV_USER","POSTGRES_ON_TV_PASS");
        loginPage.isUserLogout();
    }
    @Test(description = "Kiểm tra tìm kiếm nhưng không nhập dữ liệu, nội dung đã xóa")
    public void SA_1_2(){
        searchPage.search();
        loginPage.checkHiddenText(Locator.SEARCH_TXT_INPUT, TEXT_INPUT_SEARCH);
        dbData = dataBase.queryAndGetDb("SPORT_TV_QUERY_EVENT_TV_ACTIVE", "false");
        searchPage.inputSearch(dbData.get("name"));
        searchPage.noResultFound();
    }
    @Test(description = "Kiểm tra tag tất cả")
    public void SA_3(){
        dataSearch = getTestDataInMap(getIndexRowFromKey(getNameMethod()));
        searchPage.inputSearch(dataSearch.get("Key"));
        searchPage.selectTagAll();
        searchPage.checkResult("tương đối",dbData.get("name"));
    }
    @Test(description = "Kiểm tra tìm kiếm theo tag")
    public void SA_4(){
        String tagName = searchPage.selectRandomATag();
        searchPage.checkTagResult(tagName, dataBase);
    }
    @Test(description = "Kiểm tra tìm kiếm theo tên")
    public void SA_5(){
        dbData = dataBase.queryAndGetDb("SPORT_TV_QUERY_EVENT_VIDEO", "true");
        searchPage.inputSearch(dbData.get("name"));
        searchPage.checkResult("tuyệt đối",dbData.get("name"));
    }
    @Test(description = "Kiểm tra tìm kiếm với chữ in hoa có dấu")
    public void SA_6(){
        searchPage.inputSearch(dbData.get("name").toUpperCase());
        searchPage.checkResult("tuyệt đối",dbData.get("name"));
    }
    @Test(description = "Kiểm tra tìm kiếm với chữ thường có dấu")
    public void SA_7(){
        searchPage.inputSearch(dbData.get("name").toLowerCase());
        searchPage.checkResult("tuyệt đối",dbData.get("name"));
    }
    @Test(description = "Kiểm tra tìm kiếm với chữ in hoa không dấu")
    public void SA_8(){
        searchPage.inputSearch(removeAccent(dbData.get("name").toUpperCase()));
        searchPage.checkResult("tuyệt đối",dbData.get("name"));
    }
    @Test(description = "Kiểm tra tìm kiếm với chữ thường không dấu")
    public void SA_9(){
        searchPage.inputSearch(removeAccent(dbData.get("name").toLowerCase()));
        searchPage.checkResult("tuyệt đối",dbData.get("name"));
    }
    @Test(description = "Kiểm tra tìm kiếm khi có khoảng trắng đầu cuối")
    public void SA_10(){
        searchPage.inputSearch(" " + removeAccent(dbData.get("name").toLowerCase()) + " ");
        searchPage.checkResult("tuyệt đối",dbData.get("name"));
    }
    @Test(description = "Kiểm tra tìm kiếm tương đối")
    public void SA_11(){
        dataSearch = getTestDataInMap(getIndexRowFromKey(getNameMethod()));
        searchPage.inputSearch(dataSearch.get("Key"));
        searchPage.checkResult("tương đối", dataSearch.get("Key"));
    }
    @Test(description = "Kiểm tra  tìm kiếm với ký tự đặc biệt")
    public void SA_13(){
        dataSearch = getTestDataInMap(getIndexRowFromKey(getNameMethod()));
        searchPage.inputSearch(dataSearch.get("Key"));
        searchPage.noResultFound();
    }
    @Test(description = "Kiểm tra tìm kiếm với ký tự số")
    public void SA_14(){
        dataSearch = getTestDataInMap(getIndexRowFromKey(getNameMethod()));
        searchPage.inputSearch(dataSearch.get("Key"));
        searchPage.noResultFound();
    }
    @Test(description = "Kiểm tra tìm kiếm chỉ nhập khoảng trắng")
    public void SA_15(){
        dataSearch = getTestDataInMap(getIndexRowFromKey(getNameMethod()));
        searchPage.inputSearch(dataSearch.get("Key"));
        searchPage.noResultFound();
    }
    @Test(description = "Kiểm tra tìm kiếm nhập ký tự đb, số, khoảng trắng")
    public void SA_16(){
        dataSearch = getTestDataInMap(getIndexRowFromKey(getNameMethod()));
        searchPage.inputSearch(dataSearch.get("Key"));
        searchPage.noResultFound();
    }
    @Severity(CRITICAL)
    @Test(description = "Kiểm tra click vào video tìm kiếm có drm chưa login")
    public void SA_17(){
        dbData = dataBase.queryAndGetDb("SPORT_TV_QUERY_EVENT_VIDEO", "true");
    }
    @Severity(CRITICAL)
    @Test(description = "Kiểm tra click vào live tìm kiếm có drm chưa login")
    public void SA_18(){

    }
    @Severity(CRITICAL)
    @Test(description = "Kiểm tra login thành công khi click vào live tìm kiếm có drm chưa login")
    public void SA_19(){
        dataSearch = getTestDataInMap(getIndexRowFromKey(getNameMethod()));
        searchPage.confirmLoginToUseFeature("yes");
        loginPage.inputUserName("PHONE_NUMBER");
        loginPage.inputPassWord("PASS_WORD");
    }
    @Severity(CRITICAL)
    @Test(description = "Kiểm tra click vào video tìm kiếm không drm đã login")
    public void SA_20(){

    }
    @Severity(CRITICAL)
    @Test(description = "Kiểm tra click vào video tìm kiếm có drm đã login")
    public void SA_21(){

    }
    @Severity(CRITICAL)
    @Test(description = "Kiểm tra click vào live tìm kiếm không drm đã login")
    public void SA_22(){

    }
    @Severity(CRITICAL)
    @Test(description = "Kiểm tra click vào live tìm kiếm có drm đã login")
    public void SA_23(){
        dataSearch = getTestDataInMap(getIndexRowFromKey(getNameMethod()));
    }
    @Test(description = "Kiểm tra tìm kiếm thành công rồi back lại")
    public void SA_24(){
        searchPage.search();
        dbData = dataBase.queryAndGetDb("SPORT_TV_QUERY_EVENT_VIDEO", "true");
        searchPage.inputSearch(dbData.get("name"));
        searchPage.backSearch();
    }
    @Test(description = "Kiểm tra xem video từ tìm kiếm rồi back lại")
    public void SA_25(){
        searchPage.search();
        dbData = dataBase.queryAndGetDb("SPORT_TV_QUERY_EVENT_VIDEO", "true");
        searchPage.inputSearch(dbData.get("name"));
        searchPage.selectSearch();
        searchPage.scrollHiddenVideo();
        searchPage.backSearch();
        searchPage.invisiblePlayButton();
    }

}
