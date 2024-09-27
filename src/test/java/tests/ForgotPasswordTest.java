package tests;

import core.BaseTest;
import pages.LoginPage;
import pages.ProfilePage;

public class ForgotPasswordTest extends BaseTest {
    public LoginPage loginPage;
    public ProfilePage profilePage;
    public ForgotPasswordTest(){
        loginPage = new LoginPage();
        profilePage = new ProfilePage();
    }

}
