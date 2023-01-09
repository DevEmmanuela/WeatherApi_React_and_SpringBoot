package com.emmanuela.weatherapiproject.util;

import lombok.extern.slf4j.Slf4j;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.XML;
import org.springframework.stereotype.Component;

@Slf4j
@Component

public class XmlConversion {
    public String convert(String value) throws JSONException {
            JSONObject json = new JSONObject(value);
            String xml = XML.toString(json);
            System.out.print(xml);
    return xml;
    }
}
