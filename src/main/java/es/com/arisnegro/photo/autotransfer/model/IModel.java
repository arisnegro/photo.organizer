package es.com.arisnegro.photo.autotransfer.model;

import java.io.IOException;

import es.com.arisnegro.photo.autotransfer.AutoTransferException;

public interface IModel {

	/**
	 * Transfer the files from source directory to destination.
	 *
	 * @param source source directory.
	 * @param destination destination directory.
	 *
	 * @throws AutoTransferException if any problem exist
	 * @throws IOException if exist any problem moving the files
	 */
	public void transferFiles(String source, String destination) throws AutoTransferException, IOException;
}
