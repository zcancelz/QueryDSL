package com.pallycon.admin.cmmn.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import java.util.HashMap;

/**
 * Created by hs on 2018-11-20.
 */
public class LogUtil {
    HashMap<String, Object> logObject;
    ObjectMapper mapper;

    public LogUtil(){
        logObject = new HashMap<>();
        mapper = new ObjectMapper();
    }

    public LogUtil(JSONObject jsonObj) {
        try{
            this.logObject = mapper.readValue(jsonObj.toJSONString(), HashMap.class);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public <V> void put(String key, V value){
        this.logObject.put(key, value);
    }

    public String toLogString(){
        String result ="";
        try {
            this.logObject.put("time_stamp", DateUtil.getGMTTimeStampString());
            result =  mapper.writeValueAsString(this.logObject);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return result;
    }
    public static String toLogString(JSONObject jsonObj){
        jsonObj.put("time_stamp", DateUtil.getGMTTimeStampString());
        return jsonObj.toJSONString();
    }
    /**
     *
     * @param key
     * @param value
     * @return
     */
    public static <V> String toLogString(String key, V value){
        JSONObject logObject = new JSONObject();
        logObject.put(key, value);
        logObject.put("time_stamp", DateUtil.getGMTTimeStampString());
        return logObject.toJSONString();
    }

    public static <T> String objectToLogString(T obj){
        JSONObject logObject= new JSONObject();
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            String jsonStr = objectMapper.writeValueAsString(obj);
            JSONParser jsonParser = new JSONParser();
            logObject = (JSONObject) jsonParser.parse(jsonStr);
            logObject.put("time_stamp", DateUtil.getGMTTimeStampString());
        }catch (Exception e){
            e.printStackTrace();
        }
        return logObject.toJSONString();
    }
}
