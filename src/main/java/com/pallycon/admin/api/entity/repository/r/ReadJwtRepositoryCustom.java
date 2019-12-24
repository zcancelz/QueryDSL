package com.pallycon.admin.api.entity.repository.r;

import com.pallycon.admin.api.service.token.model.TokenRequest;
import com.pallycon.admin.api.service.token.model.TokenResponse;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by Brown on 2019-08-01.
 */
public interface ReadJwtRepositoryCustom {
    @Transactional(transactionManager = "readTransactionManager")
    TokenResponse findByUserId(TokenRequest tokenRequest);
}
