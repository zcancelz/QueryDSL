package com.pallycon.admin.api.controller;

import com.pallycon.admin.api.service.option.SearchOption;
import com.pallycon.admin.api.service.option.model.SiteFilterRequest;
import com.pallycon.admin.api.template.ResponseRestTemplate;
import com.pallycon.admin.cmmn.util.LogUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.mvc.ControllerLinkBuilder;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.annotation.Resource;
import java.util.Map;

/**
 * Created by Brown on 2019-09-26.
 */
@Controller
@RequestMapping(value = "/api")
public class OptionController extends AbstactContorller {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    protected Environment env;

    @Resource(name="searchOption")
    private SearchOption searchOption;

    @RequestMapping(value = {"/filter/site"}, produces = MediaType.APPLICATION_JSON_UTF8_VALUE, method = RequestMethod.GET)
    public ResponseEntity getSiteSearchOptionInfo(@ModelAttribute("siteFilterRequest") SiteFilterRequest siteFilterRequest,
                        @RequestHeader(required=false, value="Authorization") String jwt){
        Link self = ControllerLinkBuilder.linkTo(ControllerLinkBuilder.methodOn(OptionController.class)
                .getSiteSearchOptionInfo(siteFilterRequest, jwt)).withSelfRel();
        LogUtil logUtil = new LogUtil();
        logUtil.put("siteFilterRequest", siteFilterRequest);
        logUtil.put("jwt", jwt);
        Map<String, Object> responseData = searchOption.getSiteSearchOptionInfo(siteFilterRequest, jwt, logUtil);
        ResponseRestTemplate responseRestTemplate = setResponseTemplate(responseData, logUtil, self);
        return ResponseEntity.status((HttpStatus) responseData.get("status_code")).body(responseRestTemplate);
    }
}
