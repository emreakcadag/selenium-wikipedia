package com.emreakcadag.yazilim_test_muhendisligi_vize.selenium

import com.emreakcadag.yazilim_test_muhendisligi_vize.selenium.DriverType.CHROME
import com.emreakcadag.yazilim_test_muhendisligi_vize.selenium.DriverType.OPERA
import io.github.bonigarcia.wdm.WebDriverManager
import org.openqa.selenium.WebDriver
import org.openqa.selenium.chrome.ChromeDriver
import org.openqa.selenium.opera.OperaDriver

/**
 * Created By Emre Akçadağ
 * Selenium Test
 */
class Wikipedia {
    companion object {
        @Volatile
        private var INSTANCE: Wikipedia? = null

        var instance: Wikipedia
            get() = if (INSTANCE == null) {
                INSTANCE = Wikipedia()
                INSTANCE!!
            } else {
                INSTANCE!!
            }
            private set(_) {}
    }

    val config = WikipediaConfig()

    fun getDriver(driverType: DriverType): WebDriver = when (driverType) {
        CHROME -> {
            WebDriverManager.chromedriver().setup()
            ChromeDriver()
        }
        OPERA -> {
            WebDriverManager.operadriver().setup()
            OperaDriver()
        }
    }
}