package com.wing.util;

import java.io.IOException;

import org.codehaus.jackson.JsonParser;
import org.codehaus.jackson.JsonProcessingException;
import org.codehaus.jackson.map.DeserializationContext;
import org.codehaus.jackson.map.JsonDeserializer;
import org.joda.time.LocalTime;

public class LocalTimeDeserializer extends JsonDeserializer<LocalTime>{

	@Override
	public LocalTime deserialize(JsonParser parser, DeserializationContext context)
			throws IOException, JsonProcessingException {
		return LocalTime.parse(parser.getText());
	}
	
	
}
