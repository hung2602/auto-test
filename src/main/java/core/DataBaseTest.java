package core;

import core.PropertiesFile;
import io.qameta.allure.Step;
import org.testng.Assert;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class DataBaseTest {
    public static Connection con = null;
    private static Statement stmt;
    @Step("Set DB: {0}")
    public void setUpDB(String url, String user, String passWord) throws Exception {
        Class.forName("org.postgresql.Driver");
        String dbUrl = PropertiesFile.getPropValue(url);
        String dbUser = PropertiesFile.getPropValue(user);
        String dbPass = PropertiesFile.getPropValue(passWord);
        Connection con = DriverManager.getConnection(dbUrl, dbUser, dbPass);
        stmt = con.createStatement();
    }
    @Step("Kiểm tra data base : {0}")
    public void checkDataBase(String query, String coLumLabel, String expect) {
        expect = PropertiesFile.getPropValue(expect);
        query = PropertiesFile.getPropValue(query);
        try {
            ResultSet res = stmt.executeQuery(query);
            while (res.next())
            {
                System.out.println(res.getString(coLumLabel));
                Assert.assertEquals(expect,res.getString(coLumLabel));
            }
        }
        catch(Exception e)
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
