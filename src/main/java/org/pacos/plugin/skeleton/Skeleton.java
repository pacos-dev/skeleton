package org.pacos.plugin.skeleton;

import org.pacos.config.property.WorkingDir;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;

import com.vaadin.flow.component.dependency.NpmPackage;
import com.vaadin.flow.component.page.AppShellConfigurator;
import com.vaadin.flow.component.page.Push;
import com.vaadin.flow.spring.annotation.EnableVaadin;
import com.vaadin.flow.theme.Theme;

/**
 * Local application entry point for running the PacOS platform with the skeleton module.
 *
 * The class is a development launcher, not a plugin entry point used by an existing PacOS instance.
 * When this application is started, the skeleton is loaded as part of the same runnable environment.
 */
@SpringBootApplication(scanBasePackages = {"org.pacos.core.config", "org.pacos.plugin.*.config"})
@EnableVaadin(value = {"org.pacos.core", "org.pacos.base", "org.pacos.plugin.*"})
@EnableAsync
@Theme(value = "desktop")
@NpmPackage(value = "line-awesome", version = "1.3.0")
@Push
public class Skeleton implements AppShellConfigurator {

    public static void main(String[] args) {
        String workingDir = System.getProperty("workingDir");

        if (workingDir == null || workingDir.isBlank()) {
            workingDir = "/opt/skeleton";
            System.setProperty("workingDir", workingDir);
        }

        WorkingDir.initialize();

        SpringApplication.run(Skeleton.class, args);
    }
}
