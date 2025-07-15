import com.codeborne.selenide.SelenideElement;

import java.util.HashMap;
import java.util.Map;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.*;

public class PracticeFormPage {

    private SelenideElement firstNameInput = $("#firstName");
    private SelenideElement lastNameInput = $("#lastName");
    private SelenideElement emailInput = $("input[placeholder='name@example.com']");
    private SelenideElement genderInput = $x("//div[contains(@class, 'custom-control-inline')][.//*[@id='gender-radio-1']]");
    private SelenideElement mobileInput = $("input[placeholder='Mobile Number']");
    private SelenideElement submitButton = $(".btn-primary");

    public static String NAME = "Student Name";
    public static String EMAIL = "Student Email";
    public static String GENDER = "Gender";
    public static String MOBILE = "Mobile";

    public SelenideElement tdName = $x("/html/body/div[4]/div/div/div[2]/div/table/tbody/tr[1]/td[2]");
    public SelenideElement tdEmail = $x("/html/body/div[4]/div/div/div[2]/div/table/tbody/tr[2]/td[2]");
    public SelenideElement tdGender = $x("/html/body/div[4]/div/div/div[2]/div/table/tbody/tr[3]/td[2]");
    public SelenideElement tdMobile = $x("/html/body/div[4]/div/div/div[2]/div/table/tbody/tr[4]/td[2]");



    SelenideElement lastNameInputInvalid() {
        return $("input.form-control:invalid").should(exist);
    }

    void openPage() {
        open("/automation-practice-form");
        emailInput.shouldBe(visible);
    }

    void setFirstName(String value) {
        firstNameInput.setValue(value);
    }

    void setLastName(String value) {
        lastNameInput.setValue(value);
    }

    void setGender() {
        genderInput.click();
    }

    void setEmailInput(String value) {
        emailInput.setValue(value);
    }

    void setMobileInput(String value) {
        mobileInput.setValue(value);
    }

    void submitForm() {
        //submitButton.scrollTo();
        submitButton.click();
    }


    String tdNameText() {
        return tdName.getText();
    }

    String tdEmailText() {
        return tdEmail.getText();
    }

    String tdGenderText() {
        return tdGender.getText();
    }

    String tdMobileText() {
        return tdMobile.getText();
    }

    Map<String, String> getSubmissionResults(){
        return new HashMap<>(){{
            put(NAME, tdNameText());
            put(EMAIL, tdEmailText());
            put(GENDER, tdGenderText());
            put(MOBILE, tdMobileText());
        }};

    }



}