package com.OneClick.hackathon.util;

/**
 * Created by romilvasani on 6/29/17.
 */
import java.io.IOException;

import com.fasterxml.jackson.core.JsonParser.Feature;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

public class JsonMappingUtils {
    public static final ObjectMapper ES_IDX_OUTPUT_MAPPER;
    public static final ObjectMapper DEFAULT_JSON_MAPPER;

    private JsonMappingUtils() {
    }

    public static String toJson(Object o) {
        try {
            return DEFAULT_JSON_MAPPER.writeValueAsString(o);
        } catch (JsonProcessingException var2) {
            throw new RuntimeException(var2);
        }
    }

    public static <T> T fromJson(String json, Class<T> type) {
        if(json == null) {
            return null;
        } else {
            try {
                return DEFAULT_JSON_MAPPER.readValue(json, type);
            } catch (IOException var3) {
                throw new RuntimeException(var3);
            }
        }
    }

    static {
        ES_IDX_OUTPUT_MAPPER = (new ObjectMapper()).enable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS).disable(SerializationFeature.INDENT_OUTPUT).findAndRegisterModules();
        DEFAULT_JSON_MAPPER = (new ObjectMapper()).disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES).disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS).enable(SerializationFeature.INDENT_OUTPUT).enable(new Feature[]{Feature.ALLOW_COMMENTS}).findAndRegisterModules();
    }
}
