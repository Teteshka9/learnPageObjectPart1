package org.example;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class Main {

    public static void main (String[] args) throws InterruptedException {

        WebDriver driver = new ChromeDriver();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        driver.manage().window().maximize();


        String testingURL = "https://demoqa.com";
        driver.get(testingURL);

        WebElement elements = wait.until(ExpectedConditions
                .visibilityOfElementLocated(By.cssSelector(".card.mt-4.top-card")));
        System.out.println("Нашел первый элемент");
        assert elements!= null;
        elements.click();
        System.out.println("Нажал на первый элемент");

        System.out.println("Нажимаю на textBOX");
        WebElement textBox = wait.until(ExpectedConditions.elementToBeClickable(
                By.xpath("//span[text()='Text Box']")));
        textBox.click();
        System.out.println("Нажал на textBox");

        assert textBox != null;
        if(textBox.isEnabled()) {
            System.out.println("textbox is on");
        } else {
            System.out.println("Nothing here");
        }


        WebElement userNameInput = wait.until(ExpectedConditions
                .visibilityOfElementLocated(By.id("userName")));
        userNameInput.click();
        userNameInput.sendKeys("TestUser");

        WebElement userEmailInput = wait.until(ExpectedConditions
                .visibilityOfElementLocated(By.id("userEmail")));
        userEmailInput.click();
        userEmailInput.sendKeys("example@mail.mail");

        System.out.println("Нажимаю кнопку сабмит");
        WebElement submitButton = wait.until(ExpectedConditions
                .elementToBeClickable(By.id("submit")));
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].scrollIntoView(true);", submitButton);
        assert submitButton != null;
        submitButton.click();
        System.out.println("Нажал кнопку сабмит");

        js.executeScript("arguments[0].scrollIntoView(true);", userEmailInput);

        // короче, обычный поиск элемента не работает, если его не видно, но управление через JS видит все элементы.


//        JavascriptExecutor js = (JavascriptExecutor) driver;
//        js.executeScript("arguments[0].focus();", submitButton);
//        submitButton.click();
//        System.out.println("Нажал сабмит");

        Thread.sleep(5000);
        driver.close();
    }
}