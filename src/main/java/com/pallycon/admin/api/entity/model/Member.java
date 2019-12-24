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
@Table(name = "MEMBER_INFO")
@Data
public class Member {
    @Id
    @Column(name="SEQ")
    private int seq;
    @Column(name="USER_ID")
    private String userId;
    @Column(name="USER_EMAIL")
    private String  userEmail;

}
