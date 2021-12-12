package com.emreakcadag.yazilim_test_muhendisligi_vize.selenium

import io.github.bonigarcia.wdm.WebDriverManager
import org.openqa.selenium.WebDriver
import org.openqa.selenium.chrome.ChromeDriver

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

    val driver = chrome()
    val config = WikipediaConfig()

    private fun chrome(): WebDriver {
        WebDriverManager.chromedriver().setup()
        return ChromeDriver()
    }
}