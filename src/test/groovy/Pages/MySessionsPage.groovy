package Pages

import geb.Page

class MySessionsPage extends Page {

    static url = '/Home'

    static at = {
        title == 'Virtual Learning - My Sessions'
    }

    static content = {
        sessionsList { $('div', id: 'listSessionsUpcoming') }
    }
}
