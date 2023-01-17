package com.BaseLayer;

import java.io.File;
import java.io.FileInputStream;
import java.time.Duration;
import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.opera.OperaDriver;

import io.github.bonigarcia.wdm.WebDriverManager;

public class BaseClass {

	public static WebDriver driver;
	public static Properties prop;

	public BaseClass() {
		prop = new Properties();

		try {
			FileInputStream fis = new FileInputStream(
					System.getProperty("user.dir")+"\\src\\main\\java\\com\\Config\\config.properties");
			prop.load(fis);
		} catch (Exception e) {

		}
	}

	public static void inilitization() {

		String browsername = prop.getProperty("browsername");

		if (browsername.equals("chrome")) {
//			System.setProperty("webdriver.chrome.driver",
//					"C:\\Users\\praf0\\Downloads\\chromedriver_win32\\chromedriver.exe");
			WebDriverManager.chromedriver().setup();
			driver = new ChromeDriver();
		} else if (browsername.equals("edge")) {
			System.setProperty("webdriver.edge.driver",
					"C:\\Users\\praf0\\Desktop\\Drivers\\edgedriver_win64\\msedgedriver.exe");
			driver = new EdgeDriver();
		} else if (browsername.equals("opera")) {
			System.setProperty("webdriver.opera.driver",
					"C:\\Users\\praf0\\Downloads\\operadriver_win64 (3)\\operadriver_win64\\operadriver.exe");
			driver = new OperaDriver();
		}

		driver.manage().window().maximize();
		driver.manage().timeouts()
				.pageLoadTimeout(Duration.ofSeconds(Integer.parseInt(prop.getProperty("pageLoadTime"))));

		driver.manage().timeouts()
				.implicitlyWait(Duration.ofSeconds(Integer.parseInt(prop.getProperty("implicitWait"))));

		driver.manage().deleteAllCookies();
		driver.get(prop.getProperty("url"));

	}
}
