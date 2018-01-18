# Concat Maven Plugin #

Maven Plugin to allow concatenation of files.

Forked from [Bomas Contcat Maven Plugin](https://github.com/bomas/concat-maven-plugin "https://github.com/bomas/concat-maven-plugin").

This plugin is created against `3.2.5`, the last supported java 1.6 maven release.

## Usage ##

A basic example, which will concat the `.input` files to the `concatfile.output`

```xml
<plugin>
    <groupId>com.flawless101</groupId>
    <artifactId>concat-maven-plugin</artifactId>
    <configuration>
        <outputFile>target/concatfile.output</outputFile>
        <concatFiles>
            <param>src/test/resources/testfiles/file_1.input</param>
            <param>src/test/resources/testfiles/file_2.input</param>
            <param>src/test/resources/testfiles/file_3.input</param>
        </concatFiles>
    </configuration>
</plugin>
```

### Additional Params ###

* `appendNewline`
  * Defaults to false, will add a new line character between each concatenated file.
* `deleteTargetFile`
  * Defaults to false, if they target file should be deleted before concatenation.

## Change Log ##

Project uses semantic versioning.

1. Major - Breaking changes.
1. Minor - New backwards compatible change.
1. Patch - Bug fixes, polish etc.

### 1.0.0 ###

* Correct maven dependencies to actually be able to test the project.
* Add ability to delete the target file.