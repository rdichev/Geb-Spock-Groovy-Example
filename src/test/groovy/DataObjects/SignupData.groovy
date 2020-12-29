package DataObjects

import groovy.transform.TupleConstructor

@TupleConstructor
class SignupData {

    String email,
    password,
    confirmPassword,
    firstName,
    lastName,
    country,
    state,
    city,
    timezone
}
