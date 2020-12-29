
package Pages

import Modules.LoginModule
import Modules.FirstTimeUserModule
import geb.Page

class LoginPage extends Page {

    static url = '/Account/Login'

    static at = {
        title == 'Virtual Learning - Login'
    }

    static content = {
        firstTimeUserForm { module FirstTimeUserModule }
        loginForm { module LoginModule }
    }

    def goToSignUp(loginData) {
        loginForm.goToLogin()
        firstTimeUserForm.sendEmail(loginData.email)
    }

}
