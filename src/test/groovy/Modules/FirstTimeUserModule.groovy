package Modules

import geb.Module

class FirstTimeUserModule extends Module {

    static content = {
        form { $('form') }
        emailInput { form.$('#txtEmail') }
        continueButton { form.$('#btnContinue') }
    }

    def sendEmail(email) {
        emailInput = email
        continueButton.click()
    }

}
