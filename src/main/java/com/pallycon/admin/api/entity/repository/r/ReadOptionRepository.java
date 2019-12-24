package com.pallycon.admin.api.entity.repository.r;

import com.pallycon.admin.api.entity.model.Vendor;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by Brown on 2019-09-26.
 */
public interface ReadOptionRepository extends JpaRepository<Vendor, Integer>, ReadOptionRepositoryCustom {
}
