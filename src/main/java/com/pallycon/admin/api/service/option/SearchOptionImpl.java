package com.pallycon.admin.api.service.option;

import com.pallycon.admin.api.service.AbstractService;
import com.pallycon.admin.api.service.model.ConsoleJwtRequest;
import com.pallycon.admin.api.service.option.dao.SearchOptionDao;
import com.pallycon.admin.api.service.option.model.SiteFilterRequest;
import com.pallycon.admin.api.template.ResponseRestTemplate;
import com.pallycon.admin.cmmn.exception.ApiException;
import com.pallycon.admin.cmmn.exception.dto.ErrorDto;
import com.pallycon.admin.cmmn.util.LogUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Brown on 2019-09-26.
 */
@Service("searchOption")
public class SearchOptionImpl extends AbstractService implements SearchOption {
    @Autowired
    private SearchOptionDao searchOptionDao;

    @Override
    public Map<String, Object> getSiteSearchOptionInfo(SiteFilterRequest siteFilterRequest, String jwt, LogUtil logUtil) {
        ResponseRestTemplate responseRestTemplate = new ResponseRestTemplate();
        Map<String, Object> responseMap = new HashMap();
        ErrorDto errorDto = new ErrorDto("0000", env.getProperty("0000"));
        responseMap.put("status_code", HttpStatus.valueOf(200));
        List<String> vendorIdList = new ArrayList<>();
        try{
            ConsoleJwtRequest consoleJwtRequest = parseJwt(jwt, errorDto);
            siteFilterRequest.setUserCode(consoleJwtRequest.getUserCode());
            siteFilterRequest.setMemberSeq(consoleJwtRequest.getUserSeq());

            vendorIdList = searchOptionDao.findVendorList(siteFilterRequest, errorDto);
        }catch (ApiException e){
            e.printStackTrace();
        }finally {
            logUtil.put("error_dto", errorDto);
            cretaeResponseFormat(responseRestTemplate, vendorIdList, errorDto);
            responseMap.put("result_str", responseRestTemplate);
        }
        return responseMap;
    }
}
