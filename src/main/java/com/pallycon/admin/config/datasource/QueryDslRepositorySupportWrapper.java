package com.pallycon.admin.config.datasource;

import com.querydsl.core.dml.DeleteClause;
import com.querydsl.core.dml.UpdateClause;
import com.querydsl.core.types.EntityPath;
import com.querydsl.core.types.dsl.PathBuilder;
import com.querydsl.core.types.dsl.PathBuilderFactory;
import com.querydsl.jpa.JPQLQuery;
import com.querydsl.jpa.impl.JPADeleteClause;
import com.querydsl.jpa.impl.JPAUpdateClause;
import org.springframework.data.jpa.repository.support.Querydsl;
import org.springframework.util.Assert;

import javax.persistence.EntityManager;


public abstract class QueryDslRepositorySupportWrapper {

    private final PathBuilder<?> builder;

    private EntityManager entityManager;
    private Querydsl querydsl;

    /**
     * Creates a new {@link QueryDslRepositorySupport} instance for the given domain type.
     *
     * @param domainClass must not be {@literal null}.
     */
    public QueryDslRepositorySupportWrapper(Class<?> domainClass) {
        Assert.notNull(domainClass, "Domain class must not be null!");
        this.builder = new PathBuilderFactory().create(domainClass);
    }

    /**
     * Setter to inject {@link EntityManager}.
     *
     * @param entityManager must not be {@literal null}
     */
//	@PersistenceContext
    public void setEntityManager(EntityManager entityManager) {
        Assert.notNull(entityManager, "EntityManager must not be null!");
        this.querydsl = new Querydsl(entityManager, builder);
        this.entityManager = entityManager;
    }

    /**
     * Callback to verify configuration. Used by containers.
     */

    // It's deprecated because EntityManager bean lazy-init.
//	@PostConstruct
//	public void validate() {
//		Assert.notNull(entityManager, "EntityManager must not be null!");
//		Assert.notNull(querydsl, "Querydsl must not be null!");
//	}

    /**
     * Returns the {@link EntityManager}.
     *
     * @return the entityManager
     */
    protected EntityManager getEntityManager() {
        return entityManager;
    }

    /**
     * Returns a fresh {@link JPQLQuery}.
     *
     * @return the Querydsl {@link JPQLQuery}.
     */
    protected JPQLQuery from(EntityPath<?>... paths) {
        return querydsl.createQuery(paths);
    }

    /**
     * Returns a fresh {@link DeleteClause}.
     *
     * @param path
     * @return the Querydsl {@link DeleteClause}.
     */
    protected DeleteClause<JPADeleteClause> delete(EntityPath<?> path) {
        return new JPADeleteClause(entityManager, path);
    }

    /**
     * Returns a fresh {@link UpdateClause}.
     *
     * @param path
     * @return the Querydsl {@link UpdateClause}.
     */
    protected UpdateClause<JPAUpdateClause> update(EntityPath<?> path) {
        return new JPAUpdateClause(entityManager, path);
    }

    /**
     * Returns a {@link PathBuilder} for the configured domain type.
     *
     * @param <T>
     * @return the Querdsl {@link PathBuilder}.
     */
    @SuppressWarnings("unchecked")
    protected <T> PathBuilder<T> getBuilder() {
        return (PathBuilder<T>) builder;
    }

    /**
     * Returns the underlying Querydsl helper instance.
     *
     * @return
     */
    protected Querydsl getQuerydsl() {
        return this.querydsl;
    }

}
