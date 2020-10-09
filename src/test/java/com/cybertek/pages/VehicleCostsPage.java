package com.cybertek.pages;

import com.cybertek.utilities.Driver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class VehicleCostsPage extends BasePage {


    @FindBy(xpath = "//tr[@class='grid-row row-click-action']")
    WebElement firstRow;

    @FindBy(xpath = "//a[@class='btn dropdown-toggle']")
    WebElement dropdown;

    @FindBy(xpath = "//a[@title='Add attachment']")
    WebElement addAttachment;

    @FindBy(xpath = "//a[@title='Add note']")
    WebElement addNote;

    @FindBy(xpath = "//a[@title='Add an event to this record']")
    WebElement addEvent;

    @FindBy(xpath = "//div[@class='flash-messages-holder']")
    public WebElement confirmMessage;

    public void commonSteps(String addAction) throws InterruptedException {
        firstRow.click();
        Thread.sleep(2000);
        dropdown.click();
        if(addAction.equalsIgnoreCase("attachment")){
            addAttachment.click();
        }else if(addAction.equalsIgnoreCase("note")){
            addNote.click();
        }else if (addAction.equalsIgnoreCase("event")){
            addEvent.click();
        }

        Thread.sleep(3000);
    }


}
