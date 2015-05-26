package com.currency.util.date;

import org.joda.time.DateTimeZone;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.TimeZone;

@Component
public class DateTimeInitializer {

	public static final String DATE_TIME_FORMAT = "dd-MMM-yy HH:mm:ss"; //"yyyy-MM-dd'T'HH:mms.SSSZ"

	@PostConstruct
	private void init() {
		// set up server to run in UTC, to avoid timezone issues (ex: when querying db)
		System.setProperty("user.timezone", "UTC");
		DateTimeZone dateTimeZone = DateTimeZone.forID("UTC");
		DateTimeZone.setDefault(dateTimeZone);
		TimeZone.setDefault(dateTimeZone.toTimeZone());
	}
}
