/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Selenium;


import java.util.concurrent.TimeUnit;
import org.junit.*;
import static org.junit.Assert.*;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;


public class ManagerSideTest {
  private WebDriver driver;
  private String baseUrl;
  private boolean acceptNextAlert = true;
  private final StringBuffer verificationErrors = new StringBuffer();

  @Before
  public void setUp() throws Exception {
    System.setProperty("webdriver.chrome.driver", "/home/carolinaalbuquerque");
    driver= new ChromeDriver();
    baseUrl = "https://www.katalon.com/";
    driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
  }

  @Test
  public void testUntitledTestCase() throws Exception {
    driver.get("http://deti-tqs-02.ua.pt:8080/goCamping/");
    driver.findElement(By.linkText("Login/Sign Up")).click();
    driver.findElement(By.name("j_idt5:j_idt20:loginForm:j_idt29")).click();
    driver.findElement(By.xpath("//div[@id='j_idt5:j_idt18:j_idt21']/div/label")).click();
    driver.findElement(By.id("input_j_idt5:j_idt18:loginSignUp:j_idt23")).click();
    driver.findElement(By.id("input_j_idt5:j_idt18:loginSignUp:j_idt23")).clear();
    driver.findElement(By.id("input_j_idt5:j_idt18:loginSignUp:j_idt23")).sendKeys("Carlos Andrade");
    driver.findElement(By.id("input_j_idt5:j_idt18:loginSignUp:j_idt25")).clear();
    driver.findElement(By.id("input_j_idt5:j_idt18:loginSignUp:j_idt25")).sendKeys("andrade@mail.com");
    driver.findElement(By.id("input_j_idt5:j_idt18:loginSignUp:j_idt27")).clear();
    driver.findElement(By.id("input_j_idt5:j_idt18:loginSignUp:j_idt27")).sendKeys("12341");
    driver.findElement(By.id("input_j_idt5:j_idt18:loginSignUp:j_idt29")).clear();
    driver.findElement(By.id("input_j_idt5:j_idt18:loginSignUp:j_idt29")).sendKeys("33");
    driver.findElement(By.id("input_j_idt5:j_idt18:loginSignUp:j_idt31")).clear();
    driver.findElement(By.id("input_j_idt5:j_idt18:loginSignUp:j_idt31")).sendKeys("carlos");
    driver.findElement(By.id("input_j_idt5:j_idt18:loginSignUp:j_idt33")).clear();
    driver.findElement(By.id("input_j_idt5:j_idt18:loginSignUp:j_idt33")).sendKeys("password");
    driver.findElement(By.name("j_idt5:j_idt18:loginSignUp:j_idt39")).click();
    driver.findElement(By.id("input_j_idt5:j_idt20:loginForm:j_idt22")).click();
    driver.findElement(By.id("input_j_idt5:j_idt20:loginForm:j_idt22")).clear();
    driver.findElement(By.id("input_j_idt5:j_idt20:loginForm:j_idt22")).sendKeys("carlos");
    driver.findElement(By.id("input_j_idt5:j_idt20:loginForm:j_idt24")).click();
    driver.findElement(By.id("input_j_idt5:j_idt20:loginForm:j_idt24")).clear();
    driver.findElement(By.id("input_j_idt5:j_idt20:loginForm:j_idt24")).sendKeys("password");
    driver.findElement(By.name("j_idt5:j_idt20:loginForm:j_idt30")).click();
    driver.findElement(By.linkText("Add Campsite")).click();
    driver.findElement(By.id("input_j_idt5:title")).click();
    driver.findElement(By.id("input_j_idt5:title")).clear();
    driver.findElement(By.id("input_j_idt5:title")).sendKeys("Parque de Campismo da Fajã do Ouvidor");
    driver.findElement(By.id("input_j_idt5:location")).click();
    driver.findElement(By.id("input_j_idt5:location")).clear();
    driver.findElement(By.id("input_j_idt5:location")).sendKeys("São Jorge, Açores");
    driver.findElement(By.id("input_j_idt5:Description")).click();
    driver.findElement(By.id("input_j_idt5:Description")).clear();
    driver.findElement(By.id("input_j_idt5:Description")).sendKeys("Local tranquilo e ideal para umas férias e contacto com a Natureza. Locais atrativos e naturais perto do parque");
    driver.findElement(By.id("input_j_idt5:contacts")).click();
    driver.findElement(By.id("input_j_idt5:contacts")).clear();
    driver.findElement(By.id("input_j_idt5:contacts")).sendKeys("23173243");
    driver.findElement(By.id("input_j_idt5:j_idt21")).click();
    driver.findElement(By.id("input_j_idt5:j_idt21")).clear();
    driver.findElement(By.id("input_j_idt5:j_idt21")).sendKeys("7.0");
    driver.findElement(By.id("input_j_idt5:j_idt24")).click();
    driver.findElement(By.id("input_j_idt5:j_idt24")).clear();
    driver.findElement(By.id("input_j_idt5:j_idt24")).sendKeys("17.0");
    driver.findElement(By.id("input_j_idt5:j_idt25")).click();
    driver.findElement(By.id("input_j_idt5:j_idt25")).clear();
    driver.findElement(By.id("input_j_idt5:j_idt25")).sendKeys("8.0");
    driver.findElement(By.name("j_idt5:j_idt29")).click();
    driver.findElement(By.name("form:campsitesID:0:j_idt34")).click();
    driver.findElement(By.id("input_j_idt5:Description")).click();
    driver.findElement(By.id("input_j_idt5:Description")).clear();
    driver.findElement(By.id("input_j_idt5:Description")).sendKeys("Local tranquilo e ideal para umas férias e contacto com a Natureza. Locais atrativos e naturais perto do parque.\nWi-Fi gratuito");
    driver.findElement(By.name("j_idt5:j_idt27")).click();
    driver.findElement(By.linkText("Account")).click();
    driver.findElement(By.id("input_j_idt5:j_idt37")).click();
    driver.findElement(By.id("input_j_idt5:j_idt37")).click();
    driver.findElement(By.id("input_j_idt5:j_idt37")).clear();
    driver.findElement(By.id("input_j_idt5:j_idt37")).sendKeys("Carlos Bernardo Andrade");
    driver.findElement(By.name("j_idt5:j_idt44")).click();
    driver.findElement(By.id("j_idt5:j_idt16")).click();
  }

  @After
  public void tearDown() throws Exception {
    driver.quit();
    String verificationErrorString = verificationErrors.toString();
    if (!"".equals(verificationErrorString)) {
      fail(verificationErrorString);
    }
  }

  private boolean isElementPresent(By by) {
    try {
      driver.findElement(by);
      return true;
    } catch (NoSuchElementException e) {
      return false;
    }
  }

  private boolean isAlertPresent() {
    try {
      driver.switchTo().alert();
      return true;
    } catch (NoAlertPresentException e) {
      return false;
    }
  }

  private String closeAlertAndGetItsText() {
    try {
      Alert alert = driver.switchTo().alert();
      String alertText = alert.getText();
      if (acceptNextAlert) {
        alert.accept();
      } else {
        alert.dismiss();
      }
      return alertText;
    } finally {
      acceptNextAlert = true;
    }
  }
}
