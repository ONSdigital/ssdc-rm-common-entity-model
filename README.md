# ssdc-rm-common-entity-model

RM has a complicated data model, which spans surveys, collection exercises, cases, events, action rules and suchlike, but also has other extra data which controls role-based access control (RBAC) sample validation, and a whole host of other things.

The data model governs every part of RM so it makes no sense to attempt to slice it into pieces. Instead, the data model is common to all parts of RM, because they are interested in all the same kinds of things.

## How does this work?

Commit changes to the `main` branch of this repo, and Cloud Build will build the code, package it up into a JAR file, and deploy it to Artifact Registry, where it can be included as a dependency in any projects that need it. Read the next section for more specific instructions on how to develop using this new pattern.

## How do I develop and test changes to the model?

The process:
1. Clone this repo (if required)
2. Create a branch
3. IMPORTANT: update the `version` at the top of `pom.xml` to be a unique version with your name in it... for example `123-FRED-BLOGGS`
4. Make your changes
5. Build your changes _locally_ with `mvn clean install`
6. Change your other projects to use your version, by altering the dependency version
7. Make your code changes in the other projects
8. When you're done, update update the `version` at the top of `pom.xml` to increment as a major or minor version, depending on whether the changes are breaking or not. **BE CAREFUL** to check that nobody else has published a branch which uses the version you want to use.
9. Publish your branch and raise your pull request... note, this will actually end up building the snapshot
10. IF MODEL HAS CHANGED: Update the DDL in the ssdc-rm-ddl repo, based on your branch of this repo... and raise PR etc
11. Update dependent projects to use the 'proper' version, and raise pull requests on them

This will allow other developers to easily build and test your changes.

## What projects use this code?

Currently it is:
* Case Processor
* Case API
* Response Operations
* Notify Service
* Support Tool

Obviously, keeping 31+ classes up to date across those 5 projects would be a nightmare, so this project allows those common classes to be shared across the projects.

## Why does the sample validation live in here?

Well, the sample validation belongs to a survey, but it also tells us what columns are in the sample, which is useful information to know.

It makes sense to keep the validation stuff in here, because once the sample validation is stored against a survey in the database, it can't be changed without potentially breaking the integrity of the data stored in RM.

In short, changing anything to do with validation needs to be release managed very carefully... especially breaking changes.

## But aren't shared data models an antipattern?
What would you have instead? A shared RESTful CRUD API instead of a shared data model? That would suffer the same tight coupling problems. In fact it would be WORSE because all microservices would then have a single point of failure on the CRUD REST API, instead of the high availability Google Cloud SQL instance, which Google guarantee 99.999% uptime on.

In short, no it's not an antipattern. Distributed monoliths are the antipattern, which this pattern is the alternative to.