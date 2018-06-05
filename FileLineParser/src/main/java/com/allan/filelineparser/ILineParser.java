package com.allan.filelineparser;

import java.io.IOException;
import java.util.List;

/**
 * Interface for a file line parser
 * 
 * @author Allan Shaji Manamel
 *
 */
public interface ILineParser {
	/**
	 * Gets the tokens from the next line in file, else return null if reached end
	 * of file or if file is empty.
	 * 
	 * @return a {@link List} of {@link String} containing the tokens of line from
	 *         file or null.
	 * @throws IOException
	 */
	public List<String> getNexLineTokens() throws IOException;
}
