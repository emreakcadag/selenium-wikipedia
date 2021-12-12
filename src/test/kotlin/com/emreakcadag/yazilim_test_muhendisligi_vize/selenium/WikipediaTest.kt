package com.emreakcadag.yazilim_test_muhendisligi_vize.selenium

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.openqa.selenium.By

/**
 * Created By Emre Akçadağ
 * Selenium Test
 */
class WikipediaTest {
    private val page = Wikipedia.instance
    private val driver = Wikipedia.instance.driver

    @BeforeEach
    fun setUp() {
        // Wikipedia.org sayfası için chrome driver oluşturulur.
        driver.get(page.config.baseUrl)
    }

    @Test
    fun openLoginPage() {
        // English butonunu bulma ve tıklama işlemi gerçekleştirilir.
        driver.findElement(By.id("js-link-box-en")).click()

        // Giriş butonunu bulma ve tıklama işlemi gerçekleştirilir.
        driver.findElement(By.id("pt-login")).click()

        assertEquals(driver.findElement(By.id("firstHeading")).text, "Log in")
    }

    @Test
    fun login() {
        openLoginPage()

        // Giriş sayfasındaki kullanıcı adı ve şifre alanları bulunur.
        val etUsername = driver.findElement(By.id("wpName1"))
        val etPassword = driver.findElement(By.id("wpPassword1"))

        // Kullanıcı adı ve şifre alanlarını doldurulur.
        etUsername.sendKeys(page.config.username)
        etPassword.sendKeys(page.config.password)

        // Giriş butonu tıklanır ve tıklama işlemi gerçekleştirilir.
        driver.findElement(By.id("wpLoginAttempt")).click()

        // Giriş işleminin başarılı olup olmadığı bu aşamada doğrulanır.
        assertEquals(true, driver.findElement(By.id("pt-logout")).isDisplayed)
    }
}