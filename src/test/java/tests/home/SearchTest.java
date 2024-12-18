package tests.home;
import core.BaseTest;
import core.DataBase;
import driver.DriverManager;
import io.qameta.allure.Severity;
import locator.Locator;
import org.apache.poi.ss.usermodel.*;
import org.openqa.selenium.WebElement;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import pages.home.HomePage;
import pages.home.SearchPage;
import pages.loginsignup.LoginPage;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;
import java.util.List;
import static constant.Constant.*;
import static constant.Query.*;
import static helpers.PathHelper.getNameMethod;
import static io.qameta.allure.SeverityLevel.CRITICAL;
import static utilities.ReadExcel.*;
import static utilities.ReadExcel.getTestDataInMap;
import static utilities.RemoveAccent.*;

public class SearchTest extends BaseTest {
    private Sheet sh = null;
    public DataBase dataBase ;
    public LoginPage loginPage;
    public HomePage homePage;
    public SearchPage searchPage;
    private HashMap<String, String> dbData;
    private HashMap<String, String> dataSearch;
    private static Statement stmt ;
    private static ResultSet res ;
    private static Connection con ;
    public SearchTest(){
        loginPage = new LoginPage();
        searchPage = new SearchPage();
        homePage = new HomePage();
        dataBase = new DataBase();
    }
    @BeforeClass
    public void firstSteps(){
        sh = readSheet(workbook , "Search");
        con = dataBase.setUpDB("POSTGRES_ON_TV_URL","POSTGRES_ON_TV_USER","POSTGRES_ON_TV_PASS");
        stmt = dataBase.createStatement(con);
//        loginPage.isUserLogout();
    }
    @Test(description = "Kiểm tra tìm kiếm nhưng không nhập dữ liệu, nội dung đã xóa")
    public void SA_1_2(){
        searchPage.search();
        loginPage.checkHiddenText(Locator.SEARCH_TXT_INPUT, TEXT_INPUT_SEARCH);
        res = dataBase.queryDb(stmt, SPORT_TV_QUERY_EVENT_TV_ACTIVE.replace("key", "false"));
        dbData = dataBase.getResultDataBase(res);
        searchPage.inputSearch(dbData.get("name"));
        searchPage.noResultFound();
    }
    @Test(description = "Kiểm tra tag tất cả")
    public void SA_3(){
        dataSearch = getTestDataInMap(sh, getIndexRowFromKey(sh, getNameMethod()));
        searchPage.inputSearch(dataSearch.get("Key"));
        searchPage.selectTagAll();
        searchPage.checkResult("tương đối", dataSearch.get("Key"));
    }
    @Test(description = "Kiểm tra tìm kiếm theo tag")
    public void SA_4(){
        String tagName = searchPage.selectRandom("tag");
        res = dataBase.queryDb(stmt, SPORT_TV_QUERY_SCREEN_BLOCK.replace("key",tagName));
        dbData = dataBase.getResultDataBase(res);
        List<WebElement> listResult = searchPage.getListResult();
        searchPage.checkTagResult(stmt, dbData.get("id"), listResult);
    }
    @Test(description = "Kiểm tra tìm kiếm theo tên")
    public void SA_5(){
        res = dataBase.queryDb(stmt, SPORT_TV_QUERY_EVENT_VIDEO.replace("key", "true"));
        dbData = dataBase.getResultDataBase(res);
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
        dataSearch = getTestDataInMap(sh, getIndexRowFromKey(sh,getNameMethod()));
        searchPage.inputSearch(dataSearch.get("Key"));
        searchPage.checkResult("tương đối", dataSearch.get("Key"));
    }
    @Test(description = "Kiểm tra  tìm kiếm với ký tự đặc biệt")
    public void SA_13(){
        dataSearch = getTestDataInMap(sh,getIndexRowFromKey(sh,getNameMethod()));
        searchPage.inputSearch(dataSearch.get("Key"));
        searchPage.noResultFound();
    }
    @Test(description = "Kiểm tra tìm kiếm với ký tự số")
    public void SA_14(){
        dataSearch = getTestDataInMap(sh,getIndexRowFromKey(sh,getNameMethod()));
        searchPage.inputSearch(dataSearch.get("Key"));
        searchPage.noResultFound();
    }
    @Test(description = "Kiểm tra tìm kiếm chỉ nhập khoảng trắng")
    public void SA_15(){
        dataSearch = getTestDataInMap(sh,getIndexRowFromKey(sh,getNameMethod()));
        searchPage.inputSearch(dataSearch.get("Key"));
        searchPage.noResultFound();
    }
    @Test(description = "Kiểm tra tìm kiếm nhập ký tự đb, số, khoảng trắng")
    public void SA_16(){
        dataSearch = getTestDataInMap(sh,getIndexRowFromKey(sh, getNameMethod()));
        searchPage.inputSearch(dataSearch.get("Key"));
        searchPage.noResultFound();
    }
    @Severity(CRITICAL)
    @Test(description = "Kiểm tra click vào video tìm kiếm có drm chưa login")
    public void SA_17(){
        res = dataBase.queryDb(stmt, SPORT_TV_QUERY_EVENT_VIDEO.replace("key", "true"));
        dbData = dataBase.getResultDataBase(res);
    }
    @Severity(CRITICAL)
    @Test(description = "Kiểm tra click vào live tìm kiếm có drm chưa login")
    public void SA_18(){
        dataSearch = getTestDataInMap(sh, getIndexRowFromKey(sh, getNameMethod()));
        searchPage.inputSearch(dataSearch.get("Key"));
        searchPage.chooseLiveVideo();
        searchPage.showNotice();
    }
    @Severity(CRITICAL)
    @Test(description = "Kiểm tra login thành công khi click vào live tìm kiếm có drm chưa login")
    public void SA_19(){
        searchPage.confirmLoginToUseFeature("yes");
        loginPage.inputUserName("PHONE_NUMBER");
        loginPage.inputPassWord("PASS_WORD");
        loginPage.compareMessLoginSuccess();
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
        dataSearch = getTestDataInMap(sh, getIndexRowFromKey(sh, getNameMethod()));
    }
    @Test(description = "Kiểm tra tìm kiếm thành công rồi back lại")
    public void SA_24(){
//        searchPage.search();
        searchPage.floatingVideo();
        searchPage.closeVideo();
        res = dataBase.queryDb(stmt, SPORT_TV_QUERY_EVENT_VIDEO.replace("key", "true"));
        dbData = dataBase.getResultDataBase(res);
        searchPage.inputSearch(dbData.get("name"));
        searchPage.backSearch();
    }
    @Test(dependsOnMethods = "SA_24", description = "Kiểm tra xem video từ tìm kiếm rồi back lại")
    public void SA_25(){
        searchPage.search();
        res = dataBase.queryDb(stmt, SPORT_TV_QUERY_EVENT_VIDEO.replace("key", "true"));
        dbData = dataBase.getResultDataBase(res);
        searchPage.inputSearch(dbData.get("name"));
        searchPage.selectSearch();
        searchPage.floatingVideo();
        searchPage.backSearch();
        homePage.visibleHome();
        homePage.visibleLike();
        homePage.visibleLiveScore();
    }

