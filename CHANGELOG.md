# Changelog

## 0.5.5

_2016-07-26_

 * Fix error in functional.flatMap (causing double executions)
 * Upgrade to sbt 0.13.12
 * Upgrade to sbt-android 1.6.9
 * Upgrade to cats 0.6.1

## 0.5.4

_2016-07-25_

 * Move `app.contract.Task` to `concurrent.app.contract.Job`

## 0.5.3

_2016-07-25_

 * Added compat.Configuration.locale

## 0.5.2

_2016-07-13_

 * Add compat.Html.fromHtml
 * Set platform and build target to 24
 * Upgrade to sbt-scalariform 1.7.0
 * Upgrade to sbt-sonatype 1.1.0
 * Upgrade to sbt-android 1.6.7

## 0.5.1

_2016-07-11_

 * Fix system service resolvers inferring `Nothing`

## 0.5.0

_2016-07-05_

 * Migration to multi-project build
 * Migration to monix.Task (from scala.concurrent.Future)
 * Modular project setup (from monolith to log, core, concurrent)
 * Upgrade to sbt-android 1.6.6