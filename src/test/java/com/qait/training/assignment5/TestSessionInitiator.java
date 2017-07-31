package com.qait.training.assignment5;



import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import static com.qait.training.assignment5.ConfigPropertyReader.getProperty;
import static com.qait.training.assignment5.YamlReader.getYamlValue;
import static com.qait.training.assignment5.YamlReader.setYamlFilePath;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.Reporter;

import com.qait.training.assignment5.BasicActionKeywords;


public class TestSessionInitiator {

	protected WebDriver driver;
	private final WebDriverFactory wdfactory;
	String browser;
	String seleniumserver;
	String seleniumserverhost;
	String appbaseurl;
	String applicationpath;
	String chromedriverpath;
	String datafileloc = "";
	static int timeout;
	Map<String, Object> chromeOptions = null;
	DesiredCapabilities capabilities;

	/**
	 * Initiating the page objects
	 * 
	 */


	public BasicActionKeywords basictest;
	public AdvancedActionKeywords advancetest;

	

	public WebDriver getDriver() {
		return this.driver;
	}

	private void _initPage() {
	basictest= new BasicActionKeywords(driver);
	advancetest= new AdvancedActionKeywords(driver);


	}

	/**
	 * Page object Initiation done
	 * 
	 */
	public TestSessionInitiator(String testname) {
		wdfactory = new WebDriverFactory();
		testInitiator(testname);
	}

	private void testInitiator(String testname) {
		setYamlFilePath();
		_configureBrowser();
		_initPage();

	}

	private void _configureBrowser() {
		driver = wdfactory.getDriver(_getSessionConfig());
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Integer.parseInt(getProperty("timeout")), TimeUnit.SECONDS);
	}

	private Map<String, String> _getSessionConfig() {
		String[] configKeys = { "tier", "browser", "seleniumserver", "seleniumserverhost", "timeout", "driverpath" };
		Map<String, String> config = new HashMap<String, String>();
		for (String string : configKeys) {
			config.put(string, getProperty("./Config.properties", string));
		}
		return config;
	}

	public void launchApplication() {
		launchApplication(getYamlValue("Url"));
	}

	public void launchApplication(String loginUrl) {
		Reporter.log("\nThe application url is :- " + loginUrl, true);
		Reporter.log("The test browser is :- " + _getSessionConfig().get("browser") + "\n", true);
		driver.manage().window().maximize();
		driver.manage().deleteAllCookies();
		driver.get(loginUrl);
		
	}

	public void openUrl(String url) {
		driver.get(url);
	}

	public void closeBrowserSession() {
		Reporter.log("\n", true);
		driver.quit();
	}

	public void stepStartMessage(String testStepName) {
		Reporter.log(" ", true);
		Reporter.log("***** STARTING TEST STEP:- " + testStepName.toUpperCase() + " *****", true);
		Reporter.log(" ", true);
	}

}

