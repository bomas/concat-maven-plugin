/**
 * MIT License
 *
 * Copyright (c) 2018 Darren Forsythe
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:

 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.

 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */
package io.github.flaw101.concat;

import java.io.File;
import java.io.IOException;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.apache.maven.plugin.AbstractMojo;
import org.apache.maven.plugin.MojoExecutionException;

/**
 * Goal which concatenates several files and creates a new file as specified.
 * 
 * @Mojo( name = "concat" )
 * 
 * @goal concat
 * 
 * 		@Mojo( defaultPhase = "process-sources" )
 * @phase process-sources
 */
public class ConcatMojo extends AbstractMojo {

	/**
	 * The resulting file
	 * 
	 * @parameter
	 * @required
	 */
	private File outputFile;

	/**
	 * Files to concatenate
	 * 
	 * @parameter
	 * @required
	 */
	private List<File> concatFiles;

	/**
	 * Append newline after each concatenation
	 * 
	 * @parameter
	 */
	private boolean appendNewline = false;

	/**
	 * Deletes the target file before concatenation
	 * 
	 * @parameter
	 */
	private boolean deleteTargetFile = false;

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.apache.maven.plugin.AbstractMojo#execute()
	 */
	public void execute() throws MojoExecutionException {
		validate();
		if (deleteTargetFile) {
			FileUtils.deleteQuietly(outputFile);
		}
		concatFiles();
	}

	private void concatFiles() throws MojoExecutionException {
		try {
			getLog().debug("Going to concatenate files to destination file: " + outputFile.getAbsolutePath());
			for (File inputFile : concatFiles) {
				getLog().debug("Concatenating file: " + inputFile.getAbsolutePath());
				String input = FileUtils.readFileToString(inputFile);
				FileUtils.writeStringToFile(outputFile, input, true);
				if (appendNewline) {
					FileUtils.writeStringToFile(outputFile, System.getProperty("line.separator"), true);
				}

			}
		} catch (IOException e) {
			throw new MojoExecutionException("Failed to concatenate", e);
		}
	}

	private void validate() throws MojoExecutionException {
		if (outputFile == null) {
			throw new MojoExecutionException("Please specify a correct outPutFile");
		}

		if (concatFiles == null || concatFiles.isEmpty()) {
			throw new MojoExecutionException("Please specify the file(s) to concatenate");
		} else {
			for (File file : concatFiles) {
				if (!file.exists()) {
					throw new MojoExecutionException(file.getAbsolutePath() + " does not exists.");
				}
			}

		}
	}
}
