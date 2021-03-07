package tests;

import com.codeborne.selenide.Configuration;
import helper.BaseTest;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;

import java.io.File;
import java.time.LocalDate;
import java.util.Random;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.*;
import static java.lang.String.format;
import static java.lang.System.getProperty;
import static java.time.format.DateTimeFormatter.ofPattern;
import static org.openqa.selenium.By.*;
import static org.openqa.selenium.Keys.ENTER;

public class StudentRegistrationTest extends BaseTest {


    @Test
    void testStudentRegistrationForm()  {

        open("https://demoqa.com/automation-practice-form");

        $x("//*[@id='app']//h5").shouldHave(text("Student Registration Form"));

        int rand = new Random().nextInt(999999);

        String fName = "Fname" + rand;
        String lName = "Lname" + rand;
        String email = format("e%s@mail.com", rand);

        $(id("firstName")).setValue(fName)
                .pressEnter();
        $(id("lastName")).setValue(lName)
                .pressEnter();
        $(id("userEmail")).setValue(email)
                .pressEnter();

        int gender = 1 + new Random().nextInt(3);
        $(id(format("gender-radio-%s", gender))).parent().click();
        String genderName = $(id(format("gender-radio-%s", gender))).getValue();
        System.out.println("gender " + genderName);

        String mobileNum = "";
        for (int i = 1; i <= 10; i++) mobileNum = mobileNum + new Random().nextInt(10);
        $(id("userNumber")).setValue(mobileNum);

        LocalDate birthDate =  LocalDate.now().minusYears(22).minusDays(gender);
        String date =birthDate.format(ofPattern("dd MMM yyyy"));
        $(id("dateOfBirthInput")).sendKeys(Keys.chord(Keys.CONTROL, "a"));
        $(id("dateOfBirthInput")).sendKeys(date + ENTER);

        $(id("subjectsInput")).setValue("e")
                .pressEnter();

        int hobby = 1 + new Random().nextInt(3);
        $(id(format("hobbies-checkbox-%s", hobby))).parent()
                .click();

        File file = new File(getProperty("icon.path"));
        $(id("uploadPicture")).uploadFile(file);

        $(id("currentAddress")).setValue("country1\n city2\n street3 \n address4")
                .pressEnter();

        $(id("react-select-3-input")).setValue("utt")
                .pressEnter();
        $(id("react-select-4-input")).setValue("mer")
                .pressEnter();
        $(id("submit")).click();

        //Assert section
        $(id("example-modal-sizes-title-lg")).shouldHave(text("Thanks for submitting the form"));

        $x(".//table//tr[1]/td[2]").shouldHave(text(fName + " " + lName));
        $x(".//table//tr[2]/td[2]").shouldHave(text(email));
        $x(".//table//tr[3]/td[2]").shouldHave(text(genderName));
        $x(".//table//tr[4]/td[2]").shouldHave(text(mobileNum));

        String date2 = birthDate.format(ofPattern("dd MMMM,yyyy"));
        $x(".//table//tr[5]/td[2]").shouldHave(text(date2));
    }

}
