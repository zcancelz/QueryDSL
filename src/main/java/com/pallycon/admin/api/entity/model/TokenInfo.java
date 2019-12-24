package com.pallycon.admin.api.entity.model;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Created by Brown on 2019-10-22.
 */
@Entity
@Table(name = "TOKEN_INFO")
@Data
public class TokenInfo {
    @Id
    @Column(name="SEQ")
    int seq;
    @Column(name="MEMBER_SEQ")
    int memberSeq;
    @Column(name="TOKEN")
    String token;
    @Column(name="USE_FLAG")
    String useFlag;
    @Column(name="REG_DT")
    String regDt;
    @Column(name="REG_ID")
    String regId;

}
