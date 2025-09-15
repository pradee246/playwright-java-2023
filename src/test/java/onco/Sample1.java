package onco;

import com.microsoft.playwright.*;
import com.microsoft.playwright.options.Proxy;

public class Sample1 {

    public static void main(String[] args) throws InterruptedException {
        Playwright playwright = Playwright.create();
        Browser browser = playwright.chromium().launch(new BrowserType.LaunchOptions()
                .setHeadless(false)
                .setProxy(new Proxy("40.76.240.210:3128")));
        Page page = browser.newPage();

        page.navigate("https://app-test.irisoncology.com/");
        page.wait(2500);
        page.evaluate("document.querySelector('flt-glass-pane').shadowRoot.querySelector('flt-semantics-placeholder').click({force: true}");

        page.locator("").type("Koushik C");
        Locator locator = page.locator("#join");
        locator.press("End");
        locator.type(" man");
        locator.press("Tab");
        page.type("#fullName", "Koushik C");
        String value = page.locator("id=getMe").getAttribute("value");
        System.out.println(value);
        page.locator("(//label[text()='Clear the text']/following::input)[1")
                .clear();
    }
}
