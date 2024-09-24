package core;

import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class DataBase {
    static Connection con = null;
    private static Statement stmt;

    public void setUpDB(String url, String user, String passWord) throws Exception {
        Class.forName("org.postgresql.Driver");
        String dbUrl = PropertiesFile.getPropValue(url);
        String dbUser = PropertiesFile.getPropValue(user);
        String dbPass = PropertiesFile.getPropValue(passWord);
        Connection con = DriverManager.getConnection(dbUrl, dbUser, dbPass);
        stmt = con.createStatement();
    }
    public void checkDataBase(String query) {
        try{
            query = "Select lastname from persons where personid = '1'";
            ResultSet res = stmt.executeQuery(query);
            while (res.next())
            {
                System.out.println(res.getString(1));
            }
            Assert.assertEquals("Cuong",res.getString(1));
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
