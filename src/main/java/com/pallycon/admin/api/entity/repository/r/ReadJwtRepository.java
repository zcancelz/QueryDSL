package com.pallycon.admin.api.entity.repository.r;

import com.pallycon.admin.api.entity.model.Member;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by Brown on 2019-08-01.
 */
public interface ReadJwtRepository extends JpaRepository<Member, Integer>, ReadJwtRepositoryCustom {
}
