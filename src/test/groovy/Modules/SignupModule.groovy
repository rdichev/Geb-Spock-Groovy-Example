/* groovylint-disable ClassJavadoc, CompileStatic, DuplicateStringLiteral,
groovylint-disable FieldTypeRequired, MethodParameterTypeRequired, MethodReturnTypeRequired, NoDef */
package Modules

import geb.Module

class SignupModule extends Module {

    static content = {
        emailInput { $('#EmailAddress') }

        passwordInput { $('#Password') }

        confirmPasswordInput { $('#ConfirmPassword') }

        firstNameInput { $('#FirstName') }
        lastNameInput { $('#LastName') }

        countryInput { $('input', name: 'CountryId_input') }
        stateLabel { $('#RegionId_label') }
        stateInput { $('input', name: 'RegionId_input') }
        cityInput { $('input', name: 'CityId_input') }
        timezoneInput { $('input', name: 'TimeZoneInfoId_input') }

        saveButton { $('#saveButton') }
    }

    def signup(signupData) {
        emailInput = signupData.email
        passwordInput = signupData.password
        confirmPasswordInput = signupData.confirmPassword
        firstNameInput = signupData.firstName
        lastNameInput = signupData.lastName
        countryInput.click()
        countryInput = signupData.country
        stateInput = signupData.state
        cityInput = signupData.city
        saveButton.click()
    }
}
