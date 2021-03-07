package tests;

import helpers.BaseTest;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.Keys;

import java.time.LocalDate;
import java.util.Random;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.*;
import static java.lang.String.format;
import static java.time.format.DateTimeFormatter.ofPattern;
import static org.openqa.selenium.Keys.ENTER;

public class StudentRegFormBirthDateTest extends BaseTest {

    @Test
    void testStudentBirthDate() {
        StringBuilder mobileNum = new StringBuilder();
        int rand = new Random().nextInt(999999);
        String fName = "Fname" + rand;
        String lName = "Lname" + rand;
        String email = format("e%s@mail.com", rand);
        int gender = new Random().nextInt(3) + 1;

        for (int i = 1; i <= 10; i++)
            mobileNum.append(new Random().nextInt(10));

        LocalDate birthDate = LocalDate.now().minusYears(22).minusDays(new Random().nextInt(88));
        String dateIn = birthDate.format(ofPattern("dd MMM yyyy"));
        String dateOut = birthDate.format(ofPattern("dd MMMM,yyyy"));

        open("https://demoqa.com/automation-practice-form");
        $("#app").shouldHave(text("Student Registration Form"));

        $("#firstName").setValue(fName);
        $("#lastName").setValue(lName);
        $("#userEmail").setValue(email);

        $(format("#gender-radio-%s", gender)).parent().click();
        $("#userNumber").setValue(mobileNum.toString());
        $("#dateOfBirthInput").sendKeys(Keys.chord(Keys.CONTROL, "a"));
        $("#dateOfBirthInput").sendKeys(dateIn + ENTER);
        $("#submit").click();

        //Assert section
        $("#example-modal-sizes-title-lg").shouldHave(text("Thanks for submitting the form"));
        $x(".//table//tr[5]/td[2]").shouldHave(text(dateOut));
    }
}