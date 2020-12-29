package Modules

import geb.Module

class LoginModule extends Module {

    static content = {
        form { $('form') }

        emailInput { form.$('#txtEmail') }
        passwordInput { form.$('#Password') }

        loginButton { form.$('#btnLogIn') }
        firstTimeUserButton { form.$('#btnFirstTimeUser') }
    }

    def goToLogin() {
        firstTimeUserButton.click()
    }

}
