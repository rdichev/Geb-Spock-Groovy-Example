package utils

import com.github.javafaker.Faker

class FakerUtils {
    private static Faker faker = new Faker()

    static generateFirstName() {
        return faker.name().firstName()
    }

    static generateLastName() {
        return faker.name().lastName()
    }

    static generateEmail(firstName = null, lastName = null, useTimestamp = null) {
        def first = firstName ?: generateFirstName()
        def last = lastName ?: generateLastName()
        def timestampAddition = useTimestamp == true ? "+${DateTimeUtils.getCurrentTimestamp(true)}" : ""

        return "${first}.${last}${timestampAddition}@svtest.test".toLowerCase()
    }

    static generatePassword(length = 10) {
        def pass = "Aa!"
        pass = pass + "${faker.internet().password(length - 5, length - 1, true, true)}${faker.number().randomDigit()}"
        if (pass.length() < length) {
            pass += faker.number().randomDigit()
        }

        return pass
    }

    static generateDate() {
        GregorianCalendar cal = new GregorianCalendar()

        def year = faker.number().numberBetween(1919, 2000)
        cal.set(cal.YEAR, year)
        def day = faker.number().numberBetween(1, cal.getActualMaximum(cal.DAY_OF_YEAR))
        cal.set(cal.DAY_OF_YEAR, day)

        return (cal.get(cal.YEAR) + "-" + (cal.get(cal.MONTH) + 1) + "-" + cal.get(cal.DAY_OF_MONTH))
    }

    static randomYodaQuote() {
        def quote = faker.yoda().quote()
        if (quote.length() > 200) {
            quote = quote.substring(0, 200)
        }
        return quote
    }
}

