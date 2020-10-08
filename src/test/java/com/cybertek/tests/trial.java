package com.cybertek.tests;

import com.cybertek.pages.LoginPage;
import com.cybertek.utilities.WebDriverFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.List;

public class trial {
    WebDriver driver;

    @BeforeMethod
    public void setUp() {

        driver = WebDriverFactory.getDriver("chrome");
    }

    @AfterMethod
    public void tearDown() throws InterruptedException {
        Thread.sleep(2000);
        driver.quit();
    }

    @Test
    public void test1() {

        driver.get("http://practice.cybertekschool.com/multiple_buttons");

        //save our web elements inside the list
        List<WebElement> buttons = driver.findElements(By.tagName("button"));// hit alt-enter


        System.out.println("buttons.size() = " + buttons.size());

        //verify button size
        Assert.assertEquals(buttons.size(), 6, "verify buttons size");

        //iter + enter to create foreach loop -->shortcut
        for (WebElement button : buttons) {

            System.out.println("button.getText() = " + button.getText());
            System.out.println("button.isDisplayed() = " + button.isDisplayed());
            Assert.assertTrue(button.isDisplayed(), "verify buttons are displayed");
        }
        //click second button
        buttons.get(1).click();
    }
}