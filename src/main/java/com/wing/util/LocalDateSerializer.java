package com.wing.util;

import java.io.IOException;

import org.codehaus.jackson.JsonGenerator;
import org.codehaus.jackson.JsonProcessingException;
import org.codehaus.jackson.map.JsonSerializer;
import org.codehaus.jackson.map.SerializerProvider;
import org.joda.time.LocalDate;
import org.joda.time.format.DateTimeFormat;

public class LocalDateSerializer extends JsonSerializer<LocalDate>{

	@Override
	public void serialize(LocalDate value, JsonGenerator jgen, SerializerProvider provider)
			throws IOException, JsonProcessingException {
		jgen.writeString(value.toString(DateTimeFormat.forPattern("dd/MM/yyyy")));
	}
	
	@Override
	public Class<LocalDate> handledType() {
		return LocalDate.class;
	}

}