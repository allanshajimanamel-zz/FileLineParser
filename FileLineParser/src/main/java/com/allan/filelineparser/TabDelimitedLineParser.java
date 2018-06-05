package com.allan.filelineparser;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.NoSuchElementException;

/**
 * An instance of {@link ILineParser} that gets lline tokens separated by tabs.
 * 
 * @author Allan Shaji Manamel
 *
 */
public class TabDelimitedLineParser implements ILineParser {

	/** The reader that is being read. */
	private BufferedReader in = null;
	/** The current line. */
	private String cachedString = null;

	public TabDelimitedLineParser(File file) throws IOException {
		if (file == null) {
			throw new IllegalArgumentException("File must not be null");
		}

		try {
			FileReader reader = new FileReader(file);
			in = new BufferedReader(reader);
			cachedString = in.readLine();
			if (cachedString == null) {
				in.close();
				in = null;
			}
		} catch (IOException ex) {
			cachedString = null;
			if (in != null)
				try {
					in.close();
				} catch (IOException ex2) {
					// Ignore
				}
			in = null;
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.allan.filelineiterator.ILineParser#getNexLineTokens()
	 */
	@Override
	public List<String> getNexLineTokens() throws IOException {
		if (!hasNext()) {
			return null;
		}
		String line = next();
		// if (line == null) {
		// return null;
		// }

		return Arrays.asList(line.split("\\t"));
	}

	/**
	 * Checks if file has more lines to read.
	 * 
	 * @return {@link true} if there are more lines to read from file else
	 *         {@link false}
	 */
	public boolean hasNext() {
		return cachedString != null;
	}

	/**
	 * Gets the next line from the file
	 * 
	 * @return {@link String} representing next line from file.
	 * @throws NoSuchElementException
	 */
	public String next() throws NoSuchElementException {
		String returnString = cachedString;
		try {
			if (cachedString == null) {
				return null;
			} else {
				cachedString = in.readLine();
				if (cachedString == null && in != null) {
					in.close();
					in = null;
				}
			}
		} catch (Exception ex) {
			throw new NoSuchElementException("Exception caught while reading from file: " + ex);
		}
		return returnString;
	}

	@Override
	protected void finalize() throws Throwable {
		try {
			cachedString = null;
			if (in != null)
				try {
					in.close();
				} catch (Exception ex) {
					// ignore
				}
			in = null;
		} finally {
			super.finalize();
		}
	}

}
