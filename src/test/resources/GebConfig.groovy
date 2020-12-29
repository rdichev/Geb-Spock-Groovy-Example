/*
    This is the Geb configuration file.
    See: http://www.gebish.org/manual/current/#configuration
*/

import org.openqa.selenium.chrome.ChromeDriver
import org.openqa.selenium.chrome.ChromeOptions

waiting {
    timeout = 60
    includeCauseInMessage = true
}

atCheckWaiting = true

environments {
    // See: http://code.google.com/p/selenium/wiki/ChromeDriver
    chrome {
        driver = {
            ChromeOptions options = new ChromeOptions()
            options.addArguments('ignore-certificate-errors')
            options.setAcceptInsecureCerts(true)
            def driverInstance = new ChromeDriver(options)
            driverInstance.manage().window().maximize()
            driverInstance
        }
    }
}

baseUrl = System.getProperty('geb.build.baseUrl')
