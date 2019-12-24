package com.pallycon.admin.api.service.option.dao;

import com.pallycon.admin.api.service.option.model.SiteFilterRequest;
import com.pallycon.admin.cmmn.exception.ApiException;
import com.pallycon.admin.cmmn.exception.dto.ErrorDto;

import java.util.List;

/**
 * Created by Brown on 2019-09-26.
 */
public interface SearchOptionDao {
    List<String> findVendorList(SiteFilterRequest siteFilterRequest, ErrorDto errorDto) throws ApiException;
}
