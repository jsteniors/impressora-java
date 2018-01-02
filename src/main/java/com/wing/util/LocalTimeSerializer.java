package com.wing.util;

import java.io.IOException;

import org.codehaus.jackson.JsonGenerator;
import org.codehaus.jackson.JsonProcessingException;
import org.codehaus.jackson.map.JsonSerializer;
import org.codehaus.jackson.map.SerializerProvider;
import org.joda.time.LocalTime;

public class LocalTimeSerializer  extends JsonSerializer<LocalTime>{

	@Override
	public void serialize(LocalTime time, JsonGenerator gen, SerializerProvider ser)
			throws IOException, JsonProcessingException {
		gen.writeString(time.toString());
	}
	
	@Override
	public Class<LocalTime> handledType() {
		return LocalTime.class;
	}
}
