package club.sdll.test;/*
package club.sdll.test;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Properties;
import java.util.Set;
import java.util.concurrent.BlockingDeque;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

import org.apache.commons.configuration.XMLConfiguration;
import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.phantomjs.PhantomJSDriver;
import org.openqa.selenium.phantomjs.PhantomJSDriverService;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import com.xghl.dtc.zeuscrawler.redis.RedisProxyProvider;
import com.xghl.dtc.zeuscrawler.util.SysLog;
import com.xghl.dtc.zeuscrawler.util.XMLConfigurationHolder;
import com.xghl.dtc.zeuscrawler.util.ZeusConstants;

import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.downloader.WebDriverWrapper;
import us.codecraft.webmagic.proxy.Proxy;

*/
/**
 * @author code4crafter@gmail.com <br>
 *         Date: 13-7-26 <br>
 *         Time: 下午1:41 <br>
 *//*

public class WebDriverPool {
	private Logger logger = Logger.getLogger(getClass());

	private static int DEFAULT_CAPACITY = 5;

	private int capacity = DEFAULT_CAPACITY;

	private final static int STAT_RUNNING = 1;

	private final static int STAT_CLODED = 2;

	private AtomicInteger stat = new AtomicInteger(STAT_RUNNING);

	private WebDriver mDriver = null;
	private boolean mAutoQuitDriver = true;

	public static final String DRIVER_FIREFOX = "firefox";
	public static final String DRIVER_CHROME = "chrome";
	public static final String DRIVER_PHANTOMJS = "phantomjs";

	// protected static DesiredCapabilities sCaps;
	private String driver;
	private String phantomjsExecPath;
	private String phantomjsDriverPath;
	private String phantomjsDriverLoglevel = "ERROR";
	private static WebDriverPool instance;
	private volatile boolean closed = false;

	private String ffPluginPath;

	private String firefoxBin;

	private String downloadBasePath;

	public String getFfPluginPath() {
		return ffPluginPath;
	}

	public void setFfPluginPath(String ffPluginPath) {
		this.ffPluginPath = ffPluginPath;
	}

	public String getDownloadBasePath() {
		return downloadBasePath;
	}

	public void setDownloadBasePath(String downloadBasePath) {
		this.downloadBasePath = downloadBasePath;
	}

	public String getFirefoxBin() {
		return firefoxBin;
	}

	public void setFirefoxBin(String firefoxBin) {
		this.firefoxBin = firefoxBin;
	}

	private int maxTryGetIPCount = 12;

	public int getMaxTryGetIPCount() {
		return maxTryGetIPCount;
	}

	public void setMaxTryGetIPCount(int maxTryGetIPCount) {
		this.maxTryGetIPCount = maxTryGetIPCount;
	}

	private WebDriverPool() {
		XMLConfiguration config = XMLConfigurationHolder.instance().getConfig();
		// useProxy = config.getBoolean(ZeusConstants.zeuscrawlerUserproxy,
		// false);
		driver = config.getString(ZeusConstants.phantomjsDriverName, DRIVER_PHANTOMJS);
		ffPluginPath = config.getString(ZeusConstants.phantomjsDriverName, DRIVER_PHANTOMJS);
		Properties props = System.getProperties();
		String osName = props.getProperty("os.name");
		if (osName.toLowerCase().contains("win")) {
			phantomjsExecPath = config.getString(ZeusConstants.phantomjsExecPathWin);
		} else {
			phantomjsExecPath = config.getString(ZeusConstants.phantomjsExecPathLinux);
		}

		firefoxBin = config.getString(ZeusConstants.phantomjsFirefoxBin);

		downloadBasePath = config.getString(ZeusConstants.phantomjsDownloadBasePath);

		phantomjsDriverPath = config.getString(ZeusConstants.phantomjsDriverPath);
		phantomjsDriverLoglevel = config.getString(ZeusConstants.phantomjsLoglevel, DRIVER_PHANTOMJS);
	}

	public static WebDriverPool getInstance() {
		if (instance == null) {
			synchronized (WebDriverPool.class) {
				instance = new WebDriverPool();
			}
		}
		return instance;
	}

	*/
