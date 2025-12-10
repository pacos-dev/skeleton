package org.pacos.plugin.skeleton.config;

import org.flywaydb.core.Flyway;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.PropertySource;

@Configuration
@PropertySource("classpath:skeleton-module.properties")
public class FlywaySkeletonConfiguration {

    private static final Logger LOG = LoggerFactory.getLogger(FlywaySkeletonConfiguration.class);

    @Value("${skeleton.datasource.url}")
    public String dbUrl;

    @Value("${skeleton.datasource.username}")
    public String dbUser;

    @Value("${skeleton.datasource.password}")
    public String dbPassword;


    @Bean
    @Primary
    @Qualifier("skeletonFlyWayMigration")
    public Flyway skeletonFlyWayMigration() {
        Flyway flyway =
                //module is loaded dynamically inside pacos so the correct classpath must be defined
                Flyway.configure(getClass().getClassLoader())
                        .baselineOnMigrate(true)
                        .outOfOrder(true)
                        .dataSource(dbUrl, dbUser, dbPassword)
                        .locations("classpath:db/migration/skeleton")
                        .ignoreMigrationPatterns("*:ignored")
                        .load();
        // Start the migration
        try {
            flyway.migrate();
        } catch (Exception e) {
            LOG.error("Can't finish db migration. Check your plugin configurations ", e);
        }
        return flyway;
    }

}
