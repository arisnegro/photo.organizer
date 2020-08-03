package es.com.arisnegro.photo.autotransfer.model;

import java.io.File;
import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.lang3.StringUtils;

import es.com.arisnegro.photo.autotransfer.AutoTransferException;

/**
 * Utility class used to work with files and file names.
 *
 * @author Aristonico Silvano Negro Diez
 */
public class FileUtils {

	/**
	 * Return a list with all files in a directory.
	 *
	 * @param path path for search
	 *
	 * @return the list of all files
	 */
	public static List<String> getAllFilesInADirectory(String path) {
		List<String> fileNames;
		File         folder;
		File[]       listOfFiles;

		fileNames   = new ArrayList<String>();
		folder      = new File(path);
		listOfFiles = folder.listFiles();

		if (listOfFiles != null) {

			for (File f : listOfFiles) {

				if (f.isFile()) {

					fileNames.add(f.getName());
			    }
			}
		} else {
			fileNames = null;
		}
		return fileNames;
	}

	/**
	 * Convert the file name to directory name.
	 *
	 * @param fileName the name of the file.
	 *
	 * @return the name of the directory.
	 */
	public static String convertToDirectoryName(String fileName) {
		String dirName;

		if (StringUtils.isNotEmpty(fileName)) {
			Pattern p;
			Matcher m;

			// Pattern ^([0-9]{4})-([0-9]{2})-([0-9]{2})
			p = Pattern.compile("^([0-9]{4})-([0-9]{2})-([0-9]{2})");
			m = p.matcher(fileName);

			if (m.find()) {
				String year;
				String month;
				String day;

				year  = m.group(1);
				month = m.group(2);
				day   = m.group(3);
				dirName = MessageFormat.format("{0}_{1}_{2}", year, month, day);
			} else {

				// Pattern
				p = Pattern.compile("^[VI][IM][DG]-([0-9]{4})([0-9]{2})([0-9]{2})");
				m = p.matcher(fileName);

				if (m.find()) {
					String year;
					String month;
					String day;

					year  = m.group(1);
					month = m.group(2);
					day   = m.group(3);
					dirName = MessageFormat.format("{0}_{1}_{2}", year, month, day);
				} else {
					dirName = null;
				}
			}
		} else {
			dirName = null;
		}
		return dirName;
	}

	/**
	 * Checks if the directory exist
	 *
	 * @param path the directory
	 *
	 * @return if the directory exist or not
	 */
	public static boolean existDirectory(String path) {
		File f;

		f = new File(path);
		return f.isDirectory();
	}

	/**
	 * Gets the full path of a file
	 *
	 * @param source the source directory
	 * @param fileName the file name
	 *
	 * @return the full path file
	 */
	public static String getFullFilePath(String source, String fileName) {
		StringBuilder fullFilePath;
    	File          tmp;

    	tmp          = new File(source);
		fullFilePath = new StringBuilder();
		fullFilePath.append(tmp.getAbsolutePath());
		fullFilePath.append(File.separator);
		fullFilePath.append(fileName);
		return fullFilePath.toString();
	}

	/**
	 * Creates the directory if it does not exist
	 *
	 * @param source the parent directory
	 * @param directory the path of the directory
	 *
	 * @throws AutoTransferException if exist any problem creating the directory
	 */
     public static void mkDir(String parent, String directory) throws AutoTransferException {
    	StringBuilder fullPath;
    	File          f;
    	File          tmp;

    	tmp      = new File(parent);
 		fullPath = new StringBuilder();
 		fullPath.append(tmp.getAbsolutePath());
 		fullPath.append(File.separator);
 		fullPath.append(directory);

		f = new File(fullPath.toString());

		if (!f.exists()) {
			boolean created;

			created = f.mkdir();

			if (!created) {

				throw new AutoTransferException(MessageFormat.format("The directory {0} cannot be created", directory));
			}
		}
	}
}