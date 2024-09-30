package helpers;

import core.BaseTest;
import io.qameta.allure.Step;
import org.slf4j.Logger;
import utilities.LogHelper;

import java.sql.*;

public class DataBase extends BaseTest {
    private static Logger logger = LogHelper.getLogger();
    public static Connection con ;
    public static ResultSet res ;
    public static Statement stmt ;
    public DataBase() {
    }
    @Step("Set up kết nốt Data base: {0}")
    public Statement setUpDB(String url, String user, String passWord) {
        logger.info("Set Up DB " + url );
        try {
            Class.forName("org.postgresql.Driver");
            String dbUrl = PropertiesFile.getPropValue(url);
            String dbUser = PropertiesFile.getPropValue(user);
            String dbPass = PropertiesFile.getPropValue(passWord);
            con = DriverManager.getConnection(dbUrl, dbUser, dbPass);
            stmt = con.createStatement();
            return stmt;
        }
        catch (ClassNotFoundException | SQLException e) {
            throw new RuntimeException(e);
        }
    }
    @Step("Thực hiện truy vấn dữ liệu : {0}")
    public void queryDb(String query) {
        logger.info("Query DB: " + query );
        String content= PropertiesFile.getPropValue(query);
        if (content == null) {
            content = query;
        }
        try {
             res = stmt.executeQuery(content);
        }
        catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    @Step("Kiểm tra dữ liệu Các cột {0}")
    public void checkDataBase(String coLumLabels, String expects) {
        logger.info("Check DB: " + coLumLabels);
        String[] coLumLabel = coLumLabels.split(",");
        String[] expect = expects.split(",");
        try {
            while (res.next()) {
                for (int i = 0; i < expect.length; i++) {
                    keyword.assertEqualData(res.getString(PropertiesFile.getPropValue(coLumLabel[i])), expect[i]);
                }
            }
        }
        catch(SQLException e)
        {
            e.printStackTrace(); // hiển thị tổng quan về lỗi
        }
    }
    public void tearDown() throws Exception {
        if (con != null) {
            con.close();
        }
    }
}
