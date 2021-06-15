package org.example.config.pages;


import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import ru.yandex.qatools.htmlelements.annotations.Name;
import ru.yandex.qatools.htmlelements.element.HtmlElement;
import ru.yandex.qatools.htmlelements.loader.HtmlElementLoader;

public class SettingsPage {

    @Name("Поле 'Личные данные'")
    @FindBy(xpath = "//div[contains(text(),'Личные данные')]")
    public HtmlElement personalDataField;

    @Name("Поле с описание личным данных")
    @FindBy(xpath = "//div[contains(@class, 'user-settings_i_tx textWrap')]")
    public HtmlElement personalDataDescriptionField;

    @Name("Поле 'Имя'")
    @FindBy(id = "field_name")
    public HtmlElement nameField;

    @Name("Текст ошибки 'Пожалуйста, используйте только буквы'")
    @FindBy(xpath = "//span[contains(text(),'Пожалуйста, используйте только буквы.')]")
    public HtmlElement nameErrorText;

    @Name("Текст ошибки 'Пожалуйста, используйте только буквы'")
    @FindBy(xpath = "//span[contains(text(),'Пожалуйста, выберите место проживания из списка')]")
    public HtmlElement cityErrorText;

    @Name("Поле 'Фамилия'")
    @FindBy(id = "field_surname")
    public HtmlElement surnameField;

    @Name("Поле 'День рождения'")
    @FindBy(id = "field_bday")
    public HtmlElement birthDayField;

    @Name("Поле 'Месяц рождения'")
    @FindBy(id = "field_bmonth")
    public HtmlElement birthMonthField;

    @Name("Поле 'Год рождения'")
    @FindBy(id = "field_byear")
    public HtmlElement birthYearField;

    @Name("Радио кнопка 'Мужской пол'")
    @FindBy(id = "field_gender_1")
    public HtmlElement maleGenderButton;

    @Name("Радио кнопка 'Женский пол'")
    @FindBy(id = "field_gender_2")
    public HtmlElement femaleGenderButton;

    @Name("Поле 'Город проживания'")
    @FindBy(id = "field_citySugg_SearchInput")
    public HtmlElement currentCityField;

    @Name("Поле 'Родной город'")
    @FindBy(id = "field_cityBSugg_SearchInput")
    public HtmlElement nativeCityField;

    @Name("Первый элемент в саджестах с городом")
    @FindBy(xpath = "//li[@class = 'suggest_li']")
    public HtmlElement cityListElement;

    @Name("Кнопка 'Сохранить'")
    @FindBy(id = "hook_FormButton_button_savePopLayerEditUserProfileNew")
    public HtmlElement saveButton;

    @Name("Кнопка 'Отменить'")
    @FindBy(id = "hook_FormButton_button_cancelPopLayerEditUserProfileNew")
    public HtmlElement cancelButton;

    public SettingsPage(WebDriver driver) {
        HtmlElementLoader.populate(this, driver); }
}
