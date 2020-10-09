package com.cybertek.tests;

import com.cybertek.pages.VehicleCostsPage;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.Test;

public class UserStorySix extends firstCommonSteps {
    @Test
    public void test1() {
        extentLogger = report.createTest("Accessing vehicle costs page");
        extentLogger.info("Verify access vehicle costs page");

        Assert.assertTrue(driver.getCurrentUrl().contains("VehicleCosts"));
        extentLogger.pass("Test passed");
    }

    @Test
    public void test2() throws InterruptedException {
        extentLogger = report.createTest("Creating vehicle cost");
        Thread.sleep(2000);
        extentLogger.info("Clicking the Create Vehicle Costs Button");
        driver.findElement(By.xpath("//a[@title='Create Vehicle Costs']")).click();
        Thread.sleep(3000);

        extentLogger.info("Sending data to input fields");
        driver.findElement(By.xpath("//option[@value='tax_roll']")).click();
        Thread.sleep(2000);
        driver.findElement(By.xpath("//input[@data-name='field__total-price']")).sendKeys("55");
        Thread.sleep(2000);
        driver.findElement(By.xpath("//div/input[@placeholder='Choose a date']")).click();//sendKeys("Oct 8, 2020");
        Thread.sleep(2000);
        driver.findElement(By.xpath("//button[@data-event='click']")).click();
        Thread.sleep(2000);
        driver.findElement(By.xpath("//textarea[@data-name='field__cost-descriptions']")).sendKeys("Any information");

        extentLogger.info("Clicking the Save And Close Button");
        driver.findElement(By.cssSelector(".btn.btn-success.action-button")).click();
        Thread.sleep(3000);

        extentLogger.info("Verifying the new vehicle cost entry was saved");
        String expected = "Entity saved";
        String actual = driver.findElement(By.xpath("//div[@class='flash-messages-holder']")).getText();
        Assert.assertTrue(actual.contains(expected));

        extentLogger.pass("Test passed");

    }

    @Test
    public void test3() throws InterruptedException {
        extentLogger = report.createTest("Cancelling the attempt to create vehicle cost");
        Thread.sleep(3000);
        String urlBeforeAction = driver.getCurrentUrl();

        extentLogger.info("Clicking the Create Vehicle Costs Button");
        driver.findElement(By.xpath("//a[@title='Create Vehicle Costs']")).click();
        Thread.sleep(3000);

        extentLogger.info("Clicking the cancel button");
        driver.findElement(By.xpath("//a[@title='Cancel']")).click();

        Thread.sleep(2000);
        extentLogger.info("Verifying user is at the previous page");
        String urlAfterAction = driver.getCurrentUrl();
        Assert.assertEquals(urlBeforeAction, urlAfterAction);

        extentLogger.pass("Test passed");

    }

    @Test
    public void test4() throws InterruptedException {
        extentLogger = report.createTest("Deleting the costs");

        String totalNumberBeforeDeleting = driver.findElement(By.xpath("//label[@class='dib'][3]")).getText();

        extentLogger.info("Select the first vehicle cost row in the grid");
        driver.findElement(By.xpath("//input[@data-role='select-row-cell']")).click();

        extentLogger.info("Delete the first item");
        driver.findElement(By.xpath("//button[@title='Mass Actions']")).click();
        driver.findElement(By.xpath("/html/body/ul/li/a")).click();
        Thread.sleep(3000);
        driver.findElement(By.xpath("//a[.='Yes, Delete']")).click();

        extentLogger.info("Verify that one of the items were deleted");
        String totalNumberAfterDeleting = driver.findElement(By.xpath("//label[@class='dib'][3]")).getText();
        Assert.assertNotEquals(totalNumberBeforeDeleting,totalNumberAfterDeleting);

        extentLogger.pass("Test passed");
    }

