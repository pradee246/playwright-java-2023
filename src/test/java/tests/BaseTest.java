package tests;

import com.microsoft.playwright.*;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import pilot.PilotFactory;
import pilot.PilotManager;

public class BaseTest {

//    static Playwright playwright;
//    static Browser browser;
//    static BrowserContext context;
//    static Page page;

    protected BaseTest() {
    }

    static Browser.NewContextOptions contextOption;
    protected void setContextOption(Browser.NewContextOptions contextOption){
        if(!browserType.toLowerCase().contains("debug")){
            if(this.contextOption!=null && this.contextOption.equals(contextOption)) System.out.println("both r eq");
            this.contextOption = contextOption;
            Browser browser = PilotManager.getBrowser();
            //PilotManager.getBrowserContext().close();
            BrowserContext browserContext ;//= PilotManager.getBrowser().newContext(contextOption);
            //browserContext  = browser.contexts().get(0).browser().newContext(contextOption);
            //browser.contexts().add(PilotManager.getBrowser().newContext(contextOption));
            BrowserContext browserContext2 = browser.newContext(contextOption);
            browser.contexts().add(0, browserContext2);
            browserContext = browser.contexts().get(0);
            PilotManager.setBrowserContext(browserContext);
            Page page = browserContext.pages().size()<1 ? browserContext.newPage() : browserContext.pages().get(0);
            PilotManager.setPage(page);//(browserContext.newPage());
        }
    }
    protected void setContextOption2(Browser.NewContextOptions contextOption){
        if(!browserType.toLowerCase().contains("debug")){
            if(this.contextOption!=null && this.contextOption.equals(contextOption)) System.out.println("both r eq");
            this.contextOption = contextOption;
            Browser browser = PilotManager.getBrowser();
            PilotManager.getBrowserContext().close();
            BrowserContext browserContext = browser.newContext(contextOption);
            //browserContext = PilotManager.getBrowser().contexts().set(0, PilotManager.getBrowser().newContext(contextOption));
            //browserContext = browser.contexts().get(0);
            PilotManager.setBrowserContext(browserContext);
            PilotManager.setPage(browserContext.newPage());
        }
    }
    String browserType = "chromium";
    @BeforeClass
    protected void setUp() {
//        Driver.initDriver(ConfigFactory.getConfig().browser());
//        DriverManager.getDriver().manage().timeouts().implicitlyWait(Duration.ofSeconds(5));

        //Pilot.initPilot("chromium");
        PilotFactory.initPilot(browserType, contextOption);
        PilotManager.getPage().setDefaultTimeout(20000);
    }

    //@BeforeMethod
    protected void setUp_sub() {
        PilotManager.setPage(PilotManager.getBrowserContext().newPage());
        PilotManager.getPage().setDefaultTimeout(20000);
    }

    //@AfterMethod
    protected void tearDown_sub() {
        PilotManager.getPage().close();
    }

    @AfterClass
    protected void tearDown() {
//        page.close();
//        browser.close();
//        playwright.close();
        PilotFactory.quitPilot();
    }

    public static void initDriver(String browsertype) {
//        playwright = Playwright.create();
//        BrowserType.LaunchOptions launchOptions = new BrowserType.LaunchOptions().setHeadless(false);
//        browser = null;
//        //Page page;
//
//        if (browsertype.equalsIgnoreCase("chrome")) {
//            browser = playwright.chromium().launch(launchOptions.setChannel("chrome"));
//        } else if (browsertype.equalsIgnoreCase("firefox")) {
//            browser = playwright.firefox().launch(launchOptions);
//        } else if (browsertype.equalsIgnoreCase("chromium")) {
//            browser = playwright.chromium().launch(launchOptions);
//        }else if (browsertype.equalsIgnoreCase("edge")) {
//            browser = playwright.chromium().launch(launchOptions.setChannel("msedge"));
//        }else if (browsertype.equalsIgnoreCase("webkit")) {
//            browser = playwright.webkit().launch(launchOptions);
//        }
//        context = browser.newContext();
//        page = context.newPage();

    }




}
