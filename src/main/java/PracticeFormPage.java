import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;

import java.io.File;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

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

    public SelenideElement pickMonth = $("select[class='react-datepicker__month-select']");
    public SelenideElement pickYear = $("select[class='react-datepicker__year-select']");





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
        $(byText(day)).click();
    }


    public Map<String, String> getSubmissionResults() {
        ElementsCollection rows = $$("table tr");
        Map<String, String> resultMap = new HashMap<>();

        for (SelenideElement row : rows) {
            ElementsCollection labelCells = row.$$("td:nth-of-type(1)");
            ElementsCollection valueCells = row.$$("td:nth-of-type(2)");

            String labelText = labelCells.texts().stream().collect(Collectors.joining(", "));
            String valueText = valueCells.texts().stream().collect(Collectors.joining(", "));

            if (!valueText.isEmpty()) {
                resultMap.put(labelText, valueText);
            }
        }

        return resultMap;
    }

}