package pilot;

import com.microsoft.playwright.Browser;
import com.microsoft.playwright.BrowserContext;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.Playwright;

import java.util.Objects;

public class PilotManager {


    private PilotManager(){}

    private static ThreadLocal<Browser> br = new ThreadLocal<>();
    private static ThreadLocal<BrowserContext> bc = new ThreadLocal<>();
    private static ThreadLocal<Page> pg = new ThreadLocal<>();


    public static Browser getBrowser(){
        return br.get();
    }

    public static BrowserContext getBrowserContext(){
        return bc.get();
    }

    public static Page getPage(){ return pg.get(); }

    protected static void setBrowser(Browser browserRef){
        if(Objects.nonNull(browserRef)) br.set(browserRef);
    }

    public static void setBrowserContext(BrowserContext browserContextRef){
        if(Objects.nonNull(browserContextRef)) bc.set(browserContextRef);
    }

    public static void setPage(Page pageRef){
        if(Objects.nonNull(pageRef)) pg.set(pageRef);
    }

    protected static void unload(){
        pg.remove();
        bc.remove();
        br.remove();
    }



}
