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
@Table(name = "AUTH_INFO")
@Data
public class AuthInfo {
    @Id
    @Column(name = "SEQ")
    int seq;
    @Column(name="VENDOR_ID")
    String vendorId;
    @Column(name="MEMBER_SEQ")
    int memberSeq;
    @Column(name="GROUP_SEQ")
    int groupSeq;
    @Column(name="ACCOUNT_TYPE")
    String accountType;
}
