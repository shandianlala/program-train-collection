package club.sdll.test;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.StringUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.phantomjs.PhantomJSDriver;
import org.openqa.selenium.phantomjs.PhantomJSDriverService;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.TimeUnit;

/**
 * decription
 *
 * @author chengxiwang
 * @version v0.1
 * @data 2018年08月28日 09:04
 */
public class Test {

    public static void main(String[] args) throws InterruptedException {
//        System.setProperty("phantomjs.binary.path", "D:\\BenJiRuanJian\\phantomjs\\bin\\phantomjs.exe");//设置PhantomJs访问路径
        System.setProperty("phantomjs.binary.path", "/Users/chengxiwang/phantomjs/bin/phantomjs");//设置PhantomJs访问路径
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
//        tempCaps.setCapability(PhantomJSDriverService.PHANTOMJS_EXECUTABLE_PATH_PROPERTY, "D:\\BenJiRuanJian\\phantomjs\\bin\\phantomjs.exe");
        tempCaps.setCapability(PhantomJSDriverService.PHANTOMJS_EXECUTABLE_PATH_PROPERTY, "/Users/chengxiwang/phantomjs/bin/phantomjs");
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
        System.out.println("请求开始=====");
        //第一步进入登录页面
        tempWebDriver.get("http://www.zjzwfw.gov.cn/");
        Thread.sleep(3000);
        String page = tempWebDriver.getPageSource();
        tempWebDriver.findElement(By.xpath("//*[@id=\"loginbtn\"]")).click();

        boolean isdisplay = tempWebDriver.findElement(By.xpath("//*[@id=\"loginmenu\"]")).isDisplayed();
        //选择个人登录
        tempWebDriver.findElement(By.xpath("//*[@id=\"loginmenu\"]/ul/li[1]")).click();
//        tempWebDriver.get("https://puser.zjzwfw.gov.cn/sso/usp.do?action=ssoLogin&servicecode=njdh");
        page = tempWebDriver.getPageSource();
//        System.out.println("page=" + page);
        Thread.sleep(1000);
        //设置用户名和密码
        WebElement loginNameElement = tempWebDriver.findElement(By.xpath("//*[@id=\"loginname\"]"));
        loginNameElement.sendKeys("15697130000");
        System.out.println("用户名输入完成");
        Thread.sleep(2000);
        WebElement pwdElement = tempWebDriver.findElement(By.xpath("//*[@id=\"loginpwd\"]"));
        pwdElement.sendKeys("");
        System.out.println("密码输入完成");
        Thread.sleep(2000);
        tempWebDriver.findElement(By.xpath("//*[@id=\"submit\"]")).click();
        Thread.sleep(2000);

        String message = tempWebDriver.findElement(By.xpath("//*[@id=\"tab1\"]/div")).getText();
        String page2 = tempWebDriver.getPageSource();
        System.out.println("登录反馈消息message = " + message);
        String url = tempWebDriver.getCurrentUrl();
        System.out.println("current url = " + url);
        printCookies(tempWebDriver);
        File srcFile = ((TakesScreenshot)tempWebDriver).getScreenshotAs(OutputType.FILE);
        try {
            FileUtils.copyFile(srcFile, new File("/Users/chengxiwang/phantomjs/temp.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
//        System.out.println("登录反馈消息page2=" + page2);
//
//        tempWebDriver.get("http://www.zjzwfw.gov.cn/zjzw/member/grzx/userlist.do");
//        String page3 = tempWebDriver.getPageSource();
//        System.out.println("个人信息页面page3=" + page3);

    }

    private static void printCookies(WebDriver webDriver) {
        Set<Cookie> cookies = webDriver.manage().getCookies();
        if (cookies != null && cookies.size() > 0) {
            System.out.println("===开始打印cookies===");
            Iterator<Cookie> iterator = cookies.iterator();
            while (iterator.hasNext()) {
                Cookie cookie = iterator.next();
                System.out.println(cookie.getName() + "=" + cookie.getValue());
            }
            System.out.println("===结束打印cookies===");
        }
    }


    public static WebDriver getPhantomJs() {
        String osname = System.getProperties().getProperty("os.name");
        if (osname.equals("Linux")) {//判断系统的环境win or Linux
            System.setProperty("phantomjs.binary.path", "/usr/bin/phantomjs");
        } else {
            System.setProperty("phantomjs.binary.path", "./phantomjs/win/phantomjs.exe");//设置PhantomJs访问路径
        }
        DesiredCapabilities desiredCapabilities = DesiredCapabilities.phantomjs();
        //设置参数
        desiredCapabilities.setCapability("phantomjs.page.settings.userAgent", "Mozilla/5.0 (Windows NT 6.3; Win64; x64; rv:50.0) Gecko/20100101 Firefox/50.0");
        desiredCapabilities.setCapability("phantomjs.page.customHeaders.User-Agent", "Mozilla/5.0 (Windows NT 6.3; Win64; x64; rv:50.0) Gecko/20100101 　　Firefox/50.0");


        return new PhantomJSDriver(desiredCapabilities);
    }


}
