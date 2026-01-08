package org.pacos.plugin.skeleton;

import com.vaadin.flow.component.dependency.NpmPackage;
import com.vaadin.flow.component.page.AppShellConfigurator;
import com.vaadin.flow.component.page.Push;
import com.vaadin.flow.spring.annotation.EnableVaadin;
import com.vaadin.flow.theme.Theme;
import org.pacos.config.property.WorkingDir;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;

/**
 * Use this class to run skeleton module inside pacos system.
 */
@SpringBootApplication(scanBasePackages = {"org.pacos.core.config", "org.pacos.plugin.*.config"})
@EnableVaadin(value = {"org.pacos.core", "org.pacos.base", "org.pacos.plugin.*"})
@EnableAsync
@Theme(value = "desktop")
@NpmPackage(value = "line-awesome", version = "1.3.0")
@Push
public class Skeleton implements AppShellConfigurator {

    public static void main(String[] args) {
        System.setProperty("workingDir", "/opt/skeleton");
        WorkingDir.initialize();

        SpringApplication.run(Skeleton.class, args);
    }
}
