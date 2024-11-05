package helpers;

import core.BaseTest;
import io.qameta.allure.Step;
import org.slf4j.Logger;
import java.sql.*;
import java.util.HashMap;

public class DataBase extends BaseTest {
    private static Logger logger = LogHelper.getLogger();
    public static Connection con ;
    public static ResultSet res ;
    public static Statement stmt ;
    public static  HashMap<String, String> dataMap = new HashMap<>();
    public DataBase() {
    }
    public DataBase(ResultSet res, Statement stmt) {
        this.res = res;
        this.stmt = stmt;
    }
    @Step("Set up kết nốt Data PostGresSQL: {0}")
    public void setUpDB(String url, String user, String passWord) {
        logger.info("Set Up DB " + url );
        try {
            Class.forName("org.postgresql.Driver");
            String dbUrl = PropertiesFile.getPropValue(url);
            String dbUser = PropertiesFile.getPropValue(user);
            String dbPass = PropertiesFile.getPropValue(passWord);
            con = DriverManager.getConnection(dbUrl, dbUser, dbPass);
            stmt = con.createStatement();
        }
        catch (ClassNotFoundException | SQLException e) {
            throw new RuntimeException(e);
        }
    }
    @Step("Set up kết nốt Data MonGoDb")
    public void setUpMonGoDb(String url, String user, String passWord){
        logger.info("Set Up MonGo DB");
        try {
            Class.forName("cdata.jdbc.mongodb.MongoDBDriver");
            String dbUrl = PropertiesFile.getPropValue(url);
            String dbUser = PropertiesFile.getPropValue(user);
            String dbPass = PropertiesFile.getPropValue(passWord);
            con = DriverManager.getConnection(dbUrl, dbUser, dbPass);
            stmt = con.createStatement();
        }
        catch (ClassNotFoundException | SQLException e) {
            throw new RuntimeException(e);
        }
    }
    @Step("Set up kết nốt Redis")
    public void setUpRedis(String url, String user, String passWord) {
        logger.info("Set Up Redis");
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
    @Step("Lấy dữ liệu từ các cột db")
    public static HashMap<String, String> getResultDataBase() {
        logger.info("Get result DB: ");
        try {
            ResultSetMetaData md = res.getMetaData();
            while (res.next()) {
                for (int i = 1; i <= md.getColumnCount(); i++) {
                    dataMap.put(md.getColumnName(i), res.getString(i));
                }
            }
        }
        catch(SQLException e)
        {
            e.printStackTrace(); // hiển thị tổng quan về lỗi
        }
        return dataMap;
    }
    @Step("Kiểm tra dữ liệu cột: {0}")
    public void checkDataBase(String actuals, String expects) {
        logger.info("Check DB: ");
        String[] actual = actuals.split(",");
        String[] expect = expects.split(",");
        for (int i = 0; i < expect.length; i++) {
            keyword.assertEqualData(dataMap.get(actual[i]), expect[i]);
        }
    }
}
