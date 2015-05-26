package com.currency.util.date;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonToken;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import org.joda.time.DateTime;
import org.joda.time.DateTimeZone;
import org.joda.time.ReadableDateTime;
import org.joda.time.ReadableInstant;
import org.joda.time.format.DateTimeFormat;

import java.io.IOException;
import java.util.TimeZone;

/**
 * Based on implementation of {@link com.fasterxml.jackson.datatype.joda.deser.DateTimeDeserializer}.
 * <p/>
 * Used to be able to deserialize a DateTime in format "dd-MMM-yy HH:mm:ss" and avoiding
 * <pre>
 * Caused by: java.lang.IllegalArgumentException: Invalid format: "25-May-15 11:24:15" is malformed at "-May-15 11:24:15"
 * <pre/>
 */
public class DateTimeDeserializer extends JodaDeserializerBase<ReadableInstant> {
	private static final long serialVersionUID = 1L;

	@SuppressWarnings("unchecked")
	public DateTimeDeserializer(Class<? extends ReadableInstant> cls) {
		super((Class<ReadableInstant>) cls);
	}

	@SuppressWarnings("unchecked")
	public static <T extends ReadableInstant> JsonDeserializer<T> forType(Class<T> cls) {
		return (JsonDeserializer<T>) new DateTimeDeserializer(cls);
	}

	@Override
	public ReadableDateTime deserialize(JsonParser jp, DeserializationContext ctxt)
			throws IOException {
		JsonToken t = jp.getCurrentToken();

		if (t == JsonToken.VALUE_NUMBER_INT) {
			TimeZone tz = ctxt.getTimeZone();
			DateTimeZone dtz = (tz == null) ? DateTimeZone.UTC : DateTimeZone.forTimeZone(tz);
			return new DateTime(jp.getLongValue(), dtz);
		}
		if (t == JsonToken.VALUE_STRING) {
			String str = jp.getText().trim();
			if (str.length() == 0) { // [JACKSON-360]
				return null;
			}

			/*if (ctxt.isEnabled(DeserializationFeature.ADJUST_DATES_TO_CONTEXT_TIME_ZONE)) {
				TimeZone tz = ctxt.getTimeZone();
				DateTimeZone dtz = (tz == null) ? DateTimeZone.UTC : DateTimeZone.forTimeZone(tz);
				return new DateTime(str, dtz);
			}
			return DateTime.parse(str);*/

			return DateTime.parse(str, DateTimeFormat.forPattern(DateTimeInitializer.DATE_TIME_FORMAT));
		}
		throw ctxt.mappingException(handledType());
	}
}
