package com.emmanuela.weatherapiproject.util;

import lombok.extern.slf4j.Slf4j;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.XML;
import org.springframework.stereotype.Component;

@Slf4j
@Component

public class Util {
    public String convertToXml(String value) throws JSONException {
        log.info("Running xml conversion....");
        JSONObject json = new JSONObject(value);
        String xml = XML.toString(json);
        log.info("xml conversion{}", xml);
        return xml;
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
