///*
// * To change this license header, choose License Headers in Project Properties.
// * To change this template file, choose Tools | Templates
// * and open the template in the editor.
// */
//package Selenium;
//
//
//import java.util.regex.Pattern;
//import java.util.concurrent.TimeUnit;
//import org.junit.*;
//import static org.junit.Assert.*;
//import static org.hamcrest.CoreMatchers.*;
//import org.openqa.selenium.*;
//import org.openqa.selenium.chrome.ChromeDriver;
//import org.openqa.selenium.support.ui.Select;
//
//public class CamperSideTest {
//  private WebDriver driver;
//  private String baseUrl;
//  private boolean acceptNextAlert = true;
//  private StringBuffer verificationErrors = new StringBuffer();
//
//  @Before
//  public void setUp() throws Exception {
//    driver = new ChromeDriver();
//    baseUrl = "https://www.katalon.com/";
//    driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
//  }
//
//  @Test
//  public void testUntitledTestCase() throws Exception {
//    driver.get("http://deti-tqs-02.ua.pt:8080/goCamping/faces/index.xhtml");
//    driver.findElement(By.name("j_idt5:j_idt22")).click();
//    driver.findElement(By.id("input_j_idt5:j_idt18")).click();
//    driver.findElement(By.id("input_j_idt5:j_idt18")).click();
//    // ERROR: Caught exception [ERROR: Unsupported command [doubleClick | id=input_j_idt5:j_idt18 | ]]
//    driver.findElement(By.id("input_j_idt5:j_idt18")).clear();
//    driver.findElement(By.id("input_j_idt5:j_idt18")).sendKeys("Aveiro");
//    driver.findElement(By.name("j_idt5:j_idt21")).click();
//    driver.findElement(By.xpath("//div[@id='j_idt5:campsites:0:j_idt24_content']/table/tbody/tr[7]/td")).click();
//    driver.findElement(By.linkText("Login/Sign Up")).click();
//    driver.findElement(By.name("j_idt5:j_idt20:loginForm:j_idt29")).click();
//    driver.findElement(By.id("j_idt5_j_idt18_pane")).click();
//    driver.findElement(By.xpath("//div[@id='j_idt5:j_idt18:j_idt20']/div/label")).click();
//    driver.findElement(By.id("input_j_idt5:j_idt18:loginSignUp:j_idt23")).click();
//    driver.findElement(By.id("input_j_idt5:j_idt18:loginSignUp:j_idt23")).clear();
//    driver.findElement(By.id("input_j_idt5:j_idt18:loginSignUp:j_idt23")).sendKeys("Bernardo Silva");
//    driver.findElement(By.id("input_j_idt5:j_idt18:loginSignUp:j_idt25")).click();
//    driver.findElement(By.id("input_j_idt5:j_idt18:loginSignUp:j_idt25")).clear();
//    driver.findElement(By.id("input_j_idt5:j_idt18:loginSignUp:j_idt25")).sendKeys("bernas@gmail.com");
//    driver.findElement(By.id("input_j_idt5:j_idt18:loginSignUp:j_idt27")).click();
//    driver.findElement(By.id("input_j_idt5:j_idt18:loginSignUp:j_idt27")).clear();
//    driver.findElement(By.id("input_j_idt5:j_idt18:loginSignUp:j_idt27")).sendKeys("1992342");
//    driver.findElement(By.id("input_j_idt5:j_idt18:loginSignUp:j_idt29")).click();
//    driver.findElement(By.id("input_j_idt5:j_idt18:loginSignUp:j_idt29")).click();
//    driver.findElement(By.id("input_j_idt5:j_idt18:loginSignUp:j_idt29")).click();
//    // ERROR: Caught exception [ERROR: Unsupported command [doubleClick | id=input_j_idt5:j_idt18:loginSignUp:j_idt29 | ]]
//    driver.findElement(By.id("input_j_idt5:j_idt18:loginSignUp:j_idt29")).clear();
//    driver.findElement(By.id("input_j_idt5:j_idt18:loginSignUp:j_idt29")).sendKeys("37");
//    driver.findElement(By.id("input_j_idt5:j_idt18:loginSignUp:j_idt31")).click();
//    driver.findElement(By.id("input_j_idt5:j_idt18:loginSignUp:j_idt31")).clear();
//    driver.findElement(By.id("input_j_idt5:j_idt18:loginSignUp:j_idt31")).sendKeys("bernas");
//    driver.findElement(By.id("input_j_idt5:j_idt18:loginSignUp:j_idt33")).click();
//    driver.findElement(By.id("input_j_idt5:j_idt18:loginSignUp:j_idt33")).clear();
//    driver.findElement(By.id("input_j_idt5:j_idt18:loginSignUp:j_idt33")).sendKeys("password");
//    driver.findElement(By.name("j_idt5:j_idt18:loginSignUp:j_idt39")).click();
//    driver.findElement(By.id("input_j_idt5:j_idt20:loginForm:j_idt22")).click();
//    driver.findElement(By.id("input_j_idt5:j_idt20:loginForm:j_idt22")).clear();
//    driver.findElement(By.id("input_j_idt5:j_idt20:loginForm:j_idt22")).sendKeys("bernas");
//    driver.findElement(By.id("input_j_idt5:j_idt20:loginForm:j_idt24")).click();
//    driver.findElement(By.id("input_j_idt5:j_idt20:loginForm:j_idt24")).clear();
//    driver.findElement(By.id("input_j_idt5:j_idt20:loginForm:j_idt24")).sendKeys("password");
//    driver.findElement(By.name("j_idt5:j_idt20:loginForm:j_idt30")).click();
//    driver.findElement(By.id("input_j_idt5:searchLocation")).click();
//    driver.findElement(By.name("j_idt5:j_idt21")).click();
//    driver.findElement(By.name("j_idt5:campsites:0:j_idt38")).click();
//    driver.findElement(By.linkText("Search")).click();
//    driver.findElement(By.id("input_j_idt5:searchLocation")).click();
//    driver.findElement(By.id("input_j_idt5:searchLocation")).click();
//    // ERROR: Caught exception [ERROR: Unsupported command [doubleClick | id=input_j_idt5:searchLocation | ]]
//    driver.findElement(By.id("input_j_idt5:searchLocation")).click();
//    driver.findElement(By.id("input_j_idt5:searchLocation")).click();
//    driver.findElement(By.id("input_j_idt5:searchLocation")).clear();
//    driver.findElement(By.id("input_j_idt5:searchLocation")).sendKeys("Açores");
//    driver.findElement(By.name("j_idt5:j_idt21")).click();
//    driver.findElement(By.name("j_idt5:campsites:1:j_idt38")).click();
//    driver.findElement(By.linkText("Search")).click();
//    driver.findElement(By.name("j_idt5:j_idt22")).click();
//    driver.findElement(By.name("j_idt5:campsites:3:j_idt39")).click();
//    driver.findElement(By.id("j_idt5:loginForm:j_idt25_input")).click();
//    driver.findElement(By.id("j_idt5:loginForm:j_idt25_input-group-addon")).click();
//    driver.findElement(By.id("j_idt5:loginForm:j_idt25_input-group-addon")).click();
//    driver.findElement(By.id("j_idt5:loginForm:j_idt25_input-group-addon")).click();
//    // ERROR: Caught exception [ERROR: Unsupported command [doubleClick | id=j_idt5:loginForm:j_idt25_input-group-addon | ]]
//    driver.findElement(By.linkText("12")).click();
//    driver.findElement(By.id("j_idt5:loginForm:j_idt27_input-group-addon")).click();
//    driver.findElement(By.linkText("14")).click();
//    driver.findElement(By.id("input_j_idt5:loginForm:j_idt30")).click();
//    driver.findElement(By.id("input_j_idt5:loginForm:j_idt30")).clear();
//    driver.findElement(By.id("input_j_idt5:loginForm:j_idt30")).sendKeys("3");
//    driver.findElement(By.id("input_j_idt5:loginForm:j_idt33")).click();
//    driver.findElement(By.id("input_j_idt5:loginForm:j_idt33")).clear();
//    driver.findElement(By.id("input_j_idt5:loginForm:j_idt33")).sendKeys("1");
//    driver.findElement(By.id("input_j_idt5:loginForm:j_idt50")).clear();
//    driver.findElement(By.id("input_j_idt5:loginForm:j_idt50")).sendKeys("91299993");
//    driver.findElement(By.id("j_idt5:j_idt17_content")).click();
//    driver.findElement(By.name("j_idt5:loginForm:j_idt54")).click();
//    driver.findElement(By.id("input_j_idt5:payment:j_idt23")).click();
//    driver.findElement(By.id("input_j_idt5:payment:j_idt23")).clear();
//    driver.findElement(By.id("input_j_idt5:payment:j_idt23")).sendKeys("362823");
//    driver.findElement(By.id("input_j_idt5:payment:j_idt25")).click();
//    driver.findElement(By.id("input_j_idt5:payment:j_idt25")).clear();
//    driver.findElement(By.id("input_j_idt5:payment:j_idt25")).sendKeys("223");
//    driver.findElement(By.name("j_idt5:payment:j_idt29")).click();
//    driver.findElement(By.linkText("Account")).click();
//    driver.findElement(By.id("input_j_idt5:j_idt23")).click();
//    driver.findElement(By.id("input_j_idt5:j_idt23")).click();
//    driver.findElement(By.id("input_j_idt5:j_idt23")).click();
//    driver.findElement(By.id("input_j_idt5:j_idt23")).clear();
//    driver.findElement(By.id("input_j_idt5:j_idt23")).sendKeys("Bernardo Santos Silva");
//    driver.findElement(By.name("j_idt5:j_idt32")).click();
//    driver.findElement(By.id("input_j_idt5:j_idt49")).click();
//    driver.findElement(By.id("input_j_idt5:j_idt49")).clear();
//    driver.findElement(By.id("input_j_idt5:j_idt49")).sendKeys("password");
//    driver.findElement(By.xpath("//div[@id='j_idt5:j_idt46_content']/table/tbody/tr[2]/td")).click();
//    driver.findElement(By.id("input_j_idt5:j_idt51")).click();
//    driver.findElement(By.id("input_j_idt5:j_idt51")).click();
//    driver.findElement(By.id("input_j_idt5:j_idt51")).clear();
//    driver.findElement(By.id("input_j_idt5:j_idt51")).sendKeys("pass");
//    driver.findElement(By.id("input_j_idt5:j_idt53")).click();
//    driver.findElement(By.id("input_j_idt5:j_idt53")).clear();
//    driver.findElement(By.id("input_j_idt5:j_idt53")).sendKeys("pass");
//    driver.findElement(By.xpath("//div[@id='j_idt5:j_idt58']/button/span/i")).click();
//    driver.findElement(By.linkText("My Reservations")).click();
//    driver.findElement(By.name("form:campsitesID:0:j_idt31")).click();
//    driver.findElement(By.linkText("Favourite List")).click();
//    driver.findElement(By.name("j_idt5:campsitesID:0:j_idt34")).click();
//    driver.findElement(By.id("j_idt5:j_idt16")).click();
//  }
//
//  @After
//  public void tearDown() throws Exception {
//    driver.quit();
//    String verificationErrorString = verificationErrors.toString();
//    if (!"".equals(verificationErrorString)) {
//      fail(verificationErrorString);
//    }
//  }
//
//  private boolean isElementPresent(By by) {
//    try {
//      driver.findElement(by);
//      return true;
//    } catch (NoSuchElementException e) {
//      return false;
//    }
//  }
//
//  private boolean isAlertPresent() {
//    try {
//      driver.switchTo().alert();
//      return true;
//    } catch (NoAlertPresentException e) {
//      return false;
//    }
//  }
//
//  private String closeAlertAndGetItsText() {
//    try {
//      Alert alert = driver.switchTo().alert();
//      String alertText = alert.getText();
//      if (acceptNextAlert) {
//        alert.accept();
//      } else {
//        alert.dismiss();
//      }
//      return alertText;
//    } finally {
//      acceptNextAlert = true;
//    }
//  }
//}
//
