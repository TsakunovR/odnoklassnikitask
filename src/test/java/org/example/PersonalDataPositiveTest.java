package org.example;

import org.example.config.config.ConfProperties;
import org.example.config.pages.SettingsPage;
import org.example.config.pages.MainPage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;

import static org.example.config.data.Users.DEFAULT_USER;


public class PersonalDataPositiveTest {
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
    public Object[][] personalDataValues() {
        return  new Object[][]{
                {"Иван", "Иванов", "25", "апрель", "1990", "Санкт-Петербург", "Москва", true, "ТестИмя ТестФамилия, родился 25 апреля 1990 в городе Москва, сейчас живу в городе Санкт-Петербург"},
                {"Ivan", "Ivanov", "19", "сентябрь", "2007", "Рязань", "Калуга", true, "Ivan Ivanov, родился 19 сентября 2007 в городе Калуга, сейчас живу в городе Рязань"},
                {"Петр", "Петрович", "7", "май", "1994", "Сочи", "Геленджик", false, "ТестИмя ТестФамилия, родился 25 апреля 1990 в городе Москва, сейчас живу в городе Санкт-Петербург"}
        };
    }

    @Test(description = "Редактирование личных данных", dataProvider = "personalDataValues")
    public void editPersonalDataPositiveTest(String name, String surname, String birthDay, String birthMonth, String birthYear, String currentCity, String nativeCity, Boolean saveChanges, String personalData) {
        settingsPage.nameField.clear();
        settingsPage.nameField.sendKeys(name);
        settingsPage.surnameField.clear();
        settingsPage.surnameField.sendKeys(surname);
        settingsPage.birthDayField.sendKeys(birthDay);
        settingsPage.birthMonthField.sendKeys(birthMonth);
        settingsPage.birthYearField.sendKeys(birthYear);
        settingsPage.currentCityField.clear();
        settingsPage.currentCityField.sendKeys(currentCity);
        settingsPage.cityListElement.click();
        settingsPage.nativeCityField.clear();
        settingsPage.nativeCityField.sendKeys(nativeCity);
        settingsPage.cityListElement.click();
        if (saveChanges) {
            settingsPage.saveButton.click();
        } else {
            settingsPage.cancelButton.click();
        }
        assert new SettingsPage(driver).personalDataDescriptionField.getText().equalsIgnoreCase(personalData);
    }

    @Test(description = "Очистка поля 'Родной город'")
    public void clearNativeCityPositiveTest() {
        settingsPage.nativeCityField.clear();
        settingsPage.saveButton.click();
        settingsPage.personalDataField.exists();
    }

    @AfterMethod
    public static void quit(){
        driver.quit();
    }
}
