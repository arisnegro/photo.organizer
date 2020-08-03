package es.com.arisnegro.photo.autotransfer.model;

import static org.junit.Assert.*;

import java.util.List;

import junitparams.JUnitParamsRunner;
import junitparams.Parameters;

import org.junit.Test;
import org.junit.runner.RunWith;

/**
 * Utility class used to work with files and file names.
 *
 * @author Aristonico Silvano Negro Diez
 */
@RunWith(JUnitParamsRunner.class)
public class FileUtilsTest {

	/** A directory for the test */
	private static final String A_DIRECTORY = "c:/";

	@Test
	public void shouldGetAllFilesInADirectory() {
		List<String> fileNames;

		fileNames = FileUtils.getAllFilesInADirectory(A_DIRECTORY);
		assertNotNull("The list of files cannot be null", fileNames);
		assertFalse("The list of files cannot be empty", fileNames.isEmpty());
	}

	/**
	 * Check that the method convert the directory name correctly.
	 */
	@Test
	@Parameters({
        "2015-11-21 22.51.32.jpg, 2015_11_21"
    })
	public void shouldConvertToDirectoryName(String fileName, String expectedDirName) {
		String dirName;

		dirName = FileUtils.convertToDirectoryName(fileName);
		assertEquals("The directory name is not generated successful", expectedDirName, dirName);
	}
}