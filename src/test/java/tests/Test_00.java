package tests;

import com.microsoft.playwright.*;
import com.microsoft.playwright.options.AriaRole;
import org.testng.annotations.Test;
import pilot.PilotManager;

import java.util.regex.Pattern;

import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;
import static utils.PlaywrightHelper.*;

public class Test_00 extends BaseTest {

/*

    @Test
    void test1() {
        _navigate("https://letcode.in/");
        System.out.println(_getPageTitle());
    }

    @Test
    void test2() {
        Page page = PilotManager.getPage();
        page.navigate("https://playwright.dev/java/");
        System.out.println(page.title());
    }


    @Test
    void testAlert() {
        Page page = PilotManager.getPage();
        _navigate("https://letcode.in/alert");
        _alertAccept("#confirm", "dfg");
        //_click("#confirm", "fbfd");
        _waitForTimeout(2000);
        _alertFill_Accept("#prompt", "hgfhhello", "");
        //_click("#prompt", "fbfd");
        System.out.println(page.locator("#myName").textContent());
        _waitForTimeout(2000);
        _alertDismiss("#prompt", "");
        _waitForTimeout(5000);
        _navigate("https://letcode.in/");
        System.out.println(_getPageTitle());
    }

    @Test(priority = -1)
    void testEdit() {
        Page page = PilotManager.getPage();
        page.navigate("https://letcode.in/");
        page.pause();
        page.getByRole(AriaRole.LINK, new Page.GetByRoleOptions().setName("Log in")).click();
        page.getByRole(AriaRole.TEXTBOX, new Page.GetByRoleOptions().setName("Enter registered email"))
                .fill("pradee2468@gmail.com");
        page.getByPlaceholder("Enter password").fill("Password#123");
        page.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions().setName("LOGIN")).click();

        _waitForTimeout(5000);
        _navigate("https://letcode.in/edit");

        _type("#fullName", "First Name", "First Name");

        _type_Append("#join", " Buddy", "");

        _waitForTimeout(2000);

        _type_Clear("#join", " sgsd asg rg fh ag ", "");

        _waitForTimeout(2000);

        System.out.println(_getAttribute("#getMe", "value", ""));

        System.out.println(_isEnabled("#noEdit", "value"));
        System.out.println(_isEnabled("#dontwrite", "value"));
        System.out.println(_isEditable("#dontwrite", "value"));

    }

    @Test(priority = 0)
    void testDropDown() {
        Page page = PilotManager.getPage();
        _navigate("https://letcode.in/dropdowns");

        _selectByValueOrLabel("#fruits", "0", "Apple");

        _selectByValueOrLabel_Multiple("#superheros", new String[] { "Aquaman", "Batman"}, "");

        _waitForTimeout(2000);
    }

*/

   // @Test
    void testBasicAuth(){
        Browser.NewContextOptions contextOption = new Browser.NewContextOptions()
                .setHttpCredentials("admin", "admin");
        setContextOption2(contextOption);

        String url = "https://the-internet.herokuapp.com/basic_auth";

//        Browser browser = Playwright.create().chromium().launch();
//        BrowserContext browserContext = browser.newContext(
//                contextOption
//        );
//        Page page = browserContext.newPage();

        _navigate(url);
        System.out.println("1111111");
        System.out.println(_getText("h3", ""));


    }

   // @Test
    void testBasicAuth2() {
        Browser.NewContextOptions contextOption = new Browser.NewContextOptions()
                .setHttpCredentials("admin", "admin");
        setContextOption2(contextOption);

        String url = "https://the-internet.herokuapp.com/basic_auth";

//        Browser browser = Playwright.create().chromium().launch();
//        BrowserContext browserContext = browser.newContext(
//                contextOption
//        );
//        Page page = browserContext.newPage();
        _navigate(url);
        System.out.println("22222");
        System.out.println(_getText("h3", ""));
    }

    @Test
    void testDownload() {
        _navigate("https://the-internet.herokuapp.com/download");
       // page.navigate("https://letcode.in/file");

        System.out.println(_download("'puppy_and_kitten.jpg'", "./download/d", ""));

    }

    @Test
    void testUpload() {
        _navigate("https://ufile.io/");

        _upload("id=uf-uploader", "sample.xlsx", "");

        _waitForTimeout(10000);
    }


    @Test(priority = 0)
    void testScreenshot() {
        Page page = PilotManager.getPage();
        _navigate("https://playwright.dev/java/docs/screenshots#capture-into-buffer");

        _screenshotLocator("button.clean-btn.toggleButton_gllP","./screenshots/full.png");
        _waitForTimeout(2000);
    }

    @Test(priority = 0)
    void testAssert() {
        Page page = PilotManager.getPage();
        _navigate("https://the-internet.herokuapp.com/download");

        assertThat(page).hasTitle(Pattern.compile("The Internet.*"));
        Locator atag = page.locator("a");
        System.out.println(atag.count());

        atag.all().forEach(a -> System.out.println(a.textContent()));

        assertThat(page.locator("//div[@class='example']//a[1]")).containsText("testng-7.7.1.jar");

    }

    @Test
    void testMultiTab(){
        String url = "https://the-internet.herokuapp.com/windows";
        Page page = PilotManager.getPage();
        BrowserContext browserContext = PilotManager.getBrowserContext();
        page.navigate(url);
        System.out.println(browserContext.pages().size());
        _click("'Click Here'", "");

        page.waitForTimeout(3000);
        System.out.println(browserContext.pages().size());
        System.out.println(browserContext.pages().get(1).title() + "\n ------------------------");

        // Setting the Thread for new Page
        PilotManager.setPage(browserContext.pages().get(1));
        System.out.println(PilotManager.getPage().title());
        System.out.println(_getText("h3", "0"));

        // Setting the Thread for default Page
        PilotManager.setPage(page);
        System.out.println(PilotManager.getPage().title());
        System.out.println(_getText("h3", "0"));
    }

}
