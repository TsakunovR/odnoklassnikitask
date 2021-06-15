package org.example.config.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.FindBy;
import ru.yandex.qatools.htmlelements.annotations.Name;
import ru.yandex.qatools.htmlelements.element.HtmlElement;
import ru.yandex.qatools.htmlelements.loader.HtmlElementLoader;

public class MainPage {

    @Name("Поле 'Логин'")
    @FindBy(id = "field_email")
    public HtmlElement loginField;

    @Name("Поле 'Пароль'")
    @FindBy(id = "field_password")
    public HtmlElement passwordField;

    @Name("Кнопка 'Войти'")
    @FindBy(xpath = "//input[@value='Войти в Одноклассники']")
    public HtmlElement loginButton;

    public MainPage(WebDriver driver) {
        HtmlElementLoader.populate(this, driver); }
    }