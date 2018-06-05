package com.allan.filelineparser;

import java.io.IOException;
import java.util.List;

/**
 * Test class for testing {@link LineParserFactory} and
 * {@link TabDelimitedLineParser}
 * 
 * @author Allan Shaji Manamel
 *
 */
public class TestTabDelimitedLineParser {

	/**
	 * @param args
	 * @throws IOException
	 */
	public static void main(String[] args) throws IOException {
		List<String> result;
		ILineParser lineParser = LineParserFactory.getLineParser("test_file.tab");
		while (true) {
			StringBuilder builder = new StringBuilder();
			result = lineParser.getNexLineTokens();
			if (result == null) {
				break;
			}
			for (String value : result) {
				builder.append(value);
				builder.append(", ");
			}
			builder.delete(builder.length() - 2, builder.length());
			System.out.println(builder.toString());
		}

	}
}
