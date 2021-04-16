package pageobjects.chain;

import org.junit.jupiter.api.Test;

public class StudentRegistrationFormTests {
    StudentRegistrationFormPage studentRegistrationFormPage = new StudentRegistrationFormPage();

    @Test
    void successfulFillFormTest() {
        studentRegistrationFormPage
                .openPage()
                .fillForm()
                .checkData();
    }
}
