package ru.yandex.praktikum.pageobject;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.Keys;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;
import static java.time.Duration.ofSeconds;

public class MetroHomePage {

    // локатор кнопки выпадающего списка городов по имени класса
    @FindBy(how = How.CLASS_NAME, using = "select_metro__button")
    private SelenideElement selectCityButton;

    // локатор поля ввода «Откуда» по XPATH, поиск по плейсхолдеру
    @FindBy(how = How.XPATH, using = ".//input[@placeholder='Откуда']")
    private SelenideElement fieldFrom;

    // локатор поля ввода «Куда» по XPATH, поиск по плейсхолдеру
    @FindBy(how = How.XPATH, using = ".//input[@placeholder='Куда']")
    private SelenideElement fieldTo;

    // локатор коллекций станций «Откуда» и «Куда» маршрута по имени класса
    @FindBy(how = How.CLASS_NAME, using = "route-details-block__terminal-station")
    private ElementsCollection routeStationFromTo;

    // метод ожидания загрузки страницы: проверили видимость станции «Театральная»
    public void waitForLoadHomePage(){
        // нашли веб-элемент по тексту, добавили увеличенное ожидание 8 секунд
        $(byText("Театральная")).shouldBe(visible,ofSeconds(8));
    }

    // метод выбора города по названию
    public void chooseCity(String cityName){
        // клик по выпадающему списку городов
        selectCityButton.click();
        // выбор города
        $(byText(cityName)).click();
    }

    // метод ввода названия станции в поле «Откуда»
    public void setStationFrom(String stationFrom){
        // ввели название станции в поле ввода, а затем с помощью клавиш «Вниз» и Enter выбери его в выпадающем списке саджеста
        fieldFrom.setValue(stationFrom).sendKeys(Keys.DOWN, Keys.ENTER);
    }

    // метод ввода названия станции в поле «Куда»
    public void setStationTo(String stationTo){
        // вводим название станции в поле ввода, а затем с помощью клавиш «Вниз» и Enter выбираем его в выпадающем списке саджеста
        fieldTo.setValue(stationTo).sendKeys(Keys.DOWN, Keys.ENTER);
    }

    // метод ожидания построения маршрута: проверяем видимость кнопки «Получить ссылку на маршрут»
    public void waitForLoadRoute(){
        // ищем веб-элемент по тексту
        $(byText("Получить ссылку на маршрут")).shouldBe(visible);
    }

    // метод построения маршрута
    public void buildRoute(String stationFrom, String stationTo){
        // указываем станцию «Откуда»
        setStationFrom(stationFrom);
        // указываем станцию «Куда»
        setStationTo(stationTo);
        // ждём построения маршрута
        waitForLoadRoute();
    }

    // метод получения станции «Откуда» для построенного маршрута
    public SelenideElement getRouteStationFrom(){
        // вернули первый элемент коллекции
        return this.routeStationFromTo.get(0);
    }

    // метод получения станции «Куда» построенного маршрута
    public SelenideElement getRouteStationTo(){
        // вернули второй элемент коллекции
        return this.routeStationFromTo.get(1);
    }

}