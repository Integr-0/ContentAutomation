package utils

import org.openqa.selenium.By
import org.openqa.selenium.chrome.ChromeDriver
import org.openqa.selenium.support.locators.RelativeLocator


/**
 * **Author: Integr**
 * - **What:** Uploads a given file to TikTok
 * - **How:** Using a Selenium automation
 * - **Why:** To auto upload a video
 */
class Uploader {
    companion object {
        fun uploadTikTok(filename: String, title: String, password: String, email: String) {
            println("|| Auto-uploading video to TikTok (PlaceHolder)")

            val driver = ChromeDriver()

            driver.get("https://www.tiktok.com/login/phone-or-email/email")

            val nameField = driver.findElement(By.name("username"))
            nameField.sendKeys(email)

            val pwField = driver.findElement(RelativeLocator.with(By.tagName("input")).below(By.name("username")))
            pwField.sendKeys(password)

            pwField.submit()

            //driver.quit()
        }
    }
}