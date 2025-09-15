package tests;

import com.microsoft.playwright.*;
import org.testng.annotations.Test;

import java.nio.file.Paths;

public class Test_01 {

    Browser.NewContextOptions contextOption = new Browser.NewContextOptions()
            .setHttpCredentials("admin", "admin");


   // @Test
    void testBasicAuth(){

        String url = "https://the-internet.herokuapp.com/basic_auth";

        Browser browser = Playwright.create().chromium().launch();
        BrowserContext browserContext = browser.newContext(
                contextOption
        );
        Page page = browserContext.newPage();
        page.navigate(url);
        System.out.println(page.locator("h3").textContent());

        browser.close();

    }

     @Test
    void testMultiTab(){

         String url = "https://the-internet.herokuapp.com/windows";

         Browser browser = Playwright.create().chromium().launch(new BrowserType.LaunchOptions().setHeadless(false));
         BrowserContext browserContext = browser.newContext();
         Page page = browserContext.newPage();
         page.navigate(url);
         System.out.println(browserContext.pages().size());
         page.locator("'Click Here'").click();



         page.waitForTimeout(3000);
         System.out.println(browserContext.pages().size());
         System.out.println(browserContext.pages().get(0).title());
         browser.close();

    }
}
