package com.pallycon.admin.api.entity.repository.r;

import com.pallycon.admin.api.entity.model.TokenInfo;
import com.pallycon.admin.api.service.token.model.TokenRequest;
import com.pallycon.admin.api.service.token.model.TokenResponse;
import com.pallycon.admin.config.datasource.QueryDslRepositorySupportWrapper;
import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import static com.pallycon.admin.api.entity.model.QAuthInfo.authInfo;
import static com.pallycon.admin.api.entity.model.QGroupAuth.groupAuth;
import static com.pallycon.admin.api.entity.model.QMember.member;
import static com.pallycon.admin.api.entity.model.QTokenInfo.tokenInfo;

/**
 * Created by Brown on 2019-08-01.
 */
@Repository
public class ReadJwtRepositoryImpl extends QueryDslRepositorySupportWrapper implements ReadJwtRepositoryCustom {
    private static final String ADMIN_VENDOR_ID="INKA";

    @Autowired
    Environment env;

    private EntityManager entityManager;

    @PersistenceContext(unitName = "read-db")
    public void setFirstEntityManager(EntityManager entityManager) {
        this.entityManager = entityManager;
        super.setEntityManager(entityManager);
    }

    public ReadJwtRepositoryImpl() {
        super(TokenInfo.class);
    }


    @Override
    public TokenResponse findByUserId(TokenRequest tokenRequest) {
        JPAQueryFactory jpaQueryFactory = new JPAQueryFactory(entityManager);

        TokenResponse tokenResponse = jpaQueryFactory.select(Projections.fields(TokenResponse.class
                , member.userId.as("userId")
                , member.seq.as("memberSeq")
                , authInfo.groupSeq.as("groupSeq")
                , tokenInfo.token.as("token")))
                .from(member)
                .innerJoin(authInfo).on(member.seq.eq(authInfo.memberSeq))
                .innerJoin(tokenInfo).on(member.seq.eq(tokenInfo.memberSeq))
                .innerJoin(groupAuth).on(authInfo.groupSeq.eq(groupAuth.groupSeq))
                .where( member.userId.eq(tokenRequest.getUserId())
                        , authInfo.groupSeq.eq(Integer.parseInt(env.getProperty("admin.group-seq"))))
                .fetchOne();

        return tokenResponse;
    }
}
