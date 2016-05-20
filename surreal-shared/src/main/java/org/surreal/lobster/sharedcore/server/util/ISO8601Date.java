/**
 * 
 */
package org.surreal.lobster.sharedcore.server.util;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

import org.joda.time.DateTime;

import com.google.gson.JsonDeserializer;
import com.google.gson.JsonSerializer;

/**
 * @author kerry.baumer
 * 
 */
public abstract class ISO8601Date {

	public static final JsonDeserializer<Date> getDeserializer() {
		return new ISO8601Deserializer();
	}

	public static final JsonSerializer<Date> getSerializer() {
		return new ISO8601Serializer();
	}

	public static String toISO8601(Date date) {
		return toISO8601(date.getTime());
	}

	public static String toISO8601(long time) {
		DateTime jdt = new DateTime(time);
		return jdt.toDateTimeISO().toString();
	}
	
	public static String formatUTC(final Long localDateTime) {
		return formatUTC(new Date(localDateTime));
	}

	public static String formatUTC(final Date localDate, final String localTime) {
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		String date1 = formatter.format(localDate);
		String date2 = localTime.substring(0, 1) + ":" + localTime.substring(2);
		String date3 = date1 + "T" + date2 + "Z";
		return date3;		
	}
	
	public static String formatUTC(final Date lv_localDate) {
		String lv_dateFormateInUTC = "";// Will hold the final converted date
		String lv_localTimeZone = "";
		SimpleDateFormat lv_formatter;

		// Set output format prints "2007/10/25  18:35:07 EDT(-0400)"
		lv_formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss z'('Z')'");
		lv_formatter.setTimeZone(TimeZone.getTimeZone(lv_localTimeZone));

		// Convert the date from the local timezone to UTC timezone
		lv_formatter.setTimeZone(TimeZone.getTimeZone("UTC"));
		lv_dateFormateInUTC = lv_formatter.format(lv_localDate);

		String date1 = "";
		String date2 = "";
		String date3 = "";
		date1 = lv_dateFormateInUTC.substring(0, 10);
		date2 = lv_dateFormateInUTC.substring(11, 19);
		date3 = date1 + "T" + date2 + "Z";
		return date3;
	}

}
