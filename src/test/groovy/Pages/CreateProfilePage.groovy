package Pages

import Modules.SignupModule
import geb.Page

class CreateProfilePage extends Page {

    static url = '/Account/MyProfile'

    static at = {
        mainPanelTitle.text() == 'My Account'
    }

    static content = {
        pageContainer { $('.page-container') }
        mainPanelTitle { pageContainer.$('.title') }
        createForm { module SignupModule }
    }

}
