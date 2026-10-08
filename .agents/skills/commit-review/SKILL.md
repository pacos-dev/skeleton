---
name: skeleton-commit-review
description: Mandatory final validation gate for skeleton commit proposals.
---

# Skeleton Commit Review

Use this skill immediately before suggesting a commit.

## Pass 1

Review changed files, package placement, imports, names, dependency changes, generated files, and exact scope.

## Pass 2

Re-read complete changed files and verify Java and Maven syntax, Spring annotations, bean names, extension signatures, and test assumptions against current skeleton and core source.

## Pass 3

Review the final diff for accidental scope expansion.

Re-check test coverage, runtime mode, packaging, permissions, persistence, API documentation, lifecycle, and documentation impact.

## Required discipline

Repeat these checks 2–3 times for every commit you suggest.

Do not claim builds or tests were run unless they were actually executed.

Prefer one coherent commit per logical change.

The commit message must describe the actual change.

