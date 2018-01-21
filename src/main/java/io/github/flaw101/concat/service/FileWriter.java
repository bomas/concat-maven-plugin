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

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;

import io.github.flaw101.concat.ConcatParams;

/**
 * Concatenates the given files to an output
 * 
 * @author Darren Forsythe
 * @since 1.1.0
 *
 */
public class FileWriter {

	public void write(ConcatParams params) {
		File outputFile = params.getOutputFile();
		if (params.isDeleteTargetFile()) {
			FileUtils.deleteQuietly(params.getOutputFile());
		}
		try {
			for (File inputFile : params.getFiles()) {
				String input = FileUtils.readFileToString(inputFile);
				FileUtils.writeStringToFile(params.getOutputFile(), input, true);
				if (params.isAppendNewline()) {
					FileUtils.writeStringToFile(outputFile, System.getProperty("line.separator"), true);

				}
			}
		} catch (IOException e) {
			throw new CannotWriteToOutputFileException(e);
		}
	}
}
