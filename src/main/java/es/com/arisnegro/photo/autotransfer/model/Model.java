package es.com.arisnegro.photo.autotransfer.model;

import java.io.File;
import java.io.IOException;
import java.text.MessageFormat;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;

import es.com.arisnegro.photo.autotransfer.AutoTransferException;

public class Model implements IModel {

	@Override
	public void transferFiles(String source, String destination) throws AutoTransferException, IOException {
		List<String> fileNames;
		Set<String>  directories;

		/*
		 * Validations
		 */
		if (!FileUtils.existDirectory(source)) {

			throw new AutoTransferException(MessageFormat.format("The directory {0} doesn't exist.", source));
		}

		if (!FileUtils.existDirectory(destination)) {

			throw new AutoTransferException(MessageFormat.format("The directory {0} doesn't exist.", destination));
		}

		// Get the list of files
		fileNames = FileUtils.getAllFilesInADirectory(source);

		if (CollectionUtils.isEmpty(fileNames)) {

			throw new AutoTransferException("There is no any files to proccess.");
		}

		// Get the list of new directories
		directories = new HashSet<>();

		for (String fileName : fileNames) {

			directories.add(FileUtils.convertToDirectoryName(fileName));
		}

		// Create the directories
		for (String directory : directories) {

			System.out.println(MessageFormat.format("Creating the directory {0} if it does not exist", directory));
			FileUtils.mkDir(destination, directory);
		}

		// Move each file to its directory
		for (String fileName : fileNames) {
			StringBuilder sourceFile;
			StringBuilder destinationFile;
			String        dirName;

			dirName = FileUtils.convertToDirectoryName(fileName);

			if (StringUtils.isNotEmpty(dirName)) {

				sourceFile = new StringBuilder(new File(source).getAbsolutePath());
				sourceFile.append(File.separator);
		 		sourceFile.append(fileName);
		 		destinationFile = new StringBuilder(new File(destination).getAbsolutePath());
		 		destinationFile.append(File.separator);
		 		destinationFile.append(dirName);
		 		destinationFile.append(File.separator);
		 		destinationFile.append(fileName);
				System.out.println(MessageFormat.format("Moving file \"{0}\" to \"{1}\"", sourceFile, destinationFile));
				org.apache.commons.io.FileUtils.moveFile(new File(sourceFile.toString()), new File(destinationFile.toString()));
			} else {
				System.out.println(MessageFormat.format("Skipping file \"{0}\"", fileName));
			}
		}
	}
}