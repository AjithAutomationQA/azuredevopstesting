package com.answerconnect.app.base;

import java.awt.Desktop;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Method;
import java.net.URL;
import java.security.GeneralSecurityException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Properties;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.ITestResult;
import org.testng.TestNG;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.w3c.dom.Attr;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.ios.IOSElement;
import io.appium.java_client.remote.MobileCapabilityType;
import io.appium.java_client.service.local.AppiumDriverLocalService;
import io.appium.java_client.service.local.AppiumServiceBuilder;

public class AutomationTest {

	public static IOSDriver<IOSElement> driver;
	public static Properties prop = new Properties();
	static InputStream input = null;

	public static AppiumDriverLocalService service;
	public static AppiumServiceBuilder builder;

	public static ExtentReports extent;
	public static ExtentTest test;
	public static ExtentTest childTest;
	public static List<String> testClasses = new ArrayList<String>();


	public static void main(String[] args) throws IOException, GeneralSecurityException, InterruptedException {

		startUp();
		TestNG testng = new TestNG();
		List<String> suites = new ArrayList<String>();
		String file = "./src/test/resources/properties/test-suites/deployment/deployment.xml";
		suites.add(file);
		testng.setTestSuites(suites);
		testng.run();
	}


	public static void startUp() throws IOException, GeneralSecurityException, InterruptedException {

		loadPropertiesFile();
		generateTestNGXml_Deployment();
		report_Deployment();
		launchApp();

	}


	public static void report_Deployment() throws IOException {
		extent = new ExtentReports();
		ExtentSparkReporter spark = new ExtentSparkReporter("index.html");
		//		ExtentSparkReporter failedspark = new ExtentSparkReporter("failed-tests-index.html").filter().statusFilter().as(new Status [] {(Status.FAIL)}).apply();
		final File CONF = new File("config.xml");
		spark.loadXMLConfig(CONF);
		extent.attachReporter(spark);

	}


	@BeforeMethod
	public void beforeTest(Method method) throws Exception {
		String testcaseName = method.getName();
		try {
			test = extent.createTest(testcaseName);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println(e.getMessage());
		}
	}


	@BeforeTest
	public static void launchApp() throws InterruptedException, IOException {
		try {
			System.out.println("launch app started");
			loadPropertiesFile();
			String platformName = prop.getProperty("platformName");
			String deviceName = prop.getProperty("deviceName");
			String platformVersion = prop.getProperty("platformVersion");
			String udid = prop.getProperty("udid");
			//			String automationName = prop.getProperty("automationName");
			//			for (String str : testClasses) {
			//				String testCaseName = str.trim();
			DesiredCapabilities capabilities = new DesiredCapabilities();

			capabilities.setCapability("platformName", platformName);
			capabilities.setCapability("deviceName", deviceName);
			capabilities.setCapability("platformVersion", platformVersion);
			capabilities.setCapability("automationName", prop.getProperty("automationName"));


			if (prop.getProperty("deviceType").equalsIgnoreCase("RealDevice")) {

				capabilities.setCapability("udid", udid);
				capabilities.setCapability("xcodeOrgId", prop.getProperty("xcodeOrgId"));
				capabilities.setCapability("xcodeSigningId", prop.getProperty("xcodeSigningId"));
				capabilities.setCapability("bundleId", prop.getProperty("bundleId"));
				// capabilities.setCapability("useNewWDA", true);

			}
			else if (prop.getProperty("deviceType").equalsIgnoreCase("Simulator")) {


				// capabilities.setCapability("app", prop.getProperty("app"));
				capabilities.setCapability("bundleId", prop.getProperty("bundleId"));
				capabilities.setCapability("autoAcceptAlerts", true);
			}
			capabilities.setCapability("noReset", false);
			capabilities.setCapability("newCommandTimeout", 180);
			capabilities.setCapability(MobileCapabilityType.FULL_RESET, false);
			capabilities.setCapability(MobileCapabilityType.NO_RESET, false);
			capabilities.setCapability("unicodeKeyboard", true);
			//			service = AppiumDriverLocalService.buildService(new AppiumServiceBuilder()
			//					.usingDriverExecutable(new File("/usr/local/bin/node"))
			//					.withAppiumJS(new File(
			//							"/Applications/Appium.app/Contents/Resources/app/node_modules/appium/build/lib/main.js"))
			//					.withArgument(GeneralServerFlag.LOCAL_TIMEZONE));
			//
			//			service.start();
			//driver = new IOSDriver<IOSElement>(, capabilities);
			//driver = new IOSDriver<IOSElement>(service.getUrl(), capabilities);
			driver = new IOSDriver<IOSElement>(new URL("http://0.0.0.0:4723/wd/hub"), capabilities);

			//				if (realDevice) {
			//					acceptAlert(360);
			//				}
		}catch(Exception e) {
			System.out.println(e.getCause());
			System.out.println(e.getMessage());
			e.printStackTrace();
		}
		System.out.println("completed launch app");

	}

	public static void loadPropertiesFile() throws IOException {
		input = new FileInputStream("./src/test/resources/properties/mobileCapabilities/capabilities.properties");
		prop.load(input);
	}

