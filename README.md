# Concat Maven Plugin #

Maven Plugin to allow concatenation of files.

Forked from [Bomas Contcat Maven Plugin](https://github.com/bomas/concat-maven-plugin "https://github.com/bomas/concat-maven-plugin").

This plugin is compile to maven `3.2.5`, the last supported java 1.6 maven release.

The plugin can concat any file the plugin has access to, just provide the correct path.

The plugin is hosted on Maven Central,


```xml
<dependency>
    <groupId>io.github.flaw101</groupId>
    <artifactId>concat-maven-plugin</artifactId>
    <version><!-- A Version --> </version>
</dependency>
```

## File List Usage ##

A basic example, which will concat the `.input` files to the `concatfile.output`.

```xml
<plugin>
    <groupId>io.github.flaw101</groupId>
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

## Directory Usage ##

Will concat all files in the directory to the output file.

```xml
<plugin>
    <groupId>io.github.flaw101</groupId>
    <artifactId>concat-maven-plugin</artifactId>
    <configuration>
        <outputFile>target/concatfile.output</outputFile>
        <directory>src/test/resources/testfiles/</directory>
        <concatenationType>
            DIRECTORY
        </concatenationType>
    </configuration>
</plugin>
```

## Params ##

* `appendNewline`
  * Defaults to false, will add a new line character between each concatenated file.
* `deleteTargetFile`
  * Defaults to false, if they target file should be deleted before concatenation.
* `concatenationType`
  * For selecting different types of concatentation implements. Defaults to requiring a list of files as specified in the basic example.
* `directory`
  * When using `ConcatenationType.DIRECTORY` specify the directory from which to get all files. Natural ordering of the file name is used to sort the files.

## Change Log ##

Project uses semantic versioning.

1. Major - Breaking changes.
1. Minor - New backwards compatible change.
1. Patch - Bug fixes, polish etc.

### 1.0.0 ###

* Correct maven dependencies to actually be able to test the project.
* Add ability to delete the target file.

### 1.1.0 ###

* Add ability to concat by a directory.