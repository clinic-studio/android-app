package es.clinicstudio.app.data.api.serialization

import com.google.gson.*
import org.joda.time.DateTime
import org.joda.time.format.DateTimeFormat
import org.joda.time.format.DateTimeFormatter
import java.lang.reflect.Type
import java.text.DateFormat
import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.*


/**
 * @author vh @ recursividad.es
 */
class GsonDateTimeAdapter : JsonSerializer<DateTime>, JsonDeserializer<DateTime> {

    private val datePattern = "yyyy-MM-dd'T'HH:mm:ss"
    private val dateFormat: DateFormat
    private val dateTimeFormatter: DateTimeFormatter

    init {
        dateFormat = SimpleDateFormat(datePattern, Locale.getDefault())
        dateFormat.timeZone = TimeZone.getTimeZone("UTC")

        dateTimeFormatter = DateTimeFormat.forPattern(datePattern)
    }

    override fun serialize(src: DateTime?, typeOfSrc: Type?, context: JsonSerializationContext?): JsonElement {
        return JsonPrimitive(dateFormat.format(src))
    }

    override fun deserialize(json: JsonElement?, typeOfT: Type?, context: JsonDeserializationContext?): DateTime? {
        var result: DateTime? = null

        try {
            result = DateTime.parse(json?.asString, dateTimeFormatter)
        } catch (e: ParseException) {
        }

        return result
    }
}