    @Test(dependsOnMethods = "SA_25",description = "Kiểm tra xem video từ tìm kiếm rồi tắt video")
    public void SA_26(){
        searchPage.search();
        res = dataBase.queryDb(stmt, SPORT_TV_QUERY_EVENT_VIDEO.replace("key", "true"));
        dbData = dataBase.getResultDataBase(res);
        searchPage.inputSearch(dbData.get("name"));
        searchPage.selectSearch();
        searchPage.floatingVideo();
        searchPage.closeVideo();
        searchPage.invisiblePlayButton();
    }
    @Test(dependsOnMethods = "SA_26",description = "Kiểm tra xem video từ tìm kiếm rồi dừng - bật lại video")
    public void SA_27(){
        res = dataBase.queryDb(stmt, SPORT_TV_QUERY_EVENT_VIDEO_LEAGUE_TAG.replace("key", "true"));
        dbData = dataBase.getResultDataBase(res);
        searchPage.inputSearch(dbData.get("name"));
        searchPage.selectSearch();
        searchPage.waitBtnPause();
        searchPage.checkTimePlay();
        searchPage.checkTimeAfterStopVideo();
        searchPage.floatingVideo();
        searchPage.closeVideo();
    }
    @Test(dependsOnMethods = "SA_27" ,description = "Kiểm tra xem video từ tìm kiếm rồi tua video")
    public void SA_28(){
        res = dataBase.queryDb(stmt, SPORT_TV_QUERY_EVENT_VIDEO_TAG.replace("key", "true"));
        dbData = dataBase.getResultDataBase(res);
        searchPage.inputSearch(dbData.get("name"));
        searchPage.selectSearch();
        searchPage.waitBtnPause();
        searchPage.checkTimeAfterForwardVideo();
        searchPage.checkTimeAfterBackVideo();
    }

    @AfterClass
    public void closeConnect() throws SQLException {
        if(con != null) {
            con.close();
        }
    }
}
