package pageobjects.chain;

import com.github.javafaker.Faker;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

public class StudentRegistrationFormPage {
    Faker faker = new Faker();

    // bad practice to store test data in pageObject
    private String firstName = faker.name().firstName(),
            lastName = faker.name().lastName(),
            email = faker.internet().emailAddress(),
            gender = "Male",
            number = faker.number().digit(),
            subject = "Maths",
            hobbie1 = "Sports",
            hobbie2 = "Reading",
            hobbie3 = "Music",
            image = "image.png",
            address = faker.address().streetAddress(),
            state = "Haryana",
            city = "Karnal";

    public StudentRegistrationFormPage openPage() {
        open("https://demoqa.com/automation-practice-form");
        return this;
    }

    public StudentRegistrationFormPage fillForm() {
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

        return this;
    }

    public void checkData() {
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
