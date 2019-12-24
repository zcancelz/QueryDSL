package com.pallycon.admin.api.service.option;

import com.pallycon.admin.api.service.option.model.SiteFilterRequest;
import com.pallycon.admin.cmmn.util.LogUtil;

import java.util.Map;

/**
 * Created by Brown on 2019-09-26.
 */
public interface SearchOption {
    Map<String, Object> getSiteSearchOptionInfo(SiteFilterRequest siteFilterRequest, String jwt, LogUtil logUtil);
}
