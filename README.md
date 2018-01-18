# Concat Maven Plugin

Maven Plugin to allow concatenation of files. 

Forked from [Bomas Contcat Maven Plugin](https://github.com/bomas/concat-maven-plugin "https://github.com/bomas/concat-maven-plugin").

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
  

## Change Log ##

Project uses semantic versioning. 

Major - Breaking changes.
Minor - New backwards compatible change.
Patch - Bug fixes, polish etc. 


### 1.0.0 ###

* Correct maven dependencies to actually be able to test the project.
* Add ability to delete the target file.