	public static List<String> readSheet() {

		List<String> executionList = new ArrayList<String>();
		try  

		{  
			System.out.println("read sheet started");
	//		File file = new File("/Users/arun/eclipse-workspace/ANCT_iOS_Automation/src/test/resources/sheet/TestModuleSheet.xlsx"); //creating a new file instance  
			File file = new File("/Users/arun/Documents/GitHub/AnsweringService-IOS-Automation/src/test/resources/sheet/TestModuleSheet.xlsx");
			
			FileInputStream fis = new FileInputStream(file);   //obtaining bytes from the file  
			//creating Workbook instance that refers to .xlsx file  
			XSSFWorkbook wb = new XSSFWorkbook(fis);   
			XSSFSheet sheet = wb.getSheetAt(0);     //creating a Sheet object to retrieve object  
			Iterator<Row> itr = sheet.iterator();    //iterating over excel file  
			while (itr.hasNext())                 
			{  
				Row row = itr.next();
				Cell cell=row.getCell(1); //getting the cell representing the given column
				String value=cell.getStringCellValue();

				Cell cell2=row.getCell(0);

				if(value.trim().equalsIgnoreCase("Yes")){

					executionList.add(cell2.getStringCellValue());

				}

				System.out.println(value);



			} 
			System.out.println(executionList.toString());
		}  
		catch(Exception e)  
		{  
			e.printStackTrace();  
		}
		System.out.println("read sheet ended");

		return executionList;

	}

	public static void generateTestNGXml_Deployment() throws IOException, GeneralSecurityException {

		testClasses = readSheet();
		// testClasses1 = testClassesList("RegressionTestcases");

		try {
			DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder docBuilder = docFactory.newDocumentBuilder();

			// Create <suite> element
			Document doc = docBuilder.newDocument();

			Element suiteElement = doc.createElement("suite");
			doc.appendChild(suiteElement);

			Attr rootNameAttribute = doc.createAttribute("name");
			System.out.println(prop.getProperty("BrowserName"));
			rootNameAttribute.setValue(prop.getProperty("TestSuite"));
			suiteElement.setAttributeNode(rootNameAttribute);

			Attr orderAttribute = doc.createAttribute("preserve-order");
			orderAttribute.setValue("true");
			suiteElement.setAttributeNode(orderAttribute);

			// Attr parallelAttribute = doc.createAttribute("parallel");
			// parallelAttribute.setValue("tests");
			// suiteElement.setAttributeNode(parallelAttribute);

			Element testEle = doc.createElement("test");
			testEle.setAttribute("name", prop.getProperty("ProjectName"));
			suiteElement.appendChild(testEle);

			testEle.setAttribute("preserve-order", "true");
			// testEle.setAttribute("group-by-instances", "true");
			testEle.setAttribute("enabled", "true");

			Element classesNode = doc.createElement("classes");
			testEle.appendChild(classesNode);
			Element classEle;
			for (int i = 0; i < testClasses.size(); i++) {
				String className = testClasses.get(i);
				classEle = doc.createElement("class");
				classEle.setAttribute("name", "com.answerconnect.deployment.testcases." + className);
				classesNode.appendChild(classEle);

			}

			//			for (int i = 0; i < testClasses1.size(); i++) {
			//				String className = testClasses1.get(i);
			//				classEle = doc.createElement("class");
			//				classEle.setAttribute("name", "com.answerconnect.regression.testcases." + className);
			//				classesNode.appendChild(classEle);
			//
			//			}

			// for output to file, console
			TransformerFactory transformerFactory = TransformerFactory.newInstance();
			Transformer transformer = transformerFactory.newTransformer();
			transformer.setOutputProperty(OutputKeys.DOCTYPE_SYSTEM, "http://testng.org/testng-1.0.dtd");

			// for pretty print
			transformer.setOutputProperty(OutputKeys.INDENT, "yes");
			DOMSource source = new DOMSource(doc);

			// write to console or file
			StreamResult console = new StreamResult(System.out);
			StreamResult file = new StreamResult(
					new File("./src/test/resources/properties/test-suites/deployment/deployment.xml"));

			// write data
			transformer.transform(source, console);
			transformer.transform(source, file);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@AfterMethod
	public synchronized void afterMethod(ITestResult result) throws Exception {

		if (result.getStatus() == ITestResult.FAILURE) {
			childTest.fail(result.getThrowable());
			extent.flush();

			try {
				childTest.log(Status.FAIL, MediaEntityBuilder.createScreenCaptureFromBase64String(getBase64()).build());

			} catch (Exception e) {
				e.printStackTrace();
				childTest.log(Status.FAIL, "Unable to capture SNAPSHOT : " + e.toString());
				childTest.log(Status.FAIL, "DETAILS : " + e.toString());
				childTest.log(Status.FAIL, e.toString());
			}
			extent.flush();

		} else if (result.getStatus() == ITestResult.SKIP) {
			childTest.skip(result.getThrowable());
			extent.flush();
			try {
				//			String screenshotPath1 = getScreenshot(result.getName());
				//			childTest.log(Status.FAIL, "SNAPSHOT BELOW: " + childTest.addScreenCaptureFromPath(screenshotPath1));
				childTest.log(Status.SKIP, MediaEntityBuilder.createScreenCaptureFromBase64String(getBase64()).build());
			} catch (Exception e) {
				e.printStackTrace();
				childTest.log(Status.FAIL, "Unable to capture SNAPSHOT : " + e.toString());
			}
			extent.flush();

		} else if (result.getStatus() == ITestResult.SUCCESS) {
			String testcaseName = result.getName();
			childTest.log(Status.INFO, testcaseName + " :- Passed  ");
			extent.flush();
			Desktop.getDesktop().browse(new File("index.html").toURI());
		}
	}

	@AfterSuite
	public void tearDown() throws Exception {
//		driver.closeApp();
//		service.stop();
		System.out.println("In TearDown");
//		driver.quit();
		extent.flush();
//		service.stop();
		System.out.println("INFO - ###################################");
		System.out.println("INFO - Script Execution Complete");
		System.out.println("INFO - ####################################");
		Desktop.getDesktop().browse(new File("index.html").toURI());

	}


	public String getBase64() {
		return ((TakesScreenshot)driver).getScreenshotAs(OutputType.BASE64);
	}


}