/**
	 * Configure the GhostDriver, and initialize a WebDriver instance. This part
	 * of code comes from GhostDriver.
	 * https://github.com/detro/ghostdriver/tree/master/test/java/src/test/java/
	 * ghostdriver
	 * 
	 * @author bob.li.0718@gmail.com
	 * @throws IOException
	 *//*

	public void configure() throws IOException {

		// sCaps = new DesiredCapabilities();
		// sCaps.setJavascriptEnabled(true);
		// sCaps.setCapability("takesScreenshot", false);
		//
		// if (driver.equals(DRIVER_PHANTOMJS)) {
		// if (phantomjsExecPath != null) {
		// sCaps.setCapability(PhantomJSDriverService.PHANTOMJS_EXECUTABLE_PATH_PROPERTY,
		// phantomjsExecPath);
		// } else {
		// throw new IOException(String.format("Property '%s' not set!",
		// PhantomJSDriverService.PHANTOMJS_EXECUTABLE_PATH_PROPERTY));
		// }
		//
		// if (StringUtils.isNotEmpty(phantomjsDriverPath)) {
		// System.out.println("Test will use an external GhostDriver");
		// sCaps.setCapability(PhantomJSDriverService.PHANTOMJS_GHOSTDRIVER_PATH_PROPERTY,
		// phantomjsDriverPath);
		// } else {
		// System.out.println("Test will use PhantomJS internal GhostDriver");
		// }
		// }

//		 ArrayList<String> cliArgsCap = new ArrayList<String>();
		// cliArgsCap.add("--web-security=false");
		// cliArgsCap.add("--ssl-protocol=any");
		// cliArgsCap.add("--ignore-ssl-errors=true");
		// sCaps.setCapability(PhantomJSDriverService.PHANTOMJS_CLI_ARGS,
		// cliArgsCap);
		//
		// phantomjsDriverLoglevel = "NONE";
		// sCaps.setCapability(PhantomJSDriverService.PHANTOMJS_GHOSTDRIVER_CLI_ARGS,
		// new String[] { "--logLevel=" + phantomjsDriverLoglevel });
		//
		// if (isUrl(driver)) {
		// sCaps.setBrowserName("phantomjs");
		// mDriver = new RemoteWebDriver(new URL(driver), sCaps);
		// } else if (driver.equals(DRIVER_FIREFOX)) {
		// mDriver = new FirefoxDriver(sCaps);
		// } else if (driver.equals(DRIVER_CHROME)) {
		// mDriver = new ChromeDriver(sCaps);
		// } else if (driver.equals(DRIVER_PHANTOMJS)) {
		// mDriver = new PhantomJSDriver(sCaps);
		// // driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		// }

		// org.openqa.selenium.Proxy proxy = new org.openqa.selenium.Proxy();
		// proxy.setHttpProxy(proxyHost);
		// DesiredCapabilities cap = new DesiredCapabilities().phantomjs();
		// ArrayList<String> cliArgsCap = new ArrayList<String>();
		// cliArgsCap.add("--proxy=proxy");
		// cliArgsCap.add("--proxy-type=http");
		// cap.setCapability(PhantomJSDriverService.PHANTOMJS_CLI_ARGS, proxy);

		// PhantomJSDriver p= new PhantomJSDriver(sCaps);

		// org.openqa.selenium.Proxy proxy = new org.openqa.selenium.Proxy();
		// proxy.setProxyType(org.openqa.selenium.Proxy.ProxyType.MANUAL);
		// proxy.setAutodetect(false);
		// proxy.setHttpProxy("");
		// sCaps.setCapability(CapabilityType.PROXY, proxy);

		// ArrayList<String> cliArgsCap = new ArrayList<>();
		// cliArgsCap.add("--proxy=http://"+HOST+":"+PORT);
		// cliArgsCap.add("--proxy-auth=" + USER + ":" + PWD);
		// cliArgsCap.add("--proxy-type=http");
		// capabilities.setCapability(PhantomJSDriverService.PHANTOMJS_CLI_ARGS,
		// cliArgsCap);
		// capabilities.setCapability("phantomjs.page.settings.userAgent", "");
	}

	*/
/**
	 * check whether input is a valid URL
	 * 
	 * @author bob.li.0718@gmail.com
	 * @param urlString
	 *            urlString
	 * @return true means yes, otherwise no.
	 *//*

	private boolean isUrl(String urlString) {
		try {
			new URL(urlString);
			return true;
		} catch (MalformedURLException mue) {
			return false;
		}
	}

	*/
/**
	 * store webDrivers created
	 *//*

	private List<WebDriver> webDriverList = Collections.synchronizedList(new ArrayList<WebDriver>());

	*/
/**
	 * store webDrivers available
	 *//*

	private BlockingDeque<WebDriver> innerQueue = new LinkedBlockingDeque<WebDriver>();

	*/
