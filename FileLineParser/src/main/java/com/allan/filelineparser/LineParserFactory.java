package com.allan.filelineparser;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

/**
 * The factory class to get an instance of ILineParser for parsing a file.
 * 
 * The returned instance of {@link ILineParser} is based on the extension of the
 * file name given.
 * 
 * @author User
 *
 */
public class LineParserFactory {

	/**
	 * Returns an instance of {@link ILineParser}
	 * @param fileName The name of file to parse
	 * @return Instance of {@link ILineParser}
	 * @throws IOException
	 */
	public static ILineParser getLineParser(String fileName) throws IOException {
		if (fileName.trim().isEmpty()) {
			throw new IllegalArgumentException("Filename cannot be empty");
		}
		File file = new File(fileName);
		if (!file.exists())
			throw new FileNotFoundException("File does not exist: " + file.getPath());
		if (!file.isFile())
			throw new IOException("File is not of type 'file': " + file.getPath());

		int fileTypeBeginIndex = fileName.lastIndexOf(".");

		if (fileTypeBeginIndex == -1) {
			throw new IllegalArgumentException("Illegal file name");
		}

		String fileType = fileName.substring(fileTypeBeginIndex + 1, fileName.length());

		switch (fileType) {
		case "tab":
			return new TabDelimitedLineParser(file);
		default:
			throw new IllegalArgumentException("File type not supported");
		}
	}
}
