package tests;

import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

public class StudentRegistrationFormTests {

    @BeforeAll
    static void setup() {
        Configuration.startMaximized = true;
    }

    @Test
    void studentRegistrationTest() {
        String firstName = "Test FirstName",
                lastName = "Test LastName",
                email = "Test@mail.ru",
                gender = "Male",
                number = "0123456789",
                subject = "Maths",
                hobbie1 = "Sports",
                hobbie2 = "Reading",
                hobbie3 = "Music",
                image = "image.png",
                address = "Адрес",
                state = "Haryana",
                city = "Karnal";

        open("https://demoqa.com/automation-practice-form");
        $(".practice-form-wrapper").shouldHave(text("Student Registration Form"));

        $("#firstName").setValue(firstName);
        $("#lastName").setValue(lastName);
        $("#userEmail").setValue(email);
        $(byText(gender)).click();
        $("#userNumber").setValue(number);

        //Выбираем Date of Birth
        $("#dateOfBirthInput").click();
        $(".react-datepicker__month-select").selectOptionContainingText("January");
        $(".react-datepicker__year-select").selectOptionContainingText("1994");
        $(byText("10")).click();

        //Выбираем Subjects
        $("#subjectsContainer").click();
        $("#subjectsInput").setValue(subject);
        $("#react-select-2-option-0").click();

        //Выбираем Hobbies
        $(byText(hobbie1)).click();
        $(byText(hobbie2)).click();
        $(byText(hobbie3)).click();

        //Загружаем файл, пишем адрес
        $("#uploadPicture").uploadFromClasspath(image);
        $("#currentAddress").setValue(address);

        //Выбираем штат и город, жмем сабмит
        $(byText("Select State")).click();
        $(byText(state)).click();
        $(byText("Select City")).click();
        $(byText(city)).click();
        $("#submit").click();

        //Делаем проверки
        $(".modal-content").shouldHave(text(firstName + " " + lastName),
                text(email),
                text(gender),
                text(number),
                text("10 January,1994"),
                text(hobbie1 + ", " + hobbie2 + ", " + hobbie3),
                text(subject),
                text(image),
                text(address),
                text(state + " " + city));
    }
}
