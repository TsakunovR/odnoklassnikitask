package org.example;

import org.example.config.config.ConfProperties;
import org.example.config.pages.MainPage;
import org.example.config.pages.SettingsPage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;

import static org.example.config.data.Users.DEFAULT_USER;

public class PersonalDataNegativeTest {
    public static WebDriver driver;
    public static SettingsPage settingsPage;

    @BeforeMethod
    public static void setup(){
        System.setProperty("webdriver.chrome.driver", ConfProperties.getProperty("chromedriver"));
        driver = new ChromeDriver();
        MainPage mainPage = new MainPage(driver);
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.get(ConfProperties.getProperty("mainpage"));
        //TODO добавить ручку для сброса персональных данных пользователя в исходное состояние
        mainPage.loginField.sendKeys(DEFAULT_USER.getLogin());
        mainPage.passwordField.sendKeys(DEFAULT_USER.getPassword());
        mainPage.loginButton.click();
        driver.get(ConfProperties.getProperty("settingspage"));
        settingsPage = new SettingsPage(driver);
        settingsPage.personalDataField.click();
    }

    @DataProvider
    public Object[][] values() {
        return  new Object[][]{
                {"%$%&*?/^#"},
                {"21312312312"}
        };
    }

    @Test(description = "Заполнение имени некорректными данными", dataProvider = "values")
    public void editNameNegativeTest(String name) {
        settingsPage.nameField.clear();
        settingsPage.nameField.sendKeys(name);
        settingsPage.saveButton.click();
        settingsPage.nameErrorText.exists();
    }

    @Test(description = "Заполнение фамилии некоректными данными", dataProvider = "values")
    public void editSurnameNegativeTest(String surname) {
        settingsPage.nameField.clear();
        settingsPage.nameField.sendKeys(surname);
        settingsPage.saveButton.click();
        settingsPage.nameErrorText.exists();
    }

    @Test(description = "Очистка поля города проживания")
    public void clearCurrentCityNegativeTest() {
        settingsPage.currentCityField.clear();
        settingsPage.saveButton.click();
        settingsPage.cityErrorText.exists();
    }

    @AfterMethod
    public static void quit(){
        driver.quit();
    }
}
