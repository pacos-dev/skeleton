---
name: skeleton-naming-and-structure
description: Keep plugin packages and class names consistent with the PacOS skeleton conventions.
---

# Skeleton Naming and Structure

Use this skill when adding or reorganizing skeleton code.

## Package root

Use org.pacos.plugin.<moduleName>.

Keep plugin code below the plugin root so configuration scanning and Vaadin setup remain predictable.

## Areas

Follow config, backend, security, system, and view.

Within backend use names that communicate actual responsibility such as service, repository, api, or provider roles.

## Class names

Prefer FooService, FooRepository, FooProxy, FooConfig, FooListener, FooEvent, and the existing FooWindow pattern.

Do not use Manager or Handler as generic suffixes unless the class genuinely manages a subsystem or owns a boundary.

## Runtime discovery

Before renaming a package or class search component scanning, routing, manifests, strings, and tests.

Compile success alone does not prove runtime discovery remains correct.

