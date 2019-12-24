package com.pallycon.admin.api.service.token.model;

import lombok.Data;

/**
 * Created by Brown on 2019-08-01.
 */
@Data
public class TokenResponse {
    private String userId;
    private int memberSeq;
    private String vendorId;
    private int groupSeq;
    private String menuCode;
    private String token;
}
