import base.FrameworkConfig;
import base.FrameworkInitalize;
import config.ConfigReader;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import pages.HomePage;

public class FirstTest {


    @BeforeTest
    public void setupPlaywright() {
        ConfigReader.PopulateSettings();
        FrameworkConfig.LocalPage = new FrameworkInitalize().InitializePlaywright();
    }

    @Test
    public void testNavigation() {
        FrameworkConfig.LocalPage.navigate("https://amazon.com");
    }

    @Test
    public void testLogin() {
        FrameworkConfig.LocalPage.navigate("http://eaapp.somee.com");
        HomePage homePage = new HomePage();
        var loginPage = homePage.ClickLogin();
        homePage = loginPage.Login("admin", "password");
        homePage.ClickEmployeeList();
        //Log off
        homePage.ClickLogOff();
    }

    @Test
    public void testCreateUser() {
        FrameworkConfig.LocalPage.navigate("http://eaapp.somee.com");
        HomePage homePage = new HomePage();
        var loginPage = homePage.ClickLogin();
        homePage = loginPage.Login("admin", "password");
        homePage.ClickEmployeeList();

        //Create New User
        var createUserPage = homePage.ClickCreateNew();
        homePage = createUserPage.CreateUser("AutoTestUser", "2000", "40", "2", "autotest@tester.com");

        //Delete User
        homePage.DeleteCreateUser("AutoTestUser");

        //Log off
        homePage.ClickLogOff();


    }

    @AfterTest
    public void cleanUp() throws Exception {
        FrameworkConfig.LocalPage.close();
        FrameworkConfig.Playwright.close();
    }


}
