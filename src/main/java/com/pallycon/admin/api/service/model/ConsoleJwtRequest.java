package com.pallycon.admin.api.service.model;

import lombok.Data;

/**
 * Created by Brown on 2019-09-26.
 */
@Data
public class ConsoleJwtRequest {
    private String sub;
    private String aud;
    private String iss;
    private Boolean admin;
    private int userSeq;
    private String userId;
    private String userCert;
    private String userEmail;
    private int countrySeq;
    private int groupSeq;
    private long iat;
    private String userCode;
}
