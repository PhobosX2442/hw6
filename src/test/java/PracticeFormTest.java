import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.Selenide;
import org.junit.jupiter.api.*;

import java.util.HashMap;
import java.util.Map;

import static com.codeborne.selenide.Selenide.closeWebDriver;
import static com.codeborne.selenide.Selenide.closeWindow;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class PracticeFormTest {

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

    @BeforeEach
    public void openBrowser() throws InterruptedException{
        practiceFormPage.openPage();
    }

    @Test
    @DisplayName("Заполнение формы (некорректное)")
    public void InputFormFailed() throws InterruptedException {
        practiceFormPage.setFirstName("Петушкослав");
        practiceFormPage.submitForm();
        practiceFormPage.hasInvalidColor();
    }

    @Test
    @DisplayName("Заполнение формы")
    public void inputForm() throws InterruptedException {
        //заполнение формы
        practiceFormPage.setFirstName("Светослав");
        practiceFormPage.setLastName("Петушкевич");
        practiceFormPage.setEmailInput("testemail@mail.ru");
        practiceFormPage.setGender();
        practiceFormPage.setMobileInput("1234567890");
        practiceFormPage.getSubject("Maths");
        practiceFormPage.getHobbies("Reading");
        practiceFormPage.getStateCity("Haryana", "Karnal");
        practiceFormPage.getAddress("Random Address");
        practiceFormPage.uploadPicture("src/main/resources/testimg.jpg");
        practiceFormPage.setDateBirth("July", "1996", "6");
        //отправка данных
        practiceFormPage.submitForm();


        Map<String, String> expectedResults = new HashMap<>();
        expectedResults.put(String.valueOf(Form.valueOf("NAME").getLabel()), "Светослав Петушкевич");
        expectedResults.put(String.valueOf(Form.valueOf("EMAIL").getLabel()), "testemail@mail.ru");
        expectedResults.put(String.valueOf(Form.valueOf("GENDER").getLabel()), "Male");
        expectedResults.put(String.valueOf(Form.valueOf("MOBILE").getLabel()), "1234567890");
        expectedResults.put(String.valueOf(Form.valueOf("SUBJECTS").getLabel()), "Maths");
        expectedResults.put(String.valueOf(Form.valueOf("HOBBIES").getLabel()), "Reading");
        expectedResults.put(String.valueOf(Form.valueOf("BIRTH").getLabel()), "06 July,1996");
        expectedResults.put(String.valueOf(Form.valueOf("STATE").getLabel()), "Haryana Karnal");
        expectedResults.put(String.valueOf(Form.valueOf("ADDRESS").getLabel()), "Random Address");
        expectedResults.put(String.valueOf(Form.valueOf("PICTURE").getLabel()), "TestIMG.jpg");

        Map<String, String> actualResults = practiceFormPage.getSubmissionResults();
        Assertions.assertEquals(expectedResults, actualResults);
    }



    @Test
    @DisplayName("Переход на другую вкладку")
    public void switchPage() throws InterruptedException {
        practiceSwitchPage.clickFrame();
        practiceSwitchPage.clickPage();
        practiceSwitchPage.clickBtn();
        Selenide.confirm();
    }

    @AfterEach
    public void closeBrowser() throws InterruptedException{
        closeWindow();
    }
    @AfterAll
    public static void finish() throws InterruptedException{
        closeWebDriver();
    }

}
