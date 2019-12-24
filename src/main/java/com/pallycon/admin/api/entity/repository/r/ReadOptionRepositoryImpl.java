package com.pallycon.admin.api.entity.repository.r;

import com.pallycon.admin.api.entity.model.Vendor;
import com.pallycon.admin.api.service.option.model.SiteFilterRequest;
import com.pallycon.admin.config.datasource.QueryDslRepositorySupportWrapper;
import com.querydsl.core.types.Predicate;
import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

import static com.pallycon.admin.api.entity.model.QAuthInfo.authInfo;
import static com.pallycon.admin.api.entity.model.QVendor.vendor;

/**
 * Created by Brown on 2019-09-26.
 */
public class ReadOptionRepositoryImpl extends QueryDslRepositorySupportWrapper implements ReadOptionRepositoryCustom {
    @Autowired
    Environment env;

    private EntityManager entityManager;

    @PersistenceContext(unitName = "read-db")
    public void setFirstEntityManager(EntityManager entityManager) {
        this.entityManager = entityManager;
        super.setEntityManager(entityManager);
    }

    public ReadOptionRepositoryImpl() {
        super(Vendor.class);
    }


    @Override
    public List<String> findVendorList(SiteFilterRequest siteFilterRequest) {
        JPAQueryFactory jpaQueryFactory = new JPAQueryFactory(entityManager);
        List<String> vendorList = jpaQueryFactory.select(Projections.fields(String.class, vendor.vendorId))
                .from(vendor)
                .innerJoin(authInfo).on(vendor.vendorId.eq(authInfo.vendorId))
                .where( eqMemberSeq(siteFilterRequest)
                        , vendor.serviceUse.in(siteFilterRequest.getServiceUse()))
                .groupBy(vendor.vendorId)
                .fetch();
        return vendorList;
    }

    private Predicate eqMemberSeq(SiteFilterRequest siteFilterRequest) {
        if(siteFilterRequest.getUserCode().contains("'AD")){
            return null;
        }else{
            return authInfo.memberSeq.eq(siteFilterRequest.getMemberSeq());
        }
    }
}
