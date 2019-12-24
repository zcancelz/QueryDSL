package com.pallycon.admin.api.service.token.model;

import lombok.Data;

/**
 * Created by Brown on 2019-08-01.
 */
@Data
public class TokenRequest {
    String iss;
    String sub;
    String aud;
    Boolean admin;
    String userId;
    long iat;
    String apiCode;
}
