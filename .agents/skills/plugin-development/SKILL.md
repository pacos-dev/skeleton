---
name: skeleton-plugin-development
description: Treat the skeleton as the reference implementation for building PacOS Java plugins.
---

# Skeleton Plugin Development

Use this skill when extending the skeleton or using it as a template.

## Project structure

The reference structure is:

- config for Spring, database, Flyway, and OpenAPI
- backend for services, persistence, REST, and variable providers
- security for plugin permissions
- system for plugin-local events and coordination
- view for desktop windows and settings

Keep feature code inside the smallest matching area.

## Plugin configuration

PacOS loads plugin configuration from org.pacos.plugin.<moduleName>.config.

The skeleton uses explicit package scanning to select plugin backend and UI configuration.

## Demonstrated extension points

The skeleton currently demonstrates WindowConfig with a prototype DesktopWindow, SettingTab, Permission, VariableProvider, PluginListener, ExecutableBlock, Spring MVC controllers, OpenAPI generation, and optional Vaadin RequestHandler.

Verify the current pacos source before adding an extension not represented here.

## Development modes

Skeleton.main runs a local PacOS environment with the skeleton loaded into it.

That is different from installing the produced shaded JAR into an existing PacOS instance.

