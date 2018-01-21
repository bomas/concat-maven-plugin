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
package io.github.flaw101.concat.service;

import io.github.flaw101.concat.ConcatParams;

/**
 * Writes out to the Output file. Default is to just write the
 * {@link ConcatParams#getFiles()} to the Output file. Other types of
 * ConcatenationTypes will defer to other classes to setup the input files.
 * 
 * @author Darren Forsythe
 * @since 1.1.0
 *
 */
public class FileWriterService {

	private FileWriter defaultWriter;
	private OutputSetup directorySetup;

	public FileWriterService() {
		directorySetup = new DirectorySetup();
		defaultWriter = new FileWriter();

	}

	/**
	 * Sets up, if required, and writes to the {@link ConcatParams#getOutputFile()}
	 * 
	 * @param concatParams
	 *            - parameters for this plugin
	 */
	public void writeToOutputfile(ConcatParams concatParams) {
		switch (concatParams.getConcatenationType()) {
		case DIRECTORY:
			directorySetup.setup(concatParams);
			defaultWriter.write(concatParams);
			break;
		case FILE_LIST:
			defaultWriter.write(concatParams);
			break;
		default:
			throw new IllegalArgumentException("Concantenation Type not implemented");
		}
	}

}
