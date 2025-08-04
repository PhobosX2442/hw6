import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.Selenide;
import org.junit.jupiter.api.*;

import java.util.HashMap;
import java.util.Map;

import static com.codeborne.selenide.Selenide.closeWebDriver;
import static com.codeborne.selenide.Selenide.closeWindow;

public class PracticeFormTest {

     PracticeFormPage practiceFormPage = new PracticeFormPage();
     PracticeSwitchPage practiceSwitchPage = new PracticeSwitchPage();


     String name = "Светослав";
     String lastname = "Петушкевич";
     String email = "testemail@mail.ru";
     String mobile = "1234567890";
     String subject = "Maths";
     String hobbies = "Reading";
     String state = "Haryana";
     String city = "Karnal";
     String address = "Random Address";
     String picture = "src/main/resources/testimg.jpg";
     String monthbirth = "July";
     String yearbirth = "1996";
     String daybirth = "30";

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
        practiceFormPage.setFirstName(name);
        practiceFormPage.setLastName(lastname);
        practiceFormPage.setEmailInput(email);
        practiceFormPage.setGender();
        practiceFormPage.setMobileInput(mobile);
        practiceFormPage.getSubject(subject);
        practiceFormPage.getHobbies(hobbies);
        practiceFormPage.getStateCity(state, city);
        practiceFormPage.getAddress(address);
        practiceFormPage.uploadPicture(picture);
        practiceFormPage.setDateBirth(monthbirth, yearbirth, daybirth);
        //отправка данных
        practiceFormPage.submitForm();
        //добавление 0 на случай, если число окажется однодигитовым
        if (daybirth.length() == 1) {
            daybirth = "0" + daybirth;
        }


        Map<Form, String> expectedResults = new HashMap<>();
        expectedResults.put(Form.NAME, name + " " + lastname);
        expectedResults.put(Form.EMAIL, email);
        expectedResults.put(Form.GENDER, "Male");
        expectedResults.put(Form.MOBILE, mobile);
        expectedResults.put(Form.SUBJECTS, subject);
        expectedResults.put(Form.HOBBIES, hobbies);
        expectedResults.put(Form.BIRTH, daybirth + " " + monthbirth + "," + yearbirth);
        expectedResults.put(Form.STATE, state + " " + city);
        expectedResults.put(Form.ADDRESS, address);
        expectedResults.put(Form.PICTURE, "TestIMG.jpg");

        Map<Form, String> actualResults = practiceFormPage.getSubmissionResults();
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
