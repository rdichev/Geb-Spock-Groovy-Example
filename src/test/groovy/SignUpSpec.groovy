import DataObjects.SignupData
import Pages.LoginPage
import Pages.CreateProfilePage
import Pages.MySessionsPage
import geb.spock.GebReportingSpec
import spock.lang.Narrative
import spock.lang.Title
import spock.lang.Shared

@Title('Signup')
@Narrative('''
As first time user
I want to be able to sign up
''')
class SignUpSpec extends GebReportingSpec {

    @Shared
    SignupData sharedValidSignupData = new SignupData('john_d@testmail.com', 'P@ssword1!', 'P@ssword1!',
    'John', 'Doe', 'United States', 'California', 'Burlingame', '(UTC-08:00) Pacific Time (US & Canada)' )

    def setup() {
        to LoginPage
    }

    def "First time user should sign up successfully"() {
        when: 'I go to and fill in the First User form'
        goToSignUp(sharedValidSignupData)

        then: 'I am at Create Profile Page'
        at CreateProfilePage

        and: 'The email address is pre-populated in the "Email address" field'
        assert createForm.emailInput == sharedValidSignupData.email

        when: 'I fill in the Signup form'
        createForm.signup(sharedValidSignupData)

        then: 'I am at the Home Page'
        at MySessionsPage

        then: 'I check that the sessions lists are empty'
        waitFor(3) { sessionsList.present }
        assert sessionsList.text() == ''
    }

}
