package com.pallycon.admin.api.service.option.model;

import com.pallycon.admin.config.parameter.ParamName;
import lombok.Data;

import java.util.List;

/**
 * Created by Brown on 2019-09-26.
 */
@Data
public class SiteFilterRequest {
    int memberSeq;
    @ParamName("service_use")
    List<String> serviceUse;
    String userCode;
}
