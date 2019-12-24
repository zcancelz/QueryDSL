package com.pallycon.admin.config.datasource;

import com.zaxxer.hikari.HikariConfig;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;

import javax.sql.DataSource;
import java.util.Properties;

/**
 * Created by Brown on 2019-10-21.
 */
public class DatabaseConfig {
    public static HikariConfig createHikariConfig(String driverClassname, String userName, String password, String jdbcUrl){
        HikariConfig config = new HikariConfig();
        config.setJdbcUrl(jdbcUrl);
        config.setDriverClassName(driverClassname);
        config.setUsername(userName);
        config.setPassword(password);

        config.setConnectionTimeout(10000);
        config.setIdleTimeout(10000);
        config.setMaximumPoolSize(10);
        config.setValidationTimeout(10000);
//        config.setauto();

        config.setMaxLifetime(28000);
        config.addDataSourceProperty( "cachePrepStmts", "true");
        config.addDataSourceProperty("prepStmtCacheSize", "250");
        config.addDataSourceProperty("prepStmtCacheSqlLimit", "2048");
        config.addDataSourceProperty("useServerPrepStmts", "true");
        config.addDataSourceProperty("useLocalSessionState", "true");
        config.addDataSourceProperty("rewriteBatchedStatements", "true");
        config.addDataSourceProperty("cacheResultSetMetadata", "true");
        config.addDataSourceProperty("cacheServerConfiguration", "true");
        config.addDataSourceProperty("elideSetAutoCommits", "true");
        config.addDataSourceProperty("maintainTimeStats", "false");

        return config;
    }

    public static LocalContainerEntityManagerFactoryBean createEntityManagerFactory(DataSource dataSource, String unitName){
        LocalContainerEntityManagerFactoryBean entityManagerFactoryBean = new LocalContainerEntityManagerFactoryBean();
        HibernateJpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
        vendorAdapter.setShowSql(true);
        vendorAdapter.setDatabasePlatform("org.hibernate.dialect.MariaDBDialect");
        vendorAdapter.setGenerateDdl(false);
        Properties properties = new Properties();
        properties.setProperty("hibernate.dialect", "org.hibernate.dialect.MariaDBDialect");
        properties.setProperty("hibernate.hbm2ddl.auto", "update");
        properties.setProperty("hibernate.physical_naming_strategy", "org.hibernate.boot.model.naming.PhysicalNamingStrategyStandardImpl");
        properties.setProperty("hibernate.show_sql", "true");

        entityManagerFactoryBean.setDataSource(dataSource);
        entityManagerFactoryBean.setPackagesToScan("com.pallycon.admin.api.entity.model");
        entityManagerFactoryBean.setPersistenceUnitName(unitName);
        entityManagerFactoryBean.setJpaVendorAdapter(vendorAdapter);
        entityManagerFactoryBean.setJpaProperties(properties);

        return entityManagerFactoryBean;
    }
}
