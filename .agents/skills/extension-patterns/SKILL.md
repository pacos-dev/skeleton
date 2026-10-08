---
name: skeleton-extension-patterns
description: Implement common PacOS plugin extension points using the skeleton's existing patterns.
---

# Skeleton Extension Patterns

Use this skill when adding a plugin extension.

## Desktop window

Create a WindowConfig component and a DesktopWindow implementation.

Use prototype scope for windows.

Inject services and proxies through constructors.

Use isAllowedForCurrentSession(...) for session-level availability and perform action authorization separately where required.

## Settings and security

Implement SettingTab using the existing settings pattern.

Implement Permission with stable keys.

Do not use UI visibility as the only authorization mechanism.

## Variables

Implement VariableProvider as a Spring bean.

The current PacOS runtime discovers it automatically. Do not add manual registration listeners.

## Events

Keep plugin-local events in system.

For component-bound UI subscriptions prefer subscribeOnAttached(...).

## REST

Use Spring MVC controllers under the plugin backend API area.

Keep API models separate from persistence entities where that is the established pattern.

Preserve the OpenAPI generation path and plugin namespace.

## Persistence

Keep entities and repositories in plugin-owned packages.

Use a plugin-specific persistence unit, transaction manager, property prefix, and migration location.

Add the matching Flyway migration for schema changes.

## Automation

Implement ExecutableBlock only for real automation work.

Keep BlockMetadata.camundaDelegateName() stable and globally unique.

Automation discovery is separate from general plugin data-loader discovery.

