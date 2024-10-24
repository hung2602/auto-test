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
import static utilities.ReadExcel.*;
import static utilities.ReadExcel.getTestDataInMap;

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
        dataBase.setUpDB("POSTGRES_ON_TV_URL","POSTGRES_ON_TV_USER","POSTGRES_ON_TV_PASS");
        loginPage.isUserLogout();
    }
    @Test(description = "Kiểm tra tìm kiếm nhưng không nhập dữ liệu, nội dung đã xóa")
    public void SA_1_2(){
//        searchPage.search();
//        loginPage.checkHiddenText(Locator.SEARCH_TXT_INPUT, TEXT_INPUT_SEARCH);
        dbData = dataBase.queryAndGetDb("POSTGRES_DB_QUERY_EVENT_TV", "false");
        searchPage.inputSearch(dbData.get("name"));
        searchPage.noResultFound();
    }
    @Test(dependsOnMethods = "SA_1_2",description = "Kiểm tra tìm kiếm theo tag")
    public void SA_3(){
        searchPage.inputSearch(dbData.get("name"));
        searchPage.checkResult(dbData.get("name"));
    }
    @Test(description = "Kiểm tra tìm kiếm theo tên")
    public void SA_4(){

    }
    @Test(description = "Kiểm tra tìm kiếm với chữ in hoa có dấu")
    public void SA_5(){

    }
    @Test(description = "Kiểm tra tìm kiếm với chữ thường có dấu")
    public void SA_6(){

    }
    @Test(description = "Kiểm tra tìm kiếm với chữ in hoa không dấu")
    public void SA_7(){


    }
    @Test(description = "Kiểm tra tìm kiếm với chữ thường không dấu")
    public void SA_8(){

    }
    @Test(description = "Kiểm tra tìm kiếm khi có khoảng trắng đầu cuối")
    public void SA_9(){

    }
    @Test(description = "Kiểm tra tìm kiếm tương đối")
    public void SA_10(){

    }
    @Test(description = "Kiểm tra tìm kiếm tuyệt đối")
    public void SA_11_12_13(){

    }
    @Test(description = "Kiểm tra tìm kiếm với loại VOD")
    public void SA_14(){

    }
    @Test(description = "Kiểm tra click vào video tìm kiếm có drm chưa login")
    public void SA_15(){
        dataSearch = getTestDataInMap(getIndexRowFromKey(getNameMethod()));
    }
    @Test(description = "Kiểm tra click vào live tìm kiếm có drm chưa login")
    public void SA_16(){
        dataSearch = getTestDataInMap(getIndexRowFromKey(getNameMethod()));
    }
    @Test(description = "Kiểm tra login thành công khi click vào live tìm kiếm có drm chưa login")
    public void SA_17(){
        dataSearch = getTestDataInMap(getIndexRowFromKey(getNameMethod()));
        searchPage.confirmLoginToUseFeature("yes");
        loginPage.inputUserName("PHONE_NUMBER");
        loginPage.inputPassWord("PASS_WORD");
    }
    @Test(description = "Kiểm tra click vào video tìm kiếm không drm đã login")
    public void SA_18(){

    }
    @Test(description = "Kiểm tra click vào video tìm kiếm có drm đã login")
    public void SA_19(){

    }
    @Test(description = "Kiểm tra click vào live tìm kiếm không drm đã login")
    public void SA_20(){

    }
    @Test(description = "Kiểm tra click vào live tìm kiếm có drm đã login")
    public void SA_21(){
        dataSearch = getTestDataInMap(getIndexRowFromKey(getNameMethod()));
    }
    @Test(description = "Kiểm tra tìm kiếm thành công rồi back lại")
    public void SA_23(){
        dataSearch = getTestDataInMap(getIndexRowFromKey(getNameMethod()));
    }
    @Test(description = "Kiểm tra xem video từ tìm kiếm rồi back lại")
    public void SA_24(){
        dataSearch = getTestDataInMap(getIndexRowFromKey(getNameMethod()));
    }
    @Test(description = "Kiểm tra tìm kiếm với ký tự đb")
    public void SA_25(){
        dataSearch = getTestDataInMap(getIndexRowFromKey(getNameMethod()));
    }
    @Test(description = "Kiểm tra tìm kiếm với ký tự số")
    public void SA_26(){
        dataSearch = getTestDataInMap(getIndexRowFromKey(getNameMethod()));
    }
    @Test(description = "Kiểm tra tìm kiếm với khoảng trắng")
    public void SA_27(){
        dataSearch = getTestDataInMap(getIndexRowFromKey(getNameMethod()));
    }
    @Test(description = "Kiểm tra tìm kiếm với ký tự đb, số, khoảng trắng")
    public void SA_28(){
        dataSearch = getTestDataInMap(getIndexRowFromKey(getNameMethod()));
    }
}
