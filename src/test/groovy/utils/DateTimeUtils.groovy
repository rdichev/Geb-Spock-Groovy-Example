package utils

import com.github.javafaker.Faker
import java.text.DateFormat
import java.text.SimpleDateFormat
import java.time.Clock
import java.time.Duration
import java.time.Instant
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.time.temporal.ChronoUnit


class DateTimeUtils {
    static formatDateTime(dateTimeObject, format) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(format)
        return dateTimeObject?.format(formatter)
    }

    static calculateDaysDifference(firstDateTime, secondDateTime, dateFormat){
        Locale locale = new Locale(System.getProperty("user.language"))
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(dateFormat, locale)
        LocalDateTime requestedDateTime = LocalDateTime.parse(firstDateTime, formatter)
        LocalDateTime expiredDateTime = LocalDateTime.parse(secondDateTime, formatter)
        return Duration.between(requestedDateTime, expiredDateTime).toDays()
    }

    static generateDateOfBirth(minAge = 0, maxAge = 0) {
        if (minAge > maxAge) {
            throw new IllegalArgumentException("Invalid range, maxAge should be larger than minAge")
        }

        def currentDateTime = LocalDateTime.now()
        def currentYear = currentDateTime.getYear()
        def currentMonth = currentDateTime.getMonth()
        def currentDayOfMonth = currentDateTime.getDayOfMonth()

        def from = LocalDateTime.of(currentYear - maxAge, currentMonth, currentDayOfMonth, 0, 0)
        def to = LocalDateTime.of(currentYear - minAge, currentMonth, currentDayOfMonth, 23, 59)

        def offsetDays = new Faker().random().nextLong(Duration.between(from, to).toDays())
        return from.plusDays(offsetDays)
    }

    static getCurrentTime(inUTC = false) {
        if (inUTC == true) {
            return LocalDateTime.now(Clock.systemUTC())
        } else {
            return LocalDateTime.now()
        }
    }

    static getCurrentDateInUTCEpochTimestamp() {
        return Instant.now().toEpochMilli()
    }

    static getCurrentTimestamp(inUTC = false, String timestampFormat) {
        return formatDateTime(getCurrentTime(inUTC), timestampFormat)
    }

    static convertDateToDateTimeObject(String dateToConvert) {
        String date = dateToConvert
        Locale locale = new Locale(System.getProperty("user.language"))
        DateFormat formatter = DateFormat.getDateTimeInstance(DateFormat.MEDIUM, DateFormat.MEDIUM, locale)
        String localPattern  = ((SimpleDateFormat)formatter).toLocalizedPattern()
        def localDateTimeObject = LocalDateTime.parse(date, DateTimeFormatter.ofPattern(localPattern))
        return localDateTimeObject
    }

    static isLocalDateTimeWithinSecondsOf(actualDateTime, toDateTime, seconds = 5) {
        return actualDateTime.until(toDateTime, ChronoUnit.SECONDS) <= seconds
    }

    static getCurrentYear() {
        return LocalDateTime.now().getYear()
    }

}
