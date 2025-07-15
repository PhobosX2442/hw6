import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;
import com.codeborne.selenide.conditions.Or;
import org.junit.jupiter.api.*;

import java.util.HashMap;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class PracticeFormTest extends PracticeFormPage {

     PracticeFormPage practiceFormPage = new PracticeFormPage();
     PracticeSwitchPage practiceSwitchPage = new PracticeSwitchPage();

    @BeforeAll
    public static void setUp() {
        Configuration.browser = "chrome";
        Configuration.baseUrl = "https://demoqa.com";
        Configuration.timeout = 5000;
        Configuration.pageLoadTimeout = 60000;
        Configuration.browserSize = "1920x1280";
    }

    public static void pause() throws InterruptedException {
        Thread.sleep(200);
    }

    @Test
    @Order(0)
    @DisplayName("Заполнение формы (некорректное)")
    public void InputFormFailed() throws InterruptedException {
        practiceFormPage.openPage();
        practiceFormPage.setFirstName("Петушкослав");
        practiceFormPage.submitForm();
        practiceFormPage.lastNameInputInvalid();
        pause();
    }

    @Test
    @Order(1)
    @DisplayName("Заполнение формы")
    public void inputForm()  {
        practiceFormPage.setFirstName("Светослав");
        practiceFormPage.setLastName("Петушкевич");
        practiceFormPage.setEmailInput("testemail@mail.ru");
        practiceFormPage.setGender();
        practiceFormPage.setMobileInput("1234567890");
        practiceFormPage.submitForm();
    }

    @Test
    @Order(2)
    @DisplayName("Проверка ответа")
    public void checkForm() {
        Map<String, String> expectedResults = new HashMap<>();
        expectedResults.put(NAME, "Светослав Петушкевич");
        expectedResults.put(EMAIL, "testemail@mail.ru");
        expectedResults.put(GENDER, "Male");
        expectedResults.put(MOBILE, "1234567890");

        Map<String, String> actualResults = practiceFormPage.getSubmissionResults();

        Assertions.assertEquals(expectedResults, actualResults);
    }


    @Test
    @Order(3)
    @DisplayName("Переход на другую вкладку")
    public void switchPage() throws InterruptedException {
        pause();
        practiceSwitchPage.closeModal();
        practiceSwitchPage.clickFrame();
        practiceSwitchPage.clickPage();
        practiceSwitchPage.clickBtn();
        pause();
        Selenide.confirm();
    }

    @AfterAll
    public static void finish() throws InterruptedException{
        pause();
    }

}