    @Test
    public void test5() throws InterruptedException {
        extentLogger = report.createTest("Editing the Vehicle costs");
        Thread.sleep(3000);

        extentLogger.info("Click the first entry on the table");
        driver.findElement(By.xpath("//tr[@class='grid-row row-click-action']")).click();
        Thread.sleep(5000);

        extentLogger.info("Click the Edit button");
        driver.findElement(By.xpath("//a[@title='Edit Vehicle Costs']")).click();

        extentLogger.info("Clear the data in one of the input fields");
        WebElement inputBox = driver.findElement(By.xpath("//input[@data-name ='field__total-price']"));
        String firstValue = inputBox.getAttribute("value");
        inputBox.clear();

        extentLogger.info("Send new data to the input field");
        inputBox.sendKeys("45,000.00");
        Thread.sleep(2000);

        extentLogger.info("Verify that the data in the input field has changed");
        Assert.assertNotEquals(inputBox.getAttribute("value"),firstValue);
        extentLogger.pass("Test passed");
    }
    @Test
    public void test6() throws InterruptedException {
        extentLogger = report.createTest("Adding attachment");

        Thread.sleep(5000);
        extentLogger.info("Click the first entry on the table");
        extentLogger.info("Click the More Actions button");
        extentLogger.info("Click the Add Attachment button");
        VehicleCostsPage vehicleCostsPage = new VehicleCostsPage();
        vehicleCostsPage.commonSteps("attachment");
        Thread.sleep(3000);

        extentLogger.info("Choosing a file to attach and uploading the file");
        driver.findElement(By.xpath("//input[@name='oro_attachment[file][file]']")).sendKeys("C:/Users/Admin/Desktop/isItFair.png");
        driver.findElement(By.xpath("//button[.='Save']")).click();
        Thread.sleep(3000);


        extentLogger.info("Verifying if the attachment was uploaded");
        String expected = "Attachment created successfully";
        String actual = vehicleCostsPage.confirmMessage.getText();
        Assert.assertTrue(actual.contains(expected));

        extentLogger.pass("Test passed");

    }
    @Test
    public void test7() throws InterruptedException {
        extentLogger = report.createTest("Adding note");
        Thread.sleep(2000);

        extentLogger.info("Click the first entry on the table");
        extentLogger.info("Click the More Actions button");
        extentLogger.info("Click the Add note button");
        VehicleCostsPage vehicleCostsPage = new VehicleCostsPage();
        vehicleCostsPage.commonSteps("note");

        extentLogger.info("Sending a note to add");
        driver.switchTo().frame(0);
        driver.findElement(By.id("tinymce")).sendKeys("any information");
        driver.switchTo().defaultContent();
        Thread.sleep(3000);

        extentLogger.info("Saving the note");
        driver.findElement(By.xpath("//button[.='Add']")).click();
        Thread.sleep(2000);

        extentLogger.info("Verifying if the note has been saved successfully");
        String expected = "Note saved";
        String actual = vehicleCostsPage.confirmMessage.getText();
        Assert.assertTrue(actual.contains(expected));

        extentLogger.pass("Test passed");

    }
    @Test
    public void test8() throws InterruptedException {
        extentLogger = report.createTest("Adding event");
        Thread.sleep(2000);

        extentLogger.info("Click the first entry on the table");
        extentLogger.info("Click the More Actions button");
        extentLogger.info("Click the Add Event button");
        VehicleCostsPage vehicleCostsPage = new VehicleCostsPage();
        vehicleCostsPage.commonSteps("event");

        extentLogger.info("Sending a note to save");
        driver.findElement(By.name("oro_calendar_event_form[title]")).sendKeys("Any information");

        extentLogger.info("Saving the note");
        driver.findElement(By.xpath("//button[.='Save']")).click();
        Thread.sleep(2000);

        extentLogger.info("Verifying if the note has been saved successfully");
        String expected = "Calendar event saved";
        String actual = vehicleCostsPage.confirmMessage.getText();
        Assert.assertTrue(actual.contains(expected));

        extentLogger.pass("Test passed");

    }


}