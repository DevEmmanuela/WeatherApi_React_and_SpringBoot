package com.emmanuela.weatherapiproject.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.XML;
import org.springframework.stereotype.Component;
import java.util.Iterator;

@Slf4j
@Component

public class Util {
    public String convertToXml(String jsonValue) throws JSONException, JsonProcessingException {
        log.info("Running xml conversion....");
        log.info("json value {}", jsonValue);
        ObjectMapper mapper = new ObjectMapper();
        JsonNode jackson = mapper.readTree(jsonValue);

        String xmlRequest = XML.toString(new JSONAdapter(jackson));
        log.info("converted to xml " + xmlRequest);
        return xmlRequest;
    }
    private static class JSONAdapter extends JSONObject {

        private JsonNode jackson;

        public JSONAdapter(JsonNode jackson) {
            this.jackson = jackson;
        }

        @Override
        public Iterator<String> keys() {
            return jackson.fieldNames();
        }

        @Override
        public Object opt(String key) {
            return get(key);
        }

        @Override
        public Object get(String key) throws JSONException {
            JsonNode nested = jackson.get(key);
            if (nested.isObject()) {
                return new JSONAdapter(nested);
            } else if (nested.isTextual()) {
                return nested.asText();
            } else if (nested.isNumber()) {
                return nested.asDouble();
            } else if (nested.isBoolean()) {
                return nested.asBoolean();
            }
            return null;
        }

    }


    public String lengthValidation(String city){
        log.info("Running length validation");
        String status = "TRUE";
        if(city.trim().isEmpty()){
            status = "FALSE";
        }
        return status;
    }

}
