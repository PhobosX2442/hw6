import com.codeborne.selenide.SelenideElement;

import java.time.Duration;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.*;
import static java.time.Duration.ofSeconds;

public class PracticeSwitchPage {
    

    private SelenideElement alertsFrame = $(byText("Alerts, Frame & Windows"));
    private SelenideElement alertsPage = $x("/html/body/div[2]/div/div/div/div[1]/div/div/div[3]/div/ul/li[2]");
    private SelenideElement alertBtn = $("#alertButton");
    private SelenideElement modal = $("#closeLargeModal");


    public void closeModal()  {
        modal.click();
    }

    public void clickFrame() {
        alertsFrame.click();
    }

    public void clickPage() {
        alertsPage.shouldBe(visible, ofSeconds(5));
        alertsPage.click();
    }

    public void clickBtn() {
        alertBtn.click();
    }
}
