/**
 * 
 */
package org.surreal.lobster.sharedcore.server.util;

import java.lang.reflect.Type;
import java.util.Date;

import org.joda.time.DateTime;

import com.google.gson.JsonElement;
import com.google.gson.JsonPrimitive;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;

/**
 * The formats are as follows. Exactly the components shown here must be present, with exactly 
 * this punctuation. Note that the "T" appears literally in the string, to indicate the 
 * beginning of the time element, as specified in ISO 8601.
 * 
 * @see http://www.w3.org/TR/NOTE-datetime
 * 
 * Year:
 *    YYYY (eg 1997)
 * Year and month:
 *    YYYY-MM (eg 1997-07)
 * Complete date:
 *    YYYY-MM-DD (eg 1997-07-16)
 * Complete date plus hours and minutes:
 *    YYYY-MM-DDThh:mmTZD (eg 1997-07-16T19:20+01:00)
 * Complete date plus hours, minutes and seconds:
 *    YYYY-MM-DDThh:mm:ssTZD (eg 1997-07-16T19:20:30+01:00)
 * Complete date plus hours, minutes, seconds and a decimal fraction of a second
 *     YYYY-MM-DDThh:mm:ss.sTZD (eg 1997-07-16T19:20:30.45+01:00)
 *     
 * @author kerry.baumer
 *
 */
public final class ISO8601Serializer extends ISO8601Date implements JsonSerializer<Date> {

	@Override
	public JsonElement serialize(Date src, Type typeOfSrc, JsonSerializationContext context) {
		if(src == null) {
			return null;			
		}
		DateTime jdt = new DateTime(src.getTime());
		return new JsonPrimitive(jdt.toDateTimeISO().toString());			
	}
}