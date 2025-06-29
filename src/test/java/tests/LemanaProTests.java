package tests;

import io.qameta.allure.Owner;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import pages.MainPage;

import static com.codeborne.selenide.CollectionCondition.sizeGreaterThan;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;


@Tag("AllTests")
@Owner("Yuferev Evgeny")
public class LemanaProTests extends TestBase {

    MainPage mainPage = new MainPage();
    @ValueSource(strings = {"Ламинат", "Кирпич", "Скамейка"})
    @ParameterizedTest(name = "При поиске запроса {0} список не должен быть пустым")
    @Tag("SMOKE")
    void searchTest(String searchText) {
        mainPage.openPage()
                .search(searchText)
                .checkCardMore(10);
    }

    @ValueSource(strings = {
            "Перфоратор",
            "Светильник",
            "Видеокарта"
    })
    @ParameterizedTest(name = "Поиск на запрос {0} должен выдавать 10 результатов")
    @Tag("SMOKE")
    void searchShouldReturn10ResultsTest(String searchQuery){
        $("#searchInput").setValue(searchQuery).shouldBe(visible).pressEnter();
        $$(".product-card__wrapper").shouldBe(sizeGreaterThan(10));
    }
}
