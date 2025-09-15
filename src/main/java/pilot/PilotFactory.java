package pilot;

import com.microsoft.playwright.*;

import java.nio.file.Paths;
import java.util.Objects;

public class PilotFactory {

    public static void initPilot(String browsertype) {
//        try(Playwright playwright = Playwright.create()){}

        BrowserType.LaunchOptions launchOptions = new BrowserType.LaunchOptions().setHeadless(false);
        Browser browser = null;
        BrowserContext browserContext = null;
        Page page;

        if (browsertype.equalsIgnoreCase("chrome")) {
            browser = Playwright.create().chromium().launch(launchOptions.setChannel("chrome"));
        } else if (browsertype.equalsIgnoreCase("firefox")) {
            browser = Playwright.create().firefox().launch(launchOptions);
        } else if (browsertype.equalsIgnoreCase("chromium")) {
            browser = Playwright.create().chromium().launch(launchOptions);
        } else if (browsertype.equalsIgnoreCase("edge")) {
            browser = Playwright.create().chromium().launch(launchOptions.setChannel("msedge"));
        } else if (browsertype.equalsIgnoreCase("webkit")) {
            browser = Playwright.create().webkit().launch(launchOptions);
//        }else if (browsertype.equalsIgnoreCase("chromedebug")) { // Working code commented for further enhancement
//            browser = Playwright.create().chromium().connectOverCDP("http://localhost:3333", new BrowserType.ConnectOverCDPOptions().setSlowMo(1000) );
//            browserContext = browser.contexts().get(0);
//            page = browserContext.pages().get(0);
        }
        PilotManager.setBrowser(browser);
        browserContext = browser.newContext();
        PilotManager.setBrowserContext(browserContext);
        PilotManager.setPage(browserContext.newPage());
    }

    static Boolean captureVideo = false;
    public static void initPilot(String browsertype, Browser.NewContextOptions contextOption) {

        BrowserType.LaunchOptions launchOptions = new BrowserType.LaunchOptions().setHeadless(false);
        Browser browser = null;
        BrowserContext browserContext = null;
        if(captureVideo){
            contextOption = new Browser.NewContextOptions().setRecordVideoDir(Paths.get("videos/"))
                    .setRecordVideoSize(1280,720);
        }else{
            contextOption = new Browser.NewContextOptions();
        }


        if (browsertype.equalsIgnoreCase("chromedebug")) {
            browser = Playwright.create().chromium().connectOverCDP("http://localhost:3333", new BrowserType.ConnectOverCDPOptions().setSlowMo(1000));
            PilotManager.setBrowser(browser);
            browserContext = browser.contexts().get(0);
            //browserContext = browser.contexts().set(0, browser.newContext(contextOption));
            PilotManager.setBrowserContext(browserContext);
            PilotManager.setPage(browserContext.pages().get(0));
        } else {
            if (browsertype.equalsIgnoreCase("chrome")) {
                browser = Playwright.create().chromium().launch(launchOptions.setChannel("chrome"));
            } else if (browsertype.equalsIgnoreCase("firefox")) {
                browser = Playwright.create().firefox().launch(launchOptions);
            } else if (browsertype.equalsIgnoreCase("chromium")) {
                browser = Playwright.create().chromium().launch(launchOptions);
            } else if (browsertype.equalsIgnoreCase("edge")) {
                browser = Playwright.create().chromium().launch(launchOptions.setChannel("msedge"));
            } else if (browsertype.equalsIgnoreCase("webkit")) {
                browser = Playwright.create().webkit().launch(launchOptions);
            }
            PilotManager.setBrowser(browser);
            PilotManager.setBrowserContext(browser.newContext(contextOption));
            PilotManager.setPage(PilotManager.getBrowserContext().newPage());
        }


    }


    public static void quitPilot() {
        if (Objects.nonNull(PilotManager.getPage())) {
            PilotManager.getBrowser().close();
            PilotManager.getBrowserContext().close();
            PilotManager.getPage().close();
            PilotManager.unload();
        }
    }
}
