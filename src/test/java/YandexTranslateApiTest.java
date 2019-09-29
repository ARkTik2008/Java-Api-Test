import io.qameta.allure.Description;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;

public class YandexTranslateApiTest extends TestBase {

    @Test
    @Description("Проверка корректности перевода Автоматизированное тестирование")
    public void shouldGetTranslate() {
        REQUEST.param("lang", "ru-en").
                param("text", "Автоматизированное тестирование").
                post("/api/v1.5/tr.json/translate").
                then().
                body("text", Matchers.hasItem("Automated testing"));
    }

    @Test
    @Description("Проверка получения кода ошибки 501")
    public void shouldHaveStatus501ForIncorrectLang() {
        REQUEST.
                param("lang", "ru-kkk").
                param("text", "Автоматизация").
                post("/api/v1.5/tr.json/translate").
                then().
                body("code", Matchers.equalTo(501));
    }
}