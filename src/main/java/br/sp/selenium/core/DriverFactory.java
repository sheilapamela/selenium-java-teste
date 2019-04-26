package br.sp.selenium.core;

import java.net.MalformedURLException;
import java.net.URL;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.remote.RemoteWebDriver;

import br.sp.selenium.core.Execution.TypeExecution;

public class DriverFactory {
	
	private static ThreadLocal<WebDriver> threadDriver = new ThreadLocal<WebDriver>() {
		@Override
		protected synchronized WebDriver initialValue() {
			return initDriver();
		}
	};

	private DriverFactory() {
	}

	public static WebDriver getDriver() {
		return threadDriver.get();
	}

	public static WebDriver initDriver() {

		WebDriver driver = null;
		String driverType = System.getProperty("webDriver");

		if (Execution.Type_Execution == TypeExecution.LOCAL) {
			switch (driverType) {
			case "chromedriver":
				System.setProperty("webdriver.chrome.driver",
						System.getProperty("user.dir") + "/src/main/resources/drivers/win32/chromedriver.exe");
//				System.setProperty("webdriver.chrome.driver", "/usr/bin/chromedriver");

				ChromeOptions cOptions = new ChromeOptions();
				cOptions.setHeadless(false);
				driver = new ChromeDriver(cOptions);

				break;
			case "geckodriver":
				System.setProperty("webdriver.gecko.driver",
						System.getProperty("user.dir") + "/src/main/resources/drivers/win32/geckodriver.exe");
//				System.setProperty("webdriver.gecko.driver",
//					System.getProperty("user.dir") + "/src/main/resources/drivers/bin/geckodriver");

				FirefoxOptions fOptions = new FirefoxOptions();
				fOptions.setHeadless(false);
				driver = new FirefoxDriver(fOptions);

				break;
			}
		}

		if (Execution.Type_Execution == TypeExecution.GRID) {
			switch (driverType) {
			case "chromedriver":
				ChromeOptions cOptions = new ChromeOptions();
				cOptions.setHeadless(true);
				cOptions.setCapability("enableVNC", false);

				try {
					driver = new RemoteWebDriver(new URL("http://localhost:4444/wd/hub"), cOptions);
				} catch (MalformedURLException e) {
					System.err.println("Falha na conexão com o GRID");
					e.printStackTrace();
				}

				break;

			case "geckodriver":
				FirefoxProfile fProfile = new FirefoxProfile();
				fProfile.setPreference("browser.tabs.remote.autostart", false);
				fProfile.setPreference("browser.tabs.remote.autostart.1", false);
				fProfile.setPreference("browser.tabs.remote.autostart.2", false);

				FirefoxOptions fOptions = new FirefoxOptions();
				fOptions.setCapability("enableVNC", false);
				fOptions.setHeadless(true).setProfile(fProfile);

//				DesiredCapabilities capabilities = DesiredCapabilities.firefox();
//		        capabilities.setCapability("moz:firefoxOptions", fOptions);

				try {
//					driver = new RemoteWebDriver(new URL("http://localhost:4444/wd/hub"), DesiredCapabilities.firefox().merge(capabilities));
					driver = new RemoteWebDriver(new URL("http://localhost:4444/wd/hub"), fOptions);
				} catch (MalformedURLException e) {
					System.err.println("Falha na conexão com o GRID");
					e.printStackTrace();
				}

				break;
			}
		}
		return driver;
	}

	public static void killDriver() {
		WebDriver driver = getDriver();
		if (driver != null) {
			driver.quit();
			driver = null;
		}
		if (threadDriver != null) {
			threadDriver.remove();
		}
	}

}
