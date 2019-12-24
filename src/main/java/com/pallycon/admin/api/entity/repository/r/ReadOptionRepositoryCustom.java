package com.pallycon.admin.api.entity.repository.r;

import com.pallycon.admin.api.service.option.model.SiteFilterRequest;

import java.util.List;

/**
 * Created by Brown on 2019-09-26.
 */
public interface ReadOptionRepositoryCustom {
    List<String> findVendorList(SiteFilterRequest siteFilterRequest);
}
