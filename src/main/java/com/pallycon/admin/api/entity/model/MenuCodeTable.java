package com.pallycon.admin.api.entity.model;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 *  Created by Brown on 2019-10-22.
 */
@Entity
@Table(name = "MENU_CODE")
@Data
public class MenuCodeTable {
    @Id
    @Column(name="MENU_CODE")
    String menuCode;
    @Column(name="UPPER_MENU_CODE")
    String upperMenuCode;
    @Column(name="MENU_URI")
    String menuUrI;
    @Column(name="USE_YN")
    String useYn;
}
