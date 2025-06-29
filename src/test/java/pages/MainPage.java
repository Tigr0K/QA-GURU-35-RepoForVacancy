package pages;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;

import static com.codeborne.selenide.CollectionCondition.sizeGreaterThan;
import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.*;


public class MainPage {
    private final SelenideElement searchInput = $("#searchInput");
    private final ElementsCollection cardElements = $$(".product-card__wrapper");

    @Step("Открываем главную страницу")
    public MainPage openPage() {
        open("/");
        return this;
    }

    @Step("Отправляем поисковый запрос с значением ${text}")
    public MainPage search(String text) {
        searchInput.setValue(text).shouldBe(visible).pressEnter();
        return this;
    }

    @Step("Проверяем, что вернулось не меньше 10 элементов")
    public MainPage checkCardMore(int size) {
        cardElements.shouldBe(sizeGreaterThan(size));
        return this;
    }

}
