package club.sdll.test;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.phantomjs.PhantomJSDriver;
import org.openqa.selenium.phantomjs.PhantomJSDriverService;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.util.ArrayList;

/**
 * decription
 *
 * @author chengxiwang
 * @version v0.1
 * @data 2018年09月15日 11:22:39
 */
public class PhantomJsDriverUtils {


    public static WebDriver getPhantomJsDriver() {
        System.setProperty("phantomjs.binary.path", "D:\\BenJiRuanJian\\phantomjs\\bin\\phantomjs.exe");//设置PhantomJs访问路径
        DesiredCapabilities tempCaps = DesiredCapabilities.phantomjs();
        tempCaps.setJavascriptEnabled(true);
        //ssl证书支持
        tempCaps.setCapability("acceptSslCerts", true);
        //截屏支持
        tempCaps.setCapability("takesScreenshot", true);
        //css搜索支持
        tempCaps.setCapability("cssSelectorsEnabled", true);
        //js支持
        tempCaps.setJavascriptEnabled(true);
        tempCaps.setCapability(PhantomJSDriverService.PHANTOMJS_EXECUTABLE_PATH_PROPERTY, "D:\\BenJiRuanJian\\phantomjs\\bin\\phantomjs.exe");
        WebDriver tempWebDriver = null;
//        tempCaps.setCapability("phantomjs.page.settings.userAgent", "Mozilla/5.0 (Macintosh; Intel Mac OS X 10_13_5) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/68.0.3440.106 Safari/537.36");
        tempCaps.setCapability("phantomjs.page.settings.userAgent", "Mozilla/5.0 (Windows NT 6.3; Win64; x64; rv:50.0) Gecko/20100101 Firefox/50.0");
        tempCaps.setCapability("phantomjs.page.customHeaders.User-Agent", "Mozilla/5.0 (Windows NT 6.3; Win64; x64; rv:50.0) Gecko/20100101 　　Firefox/50.0");


        ArrayList<String> cliArgsCap = new ArrayList<String>();
        cliArgsCap.add("--web-security=false");
        cliArgsCap.add("--ssl-protocol=any");
        cliArgsCap.add("--ignore-ssl-errors=true");
        cliArgsCap.add("--load-images=no");
        tempCaps.setCapability(PhantomJSDriverService.PHANTOMJS_CLI_ARGS, cliArgsCap);
        tempCaps.setCapability(PhantomJSDriverService.PHANTOMJS_GHOSTDRIVER_CLI_ARGS,
                new String[] { "--logLevel=NONE" });
        tempWebDriver = new PhantomJSDriver(tempCaps);
        return tempWebDriver;
    }


}
