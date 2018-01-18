/**
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
package io.github.flaw101.concat;

import java.io.File;

import org.apache.commons.io.FileUtils;
import org.apache.maven.plugin.MojoExecutionException;
import org.apache.maven.plugin.testing.AbstractMojoTestCase;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import io.github.flaw101.concat.ConcatMojo;

public class ConcatMojoTest extends AbstractMojoTestCase {
	private static String NEWLINE = System.getProperty("line.separator");

	/** {@inheritDoc} */
	@Before
	protected void setUp() throws Exception {
		super.setUp();
		FileUtils.deleteQuietly(new File("target/concatfile.output"));
	}

	/** {@inheritDoc} */
	@After
	protected void tearDown() throws Exception {
		super.tearDown();
	}

	@Test
	public void testConcat() throws Exception {
		execute("src/test/resources/test-pom.xml", "concat", "target/concatfile.output", "file1file2file3");
	}
	
	@Test
	public void testConcatShouldNotDeleteTarget() throws Exception {
		FileUtils.copyFile(new File("src/test/resources/testfiles/file_1.input"), new File("target/concatfile.output"));
		execute("src/test/resources/test-pom.xml", "concat", "target/concatfile.output", "file1file1file2file3");
	}

	@Test
	public void testConcatDeleteTargetFile() throws Exception {
		FileUtils.copyFile(new File("src/test/resources/testfiles/file_1.input"), new File("target/concatfile.output"));
		execute("src/test/resources/test-delete-target-file-pom.xml", "concat", "target/concatfile.output",
				"file1file2file3");
	}

	@Test
	public void testConcatNewline() throws Exception {
		String expectedResult = "file1" + NEWLINE + "file2" + NEWLINE + "file3" + NEWLINE;
		execute("src/test/resources/test-newline-pom.xml", "concat", "target/concatfile.output", expectedResult);

	}

	@Test
	public void testMissingOutputParameter() throws Exception {
		try {
			execute("src/test/resources/test-missing-output-pom.xml", "concat", "bla", "nothing");
		} catch (Exception e) {
			assertEquals(MojoExecutionException.class, e.getClass());
		}
	}

	@Test
	public void testMissingConcatParameter() throws Exception {
		try {
			execute("src/test/resources/test-missing-concat-pom.xml", "concat", "bla", "nothing");
		} catch (Exception e) {
			assertEquals(MojoExecutionException.class, e.getClass());
		}
	}

	private void execute(String pomPath, String mojo, String resultFile, String expectedResult) throws Exception {
		File pom = getTestFile(pomPath);
		assertNotNull(pom);
		assertTrue(pom.exists());

		ConcatMojo concatMojo = (ConcatMojo) lookupMojo(mojo, pom);
		assertNotNull(concatMojo);
		concatMojo.execute();

		File outputFile = getTestFile(resultFile);
		assertNotNull(outputFile);
		assertTrue(outputFile.exists());
		String output = FileUtils.readFileToString(outputFile);
		assertEquals(expectedResult, output);
	}

}
