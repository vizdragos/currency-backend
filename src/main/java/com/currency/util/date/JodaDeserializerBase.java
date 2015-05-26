package com.currency.util.date;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.deser.std.StdScalarDeserializer;
import com.fasterxml.jackson.databind.jsontype.TypeDeserializer;

import java.io.IOException;

/**
 * Based on implementation of {@link com.fasterxml.jackson.datatype.joda.deser.JodaDeserializerBase}.
 * <p/>
 * Used to be able to deserialize a DateTime in format "dd-MMM-yy HH:mm:ss" and avoiding
 * <pre>
 * Caused by: java.lang.IllegalArgumentException: Invalid format: "25-May-15 11:24:15" is malformed at "-May-15 11:24:15"
 * <pre/>
 */
public abstract class JodaDeserializerBase<T> extends StdScalarDeserializer<T>
{
	protected JodaDeserializerBase(Class<?> cls) {
		super(cls);
	}

	protected JodaDeserializerBase(JodaDeserializerBase<?> src) {
		super(src);
	}

	@Override
	public Object deserializeWithType(JsonParser jp, DeserializationContext ctxt, TypeDeserializer typeDeserializer) throws IOException, JsonProcessingException {
		return typeDeserializer.deserializeTypedFromAny(jp, ctxt);
	}
}
