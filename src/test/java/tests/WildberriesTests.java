package tests;

import io.qameta.allure.Owner;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.ValueSource;
import pages.MainPage;

import java.util.List;
import java.util.stream.Stream;



@Tag("AllTests")
@Owner("Yuferev Evgeny")
public class WildberriesTests extends TestBase {

    MainPage mainPage = new MainPage();

    @ValueSource(strings = {"Перфоратор", "Светильник", "Видеокарта"})
    @ParameterizedTest(name = "При поиске запроса {0} список не должен быть пустым")
    @Tag("SMOKE")
    void searchTest(String searchText) {
        mainPage.openPage()
                .search(searchText)
                .checkCardMore(10);
    }

    @CsvSource(value = {"Корзина, Перейти на главную"})
    @Tag("SMOKE")
    @ParameterizedTest(name = "Появление кнопки при нажатии на корзину")
    void buttunForClickOnBasketTest(String chapter, String buttonText) {
        mainPage.openPage()
                .serachElement(chapter)
                .buttonEmptyBasket(buttonText);
    }

    static Stream<Arguments> titlsOnClickBattonAddress() {
        return Stream.of(
                Arguments.of(" Адреса ", List.of(
                        "Как сделать заказ",
                        "Способы оплаты",
                        "Доставка",
                        "Возврат товара",
                        "Возврат денежных средств",
                        "Вопросы и ответы"))
        );
    }

    @MethodSource
    @ParameterizedTest(name = "Заголовки при нажитии на кнопку \"Адреса")
    void titlsOnClickBattonAddress(String chapter, List<String> expectedLinks) {
        mainPage.openPage()
                .serachElement(chapter)
                .titlesExists(expectedLinks);

    }

    @Tag("SMOKE")
    @Test
    @DisplayName("Появление всплывающего меню при клике на поиск по фото")
    void popUpPhotoSearch(){
        mainPage.openPage()
                .clickPhotoSearch()
                .popUpPhotoSearch();
    }

    @Tag("SMOKE")
    @Test
    @DisplayName("Наличие карусели баннеров")
    void bannerCarouselExist(){
        mainPage.openPage()
                .bannerCarouselExist();
    }
}

