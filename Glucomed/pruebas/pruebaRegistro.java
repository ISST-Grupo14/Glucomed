package es.isst.glucomed.utilities;



import java.util.regex.Pattern;
import java.util.concurrent.TimeUnit;
import org.junit.*;
import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;
import org.openqa.selenium.*;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;

public class pruebaRegistro {
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
  public void testPruebaRegistro() throws Exception {
    driver.get(baseUrl + "/");
    driver.findElement(By.name("registro")).click();
    assertEquals("Registro de usuario", driver.findElement(By.cssSelector("h3")).getText());
    driver.findElement(By.name("nombre")).clear();
    driver.findElement(By.name("nombre")).sendKeys("a");
    try {
      assertEquals("a", driver.findElement(By.name("apellidos")).getAttribute("value"));
    } catch (Error e) {
      verificationErrors.append(e.toString());
    }
    driver.findElement(By.name("apellidos")).clear();
    driver.findElement(By.name("apellidos")).sendKeys("a");
    try {
      assertEquals("a@a.com", driver.findElement(By.name("email")).getAttribute("value"));
    } catch (Error e) {
      verificationErrors.append(e.toString());
    }
    new Select(driver.findElement(By.name("tipoUser"))).selectByVisibleText("Paciente");
    driver.findElement(By.name("email")).clear();
    driver.findElement(By.name("email")).sendKeys("a@a.com");
//    try {
//      assertEquals("a@a.com", driver.findElement(By.name("email")).getAttribute("value"));
//    } catch (Error e) {
//      verificationErrors.append(e.toString());
//    }
    driver.findElement(By.name("password")).clear();
    driver.findElement(By.name("password")).sendKeys("a");
//    try {
//      assertEquals("a", driver.findElement(By.name("password_repeat")).getAttribute("value"));
//    } catch (Error e) {
//      verificationErrors.append(e.toString());
//    }
    driver.findElement(By.name("password_repeat")).clear();
    driver.findElement(By.name("password_repeat")).sendKeys("a");
//    try {
//      assertEquals("a", driver.findElement(By.name("password")).getAttribute("value"));
//    } catch (Error e) {
//      verificationErrors.append(e.toString());
//    }
    driver.findElement(By.name("commit")).click();
    assertEquals("Credenciales", driver.findElement(By.cssSelector("h3")).getText());
    //da error al registrarse por segunda vez
    // ERROR: Caught exception [unknown command []]
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
