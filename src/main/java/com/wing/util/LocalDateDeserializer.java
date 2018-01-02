package com.wing.util;

import java.io.IOException;

import org.codehaus.jackson.JsonParser;
import org.codehaus.jackson.JsonProcessingException;
import org.codehaus.jackson.map.DeserializationContext;
import org.codehaus.jackson.map.JsonDeserializer;
import org.joda.time.LocalDate;
import org.joda.time.format.DateTimeFormat;

public class LocalDateDeserializer extends JsonDeserializer<LocalDate>{

	@Override
	public LocalDate deserialize(JsonParser jp, DeserializationContext ctxt)
			throws IOException, JsonProcessingException {
		if(!jp.getText().isEmpty())
		return LocalDate.parse(jp.getText(),DateTimeFormat.forPattern("dd/MM/yyyy"));
		else return null;
	}

}