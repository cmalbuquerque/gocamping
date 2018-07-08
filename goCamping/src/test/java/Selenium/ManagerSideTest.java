///*
// * To change this license header, choose License Headers in Project Properties.
// * To change this template file, choose Tools | Templates
// * and open the template in the editor.
// */
//package Selenium;
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
//public class ManagerSideTest {
//  private WebDriver driver;
//  private String baseUrl;
//  private boolean acceptNextAlert = true;
//  private StringBuffer verificationErrors = new StringBuffer();
//
//  @Before
//  public void setUp() throws Exception {
//    driver = new ChromeDriver();
//    baseUrl = "https://www.katalon.com/";
//    driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
//  }
//
//  @Test
//  public void testManagerSide() throws Exception {
//    driver.get("http://localhost:8080/goCamping/");
//    driver.findElement(By.linkText("Login/Sign Up")).click();
//    driver.findElement(By.name("j_idt5:j_idt20:loginForm:j_idt29")).click();
//    driver.findElement(By.id("input_j_idt5:j_idt18:j_idt21")).click();
//    driver.findElement(By.id("input_j_idt5:j_idt18:loginSignUp:j_idt23")).click();
//    driver.findElement(By.id("input_j_idt5:j_idt18:loginSignUp:j_idt23")).clear();
//    driver.findElement(By.id("input_j_idt5:j_idt18:loginSignUp:j_idt23")).sendKeys("Diana Santos");
//    driver.findElement(By.id("input_j_idt5:j_idt18:loginSignUp:j_idt25")).click();
//    driver.findElement(By.id("input_j_idt5:j_idt18:loginSignUp:j_idt25")).clear();
//    driver.findElement(By.id("input_j_idt5:j_idt18:loginSignUp:j_idt25")).sendKeys("diana@gmail.com");
//    driver.findElement(By.id("input_j_idt5:j_idt18:loginSignUp:j_idt27")).click();
//    driver.findElement(By.id("input_j_idt5:j_idt18:loginSignUp:j_idt27")).clear();
//    driver.findElement(By.id("input_j_idt5:j_idt18:loginSignUp:j_idt27")).sendKeys("231882328");
//    driver.findElement(By.id("input_j_idt5:j_idt18:loginSignUp:j_idt29")).click();
//    driver.findElement(By.id("input_j_idt5:j_idt18:loginSignUp:j_idt29")).clear();
//    driver.findElement(By.id("input_j_idt5:j_idt18:loginSignUp:j_idt29")).sendKeys("1723");
//    driver.findElement(By.id("input_j_idt5:j_idt18:loginSignUp:j_idt31")).click();
//    driver.findElement(By.id("input_j_idt5:j_idt18:loginSignUp:j_idt31")).clear();
//    driver.findElement(By.id("input_j_idt5:j_idt18:loginSignUp:j_idt31")).sendKeys("diana");
//    driver.findElement(By.id("input_j_idt5:j_idt18:loginSignUp:j_idt33")).click();
//    driver.findElement(By.id("input_j_idt5:j_idt18:loginSignUp:j_idt33")).clear();
//    driver.findElement(By.id("input_j_idt5:j_idt18:loginSignUp:j_idt33")).sendKeys("pass");
//    driver.findElement(By.name("j_idt5:j_idt18:loginSignUp:j_idt39")).click();
//    driver.findElement(By.id("input_j_idt5:j_idt20:loginForm:j_idt22")).click();
//    driver.findElement(By.id("input_j_idt5:j_idt20:loginForm:j_idt22")).clear();
//    driver.findElement(By.id("input_j_idt5:j_idt20:loginForm:j_idt22")).sendKeys("diana");
//    driver.findElement(By.id("input_j_idt5:j_idt20:loginForm:j_idt24")).click();
//    driver.findElement(By.id("input_j_idt5:j_idt20:loginForm:j_idt24")).clear();
//    driver.findElement(By.id("input_j_idt5:j_idt20:loginForm:j_idt24")).sendKeys("pass");
//    driver.findElement(By.name("j_idt5:j_idt20:loginForm:j_idt30")).click();
//    driver.findElement(By.linkText("Add Campsite")).click();
//    driver.findElement(By.id("input_j_idt5:title")).click();
//    driver.findElement(By.id("input_j_idt5:title")).clear();
//    driver.findElement(By.id("input_j_idt5:title")).sendKeys("Parque de Campismo de Alvor");
//    driver.findElement(By.id("input_j_idt5:location")).click();
//    driver.findElement(By.id("input_j_idt5:location")).clear();
//    driver.findElement(By.id("input_j_idt5:location")).sendKeys("Alvor, Portimão");
//    driver.findElement(By.id("input_j_idt5:Description")).click();
//    driver.findElement(By.id("input_j_idt5:Description")).clear();
//    driver.findElement(By.id("input_j_idt5:Description")).sendKeys("Acolhedor, a 3 minutos do centro da Vila e perto da praia. Zona para crianças, Wi-Fi gratuito e piscina ar livre");
//    driver.findElement(By.id("input_j_idt5:contacts")).click();
//    driver.findElement(By.id("input_j_idt5:contacts")).clear();
//    driver.findElement(By.id("input_j_idt5:contacts")).sendKeys("238567432");
//    driver.findElement(By.id("input_j_idt5:j_idt21")).click();
//    driver.findElement(By.id("input_j_idt5:j_idt21")).clear();
//    driver.findElement(By.id("input_j_idt5:j_idt21")).sendKeys("10.0");
//    driver.findElement(By.id("input_j_idt5:j_idt24")).click();
//    driver.findElement(By.id("input_j_idt5:j_idt24")).clear();
//    driver.findElement(By.id("input_j_idt5:j_idt24")).sendKeys("33.0");
//    driver.findElement(By.id("input_j_idt5:j_idt25")).click();
//    driver.findElement(By.id("input_j_idt5:j_idt25")).clear();
//    driver.findElement(By.id("input_j_idt5:j_idt25")).sendKeys("14.0");
//    driver.findElement(By.name("j_idt5:j_idt29")).click();
//    driver.findElement(By.name("form:campsitesID:0:j_idt34")).click();
//    driver.findElement(By.id("input_j_idt5:Description")).click();
//    driver.findElement(By.id("input_j_idt5:Description")).clear();
//    driver.findElement(By.id("input_j_idt5:Description")).sendKeys("Acolhedor, a 3 minutos do centro da Vila e perto da praia. Zona para crianças, Wi-Fi gratuito e piscina ar livre.\nIdeal para umas férias em família");
//    driver.findElement(By.xpath("//div[@id='j_idt5:j_idt27']/button/span/i")).click();
//    driver.findElement(By.linkText("Account")).click();
//    driver.findElement(By.id("input_j_idt5:j_idt37")).click();
//    driver.findElement(By.id("input_j_idt5:j_idt37")).clear();
//    driver.findElement(By.id("input_j_idt5:j_idt37")).sendKeys("Diana Almeida Santos");
//    driver.findElement(By.name("j_idt5:j_idt44")).click();
//    driver.findElement(By.linkText("Search")).click();
//    driver.findElement(By.id("input_j_idt5:searchLocation")).click();
//    driver.findElement(By.id("input_j_idt5:searchLocation")).clear();
//    driver.findElement(By.id("input_j_idt5:searchLocation")).sendKeys("Alvor");
//    driver.findElement(By.name("j_idt5:j_idt21")).click();
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
