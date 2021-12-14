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
    private val driver = Wikipedia.instance.getDriver(DriverType.CHROME)

    @BeforeEach
    fun setUp() {
        // Wikipedia.org sayfası için chrome driver oluşturulur.
        driver.get(page.config.baseUrl)
    }

    @Test
    fun openLoginPage() {
        goToLoginPage()

        assertEquals(driver.findElement(By.id("firstHeading")).text, "Log in")
    }

    @Test
    fun loginWithoutUsernameAndPassword() {
        goToLoginPage()
        loginByUsernameAndPassword("", "")

        Thread.sleep(3000)

        assertEquals(driver.findElement(By.id("wpLoginAttempt")).isDisplayed, true)
    }

    @Test
    fun loginWithoutPassword() {
        goToLoginPage()
        loginByUsernameAndPassword(page.config.username, "")

        assertEquals(driver.findElement(By.id("wpLoginAttempt")).isEnabled, true)
    }

    @Test
    fun loginWithoutUsername() {
        goToLoginPage()
        loginByUsernameAndPassword("", page.config.password)

        assertEquals(driver.findElement(By.id("wpLoginAttempt")).isEnabled, true)
    }

    @Test
    fun loginWithWrongPassword() {
        goToLoginPage()
        loginByUsernameAndPassword(page.config.username, "wrongPassword")

        assertEquals(driver.findElement(By.id("wpLoginAttempt")).isEnabled, true)
    }

    @Test
    fun login() {
        goToLoginPage()
        loginByUsernameAndPassword(page.config.username, page.config.password)

        // Giriş işleminin başarılı olup olmadığı bu aşamada doğrulanır.
        assertEquals(true, driver.findElement(By.id("pt-logout")).isEnabled)
    }

    private fun goToLoginPage() {
        // English butonunu bulma ve tıklama işlemi gerçekleştirilir.
        driver.findElement(By.id("js-link-box-en")).click()

        // Giriş butonunu bulma ve tıklama işlemi gerçekleştirilir.
        driver.findElement(By.id("pt-login")).click()
    }

    private fun loginByUsernameAndPassword(username: String?, password: String?) {
        // Giriş sayfasındaki kullanıcı adı ve şifre alanları bulunur.
        val etUsername = driver.findElement(By.id("wpName1"))
        val etPassword = driver.findElement(By.id("wpPassword1"))

        // Kullanıcı adı ve şifre alanlarını doldurulur.
        etUsername.sendKeys(username)
        etPassword.sendKeys(password)

        // Giriş butonu tıklanır ve tıklama işlemi gerçekleştirilir.
        driver.findElement(By.id("wpLoginAttempt")).click()
        Thread.sleep(3000)
    }
}