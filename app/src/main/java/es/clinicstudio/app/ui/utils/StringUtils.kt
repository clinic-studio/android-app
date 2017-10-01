package es.clinicstudio.app.ui.utils

import android.content.Context
import es.clinicstudio.app.R
import org.joda.time.*

/**
 * Useful String functions.
 *
 * @author vh @ recursividad.es
 */
class StringUtils {

    /**
     * Get a text that represents the given date time.
     *
     * @param dateTime Date time.
     * @param context Application context.
     * @return String with the date formatted.
     */
    fun getDateString(dateTime: DateTime, context: Context): String {
        val result: String

        // Calculate the minutes, hours, and days elapsed since the date time parameter
        val minutes = Minutes.minutesBetween(dateTime.toDateTime(DateTimeZone.UTC), LocalDateTime.now().toDateTime()).minutes
        val hours = Hours.hoursBetween(dateTime.toDateTime(DateTimeZone.UTC), LocalDateTime.now().toDateTime()).hours
        val days = Days.daysBetween(dateTime.toDateTime(DateTimeZone.UTC), LocalDateTime.now().toDateTime()).days

        when {
            minutes < 1 -> {
                result = context.getString(R.string.just_now)
            }
            hours < 1 -> {
                result = context.resources.getQuantityString(R.plurals.plurals_x_minutes_ago, minutes, minutes)
            }
            days < 1 -> {
                result = context.resources.getQuantityString(R.plurals.plurals_x_hours_ago, hours, hours)
            }
            days == 1 -> {
                result = context.getString(R.string.yesterday)
            }
            else -> {
                result = context.getString(R.string.on_date, dateTime.toString("dd/MM/yyyy"))
            }
        }

        return result
    }
}