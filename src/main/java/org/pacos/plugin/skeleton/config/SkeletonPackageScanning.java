package org.pacos.plugin.skeleton.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 * Selects the skeleton packages that belong to the plugin Spring context.
 *
 * PacOS discovers configuration from the plugin's {@code .config} package. Keeping component scanning
 * explicit prevents unrelated packages from being pulled into the plugin context accidentally.
 */
@Configuration
@ComponentScan(basePackages = {
        "org.pacos.plugin.skeleton.backend",
        "org.pacos.plugin.skeleton.view.config",
        "org.pacos.plugin.skeleton.view.setting"})
public class SkeletonPackageScanning {
}
