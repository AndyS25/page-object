package ru.netology.web.page;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import ru.netology.web.data.DataHelper;

import java.time.Duration;

import static com.codeborne.selenide.Condition.exactText;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;

public class TransferPage {
    private final SelenideElement transferHeading = $(byText("Пополнение карты"));
    private final SelenideElement inputForAmountToTransfer = $("[data-test-id=amount] input");
    private final SelenideElement cardFromToTransfer = $("[data-test-id=from] input");
    private final SelenideElement transferButton = $("[data-test-id=action-transfer]");
    private final SelenideElement errorMessage = $("[data-test-id=error-notification]");

    public TransferPage() {
        transferHeading.shouldBe(Condition.visible);
    }

    public DashboardPage makeValidTransfer(String amountToTransfer, DataHelper.CardInfo cardInfo) {
        makeAnyTransfer(amountToTransfer, cardInfo);

        return new DashboardPage();
    }

    public void makeAnyTransfer(String amountToTransfer, DataHelper.CardInfo cardInfo) {
        inputForAmountToTransfer.setValue(amountToTransfer);
        cardFromToTransfer.setValue(cardInfo.getCardNumber());
        transferButton.click();
    }

    public void transferFail(String expectedText) {
        errorMessage.shouldHave(exactText(expectedText)).shouldBe((Condition.visible), Duration.ofSeconds(15));
    }

}
