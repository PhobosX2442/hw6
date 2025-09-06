import com.codeborne.selenide.Condition;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.*;

public class PracticeFormPage {

    private SelenideElement firstNameInput = $("#firstName");
    private SelenideElement lastNameInput = $("#lastName");
    private SelenideElement emailInput = $("input[placeholder='name@example.com']");
    private SelenideElement genderInput = $x("//div[contains(@class, 'custom-control-inline')][.//*[@id='gender-radio-1']]");
    private SelenideElement mobileInput = $("input[placeholder='Mobile Number']");
    private SelenideElement submitButton = $(".btn-primary");
    private SelenideElement inputDateBirth = $("#dateOfBirthInput");
    private SelenideElement subjectsArea = $("#subjectsInput");
    private SelenideElement stateArea = $("#state");
    private SelenideElement cityArea = $("#city");
    private SelenideElement addressArea = $("#currentAddress");
    private SelenideElement inputUploadPicture = $("#uploadPicture");

    private SelenideElement pickMonth = $("select[class='react-datepicker__month-select']");
    private SelenideElement pickYear = $("select[class='react-datepicker__year-select']");





    SelenideElement hasInvalidColor() {
        return $("input.form-control:invalid").should(exist);
    }

    public void openPage() {
        open("/automation-practice-form");
        emailInput.shouldBe(visible);
    }

    public void setFirstName(String value) {
        firstNameInput.setValue(value);
    }

    public void setLastName(String value) {
        lastNameInput.setValue(value);
    }

    public void setGender() {
        genderInput.click();
    }

    public void setEmailInput(String value) {
        emailInput.setValue(value);
    }

    public void setMobileInput(String value) {
        mobileInput.setValue(value);
    }

    public void submitForm() {
        submitButton.scrollTo();
        submitButton.click();
    }

    public void getSubject(String value) {
        subjectsArea.setValue(value);
        subjectsArea.pressEnter();
    }
    public void getHobbies(String value) {
        $(byText(value)).click();
    }

    public void getStateCity(String state, String city) {
        stateArea.click();
        $(byText(state)).click();
        cityArea.click();
        $(byText(city)).click();
    }

    public void getAddress(String value) {
        addressArea.setValue(value);
    }

    public void uploadPicture(String value) {
        inputUploadPicture.uploadFile(new File(value));
    }

    public void setDateBirth(String month, String year, String day) {
        inputDateBirth.click();
        pickMonth.selectOptionContainingText(month);
        pickYear.selectOptionContainingText(year);
        $$("div.react-datepicker__day:not(.react-datepicker__day--outside-month)")
                .findBy(text(day))
                .click();

    }



    public Map<Form, String> getSubmissionResults() {
        ElementsCollection rows = $$("table tbody tr");
        Map<Form, String> resultMap = new HashMap<>();

        for (SelenideElement row : rows) {
            String labelText = row.$("td", 0).getText();
            String valueText = row.$("td", 1).getText();

            if (!valueText.isEmpty()) {
                resultMap.put(Form.fromLabel(labelText), valueText);
            }
        }

        return resultMap;
    }

}