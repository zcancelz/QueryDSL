package com.pallycon.admin.api.entity.model;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Created by Brown on 2019-06-26.
 */
@Entity
@Table(name = "VENDOR_INFO")
@Data
public class Vendor {
    @Id
    @Column(name = "VENDOR_ID")
    private String vendorId;
    @Column(name = "SERVICE_NAME")
    private String serviceName;
    @Column(name = "SERVICE_USE")
    private String serviceUse;
    @Column(name = "MEMBER_SEQ")
    private int memberSeq;

}
