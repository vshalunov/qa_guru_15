package com.github.zlwqa.tests;

import com.github.zlwqa.config.ApiConfig;
import org.aeonbits.owner.ConfigFactory;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

import static com.codeborne.selenide.CollectionCondition.texts;
import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.*;


@Tag("MvideoTests")
@DisplayName("Тесты для Мвидео")
public class MvideoTests extends TestBase {
    ApiConfig apiConfig = ConfigFactory.create(ApiConfig.class, System.getProperties());

    @ValueSource(strings = {"беспроводной контроллер playstation 5 dualsense rainbo ice banana",
            "воздухоувлажнитель vitek vt-2338"})
    @Tag("Critical")
    @Tag("High")
    @Tag("Web")
    @DisplayName("Результаты поиска")
    @ParameterizedTest(name = "Отображение товара {0} в результатах поиска")
    void displayProductAfterSearchTests(String searchQuery) {
        open(apiConfig.baseURL());
        $(".input__field").setValue(searchQuery).pressEnter();
        $$("a.product-title__text").shouldHave(texts(searchQuery));
    }

    @CsvSource(value = {
            "беспроводной контроллер playstation 5 dualsense rainbo ice banana | 4007 5615",
            "воздухоувлажнитель vitek vt-2338 | 2006 1485"
    }, delimiter = '|')
    @Tag("Minor")
    @Tag("Medium")
    @Tag("Web")
    @DisplayName("Карточка товара")
    @ParameterizedTest(name = "Отображение кода {1} у товара {0}")
    void displayProductCodeInProductCardTests(String searchQuery, String productCode) {
        open(apiConfig.baseURL());
        $(".input__field").setValue(searchQuery).pressEnter();
        $$("a.product-title__text").find(text(searchQuery)).click();
        $(".product-code-container").shouldHave(text(productCode));
    }
}    
