package com.pallycon.admin.api.service.option.dao;

import com.pallycon.admin.api.entity.repository.r.ReadOptionRepository;
import com.pallycon.admin.api.service.option.model.SiteFilterRequest;
import com.pallycon.admin.cmmn.exception.ApiException;
import com.pallycon.admin.cmmn.exception.dto.ErrorDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Brown on 2019-09-26.
 */
@Service
public class SearchOptionDaoImpl implements SearchOptionDao {
    @Autowired
    protected Environment env;
    @Autowired
    private ReadOptionRepository readOptionRepository;
    @Override
    public List<String> findVendorList(SiteFilterRequest siteFilterRequest, ErrorDto errorDto) throws ApiException {
        List<String> vendorIdList = new ArrayList<>();
        try{
            vendorIdList = readOptionRepository.findVendorList(siteFilterRequest);
        }catch (Exception e){
            e.printStackTrace();
            errorDto.setErrorCode("A9007");
            errorDto.setMessage(env.getProperty(errorDto.getErrorCode()));
            throw new ApiException("findBySeq", errorDto);
        }
        return vendorIdList;
    }
}
