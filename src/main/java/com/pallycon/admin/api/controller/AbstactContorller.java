package com.pallycon.admin.api.controller;

import com.pallycon.admin.api.template.ResponseRestTemplate;
import com.pallycon.admin.cmmn.util.LogUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.hateoas.Link;

import java.util.Map;

/**
 * Created by Brown on 2019-09-26.
 */
public abstract class AbstactContorller {
    protected Logger logger = LoggerFactory.getLogger(this.getClass());

    protected ResponseRestTemplate setResponseTemplate(Map<String, Object> responseData, LogUtil logUtil, Link self){
        ResponseRestTemplate responseRestTemplate = (ResponseRestTemplate)responseData.get("result_str");
        logUtil.put("response", responseRestTemplate);
        logger.info(logUtil.toLogString());
        responseRestTemplate.add(self);
        return responseRestTemplate;
    }
}
