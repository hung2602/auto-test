package core;

import core.PropertiesFile;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.slf4j.Logger;
import org.testng.Assert;
import utilities.LogHelper;

import java.sql.*;

public class DataBaseTest {
    private static Logger logger = LogHelper.getLogger();
    public static Connection con ;
    public static ResultSet res ;
    public static Statement stmt ;
    public DataBaseTest( ) {
    }
    @Step("Set up kết nốt Data base: {0}")
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
    @Step("Thực hiện truy vấn dữ liệu : {0}")
    public void queryDb(String query) {
        logger.info("Query DB: " + query );
        query = PropertiesFile.getPropValue(query);
        try {
            res = stmt.executeQuery(query);
        }
        catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    @Step("Kiểm tra, so sánh dữ liệu : Cột {0}, Giá trị {1}")
    public void checkDataBase(String coLumLabel, String expect) {
        coLumLabel = PropertiesFile.getPropValue(coLumLabel);
        logger.info("Check DB: " + coLumLabel );
        try {
            while (res.next())
            {
                Assert.assertEquals(res.getString(coLumLabel), expect);
            }
        }
        catch(SQLException e)
        {
            e.printStackTrace();
        }
    }
    public void tearDown() throws Exception {
        if (con != null) {
            con.close();
        }
    }
}
