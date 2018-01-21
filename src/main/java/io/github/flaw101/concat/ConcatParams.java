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
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

/**
 * Paramerters required for building the output file and validation.
 * 
 * @author Darren Forsythe
 * @Since 1.1.0
 *
 */
public class ConcatParams {

	private final String directory;
	private final File outputFile;
	private final boolean deleteTargetFile;
	private final boolean appendNewline;
	private final Collection<File> files = new ArrayList<File>();
	private final ConcantenationType concatenationType;

	public ConcatParams(String directory, List<File> files, File outputFile, boolean deleteTargetFile,
			boolean appendNewline, ConcantenationType concatentationType) {
		this.directory = directory;
		if (files != null) {
			this.files.addAll(files);
		}
		this.concatenationType = concatentationType;
		this.outputFile = outputFile;
		this.deleteTargetFile = deleteTargetFile;
		this.appendNewline = appendNewline;
	}

	public boolean isAppendNewline() {
		return appendNewline;
	}

	public File getOutputFile() {
		return outputFile;
	}

	public boolean isDeleteTargetFile() {
		return deleteTargetFile;
	}

	public ConcantenationType getConcatenationType() {
		return concatenationType;
	}

	public Collection<File> getFiles() {
		return Collections.unmodifiableCollection(files);
	}

	public void addAll(Collection<File> files) {
		this.files.addAll(files);
	}

	public String getDirectory() {
		return directory;
	}
}
