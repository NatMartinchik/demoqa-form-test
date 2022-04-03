package com.martinchikn;

import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.io.File;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$x;
import static com.codeborne.selenide.Selenide.open;

public class DemoqaTests {

    @BeforeAll
    static void setUp() {
        Configuration.baseUrl = "https://demoqa.com";
        Configuration.browserSize = "1920x1080";
    }

    @Test
    void fillFormTest() {
        String fname = "Alex";
        String lname = "Egorov";
        String email = "alexetest@test.ru";
        String phone = "7000999001";
        String address = "36 Main str, app 54";

        open("/automation-practice-form");
        zoom(0.5);

        $("#firstName").setValue(fname);
        $("#lastName").setValue(lname);
        $("#userEmail").setValue(email);
        $(byText("Male")).click();
        $("#userNumber").setValue(phone);
        $("#dateOfBirthInput").click();
        $(".react-datepicker__month-select").selectOptionByValue("8");
        $(".react-datepicker__year-select").selectOptionByValue("1995");
        $x("//*[@id=\"dateOfBirth\"]/div[2]/div[2]/div/div/div[2]/div[2]/div[1]/div[7]").click();
        $("#subjectsInput").setValue("Computer Science").pressEnter();
        $(byText("Reading")).click();
        $("input#uploadPicture").uploadFile(new File("src/test/resources/cat.jpg"));
        $("#currentAddress").setValue(address);
        $(".css-1wa3eu0-placeholder").click();
        $(byText("NCR")).click();
        $(".css-1wa3eu0-placeholder").click();
        $(byText("Delhi")).click();
        $("#submit").click();

        $(".table-responsive").shouldHave(
                text( fname + " " + lname),
                text(phone),
                text(email),
                text("Male"),
                text("02 September,1995"),
                text("Computer Science"),
                text("cat.jpg"),
                text("Reading"),
                text("36 Main str, app 54"),
                text("NCR Delhi"));

        $("#closeLargeModal").click();

    }
}

