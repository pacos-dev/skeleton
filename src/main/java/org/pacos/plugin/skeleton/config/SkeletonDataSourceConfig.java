package org.pacos.plugin.skeleton.config;

import com.zaxxer.hikari.HikariDataSource;
import jakarta.persistence.EntityManagerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

@Configuration
@EnableJpaRepositories(basePackages = "org.pacos.plugin.skeleton.backend",
        entityManagerFactoryRef = "skeletonEntityManagerFactory",
        transactionManagerRef = "skeletonTransactionManager")
@PropertySource("classpath:skeleton-module.properties")
@EnableTransactionManagement
@DependsOn("skeletonFlyWayMigration")
public class SkeletonDataSourceConfig {

    private final Environment environment;

    @Autowired
    public SkeletonDataSourceConfig(Environment environment) {
        this.environment = environment;
    }

    @Bean("skeletonDataSource")
    public DataSource skeletonDataSource() {
        HikariDataSource dataSource = new HikariDataSource();
        dataSource.setDriverClassName(Objects.requireNonNull(environment.getProperty("skeleton.datasource.driverClassName")));
        dataSource.setJdbcUrl(environment.getProperty("skeleton.datasource.url"));
        dataSource.setPassword(environment.getProperty("skeleton.datasource.password"));
        dataSource.setUsername(environment.getProperty("skeleton.datasource.username"));

        dataSource.setMaximumPoolSize(10);
        dataSource.setMinimumIdle(5);
        dataSource.setIdleTimeout(600000);
        dataSource.setMaxLifetime(1800000);
        dataSource.setConnectionTimeout(30000);
        return dataSource;
    }

    @Bean("skeletonJpaVendorAdapter")
    public JpaVendorAdapter skeletonJpaVendorAdapter() {
        HibernateJpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
        vendorAdapter.setGenerateDdl(true);
        vendorAdapter.setShowSql(true);
        return vendorAdapter;
    }

    @Bean
    public LocalContainerEntityManagerFactoryBean skeletonEntityManagerFactory(
            JpaVendorAdapter skeletonJpaVendorAdapter, @Qualifier("skeletonDataSource") DataSource dataSource) {

        LocalContainerEntityManagerFactoryBean emFactory = new LocalContainerEntityManagerFactoryBean();
        emFactory.setDataSource(dataSource);
        emFactory.setPackagesToScan("org.pacos.plugin.skeleton.backend");
        emFactory.setJpaVendorAdapter(skeletonJpaVendorAdapter);
        emFactory.setPersistenceUnitName("skeletonModule");

        Map<String, Object> properties = new HashMap<>();
        properties.put("hibernate.hbm2ddl.auto", environment.getProperty("spring.jpa.hibernate.ddl-auto"));
        properties.put("hibernate.show_sql", environment.getProperty("spring.jpa.hibernate.show-sql"));
        properties.put("hibernate.format_sql", environment.getProperty("spring.jpa.hibernate.format-sql"));
        properties.put("hibernate.jdbc.batch_size", environment.getProperty("spring.jpa.hibernate.jdbc.batch_size"));
        properties.put("hibernate.generate_statistics", environment.getProperty("spring.jpa.hibernate.generate_statistics"));

        properties.put("hibernate.order_inserts", "true");
        properties.put("hibernate.order_updates", "true");
        properties.put("hibernate.connection.provider_disables_autocommit", "true");
        properties.put("hibernate.connection.release_mode", "after_transaction");
        properties.put("hibernate.transaction.coordinator_class", "jdbc");

        emFactory.setJpaPropertyMap(properties);

        return emFactory;
    }


    @Bean
    public PlatformTransactionManager skeletonTransactionManager(
            @Qualifier("skeletonEntityManagerFactory") EntityManagerFactory entityManagerFactory) {
        JpaTransactionManager transactionManager = new JpaTransactionManager();
        transactionManager.setEntityManagerFactory(entityManagerFactory);
        return transactionManager;
    }
}
