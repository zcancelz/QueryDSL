package com.pallycon.admin.api.template;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonSetter;
import org.springframework.hateoas.ResourceSupport;

/**
 * Created by Brown on 2019-06-19.
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ResponseRestTemplate extends ResourceSupport {

    @JsonProperty("error_code")
    public String errorCode;
    @JsonProperty("error_message")
    public String errorMessage;
    public Object dataInfo;
    @JsonProperty("count")
    public long count;

    public Object getDataInfo() {
        return dataInfo;
    }

    @JsonSetter("data")
    public void setDataInfo(Object dataInfo) {
        this.dataInfo = dataInfo;
    }
}
