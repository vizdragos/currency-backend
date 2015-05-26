package com.currency.util.date;

import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.module.SimpleModule;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import com.fasterxml.jackson.datatype.joda.PackageVersion;
import com.fasterxml.jackson.datatype.joda.deser.*;
import com.fasterxml.jackson.datatype.joda.deser.key.DateTimeKeyDeserializer;
import com.fasterxml.jackson.datatype.joda.deser.key.LocalDateKeyDeserializer;
import com.fasterxml.jackson.datatype.joda.deser.key.LocalDateTimeKeyDeserializer;
import com.fasterxml.jackson.datatype.joda.deser.key.LocalTimeKeyDeserializer;
import com.fasterxml.jackson.datatype.joda.ser.*;
import org.joda.time.*;

/**
 * Based on implementation of {@link com.currency.util.date.JodaModule}.
 * <p/>
 * Used to be able to deserialize a DateTime in format "dd-MMM-yy HH:mm:ss" and avoiding
 * <pre>
 * Caused by: java.lang.IllegalArgumentException: Invalid format: "25-May-15 11:24:15" is malformed at "-May-15 11:24:15"
 * <pre/>
 */
public class JodaModule extends SimpleModule {
	private static final long serialVersionUID = 1L;

	@SuppressWarnings("unchecked")
	public JodaModule() {
		super(PackageVersion.VERSION);
		// first deserializers
		addDeserializer(DateMidnight.class, new DateMidnightDeserializer());
		addDeserializer(DateTime.class, com.currency.util.date.DateTimeDeserializer.forType(DateTime.class));
		addDeserializer(DateTimeZone.class, new DateTimeZoneDeserializer());

		addDeserializer(Duration.class, new DurationDeserializer());
		addDeserializer(Instant.class, new InstantDeserializer());
		addDeserializer(LocalDateTime.class, new LocalDateTimeDeserializer());
		addDeserializer(LocalDate.class, new LocalDateDeserializer());
		addDeserializer(LocalTime.class, new LocalTimeDeserializer());
		JsonDeserializer<?> deser = new PeriodDeserializer(true);
		addDeserializer(Period.class, (JsonDeserializer<Period>) deser);
		addDeserializer(ReadablePeriod.class, new PeriodDeserializer(false));
		addDeserializer(ReadableDateTime.class, DateTimeDeserializer.forType(ReadableDateTime.class));
		addDeserializer(ReadableInstant.class, DateTimeDeserializer.forType(ReadableInstant.class));
		addDeserializer(Interval.class, new IntervalDeserializer());
		addDeserializer(MonthDay.class, new MonthDayDeserializer());
		addDeserializer(YearMonth.class, new YearMonthDeserializer());

		// then serializers:
		final JsonSerializer<Object> stringSer = ToStringSerializer.instance;
		addSerializer(DateMidnight.class, new DateMidnightSerializer());
		addSerializer(DateTime.class, new DateTimeSerializer());
		addSerializer(DateTimeZone.class, new DateTimeZoneSerializer());
		addSerializer(Duration.class, new DurationSerializer());
		addSerializer(Instant.class, new InstantSerializer());
		addSerializer(LocalDateTime.class, new LocalDateTimeSerializer());
		addSerializer(LocalDate.class, new LocalDateSerializer());
		addSerializer(LocalTime.class, new LocalTimeSerializer());
		addSerializer(Period.class, new PeriodSerializer());
		addSerializer(Interval.class, new IntervalSerializer());
		addSerializer(MonthDay.class, stringSer);
		addSerializer(YearMonth.class, stringSer);

		// then key deserializers
		addKeyDeserializer(DateTime.class, new DateTimeKeyDeserializer());
		addKeyDeserializer(LocalTime.class, new LocalTimeKeyDeserializer());
		addKeyDeserializer(LocalDate.class, new LocalDateKeyDeserializer());
		addKeyDeserializer(LocalDateTime.class, new LocalDateTimeKeyDeserializer());
	}

	// yes, will try to avoid duplicate registations (if MapperFeature enabled)
	@Override
	public String getModuleName() {
		return getClass().getSimpleName();
	}

	@Override
	public int hashCode() {
		return getClass().hashCode();
	}

	@Override
	public boolean equals(Object o) {
		return this == o;
	}
}
