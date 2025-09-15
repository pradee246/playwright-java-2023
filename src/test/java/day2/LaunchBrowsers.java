package day2;

import com.microsoft.playwright.*;

public class LaunchBrowsers {
	
	public static void main(String[] args) {
		
		Playwright playwright = Playwright.create();
//		Browser browser = playwright.chromium().launch(
//					new BrowserType.LaunchOptions()
//					.setHeadless(false)
//					.setChannel("msedge")
//				);
//		Page page = browser.newPage();

		Browser browser = playwright.chromium().connectOverCDP("http://localhost:3333",new BrowserType.ConnectOverCDPOptions().setSlowMo(500));
		BrowserContext defaultContext = browser.contexts().get(0);
		Page page = defaultContext.pages().get(0);

		page.navigate("https://letcode.in/");
		System.out.println(page.title());
		page.locator("//a[normalize-space()='Sign up']").click();
		page.locator("#name").fill("dgdfg ");
		page.locator("//a[normalize-space()='Log in']").click();
		//page.close();
		//browser.close();
		playwright.close();
	}

}
