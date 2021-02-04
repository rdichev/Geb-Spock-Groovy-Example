package utils

import org.openqa.selenium.JavascriptExecutor

class JavaScriptUtils {
    static runScript(driver, script) {
        JavascriptExecutor js =((JavascriptExecutor) driver)
        js.executeScript(script)
    }

    static runScript(driver, script, element) {
        JavascriptExecutor js =((JavascriptExecutor) driver)
        js.executeScript(script, element)
    }

    static scrollToElement(driver, element) {
        runScript(driver, "arguments[0].scrollIntoView(true);", element)
    }

    static clickOnElement(driver, element) {
        runScript(driver, "arguments[0].click();", element)
    }
}

