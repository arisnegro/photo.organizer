package es.com.arisnegro.photo.autotransfer;

import java.io.IOException;
import java.text.MessageFormat;

import es.com.arisnegro.photo.autotransfer.model.Model;

public class PhotoAutoTransfer {

	private static final String SOURCE_DIR = "C:/Users/asnegro/Desktop/Photos";
	private static final String DESTINATION_DIR = "C:/Users/asnegro/Desktop/Photos";

	public static void main(String[] args) throws AutoTransferException, IOException {
		Model m;

		m = new Model();
		System.out.println(MessageFormat.format("Moving from {0} to {1}", SOURCE_DIR, DESTINATION_DIR));
		m.transferFiles(SOURCE_DIR, DESTINATION_DIR);
		System.out.println("Finish!!");
	}

}