/**
	 * 
	 * @return
	 * @throws InterruptedException
	 *//*

	public WebDriver get() throws InterruptedException {
		checkRunning();
		WebDriver poll = innerQueue.poll();
		if (poll != null) {
			return poll;
		}
		if (webDriverList.size() < capacity) {
			synchronized (webDriverList) {
				if (webDriverList.size() < capacity) {

					try {
						configure();
						innerQueue.add(mDriver);
						webDriverList.add(mDriver);
					} catch (IOException e) {
						e.printStackTrace();
					}

					// ChromeDriver e = new ChromeDriver();
					// WebDriver e = getWebDriver();
					// innerQueue.add(e);
					// webDriverList.add(e);
				}
			}

		}
		return innerQueue.take();
	}

	public WebDriverWrapper getTempWebDriver(Site site) {
		DesiredCapabilities tempCaps = new DesiredCapabilities();
		tempCaps.setJavascriptEnabled(true);
		tempCaps.setCapability("takesScreenshot", false);

		String tempDriver = driver;
		if (StringUtils.isNotEmpty(site.getDriver())) {
			tempDriver = site.getDriver();
		}
		if (tempDriver.equals(DRIVER_PHANTOMJS)) {
			if (phantomjsExecPath != null) {
				tempCaps.setCapability(PhantomJSDriverService.PHANTOMJS_EXECUTABLE_PATH_PROPERTY, phantomjsExecPath);
			}

			if (StringUtils.isNotEmpty(phantomjsDriverPath)) {
				System.out.println("Test will use an external GhostDriver");
				tempCaps.setCapability(PhantomJSDriverService.PHANTOMJS_GHOSTDRIVER_PATH_PROPERTY, phantomjsDriverPath);
			} else {
				System.out.println("Test will use PhantomJS internal GhostDriver");
			}
		}
		boolean useProxy = site.isUseIPProxy();
		Proxy tempProxy = null;

		if (useProxy) {
			tempProxy = RedisProxyProvider.instance().getProxy(Proxy.TYPE_SITE_DIGGER,site);
			if (tempProxy == null) {
				for(int i=0;i<5;i++)
				{
					tempProxy = RedisProxyProvider.instance().getProxy(Proxy.TYPE_SITE_DIGGER,site);
					if (tempProxy != null) {
						break;
					} 
				}
			}
				if (tempProxy == null) {
					SysLog.error("no proxy for tempWebDriver.has try " + maxTryGetIPCount + " time");
				}
		}

		WebDriver tempWebDriver = null;
		if (isUrl(tempDriver)) {
			tempCaps.setBrowserName("phantomjs");
			try {
				tempWebDriver = new RemoteWebDriver(new URL(driver), tempCaps);
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else if (tempDriver.equals(DRIVER_FIREFOX)) {

			// tempCaps.setJavascriptEnabled(true);
			// tempCaps.setCapability("takesScreenshot", false);
			// cliArgsCap.add("--web-security=false");
			// cliArgsCap.add("--ssl-protocol=any");
			// cliArgsCap.add("--ignore-ssl-errors=true");
			// cliArgsCap.add("--load-images=false"); //不加载图片
			// tempCaps.setCapability("ignoreProtectedModeSettings", true);
			//
			// tempCaps.setCapability(CapabilityType.ForSeleniumServer.AVOIDING_PROXY,
			// true);
			// tempCaps.setCapability(CapabilityType.ForSeleniumServer.ONLY_PROXYING_SELENIUM_TRAFFIC,
			// true);
			// tempCaps.setCapability(CapabilityType.ACCEPT_SSL_CERTS, true);
			// tempCaps.setPlatform(Platform.ANY);
			// tempCaps.setBrowserName("firefox");
			//
			//
			// org.openqa.selenium.Proxy proxy1 = new
			// org.openqa.selenium.Proxy();
			// proxy1.setProxyType(org.openqa.selenium.Proxy.ProxyType.MANUAL);
			// proxy1.setAutodetect(false);
			// proxy1.setHttpProxy(tempProxy.getIPInfo());
			// proxy1.setSslProxy(tempProxy.getIPInfo());
			//// tempCaps.setCapability(CapabilityType.PROXY, proxy1);
			//
			// FirefoxProfile profile = new FirefoxProfile();
			// profile.setEnableNativeEvents(true);
			// profile.setPreference("browser.download.folderList", 2);
			// profile.setPreference("browser.download.dir", downloadBasePath);
			//
			// profile.setAcceptUntrustedCertificates(true);
			// profile.setAssumeUntrustedCertificateIssuer(false);
			// profile.setEnableNativeEvents(false);
			// profile.setPreference("network.proxy.type", 1);
			// if (tempProxy != null)
			// {
			// profile.setPreference("network.proxy.http", tempProxy.getHost());
			// profile.setPreference("network.proxy.http_port",
			// tempProxy.getPort());
			// }
			//
			// profile.setPreference("network.proxy.username",
			// tempProxy.getUsername());
			// profile.setPreference("network.proxy.password",
			// tempProxy.getPassword());
			//
			// tempCaps.setCapability("firefox_profile", profile);
			// System.setProperty("webdriver.firefox.bin", firefoxBin);
			//
			//
			//
			//
			//
			//
			//
			//
			// File modifyHeaders = new
			// File("plugin/modify_headers-0.7.1.1-fx.xpi");
			// profile.setEnableNativeEvents(false);
			// //profile.addExtension("modify_headers-0.7.1.1-fx.xpi", new
			// ClasspathExtension(AutoBuy.class,
			// "/plugin/modify_headers-0.7.1.1-fx.xpi"));
			// try
			// {
			// profile.addExtension(modifyHeaders);
			// } catch (IOException e)
			// {
			// e.printStackTrace();
			// }
			// profile.setPreference("modifyheaders.headers.count", 1);
			// profile.setPreference("modifyheaders.config.active", true);
			// profile.setPreference("modifyheaders.config.alwaysOn", true);
			//
			// profile.setPreference("modifyheaders.headers.action0", "Modify");
			// profile.setPreference("modifyheaders.headers.name0",
			// "User-Agent");
			// profile.setPreference("modifyheaders.headers.value0",
			// "Mozilla/5.0 (iPhone; CPU iPhone OS 8_1_3 like Mac OS X)
			// AppleWebKit/600.1.4 (KHTML, like Gecko) Mobile/12B466
			// MicroMessenger/6.2.4 NetType/WIFI Language/zh_CN");
			// profile.setPreference("modifyheaders.headers.enabled0", true);
			//

			FirefoxProfile profile = new FirefoxProfile();
			File modifyHeaders = new File(ffPluginPath + "/plugin/modify_headers-0.7.1.1-fx.xpi");
			profile.setEnableNativeEvents(false);
			try {
				profile.addExtension(modifyHeaders);
			} catch (IOException e) {
				e.printStackTrace();
			}

			profile.setPreference("permissions.default.image", 2);
			// 关掉flash
			profile.setPreference("dom.ipc.plugins.enabled.libflashplayer.so", false);
			// 禁用css,不方便调试了。。
			// fireFoxProfile.setPreference("permissions.default.stylesheet",
			// 2);

			// 启动快速加载，不过好像没什么改变。照官方说法在load结束前就可以开始操作，不过我这还是被blocked直到页面加载完毕
			// firefoxProfile.setPreference("webdriver.load.strategy",
			// "unstable");

			profile.setPreference("modifyheaders.headers.count", 1);
			profile.setPreference("modifyheaders.config.active", true);
			profile.setPreference("modifyheaders.config.alwaysOn", true);

			profile.setPreference("modifyheaders.headers.action0", "Modify");
			profile.setPreference("modifyheaders.headers.name0", "User-Agent");
			profile.setPreference("modifyheaders.headers.value0",
					"Mozilla/5.0 (iPhone; CPU iPhone OS 8_1_3 like Mac OS X) AppleWebKit/600.1.4 (KHTML, like Gecko) Mobile/12B466 MicroMessenger/6.2.4 NetType/WIFI Language/zh_CN");
			profile.setPreference("modifyheaders.headers.enabled0", true);
			// profile.setPreference("modifyheaders.headers.value0","Mozilla/5.0
			// (Windows NT 10.0; WOW64) AppleWebKit/537.36 (KHTML, like Gecko)
			// Chrome/58.0.3029.110 Safari/537.36");

			profile.setPreference("network.proxy.type", "1");
			// profile.setPreference("network.proxy.http", tempProxy.getHost());
			// profile.setPreference("network.proxy.http_port",
			// tempProxy.getPort()+"");
			profile.setPreference("network.proxy.no_proxies_on", "localhost, 127.0.0.1");

			File closeProxyFile = new File(ffPluginPath + "/plugin/close_proxy_authentication-1.1.xpi");
			try {
				profile.addExtension(closeProxyFile);
			} catch (IOException e) {
				e.printStackTrace();
			}

			// String credentials =
			// tempProxy.getUsername()+":"+tempProxy.getPassword();
			// profile.setPreference("extensions.closeproxyauth.authtoken",
			// credentials);

			DesiredCapabilities capabilities = DesiredCapabilities.firefox();
			capabilities.setBrowserName("firefox");
			capabilities.setPlatform(org.openqa.selenium.Platform.ANY);
			capabilities.setCapability(FirefoxDriver.PROFILE, profile);

			FirefoxDriver tempWebDriver2 = new FirefoxDriver(profile);

			tempWebDriver = tempWebDriver2;

		} else if (tempDriver.equals(DRIVER_CHROME)) {
			tempWebDriver = new ChromeDriver(tempCaps);
		} else if (tempDriver.equals(DRIVER_PHANTOMJS)) {
			if (StringUtils.isNotEmpty(site.getUserAgent())) {
				tempCaps.setCapability("phantomjs.page.settings.userAgent", site.getUserAgent());
			}
			ArrayList<String> cliArgsCap = new ArrayList<String>();
			cliArgsCap.add("--web-security=false");
			cliArgsCap.add("--ssl-protocol=any");
			cliArgsCap.add("--ignore-ssl-errors=true");
			cliArgsCap.add("--load-images=no");
			tempCaps.setCapability("takesScreenshot", false);
			tempCaps.setCapability(PhantomJSDriverService.PHANTOMJS_CLI_ARGS, cliArgsCap);
			tempCaps.setCapability(PhantomJSDriverService.PHANTOMJS_GHOSTDRIVER_CLI_ARGS,
					new String[] { "--logLevel=NONE" });
			Map<String, String> headers = site.getHeaders();
			if (headers != null && !headers.isEmpty()) {
				Set<Entry<String, String>> entrys = headers.entrySet();
				for (Entry<String, String> entry : entrys) {
					String keyPre = "phantomjs.page.customHeaders.";
					if (entry.getKey().toLowerCase().contains("userAgent")) {
						keyPre = "phantomjs.page.settings.";
					}
					tempCaps.setCapability(keyPre + entry.getKey(), entry.getValue());
				}
			}
			if(useProxy && tempProxy!=null)
			{
				 cliArgsCap.add("--proxy=" + tempProxy.getIPInfo());
				 cliArgsCap.add("--proxy-type=http");
				 
					if(tempProxy.getType()!=Proxy.TYPE_SITE_DIGGER)
					{
						org.openqa.selenium.Proxy proxy1 = new org.openqa.selenium.Proxy();
						proxy1.setProxyType(org.openqa.selenium.Proxy.ProxyType.MANUAL);
						proxy1.setAutodetect(false);
						proxy1.setHttpProxy(tempProxy.getIPInfo());
						proxy1.setSslProxy(tempProxy.getIPInfo());  
						if (tempProxy != null && tempProxy.isUsePasswd()) {
							proxy1.setSocksPassword(tempProxy.getPassword());
							proxy1.setSocksUsername(tempProxy.getUsername());
						}
						tempCaps.setCapability(CapabilityType.PROXY, proxy1); 
						cliArgsCap.add(	"--proxy-auth=" + tempProxy.getUsername().trim() + ":" + tempProxy.getPassword().trim());
					}
			}
			
			tempWebDriver = new PhantomJSDriver(tempCaps);
		}
		if (tempWebDriver != null) {
			tempWebDriver.manage().timeouts().implicitlyWait(100, TimeUnit.SECONDS).pageLoadTimeout(100, TimeUnit.SECONDS)
					.setScriptTimeout(100, TimeUnit.SECONDS);

			tempWebDriver.manage().deleteAllCookies();
			WebDriverWrapper webDriverWrapper = new WebDriverWrapper(tempWebDriver, tempProxy);
			return webDriverWrapper;
		}
		return null;
	}

	private class StartDriverRunnable implements Runnable {
		private WebDriver tempWebDriver;
		private String url;

		public StartDriverRunnable(WebDriver tempWebDriver, String url) {
			this.tempWebDriver = tempWebDriver;
			this.url = url;
		}

		@Override
		public void run() {
			tempWebDriver.navigate().to(url);
			this.tempWebDriver = null;
		}

	}

	public void closeWebDriver(WebDriver webDriver) {
		if (webDriver != null) {
			try {
				webDriver.close();
			} catch (Exception e) {

			}
			try {
				webDriver.quit();
			} catch (Exception e) {

			}

		}
	}

	public void returnToPool(WebDriver webDriver) {
		checkRunning();
		innerQueue.add(webDriver);
	}

	protected void checkRunning() {
		if (!stat.compareAndSet(STAT_RUNNING, STAT_RUNNING)) {
			logger.info("Already closed!");
		}
	}

	public void closeAll() {
		boolean b = stat.compareAndSet(STAT_RUNNING, STAT_CLODED);
		if (!b) {
			return;
		}
		for (WebDriver webDriver : webDriverList) {
			logger.info("Quit webDriver" + webDriver);
			webDriver.quit();
			webDriver = null;
		}

	}

}
*/
