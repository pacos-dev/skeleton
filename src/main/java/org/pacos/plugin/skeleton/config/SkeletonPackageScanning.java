package org.pacos.plugin.skeleton.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 * Defines package to scan by spring context during launch
 */
@Configuration
@ComponentScan(basePackages = {
        "org.pacos.plugin.skeleton.backend",
        "org.pacos.plugin.skeleton.view.config",
        "org.pacos.plugin.skeleton.view.setting"})
public class SkeletonPackageScanning {
}
