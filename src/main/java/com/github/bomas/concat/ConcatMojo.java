package com.github.bomas.concat;

/*
 * Copyright 2001-2005 The Apache Software Foundation.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

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
 * @goal concat
 * 
 * @Mojo( defaultPhase = "process-sources" )
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
	private boolean appendNewline=false;

	
	/* (non-Javadoc)
	 * @see org.apache.maven.plugin.AbstractMojo#execute()
	 */
	public void execute() throws MojoExecutionException {
		if(validate()){
			getLog().debug("Going to concatenate files to destination file: " + outputFile.getAbsolutePath());
			try {
				for(File inputFile : concatFiles){
					getLog().debug("Concatenating file: " + inputFile.getAbsolutePath());
					String input = FileUtils.readFileToString(inputFile);
					FileUtils.writeStringToFile(outputFile, input, true);
					if(appendNewline){
						FileUtils.writeStringToFile(outputFile, System.getProperty("line.separator"), true);
					}
					
				}
			}catch(IOException e){
				throw new MojoExecutionException("Failed to concatenate", e);
			}
		}
	}
	
	private boolean validate() throws MojoExecutionException{
		if(outputFile == null){
			throw new MojoExecutionException("Please specify a correct outPutFile");
		}
		
		if(concatFiles == null || concatFiles.size() ==0){
			throw new MojoExecutionException("Please specify the file(s) to concatenate");
		}else{
			for(File file : concatFiles){
				if(!file.exists()){
					throw new MojoExecutionException(file.getAbsolutePath() + " does not exists.");
				}
			}
				
		}
		
		return true;
	}
}
