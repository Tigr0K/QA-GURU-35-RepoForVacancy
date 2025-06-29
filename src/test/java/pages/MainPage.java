package pages;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;

import java.util.List;

import static com.codeborne.selenide.CollectionCondition.sizeGreaterThan;
import static com.codeborne.selenide.CollectionCondition.texts;
import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.*;


public class MainPage {
    private final SelenideElement searchInput = $("#searchInput");
    private final ElementsCollection cardElements = $$(".product-card__wrapper");
    private final SelenideElement emptyBasketButton = $(".basket-empty__btn");
    private final ElementsCollection itemNavbar = $$(".navbar-pc__item");
    private final ElementsCollection listOfTitles = $$(".service-menu__list li");
    private final SelenideElement searchByImageFormAbNew = $("#searchByImageFormAbNew");
    private final SelenideElement uploadImageForSearchByImagePopUpContainer = $("#uploadImageForSearchByImagePopUpContainer");
    private final SelenideElement bannerCarousel = $(".main-page__banner");

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

    @Step("Поиск иконки ${chapter}")
    public MainPage serachElement(String chapter) {
        itemNavbar.findBy(text(chapter)).click();
        return this;
    }
    @Step("Появление кнопки при нажатии на пустую страницу")
    public MainPage buttonEmptyBasket(String buttonText) {
        emptyBasketButton.shouldHave(text(buttonText));
        return this;
    }
    @Step("Поиск заголовков при нажатии на кнопку")
    public MainPage titlesExists(List<String> expectedLinks) {
        listOfTitles.filter(visible).shouldHave(texts(expectedLinks));
        return this;
    }
    @Step("Клик на поиск товара по фото")
    public MainPage clickPhotoSearch() {
        searchByImageFormAbNew.shouldBe(visible).click();
        return this;
    }
    @Step("Появление всплывающего меню при клике на поиск по фото")
    public MainPage popUpPhotoSearch() {
        uploadImageForSearchByImagePopUpContainer.shouldBe(visible);
        return this;
    }
    @Step("Наличие карусели баннеров")
    public MainPage  bannerCarouselExist() {
         bannerCarousel.shouldBe(visible);
        return this;
    }

}


