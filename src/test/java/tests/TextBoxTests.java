package tests;

import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

public class TextBoxTests {

    @BeforeAll
    static void setup() {
        Configuration.startMaximized = true;
    }

    @Disabled
    @Test
    void dataAppearsInOutputBlockTest() {
        String name = "Alex";

        open("https://demoqa.com/text-box");
        $(".main-header").shouldHave(text("Text Box"));

        $("#userName").setValue(name);
        $("#userEmail").setValue("aa@aa.aa");
        $("#currentAddress").setValue("currentAddress here");
        $("#permanentAddress").setValue("permanentAddress here");
        $("#submit").click();

        $("#output").shouldHave(text(name), text("aa@aa.aa"));
    }

    @Disabled
    @Test
    void testStructure() {
        // arrange  | given  |

        // act      | when   |

        // assert   | then   |
    }


}
