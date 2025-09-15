package utils;

import com.microsoft.playwright.Download;
import com.microsoft.playwright.FileChooser;
import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.options.ScreenshotCaret;
import com.microsoft.playwright.options.SelectOption;
import com.microsoft.playwright.options.WaitForSelectorState;


import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Base64;
import java.util.List;

import static pilot.PilotManager.getPage;

public class PlaywrightHelper {


    private PlaywrightHelper() {    }

//    private static Keys get_CTRL_CMD (){
//        Platform platformName = ((HasCapabilities) DriverManager.getDriver()).getCapabilities().getPlatformName();
//        Keys cmdCtrl = platformName.is(Platform.MAC) ? Keys.COMMAND : Keys.CONTROL;
//        return cmdCtrl;
//    }

    public static void _waiting(long seconds, long milliSeconds) {
        try {
            seconds = seconds + milliSeconds / 1000;
            Thread.sleep(seconds * 1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void _waiting(long seconds) {
        _waiting(seconds, 0);
    }

    public static void _waitForTimeout(double milliSeconds){
        getPage().waitForTimeout(milliSeconds);
    }


    public static void _navigate(String URL) {
        getPage().navigate(URL);
        //Logger.info("Navigated to previous page");
    }

    public static String _getPageTitle() {
        String title = getPage().title();
        //Logger.info("'" + title + "' Title is returned");
        return title;
    }

    public static void _click(Locator locator, String elementName) {
        locator.last().waitFor(); locator.scrollIntoViewIfNeeded();
        locator.click();
        //if (!elementName.isEmpty()) Logger.info("Clicked - " + elementName);
    }

    public static void _click(String loc, String elementName) {
        Locator locator = getPage().locator(loc);
        locator.last().waitFor(); locator.scrollIntoViewIfNeeded();
        locator.click();
        //if (!elementName.isEmpty()) Logger.info("Clicked - " + elementName);
    }

    public static void _type(String loc, String txtToType, String elementName) {
        Locator locator = getPage().locator(loc);
        locator.last().waitFor(); locator.scrollIntoViewIfNeeded();
        locator.type(txtToType, new Locator.TypeOptions().setDelay(100));
        //if (!elementName.isEmpty()) Logger.info("Clicked - " + elementName);
    }
    public static void _type_Append(String loc, String txtToType, String elementName) {
        Locator locator = getPage().locator(loc);
        locator.last().waitFor(); locator.scrollIntoViewIfNeeded();
        locator.press("End");
        locator.type(txtToType);
        //if (!elementName.isEmpty()) Logger.info("Clicked - " + elementName);
    }
    public static void _type_Clear(String loc, String txtToType, String elementName) {
        Locator locator = getPage().locator(loc);
        locator.last().waitFor(); locator.scrollIntoViewIfNeeded();
        locator.clear();
        locator.type(txtToType, new Locator.TypeOptions().setDelay(100));
        //if (!elementName.isEmpty()) Logger.info("Clicked - " + elementName);
    }

    public static String _getValue(String loc, String elementName) {
        Locator locator = getPage().locator(loc);
        locator.last().waitFor(); locator.scrollIntoViewIfNeeded();
        String value = locator.getAttribute("value");
        //if (!elementName.isEmpty()) Logger.info("Clicked - " + elementName);
        return value;
    }

    public static String _getText(String loc, String elementName) {
        Locator locator = getPage().locator(loc);
        locator.last().waitFor(); locator.scrollIntoViewIfNeeded();
        String value = locator.textContent();
        //if (!elementName.isEmpty()) Logger.info("Clicked - " + elementName);
        return value;
    }

    public static String _getAttribute(String loc, String attribute, String elementName) {
        Locator locator = getPage().locator(loc).first();
        locator.last().waitFor(); locator.scrollIntoViewIfNeeded();
        String value = locator.getAttribute(attribute);
        //if (!elementName.isEmpty()) Logger.info("Clicked - " + elementName);
        return value;
    }

    public static boolean _isEditable(String loc, String elementName) {
        Locator locator = getPage().locator(loc).first();
        locator.last().waitFor(); locator.scrollIntoViewIfNeeded();
        boolean value = locator.isEditable();
        //if (!elementName.isEmpty()) Logger.info("Clicked - " + elementName);
        return value;
    }

    public static boolean _isEnabled(String loc, String elementName) {
        Locator locator = getPage().locator(loc).first();
        locator.last().waitFor(); locator.scrollIntoViewIfNeeded();
        boolean value = locator.isEnabled();
        //if (!elementName.isEmpty()) Logger.info("Clicked - " + elementName);
        return value;
    }

    //TODO: Select <select>
    public static void _selectByValueOrLabel(String loc, String txtToSelect, String elementName) {
        Locator locator = getPage().locator(loc);
        locator.last().waitFor(); locator.scrollIntoViewIfNeeded();
        List<String> option = locator.selectOption(txtToSelect);
        //if (!elementName.isEmpty()) Logger.info("Clicked - " + elementName);
    }

    public static void _selectByValueOrLabel_Multiple(String loc, String[] txtToSelect, String elementName) {
        Locator locator = getPage().locator(loc);
        locator.last().waitFor(); locator.scrollIntoViewIfNeeded();
        List<String> option = locator.selectOption(txtToSelect);
        //if (!elementName.isEmpty()) Logger.info("Clicked - " + elementName);
    }

    public static void  _selectByIndex(String loc, int index, String elementName) {
        Locator locator = getPage().locator(loc);
        locator.last().waitFor(); locator.scrollIntoViewIfNeeded();
        List<String> option = locator.selectOption(new SelectOption().setIndex(index));
        //if (!elementName.isEmpty()) Logger.info("Clicked - " + elementName);
    }

    //TODO: Alert / Dialog
    public static void _alertAccept(String locatorToGetAlert, String locName) {

        String[] dialogMsg = new String[1];
        getPage().onceDialog(dialog -> {
            dialogMsg[0] = dialog.message();
            dialog.accept();
        });
        _click(locatorToGetAlert, locName);
        System.out.println(dialogMsg[0]);

        //Logger.info("'" + title + "' Title is returned");
    }

    public static void _alertFill_Accept(String locatorToGetAlert, String txtToFill, String locName) {

        String[] dialogMsg = new String[1];
        getPage().onceDialog(dialog -> {
            dialogMsg[0] = dialog.message();
            dialog.accept(txtToFill);
        });
        _click(locatorToGetAlert, locName);
        System.out.println(dialogMsg[0]);
        //Logger.info("'" + title + "' Title is returned");
    }

    public static void _alertDismiss(String locatorToGetAlert, String locName) {

        String[] dialogMsg = new String[1];
        getPage().onceDialog(dialog -> {
            dialogMsg[0] = dialog.message();
            dialog.dismiss();
        });
        _click(locatorToGetAlert, locName);
        System.out.println(dialogMsg[0]);

        //Logger.info("'" + title + "' Title is returned");
    }

    //TODO: Download
    public static String _download(String locatorToStartDownload, String pathToSaveFile, String locName){
        Page page = getPage();
        // Wait for the download to start
        Download download = page.waitForDownload(() -> {
           page.locator(locatorToStartDownload).click();  // Perform the action that initiates download
        });
        System.out.println("Downloading..... | " + pathToSaveFile + "/" + download.suggestedFilename());
        // Wait for the download process to complete
        Path path = download.path();
        // Folders are created by playwright if not present
        Path saveAsPath = Paths.get(pathToSaveFile + "/" + download.suggestedFilename());
        download.saveAs(saveAsPath);
        System.out.println("Downloaded: " + saveAsPath);
        return download.failure(); // return status
        //Logger.info("'" + title + "' Title is returned");
    }

    public static void _upload(String locatorToUpload, String pathOfFileWithExtension, String locName) {
        Page page = getPage();
        FileChooser fileChooser = page.waitForFileChooser(()->{
            page.locator(locatorToUpload).click();
        });
        System.out.println("Uploading... " + pathOfFileWithExtension);
        fileChooser.setFiles(Paths.get(pathOfFileWithExtension));

        //Logger.info("'" + title + "' Title is returned");
    }

    // TODO: Screen shots https://www.youtube.com/watch?v=e2xdGuD1LPc
    public static void _screenshot(String pathOfFileWithExtension) {

        getPage().screenshot(new Page.ScreenshotOptions()
                .setPath(Paths.get(pathOfFileWithExtension)));
        //Logger.info("'" + title + "' Title is returned");
    }

    public static void _screenshotFull(String pathOfFileWithExtension) {

        getPage().screenshot(new Page.ScreenshotOptions()
                .setPath(Paths.get(pathOfFileWithExtension)).setFullPage(true));
        //Logger.info("'" + title + "' Title is returned");
    }

    public static String _screenshotAsBase64() {

        byte[] buffer = getPage().screenshot();
        return Base64.getEncoder().encodeToString(buffer);
        //Logger.info("'" + title + "' Title is returned");
    }

    public static void _screenshotLocator(String locator, String pathOfFileWithExtension) {

        getPage().locator(locator).screenshot(new Locator.ScreenshotOptions()
                .setPath(Paths.get(pathOfFileWithExtension))
                .setCaret(ScreenshotCaret.INITIAL));
        //Logger.info("'" + title + "' Title is returned");
    }

    //TODO: Wait for
    public static boolean _waitForVisible(String loc, double timeout_milliSec, String elementName) {
        Locator locator = getPage().locator(loc);
        locator.last().waitFor(new Locator.WaitForOptions()
                .setState(WaitForSelectorState.VISIBLE)
                .setTimeout(timeout_milliSec));
        return locator.isVisible();
        //if (!elementName.isEmpty()) Logger.info("Clicked - " + elementName);
    }
    public static void _waitForPresent(String loc, double timeout_milliSec, String elementName) {
        Locator locator = getPage().locator(loc);
        locator.last().waitFor(new Locator.WaitForOptions()
                .setState(WaitForSelectorState.ATTACHED)
                .setTimeout(timeout_milliSec));
        //if (!elementName.isEmpty()) Logger.info("Clicked - " + elementName);
    }
    public static void _waitForNotPresent(String loc, double timeout_milliSec, String elementName) {
        Locator locator = getPage().locator(loc);
        locator.last().waitFor(new Locator.WaitForOptions()
                .setState(WaitForSelectorState.DETACHED)
                .setTimeout(timeout_milliSec));
        //if (!elementName.isEmpty()) Logger.info("Clicked - " + elementName);
    }
    public static boolean _waitForHidden(String loc, double timeout_milliSec, String elementName) {
        Locator locator = getPage().locator(loc);
        locator.last().waitFor(new Locator.WaitForOptions()
                .setState(WaitForSelectorState.HIDDEN)
                .setTimeout(timeout_milliSec));
        return locator.isHidden();
        //if (!elementName.isEmpty()) Logger.info("Clicked - " + elementName);
    }
}
