/**
 * 
 */
package com.allan.filelineiterator;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;

import com.allan.filelineparser.ILineParser;
import com.allan.filelineparser.LineParserFactory;
import com.allan.filelineparser.TabDelimitedLineParser;

/**
 * @author User
 *
 */
public class TabDelimitedLineParserTest {

	@Test(expected = IllegalArgumentException.class)
	public void testEmptyFileName() throws IOException {
		LineParserFactory.getLineParser("");
	}
	
	@Test(expected = FileNotFoundException.class)
	public void testFileNotExists() throws IOException {
		LineParserFactory.getLineParser("test_file_not_exist.tab");
	}
	
	
	@Test(expected = IllegalArgumentException.class)
	public void testFileIllegalFileName() throws IOException {
		LineParserFactory.getLineParser("test_file_no_extension");
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void testFileUnsupportedExtension() throws IOException {
		LineParserFactory.getLineParser("test_file_unsupported_extension.comma");
	}
	
	@Test
	public void testGetFileParser() throws IOException {
		ILineParser lineParser = LineParserFactory.getLineParser("test_file.tab");
		Assert.assertNotNull(lineParser);
		Assert.assertTrue(lineParser instanceof TabDelimitedLineParser);
	}
	
	@Test
	public void testGetTokensForEmptyFile() throws IOException {
		ILineParser lineParser = LineParserFactory.getLineParser("test_file_empty.tab");
		Assert.assertNull(lineParser.getNexLineTokens());
	}
	
	@Test
	public void testGetTokensForTabbedSpaces() throws IOException {
		ILineParser lineParser = LineParserFactory.getLineParser("test_file_tabbed_spaces.tab");
		List<String> result = lineParser.getNexLineTokens();
		Assert.assertEquals(3, result.size());
		Assert.assertNull(lineParser.getNexLineTokens());
	}
	
	@Test
	public void testGetTokens() throws IOException {
		ILineParser lineParser = LineParserFactory.getLineParser("test_file.tab");
		List<String> result = lineParser.getNexLineTokens();
		Assert.assertEquals(4, result.size());
		Assert.assertTrue("This".equals(result.get(0)));
		Assert.assertTrue("is".equals(result.get(1)));
		Assert.assertTrue("a".equals(result.get(2)));
		Assert.assertTrue("test".equals(result.get(3)));
		
		result = lineParser.getNexLineTokens();
		Assert.assertEquals(3, result.size());
		Assert.assertTrue("red".equals(result.get(0)));
		Assert.assertTrue("green".equals(result.get(1)));
		Assert.assertTrue("blue".equals(result.get(2)));
		
		Assert.assertNull(lineParser.getNexLineTokens());
	}
	
}
