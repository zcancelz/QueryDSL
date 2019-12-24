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
@Table(name = "GROUP_AUTH")
@Data
public class GroupAuth {
    @Id
    @Column(name="SEQ")
    int seq;
    @Column(name="GROUP_SEQ")
    int groupSeq;
    @Column(name="MENU_CODE")
    String menuCode;
}
