package es.isst.glucomed.utilities;



import java.util.regex.Pattern;
import java.util.concurrent.TimeUnit;
import org.junit.*;
import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;
import org.openqa.selenium.*;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;

public class pruebaInsertar {
  private WebDriver driver;
  private String baseUrl;
  private boolean acceptNextAlert = true;
  private StringBuffer verificationErrors = new StringBuffer();

  @Before
  public void setUp() throws Exception {
    driver = new FirefoxDriver();
    baseUrl = "http://localhost:8888";
    driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
  }

  @Test
  public void testPruebaInsertar() throws Exception {
    driver.get(baseUrl + "/login");
    driver.findElement(By.name("email")).clear();
    driver.findElement(By.name("email")).sendKeys("a@a.com");
    driver.findElement(By.name("password")).clear();
    driver.findElement(By.name("password")).sendKeys("a");
    driver.findElement(By.name("commit")).click();
    driver.findElement(By.linkText("Introducir")).click();
    assertEquals("Introduce Datos", driver.findElement(By.cssSelector("span.titulo")).getText());
    driver.findElement(By.name("fecha")).clear();
    driver.findElement(By.name("fecha")).sendKeys("2016-05-09");
    driver.findElement(By.name("hora")).clear();
    driver.findElement(By.name("hora")).sendKeys("20:45");
    driver.findElement(By.name("valorGlucosa")).clear();
    driver.findElement(By.name("valorGlucosa")).sendKeys("105");
    try {
      assertEquals("2016-05-09", driver.findElement(By.name("fecha")).getAttribute("value"));
    } catch (Error e) {
      verificationErrors.append(e.toString());
    }
    try {
      assertEquals("20:45", driver.findElement(By.name("hora")).getAttribute("value"));
    } catch (Error e) {
      verificationErrors.append(e.toString());
    }
    try {
      assertEquals("105", driver.findElement(By.name("valorGlucosa")).getAttribute("value"));
    } catch (Error e) {
      verificationErrors.append(e.toString());
    }
    driver.findElement(By.name("commit")).click();
    driver.findElement(By.linkText("Historial")).click();
    assertEquals("Historial de Datos", driver.findElement(By.cssSelector("span.titulo")).getText());
    assertEquals("2016-05-09", driver.findElement(By.cssSelector("#myTable > tr > td")).getText());
    assertEquals("20:45", driver.findElement(By.xpath("//tbody[@id='myTable']/tr/td[2]")).getText());
    assertEquals("105", driver.findElement(By.xpath("//tbody[@id='myTable']/tr/td[3]")).getText());
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
